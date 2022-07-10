import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormComponent } from './components/form/form.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { ForecastComponent } from './components/forecast/forecast.component';
import { AuthGuard } from './guards/auth.guard';
import { HomeGuard } from './guards/home.guard';
import { ProfileComponent } from './components/profile/profile.component';




const routes: Routes = [
  //{ path: '', redirectTo: 'signup', pathMatch: 'full' },
  { path: 'signup', component: SignupComponent,
    canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent,
    canActivate: [AuthGuard] },
  { path: 'home', component: HomeComponent},
  { path:'current',component:FormComponent},
  { path:'forecast',component:ForecastComponent},
  { path: 'user', component: ProfileComponent,
    canActivate: [HomeGuard] },
    
  { path: '**', redirectTo: 'home' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
