import { Injectable } from '@angular/core';
import {API_URL} from '../../../constants';
import {Activity} from '../../models/activity/activity';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  private readonly ACTIVITIES_API = `${API_URL}/activities`;

  constructor(
    private httpClient: HttpClient
  ) { }

  create(activity: Activity): Observable<Activity> {
    return this.httpClient.post<Activity>(`${this.ACTIVITIES_API}`, activity);
  }

  get(id: string): Observable<Activity> {
    return this.httpClient.get<Activity>(`${this.ACTIVITIES_API}/${id}`);
  }

  update(activity: Activity): Observable<Activity> {
    return this.httpClient.put<Activity>(`${this.ACTIVITIES_API}/${activity.id}`, activity);
  }

  delete(id: string): Observable<void> {
    return this.httpClient.delete<void>(`${this.ACTIVITIES_API}/${id}`);
  }
}
