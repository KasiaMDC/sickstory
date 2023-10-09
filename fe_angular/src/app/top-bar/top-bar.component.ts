import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginStorageService } from "../services/login-storage.service";

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent {
  constructor(
      private router: Router,
      private loginStorageService: LoginStorageService
  ) { }

  logout(): void {
    this.loginStorageService.removeValue();
    this.router.navigate(['/login']);
  }

}
