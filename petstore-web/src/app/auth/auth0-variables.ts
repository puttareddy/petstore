interface AuthConfig {
  clientID: string;
  domain: string;
  callbackURL: string;
}

let clientID = process.env.CLIENT_ID || 'asDe6Ro5hBb2zZxB83AKR2K16ooLUXLC';
let domain = process.env.DOMAIN || 'puttareddy.auth0.com';
let appUrl = process.env.APP_URL || 'http://localhost:4200';

export const AUTH_CONFIG: AuthConfig = {
  clientID: clientID,
  domain: domain,
  callbackURL: `${appUrl}/callback`
};