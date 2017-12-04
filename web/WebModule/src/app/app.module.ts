import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { DataTablesModule } from 'angular-datatables';
import { AppComponent } from './app.component';
import { HttpModule } from '@angular/http';
import { Routes, RouterModule } from "@angular/router";
import { routing } from './app.routing';


import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AppConstants } from './constants/AppConstants';
import { ErrorService } from './service/error.service';
import { LoginService } from './service/login.service';
import { PathUtil } from './util/PathUtil';
import { HeaderAdminComponent} from './component/header-admin/header-admin.component';
import { Header1Component } from './component/header1/header1.component';
import { LoginComponent } from './component/login/login.component';
import { ForgotpasswordComponent } from './component/login/forgotpassword.component';
import { RegisterationComponent } from './component/login/registeration.component';
import { HeaderComponent } from './component/header/header.component';
import { MenuComponent } from './component/menu/menu.component';
import { DisplayMenuComponent } from './component/menu/displaymenu.component';
import { DeleteMenuComponent } from './component/menu/deletemenu.component';
import { UpdateMenuComponent } from './component/menu/updatemenu.component';
import { CategoryComponent } from './component/category/category.component';
import { CreateCategoryComponent } from './component/category/createcategory.component';
import { UpdateCategoryComponent } from './component/category/updatecategory.component';
import { DeleteCategoryComponent } from './component/category/deletecategory.component';
import { ItemComponent } from './component/item/item.component';
import { CreateItemComponent } from './component/item/createitem.component';
import { DeleteItemComponent } from './component/item/deleteitem.component';
import { UpdateItemComponent } from './component/item/updateitem.component';
import { OrderComponent } from './component/order/order.component';
import { Respond2orderComponent } from './component/order/respond2order.component';
import { CreateMenuComponent } from './component/menu/createmenu.component';
import { NavigationComponent } from './component/navigation/navigation.component';
import { ImageComponent } from './component/image/image.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderAdminComponent,
    LoginComponent,
    ForgotpasswordComponent,
    RegisterationComponent,
    Header1Component,
    HeaderComponent,
    MenuComponent,
    DisplayMenuComponent,
    CreateMenuComponent,
    DeleteMenuComponent,
    UpdateMenuComponent,
    CategoryComponent,
    CreateCategoryComponent,
    UpdateCategoryComponent,
    DeleteCategoryComponent,
    OrderComponent,
    Respond2orderComponent,
    ItemComponent,
    CreateItemComponent,
    UpdateItemComponent,
    DeleteItemComponent,
    NavigationComponent,
    ImageComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    DataTablesModule,
    ReactiveFormsModule,
    HttpModule,
    routing


  ],
  providers: [AppConstants, ErrorService, PathUtil, LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
