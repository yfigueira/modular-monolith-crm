import {Component, inject} from '@angular/core';
import {DealSummary} from '../../models/deal/deal-summary';
import {DealStage} from '../../models/deal/deal-stage';
import {DealService} from '../../services/deal/deal.service';
import {ButtonComponent, ButtonStyle} from '../common/form-elements/button/button.component';
import {faBars, faHandshake} from '@fortawesome/free-solid-svg-icons';
import {
  CdkDrag,
  CdkDragDrop,
  CdkDragHandle,
  CdkDropList,
  CdkDropListGroup,
  moveItemInArray, transferArrayItem
} from '@angular/cdk/drag-drop';
import {Dialog} from '@angular/cdk/dialog';
import {NewDealFormComponent} from './new-deal-form/new-deal-form.component';
import {Deal} from '../../models/deal/deal';
import {RouterLink} from '@angular/router';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import {LeadSummary} from '../../models/lead/lead-summary';

@Component({
  selector: 'app-deals',
  imports: [
    ButtonComponent,
    CdkDropListGroup,
    CdkDropList,
    RouterLink,
    CdkDrag,
    CdkDragHandle,
    FaIconComponent
  ],
  templateUrl: './deals.component.html',
  styleUrl: './deals.component.css'
})
export class DealsComponent {

  private dialog = inject(Dialog);

  kanbanData: {[stageCode: number]: KanbanColumnProperties} = {
    [DealStage.NEW.code]: {stage: DealStage.NEW, deals: []},
    [DealStage.DISCOVERY.code]: {stage: DealStage.DISCOVERY, deals: []},
    [DealStage.PROPOSAL.code]: {stage: DealStage.PROPOSAL, deals: []},
    [DealStage.NEGOTIATION.code]: {stage: DealStage.NEGOTIATION, deals: []},
    [DealStage.CLOSED_LOST.code]: {stage: DealStage.CLOSED_LOST, deals: []},
    [DealStage.CLOSED_WON.code]: {stage: DealStage.CLOSED_WON, deals: []}
  }

  constructor(
    private service: DealService
  ) {
    this.service
      .getAll()
      .subscribe(data => {
        data.map(d => {
          const stageDeals = this.kanbanData[d.stage].deals;
          stageDeals?.push(d)
        })
      })
  }

  drop(event: CdkDragDrop<KanbanColumnProperties>): void {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data.deals, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data.deals,
        event.container.data.deals,
        event.previousIndex,
        event.currentIndex
      );
      const dealId = (event.item.data as DealSummary).id;
      const stageCode = event.container.data.stage.code;
      this.changeDealStage(dealId, stageCode);
    }
  }

  openNewDealModal(): void {
    const dialogRef = this.dialog.open(
      NewDealFormComponent,
      { disableClose: true }
    );
    dialogRef.closed.subscribe(result => {
      if (result) {
        this.createDeal(result as Deal);
      }
    })
  }

  getDealStages() {
    return Object.values(DealStage);
  }

  private createDeal(deal: Deal): void {
    this.service.create(deal).subscribe(data =>
      this.kanbanData[data.stage!].deals.push(data as DealSummary));
  }

  private changeDealStage(dealId: string, stageCode: number): void {
    this.service.changeStage(dealId, stageCode).subscribe();
  }

  protected readonly ButtonStyle = ButtonStyle;
  protected readonly String = String;
  protected readonly faHandshake = faHandshake;
  protected readonly faBars = faBars;
}

interface KanbanColumnProperties {
  stage: {code: number, label: string, colorHex: string};
  deals: DealSummary[];
}
