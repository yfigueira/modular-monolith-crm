import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../../../constants';
import {Observable} from 'rxjs';
import {JobTitle} from '../../../models/lead/jobtitle/job-title';

@Injectable({
  providedIn: 'root'
})
export class LeadJobTitleService {

  private readonly JOB_TITLES_API = `${API_URL}/leads/job-titles`;

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
