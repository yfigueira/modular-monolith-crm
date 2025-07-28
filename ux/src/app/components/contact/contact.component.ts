import {Component, inject, OnInit} from '@angular/core';
import {ContactService} from '../../services/contact/contact.service';
import {Contact} from '../../models/contact/contact';
import {ActivatedRoute, Router} from '@angular/router';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import {faAngleLeft, faFloppyDisk, faTrashCan, faUserPlus} from '@fortawesome/free-solid-svg-icons';
import {Location} from '@angular/common';
import {ButtonComponent, ButtonStyle} from '../common/form-elements/button/button.component';
import {InputComponent} from '../common/form-elements/input/input.component';
import {AccountSummary} from '../../models/account/account-summary';
import {AccountService} from '../../services/account/account.service';
import {SelectComponent} from '../common/form-elements/select/select.component';
import {JobTitle} from '../../models/contact/jobtitle/job-title';
import {ContactJobTitleService} from '../../services/contact/jobtitle/contact-job-title.service';
import {ContactPriority} from '../../models/contact/contact-priority';
import {Dialog} from '@angular/cdk/dialog';
import {NewJobTitleFormComponent} from './new-job-title-form/new-job-title-form.component';
import {ActivitiesComponent} from '../activities/activities.component';

@Component({
  selector: 'app-contact',
  imports: [
    FaIconComponent,
    ButtonComponent,
    InputComponent,
    SelectComponent,
    ActivitiesComponent
  ],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent implements OnInit{

  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private dialog = inject(Dialog);

  readonly activeId: string | null;

  contact: Contact = {
    firstName: '',
    lastName: '',
    email: ''
  }

  accounts: AccountSummary[] = [];
  jobTitles: JobTitle[] = [];

  constructor(
    private service: ContactService,
    private location: Location,
    private accountService: AccountService,
    private jobTitleService: ContactJobTitleService
  ) {
    this.activeId = this.route.snapshot.paramMap.get('id');
  }

  ngOnInit(): void {
    if (this.activeId) {
      this.service.get(this.activeId)
        .subscribe(data => this.contact = data)
    }
    this.accountService.getAll().subscribe(data => this.accounts = data);
    this.jobTitleService.getAll().subscribe(data => this.jobTitles = data);
  }

  updateContact(): void {
    this.service
      .update(this.contact)
      .subscribe(data => this.contact = data);
  }

  deleteContact(): void {
    this.service
      .delete(this.activeId!)
      .subscribe(() => this.goBack())
  }

  goBack(): void {
    this.location.back();
  }

  goToAccounts(): void {
    this.router.navigate(['/accounts'])
  }

  openNewJobTitleModal() {
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

  createJobTitle(jobTitle: JobTitle) {
    this.jobTitleService.create(jobTitle)
      .subscribe(data => this.jobTitles.push(data))
  }

  getAccountsOptions() {
    return this.accounts.map(acc => ({id: acc.id, label: acc.name}));
  }

  getJobTitlesOptions() {
    return this.jobTitles.map(j => ({id: j.id!, label: j.name}));
  }

  getContactPrioritiesOptions() {
    return Object.values(ContactPriority).map(p => ({id: p.code, label: p.label}))
  }

  protected readonly faAngleLeft = faAngleLeft;
  protected readonly faTrashCan = faTrashCan;
  protected readonly faFloppyDisk = faFloppyDisk;
  protected readonly ButtonStyle = ButtonStyle;
  protected readonly faUserPlus = faUserPlus;
}
