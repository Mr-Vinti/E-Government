import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PlatformLocation } from '@angular/common';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { StringResponse } from '../../shared/models/string-response';
import { Record } from '../../shared/models/record.model';
import { Paap } from '../../shared/models/paap.model';
import { PaapDetail } from '../../shared/models/paap-detail.model';

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

  getDropdown(): Observable<any> {
    const url =
      'http://e-licitatie.ro/api-pub/ComboPub/searchContractingAuthorities?filter=&pageIndex=0&pageSize=20&parentId=';

    return this.http.get<any>(url);
  }

  getPaaps(texts: string[]): Observable<Paap[]> {
    const url = 'http://localhost:8080/egov/api/paap';

    return this.http.post<any>(url, texts);
  }

  getPaapDetails(paapId: number): Observable<PaapDetail[]> {
    const url = 'http://localhost:8080/egov/api/paap';

    let params: HttpParams = new HttpParams();
    params = params.append('paapId', paapId.toString());

    return this.http.get<any>(url, { params });
  }
}
