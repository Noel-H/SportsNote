import { Component, OnInit } from '@angular/core';
import {HelloWorldService} from "../service/hello-world.service";
import {HelloWorldDTO} from "../interface/hello-world-d-t-o";

@Component({
  selector: 'app-hello-world',
  templateUrl: './hello-world.component.html',
  styleUrls: ['./hello-world.component.scss']
})
export class HelloWorldComponent implements OnInit{

  helloWorld!: String;

  constructor(private helloWorldService: HelloWorldService) {
  }

  ngOnInit(): void {
    this.helloWorldService.getHelloWorld()
      .subscribe(
      (data: HelloWorldDTO) => this.helloWorld = data.helloWorld);
  }
}
