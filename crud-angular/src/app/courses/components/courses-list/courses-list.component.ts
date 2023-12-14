import { Component, EventEmitter, Input, Output } from '@angular/core';

import { Course } from '../../model/course';

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrl: './courses-list.component.scss'
})
export class CoursesListComponent {
  @Input() courses: Course[] = [];
  @Output() addEvent = new EventEmitter(false);

  readonly displayedColumns = ['_id', 'name', 'category', 'actions']  // Tipada por inferência de tipos

  onAdd() {
    this.addEvent.emit(true);
  }
}
