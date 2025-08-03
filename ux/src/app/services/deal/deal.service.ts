import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../../constants';
import {Observable} from 'rxjs';
import {DealSummary} from '../../models/deal/deal-summary';
import {Deal} from '../../models/deal/deal';

@Injectable({
  providedIn: 'root'
})
export class DealService {

  private readonly DEALS_API = `${API_URL}/deals`;

  constructor(
    private httpClient: HttpClient
  ) { }

  getAll(): Observable<DealSummary[]> {
    return this.httpClient.get<DealSummary[]>(`${this.DEALS_API}`);
  }

  get(id: string): Observable<Deal> {
    return this.httpClient.get<Deal>(`${this.DEALS_API}/${id}`);
  }

  create(deal: Deal): Observable<Deal> {
    return this.httpClient.post<Deal>(`${this.DEALS_API}`, deal);
  }

  update(deal: Deal): Observable<Deal> {
    return this.httpClient.put<Deal>(`${this.DEALS_API}/${deal.id}`, deal);
  }

  delete(id: string): Observable<void> {
    return this.httpClient.delete<void>(`${this.DEALS_API}/${id}`);
  }

  changeStage(dealId: string, stageCode: number): Observable<void> {
    return this.httpClient.post<void>(`${this.DEALS_API}/${dealId}/stage/${stageCode}`, null)
  }
}
