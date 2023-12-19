import { Component, EventEmitter, Input, Output } from '@angular/core';

import { Course } from '../../model/course';

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrl: './courses-list.component.scss'
})
export class CoursesListComponent {
  @Input() courses: Course[] = [];
  /*
  O valor false é passado como argumento para o construtor. Isso significa que os eventos emitidos
  serão síncronos. Em termos simples, quando um evento é emitido, o Angular irá notificar
  imediatamente os ouvintes desse evento antes de continuar com outras tarefas.
  */
  /*
  Se fosse true (o valor padrão), os eventos seriam assíncronos, o que significa que a notificação
  aos ouvintes seria feita em um momento posterior, permitindo que outras tarefas sejam concluídas primeiro.
  */
  @Output() addEvent = new EventEmitter(false);
  @Output() editEvent = new EventEmitter(false);
  @Output() removeEvent = new EventEmitter(false);

  readonly displayedColumns = ['_id', 'name', 'category', 'actions']  // Tipada por inferência de tipos

  onAdd() {
    this.addEvent.emit(true);
  }

  onEdit(course: Course) {
    this.editEvent.emit(course);
  }

  onRemove(course: Course) {
    this.removeEvent.emit(course);
  }
}
