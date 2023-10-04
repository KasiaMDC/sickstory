import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

//import { products } from '../products';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent {
  patients: any[] = []; // Initialize array

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchPatients();
  }

  // Make the API call to fetch patients
  fetchPatients(): void {
      this.patients = [
          {
              id: 1,
              name: 'John Doe',
              price: 100,
              description: 'Regular check-up'
          },
          {
              id: 2,
              name: 'Alice Smith',
              price: 150,
              description: 'Dental cleaning'
          },
          {
              id: 3,
              name: 'Michael Johnson',
              price: 200,
              description: 'Orthopedic consultation'
          },
          {
              id: 4,
              name: 'Emily Davis',
              price: 120,
              description: 'Eye examination'
          }
      ];
    /*this.http.get<any[]>('http://localhost:9002/rest/v1/patients')
      .subscribe(
        {
          next: (data) => {
            this.patients = data;
          },
          error: (error) => {
            console.error('Error fetching products:', error);
          }
        });*/
  }
}
