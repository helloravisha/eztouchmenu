/**
 * Created by harini on 11/6/17.
 */

import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import {RestURLs} from '../../constants/RestURLs';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  data: any = null;
  itemData: any = null;

  constructor(private _http: Http) {
    this.getOrders();
    this.getItems();
  }
  private getOrders() {
    return this._http.get(RestURLs.ORDERS_GET_URL)
      .map((res: Response) => res.json())
      .subscribe(data => {
        this.data = data;
      });
  }
  private getItems() {
    return this._http.get(RestURLs.ITEM_GET_URL)
      .map((res: Response) => res.json())
      .subscribe(data => {
        this.itemData = data;
        console.log(this.itemData);
      });
  }

  ngOnInit() {
  }

}
