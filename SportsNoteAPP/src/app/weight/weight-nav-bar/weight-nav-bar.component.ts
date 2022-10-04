import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {WeightNavBarAddDialogComponent} from "./weight-nav-bar-add-dialog/weight-nav-bar-add-dialog.component";
import {Observable} from "rxjs";

@Component({
  selector: 'app-weight-nav-bar',
  templateUrl: './weight-nav-bar.component.html',
  styleUrls: ['./weight-nav-bar.component.scss']
})
export class WeightNavBarComponent implements OnInit {

  @Input() isAverageWeightToggleSelected : Observable<boolean> = new Observable<boolean>();
  @Output() onWeightChange = new EventEmitter<void>();
  isAverageWeightToggleOn : boolean = false;

  constructor(private dialog: MatDialog) { }

  ngOnInit(): void {
    this.isAverageWeightToggleSelected.subscribe(value => {
      this.isAverageWeightToggleOn = value
    });
  }

  openDialog(): void {
    this.dialog.open(WeightNavBarAddDialogComponent, {})
      .afterClosed()
      .subscribe(value => {
        console.log('valueLog', value)
        if(value == "success"){
          this.onWeightChange.emit();
        }
      });
  }

}
