import {Injectable} from '@angular/core';
import { Http, Response } from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {IAppConfig} from './app.config.model'

//import { environment } from '../environments/environment';
//https://stackoverflow.com/questions/49475002/use-server-environment-variable-on-image
//https://blogs.msdn.microsoft.com/premier_developer/2018/03/01/angular-how-to-editable-config-files/

@Injectable()
export class AppConfigService{

  static settings: IAppConfig;

  constructor(private http: Http) {}

  load() {
        console.log('>>>>>>>>>>>>> loading started<<<<<<<<<<<<');
        //const jsonFile = `assets/config/config.${environment.name}.json`;
        const jsonFile = '../assets/config.json';
        return new Promise<void>((resolve, reject) => {
          this.http.get(jsonFile).toPromise().then((response : Response) => {
            AppConfigService.settings = <IAppConfig>response.json();
             resolve();
          }).catch((response: any) => {
             reject(`Could not load file '${jsonFile}': ${JSON.stringify(response)}`);
          });
      });
    } 
}
