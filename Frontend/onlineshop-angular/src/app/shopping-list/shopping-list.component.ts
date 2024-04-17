import { Component, OnInit,  HostListener } from '@angular/core';
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

  shoppings: Array<{	
        id?: any;
        code?: string;
        totalPrice?: any;
        dateTimeT?: any;
        dateTime?: any;
        userId?: any;
        userUsername?: string;
  }> =[];

  users ?: User[];

  userId='';
  code='';
  totalPrice='';
  dateTimeBeginning='';
  dateTimeBeginningValue='';
  dateTimeEnd='';
  dateTimeEndValue='';

  default='';

  pageNum = 0;

  constructor(private shoppingService: ShoppingService, private tokenStorageService: TokenStorageService, private router: Router) { }

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

  async getAll(){
      await this.getUsers();
      await this.getShoppings();
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

  async loadMore() {
        this.pageNum++;
        this.getShoppings();
}


    getRequestParams(userId:any, code:string, totalPrice:any, dateTimeBeginningValue:any, dateTimeEndValue:any, pageNum: number): any {
            let params: any = {};
            if (userId) {
            params[`userId`] = userId;
            }
            if (code) {
                params[`code`] = code;
            }
            if (totalPrice) {
                params[`totalPrice`] = totalPrice;
            }
            if (dateTimeBeginningValue) {
                params[`dateTimeBeginning`] = this.formatDateTime(dateTimeBeginningValue);
            }
            if (dateTimeEndValue) {
                params[`dateTimeEnd`] = this.formatDateTime(dateTimeEndValue);
            }
            if (pageNum) {
            params[`pageNum`] = pageNum ;
            }
        return params;
    }

  private getShoppings(){
        return new Promise((resolve, reject) => {
          try {
            const params = this.getRequestParams(this.userId, this.code, this.totalPrice, this.dateTimeBeginningValue, this.dateTimeEndValue, this.pageNum);
            if(this.pageNum==0){
                resolve(
                    this.shoppingService.getShoppingsList(params).subscribe(data => {
                            this.shoppings = data;
                    })
                );
            }
            else {
                resolve(
                    this.shoppingService.getShoppingsList(params).subscribe(data => {
                            data.map((item:any) =>  {
                                this.shoppings?.push(item);
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


  private getUsers(){
        return new Promise((resolve, reject) => {
          try {
              this.shoppingService.getUsers().subscribe(data => {
                  resolve(this.users = data);
              }); 
          }
          catch (error) {
              reject(console.log(error));
          }
      });
  }


  addShopping(){
	      this.router.navigate(['create-shopping']);
	}

  updateShopping(id: number){
        this.router.navigate(['update-shopping', id]);
  }

  deleteShopping(id: number){
        try {
            this.shoppingService.deleteShopping(id).subscribe( data => {
                this.getShoppings();
            });
        }
        catch (error) {
              console.log(error);
        }
  }

  formatDateTime(value:any)  {
      const enteredValue = value;
      const day = enteredValue.substring(8, 10);
      const month = enteredValue.substring(5, 7);
      const year = enteredValue.substring(0, 4);
      const time = enteredValue.substring(11, 16);
      const formatedValue = day.concat(".", month, ".", year, ". ", time);
      return formatedValue;
  }

  searchShoppings() : void  {
        this.pageNum = 0;
        this.getShoppings();
  }


  selectShopping(id: number){
        this.router.navigate(['result',id]);
  }
  


}
