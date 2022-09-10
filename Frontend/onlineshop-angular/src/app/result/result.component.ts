import { Component, OnInit } from '@angular/core';
import { Item } from '../class/item';
import { ItemService } from '../services/item.service';
import { Shopping } from '../class/shopping';
import { ShoppingService } from '../services/shopping.service';
import { User } from '../class/user';
import { ActivatedRoute } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {
	
  shoppingId?:any;
  items?: Item[]; 
  shopping: Shopping = new Shopping();
  user: User = new User();

  constructor(private itemService: ItemService,
    private shoppingService: ShoppingService,
	private tokenStorageService: TokenStorageService,
    private route: ActivatedRoute) { }


  ngOnInit(): void {
	this.user = this.tokenStorageService.getUser();
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
