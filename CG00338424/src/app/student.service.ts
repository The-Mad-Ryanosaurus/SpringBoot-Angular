import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from './students/student.model';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.apiUrl}/students`);
  }

  getStudentById(sid: string): Observable<Student> {
    return this.http.get<Student>(`${this.apiUrl}/students/${sid}`);
  }

  deleteStudent(sid: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/students/${sid}`);
  }
}