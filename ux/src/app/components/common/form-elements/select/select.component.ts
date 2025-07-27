import {Component, input, model} from '@angular/core';

@Component({
  selector: 'app-select',
  imports: [],
  templateUrl: './select.component.html',
  styleUrl: './select.component.css'
})
export class SelectComponent {

  id = input.required<string>();
  label = input<string>('');
  options = input<{id: string | number, label: string}[]>([]);

  value = model<string | number | undefined>('');

  selectOption(selected: string) {
    this.value.set(selected);
  }

  protected readonly getSelection = getSelection;
}
