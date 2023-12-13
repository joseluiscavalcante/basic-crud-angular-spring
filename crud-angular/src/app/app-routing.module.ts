import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Módulo de rotas global
const routes: Routes = [
  { path:'', pathMatch:'full', redirectTo:'courses' },
  // Permite o carregamento tardio do módulo escolhido
  {
    path:'courses',
    loadChildren: () => import('./courses/courses.module').then(m => m.CoursesModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
