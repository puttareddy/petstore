import {Component, OnInit} from '@angular/core';
import { AuthService } from './../auth/auth.service';
import {MessageService} from './message.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  constructor(public messageService: MessageService, public auth: AuthService) {
  }

  ngOnInit() {
  }
}
