/**
 * Created by harini on 11/6/17.
 */

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params} from '@angular/router';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import {RestURLs} from '../../constants/RestURLs';


@Component({
  selector: 'app-deletecategory',
  templateUrl: './deletecategory.component.html',
  styleUrls: ['./category.component.css']
})
export class DeleteCategoryComponent implements OnInit {
  data: any = null;
  categoryID: any = null;
  currentCategory: any = null;

  constructor(private _http: Http, private activatedRoute: ActivatedRoute,) {
    this.activatedRoute.queryParams.subscribe((params: Params) => {
      this.categoryID = params['id'];
    });
    this.getComponent();
  }
  private getComponent() {
    return this._http.get(RestURLs.CATEGORY_GET_URL)
      .map((res: Response) => res.json())
      .subscribe(data => {
        this.data = data;
        for (const eachCategroy of this.data) {
          if (eachCategroy.id == this.categoryID) {
            this.currentCategory = eachCategroy;
          }
        }
      });
  }
  private deleteCategory() {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    const url = RestURLs.CATEGORY_GET_URL + '/' + this.currentCategory.id;
    console.log(this.currentCategory);
    return this._http.delete(`${url}`, this.currentCategory)
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
