import { Component } from '@angular/core';
import {ButtonComponent, ButtonStyle} from '../../common/form-elements/button/button.component';
import {InputComponent} from '../../common/form-elements/input/input.component';
import {Contact} from '../../../models/contact/contact';
import {DialogRef} from '@angular/cdk/dialog';

@Component({
  selector: 'app-new-account-contact-form',
  imports: [
    ButtonComponent,
    InputComponent
  ],
  templateUrl: './new-account-contact-form.component.html',
  styleUrl: './new-account-contact-form.component.css'
})
export class NewAccountContactFormComponent {

  protected readonly ButtonStyle = ButtonStyle;

  newContact: Contact = {
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: ''
  }

  constructor(
    public dialogRef: DialogRef<Contact>
  ) {
  }

  handleCreateContact(): void {
    this.dialogRef.close(this.newContact);
  }

  handleClose(): void {
    this.dialogRef.close();
  }
}
