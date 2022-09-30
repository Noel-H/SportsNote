import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Chart} from "chart.js";
import { registerables } from 'chart.js';
import {WeightRecordDTO} from "../../interface/weight-record-d-t-o";

@Component({
  selector: 'app-weight-chart',
  templateUrl: './weight-chart.component.html',
  styleUrls: ['./weight-chart.component.scss']
})

export class WeightChartComponent implements OnInit, OnChanges {

  @Input() weightRecordList : WeightRecordDTO[] = [];
  @Input() averageWeightRecordList : WeightRecordDTO[] = [];
  @Input() isAverageWeightToggleSelected : boolean = false;
  dateList : Date[] = [];
  weightList : number[] = [];
  averageWeightList : number[] = [];
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
    this.getAverageWeightFromAverageWeightRecordList()
    this.updateChart();
  }

  createdChart(): void{
    this.chart = new Chart('myChart', {
      type : "line",
      data : {
        labels : []
        ,
        datasets : []
      },
      options : {
        responsive : true,
        maintainAspectRatio : false
      }
    })
  }

  private updateChart(){
    this.chart.data = {labels: []};
    this.chart.data = {datasets: []};
    this.chart.data.datasets[0] = {data : []};
    this.chart.data.datasets[1] = {data : []};

    this.chart.data.labels = this.dateList.reverse();
    this.chart.data.datasets[0].label = 'Weight/Date';
    this.chart.data.datasets[0].data = this.weightList.reverse();
    this.chart.data.datasets[0].borderColor = '#247BC0';
    // this.chart.data.datasets[0].hidden = false;

    this.chart.data.datasets[1].label = 'Average Weight/Date';
    this.chart.data.datasets[1].data = this.averageWeightList.reverse();
    this.chart.data.datasets[1].borderColor = '#C979E5';
    // this.chart.data.datasets[1].hidden = true;

    this.showCorrectData();

    this.chart.update();
  }

  private getDateFromWeightRecordList() {
    this.dateList = this.weightRecordList.map(weighRecordDTO => weighRecordDTO.date);
  }

  private getWeightFromWeightRecordList() {
    this.weightList = this.weightRecordList.map(weighRecordDTO => weighRecordDTO.weight);
  }

  private getAverageWeightFromAverageWeightRecordList() {
    this.averageWeightList = this.averageWeightRecordList.map(weighRecordDTO => weighRecordDTO.weight);
  }

  private showCorrectData(){
    if (this.isAverageWeightToggleSelected){
      this.chart.data.datasets[0].hidden = true;
      this.chart.data.datasets[1].hidden = false;
    } else {
      this.chart.data.datasets[0].hidden = false;
      this.chart.data.datasets[1].hidden = true;
    }
  }
}
