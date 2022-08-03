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



@NgModule({
  declarations: [
    AppComponent,
    HelloWorldComponent,
    WeightComponent,
    NavBarComponent,
    AverageWeightComponent,
    WeightChartComponent,
    WeightTableComponent,
    WeightNavBarComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatButtonToggleModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
