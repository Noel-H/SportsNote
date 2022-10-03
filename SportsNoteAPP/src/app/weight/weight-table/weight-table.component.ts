import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {WeightRecordDTO} from "../../dto/weight-record-d-t-o";
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
  @Output() onSetToggleToWeight = new EventEmitter<void>();
  @Output() onSetToggleToAverageWeight = new EventEmitter<void>();
  @Output() period : EventEmitter<number> = new EventEmitter<number>();
  displayedColumns: string[] = ['date', 'weight', 'options'];
  recordList : WeightRecordDTO[] = [];
  weightType : String = 'test';
  isDateFilterActivated : boolean = false;

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

  colorByWeighTypeSelection(): string{
    if (this.isAverageWeightToggleSelected){
      return 'averageWeightColor';
    } else {
      return 'weightColor';
    }
  }

  setToggleToWeight(){
    console.log('setToggleToWeight', 'Click !')
    this.onSetToggleToWeight.emit();
  }

  setToggleToAverageWeight(){
    console.log('setToggleToAverageWeight', 'Click !')
    this.onSetToggleToAverageWeight.emit();
  }

  setDateFilterToLastWeek(){
    this.activateDateFilter();
    this.period.emit(604800000);
  }

  setDateFilterToLastMonth(){
    this.activateDateFilter();
    this.period.emit(2628000000);
  }

  setDateFilterToLastThreeMonth(){
    this.activateDateFilter();
    this.period.emit(7884000000);
  }

  setDateFilterToLastSixMonth(){
    this.activateDateFilter();
    this.period.emit(15770000000);
  }

  setDateFilterToLastYear(){
    this.activateDateFilter();
    this.period.emit(31540000000);
  }

  setDateFilterToChoose(){
    this.activateDateFilter();
  }

  setDateFilterToAll(){
    this.deactivateDateFilter();
    this.period.emit(0);
  }

  activateDateFilter(){
    this.isDateFilterActivated = true;
  }

  deactivateDateFilter(){
    this.isDateFilterActivated = false;
  }
}
