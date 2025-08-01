import {ActivitySummary} from '../activity/activity-summary';

export type Lead = {
  id?: string,
  firstName: string,
  lastName: string,
  email: string,
  phoneNumber?: string,
  subject?: string,
  city?: string,
  state: number,
  isActive?: boolean,
  owner: string,
  jobTitle?: string,
  company?: string,
  activities?: ActivitySummary[]
}
