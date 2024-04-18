import { Component, OnInit, HostListener } from '@angular/core';
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

  name = '';
  brand = '';
  category = '';
  price = '';

  default = '';

  products: Array<{	
    id?: any;
    name?: string;
	  brand?: string;
    quantity?: number;
    price?: number;
    category?:any;
  }> =[];

  categories? : Category[] = [];

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

  
  @HostListener('window:scroll', ['$event']) 
  onScroll(event:Event) {   
        if(document.documentElement.clientHeight + window.scrollY >=
            (document.documentElement.scrollHeight || document.documentElement.clientHeight)){
                setTimeout(() => {
                    console.log('You are at the bottom!');
                    this.loadMore();
                }, 1000);
          }   
  }  


  loadMore() {
      this.pageNum++;
      this.getProducts();
  }

  getRequestParams(name:string, brand:string, category:any, price:any, pageNum: number): any {
        let params: any = {};
        if (name) {
          params[`name`] = name;
        }
        if (brand) {
            params[`brand`] = brand;
        }
        if (category) {
            params[`category`] = category;
        }
        if (price) {
            params[`price`] = this.price;
        }
        if (pageNum) {
          params[`pageNum`] = pageNum ;
        }
    return params;
  }

  private getProducts(){
        return new Promise((resolve, reject) => {
            try {
              const params = this.getRequestParams(this.name, this.brand, this.category, this.price, this.pageNum);
              if(this.pageNum==0){
                  resolve(
                      this.productService.getProductsList(params).subscribe(data => {
                              this.products = data;
                      })
                  );
              }
              else {
                  resolve(
                      this.productService.getProductsList(params).subscribe(data => {
                              data.map((item:any) =>  {
                                  this.products?.push(item);
                              })
                      })
                  );
              }
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
              this.pageNum = 0;
              this.getProducts();
            });
      }
      catch (error) {
            console.log(error);
      }
  }

	searchProducts(): void {
      this.pageNum = 0;
      this.getProducts();
  }







}
