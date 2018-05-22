import {Injectable} from '@angular/core';
import {HttpHeaders, HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

import {Pet} from '../pet';
import { AppConfigService } from '../app.config.service';

@Injectable()
export class PetSearchService {

  private baseUrl = AppConfigService.settings.apiUrl + '/api/pet';

  constructor(private http: HttpClient) {
  }

  search(term: string): Observable<Pet[]> {
    return this.http
      .get(`${this.baseUrl}/?name=${term}`,{
        headers: new HttpHeaders({'Content-Type': 'application/json'})
        .set('Accept', 'application/json')
        .set('Authorization', `Bearer ${localStorage.getItem('access_token')}`)
        
      })
      .map(response => response as Pet[]);
  }
}
