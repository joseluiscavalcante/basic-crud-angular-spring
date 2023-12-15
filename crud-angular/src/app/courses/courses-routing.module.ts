import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CourseFormComponent } from './containers/course-form/course-form.component';
import { CoursesComponent } from './containers/courses/courses.component';
import { courseResolver } from './guards/course.resolver';

const routes: Routes = [
  // Módulos são independentes
  // Permite carregar o Componente deste módulo que foi chamado no Módulo de Rotas Global
  {path:'', component: CoursesComponent},
  {path:'new', component: CourseFormComponent, resolve: {course: courseResolver}},
  {path:'edit/:id', component: CourseFormComponent, resolve: {course: courseResolver}},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoursesRoutingModule { }
