import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {InvoiceDto} from "../dto/invoice.dto";

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

private options = {
    headers: new HttpHeaders({'content-type':'application/json'}),
    withCredentials: false
  }

  constructor(private httpClient: HttpClient) {
  }

  public getInvoiceList(): Observable<Array<InvoiceDto>> {
    return this.httpClient.get<Array<InvoiceDto>>(`${environment.apiBasePath}/invoices`)
  }

  public download(date: string) {
    return this.httpClient.get(`${environment.apiBasePath}/download/${date}`, {responseType: 'blob'})
  }

  public saveInvoice(data: InvoiceDto): Observable<InvoiceDto> {
    return this.httpClient.post<InvoiceDto>(`${environment.apiBasePath}/invoices`, data, this.options)
  }
}
