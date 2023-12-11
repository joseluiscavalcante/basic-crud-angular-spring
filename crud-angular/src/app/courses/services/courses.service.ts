import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Course } from '../model/course';
import { Observable, delay, first, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private readonly API = './assets/courses.json';

  constructor(private httpClient: HttpClient) { }

  list(): Observable<Course[]> {
    return this.httpClient.get<Course[]>(this.API)
    // Manipular a informação de forma reativa antes do retorno ao component
    .pipe(
      // Como o meu servidor não é reativo (stream), não iremos manter a conexão aberta após receber a primeira resposta
      first(),

      // Simular tempo de espera da resposta do servidor
      delay(5000),

      // Debugar - Verificar se está tudo funcionando
      tap(courses => console.log(courses))
    );
  }
}
