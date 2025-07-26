import {Component, input, output} from '@angular/core';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import {faChevronRight} from '@fortawesome/free-solid-svg-icons';
import {AccountSummary} from '../../../models/account/account-summary';

@Component({
  selector: 'app-account-list-item',
  imports: [
    FaIconComponent
  ],
  templateUrl: './account-list-item.component.html',
  styleUrl: './account-list-item.component.css'
})
export class AccountListItemComponent {

  protected readonly faChevronRight = faChevronRight;
  data = input<AccountSummary>({id: "", name: "", country: "", city: ""});
  clickEvent = output<string>();

  handleClick() {
    this.clickEvent.emit(this.data().id)
  }
}
