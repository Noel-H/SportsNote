import {LOCALE_ID, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { WeightComponent } from './weight/weight.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatTableModule} from "@angular/material/table";
import { WeightChartComponent } from './weight/weight-chart/weight-chart.component';
import { WeightTableComponent } from './weight/weight-table/weight-table.component';
import { WeightNavBarComponent } from './weight/weight-nav-bar/weight-nav-bar.component';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatDialogModule} from '@angular/material/dialog';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatNativeDateModule} from "@angular/material/core";
import {MatInputModule} from "@angular/material/input";
import { WeightNavBarAddDialogComponent } from './weight/weight-nav-bar/weight-nav-bar-add-dialog/weight-nav-bar-add-dialog.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatMenuModule} from "@angular/material/menu";
import { WeightTableUpdateDialogComponent } from './weight/weight-table/weight-table-update-dialog/weight-table-update-dialog.component';
import { WeightTableDeleteDialogComponent } from './weight/weight-table/weight-table-delete-dialog/weight-table-delete-dialog.component';
import {registerLocaleData} from "@angular/common";

import fr from '@angular/common/locales/fr';

registerLocaleData(fr);

@NgModule({
  declarations: [
    AppComponent,
    WeightComponent,
    WeightChartComponent,
    WeightTableComponent,
    WeightNavBarComponent,
    WeightNavBarAddDialogComponent,
    WeightTableUpdateDialogComponent,
    WeightTableDeleteDialogComponent
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
    ReactiveFormsModule,
    FormsModule,
    MatMenuModule
  ],
  providers: [
    {
      provide: LOCALE_ID, useValue: 'fr-FR'
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
