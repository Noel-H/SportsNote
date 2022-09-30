import { Component, OnInit } from '@angular/core';
import {WeightRecordListService} from "../../../service/weight-record-list.service";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-weight-nav-bar-add-dialog',
  templateUrl: './weight-nav-bar-add-dialog.component.html',
  styleUrls: ['./weight-nav-bar-add-dialog.component.scss']
})
export class WeightNavBarAddDialogComponent implements OnInit {

  date : FormControl = new FormControl(new Date(), Validators.required);
  weight : FormControl = new FormControl(null, [Validators.required, Validators.pattern(/^\d*[.,]?\d?$/)]);

  constructor(private weightRecordListService : WeightRecordListService) {}

  ngOnInit(): void {
  }

  isFormInvalid(): boolean{
    return this.date.invalid || this.weight.invalid;
  }

  addButtonClick(): void {
    console.log(this.date.value);
    console.log();
    console.log();
    console.log();
    this.weightRecordListService.addWeightRecord({date : this.date.value, weight : this.weight.value}).subscribe();
  }

}
