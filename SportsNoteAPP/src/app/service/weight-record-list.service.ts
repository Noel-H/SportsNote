import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {WeightRecordDTO} from "../dto/weight-record-d-t-o";

@Injectable({
  providedIn: 'root'
})
export class WeightRecordListService {

  constructor(private http: HttpClient) { }

  getWeightRecordList(): Observable<WeightRecordDTO[]>{
    return this.http.get<WeightRecordDTO[]>('http://localhost:8080/weight_record/list');
  }

  addWeightRecord(weightRecordDTO : WeightRecordDTO): Observable<WeightRecordDTO>{
    return this.http.post<WeightRecordDTO>('http://localhost:8080/weight_record', weightRecordDTO);
  }

  updateWeightRecord(weightRecordDTO : WeightRecordDTO): Observable<WeightRecordDTO>{
    return this.http.put<WeightRecordDTO>('http://localhost:8080/weight_record', weightRecordDTO);
  }

  deleteWeightRecord(weightRecordDTO : WeightRecordDTO): Observable<WeightRecordDTO>{
    const httpOptions = {
      headers : new HttpHeaders({'Content-Type' : 'application/json'}),
      body : weightRecordDTO
    };
    return this.http.delete<WeightRecordDTO>('http://localhost:8080/weight_record', httpOptions);
  }

  getAverageWeightRecordList(): Observable<WeightRecordDTO[]>{
    return this.http.get<WeightRecordDTO[]>('http://localhost:8080/average_weight_record/list');
  }
}
