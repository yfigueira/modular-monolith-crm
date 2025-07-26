export type Contact = {
  id?: string,
  firstName: string,
  lastName: string,
  email: string,
  priority?: number,
  company?: string,
  jobTitle?: Object,
  phoneNumber?: string,
  privateEmail?: string,
  privatePhoneNumber?: string,
  activities?: Object[]
}
