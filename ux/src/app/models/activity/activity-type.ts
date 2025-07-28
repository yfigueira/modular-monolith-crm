import {faAt, faCheck, faMugHot, faPhone} from '@fortawesome/free-solid-svg-icons';

export const ActivityType = {
  PHONE_CALL: {code: 1, label: 'Phone call', icon: faPhone},
  EMAIL: {code: 2, label: 'Email', icon: faAt},
  MEETING: {code: 3, label: 'Meeting', icon: faMugHot},
  TODO: {code: 4, label: 'Todo', icon: faCheck}
}
