import {Injectable} from '@angular/core';
import {HttpHeaders, HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
//https://stackoverflow.com/questions/49475002/use-server-environment-variable-on-image
@Injectable()
export class AppConfigService{

    constructor(private http: HttpClient) {
         var obj;
         this.getJSON().subscribe(data => obj=data, error => console.log(error));
    }

    public getJSON(): Observable<any> {
         return this.http.get("/assets/config.json")
                         .map((res:any) => res.json())
                         .catch((error:any) => {
                           console.log('error loading file happend'+error);
                           return error;
                         });

     }
}
