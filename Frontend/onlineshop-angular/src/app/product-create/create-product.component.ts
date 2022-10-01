import { Component, OnInit } from '@angular/core';
import { Product } from '../class/product';
import { ProductService } from '../services/product.service';
import { Router } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {
	
  private roles: string[] = [];
  isLoggedIn = false;
  showAdmin = false;
  showUser = false;
  username?: string;

  product: Product = new Product();
  constructor(private productService: ProductService, private tokenStorageService: TokenStorageService, private router: Router) { }

  ngOnInit(): void {
	 this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdmin = this.roles.includes('ROLE_ADMIN');
      this.showUser = this.roles.includes('ROLE_USER');

      this.username = user.username;
    }
  }

  saveProduct(){
    this.productService.createProduct(this.product).subscribe( data =>{
      console.log(data);
      this.goToProductList();
    },
    error => console.log(error));
  }

  goToProductList(){
    this.router.navigate(['/products']);
  }
  
  onSubmit(){
    console.log(this.product);
    this.saveProduct();
  }


  cancel(){
	this.goToProductList();
  }


}
