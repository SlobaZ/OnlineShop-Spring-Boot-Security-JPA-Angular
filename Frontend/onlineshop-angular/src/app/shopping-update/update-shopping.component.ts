import { Component, OnInit } from '@angular/core';
import { ShoppingService } from '../services/shopping.service';
import { Shopping } from '../class/shopping';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-update-shopping',
  templateUrl: './update-shopping.component.html',
  styleUrls: ['./update-shopping.component.css']
})
export class UpdateShoppingComponent implements OnInit {
	
	private roles: string[] = [];
  isLoggedIn = false;
  showAdmin = false;
  showUser = false;
  username?: string;
 
  id?: any;
  shopping: Shopping = new Shopping();
  
  constructor(private shoppingService: ShoppingService, private tokenStorageService: TokenStorageService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
	
        this.isLoggedIn = !!this.tokenStorageService.getToken();

        if (this.isLoggedIn) {
          const user = this.tokenStorageService.getUser();
          this.roles = user.roles;

          this.showAdmin = this.roles.includes('ROLE_ADMIN');
          this.showUser = this.roles.includes('ROLE_USER');

          this.username = user.username;
        }

        
        try {
              this.id = this.route.snapshot.params['id'];

              this.shoppingService.getShoppingById(this.id).subscribe(data => {
                this.shopping = data;
              });
        }
        catch (error) {
              console.log(error);
        }
  }
 
  onSubmit(){
      try {
            this.shoppingService.updateShopping(this.id, this.shopping).subscribe( data =>{
              this.goToShoppingList();
            });
      }
      catch (error) {
            console.log(error);
      }
  }

  goToShoppingList(){
      this.router.navigate(['/shoppings']);
  }


  cancel(){
	    this.goToShoppingList();
  }
}
