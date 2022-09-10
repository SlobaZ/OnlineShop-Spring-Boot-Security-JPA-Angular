import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';

import { ProductListComponent } from './product-list/product-list.component';
import { CreateProductComponent } from './product-create/create-product.component';
import { UpdateProductComponent } from './product-update/update-product.component';
import { UserListComponent } from './user-list/user-list.component';
import { UpdateUserComponent } from './user-update/update-user.component';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';
import { UpdateShoppingComponent } from './shopping-update/update-shopping.component';
import { CreateShoppingComponent } from './shopping-create/create-shopping.component';
import { ItemListComponent } from './item-list/item-list.component';
import { ResultComponent } from './result/result.component';

import { authInterceptorProviders } from './helpers/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
	ProfileComponent,
    ProductListComponent,
	CreateProductComponent,
    UpdateProductComponent,
	UserListComponent,
	UpdateUserComponent,
	ShoppingListComponent,
	UpdateShoppingComponent,
	CreateShoppingComponent,
	ItemListComponent,
	ResultComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
