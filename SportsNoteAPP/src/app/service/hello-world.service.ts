import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {Observable} from "rxjs";
import {HelloWorld} from "../interface/hello-world";

@Injectable({
  providedIn: 'root'
})
export class HelloWorldService {

  constructor(private http: HttpClient) { }

  getHelloWorld(): Observable<HelloWorld>{
    return this.http.get<HelloWorld>('http://localhost:8080/');
  }
}
