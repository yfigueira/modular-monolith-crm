import {Component, inject} from '@angular/core';
import {ButtonComponent, ButtonStyle} from "../common/form-elements/button/button.component";
import {faBars, faUserPlus} from '@fortawesome/free-solid-svg-icons';
import {
  CdkDrag,
  CdkDragDrop,
  CdkDragHandle,
  CdkDropList,
  CdkDropListGroup,
  moveItemInArray, transferArrayItem
} from '@angular/cdk/drag-drop';
import {LeadState} from '../../models/lead/lead-state';
import {LeadSummary} from '../../models/lead/lead-summary';
import {LeadService} from '../../services/lead/lead.service';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import {Dialog} from '@angular/cdk/dialog';
import {NewLeadFormComponent} from './new-lead-form/new-lead-form.component';
import {Lead} from '../../models/lead/lead';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-leads',
  imports: [
    ButtonComponent,
    CdkDropListGroup,
    CdkDropList,
    CdkDrag,
    FaIconComponent,
    CdkDragHandle,
    RouterLink
  ],
  templateUrl: './leads.component.html',
  styleUrl: './leads.component.css'
})
export class LeadsComponent {

  private dialog = inject(Dialog);

  dropListData: {[stateCode: number]: DropListProperties} = {
    [LeadState.NEW.code]: {state: LeadState.NEW, leads: []},
    [LeadState.CONTACT_ATTEMPT.code]: {state: LeadState.CONTACT_ATTEMPT, leads: []},
    [LeadState.CONTACTED.code]: {state: LeadState.CONTACTED, leads: []},
    [LeadState.DISQUALIFIED.code]: {state: LeadState.DISQUALIFIED, leads: []},
    [LeadState.QUALIFIED.code]: {state: LeadState.QUALIFIED, leads: []}
  }

  constructor(
    private service: LeadService
  ) {
    this.service
      .getAll()
      .subscribe(data => {
        data.map(l => {
          const stateLeads = this.dropListData[l.state].leads;
          stateLeads?.push(l);
        })
      })
  }

  changeLeadState(leadId: string, state: number): void {
    this.service.changeState(leadId, state).subscribe();
  }

  drop(event: CdkDragDrop<DropListProperties>): void {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data.leads, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data.leads,
        event.container.data.leads,
        event.previousIndex,
        event.currentIndex
      );
      const leadId = (event.item.data as LeadSummary).id;
      const stateCode = event.container.data.state.code;
      this.changeLeadState(leadId, stateCode);
    }
  }

  openNewLeadModal(): void {
    const dialogRef = this.dialog.open(
      NewLeadFormComponent,
      { disableClose: true }
    );
    dialogRef.closed.subscribe(result => {
      if (result) {
        this.createLead(result as Lead);
      }
    })
  }

  getLeadStates() {
    return Object.values(LeadState);
  }

  private createLead(lead: Lead): void {
    this.service.create(lead).subscribe(data =>
      this.dropListData[lead.state].leads.push(lead as LeadSummary))
  }

  protected readonly ButtonStyle = ButtonStyle;
  protected readonly faUserPlus = faUserPlus;
  protected readonly faBars = faBars;
  protected readonly String = String;
}

interface DropListProperties {
  state: {code: number, label: string, colorHex: string};
  leads: LeadSummary[]
}
