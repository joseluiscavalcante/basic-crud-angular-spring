import { Pipe, PipeTransform } from '@angular/core';

/* Um pipe é uma maneira de transformar dados em um template Angular antes de exibi-los.
Os pipes podem ser usados para formatar datas, números, strings, entre outros tipos de dados,
tornando mais fácil a manipulação e apresentação desses dados no template.
*/
@Pipe({
  name: 'category'
})
export class CategoryPipe implements PipeTransform {

  transform(value: string): string {
    switch(value) {
      case 'front-end': return 'code'
      case 'back-end': return 'computer'
    }
    return 'code';

  }

}
