import {Component} from '@angular/core';
import {ContactService} from '../../services/contact/contact.service';
import {ContactSummary} from '../../models/contact/contact-summary';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import {faEllipsis, faEllipsisVertical, faPenToSquare} from '@fortawesome/free-solid-svg-icons';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-contacts',
  imports: [
    FaIconComponent,
    RouterLink
  ],
  templateUrl: './contacts.component.html',
  styleUrl: './contacts.component.css'
})
export class ContactsComponent {

  contacts: ContactSummary[];

  constructor(
    private service: ContactService
  ) {
    this.contacts = [];
    this.service.getAll().subscribe(data => this.contacts = data);
  }


  protected readonly faEllipsisVertical = faEllipsisVertical;
  protected readonly faEllipsis = faEllipsis;
  protected readonly faPenToSquare = faPenToSquare;
}
