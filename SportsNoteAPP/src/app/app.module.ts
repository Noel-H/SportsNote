import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HelloWorldComponent } from './hello-world/hello-world.component';
import { WeightComponent } from './weight/weight.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { AverageWeightComponent } from './average-weight/average-weight.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatTableModule} from "@angular/material/table";
import { WeightChartComponent } from './weight-chart/weight-chart.component';
import { WeightTableComponent } from './weight-table/weight-table.component';
import { WeightNavBarComponent } from './weight-nav-bar/weight-nav-bar.component';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatDialogModule} from '@angular/material/dialog';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatCardModule} from "@angular/material/card";
import {WeightRecordViewModule} from "./weight-record-view/weight-record-view.module";
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatNativeDateModule} from "@angular/material/core";
import {MatInputModule} from "@angular/material/input";
import { WeightNavBarAddDialogComponent } from './weight-nav-bar-add-dialog/weight-nav-bar-add-dialog.component';


@NgModule({
  declarations: [
    AppComponent,
    HelloWorldComponent,
    WeightComponent,
    NavBarComponent,
    AverageWeightComponent,
    WeightChartComponent,
    WeightTableComponent,
    WeightNavBarComponent,
    WeightNavBarAddDialogComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatButtonToggleModule,
    MatDialogModule,
    MatDatepickerModule,
    MatCardModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatInputModule,
    WeightRecordViewModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
