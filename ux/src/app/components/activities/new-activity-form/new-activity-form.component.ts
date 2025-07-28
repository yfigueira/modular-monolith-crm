import {Component} from '@angular/core';
import {InputComponent} from '../../common/form-elements/input/input.component';
import {Activity} from '../../../models/activity/activity';
import {SelectComponent} from '../../common/form-elements/select/select.component';
import {ButtonComponent, ButtonStyle} from '../../common/form-elements/button/button.component';
import {DialogRef} from '@angular/cdk/dialog';
import {ActivityType} from '../../../models/activity/activity-type';
import {ActivityStatus} from '../../../models/activity/activity-status';
import {User} from '../../../models/user/user';
import {UserService} from '../../../services/user/user.service';
import {AuthenticationService} from '../../../services/authentication/authentication.service';
import {DatetimeComponent} from '../../common/form-elements/datetime/datetime.component';

@Component({
  selector: 'app-new-activity-form',
  imports: [
    InputComponent,
    SelectComponent,
    ButtonComponent,
    DatetimeComponent
  ],
  templateUrl: './new-activity-form.component.html',
  styleUrl: './new-activity-form.component.css'
})
export class NewActivityFormComponent {

  users: User[] = [];
  defaultOwner = '';
  newActivity: Activity = {
    subject: '',
    description: '',
    scheduledAt: '',
    completedAt: '',
    type: 0,
    owner: ''
  }

  constructor(
    private dialogRef: DialogRef<Activity>,
    private userService: UserService,
    private authService: AuthenticationService
  ) {
    this.userService.getAll().subscribe(data => {
      this.users = data;
      this.defaultOwner = this.authService.profile?.id || '';
      this.newActivity = {
        subject: '',
        description: '',
        scheduledAt: '',
        completedAt: '',
        type: ActivityType.PHONE_CALL.code,
        owner: this.defaultOwner
      }
    });
  }

  handleCreateActivity(): void {
    this.dialogRef.close(this.newActivity);
  }

  handleClose(): void {
    this.dialogRef.close();
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

  protected readonly ButtonStyle = ButtonStyle;
}
