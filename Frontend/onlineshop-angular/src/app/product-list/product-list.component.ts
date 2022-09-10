import { Component, OnInit } from '@angular/core';
import { Product } from '../class/product'
import { ProductService } from '../services/product.service'
import { Router } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';
import { Category } from '../class/category'

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
	
  private roles: string[] = [];
  isLoggedIn = false;
  showAdmin = false;
  showUser = false;

  name='';
  brand='';
  category ='';
  price='';

  default='';

  products?: Product[]; 
  categories?: Category[];

  constructor(private productService: ProductService, private tokenStorageService: TokenStorageService, private router: Router) { }

  ngOnInit(): void {
	 this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdmin = this.roles.includes('ROLE_ADMIN');
      this.showUser = this.roles.includes('ROLE_USER');

    }

	this.getCategories();
    this.getProducts();
  }

  private getProducts(){
    this.productService.getProductsList().subscribe(data => {
      this.products = data;
    });
  }

	private getCategories(){
    this.productService.getCategories().subscribe(data => {
      this.categories = data;
    });
  }

  addProduct(){
	this.router.navigate(['create-product']);
	}

  updateProduct(id: any){
    this.router.navigate(['update-product', id]);
  }

  deleteProduct(id: any){
    this.productService.deleteProduct(id).subscribe( data => {
      console.log(data);
      this.getProducts();
    })
  }

	searchProducts(): void {
    this.productService.findProducts(this.name,this.brand,this.category,this.price).subscribe(data => {
      this.products = data;
    });
  }




}
