import {ActivitySummary} from '../activity/activity-summary';

export type Contact = {
  id?: string,
  firstName: string,
  lastName: string,
  email: string,
  priority?: number,
  company?: string,
  jobTitle?: string,
  phoneNumber?: string,
  privateEmail?: string,
  privatePhoneNumber?: string,
  activities?: ActivitySummary[]
}
