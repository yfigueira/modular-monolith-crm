import {ActivitySummary} from '../activity/activity-summary';

export type Deal = {
  id?: string,
  title: string,
  stage?: number,
  expectedRevenue?: string,
  expectedCloseDate?: string,
  contact?: string,
  owner: string,
  activities: ActivitySummary[]
}
