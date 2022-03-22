import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {Observable} from "rxjs";
import {HelloWorldDTO} from "../interface/hello-world-d-t-o";

@Injectable({
  providedIn: 'root'
})
export class HelloWorldService {

  constructor(private http: HttpClient) { }

  getHelloWorld(): Observable<HelloWorldDTO>{
    return this.http.get<HelloWorldDTO>('http://localhost:8080/');
  }
}
