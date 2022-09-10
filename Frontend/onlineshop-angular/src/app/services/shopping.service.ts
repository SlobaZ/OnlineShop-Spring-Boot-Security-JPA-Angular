import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Shopping } from '../class/shopping';
import { User } from '../class/user';

@Injectable({
  providedIn: 'root'
})
export class ShoppingService {

  private baseURL = "http://localhost:8080/shoppings";
  private userURL = "http://localhost:8080/users";

  constructor(private httpClient: HttpClient) { }
  
  getShoppingsList(): Observable<Shopping[]>{
    return this.httpClient.get<Shopping[]>(`${this.baseURL}`);
  }

  createShopping(shopping: Shopping): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/createshopping`, shopping);
  }

  getShoppingById(id: number): Observable<Shopping>{
    return this.httpClient.get<Shopping>(`${this.baseURL}/${id}`);
  }

  updateShopping(id: number, shopping: Shopping): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, shopping);
  }

  deleteShopping(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }

  buy(id: number): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/${id}/buy`, id);
  }

  searchShoppings(userId:any,code:any,totalPrice:any,dateTimeBeginning:any,dateTimeEnd:any): Observable<Shopping[]>{
     return this.httpClient.get<Shopping[]>(`${this.baseURL}?userId=${userId}&code=${code}&totalPrice=${totalPrice}&dateTimeBeginning=${dateTimeBeginning}&dateTimeEnd=${dateTimeEnd}`);
  }

  getUsers(): Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.userURL}`);
  }

}
