/**
 * Created by harini on 11/6/17.
 */
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params} from '@angular/router';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import {RestURLs} from '../../constants/RestURLs';

@Component({
  selector: 'app-order',
  templateUrl: './deleteitem.component.html',
  styleUrls: ['./item.component.css']
})
export class DeleteItemComponent implements OnInit {
  data: any = null;
  itemID: any = null;
  currentItem: any = null;

  constructor(private _http: Http, private activatedRoute: ActivatedRoute,) {
    this.activatedRoute.queryParams.subscribe((params: Params) => {
      this.itemID = params['id'];
      // console.log(this.itemID);
    });
    this.getItems();
  }
  private getItems() {
    return this._http.get(RestURLs.ITEM_GET_URL)
      .map((res: Response) => res.json())
      .subscribe(data => {
        this.data = data;
        for (const eachItem of this.data) {
          if (eachItem.id == this.itemID) {
            this.currentItem = eachItem;
            // console.log(this.currentItem);
          }
        }
      });
  }
  private deleteItem() {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    const url = RestURLs.ITEM_GET_URL + '/' + this.currentItem.id;
    console.log(this.currentItem);
    return this._http.delete(`${url}`, this.currentItem)
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
  }

}
