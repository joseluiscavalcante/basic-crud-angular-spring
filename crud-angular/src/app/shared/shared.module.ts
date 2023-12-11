import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ErrorDialogComponent } from './components/error-dialog/error-dialog.component';
import { ImportsMaterialModule } from './imports-material/imports-material.module';
import { CategoryPipe } from './pipes/category.pipe';



@NgModule({
  // Declara os componentes, diretivas e pipes que pertencem a esse módulo.
  declarations: [
    ErrorDialogComponent,
    CategoryPipe
  ],
  // Importar outros módulos cujas funcionalidades são necessárias para este módulo.
  imports: [
    CommonModule,
    ImportsMaterialModule
  ],
  // Disponibilizar componentes, diretivas ou pipes para outros módulos.
  exports: [
    ErrorDialogComponent,
    CategoryPipe
  ]
})
export class SharedModule { }
