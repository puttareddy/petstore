import { config } from '../../../config';

interface AuthConfig {
  clientID: string;
  domain: string;
  callbackURL: string;
}

export const AUTH_CONFIG: AuthConfig = {
  clientID: config.clientID,
  domain: config.domain,
  callbackURL: `${config.appUrl}/callback`
};