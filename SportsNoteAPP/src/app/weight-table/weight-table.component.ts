import {Component, Input, OnInit} from '@angular/core';
import {WeightRecordDTO} from "../interface/weight-record-d-t-o";

@Component({
  selector: 'app-weight-table',
  templateUrl: './weight-table.component.html',
  styleUrls: ['./weight-table.component.scss']
})
export class WeightTableComponent implements OnInit {

  @Input() weightRecordList : WeightRecordDTO[] = [];
  @Input() averageWeightRecordList : WeightRecordDTO[] = [];
  displayedColumns: string[] = ['date','weight'];

  constructor() { }

  ngOnInit(): void {

  }

}
