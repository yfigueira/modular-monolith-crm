import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';
import {UserProfile} from '../../models/authentication/user-profile';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private _keycloak: Keycloak | undefined
  private _profile: UserProfile | undefined

  constructor() { }

  get keycloak(): Keycloak {
    if (!this._keycloak) {
      this._keycloak = new Keycloak({
        url: "http://localhost:9000",
        realm: "crm-realm",
        clientId: "crm-web-client"
      });
    }
    return this._keycloak;
  }

  get profile(): UserProfile| undefined {
    return this._profile
  }

  async init() {
    const authenticated = await this.keycloak.init({
      onLoad: "login-required"
    });

    if (authenticated) {
      this._profile = (await this.keycloak.loadUserProfile()) as UserProfile;
      // @ts-ignore
      this._profile.id = this.keycloak?.tokenParsed.sub;
      this._profile.token = this.profile?.token
    }
  }

  login() {
    return this.keycloak.login();
  }

  logout() {
    return this.keycloak.logout();
  }
}
