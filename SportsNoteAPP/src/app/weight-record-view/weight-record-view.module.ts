import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WeightRecordComponent } from './weight-record/weight-record.component';



@NgModule({
    declarations: [
        WeightRecordComponent
    ],
    exports: [
        WeightRecordComponent
    ],
    imports: [
        CommonModule
    ]
})
export class WeightRecordViewModule { }
