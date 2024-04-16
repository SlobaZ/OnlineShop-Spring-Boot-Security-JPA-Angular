import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Item } from '../class/item';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

//  private baseURL = "http://localhost:8080/api/shoppings/{shoppingId}/items";   
  private shoppingURL = "http://localhost:8080/api/shoppings";       

  constructor(private httpClient: HttpClient) { }
  
  getAllsByShoppingId(shoppingId: number): Observable<Item[]>{
    return this.httpClient.get<Item[]>(`${this.shoppingURL}/${shoppingId}/items`);
  }

  createItem(shoppingId: number,item: Item): Observable<Object>{
    return this.httpClient.post(`${this.shoppingURL}/${shoppingId}/items`, item);
  }

  getItemById(shoppingId: number,id: number): Observable<Item>{
    return this.httpClient.get<Item>(`${this.shoppingURL}/${shoppingId}/items/${id}`);
  }

  deleteItem(shoppingId: number,id: number): Observable<Object>{
    return this.httpClient.delete(`${this.shoppingURL}/${shoppingId}/items/${id}`);
  }

  buyItem(shoppingId: number,id: number, itemQuantity: number): Observable<Object>{
    return this.httpClient.post(`${this.shoppingURL}/${shoppingId}/items/${id}/${itemQuantity}/buyItem`, itemQuantity);
  }

  resetItem(shoppingId: number,id: number): Observable<Object>{
    return this.httpClient.post(`${this.shoppingURL}/${shoppingId}/items/${id}/resetItem`, id);
  }




}
