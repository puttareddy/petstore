import { AppConfigService } from '../app.config.service';

interface AuthConfig {
  clientID: string;
  domain: string;
  callbackURL: string;
}

export const AUTH_CONFIG: AuthConfig = {
  clientID: AppConfigService.settings.clientID,
  domain: AppConfigService.settings.domain,
  callbackURL: `${AppConfigService.settings.appUrl}/callback`
};