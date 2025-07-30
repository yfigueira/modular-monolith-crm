export type LeadSummary = {
  id: string,
  firstName: string,
  lastName: string,
  email: string,
  phoneNumber?: string,
  subject: string,
  state: number,
  isActive: boolean
}
