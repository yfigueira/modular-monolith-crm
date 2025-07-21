import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {AppHeaderComponent} from './components/common/app-header/app-header.component';
import "@fontsource/jetbrains-mono";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, AppHeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ux';
}
