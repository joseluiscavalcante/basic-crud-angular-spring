import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { Course } from '../../model/course';
import { CoursesService } from '../../services/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrl: './course-form.component.scss'
})
export class CourseFormComponent implements OnInit {
  // FormGroup gerencia e valida um conjunto de controles em um formulário.
  form = this.formBuilder.group({
    _id: [''],
    name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
    category: ['', [Validators.required]]
  });

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: CoursesService,
    private _snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const course: Course = this.route.snapshot.data['course'];

    this.form.setValue({
      _id: course._id,
      name: course.name,
      category: course.category
    });
  }

  private onError() {
    this._snackBar.open('Erro ao salvar curso.', 'Fechar', {duration: 5000});
  }

  onReturn() {
    this.location.back();
  }

  private onSuccess() {
    this._snackBar.open('Curso salvo com sucesso!', 'Fechar', {duration: 5000});
    this.onReturn();
  }

  getErrorMessage(fieldName: string) {
    const field = this.form.get(fieldName);
    if(field?.hasError('required')){
      return 'Campo obrigatório.';
    }

    if(field?.hasError('minlength')){
      /*
      field.errors é um objeto que tem uma propriedade chamada 'minlength',
      e essa propriedade por sua vez tem uma propriedade chamada 'requiredLength',
      essa expressão irá recuperar o valor associado a 'requiredLength'.
      */
      const requiredLength = field.errors ? field.errors['minlength']['requiredLength'] : 3;
      return `O tamanho mínimo é de ${requiredLength} caracteres.`;
    }

    if(field?.hasError('maxlength')){
      const requiredLength = field.errors ? field.errors['maxlength']['requiredLength'] : 100;
      return `O tamanho máximo é de ${requiredLength} caracteres.`;
    }

    return 'Campo inválido.';
  }

  onSubmit() {
    // 'this.form.value' retorna um objeto que representa o estado atual do formulário.
    this.service.save(this.form.value).subscribe({
      next: (result) => console.log(result),
      error: () => this.onError(),
      complete: () => this.onSuccess()
    });
  }

}
