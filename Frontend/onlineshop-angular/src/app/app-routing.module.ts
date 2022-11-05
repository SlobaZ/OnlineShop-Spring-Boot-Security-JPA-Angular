import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

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
import { SelectedShoppingComponent } from './shopping-selected/selected-shopping.component';
import { CreateShoppingComponent } from './shopping-create/create-shopping.component';
import { ItemListComponent } from './item-list/item-list.component';
import { ResultComponent } from './result/result.component';


const routes: Routes = [
  { path: '', redirectTo: 'products', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'products', component: ProductListComponent },
  { path: 'create-product', component: CreateProductComponent},
  { path: 'update-product/:id', component: UpdateProductComponent},
  { path: 'users', component: UserListComponent},
  { path: 'update-user/:id', component: UpdateUserComponent},
  { path: 'shoppings', component: ShoppingListComponent},
  { path: 'update-shopping/:id', component: UpdateShoppingComponent},
  { path: 'create-shopping', component: CreateShoppingComponent},
  { path: 'selected-shopping/:id', component: SelectedShoppingComponent},
  { path: 'buy/:id', component: ItemListComponent},
  { path: 'result/:id', component: ResultComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
