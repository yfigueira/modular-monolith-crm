import {Component, inject, OnInit} from '@angular/core';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {faAngleLeft, faFloppyDisk, faTrashCan} from "@fortawesome/free-solid-svg-icons";
import {Location} from '@angular/common';
import {ActivitiesComponent} from '../activities/activities.component';
import {Lead} from '../../models/lead/lead';
import {JobTitle} from '../../models/lead/jobtitle/job-title';
import {Company} from '../../models/lead/company/company';
import {LeadJobTitleService} from '../../services/lead/jobtitle/lead-job-title.service';
import {CompanyService} from '../../services/lead/company/company.service';
import {User} from '../../models/user/user';
import {UserService} from '../../services/user/user.service';
import {ActivatedRoute} from '@angular/router';
import {Dialog} from '@angular/cdk/dialog';
import {LeadService} from '../../services/lead/lead.service';
import {ButtonComponent, ButtonStyle} from '../common/form-elements/button/button.component';
import {InputComponent} from '../common/form-elements/input/input.component';
import {SelectComponent} from '../common/form-elements/select/select.component';
import {AuthenticationService} from '../../services/authentication/authentication.service';
import {NewCompanyFormComponent} from './new-company-form/new-company-form.component';
import {NewJobTitleFormComponent} from './new-job-title-form/new-job-title-form.component';

@Component({
  selector: 'app-lead',
  imports: [
    FaIconComponent,
    ActivitiesComponent,
    ButtonComponent,
    InputComponent,
    SelectComponent
  ],
  templateUrl: './lead.component.html',
  styleUrl: './lead.component.css'
})
export class LeadComponent implements OnInit {

  private route = inject(ActivatedRoute);
  private dialog = inject(Dialog);

  lead: Lead = {
    firstName: '',
    lastName: '',
    email: '',
    state: 0,
    owner: ''
  }

  activeId: string | null;

  users: User[] = [];
  jobTitles: JobTitle[] = [];
  companies: Company[] = [];
  defaultOwner = '';

  constructor(
    private service: LeadService,
    private location: Location,
    private userService: UserService,
    private authService: AuthenticationService,
    private jobTitleService: LeadJobTitleService,
    private companyService: CompanyService
  ) {
    this.activeId = this.route.snapshot.paramMap.get('id');
  }

  ngOnInit(): void {
    if (this.activeId) {
      this.service.get(this.activeId).subscribe(data => this.lead = data)
    }
    this.userService.getAll().subscribe(data => this.users = data);
    this.jobTitleService.getAll().subscribe(data => this.jobTitles = data);
    this.companyService.getAll().subscribe(data => this.companies = data);
    this.defaultOwner = this.authService.profile?.id || '';
  }

  updateLead() {
    this.service
      .update(this.lead)
      .subscribe(data => this.lead = data);
  }

  deleteLead() {
    this.service
      .delete(this.activeId!)
      .subscribe(() => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }

  openNewCompanyModal(): void {
    const dialogRef = this.dialog.open(
      NewCompanyFormComponent,
      { disableClose: true }
    );
    dialogRef.closed.subscribe(result => {
      if (result) {
        this.createCompany(result as Company);
      }
    })
  }

  createCompany(company: Company): void {
    this.companyService.create(company)
      .subscribe(data => this.companies.push(data));
  }

  openNewJobTitleModal(): void {
    const dialogRef = this.dialog.open(
      NewJobTitleFormComponent,
      { disableClose: true }
    );
    dialogRef.closed.subscribe(result => {
      if (result) {
        this.createJobTitle(result as JobTitle);
      }
    })
  }

  createJobTitle(jobTitle: JobTitle): void {
    this.jobTitleService.create(jobTitle)
      .subscribe(data => this.jobTitles.push(data));
  }

  getCompaniesOptions() {
    return this.companies.map(c => ({id: c.id!, label: c.name}));
  }

  getUsersOptions() {
    return this.users.map(u => ({id: u.id, label: `${u.firstName} ${u.lastName}`}));
  }

  getJobTitlesOptions() {
    return this.jobTitles.map(j => ({id: j.id!, label: j.name}));
  }

  protected readonly faAngleLeft = faAngleLeft;
  protected readonly faTrashCan = faTrashCan;
  protected readonly faFloppyDisk = faFloppyDisk;
  protected readonly ButtonStyle = ButtonStyle;
}
