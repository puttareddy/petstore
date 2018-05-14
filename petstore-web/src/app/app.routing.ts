import {ModuleWithProviders} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {DashboardComponent} from './dashboard/dashboard.component';
import {PetDetailComponent} from './pet-details/pet-detail.component';
import {PetsComponent} from './pets/pets.component';
import {CallbackComponent} from './callback/callback.component'
import {HomeComponent} from './home/home.component'

const appRoutes: Routes = [
  {
    path: '',
    component: DashboardComponent    
  },
  {
    path: 'home',
    component: DashboardComponent
  },
  {
    path: 'callback',
    component: CallbackComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'detail/:id',
    component: PetDetailComponent
  },
  {
    path: 'pets',
    component: PetsComponent
  }
];
export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
