import { Component, OnInit } from '@angular/core';
import { User } from '../class/user';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
	
  private roles: string[] = [];
  isLoggedIn = false;
  showAdmin = false;
  showUser = false;

  users?: User[]; 
	username='';
	email='';

  constructor(private userService: UserService, private tokenStorageService: TokenStorageService, private router: Router) { }

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
  }

  private getUsers(){
        return new Promise((resolve, reject) => {
              try {
                  this.userService.getUsersList().subscribe(data => {
                      resolve(this.users = data);
                  }); 
              }
              catch (error) {
                  reject(console.log(error));
              }
        });
  }

  addUser(){
	    this.router.navigate(['register']);
	}

  updateUser(id: number){
        this.router.navigate(['update-user', id]);
  }

  deleteUser(id: number){
        try {
              this.userService.deleteUser(id).subscribe( data => {
                    this.getUsers();
              });
        }
        catch (error) {
              console.log(error);
        }
  }



 	searchUsers(): void {
        try {
              this.userService.findUsers(this.username,this.email).subscribe(data => {
                this.users = data;
              });
        }
        catch (error) {
              console.log(error);
        }
  }










}
