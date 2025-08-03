import {Component, inject, OnInit} from '@angular/core';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {faAngleLeft} from '@fortawesome/free-solid-svg-icons';
import {Location} from '@angular/common';
import {ActivatedRoute} from '@angular/router';
import {DealService} from '../../services/deal/deal.service';
import {Deal} from '../../models/deal/deal';
import {ActivitySummary} from '../../models/activity/activity-summary';

@Component({
  selector: 'app-deal',
    imports: [
        FaIconComponent
    ],
  templateUrl: './deal.component.html',
  styleUrl: './deal.component.css'
})
export class DealComponent implements OnInit{

  private route = inject(ActivatedRoute);

  deal: Deal = {
    id: '',
    title: '',
    stage: 0,
    expectedRevenue: '',
    expectedCloseDate: '',
    contact: '',
    owner: '',
    activities: []
  }

  activeId: string | null;

  constructor(
    private service: DealService,
    private location: Location,
  ) {
    this.activeId = this.route.snapshot.paramMap.get('id');
  }

  ngOnInit(): void {
    if (this.activeId) {
      this.service.get(this.activeId).subscribe(data => this.deal = data)
    }
  }

  goBack(): void {
    this.location.back();
  }

  protected readonly faAngleLeft = faAngleLeft;
}
