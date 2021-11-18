import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Result} from "./Result";
import {ApiService} from "./api.service";

@Injectable()
export class CalculatorService {
  constructor(private apiService: ApiService) {
  }

  SendEqnAttributes(operationMapping: string, op1: number, op2: number): Observable<Result> {
    return this.apiService.post("Result", {"op1": op1, "op2": op2, "operation": operationMapping});
  }

  SendOneOperand(operationMapping: string, op1: number): Observable<Result> {
    return this.apiService.post("Result", {"operand": op1, "operation": operationMapping});
  }
}
