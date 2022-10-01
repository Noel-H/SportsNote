import { Component, OnInit } from '@angular/core';
import {WeightRecordListService} from "../../../service/weight-record-list.service";
import {FormControl, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-weight-nav-bar-add-dialog',
  templateUrl: './weight-nav-bar-add-dialog.component.html',
  styleUrls: ['./weight-nav-bar-add-dialog.component.scss']
})
export class WeightNavBarAddDialogComponent implements OnInit {

  date : FormControl = new FormControl(new Date(), Validators.required);
  weight : FormControl = new FormControl(null, [Validators.required, Validators.pattern(/^\d*[.,]?\d?$/)]);
  isLoading : boolean = false;
  isError : boolean = false;

  constructor(private weightRecordListService : WeightRecordListService,
              private dialogRef : MatDialogRef<WeightNavBarAddDialogComponent>) {}

  ngOnInit(): void {
  }

  isFormInvalid(): boolean{
    return this.date.invalid || this.weight.invalid;
  }

  addButtonClick(): void {
    this.isLoading = true;
    this.weightRecordListService.addWeightRecord({date : this.date.value, weight : this.weight.value})
      .subscribe(value => {
          console.log('testvalue', value)
          this.dialogRef.close("success");
        },
        error => {
          this.isLoading = false;
          this.isError = true;
        });
  }

}
