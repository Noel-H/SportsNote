import {Component, EventEmitter, OnInit} from '@angular/core';
import {WeightRecordDTO} from "../dto/weight-record-d-t-o";
import {WeightRecordListService} from "../service/weight-record-list.service";

@Component({
  selector: 'app-weight',
  templateUrl: './weight.component.html',
  styleUrls: ['./weight.component.scss']
})
export class WeightComponent implements OnInit {

  weightRecordList : WeightRecordDTO[] = [];
  weightRecordListForFilter : WeightRecordDTO[] = [];
  averageWeightRecordList : WeightRecordDTO[] = [];
  averageWeightRecordListForFilter : WeightRecordDTO[] = [];
  isAverageWeightToggleSelected : boolean = false;
  period : number = 0;
  onWeightChange : EventEmitter<WeightRecordDTO[]> = new EventEmitter<WeightRecordDTO[]>();
  onAverageWeightChange : EventEmitter<WeightRecordDTO[]> = new EventEmitter<WeightRecordDTO[]>();

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
        (data :WeightRecordDTO[]) => {
          this.weightRecordList = data.reverse();
          this.weightRecordListForFilter = data;
          this.onWeightChange.emit(data);
        });
  }

  getAverageWeightRecordList(){
    this.weightRecordListService.getAverageWeightRecordList()
      .subscribe(
        (data :WeightRecordDTO[]) => {
          this.averageWeightRecordList = data.reverse();
          this.averageWeightRecordListForFilter = data;
          this.onAverageWeightChange.emit(data);
        });
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
    if (period == 0){
      return weightRecordDTOList;
    }

    let filteredData : WeightRecordDTO[] = [];
    let dateMinusPeriod : number = weightRecordDTOList[0].date-period;

    for (let i = 0; i < weightRecordDTOList.length; i++) {
      if (weightRecordDTOList[i].date <= weightRecordDTOList[0].date && weightRecordDTOList[i].date > dateMinusPeriod){
        filteredData.push(weightRecordDTOList[i])
      }
    }
    return filteredData;
  }

  setPeriod(periodInMillisecond: number){
    this.period = periodInMillisecond;
    this.weightRecordList = this.filterDataByPeriod(this.weightRecordListForFilter,periodInMillisecond);
    this.averageWeightRecordList = this.filterDataByPeriod(this.averageWeightRecordListForFilter,periodInMillisecond);
  }
}
