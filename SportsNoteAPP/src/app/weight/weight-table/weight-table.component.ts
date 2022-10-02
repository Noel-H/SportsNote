import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {WeightRecordDTO} from "../../interface/weight-record-d-t-o";
import {WeightTableUpdateDialogComponent} from "./weight-table-update-dialog/weight-table-update-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {WeightTableDeleteDialogComponent} from "./weight-table-delete-dialog/weight-table-delete-dialog.component";

@Component({
  selector: 'app-weight-table',
  templateUrl: './weight-table.component.html',
  styleUrls: ['./weight-table.component.scss']
})
export class WeightTableComponent implements OnInit, OnChanges {

  @Input() weightRecordList: WeightRecordDTO[] = [];
  @Input() averageWeightRecordList: WeightRecordDTO[] = [];
  @Input() isAverageWeightToggleSelected: boolean = false;
  @Output() onWeightChange = new EventEmitter<void>();
  displayedColumns: string[] = ['date', 'weight', 'options'];
  recordList : WeightRecordDTO[] = [];
  weightType : String = 'test';

  constructor(private dialog: MatDialog) {
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

  openUpdateDialog(weightRecordDTO: WeightRecordDTO): void {
    this.dialog.open(WeightTableUpdateDialogComponent, {
      data: {
        weightRecordDTO: weightRecordDTO
      }
    }).afterClosed()
      .subscribe(value => {
        console.log('valueLog', value)
        if(value == "success"){
          this.onWeightChange.emit();
        }
      });
  }

  openDeleteDialog(weightRecordDTO: WeightRecordDTO): void {
    this.dialog.open(WeightTableDeleteDialogComponent, {
      data: {
        weightRecordDTO: weightRecordDTO,
      },
    autoFocus: false
    }).afterClosed()
      .subscribe(value => {
        console.log('valueLog', value)
        if(value == "success"){
          this.onWeightChange.emit();
        }
      });
  }

}
