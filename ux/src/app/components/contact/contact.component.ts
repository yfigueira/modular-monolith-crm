import {Component, inject, OnInit} from '@angular/core';
import {ContactService} from '../../services/contact/contact.service';
import {Contact} from '../../models/contact/contact';
import {ActivatedRoute} from '@angular/router';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import {faAngleLeft} from '@fortawesome/free-solid-svg-icons';
import {Location} from '@angular/common';

@Component({
  selector: 'app-contact',
  imports: [
    FaIconComponent
  ],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent implements OnInit{

  private route = inject(ActivatedRoute);
  readonly activeId: string | null;

  contact: Contact = {
    firstName: '',
    lastName: '',
    email: ''
  }

  constructor(
    private service: ContactService,
    private location: Location
  ) {
    this.activeId = this.route.snapshot.paramMap.get('id');
  }

  ngOnInit(): void {
    if (this.activeId) {
      this.service.get(this.activeId)
        .subscribe(data => this.contact = data)
    }
  }

  goBack(): void {
    this.location.back();
  }

  protected readonly faAngleLeft = faAngleLeft;
}
