import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from "@angular/router";
import {LoginStorageService} from "../services/login-storage.service";
import {ActivatedRoute} from '@angular/router';


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
    ) {
    }

    ngOnInit(): void {
        const patientId = this.route.snapshot.queryParamMap.get('id');
        const getPatientUrl: string = `http://localhost:8080/patient/${patientId}`;

        if (!this.isNewPatientPage()) {
            fetch(getPatientUrl, this.loginStorageService.getFetchConfig('GET'))
                .then((response) => {
                    if (!response.ok) {
                        throw new Error('error');
                    }
                    return response.json();
                })
                .then(data => {
                    // Assign the response (JSON data) to the patients variable
                    this.firstName = data.firstName;
                    this.lastName = data.lastName;
                })
        }
    }

    isNewPatientPage(): boolean {
        const patientId = this.route.snapshot.queryParamMap.get('id');
        return patientId == null;
    }

    save(): void {
        const patientId = this.route.snapshot.queryParamMap.get('id');

        //const hashedPassword = this.password;//this.md5Service.generateMd5Hash(this.password);
        const newPatientUrl = `http://localhost:8080/patient/add?firstName=${this.firstName}&lastName=${this.lastName}`;
        const updatePatientUrl = `http://localhost:8080/patient/${patientId}?firstName=${this.firstName}&lastName=${this.lastName}`;

        if (this.isNewPatientPage()) {
            fetch(newPatientUrl, this.loginStorageService.getFetchConfig('POST'))
                .then((response) => {
                    if (!response.ok) {
                        throw new Error('New patient cannot be created');
                    }
                    alert("Patient created successfully!")
                    this.router.navigate(['/']);
                })
        } else {
            fetch(updatePatientUrl, this.loginStorageService.getFetchConfig('PUT'))
                .then((response) => {
                    if (!response.ok) {
                        throw new Error('New patient cannot be created');
                    }
                    alert("Patient updated successfully!")
                    this.router.navigate(['/']);
                })
        }
    }

    cancel(): void {
        this.router.navigate(['/'])
    }
}
