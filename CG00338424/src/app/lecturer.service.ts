import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Lecturer } from './lecturers/lecturer.model';

@Injectable({
  providedIn: 'root',
})
export class LecturerService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getLecturers(): Observable<Lecturer[]> {
    return this.http.get<Lecturer[]>(`${this.apiUrl}/lecturers`);
  }

  getLecturerById(lid: string): Observable<Lecturer> {
    return this.http.get<Lecturer>(`${this.apiUrl}/lecturers/${lid}`);
  }

  updateLecturer(lid: string, lecturer: any): Observable<Lecturer> {
    return this.http.put<Lecturer>(`${this.apiUrl}/lecturers/${lid}`, lecturer);
  }
}