import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Lecturer } from '../lecturers/lecturer.model';
import { LecturerService } from '../lecturer.service';

@Component({
  selector: 'app-update-lecturer',
  templateUrl: './updatelecturer.component.html',
  styleUrls: ['./updatelecturer.component.css']
})
export class UpdateLecturerComponent implements OnInit {

  lecturer: Lecturer = {
    lid: '',
    name: '',
    taxBand: '',
    salaryScale: ''
  };

  lidErrorMessage = 'LID Cannot Be Altered!';

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private lecturerService: LecturerService
  ) { }

  ngOnInit() {
    const state = this.router.getCurrentNavigation()?.extras.state;
    if (state && state['lecturer']) {
      this.lecturer = state['lecturer'];
    } else {
      this.route.paramMap.subscribe(params => {
        const lid = params.get('lid');
        if (lid) {
          this.fetchLecturerById(lid);
        }
      });
    }
  }

  fetchLecturerById(lid: string) {
    this.lecturerService.getLecturerById(lid)
      .subscribe(lecturer => {
        this.lecturer = lecturer;
      });
  }

  onSubmit() {
    const updatedLecturer = {
      name: this.lecturer.name,
      taxBand: this.lecturer.taxBand,
      salaryScale: this.lecturer.salaryScale
    };

    this.lecturerService.updateLecturer(this.lecturer.lid, updatedLecturer)
      .subscribe(() => {
        this.router.navigate(['/lecturers']);
      });
  }
}