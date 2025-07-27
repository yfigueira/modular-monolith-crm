import {Component, input, model} from '@angular/core';

@Component({
  selector: 'app-datetime',
  imports: [],
  templateUrl: './datetime.component.html',
  styleUrl: './datetime.component.css'
})
export class DatetimeComponent {

  id = input.required<string>();
  label = input<string>('');

  value = model<string | undefined>();

  selectDatetime(selected: string) {
    this.value.set(selected)
  }
}
