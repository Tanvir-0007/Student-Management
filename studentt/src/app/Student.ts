import {Subject} from "./Subject";

export class Student {
  id: number;
  name: string;
  roll: number;
  email: string;
  subjects: Subject[];

  constructor() {
    this.id = 0;
    this.name = '';
    this.roll = 0;
    this.email = '';
    this.subjects = [];
  }
}
