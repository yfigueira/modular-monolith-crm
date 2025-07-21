import { Component } from '@angular/core';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {faRightFromBracket} from '@fortawesome/free-solid-svg-icons';
import {AuthenticationService} from '../../../services/authentication/authentication.service';

@Component({
  selector: 'app-header',
  imports: [FontAwesomeModule],
  templateUrl: './app-header.component.html',
  styleUrl: './app-header.component.css'
})
export class AppHeaderComponent {
  signOutIcon = faRightFromBracket;

  constructor(
    private authService: AuthenticationService
  ) {
  }

  async logout(): Promise<void> {
    await this.authService.logout();
  }

  loggedUser(): string {
    return `${this.authService.profile?.firstName} ${this.authService.profile?.lastName}`
  }
}
