import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Router} from "@angular/router";
import {LoginStorageService} from "../services/login-storage.service";
//const { Md5 } = require('ts-md5/dist/md5');

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  username?: string;
  password?: string;

  constructor(
      private http: HttpClient,
      private router: Router,
      private loginStorageService: LoginStorageService
      //private md5Service: Md5Service
  ) { }

  onSubmit(): void {
    //const hashedPassword = this.password;//this.md5Service.generateMd5Hash(this.password);
    const authUrl = `http://localhost:8080/auth?username=${this.username}&password=${this.password}`;
    const testloginUrl = `http://localhost:8080/testlogin`;

    fetch(authUrl)
        .then((response) => {
          if (!response.ok) {
            throw new Error('Error obtaining authentication header');
          }
          return response.text();
        })
        .then((authenticationHeader) => {
            this.loginStorageService.setValue(authenticationHeader);

            // Process the first data or trigger the second fetch based on the first data
            const customHeaders = {
                'Authorization': authenticationHeader
            };

            const fetchConfig: RequestInit = {
                method: 'GET',
                headers: new Headers(customHeaders),
                //credentials: 'include'
            };

            return fetch(testloginUrl, fetchConfig);
        })
        .then((secondResponse) => {
          if (!secondResponse.ok) {
              this.loginStorageService.removeValue();
            alert('Wrong username/password!');
            throw new Error('Wrong username/password');
          }

          // redirect to the main page
          this.router.navigate(['/']);
        })
  }
}
