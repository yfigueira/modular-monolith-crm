import {ContactSummary} from '../contact/contact-summary';

export type Account = {
  id?: string,
  name: string,
  country: string,
  city: string,
  street?: string,
  streetNumber?: string,
  zipCode?: string,
  tin: string,
  webUrl?: string,
  contacts?: ContactSummary[]
}
