import {HttpHeaders, HttpInterceptorFn} from '@angular/common/http';
import {inject} from '@angular/core';
import {AuthenticationService} from '../../services/authentication/authentication.service';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const token = inject(AuthenticationService).keycloak.token;

  if (token) {
    const authReq = req.clone({
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      })
    });
    return next(authReq);
  }
  return next(req);
};
