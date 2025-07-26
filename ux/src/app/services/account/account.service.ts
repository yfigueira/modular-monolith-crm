import { Injectable } from '@angular/core';
import {AccountSummary} from '../../models/account/account-summary';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Account} from '../../models/account/account';
import {Contact} from '../../models/contact/contact';
import {ContactSummary} from '../../models/contact/contact-summary';
import {API_URL} from '../../../constants';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private readonly ACCOUNTS_API = `${API_URL}/accounts`;

  constructor(
    private httpClient: HttpClient
  ) { }

  getAll(): Observable<AccountSummary[]> {
    return this.httpClient.get<AccountSummary[]>(`${this.ACCOUNTS_API}`);
  }

  get(id: string): Observable<Account> {
    return this.httpClient.get<Account>(`${this.ACCOUNTS_API}/${id}`);
  }

  create(account: Account): Observable<Account> {
    return this.httpClient.post<Account>(`${this.ACCOUNTS_API}`, account);
  }

  update(account: Account): Observable<Account> {
    return this.httpClient.put<Account>(`${this.ACCOUNTS_API}/${account.id}`, account);
  }

  delete(id: string): Observable<void> {
    return this.httpClient.delete<void>(`${this.ACCOUNTS_API}/${id}`);
  }

  addContact(id: string, contact: Contact): Observable<ContactSummary> {
    return this.httpClient.post<ContactSummary>(`${this.ACCOUNTS_API}/${id}/contacts`, contact);
  }
}
