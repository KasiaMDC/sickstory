import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Router} from "@angular/router";
import {LoginStorageService} from "../services/login-storage.service";
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-edit-patient',
  templateUrl: './edit-patient.component.html',
  styleUrls: ['./edit-patient.component.css']
})
export class EditPatientComponent {
  firstName?: string;
  lastName?: string;

  constructor(
      private http: HttpClient,
      private router: Router,
      private loginStorageService: LoginStorageService,
      //private md5Service: Md5Service
      private route: ActivatedRoute
  ) { }




  save(): void {
    //const hashedPassword = this.password;//this.md5Service.generateMd5Hash(this.password);
    const newPatientUrl = `http://localhost:8080/patient/add?firstName=${this.firstName}&lastName=${this.lastName}`;
    const updatePatientUrl = `http://localhost:8080/testlogin`;

    const paramValue = this.route.snapshot.queryParamMap.get('id');
    if (paramValue == null) {
        const authenticationHeader: string = this.loginStorageService.getValue()!;

        // Process the first data or trigger the second fetch based on the first data
        const customHeaders = {
            'Authorization': authenticationHeader
        };

        const fetchConfig: RequestInit = {
            method: 'POST',
            headers: new Headers(customHeaders),
            //credentials: 'include'
        };


        fetch(newPatientUrl, fetchConfig)
          .then((response) => {
            if (!response.ok) {
              throw new Error('New patient cannot be created');
            }
            alert("User created successfully!")
            this.router.navigate(['/']);
          })
    }
  }
    cancel(): void {}
}
