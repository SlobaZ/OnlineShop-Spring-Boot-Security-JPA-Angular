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

  products ?: Product[] = []; 
  categories? : Category[] = [];

  page = 1;
  pageNum = 0;

  constructor(private productService: ProductService, private tokenStorageService: TokenStorageService, private router: Router) { }

  ngOnInit(): void {

      this.isLoggedIn = !!this.tokenStorageService.getToken();

      if (this.isLoggedIn) {
        const user = this.tokenStorageService.getUser();
        this.roles = user.roles;

        this.showAdmin = this.roles.includes('ROLE_ADMIN');
        this.showUser = this.roles.includes('ROLE_USER');

      }

      this.getAll();

  }

  async getAll() {
      await this.getCategories();
      await this.getProducts();
  }

  private getProducts(){
    return new Promise((resolve, reject) => {
        try {
            this.productService.getProductsList(this.pageNum).subscribe(data => {
                resolve(this.products = data);
            }); 
        }
        catch (error) {
            reject(console.log(error));
        }
    });
  }

	private getCategories(){
    return new Promise((resolve, reject) => {
        try {
            this.productService.getCategories().subscribe(data => {
              resolve(this.categories = data);
            }); 
        }
        catch (error) {
              reject(console.log(error));
        }
    });
  }

  addProduct(){
	    this.router.navigate(['add-or-update-product', "add"]);
	}

  updateProduct(id: any){
      this.router.navigate(['add-or-update-product', id]);
  }

  deleteProduct(id: any){
      try {
            this.productService.deleteProduct(id).subscribe( data => {
              this.getProducts();
            });
      }
      catch (error) {
            console.log(error);
      }
  }

	searchProducts(): void {
        try {
            this.productService.findProducts(this.name,this.brand,this.category,this.price).subscribe(data => {
              this.products = data;
            });
        }
        catch (error) {
              console.log(error);
        }
  }




  plusPage() {
    this.page++;
    this.pageNum++;
    this.getProducts();
  }

  minusPage() {
    if(this.pageNum>0){
      this.page--;
      this.pageNum--;
      this.getProducts();
    }
  }



}
