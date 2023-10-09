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
  ) { }

  toggleExpanded(): void {
    this.expanded = !this.expanded;
  }

  goToEditPatient(): void {
    this.router.navigate(['/patient'], { queryParams: { id: this.patient.id } });

  }

}
