import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {WeightRecordDTO} from "../../dto/weight-record-d-t-o";
import {WeightTableUpdateDialogComponent} from "./weight-table-update-dialog/weight-table-update-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {WeightTableDeleteDialogComponent} from "./weight-table-delete-dialog/weight-table-delete-dialog.component";
import {Observable} from "rxjs";

@Component({
  selector: 'app-weight-table',
  templateUrl: './weight-table.component.html',
  styleUrls: ['./weight-table.component.scss']
})
export class WeightTableComponent implements OnInit {

  @Input() weightRecordList: WeightRecordDTO[] = [];
  @Input() averageWeightRecordList: WeightRecordDTO[] = [];
  @Input() isAverageWeightToggleSelected : Observable<boolean> = new Observable<boolean>();
  @Input() weightChange: Observable<WeightRecordDTO[]> = new Observable<WeightRecordDTO[]>();
  @Input() averageWeightChange: Observable<WeightRecordDTO[]> = new Observable<WeightRecordDTO[]>();
  @Output() onWeightChange = new EventEmitter<void>();
  @Output() onSetToggleToWeight = new EventEmitter<void>();
  @Output() onSetToggleToAverageWeight = new EventEmitter<void>();
  @Output() period : EventEmitter<number> = new EventEmitter<number>();
  isAverageWeightToggleOn : boolean = false;
  displayedColumns: string[] = ['date', 'weight', 'options'];
  recordList : WeightRecordDTO[] = [];
  weightType : String = 'test';
  isDateFilterActivated : boolean = false;
  periodFilterStatus : string = 'All';

  constructor(private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.isAverageWeightToggleSelected.subscribe(value => {
      this.isAverageWeightToggleOn = value;
      this.getList()
    });
    this.weightChange.subscribe(value => {
      this.weightRecordList = value;
      this.recordList = value;
    });
    this.averageWeightChange.subscribe(value => {
      this.averageWeightRecordList = value
      this.recordList = value;
    });
  }

  getList(){
    if (this.isAverageWeightToggleOn){
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
    if (this.isAverageWeightToggleOn){
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
    this.periodFilterStatus = "LastWeek";
  }

  setDateFilterToLastTwoWeek(){
    this.activateDateFilter();
    this.period.emit(604800000*2);
    this.periodFilterStatus = "LastTwoWeek";
  }

  setDateFilterToLastMonth(){
    this.activateDateFilter();
    this.period.emit(2628000000);
    this.periodFilterStatus = "LastMonth";
  }

  setDateFilterToLastThreeMonth(){
    this.activateDateFilter();
    this.period.emit(7884000000);
    this.periodFilterStatus = "LastThreeMonth";
  }

  setDateFilterToLastSixMonth(){
    this.activateDateFilter();
    this.period.emit(15770000000);
    this.periodFilterStatus = "LastSixMonth";
  }

  setDateFilterToLastYear(){
    this.activateDateFilter();
    this.period.emit(31540000000);
    this.periodFilterStatus = "LastYear";
  }

  setDateFilterToChoose(){
    this.activateDateFilter();
    this.periodFilterStatus = "Choose";
  }

  setDateFilterToAll(){
    this.deactivateDateFilter();
    this.period.emit(0);
    this.periodFilterStatus = "All";
  }

  isPeriodFilterStatusLastWeekForDisabled(): boolean{
    return this.periodFilterStatus == "LastWeek";

  }

  isPeriodFilterStatusLastWeekForClass(): string{
    if (this.periodFilterStatus=="LastWeek"){
      return "primaryColorMenu";
    }
    return "";
  }

  isPeriodFilterStatusLastTwoWeekForDisabled(): boolean{
    return this.periodFilterStatus == "LastTwoWeek";

  }

  isPeriodFilterStatusLastTwoWeekForClass(): string{
    if (this.periodFilterStatus=="LastTwoWeek"){
      return "primaryColorMenu";
    }
    return "";
  }

  isPeriodFilterStatusLastMonthForDisabled(): boolean{
    return this.periodFilterStatus == "LastMonth";

  }

  isPeriodFilterStatusLastMonthForClass(): string{
    if (this.periodFilterStatus=="LastMonth"){
      return "primaryColorMenu";
    }
    return "";
  }

  isPeriodFilterStatusLastThreeMonthForDisabled(): boolean{
    return this.periodFilterStatus == "LastThreeMonth";

  }

  isPeriodFilterStatusLastThreeMonthForClass(): string{
    if (this.periodFilterStatus=="LastThreeMonth"){
      return "primaryColorMenu";
    }
    return "";
  }

  isPeriodFilterStatusLastSixMonthForDisabled(): boolean{
    return this.periodFilterStatus == "LastSixMonth";

  }

  isPeriodFilterStatusLastSixMonthForClass(): string{
    if (this.periodFilterStatus=="LastSixMonth"){
      return "primaryColorMenu";
    }
    return "";
  }

  isPeriodFilterStatusLastYearForDisabled(): boolean{
    return this.periodFilterStatus == "LastYear";

  }

  isPeriodFilterStatusLastYearForClass(): string{
    if (this.periodFilterStatus=="LastYear"){
      return "primaryColorMenu";
    }
    return "";
  }

  isPeriodFilterStatusAllForDisabled(): boolean{
    return this.periodFilterStatus == "All";

  }

  isPeriodFilterStatusAllForClass(): string{
    if (this.periodFilterStatus=="All"){
      return "greyColorMenu";
    }
    return "";
  }


  activateDateFilter(){
    this.isDateFilterActivated = true;
  }

  deactivateDateFilter(){
    this.isDateFilterActivated = false;
  }
}
