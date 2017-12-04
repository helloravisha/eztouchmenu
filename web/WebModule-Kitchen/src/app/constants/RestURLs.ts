import {Injectable} from '@angular/core';
@Injectable()


export class RestURLs{
  public static ITEM_GET_URL="https://ip26hxunoh.execute-api.us-east-1.amazonaws.com/dev/items";
  public static CATEGORY_GET_URL="https://ip26hxunoh.execute-api.us-east-1.amazonaws.com/dev/categories";
  public static ORDERS_GET_URL="https://ip26hxunoh.execute-api.us-east-1.amazonaws.com/dev/orders";
  public static IMAGE_UPLOAD_URL="https://ip26hxunoh.execute-api.us-east-1.amazonaws.com/dev/images";
}
