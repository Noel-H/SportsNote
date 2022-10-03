import { Component, OnInit } from '@angular/core';
import {WeightRecordDTO} from "../dto/weight-record-d-t-o";
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
  oneWeek : number = 604800000;
  oneMonth : number = 2628000000;
  threeMonth : number = 7884000000;
  oneYear : number = 31540000000;

  constructor(private weightRecordListService : WeightRecordListService) { }

  ngOnInit(): void {
    this.getWeightRecordListAndAverageWeightRecordList()
  }

  getWeightRecordListAndAverageWeightRecordList(){
    this.getWeightRecordList();
    this.getAverageWeightRecordList();
  }

  getWeightRecordList(){
    this.weightRecordListService.getWeightRecordList()
      .subscribe(
        (data :WeightRecordDTO[]) => this.weightRecordList = this.filterDataByPeriod(data.reverse(), this.oneWeek));
  }

  getAverageWeightRecordList(){
    this.weightRecordListService.getAverageWeightRecordList()
      .subscribe(
        (data :WeightRecordDTO[]) => this.averageWeightRecordList = this.filterDataByPeriod(data.reverse(), this.oneWeek));
  }

  setToggleOnWeight(){
    console.log('setToggleToWeight', 'Switch !')
    this.isAverageWeightToggleSelected = false;
  }

  setToggleOnAverageWeight(){
    console.log('setToggleToAverageWeight', 'Switch !')
    this.isAverageWeightToggleSelected = true;
  }

  private filterDataByPeriod(weightRecordDTOList: WeightRecordDTO[], period: number): WeightRecordDTO[] {
    let filteredData : WeightRecordDTO[] = [];
    let dateMinusPeriod : number = weightRecordDTOList[0].date-period;

    for (let i = 0; i < weightRecordDTOList.length; i++) {
      if (weightRecordDTOList[i].date <= weightRecordDTOList[0].date && weightRecordDTOList[i].date > dateMinusPeriod){
        filteredData.push(weightRecordDTOList[i])
      }
    }
    return filteredData;
  }
}
