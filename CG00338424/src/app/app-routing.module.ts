import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LecturersComponent } from './lecturers/lecturers.component';
import { StudentsComponent } from './students/students.component';
import { HomeComponent } from './home/home.component';
import { UpdateLecturerComponent } from './updatelecturer/updatelecturer.component';
import { ErrorComponent } from './error/error.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'lecturers', component: LecturersComponent },
  { path: 'updatelecturer/:lid', component: UpdateLecturerComponent },
  { path: 'students', component: StudentsComponent },
  { path: 'error', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }