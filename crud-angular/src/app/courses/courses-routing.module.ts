import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CourseFormComponent } from './course-form/course-form.component';
import { CoursesComponent } from './courses/courses.component';

const routes: Routes = [
  // Módulos são independentes
  // Permite carregar o Componente deste módulo que foi chamado no Módulo de Rotas Global
  {path:'', component: CoursesComponent},
  {path:'new', component: CourseFormComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoursesRoutingModule { }
