import {Component, input, model} from '@angular/core';

@Component({
  selector: 'app-input',
  imports: [],
  templateUrl: './input.component.html',
  styleUrl: './input.component.css'
})
export class InputComponent {

  id = input.required<string>();
  label = input<string>('');
  required = input<boolean>(false);
  placeholder = input<string>('');

  value = model<string | undefined>('');

  updateValue(currentValue: string) {
    this.value.set(currentValue)
  }

  protected readonly HTMLInputElement = HTMLInputElement;
}
