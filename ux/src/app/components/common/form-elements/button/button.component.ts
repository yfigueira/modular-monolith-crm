import {Component, input, output} from '@angular/core';
import {FaIconComponent, IconDefinition} from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'app-button',
  imports: [
    FaIconComponent
  ],
  templateUrl: './button.component.html',
  styleUrl: './button.component.css'
})
export class ButtonComponent {

  name = input.required<string>();
  style = input<ButtonStyle>(ButtonStyle.DEFAULT);
  text = input<string | undefined>();
  icon = input<IconDefinition | undefined>();

  clickEvent = output<void>();

  handleClick() {
    this.clickEvent.emit()
  }
}

export enum ButtonStyle {
  INFO = "app-info-button",
  SUCCESS = "app-success-button",
  WARNING = "app-warning-button",
  DANGER = "app-danger-button",
  DEFAULT = "app-default-button"
}
