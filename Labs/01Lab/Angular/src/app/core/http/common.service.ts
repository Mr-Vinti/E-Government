import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PlatformLocation } from '@angular/common';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { StringResponse } from '../../shared/models/string-response';
import { Record } from '../../shared/models/record.model';

@Injectable({
  providedIn: 'root',
})
export class CommonService {
  constructor(private http: HttpClient, private pl: PlatformLocation) {}

  addRecord(record: Record): Observable<StringResponse> {
    const url = environment.apiResourceUri + '/records';

    return this.http.post<StringResponse>(url, record);
  }

  getRecords(): Observable<StringResponse> {
    const url = environment.apiResourceUri + '/records';

    return this.http.get<StringResponse>(url);
  }
}
