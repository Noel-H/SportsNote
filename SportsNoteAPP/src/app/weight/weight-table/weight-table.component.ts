import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {WeightRecordDTO} from "../../interface/weight-record-d-t-o";

@Component({
  selector: 'app-weight-table',
  templateUrl: './weight-table.component.html',
  styleUrls: ['./weight-table.component.scss']
})
export class WeightTableComponent implements OnInit, OnChanges {

  @Input() weightRecordList: WeightRecordDTO[] = [];
  @Input() averageWeightRecordList: WeightRecordDTO[] = [];
  @Input() isAverageWeightToggleSelected: boolean = false;
  displayedColumns: string[] = ['date', 'weight', 'options'];
  recordList : WeightRecordDTO[] = [];
  weightType : String = 'test';

  constructor() {
  }

  ngOnInit(): void {

  }

  ngOnChanges(changes: SimpleChanges): void {
    this.getList();
  }

  getList(){
    if (this.isAverageWeightToggleSelected){
      this.recordList = this.averageWeightRecordList;
      this.weightType = 'Average weight';
    } else {
      this.recordList = this.weightRecordList;
      this.weightType = 'Weight';
    }
  }

}
