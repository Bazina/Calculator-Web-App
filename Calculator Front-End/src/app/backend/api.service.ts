import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from 'src/environments/environment';
import {Result} from "./Result";

const httpOptions = {
  headers: new HttpHeaders({
    'content-type': 'application/json',
  })
};

@Injectable()
export class ApiService {
  constructor(private http: HttpClient) {
  }

  post(Mapping: String, body: Object = {}): Observable<Result> {
    return this.http.post<Result>(`${environment.api_url}${Mapping}`, JSON.stringify(body), httpOptions);
  }

  get(Mapping: String, getValue: string): Observable<Result> {
    return this.http.get<Result>(`${environment.api_url}${Mapping}/${getValue}`, httpOptions);
  }
}
