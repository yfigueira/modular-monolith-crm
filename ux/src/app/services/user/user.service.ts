import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../models/user/user';
import {API_URL} from '../../../constants';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly USERS_API = `${API_URL}/users`;

  constructor(
    private httpClient: HttpClient
  ) { }

  getAll(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.USERS_API}`)
  }
}
