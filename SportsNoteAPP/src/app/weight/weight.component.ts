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

  constructor(private weightRecordListService : WeightRecordListService) { }

  ngOnInit(): void {
    this.weightRecordListService.getWeightRecordList()
      .subscribe(
        (data :WeightRecordDTO[]) => this.weightRecordList = data.reverse());
  }

}
