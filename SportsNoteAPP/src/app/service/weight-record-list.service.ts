import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {WeightRecordDTO} from "../interface/weight-record-d-t-o";

@Injectable({
  providedIn: 'root'
})
export class WeightRecordListService {

  constructor(private http: HttpClient) { }

  getWeightRecordList(): Observable<WeightRecordDTO[]>{
    return this.http.get<WeightRecordDTO[]>('http://localhost:8080/weight_record/list');
  }
}
