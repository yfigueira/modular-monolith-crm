import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../../../constants';
import {Observable} from 'rxjs';
import {Company} from '../../../models/lead/company/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private readonly COMPANIES_API = `${API_URL}/leads/companies`;

  constructor(
    private httpClient: HttpClient
  ) { }

  getAll(): Observable<Company[]> {
    return this.httpClient.get<Company[]>(`${this.COMPANIES_API}`)
  }

  create(company: Company): Observable<Company> {
    return this.httpClient.post<Company>(`${this.COMPANIES_API}`, company);
  }
}
