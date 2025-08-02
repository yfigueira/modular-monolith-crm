import { Component } from '@angular/core';
import {ButtonComponent, ButtonStyle} from "../../common/form-elements/button/button.component";
import {InputComponent} from "../../common/form-elements/input/input.component";
import {JobTitle} from '../../../models/lead/jobtitle/job-title';
import {DialogRef} from '@angular/cdk/dialog';

@Component({
  selector: 'app-new-job-title-form',
    imports: [
        ButtonComponent,
        InputComponent
    ],
  templateUrl: './new-job-title-form.component.html',
  styleUrl: './new-job-title-form.component.css'
})
export class NewJobTitleFormComponent {

  newJobTitle: JobTitle = {
    name: ''
  }

  constructor(
    public dialogRef: DialogRef<JobTitle>
  ) {
  }

  handleCreateJobTitle() {
    this.dialogRef.close(this.newJobTitle);
  }

  handleClose() {
    this.dialogRef.close();
  }

    protected readonly ButtonStyle = ButtonStyle;
}
