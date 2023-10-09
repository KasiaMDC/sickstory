import { Component, Input, Inject } from '@angular/core';
import { Patient } from "../domain/patient";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginStorageService} from "../services/login-storage.service";


@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent {
  @Input() patient!: Patient;
  expanded: boolean = false;

  constructor(
      private router: Router,
      private loginStorageService: LoginStorageService,
  ) { }

  toggleExpanded(): void {
    this.expanded = !this.expanded;
  }

  goToEditPatient(): void {
    this.router.navigate(['/patient'], { queryParams: { id: this.patient.id } });

  }

  deletePatient(): void {

    const authenticationHeader: string = this.loginStorageService.getValue()!;
    const deletePatientUrl: string = `http://localhost:8080/patient/${this.patient.id}`;

    // Process the first data or trigger the second fetch based on the first data
    const customHeaders = {
      'Authorization': authenticationHeader
    };

    const fetchConfig: RequestInit = {
      method: 'DELETE',
      headers: new Headers(customHeaders),
      //credentials: 'include'
    };

    fetch(deletePatientUrl, fetchConfig)
        .then((response) => {
          if (!response.ok) {
            throw new Error('Could not delete patient');
          }
          alert("Patient deleted successfully!");
          this.router.navigate(['/']);
        })
  }

}
