import { Component } from '@angular/core';
import {ButtonComponent, ButtonStyle} from "../../common/form-elements/button/button.component";
import {InputComponent} from "../../common/form-elements/input/input.component";
import {DialogRef} from '@angular/cdk/dialog';
import {Company} from '../../../models/lead/company/company';

@Component({
  selector: 'app-new-company-form',
    imports: [
        ButtonComponent,
        InputComponent
    ],
  templateUrl: './new-company-form.component.html',
  styleUrl: './new-company-form.component.css'
})
export class NewCompanyFormComponent {

  newCompany: Company = {
    name: ''
  }

  constructor(
    public dialogRef: DialogRef<Company>
  ) {
  }

  handleCreateCompany() {
    this.dialogRef.close(this.newCompany);
  }

  handleClose() {
    this.dialogRef.close();
  }

  protected readonly ButtonStyle = ButtonStyle;
}
