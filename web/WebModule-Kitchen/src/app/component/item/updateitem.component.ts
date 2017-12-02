/**
 * Created by harini on 11/6/17.
 */

import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { FormControl, FormGroup} from '@angular/forms';
import { Router, ActivatedRoute, Params} from '@angular/router';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import {RestURLs} from '../../constants/RestURLs';


@Component({
  selector: 'app-order',
  templateUrl: './updateitem.component.html',
  styleUrls: ['./item.component.css']
})
export class UpdateItemComponent implements OnInit {
  data: any = null;
  itemsData: any = null;
  itemUpdateForm: any = null;
  itemID: any = null;
  currentItem: any = null;
  acceptedMimeTypes = [
    'image/gif',
    'image/jpeg',
    'image/png'
  ];

  @ViewChild('fileInput') fileInput: ElementRef;
  fileDataUri = '';
  errorMsg = '';

  constructor(private _http: Http, private activatedRoute: ActivatedRoute, private router: Router) {
    this.activatedRoute.queryParams.subscribe((params: Params) => {
      this.itemID = params['id'];
      // console.log(this.itemID);
    });
    this.getItems();
    this.getComponent();
  }
  private getComponent() {
    return this._http.get(RestURLs.CATEGORY_GET_URL)
      .map((res: Response) => res.json())
      .subscribe(data => {
        this.data = data;
      });
  }

  private getItems() {
    return this._http.get(RestURLs.ITEM_GET_URL)
      .map((res: Response) => res.json())
      .subscribe(data => {
        this.itemsData = data;
        for (const eachItem of this.itemsData) {
          if (eachItem.id == this.itemID) {
            this.currentItem = eachItem;
            // console.log(this.currentItem);
          }
        }
      });
  }
  previewFile() {
    const file = this.fileInput.nativeElement.files[0];
    if (file && this.validateFile(file)) {

      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.fileDataUri = reader.result;
        console.log(this.fileDataUri);
      }
    } else {
      this.errorMsg = 'File must be jpg, png, or gif and cannot be exceed 500 KB in size'
    }
  }

  uploadFile(event: Event) {
    event.preventDefault();

    // get only the base64 file and post it
    if (this.fileDataUri.length > 0) {
      const base64File = this.fileDataUri.split(',')[1];
      const requestData = {'image': base64File};
      console.log(requestData);
      const headers = new Headers();
      headers.append('Content-Type', 'application/json');
      this._http.post(`${RestURLs.IMAGE_UPLOAD_URL}`, requestData, headers)
        .subscribe(
          data => {
            console.log(data);
          },
          err => {
            console.log('Error occured: ' + err);
          }
        );
    }
  }

  validateFile(file) {
    return this.acceptedMimeTypes.includes(file.type) && file.size < 500000;
  }

  private updateItem (res) {
    if (res.value.name && res.value.description && res.value.price) {
      this.currentItem.name = res.value.name;
      this.currentItem.description = res.value.description;
      this.currentItem.price = res.value.price;
    }
    for (const category of this.data){
      if (category.id == res.value.foodcategory){
        res.value.foodcategory = category;
      }
    }
    // Need to retrieve the file path from S3 using upload response
    this.currentItem.imagepath = 'https://s3-us-west-1.amazonaws.com/babytrak-assets/menuimages/mushroom-rice_625x350_61424324920.jpg';
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    const url = RestURLs.ITEM_GET_URL + '/' + this.currentItem.id;
    console.log(this.currentItem);
    this._http.put(`${url}`, this.currentItem, headers)
      .map((res1: Response) => res1.json())
      .subscribe(
        data => {
          console.log(data);
        },
        err => {
          console.log('Error occured: ' + err);
        }
      );
    setTimeout(() => {
      this.router.navigate(['/items']);
    }, 500);
  }


  ngOnInit() {
    this.itemUpdateForm = new FormGroup({
      name: new FormControl(),
      description: new FormControl(),
      price: new FormControl(),
      // imagepath: new FormControl()
    });
  }

}
