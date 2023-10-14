import {Component, Input, Inject} from '@angular/core';
import {Patient} from "../domain/patient";
import {Sickness} from "../domain/sickness";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginStorageService} from "../services/login-storage.service";
import { FormsModule } from '@angular/forms';


@Component({
    selector: 'app-patient',
    templateUrl: './patient.component.html',
    styleUrls: ['./patient.component.css']
})
export class PatientComponent {
    @Input() patient!: Patient;
    @Input() sickness!: Sickness;
    expanded: boolean = false;
    sicknesses: Sickness[] = []; // Initialize array

    constructor(
        private router: Router,
        private loginStorageService: LoginStorageService,
    ) {
    }

    toggleExpanded(): void {
        this.expanded = !this.expanded;
    }

    getSicknessList(): void {
        const listSicknessUrl: string = `http://localhost:8080/patient/${this.patient.id}/sickness/list`;

        fetch(listSicknessUrl, this.loginStorageService.getFetchConfig('GET'))
            .then((response) => {
                if (!response.ok) {
                    throw new Error('List cound not be created');
                }
                return response.json();
            })
            .then(data => {
                this.sicknesses = data;
                // Assign the response (JSON data) to the patients variable

            })
            .catch(error => {
                console.error('Error fetching sicknesses:', error);
            });
    }


    action(): void {
        this.toggleExpanded();
        this.getSicknessList();
    }

    goToEditPatient(): void {
        this.router.navigate(['/patient'], {queryParams: {id: this.patient.id}});

    }

    deletePatient(): void {
        const deletePatientUrl: string = `http://localhost:8080/patient/${this.patient.id}`;

        fetch(deletePatientUrl, this.loginStorageService.getFetchConfig('DELETE'))
            .then((response) => {
                if (!response.ok) {
                    throw new Error('Could not delete patient');
                }
                alert("Patient deleted successfully!");
                this.router.navigate(['/']);
            })
    }
    goToEditSickness(sickness: Sickness): void {
        this.router.navigate(['/sickness'], {queryParams: {id: sickness.uid}});
    }
    deleteSickness(sickness: Sickness): void {
        const deleteSicknessUrl: string = `http://localhost:8080/patient/${this.patient.id}/sickness/${sickness.uid}`;

        fetch(deleteSicknessUrl, this.loginStorageService.getFetchConfig('DELETE'))
            .then((response) => {
                if (!response.ok) {
                    throw new Error('Could not delete sickness');
                }
                alert("Sickness deleted successfully!");
                this.router.navigate(['/']);
            })}
    addSickness():void{
        this.router.navigate(['/sickness'])
    }

}
