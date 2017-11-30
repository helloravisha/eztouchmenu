/**
 * Created by harini on 11/6/17.
 */
import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { FormControl, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import {RestURLs} from '../../constants/RestURLs';


@Component({
  selector: 'app-order',
  templateUrl: './createitem.component.html',
  styleUrls: ['./item.component.css']
})
export class CreateItemComponent implements OnInit {
  data: any = null;
  itemCreateForm: any = null;
  acceptedMimeTypes = [
    'image/gif',
    'image/jpeg',
    'image/png'
  ];

  @ViewChild('fileInput') fileInput: ElementRef;
  fileDataUri = '';
  errorMsg = '';

  constructor(private _http: Http, private router: Router) {
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

  private createItem (res) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    const url = RestURLs.ITEM_GET_URL;
    res.value.imagepath = 'https://s3-us-west-1.amazonaws.com/babytrak-assets/menuimages/mushroom-rice_625x350_61424324920.jpg';
    console.log(res.value);
    this._http.post(`${url}`, res.value, headers)
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
    this.itemCreateForm = new FormGroup({
      name: new FormControl(),
      description: new FormControl(),
      price: new FormControl(),
      imagepath: new FormControl()
    });
  }

}
