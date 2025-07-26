import {Component, inject, OnInit} from '@angular/core';
import {AccountListItemComponent} from './account-list-item/account-list-item.component';
import {AccountSummary} from '../../models/account/account-summary';
import {AccountService} from '../../services/account/account.service';
import {Account} from '../../models/account/account';
import {switchMap} from 'rxjs';
import {InputComponent} from '../common/form-elements/input/input.component';
import {FaIconComponent} from '@fortawesome/angular-fontawesome';
import {
  faEllipsis,
  faEllipsisVertical,
  faFloppyDisk,
  faPenToSquare, faPlus,
  faTrashCan,
  faUserPlus
} from '@fortawesome/free-solid-svg-icons';
import {ButtonComponent, ButtonStyle} from '../common/form-elements/button/button.component';
import {Dialog} from '@angular/cdk/dialog';
import {NewAccountFormComponent} from './new-account-form/new-account-form.component';
import {NewAccountContactFormComponent} from './new-account-contact-form/new-account-contact-form.component';
import {Contact} from '../../models/contact/contact';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-accounts',
  imports: [
    AccountListItemComponent,
    InputComponent,
    FaIconComponent,
    ButtonComponent,
    RouterLink
  ],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css'
})
export class AccountsComponent implements OnInit {

  private dialog = inject(Dialog);
  private router = inject(Router);

  accounts: AccountSummary[] = [];
  activeAccount: Account = {name: "", country: "", city: "", tin: ""};

  constructor(
    private service: AccountService
  ) {
  }

  ngOnInit(): void {
    this.service
      .getAll()
      .pipe(
        switchMap(accounts => {
          this.accounts = accounts;
          return this.service.get(accounts[0].id);
        })
      )
      .subscribe(data => this.activeAccount = data);
  }

  selectAccount(id: string): void {
    this.service
      .get(id)
      .subscribe(data => this.activeAccount = data);
  }

  createAccount(account: Account): void {
    this.service
      .create(account)
      .subscribe(data => this.accounts.push(data as AccountSummary));
  }

  updateAccount(): void {
    this.service
      .update(this.activeAccount)
      .subscribe(data => this.activeAccount = data);
  }

  deleteAccount(): void {
    this.service
      .delete(this.activeAccount.id!)
      .subscribe(() => this.router.navigate(['/accounts']))
  }

  addContact(contact: Contact): void {
    this.service
      .addContact(this.activeAccount.id!, contact)
      .subscribe(data => this.activeAccount.contacts?.push(data));
  }

  openNewAccountModal(): void {
    const dialogRef = this.dialog.open(
      NewAccountFormComponent,
      { disableClose: true }
    );
    dialogRef.closed.subscribe(result => {
      if (result) {
        this.createAccount(result as Account)
      }
    })
  }

  openNewContactModal(): void {
    const dialogRef = this.dialog.open(
      NewAccountContactFormComponent,
      { disableClose: true }
    );
    dialogRef.closed.subscribe(result => {
      if (result) {
        this.addContact(result as Contact);
      }
    })
  }

  protected readonly ButtonStyle = ButtonStyle;
  protected readonly AccountListItemComponent = AccountListItemComponent;
  protected readonly faEllipsisVertical = faEllipsisVertical;
  protected readonly faPenToSquare = faPenToSquare;
  protected readonly faEllipsis = faEllipsis;
  protected readonly faFloppyDisk = faFloppyDisk;
  protected readonly faTrashCan = faTrashCan;
  protected readonly faUserPlus = faUserPlus;
  protected readonly faPlus = faPlus;
}
