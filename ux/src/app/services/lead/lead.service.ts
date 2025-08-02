import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../../constants';
import {Observable} from 'rxjs';
import {LeadSummary} from '../../models/lead/lead-summary';
import {Lead} from '../../models/lead/lead';

@Injectable({
  providedIn: 'root'
})
export class LeadService {

  private readonly LEADS_API = `${API_URL}/leads`;

  constructor(
    private httpClient: HttpClient
  ) { }

  getAll(): Observable<LeadSummary[]> {
    return this.httpClient.get<LeadSummary[]>(`${this.LEADS_API}`);
  }

  get(id: string): Observable<Lead> {
    return this.httpClient.get<Lead>(`${this.LEADS_API}/${id}`);
  }

  create(lead: Lead): Observable<Lead> {
    return this.httpClient.post<Lead>(`${this.LEADS_API}`, lead);
  }

  update(lead: Lead): Observable<Lead> {
    return this.httpClient.put<Lead>(`${this.LEADS_API}/${lead.id}`, lead);
  }

  delete(id: string): Observable<void> {
    return this.httpClient.delete<void>(`${this.LEADS_API}/${id}`);
  }

  changeState(leadId: string, stateCode: number): Observable<void> {
    return this.httpClient.post<void>(`${this.LEADS_API}/${leadId}/state/${stateCode}`, null);
  }
}
