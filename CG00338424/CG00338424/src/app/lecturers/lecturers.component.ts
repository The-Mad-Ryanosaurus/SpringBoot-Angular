import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Lecturer } from './lecturer.model';
import { LecturerService } from '../lecturer.service';

@Component({
  selector: 'app-lecturers',
  templateUrl: './lecturers.component.html',
  styleUrls: ['./lecturers.component.css']
})
export class LecturersComponent implements OnInit {

  lecturers: Lecturer[] = [];

  constructor(private lecturerService: LecturerService, private router: Router) { }

  ngOnInit() {
    this.fetchLecturers();
  }

  fetchLecturers() {
    this.lecturerService.getLecturers()
      .subscribe(lecturers => {
        this.lecturers = lecturers;
      });
  }

  onUpdateButtonClick(lid: string) {
    const selectedLecturer = this.lecturers.find(lecturer => lecturer.lid === lid);
    if (selectedLecturer) {
      this.router.navigate(['/updatelecturer', lid], { state: { lecturer: selectedLecturer } });
    }
  }
}