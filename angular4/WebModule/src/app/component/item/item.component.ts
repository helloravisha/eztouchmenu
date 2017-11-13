/**
 * Created by harini on 11/6/17.
 */


import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import {RestURLs} from '../../constants/RestURLs';

@Component({
  selector: 'app-order',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {
  data: any = null;

  constructor(private _http: Http) {
    this.getItems();
  }

  private getItems() {
    return this._http.get(RestURLs.ITEM_GET_URL)
      .map((res: Response) => res.json())
      .subscribe(data => {
        this.data = data;
        console.log(this.data);
      });
  }

  ngOnInit() {
  }

}



// Write code for APi here
// Write parsing login of REST API
// Store it in objects
// Any varialbes in this file can be used in the HTML file
// Search: How to consume REST API inside with type script
// Login should be only inside this file
// We can do for loop or basic UI inside HTML but NO LOGIN
// Using <script> inside HTML file is not advisable
// Create classes indie vo and to
// REST API (read data) details in vo folder
// Create Update Delete then use/ create classes inside vo folder

