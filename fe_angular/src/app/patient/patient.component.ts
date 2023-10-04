import { Component, Input, Inject } from '@angular/core';
import { Patient } from "../domain/patient";

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent {
  @Input() patient!: Patient;
  expanded: boolean = false;

  toggleExpanded(): void {
    this.expanded = !this.expanded;
  }
}
