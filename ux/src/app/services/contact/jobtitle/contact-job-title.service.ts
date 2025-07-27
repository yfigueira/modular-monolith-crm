import { Injectable } from '@angular/core';
import {API_URL} from '../../../../constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JobTitle} from '../../../models/contact/jobtitle/job-title';

@Injectable({
  providedIn: 'root'
})
export class ContactJobTitleService {

  private readonly JOB_TITLES_API = `${API_URL}/accounts/job-titles`;

  constructor(
    private httpClient: HttpClient
  ) { }

  getAll(): Observable<JobTitle[]> {
    return this.httpClient.get<JobTitle[]>(`${this.JOB_TITLES_API}`);
  }

  create(jobTitle: JobTitle): Observable<JobTitle> {
    return this.httpClient.post<JobTitle>(`${this.JOB_TITLES_API}`, jobTitle);
  }
}
