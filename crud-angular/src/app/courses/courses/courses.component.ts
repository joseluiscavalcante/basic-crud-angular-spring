import { Observable, catchError, of } from 'rxjs';
import { Component, OnInit } from '@angular/core';

import { Course } from '../model/course';
import { CoursesService } from '../services/courses.service';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {
  public courses$: Observable<Course[]>;  // Identifica que é um observable
  public displayedColumns = ['_id', 'name', 'category']  // Tipada por inferência de tipos

  constructor(
    private coursesService: CoursesService,
    public dialog: MatDialog
    ) {
    // Inicialização das variáveis no construtor devido ao  Strict mode do Angular
    // O modo estrito melhora a capacidade de manutenção e ajuda a detectar bugs com antecedência.
    this.courses$ = this.coursesService.list()
    .pipe(
      catchError(error => {
        this.onError('Erro ao carregar cursos.')
        /*
          Operador of(): maneira conveniente de criar observables com valores predefinidos,
          especialmente quando você precisa simular uma fonte de dados observável para testes
          ou para fornecer dados iniciais em um fluxo de dados observável.
        */
        return of([]);
      })
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  ngOnInit(): void {}
}
