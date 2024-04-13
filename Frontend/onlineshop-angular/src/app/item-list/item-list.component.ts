import { Component, OnInit } from '@angular/core';
import { Item } from '../class/item'
import { ItemService } from '../services/item.service'
import { ShoppingService } from '../services/shopping.service'
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  shoppingId?:any;
  items?: Item[]; 

  constructor(private itemService: ItemService,
    private shoppingService: ShoppingService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.getAll();
  }
  
  async getAll(){
      await this.getItems();
  }

  getItems(){
      return new Promise((resolve, reject) => {
        try {
            this.shoppingId = this.route.snapshot.params['id'];
            this.itemService.getAllsByShoppingId(this.shoppingId).subscribe(data => {
                resolve(this.items = data);
            }); 
        }
        catch (error) {
            reject(console.log(error));
        }
    });
 }

  deleteItem(id: number){
      try {
            this.itemService.deleteItem(this.shoppingId,id).subscribe( data => {
                  this.getItems();
            });
      }
      catch (error) {
            console.log(error);
      }
  }


  goToResult(id: number){
      this.router.navigate(['result',id]);
  }

  buyItem(id: number, itemQuantity: number ){
        try {
              this.itemService.buyItem(this.shoppingId,id,itemQuantity).subscribe( data =>{
                    this.getItems();
              });
        }
        catch (error) {
              console.log(error);
        }
  }

  resetItem(id: number){
      try {
            this.itemService.resetItem(this.shoppingId,id).subscribe( data =>{ 
              this.getItems();
            });
      }
      catch (error) {
            console.log(error);
      }
  }
  
  buy(){
      try {
            this.shoppingService.buy(this.shoppingId).subscribe( data =>{
              this.goToResult(this.shoppingId);
            });
      }
      catch (error) {
            console.log(error);
      }
  }



  
}
