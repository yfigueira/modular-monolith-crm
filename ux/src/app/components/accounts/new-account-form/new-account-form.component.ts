import {Component, output} from '@angular/core';
import {InputComponent} from '../../common/form-elements/input/input.component';
import {ButtonComponent, ButtonStyle} from '../../common/form-elements/button/button.component';
import {Account} from '../../../models/account/account';
import {DialogRef} from '@angular/cdk/dialog';

@Component({
  selector: 'app-new-account-form',
  imports: [
    InputComponent,
    ButtonComponent
  ],
  templateUrl: './new-account-form.component.html',
  styleUrl: './new-account-form.component.css'
})
export class NewAccountFormComponent {

  newAccount: Account = {
    name: '',
    tin: '',
    webUrl: '',
    country: '',
    city: '',
    street: '',
    streetNumber: '',
    zipCode: ''
  }

  constructor(
    public dialogRef: DialogRef<Account>
  ) {
  }

  handleCreateAccount(): void {
    this.dialogRef.close(this.newAccount)
  }

  handleClose(): void {
    this.dialogRef.close()
  }

  protected readonly ButtonStyle = ButtonStyle;
}
