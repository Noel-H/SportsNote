import { Component, OnInit } from '@angular/core';
import {WeightRecordListService} from "../service/weight-record-list.service";

@Component({
  selector: 'app-weight-nav-bar-add-dialog',
  templateUrl: './weight-nav-bar-add-dialog.component.html',
  styleUrls: ['./weight-nav-bar-add-dialog.component.scss']
})
export class WeightNavBarAddDialogComponent implements OnInit {

  date : Date = new Date();
  weight : number = 70;

  constructor(private weightRecordListService : WeightRecordListService) {}

  ngOnInit(): void {
  }

  addButtonClick(): void {
    this.weightRecordListService.addWeightRecord({date : this.date, weight : this.weight}).subscribe();
  }

}
