export type Activity = {
  id?: string,
  subject: string,
  description?: string,
  scheduledAt?: string,
  completedAt?: string,
  type: number,
  status?: number,
  entity?: string,
  entityType?: number,
  owner: string
}
