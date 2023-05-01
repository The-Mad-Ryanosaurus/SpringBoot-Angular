import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LecturersComponent } from './lecturers/lecturers.component';
import { StudentsComponent } from './students/students.component';
import { AppRoutingModule } from './app-routing.module';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { UpdateLecturerComponent } from './updatelecturer/updatelecturer.component';
import { FormsModule } from '@angular/forms';
import { ErrorComponent } from './error/error.component';




@NgModule({
  declarations: [
    AppComponent,
    LecturersComponent,
    StudentsComponent,
    UpdateLecturerComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NoopAnimationsModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }