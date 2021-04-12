import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-add-button',
  templateUrl: './add-button.component.html',
  styleUrls: ['./add-button.component.css']
})
export class AddButtonComponent implements OnInit {

  @Input() addUrl: string;

  constructor() { }

  ngOnInit() {
  }

}
