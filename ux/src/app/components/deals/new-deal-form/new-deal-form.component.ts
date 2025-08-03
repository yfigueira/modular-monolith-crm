import { Component } from '@angular/core';
import {InputComponent} from '../../common/form-elements/input/input.component';
import {Deal} from '../../../models/deal/deal';
import {DatetimeComponent} from '../../common/form-elements/datetime/datetime.component';
import {ButtonComponent, ButtonStyle} from '../../common/form-elements/button/button.component';
import {User} from '../../../models/user/user';
import {ContactSummary} from '../../../models/contact/contact-summary';
import {UserService} from '../../../services/user/user.service';
import {AuthenticationService} from '../../../services/authentication/authentication.service';
import {ContactService} from '../../../services/contact/contact.service';
import {DialogRef} from '@angular/cdk/dialog';
import {DealStage} from '../../../models/deal/deal-stage';
import {SelectComponent} from '../../common/form-elements/select/select.component';

@Component({
  selector: 'app-new-deal-form',
  imports: [
    InputComponent,
    DatetimeComponent,
    ButtonComponent,
    SelectComponent
  ],
  templateUrl: './new-deal-form.component.html',
  styleUrl: './new-deal-form.component.css'
})
export class NewDealFormComponent {

  users: User[] = [];
  contacts: ContactSummary[] = [];
  newDeal: Deal = {
    title: '',
    owner: '',
    activities: []
  }
  defaultOwner = '';

  constructor(
    private dialogRef: DialogRef<Deal>,
    private userService: UserService,
    private contactService: ContactService,
    private authService: AuthenticationService,
  ) {
    this.userService.getAll().subscribe(data => this.users = data);
    this.contactService.getAll().subscribe(data => this.contacts = data);
    this.defaultOwner = this.authService.profile?.id || '';
    this.newDeal = {
      title: '',
      stage: DealStage.NEW.code,
      owner: this.defaultOwner,
      expectedRevenue: '',
      expectedCloseDate: '',
      activities: []
    }
  }

  handleCreateDeal(): void {
    this.dialogRef.close(this.newDeal);
  }

  handleClose(): void {
    this.dialogRef.close();
  }

  getUsersOptions() {
    return this.users.map(u => ({id: u.id, label: `${u.firstName} ${u.lastName}`}));
  }

  getContactsOptions() {
    return this.contacts.map(c => ({id: c.id, label: `${c.firstName} ${c.lastName}`}));
  }

  protected readonly ButtonStyle = ButtonStyle;
}
