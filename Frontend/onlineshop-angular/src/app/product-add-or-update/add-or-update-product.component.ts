import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../class/product';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-add-or-update-product',
  templateUrl: './add-or-update-product.component.html',
  styleUrls: ['./add-or-update-product.component.css']
})
export class AddOrUpdateProductComponent implements OnInit {
	
  private roles: string[] = [];
  isLoggedIn = false;
  showAdmin = false;
  showUser = false;
  username?: string;
 
  id?: any;
  product : Product = new Product();
  title ?: string;

  errorMessage = '';
  unsuccessful = false;

  constructor(private productService: ProductService, private tokenStorageService: TokenStorageService,
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

        this.id = this.route.snapshot.params['id'];

        if(this.id === "add"){
            return;
        }
        else {
              try {
                    this.productService.getProductById(this.id).subscribe(data => {
                      this.product = data;
                    });
              }
              catch (error) {
                    console.log(error);
              }
        }
  }
 
  onSubmit(){

      if(this.id === "add"){
          try {
              this.productService.createProduct(this.product).subscribe( data =>{
                this.goToProductList()
              },
              error => {
                this.unsuccessful = true;
              });
          }
          catch (error:any) {
                console.log(error);
                this.unsuccessful = true;
          }
      }
      else {
          try {
            this.productService.updateProduct(this.id, this.product).subscribe( data =>{
              this.goToProductList();
            },
            error => {
              this.unsuccessful = true;
            });
          }
          catch (error:any) {
                console.log(error);
                this.unsuccessful = true;
          }
      }

  }



  getTitle = () => {
    if(this.id === 'add') {
        return   this.title ='Add Product';
    }
    else {
        return   this.title ='Update Product';
    }
}

  goToProductList(){
        this.router.navigate(['/products']);
  }

  cancel(){
	    this.goToProductList();
  }



}
