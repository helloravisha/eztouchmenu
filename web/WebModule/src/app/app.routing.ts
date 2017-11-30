/**
 * Created by ravisha on 3/25/17.
 */
/**
 * Created by ravisha on 3/11/17.
 */
import {ModuleWithProviders} from '@angular/core';
import {Routes,RouterModule} from '@angular/router';

import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { ForgotpasswordComponent } from './component/login/forgotpassword.component';
import { RegisterationComponent } from './component/login/registeration.component';
import { MenuComponent } from './component/menu/menu.component';
import { DisplayMenuComponent } from './component/menu/displaymenu.component';
import { DeleteMenuComponent } from './component/menu/deletemenu.component';
import { UpdateMenuComponent } from './component/menu/updatemenu.component';
import { CategoryComponent } from  './component/category/category.component';
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
import { ImageComponent } from './component/image/image.component';

const appRoutes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'registration', component: RegisterationComponent},
  { path: 'forgotPassword', component: ForgotpasswordComponent},
  { path: 'menu', component: MenuComponent},
  { path: 'displayMenu', component: DisplayMenuComponent},
  { path: 'createMenu', component: CreateMenuComponent},
  { path: 'deleteMenu', component: DeleteMenuComponent},
  { path: 'updateMenu', component: UpdateMenuComponent},
  { path: 'categories', component: CategoryComponent},
  { path: 'createCategory', component: CreateCategoryComponent},
  { path: 'updateCategory', component: UpdateCategoryComponent},
  { path: 'deleteCategory', component: DeleteCategoryComponent},
  { path: 'items', component: ItemComponent},
  { path: 'createItem', component: CreateItemComponent},
  { path: 'updateItem', component: UpdateItemComponent},
  { path: 'deleteItem', component: DeleteItemComponent},
  { path: 'orders', component: OrderComponent},
  { path: 'orderRespond', component: Respond2orderComponent},
  { path: 'imageUpload', component: ImageComponent}
];


export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);


