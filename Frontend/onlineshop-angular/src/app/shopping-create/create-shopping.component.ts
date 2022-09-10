import { Component, OnInit } from '@angular/core';
import { Shopping } from '../class/shopping';
import { ShoppingService } from '../services/shopping.service';
import { Router } from '@angular/router'; 
import { TokenStorageService } from '../services/token-storage.service';


@Component({
  selector: 'app-create-shopping',
  templateUrl: './create-shopping.component.html',
  styleUrls: ['./create-shopping.component.css'] 
})
export class CreateShoppingComponent implements OnInit { 
	
  private roles: string[] = [];
  isLoggedIn = false;
  showAdmin = false;
  showUser = false;
  username?: string;

  shopping: Shopping = new Shopping();
  shoppingId?: any;
  userId?:any;
	
  constructor(private shoppingService: ShoppingService,  private tokenStorageService: TokenStorageService,  private router: Router) { }

  ngOnInit(): void {
	
	this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdmin = this.roles.includes('ROLE_ADMIN');
      this.showUser = this.roles.includes('ROLE_USER');

      this.username = user.username;
	  this.userId = user.id;
    }

	if(!this.isLoggedIn){
		this.router.navigate(['login']);
	}


  }


  saveShopping(){
    this.shoppingService.createShopping(this.shopping).subscribe( data =>{
      console.log(data);
      this.shopping = data;
      this.goToItemList(this.shopping.id);
    },
    error => console.log(error));
  }

  goToItemList(id: number){
    this.router.navigate(['buy', id]);
  }
  
  onSubmit(){
    console.log(this.shopping);
    this.saveShopping();
  }
}
