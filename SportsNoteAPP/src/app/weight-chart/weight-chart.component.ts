import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Chart} from "chart.js";
import { registerables } from 'chart.js';
import {WeightRecordDTO} from "../interface/weight-record-d-t-o";

@Component({
  selector: 'app-weight-chart',
  templateUrl: './weight-chart.component.html',
  styleUrls: ['./weight-chart.component.scss']
})

export class WeightChartComponent implements OnInit, OnChanges {

  @Input() weightRecordList : WeightRecordDTO[] = [];
  dateList : Date[] = [];
  weightList : number[] = [];
  chart : any = [];
  // chart : Chart = new Chart('testChart',{type : "line", data : {labels : [], datasets : []}});

  constructor() { }

  ngOnInit(): void {
    Chart.register(...registerables);
    this.createdChart();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.getDateFromWeightRecordList();
    this.getWeightFromWeightRecordList();
    this.updateChart();
  }

  createdChart(): void{
    this.chart = new Chart('myChart', {
      type : "line",
      data : {
        labels : []
        ,
        datasets : []
      }
    })
  }

  private updateChart(){
    this.chart.data = {labels: []};
    this.chart.data = {datasets: []};
    this.chart.data.datasets[0] = {data : []};

    this.chart.data.labels = this.dateList.reverse();
    this.chart.data.datasets[0].label = 'Weight/Date';
    this.chart.data.datasets[0].data = this.weightList.reverse();

    this.chart.update();
  }

  private getDateFromWeightRecordList() {
    this.dateList = this.weightRecordList.map(weighRecordDTO => weighRecordDTO.date);
  }

  private getWeightFromWeightRecordList() {
    this.weightList = this.weightRecordList.map(weighRecordDTO => weighRecordDTO.weight);
  }
}
