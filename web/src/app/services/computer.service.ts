import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {ComputerDto} from "../dto/computer.dto";

@Injectable({
  providedIn: 'root'
})
export class ComputerService {

private options = {
    headers: new HttpHeaders({'content-type':'application/json'}),
    withCredentials: false
  }

  constructor(private httpClient: HttpClient) {
  }

  public getComputerList(): Observable<Array<ComputerDto>> {
    return this.httpClient.get<Array<ComputerDto>>(`${environment.apiBasePath}/computers`)
  }

  public getComputer(date: string): Observable<Array<ComputerDto>> {
    return this.httpClient.get<Array<ComputerDto>>(`${environment.apiBasePath}/computers/${date}`)
  }

}
