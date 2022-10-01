import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {WeightNavBarAddDialogComponent} from "./weight-nav-bar-add-dialog/weight-nav-bar-add-dialog.component";

@Component({
  selector: 'app-weight-nav-bar',
  templateUrl: './weight-nav-bar.component.html',
  styleUrls: ['./weight-nav-bar.component.scss']
})
export class WeightNavBarComponent implements OnInit {

  @Output() onWeightChange = new EventEmitter<void>();

  constructor(private dialog: MatDialog) { }

  ngOnInit(): void {
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
