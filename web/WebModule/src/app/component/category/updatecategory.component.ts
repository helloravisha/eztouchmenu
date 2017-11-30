/**
 * Created by harini on 11/6/17.
 */

import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { ActivatedRoute, Params} from '@angular/router';
import { FormControl, FormGroup} from '@angular/forms';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import {RestURLs} from '../../constants/RestURLs';


@Component({
  selector: 'app-updatecategory',
  templateUrl: './updatecategory.component.html',
  styleUrls: ['./category.component.css']
})
export class UpdateCategoryComponent implements OnInit {
  data: any = null;
  categoryID: any = null;
  currentCategory: any = null;
  categoryUpdateForm: any = null;

  acceptedMimeTypes = [
    'image/gif',
    'image/jpeg',
    'image/png'
  ];

  @ViewChild('fileInput') fileInput: ElementRef;
  fileDataUri = '';
  errorMsg = '';

  constructor(private _http: Http, private activatedRoute: ActivatedRoute) {
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

  private updateCategory(res) {
    console.log(res);
    if (res.value.name && res.value.description) {
      this.currentCategory.name = res.value.name;
      this.currentCategory.description = res.value.description;
    }
    // Need to retrieve the file path from S3 using upload response
    this.currentCategory.imagepath = 'https://s3-us-west-1.amazonaws.com/ezmenutouch-images/drinks.png';
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    const url = RestURLs.CATEGORY_GET_URL + '/' + this.currentCategory.id;
    console.log(this.currentCategory);
    return this._http.put(`${url}`, this.currentCategory, headers)
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
    this.categoryUpdateForm = new FormGroup({
      name: new FormControl(),
      description: new FormControl()
      // imagepath: new FormControl()
    });
  }

}
