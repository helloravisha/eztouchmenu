import { Injectable } from '@angular/core';
import {LoginComponentInterface} from "../component/login/LoginComponentInterface";


@Injectable()
export class LoginService {



  constructor() {

  }

  public  signUp(loginUserEmail:string,password:string,loginComponentInterface:LoginComponentInterface):void{

  }

  public  login(loginUserEmail:string,password:string,loginComponentInterface:LoginComponentInterface):void{
            loginComponentInterface.successMessageCallBack("Welcome "+loginUserEmail);
  }

  public resetPassword(loginUserEmail:string,loginComponentInterface:LoginComponentInterface){

  }


}
