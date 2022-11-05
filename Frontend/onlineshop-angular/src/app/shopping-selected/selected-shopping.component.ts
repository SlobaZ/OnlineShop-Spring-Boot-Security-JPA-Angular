import { Component, OnInit } from '@angular/core';
import { Item } from '../class/item';
import { ItemService } from '../services/item.service';
import { Shopping } from '../class/shopping';
import { ShoppingService } from '../services/shopping.service';
import { ActivatedRoute } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-result',
  templateUrl: './selected-shopping.component.html',
  styleUrls: ['./selected-shopping.component.css']
})
export class SelectedShoppingComponent implements OnInit {
	
  private roles: string[] = [];
  isLoggedIn = false;
  showAdmin = false;
  showUser = false;

  shoppingId?:any;
  items?: Item[]; 
  shopping: Shopping = new Shopping();


  constructor(private itemService: ItemService,
    private shoppingService: ShoppingService,
	private tokenStorageService: TokenStorageService,
    private route: ActivatedRoute) { }


  ngOnInit(): void {
	this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdmin = this.roles.includes('ROLE_ADMIN');
      this.showUser = this.roles.includes('ROLE_USER');
    }
	this.getItems();
	this.getShopping();
  }

   getItems(){
	this.shoppingId = this.route.snapshot.params['id'];
    this.itemService.getAllsByShoppingId(this.shoppingId).subscribe(data => {
      this.items = data;
    }, error => console.log(error));
 	}

	getShopping(){
	this.shoppingId = this.route.snapshot.params['id'];
    this.shoppingService.getShoppingById(this.shoppingId).subscribe( data =>{
      this.shopping = data;
    },
    error => console.log(error));
  	}

	

}
