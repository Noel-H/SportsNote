import { Component, OnInit } from '@angular/core';
import {Chart} from "chart.js";
import { registerables } from 'chart.js';
import {WeightRecordDTO} from "../interface/weight-record-d-t-o";
import {WeightComponent} from "../weight/weight.component";

@Component({
  selector: 'app-weight-chart',
  templateUrl: './weight-chart.component.html',
  styleUrls: ['./weight-chart.component.scss']
})

export class WeightChartComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    Chart.register(...registerables);
    this.createdChart()
  }

  createdChart(): void{
    new Chart('myChart', {
      type : "line",
      data : {
        labels : ["Paris","Lyon","Tokyo","Toronto"],
        datasets : [{
          data : [100,200,300,400]
        }]
      }
    })
  }
}
