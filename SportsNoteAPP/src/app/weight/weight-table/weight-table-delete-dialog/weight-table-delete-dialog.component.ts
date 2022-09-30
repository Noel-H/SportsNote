import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {WeightRecordListService} from "../../../service/weight-record-list.service";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {WeightRecordDTO} from "../../../interface/weight-record-d-t-o";

@Component({
  selector: 'app-weight-table-delete-dialog',
  templateUrl: './weight-table-delete-dialog.component.html',
  styleUrls: ['./weight-table-delete-dialog.component.scss']
})
export class WeightTableDeleteDialogComponent implements OnInit {

  date : FormControl = new FormControl(new Date(this.data.weightRecordDTO.date), Validators.required);
  weight : FormControl = new FormControl(this.data.weightRecordDTO.weight, [Validators.required, Validators.pattern(/^\d*[.,]?\d?$/),]);

  constructor(private weightRecordListService : WeightRecordListService,
              @Inject(MAT_DIALOG_DATA) private data: {weightRecordDTO: WeightRecordDTO}) { }

  ngOnInit(): void {
  }

  isFormInvalid(): boolean{
    return this.date.invalid || this.weight.invalid;
  }

  deleteButtonClick(): void {
    // console.log(new Date(this.data.weightRecordDTO.date).getFullYear());
    // console.log(new Date(this.data.weightRecordDTO.date));
    // console.log(new Date(this.data.weightRecordDTO.date).getDate());
    // console.log(new Date(this.data.weightRecordDTO.date).valueOf());
    // this.weightRecordListService.deleteWeightRecord(new Date(this.data.weightRecordDTO.date).getFullYear()+"-"+new Date(this.data.weightRecordDTO.date).getMonth()+"-"+new Date(this.data.weightRecordDTO.date).getDate()).subscribe();
  }

}
