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

  create(lead: Lead): Observable<Lead> {
    return this.httpClient.post<Lead>(`${this.LEADS_API}`, lead);
  }

  changeState(leadId: string, stateCode: number): Observable<void> {
    return this.httpClient.post<void>(`${this.LEADS_API}/${leadId}/state/${stateCode}`, null);
  }
}
