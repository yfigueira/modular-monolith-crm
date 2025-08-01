import { Component } from '@angular/core';
import {DialogRef} from '@angular/cdk/dialog';
import { Lead } from '../../../models/lead/lead';
import {UserService} from '../../../services/user/user.service';
import {AuthenticationService} from '../../../services/authentication/authentication.service';
import {User} from '../../../models/user/user';
import {InputComponent} from '../../common/form-elements/input/input.component';
import {SelectComponent} from '../../common/form-elements/select/select.component';
import {LeadState} from '../../../models/lead/lead-state';
import {ButtonComponent, ButtonStyle} from '../../common/form-elements/button/button.component';

@Component({
  selector: 'app-new-lead-form',
  imports: [
    InputComponent,
    SelectComponent,
    ButtonComponent
  ],
  templateUrl: './new-lead-form.component.html',
  styleUrl: './new-lead-form.component.css'
})
export class NewLeadFormComponent {

  users: User[] = [];
  defaultOwner = '';
  newLead: Lead = {
    firstName: '',
    lastName: '',
    email: '',
    state: 0,
    owner: ''
  }

  constructor(
    private dialogRef: DialogRef<Lead>,
    private userService: UserService,
    private authService: AuthenticationService
  ) {
    this.userService.getAll().subscribe(data => this.users = data);
    this.defaultOwner = this.authService.profile?.id || '';
    this.newLead = {
      firstName: '',
      lastName: '',
      email: '',
      phoneNumber: '',
      subject: '',
      isActive: true,
      state: LeadState.NEW.code,
      owner: this.defaultOwner
    }
  }

  handleCreateLead(): void {
    this.dialogRef.close(this.newLead);
  }

  handleClose(): void {
    this.dialogRef.close();
  }

  getUsersOptions() {
    return this.users.map(user => ({id: user.id, label: `${user.firstName} ${user.lastName}`}));
  }

  protected readonly ButtonStyle = ButtonStyle;
}
