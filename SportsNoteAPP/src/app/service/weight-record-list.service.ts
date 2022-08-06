import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, of, tap} from "rxjs";
import {WeightRecordDTO} from "../interface/weight-record-d-t-o";

@Injectable({
  providedIn: 'root'
})
export class WeightRecordListService {

  constructor(private http: HttpClient) { }

  getWeightRecordList(): Observable<WeightRecordDTO[]>{
    return this.http.get<WeightRecordDTO[]>('http://localhost:8080/weight_record/list').pipe(
      tap((weightRecordList) => console.table(weightRecordList)),
      catchError((error) => {
        console.log(error);
        return of([])
      })
    );
  }

  addWeightRecord(weightRecordDTO : WeightRecordDTO): Observable<WeightRecordDTO|undefined>{
    const httpOptions = {
      headers : new HttpHeaders({'Content-Type' : 'application/json'})
    };
    return this.http.post<WeightRecordDTO>('http://localhost:8080/weight_record', weightRecordDTO, httpOptions).pipe(
      tap((response) => console.table(response)),
      catchError((error) => {
        console.log(error);
        return of(undefined)
      })
    );
  }

  updateWeightRecord(weightRecordDTO : WeightRecordDTO): Observable<WeightRecordDTO|undefined>{
    const httpOptions = {
      headers : new HttpHeaders({'Content-Type' : 'application/json'})
    };
    return this.http.put<WeightRecordDTO>('http://localhost:8080/weight_record', weightRecordDTO, httpOptions).pipe(
      tap((response) => console.table(response)),
      catchError((error) => {
        console.log(error);
        return of(undefined)
      })
    );
  }

  getAverageWeightRecordList(): Observable<WeightRecordDTO[]>{
    return this.http.get<WeightRecordDTO[]>('http://localhost:8080/average_weight_record/list').pipe(
      tap((averageWeightRecordList) => console.table(averageWeightRecordList)),
      catchError((error) => {
        console.log(error);
        return of([])
      })
    );
  }
}
