import { Component } from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';
import {faAddressBook, faFileSignature, faHandshake, faSackDollar} from '@fortawesome/free-solid-svg-icons';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'app-navbar',
  imports: [
    RouterLink,
    FaIconComponent,
    RouterLinkActive
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  routes = [
    {label: "Deals", routerLink: "deals", icon: faHandshake},
    {label: "Leads", routerLink: "leads", icon: faSackDollar},
    {label: "Accounts", routerLink: "accounts", icon: faFileSignature},
    {label: "Contacts", routerLink: "contacts", icon: faAddressBook}
  ]
}
