import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from "@angular/router";
import {LoginStorageService} from "../services/login-storage.service";
import {Sickness} from "../domain/sickness";
import { DatePipe } from '@angular/common';

interface EditSicknessQueryParams {
    patientId?: string | null;
    sicknessUid?: string | null;
}

@Component({
    selector: 'app-edit-sickness',
    templateUrl: './edit-sickness.component.html',
    styleUrls: ['./edit-sickness.component.css']
})
export class EditSicknessComponent {
    name?: string;
    startDate?: Date;
    endDate?: Date;
    symptoms?: string;
    commentsToTheDoctorsAppointment?: string;
    medicine?: string;

    constructor(
        private http: HttpClient,
        private router: Router,
        private loginStorageService: LoginStorageService,
        //private md5Service: Md5Service
        private route: ActivatedRoute,
        private datePipe: DatePipe
    ) {
    }


    getQueryParams(): EditSicknessQueryParams {
        return {
            sicknessUid: this.route.snapshot.queryParamMap.get('uid'),
            patientId: this.route.snapshot.queryParamMap.get('patientId')
        };
    }

    ngOnInit(): void {
        const params: EditSicknessQueryParams = this.getQueryParams();
        const getPatientUrl: string = `http://localhost:8080/patient/${params.patientId}/sickness/${params.sicknessUid}`;

        if (!this.isNewSicknessPage()) {
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
                    this.startDate = this.convertStringToDate(data.startDate);
                    this.endDate = this.convertStringToDate(data.endDate);
                    this.symptoms = data.symptoms;
                    this.commentsToTheDoctorsAppointment = data.commentsToTheDoctorsAppointment;
                    this.medicine = data.medicine;
                })
        }
    }

    isNewSicknessPage(): boolean {
        const sicknessUid = this.route.snapshot.queryParamMap.get('uid');
        return sicknessUid == null;
    }

    formatDate(date: Date): string {
        return this.datePipe.transform(date, 'dd-MM-yyyy')!;
    }

    convertStringToDate(input: string): Date {
        // Split the string into day, month, and year components
        const [day, month, year] = input.split('-').map(Number);
        // Note: Months in JavaScript's Date object are zero-based (0 for January, 11 for December)
        return new Date(year, month - 1, day);
    }

    save(): void {
        const params: EditSicknessQueryParams = this.getQueryParams();

        //const hashedPassword = this.password;//this.md5Service.generateMd5Hash(this.password);
        const newSicknessUrl = `http://localhost:8080/patient/${params.patientId}/sickness`;
        const updateSicknessUrl = `http://localhost:8080/patient/${params.patientId}/sickness/${params.sicknessUid}`;

        if (params.sicknessUid == null) {
            const sicknessBody: Sickness = {
                name: this.name!,
                startDate: this.formatDate(this.startDate!),
                endDate: this.formatDate(this.endDate!),
                symptoms: this.symptoms!,
                commentsToTheDoctorsAppointment: this.commentsToTheDoctorsAppointment!,
                medicine: this.medicine!
            };
            fetch(newSicknessUrl, this.loginStorageService.getFetchConfig('POST', JSON.stringify(sicknessBody)))
                .then((response) => {
                    if (!response.ok) {
                        throw new Error('New sickness cannot be created');
                    }
                    alert("Sickness created successfully!")
                    this.router.navigate(['/']);
                })
        } else {
            fetch(updateSicknessUrl, this.loginStorageService.getFetchConfig('PUT'))
                .then((response) => {
                    if (!response.ok) {
                        throw new Error('Sickness cannot be updated');
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
