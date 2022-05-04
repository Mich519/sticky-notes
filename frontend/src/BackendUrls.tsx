export const BACKEND_URL = process.env.REACT_APP_BACKEND_URL;
export const REGISTRATION_ENDPOINT = BACKEND_URL + '/api/v1/auth/signup';
export const LOGIN_ENDPOINT = BACKEND_URL + '/api/v1/auth/signin';
export const USERS_URL = BACKEND_URL + '/api/v1/users';
export const MY_NOTES_URL = BACKEND_URL + '/api/v1/users/me/notes';