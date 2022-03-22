import { Component, OnInit } from '@angular/core';
import {HelloWorldService} from "../service/hello-world.service";
import {HelloWorld} from "../interface/hello-world";

@Component({
  selector: 'app-hello-world',
  templateUrl: './hello-world.component.html',
  styleUrls: ['./hello-world.component.css']
})
export class HelloWorldComponent implements OnInit{

  helloWorld!: String;

  constructor(private helloWorldService: HelloWorldService) {
  }

  ngOnInit(): void {
    this.helloWorldService.getHelloWorld()
      .subscribe(
      (data: HelloWorld) => this.helloWorld = data.helloWorld);
  }
}
