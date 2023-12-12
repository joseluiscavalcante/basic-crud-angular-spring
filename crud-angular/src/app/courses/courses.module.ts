import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { ImportsMaterialModule } from '../shared/imports-material/imports-material.module';
import { SharedModule } from '../shared/shared.module';
import { CoursesRoutingModule } from './courses-routing.module';
import { CoursesComponent } from './courses/courses.component';  // Módulo necessário para utilizar FormBuilder e FormGroup
import { CourseFormComponent } from './course-form/course-form.component';

@NgModule({
  declarations: [
    CoursesComponent,
    CourseFormComponent
  ],
  imports: [
    CommonModule,
    CoursesRoutingModule,
    ImportsMaterialModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class CoursesModule { }
