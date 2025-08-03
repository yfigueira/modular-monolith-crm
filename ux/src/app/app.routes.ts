import { Routes } from '@angular/router';
import {DealsComponent} from './components/deals/deals.component';
import {LeadsComponent} from './components/leads/leads.component';
import {AccountsComponent} from './components/accounts/accounts.component';
import {ContactsComponent} from './components/contacts/contacts.component';
import {ContactComponent} from './components/contact/contact.component';
import {ActivityComponent} from './components/activity/activity.component';
import {LeadComponent} from './components/lead/lead.component';
import {DealComponent} from './components/deal/deal.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/deals',
    pathMatch: 'full'
  },
  {
    path: 'deals',
    component: DealsComponent
  },
  {
    path: 'deals/:id',
    component: DealComponent
  },
  {
    path: 'leads',
    component: LeadsComponent
  },
  {
    path: 'leads/:id',
    component: LeadComponent
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
