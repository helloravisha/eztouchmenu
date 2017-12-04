/**
 * Created by harini on 11/6/17.
 */
import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import {RestURLs} from '../../constants/RestURLs';

@Component({
  selector: 'app-displaymenu',
  templateUrl: './displaymenu.component.html',
  styleUrls: ['./menu.component.css']
})
export class DisplayMenuComponent implements OnInit {

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
