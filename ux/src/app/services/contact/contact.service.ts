import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Contact} from '../../models/contact/contact';
import {API_URL} from '../../../constants';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private readonly CONTACTS_API = `${API_URL}/accounts/contacts`;

  constructor(
    private httpClient: HttpClient
  ) { }

  get(id: string): Observable<Contact> {
    return this.httpClient.get<Contact>(`${this.CONTACTS_API}/${id}`);
  }

  update(contact: Contact): Observable<Contact> {
    return this.httpClient.put<Contact>(`${this.CONTACTS_API}/${contact.id}`, contact);
  }

  delete(id: string): Observable<void> {
    return this.httpClient.delete<void>(`${this.CONTACTS_API}/${id}`);
  }
}
