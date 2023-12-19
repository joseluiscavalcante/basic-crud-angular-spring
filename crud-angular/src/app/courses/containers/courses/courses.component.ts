import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { CoursesService } from '../../services/courses.service';
import { Course } from './../../model/course';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {
  // $:Identifica que é um observable
  public courses$: Observable<Course[]> | null = null;

  constructor(
    private coursesService: CoursesService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private _snackBar: MatSnackBar,
    ) {
    /*
    Inicializar variáveis no construtor devido ao  Strict mode do Angular
    O modo estrito melhora a capacidade de manutenção e ajuda a detectar bugs com antecedência.
    */
    this.refresh();
  }

  refresh() {
    this.courses$ = this.coursesService.list()
      .pipe(
        catchError(error => {
          this.onError('Erro ao carregar cursos.');
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

  ngOnInit(): void { /* TODO document why this method 'ngOnInit' is empty */ }

  onAdd() {
    console.log('onAdd');
    this.router.navigate(['new'], {relativeTo: this.route});  // Realiza a navegação até a rota new com base na rota atual
  }

  onEdit(course: Course) {
    this.router.navigate(['edit', course._id], {relativeTo: this.route});
  }

  onRemove(course: Course) {
    this.coursesService.delete(course._id).subscribe({
      next: () => this.refresh(),
      error: () => this.onError('Erro ao tentar remover curso.'),
      complete: () => this._snackBar.open('Curso deletado com sucesso!', 'Fechar', {duration: 5000,
                                                                                    verticalPosition: 'top',
                                                                                    horizontalPosition: 'center'})
    });
  }
}
