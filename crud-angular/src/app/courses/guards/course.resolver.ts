import { inject } from '@angular/core';  // Função de injeção de dependência do Angular usada para obter instâncias de serviços.
import { ResolveFn } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Course } from '../model/course';
import { CoursesService } from '../services/courses.service';

/*
Resolvers são usados para pré-carregar dados antes que uma rota seja ativada.
No contexto de uma rota, o resolver garante que determinados dados estejam
disponíveis antes que a rota seja ativada, evitando que a rota seja exibida
até que esses dados estejam prontos.
*/

export const courseResolver: ResolveFn<Observable<Course>> = (route, state,  service: CoursesService = inject(CoursesService)) => {
  // A utilização do '?' permite verificar se 'route.params' é nulo ou indefinido antes de tentar acessar a propriedade 'id'.
  // Se 'route.params' for nulo ou indefinido, a expressão inteira será avaliada como undefined, evitando erros de runtime
  if (route.params?.['id']){
    return service.loadById(route.params['id']);
  }

  return of({_id: '', name: '', category: ''});
};
