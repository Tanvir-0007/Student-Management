import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from './student';
import {Subject} from "./Subject";

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private apiUrl = 'http://localhost:8080/students';

  constructor(private http: HttpClient) { }

  getAllStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl);
  }

  getStudentById(id: number): Observable<Student> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Student>(url);
  }


  createStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(this.apiUrl, student);
  }

  updateStudent(id: number, student: Student): Observable<Student> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.put<Student>(url, student);
  }

  deleteStudent(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }

  addSubjectToStudent(studentId: number, subject: Subject): Observable<Student> {
    const url = `${this.apiUrl}/${studentId}/subjects`;
    return this.http.post<Student>(url, subject);
  }
}

