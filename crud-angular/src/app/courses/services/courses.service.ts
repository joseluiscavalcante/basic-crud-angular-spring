import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, Observable, tap } from 'rxjs';

import { Course } from '../model/course';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private readonly API = 'api/courses';

  constructor(private httpClient: HttpClient) { }

  list(): Observable<Course[]> {
    return this.httpClient.get<Course[]>(this.API)
    // Manipular a informação de forma reativa antes do retorno ao component
    .pipe(
      // Como o meu servidor não é reativo (stream), não iremos manter a conexão aberta após receber a primeira resposta
      first(),

      // Simular tempo de espera da resposta do servidor
      delay(1000),

      // Debugar - Verificar se está tudo funcionando
      tap(courses => console.log(courses))
    );
  }

  // Partial é usado pois estão sendo enviados apenas uma parte dos atributos da entidade
  save(record: Partial<Course>) {
    return this.httpClient.post<Course>(this.API, record).pipe(first());
  }

  loadById(id: number) {
    return this.httpClient.get<Course>(`${this.API}/${id}`);
  }
}
