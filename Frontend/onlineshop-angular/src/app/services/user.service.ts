import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { User } from '../class/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseURL = "http://localhost:8080/api/users";

  constructor(private httpClient: HttpClient) { }
  
  getUsersList(pageNum:number): Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.baseURL}?pageNum=${pageNum}`);
  }

  createUser(user: User): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, user);
  }

  getUserById(id: number): Observable<User>{
    return this.httpClient.get<User>(`${this.baseURL}/${id}`);
  }

  updateUser(id: number, user: User): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, user);
  }

  deleteUser(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }


  findUsers(username:any,email:any,pageNum:number): Observable<User[]>{
     return this.httpClient.get<User[]>(`${this.baseURL}?username=${username}&email=${email}&pageNum=${pageNum}`);
  }


}

