import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {WeightRecordListService} from "../../../service/weight-record-list.service";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {WeightRecordDTO} from "../../../interface/weight-record-d-t-o";

@Component({
  selector: 'app-weight-table-update-dialog',
  templateUrl: './weight-table-update-dialog.component.html',
  styleUrls: ['./weight-table-update-dialog.component.scss']
})
export class WeightTableUpdateDialogComponent implements OnInit {

  date : FormControl = new FormControl(new Date(this.data.weightRecordDTO.date), Validators.required);
  weight : FormControl = new FormControl(this.data.weightRecordDTO.weight, [Validators.required, Validators.pattern(/^\d*[.,]?\d?$/),]);

  constructor(private weightRecordListService : WeightRecordListService,
              @Inject(MAT_DIALOG_DATA) private data: {weightRecordDTO: WeightRecordDTO}) { }

  ngOnInit(): void {
  }

  isFormInvalid(): boolean{
    return this.date.invalid || this.weight.invalid;
  }

  updateButtonClick(): void {
    this.weightRecordListService.updateWeightRecord({date : this.date.value, weight : this.weight.value}).subscribe();
  }

}