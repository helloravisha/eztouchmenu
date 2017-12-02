/**
 * Created by harini on 11/6/17.
 */

import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import {RestURLs} from '../../constants/RestURLs';


@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})

export class CategoryComponent implements OnInit {
  data: any = null;

  constructor(private _http: Http) {
    this.getComponent();
  }

  private getComponent() {
    return this._http.get(RestURLs.CATEGORY_GET_URL)
      .map((res: Response) => res.json())
      .subscribe(data => {
        this.data = data;
        console.log(this.data);
      });
  }
  ngOnInit() {
  }

}

// Write code for API here
// Write parsing login of REST API
// Store it in objects
// Any varialbes in this file can be used in the HTML file
// Search: How to consume REST API inside with type script
// Logic should be only inside this file
// We can do 'for loop' or basic UI inside HTML but NO LOGIC
// Using <script> inside HTML file is not advisable
// Create class with required variables inside vo. REST API (read data) details in vo folder
// Use the constant for the RestAPI URL
// Create Update Delete then use/create classes inside vo folder

