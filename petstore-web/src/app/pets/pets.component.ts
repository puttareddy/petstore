import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {Pet} from '../pet';
import {PetService} from '../pets/pet.service';
import { AuthService } from './../auth/auth.service';

@Component({
  selector: 'app-pets',
  templateUrl: 'pets.component.html',
  styleUrls: ['pets.component.css']
})
export class PetsComponent implements OnInit {

  pets: Pet[];
  selectedPet: Pet;

  constructor(private router: Router,
              private petService: PetService,
            public auth: AuthService) {
  }

  ngOnInit(): void {
    this.getPets();
  }

  getPets(): void {
    this.petService.getPets()
      .subscribe(pets => this.pets = pets);
  }

  onSelect(pet: Pet): void {
    this.selectedPet = pet;
  }

  gotoDetail(): void {
    this.router.navigate(['/detail', this.selectedPet.id]);
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.petService.create({name} as Pet)
      .subscribe(pet => {
        this.pets.push(pet);
        this.selectedPet = null;
      });
  }

  delete(pet: Pet): void {
    this.petService.delete(pet.id)
      .subscribe(() => {
        this.pets = this.pets.filter(p => p !== pet);
        if (this.selectedPet === pet) { this.selectedPet = null; }
      });
  }
}
