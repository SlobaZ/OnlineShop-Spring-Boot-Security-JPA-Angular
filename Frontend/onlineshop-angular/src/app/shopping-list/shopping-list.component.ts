import { Component, OnInit } from '@angular/core';
import { Shopping } from '../class/shopping';
import { User } from '../class/user';
import { ShoppingService } from '../services/shopping.service';
import { Router } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit {
	
  private roles: string[] = [];
  isLoggedIn = false;
  showAdmin = false;
  showUser = false;

  shoppings?: Shopping[]; 
  users?: User[];
  userId='';
  code='';
  totalPrice='';
  dateTimeBeginning='';
  dateTimeEnd='';

  default='';

  constructor(private shoppingService: ShoppingService, private tokenStorageService: TokenStorageService, private router: Router) { }

  ngOnInit(): void {
	this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdmin = this.roles.includes('ROLE_ADMIN');
      this.showUser = this.roles.includes('ROLE_USER');

    }
	this.getUsers();
    this.getShoppings();
  }

  private getShoppings(){
    this.shoppingService.getShoppingsList().subscribe(data => {
      this.shoppings = data;
    });
  }

  private getUsers(){
    this.shoppingService.getUsers().subscribe(data => {
      this.users = data;
    });
  }

  addShopping(){
	this.router.navigate(['create-shopping']);
	}

  updateShopping(id: number){
    this.router.navigate(['update-shopping', id]);
  }

  deleteShopping(id: number){
    this.shoppingService.deleteShopping(id).subscribe( data => {
      console.log(data);
      this.getShoppings();
    })
  }

 searchShoppings(): void {
    this.shoppingService.searchShoppings(this.userId,this.code,this.totalPrice,this.dateTimeBeginning,this.dateTimeEnd).subscribe(data => {
      this.shoppings = data;
    });
  }

 selectShopping(id: number){
    this.router.navigate(['selected-shopping',id]);
  }
  


}
