import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginStorageService implements CanActivate {
  private loginStorageKey = 'login';

  constructor(
    private router: Router
  ) { }

  setValue(value: string): void {
    localStorage.setItem(this.loginStorageKey, value);
  }

  getValue(): string | null {
    return localStorage.getItem(this.loginStorageKey);
  }

  removeValue(): void {
    localStorage.removeItem(this.loginStorageKey);
  }

  canActivate(): boolean {
    if (this.getValue() !== null) {
      return true;
    } else {
      // Redirect to a specific route when the value is not as expected
      this.router.navigate(['/login']);
      return false;
    }
  }

  getFetchConfig(httpMethod: string): RequestInit {
    const authenticationHeader: string = this.getValue()!;

    const customHeaders = {
      'Authorization': authenticationHeader
    };

    const fetchConfig: RequestInit = {
      method: httpMethod,
      headers: new Headers(customHeaders),
      //credentials: 'include'
    };
    return fetchConfig;
  }
}
