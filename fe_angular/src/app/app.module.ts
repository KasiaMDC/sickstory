import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';

import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { PatientListComponent } from './patient-list/patient-list.component';
import { PatientComponent } from './patient/patient.component';
import { LoginComponent } from './login/login.component';
import { LoginStorageService } from "./services/login-storage.service";
import {EditPatientComponent} from "./edit-patient/edit-patient.component";
import {EditSicknessComponent} from "./edit-sickness/edit-sickness.component";

@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot([
      { path: '', canActivate: [LoginStorageService], component: PatientListComponent },
      { path: 'login', component: LoginComponent },
      { path: 'patient', canActivate: [LoginStorageService], component: EditPatientComponent },
      { path: 'sickness', canActivate: [LoginStorageService], component: EditSicknessComponent }
    ])
  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    PatientComponent,
    PatientListComponent,
    LoginComponent,
    EditPatientComponent,
    EditSicknessComponent
  ],
  providers: [
    DatePipe,
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
