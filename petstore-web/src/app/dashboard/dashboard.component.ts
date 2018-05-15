import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {Pet} from '../pet';
import {PetService} from '../pets/pet.service';
import { AuthService } from './../auth/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: 'dashboard.component.html',
  styleUrls: ['dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  pets: Pet[] = [];

  constructor(private router: Router,
              private petService: PetService,
            public auth: AuthService) {
  }

  ngOnInit(): void {
    this.petService.getPets()
      .subscribe(pets => { 
        console.log('>>pets: ',pets);
        // this.pets= Object.values(pets)});
        this.pets = pets.slice(0, 4)});
  }

  gotoDetail(pet: Pet): void {
    const link = ['/detail', pet.id];
    this.router.navigate(link);
  }
}
