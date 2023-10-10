import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {LoginStorageService} from "../services/login-storage.service";
import {Router} from "@angular/router";

//import { products } from '../products';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent {
  patients: any[] = []; // Initialize array

  constructor(private http: HttpClient,
              private loginStorageService: LoginStorageService,
              private router: Router,) {}

  ngOnInit(): void {
    this.fetchPatients();
  }

  // Make the API call to fetch patients
  fetchPatients(): void {
      const authenticationHeader: string = this.loginStorageService.getValue()!;
      const listPatientsUrl: string = 'http://localhost:8080/patient/list';

      // Process the first data or trigger the second fetch based on the first data
      const customHeaders = {
          'Authorization': authenticationHeader
      };

      const fetchConfig: RequestInit = {
          method: 'GET',
          headers: new Headers(customHeaders),
          //credentials: 'include'
      };


      fetch(listPatientsUrl, fetchConfig)
          .then((response) => {
              if (!response.ok) {
                  throw new Error('New patient cannot be created');
              }
              return response.json();
          })
          .then(data => {
              // Assign the response (JSON data) to the patients variable
              this.patients = data;
          })
          .catch(error => {
              console.error('Error fetching patients:', error);
          });
  }

    addPatient(): void {
        this.router.navigate(['/patient'])
    }
}
