import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Product } from '../class/product';
import { Category } from '../class/category';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseURL = "http://localhost:8080/api/products";

  constructor(private httpClient: HttpClient) { }


  getProductsList(pageNum:number): Observable<Product[]>{
    return this.httpClient.get<Product[]>(`${this.baseURL}?pageNum=${pageNum}`);
  }

  createProduct(product: Product): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, product);
  }

  getProductById(id: number): Observable<Product>{
    return this.httpClient.get<Product>(`${this.baseURL}/${id}`);
  }

  updateProduct(id: number, product: Product): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, product);
  }

  deleteProduct(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }

  searchProducts(name:any,brand:any,category:any,price:any): Observable<Product[]>{
     return this.httpClient.get<Product[]>(`${this.baseURL}?name=${name}&brand=${brand}&category=${category}&price=${price}`);
  }

  getCategories(): Observable<Category[]>{
    return this.httpClient.get<Category[]>(`${this.baseURL}/category`);
  }



}
