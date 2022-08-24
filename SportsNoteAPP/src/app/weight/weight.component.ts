import { Component, OnInit } from '@angular/core';
import {WeightRecordDTO} from "../interface/weight-record-d-t-o";
import {WeightRecordListService} from "../service/weight-record-list.service";

@Component({
  selector: 'app-weight',
  templateUrl: './weight.component.html',
  styleUrls: ['./weight.component.scss']
})
export class WeightComponent implements OnInit {

  weightRecordList : WeightRecordDTO[] = [];
  averageWeightRecordList : WeightRecordDTO[] = [];
  isAverageWeightToggleSelected : boolean = false;

  constructor(private weightRecordListService : WeightRecordListService) { }

  ngOnInit(): void {
    this.weightRecordListService.getWeightRecordList()
      .subscribe(
        (data :WeightRecordDTO[]) => this.weightRecordList = data.reverse());

    this.weightRecordListService.getAverageWeightRecordList()
      .subscribe(
        (data :WeightRecordDTO[]) => this.averageWeightRecordList = data.reverse());
  }

  setToggleOnWeight(){
    this.isAverageWeightToggleSelected = false;
  }

  setToggleOnAverageWeight(){
    this.isAverageWeightToggleSelected = true;
  }

  //test
  addWeightRecord(){
    console.log('Data added')
    // this.weightRecordListService.addWeightRecord(new class implements WeightRecordDTO {
    //   date: Date = new Date(2022,8,7);
    //   weight: number = 80.5;
    // }).subscribe()
  }

  deleteWeightRecord(){
    console.log('Data Deleted')
    // this.weightRecordListService.deleteWeightRecord('2022-08-07').subscribe()
  }

}
