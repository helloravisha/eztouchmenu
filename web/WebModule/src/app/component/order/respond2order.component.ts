/**
 * Created by harini on 11/26/17.
 */
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup} from '@angular/forms';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import {RestURLs} from '../../constants/RestURLs';


@Component({
  selector: 'app-respond2order',
  templateUrl: './respond2order.component.html',
  styleUrls: ['./order.component.css']
})
export class Respond2orderComponent implements OnInit {
  orderUpdateForm: any = null;
  data: any = null;
  itemData: any = null;
  orderID: any = null;
  currentOrder: any = null;
  orderedItems: any = null;

  constructor(private _http: Http, private activatedRoute: ActivatedRoute) {
    this.activatedRoute.queryParams.subscribe((params: Params) => {
      this.orderID = params['id'];
    });
    this.getItems();
    this.getOrders();
  }

  mySplit (input: string) {
    return input.split(',');
   }
  private getItems() {
    return this._http.get(RestURLs.ITEM_GET_URL)
      .map((res: Response) => res.json())
      .subscribe(data => {
        this.itemData = data;
      });
  }

  private getOrders() {
    return this._http.get(RestURLs.ORDERS_GET_URL)
      .map((res: Response) => res.json())
      .subscribe(data => {
        this.data = data;
        for (const order of data) {
          if (order.id == this.orderID) {
            this.currentOrder = order;
            this.orderedItems = this.mySplit(order.orderedItems);
          }
        }
      });
  }

  private updateOrder(res) {
    this.currentOrder.orderStatus = res.value.orderStatus;
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    const url = RestURLs.ORDERS_GET_URL + '/' + this.currentOrder.id;
    console.log(this.currentOrder);
    return this._http.put(`${url}`, this.currentOrder, headers)
      .map((res1: Response) => res1.json())
      .subscribe(
      data => {
        console.log(data);
      },
      err => {
        console.log('Error occured: ' + err);
      }
    );
  }

  ngOnInit() {
    this.orderUpdateForm = new FormGroup({
      orderStatus: new FormControl()
    });
  }
}
