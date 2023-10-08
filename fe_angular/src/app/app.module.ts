import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { PatientListComponent } from './patient-list/patient-list.component';
import { PatientComponent } from './patient/patient.component';
import { LoginStorageService } from "./services/login-storage.service";

@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path: '', canActivate: [LoginStorageService], component: PatientListComponent },
      //{ path: 'login', component: LoginComponent },
    ])
  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    PatientComponent,
    PatientListComponent
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
