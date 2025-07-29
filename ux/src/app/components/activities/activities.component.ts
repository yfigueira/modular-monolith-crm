import {Component, inject, input} from '@angular/core';
import {ButtonComponent, ButtonStyle} from "../common/form-elements/button/button.component";
import {faPlus} from '@fortawesome/free-solid-svg-icons';
import {ActivitySummary} from '../../models/activity/activity-summary';
import {Dialog} from '@angular/cdk/dialog';
import {NewActivityFormComponent} from './new-activity-form/new-activity-form.component';
import {Activity} from '../../models/activity/activity';
import {ActivityService} from '../../services/activity/activity.service';
import {EntityType} from '../../models/activity/entity-type';
import {ActivityType} from '../../models/activity/activity-type';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import {DatePipe} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-activities',
  imports: [
    ButtonComponent,
    FaIconComponent,
    DatePipe,
    RouterLink
  ],
  templateUrl: './activities.component.html',
  styleUrl: './activities.component.css'
})
export class ActivitiesComponent {

  private dialog = inject(Dialog);

  activities = input<ActivitySummary[]>();
  parent = input<string>();

  constructor(
    private service: ActivityService,
  ) {
  }

  createActivity(activity: Activity) {
    activity.entity = this.parent();
    activity.entityType = EntityType.CONTACT.code;

    this.service.create(activity)
      .subscribe(data => this.activities()?.push(data as ActivitySummary))
  }

  openNewActivityModal(): void {
    const dialogRef = this.dialog.open(
      NewActivityFormComponent,
      { disableClose: true }
    );
    dialogRef.closed.subscribe(result => {
      if (result) {
        this.createActivity(result as Activity)
      }
    });
  }

  getActivityType(code: number | undefined) {
    return Object
      .values(ActivityType)
      .find(type => type.code === code);
  }

  protected readonly ButtonStyle = ButtonStyle;
  protected readonly Object = Object;
  protected readonly faPlus = faPlus;
}
