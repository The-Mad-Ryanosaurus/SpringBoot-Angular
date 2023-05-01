import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from './student.model';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {

  students: Student[] = [];

  constructor(private studentService: StudentService, private router: Router) { }

  ngOnInit() {
    this.fetchStudents();
  }

  fetchStudents() {
    this.studentService.getStudents()
      .subscribe(students => {
        this.students = students;
      });
  }

  onDeleteButtonClick(sid: string) {
    this.studentService.deleteStudent(sid).subscribe(
      (response) => {
        console.log(response);
        // handle the success case, e.g., refresh the students list
        this.fetchStudents();
      },
      (error) => {
        console.log(error);
        // handle the error case, e.g., display an error message
        this.router.navigate(['/error'], { queryParams: { message: error.error.message } });
      }
    );
  }
}