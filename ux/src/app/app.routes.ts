import { Routes } from '@angular/router';
import {DealsComponent} from './components/deals/deals.component';
import {LeadsComponent} from './components/leads/leads.component';
import {AccountsComponent} from './components/accounts/accounts.component';
import {ContactsComponent} from './components/contacts/contacts.component';
import {ContactComponent} from './components/contact/contact.component';
import {ActivityComponent} from './components/activity/activity.component';

export const routes: Routes = [
  {
    path: 'deals',
    component: DealsComponent
  },
  {
    path: 'leads',
    component: LeadsComponent
  },
  {
    path: 'accounts',
    component: AccountsComponent
  },
  {
    path: 'contacts',
    component: ContactsComponent
  },
  {
    path: 'contacts/:id',
    component: ContactComponent
  },
  {
    path: 'activities/:id',
    component: ActivityComponent
  }
];
