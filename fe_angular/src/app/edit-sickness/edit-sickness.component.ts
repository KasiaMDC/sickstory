import {Component, Input} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from "@angular/router";
import {LoginStorageService} from "../services/login-storage.service";
import {ActivatedRoute} from '@angular/router';
import {Patient} from "../domain/patient";


@Component({
    selector: 'app-edit-sickness',
    templateUrl: './edit-sickness.component.html',
    styleUrls: ['./edit-sickness.component.css']
})
export class EditSicknessComponent {
    @Input() patient!: Patient;


    uid?: number;
    name?: string;
    startDate?: string;
    endDate?: string;
    symptoms?: string;
    commentsToTheDoctorsAppointment?: string;
    medicine?: string;

    constructor(
        private http: HttpClient,
        private router: Router,
        private loginStorageService: LoginStorageService,
        //private md5Service: Md5Service
        private route: ActivatedRoute
    ) {
    }

    ngOnInit(): void {
        const sicknessUid = this.route.snapshot.queryParamMap.get('id');
        const getPatientUrl: string = `http://localhost:8080/patient/${this.patient.id}/sickness/${sicknessUid}`;

        fetch(getPatientUrl, this.loginStorageService.getFetchConfig('GET'))
            .then((response) => {
                if (!response.ok) {
                    throw new Error('error');
                }
                return response.json();
            })
            .then(data => {
                // Assign the response (JSON data) to the patients variable
                this.name = data.name;
                this.startDate = data.startDate;
                this.endDate = data.endDate;
                this.symptoms = data.symptoms;
                this.commentsToTheDoctorsAppointment = data.commentsToTheDoctorsAppointment;
                this.medicine = data.medicine;

            })
    }

    save(): void {
        const sicknessUid = this.route.snapshot.queryParamMap.get('uid');

        //const hashedPassword = this.password;//this.md5Service.generateMd5Hash(this.password);
        const newPatientUrl = `http://localhost:8080/patient/${this.patient.id}/sickness?name=${this.name}&startDate=${this.startDate}&endDate=${this.endDate}&symptoms=${this.symptoms}&commentsToTheDoctorsAppointment=${this.commentsToTheDoctorsAppointment}&medicine=${this.medicine}`;
        const updatePatientUrl = `http://localhost:8080/patient/${this.patient.id}/sickness/${sicknessUid}?name=${this.name}&startDate=${this.startDate}&endDate=${this.endDate}&symptoms=${this.symptoms}&commentsToTheDoctorsAppointment=${this.commentsToTheDoctorsAppointment}&medicine=${this.medicine}`;

        if (sicknessUid == null) {
            fetch(newPatientUrl, this.loginStorageService.getFetchConfig('POST'))
                .then((response) => {
                    if (!response.ok) {
                        throw new Error('New sickness cannot be created');
                    }
                    alert("Sickness created successfully!")
                    this.router.navigate(['/']);
                })
        } else {
            fetch(updatePatientUrl, this.loginStorageService.getFetchConfig('PUT'))
                .then((response) => {
                    if (!response.ok) {
                        throw new Error('New sickness cannot be updated');
                    }
                    alert("Sickness updated successfully!")
                    this.router.navigate(['/']);
                })
        }
    }

    cancel(): void {
        this.router.navigate(['/'])
    }
}