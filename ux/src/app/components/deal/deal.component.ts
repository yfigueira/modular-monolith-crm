import {Component, inject, OnInit} from '@angular/core';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {faAngleLeft, faFloppyDisk, faTrashCan} from '@fortawesome/free-solid-svg-icons';
import {Location} from '@angular/common';
import {ActivatedRoute} from '@angular/router';
import {DealService} from '../../services/deal/deal.service';
import {Deal} from '../../models/deal/deal';
import {ActivitySummary} from '../../models/activity/activity-summary';
import {ButtonComponent, ButtonStyle} from '../common/form-elements/button/button.component';
import {ActivitiesComponent} from '../activities/activities.component';
import {InputComponent} from '../common/form-elements/input/input.component';
import {SelectComponent} from '../common/form-elements/select/select.component';
import {User} from '../../models/user/user';
import {UserService} from '../../services/user/user.service';
import {ContactSummary} from '../../models/contact/contact-summary';
import {ContactService} from '../../services/contact/contact.service';
import {DatetimeComponent} from '../common/form-elements/datetime/datetime.component';

@Component({
  selector: 'app-deal',
  imports: [
    FaIconComponent,
    ButtonComponent,
    ActivitiesComponent,
    InputComponent,
    SelectComponent,
    DatetimeComponent
  ],
  templateUrl: './deal.component.html',
  styleUrl: './deal.component.css'
})
export class DealComponent implements OnInit{

  private route = inject(ActivatedRoute);

  deal: Deal = {
    id: '',
    title: '',
    stage: 0,
    expectedRevenue: '',
    expectedCloseDate: '',
    contact: '',
    owner: '',
    activities: []
  }

  activeId: string | null;

  users: User[] = [];
  contacts: ContactSummary[] = [];

  constructor(
    private service: DealService,
    private location: Location,
    private userService: UserService,
    private contactService: ContactService
  ) {
    this.activeId = this.route.snapshot.paramMap.get('id');
  }

  updateDeal(): void {
    this.service
      .update(this.deal)
      .subscribe(data => this.deal = data);
  }

  deleteDeal(): void {
    this.service
      .delete(this.activeId!)
      .subscribe(() => this.goBack());
  }

  ngOnInit(): void {
    if (this.activeId) {
      this.service.get(this.activeId).subscribe(data => this.deal = data)
    }
    this.userService.getAll().subscribe(data => this.users = data);
    this.contactService.getAll().subscribe(data => this.contacts = data);
  }

  goBack(): void {
    this.location.back();
  }

  getUsersOptions() {
    return this.users.map(u => ({id: u.id, label: `${u.firstName} ${u.lastName}`}));
  }

  getContactsOptions() {
    return this.contacts.map(c => ({id: c.id, label: `${c.firstName} ${c.lastName}`}));
  }

  protected readonly faAngleLeft = faAngleLeft;
  protected readonly faFloppyDisk = faFloppyDisk;
  protected readonly faTrashCan = faTrashCan;
  protected readonly ButtonStyle = ButtonStyle;
}
