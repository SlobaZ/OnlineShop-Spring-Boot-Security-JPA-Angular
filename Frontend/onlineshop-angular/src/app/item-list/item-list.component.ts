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
    this.getItems();
  }
  
  getItems(){
	this.shoppingId = this.route.snapshot.params['id'];
    this.itemService.getAllsByShoppingId(this.shoppingId).subscribe(data => {
      this.items = data;
    }, error => console.log(error));
 }

  deleteItem(id: number){
    this.itemService.deleteItem(this.shoppingId,id).subscribe( data => {
      console.log(data);
      this.getItems();
    })
  }


  goToResult(id: number){
    this.router.navigate(['result',id]);
  }

  buyItem(id: number, itemQuantity: number ){
    this.itemService.buyItem(this.shoppingId,id,itemQuantity).subscribe( data =>{
      console.log(data);
      this.getItems();
    },
    error => console.log(error));
  }

  resetItem(id: number){
    this.itemService.resetItem(this.shoppingId,id).subscribe( data =>{
      console.log(data);
      this.getItems();
    },
    error => console.log(error));
  }
  
  buy(){
    this.shoppingService.buy(this.shoppingId).subscribe( data =>{
      console.log(data);
      this.goToResult(this.shoppingId);
    },
    error => console.log(error));
  }



  
}
