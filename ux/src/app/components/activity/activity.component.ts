import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ActivityService} from '../../services/activity/activity.service';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import {faAngleLeft, faFloppyDisk, faTrashCan} from '@fortawesome/free-solid-svg-icons';
import {Location} from '@angular/common';
import {ButtonComponent, ButtonStyle} from '../common/form-elements/button/button.component';
import {Activity} from '../../models/activity/activity';
import {InputComponent} from '../common/form-elements/input/input.component';
import {DatetimeComponent} from '../common/form-elements/datetime/datetime.component';
import {ActivityType} from '../../models/activity/activity-type';
import {ActivityStatus} from '../../models/activity/activity-status';
import {User} from '../../models/user/user';
import {UserService} from '../../services/user/user.service';
import {SelectComponent} from '../common/form-elements/select/select.component';

@Component({
  selector: 'app-activity',
  imports: [
    FaIconComponent,
    ButtonComponent,
    InputComponent,
    DatetimeComponent,
    SelectComponent
  ],
  templateUrl: './activity.component.html',
  styleUrl: './activity.component.css'
})
export class ActivityComponent implements OnInit{

  private route = inject(ActivatedRoute);

  users: User[] = [];
  activity: Activity = {
    subject: '',
    type: 0,
    owner: ''
  }

  readonly activeId: string | null;

  constructor(
    private service: ActivityService,
    private location: Location,
    private userService: UserService
  ) {
    this.activeId = this.route.snapshot.paramMap.get('id');
    this.userService.getAll().subscribe(data => this.users = data);
  }

  ngOnInit(): void {
    if (this.activeId) {
      this.service.get(this.activeId).subscribe(data => this.activity = data)
    }
  }

  updateActivity(): void {
    this.service
      .update(this.activity)
      .subscribe(data => this.activity = data);
  }

  deleteActivity(): void {
    this.service
      .delete(this.activeId!)
      .subscribe(() => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }

  getActivityTypesOptions() {
    return Object.values(ActivityType).map(t => ({id: t.code, label: t.label}));
  }

  getActivityStatusesOptions() {
    return Object.values(ActivityStatus).map(s => ({id: s.code, label: s.label}));
  }

  getUsersOptions() {
    return this.users.map(user => ({id: user.id, label: `${user.firstName} ${user.lastName}`}));
  }

  protected readonly faAngleLeft = faAngleLeft;
  protected readonly ButtonStyle = ButtonStyle;
  protected readonly faTrashCan = faTrashCan;
  protected readonly faFloppyDisk = faFloppyDisk;
}
