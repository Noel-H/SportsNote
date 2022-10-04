import {Component, Input, OnInit} from '@angular/core';
import {Chart} from "chart.js";
import { registerables } from 'chart.js';
import {WeightRecordDTO} from "../../dto/weight-record-d-t-o";
import {Observable} from "rxjs";

@Component({
  selector: 'app-weight-chart',
  templateUrl: './weight-chart.component.html',
  styleUrls: ['./weight-chart.component.scss']
})

export class WeightChartComponent implements OnInit {

  @Input() weightRecordList : WeightRecordDTO[] = [];
  @Input() averageWeightRecordList : WeightRecordDTO[] = [];
  @Input() isAverageWeightToggleSelected : Observable<boolean> = new Observable<boolean>();
  @Input() weightChange: Observable<WeightRecordDTO[]> = new Observable<WeightRecordDTO[]>();
  @Input() averageWeightChange: Observable<WeightRecordDTO[]> = new Observable<WeightRecordDTO[]>();
  isAverageWeightToggleOn : boolean = false;
  dateList : Date[] = [];
  weightList : number[] = [];
  averageWeightList : number[] = [];
  chart : any = [];
  // chart : Chart = new Chart('testChart',{type : "line", data : {labels : [], datasets : []}});

  constructor() { }

  ngOnInit(): void {
    Chart.register(...registerables);
    this.createdChart();
    this.isAverageWeightToggleSelected.subscribe(value => {
      this.isAverageWeightToggleOn = value
    });
    this.weightChange.subscribe(value => {
      this.weightRecordList = value
      this.loadData();
    });
    this.averageWeightChange.subscribe(value => {
      this.averageWeightRecordList = value
      this.loadData();
    });
  }

  loadData(): void {
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

    this.chart.data.labels = this.dateList.reverse().map(value => value.toLocaleDateString());
    this.chart.data.datasets[0].label = 'Weight/Date';
    this.chart.data.datasets[0].data = this.weightList.reverse();
    this.chart.data.datasets[0].borderColor = '#3F51B5';
    this.chart.data.datasets[0].pointRadius = 0;
    // this.chart.data.datasets[0].hidden = false;

    this.chart.data.datasets[1].label = 'Average Weight/Date';
    this.chart.data.datasets[1].data = this.averageWeightList.reverse();
    this.chart.data.datasets[1].borderColor = '#FF4081';
    this.chart.data.datasets[1].pointRadius = 0;
    // this.chart.data.datasets[1].hidden = true;

    this.showCorrectData();

    this.chart.update();
  }

  private getDateFromWeightRecordList() {
    this.dateList = this.weightRecordList.map(weighRecordDTO => new Date(weighRecordDTO.date));
  }

  private getWeightFromWeightRecordList() {
    this.weightList = this.weightRecordList.map(weighRecordDTO => weighRecordDTO.weight);
  }

  private getAverageWeightFromAverageWeightRecordList() {
    this.averageWeightList = this.averageWeightRecordList.map(weighRecordDTO => weighRecordDTO.weight);
  }

  private showCorrectData(){
    if (this.isAverageWeightToggleOn){
      this.chart.data.datasets[0].hidden = true;
      this.chart.data.datasets[1].hidden = false;
    } else {
      this.chart.data.datasets[0].hidden = false;
      this.chart.data.datasets[1].hidden = true;
    }
  }
}
