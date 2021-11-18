import {Component, Injectable, OnInit} from '@angular/core';
import {CalculatorService} from "../backend/calculator.service";
import {isNumeric} from "rxjs/internal-compatibility";
import {
  faDivide,
  faPercentage,
  faBackspace,
  faSquareRootAlt,
  faTimes,
  faMinus,
  faPlus,
  faEquals
} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css']
})

@Injectable({providedIn: 'root'})
export class CalculatorComponent implements OnInit {
  faDivide = faDivide;
  faPercentage = faPercentage;
  faBackspace = faBackspace;
  faSquareRootAlt = faSquareRootAlt;
  faTimes = faTimes;
  faMinus = faMinus;
  faPlus = faPlus;
  faEquals = faEquals;

  valueInserted: boolean;

  op1: string = "0";
  op2: string = "";
  op1Shape: string = "0";
  op2Shape: string = "";
  oneOP: boolean = false;
  operation: string = "";
  operationSymbol: string = "";
  setSecondOP: boolean = false;

  displayedEqn: string = "";
  displayedResult: string = "0";

  errorMessage: string = "";
  errorFlag: boolean;
  opSelect: boolean = false;

  constructor(private Calculator: CalculatorService) {
    this.valueInserted = true;
    this.errorFlag = false;
  }

  changeFont() {
    if (!isNumeric(this.displayedResult)) {
      return "1.5rem";
    } else {
      if (this.displayedResult.length > 11) {
        let x = (this.displayedResult.length % 11) / 4.5;
        return (3 - x) + "rem";
      }
      return "3rem";
    }
  }

  ngOnInit(): void {
  }

  num(numInput: string) {
    //if (this.displayedResult.length >= 16 && isNumeric(this.displayedResult) && !this.opSelect) return;
    if (this.errorMessage != "") this.delAll();

    if (numInput == "0" && Number.parseFloat(this.op1) == 0) return;

    if (!this.opSelect) {
      if (this.valueInserted) {
        this.op1 = numInput.toString();
        this.op1Shape = this.op1;
        this.displayedResult = this.op1Shape;
        this.valueInserted = false;
      } else {
        this.op1 = this.op1.concat(numInput.toString());
        this.op1Shape = this.op1;
        this.displayedResult = this.op1Shape;
      }
      this.op1Shape = this.op1;
    } else {
      if (this.oneOP) {
        this.oneOP = false;
        this.displayedEqn = this.op1 + " " + this.operationSymbol + " ";
        this.op2 = "";
      }
      this.op2 = this.op2.concat(numInput.toString());
      this.op2Shape = this.op2;
      this.displayedResult = this.op2Shape;
    }
  }

  setOP(opName: string, symbol: string) {
    if (this.opSelect && this.op2 != "") {
      this.setSecondOP = true;
      this.calc();
      this.operationSymbol = symbol;
      this.operation = opName;
      this.opSelect = true;
    } else {
      this.operation = opName;
      this.operationSymbol = symbol;
      this.opSelect = true;
      this.op2 = "";
      this.op2Shape = "";
      this.displayedEqn = this.op1.concat(" ", symbol, " ");
    }
  }

  decimalPoint() {
    if (!this.opSelect) {
      if (!this.op1.includes(".")) {
        this.op1 = this.op1.concat(".");
        this.op1Shape = this.op1;
        this.displayedResult = this.op1Shape;
      }
    } else {
      if (!this.op2.includes(".")) {
        this.op2 = this.op2.concat(".");
        this.op2Shape = this.op2;
        this.displayedResult = this.op2Shape;
      }
    }
  }

  percentTax() {
    let taxOperand, operand, percent_tax;

    if (this.op2 == "") {
      this.op2 = this.op1;
      this.op2Shape = this.op1Shape;
    }

    if (!this.opSelect && this.displayedEqn == "") {
      this.delAll();
      return;
    }
    if (this.operation == "divide" || this.operation == "multiply") {
      percent_tax = "percent";
      taxOperand = 0;
      operand = Number.parseFloat(this.op2);
      if (this.op2 == "") operand = Number.parseFloat(this.op1);
    } else {
      percent_tax = "tax";
      taxOperand = Number.parseFloat(this.op2);
      operand = Number.parseFloat(this.op1);
    }
    this.Calculator.SendEqnAttributes(percent_tax, operand, taxOperand).subscribe(
      response => {
        if (!response.error) {
          this.valueInserted = true;
          this.displayedResult = response.result.toString();
          this.op2 = this.displayedResult;
          this.op2Shape = this.op2;
          let x = this.displayedEqn.split(" ");
          if (x.length > 2) this.displayedEqn = x[0] + " " + x[1] + " ";
          this.displayedEqn = this.displayedEqn.concat(this.op2Shape);
          this.setSecondOP = false;
          this.oneOP = true;
        } else {
          this.errorMessage = response.message;
          this.errorFlag = true;
          this.displayedResult = this.errorMessage;
        }
      },
      error => {
        console.log(error);
      }
    )
  }

  oneOperand(opName: string, symbol: string) {
    let operand;
    let operandShape: string;
    let flag = symbol == "sqrt" || symbol == "sqr" || symbol == "1/";

    if (this.op2 == "") {
      this.op2 = this.op1;
      this.op2Shape = this.op1Shape;
    }

    if (!this.opSelect) {
      operandShape = this.op1Shape;
      operand = Number.parseFloat(this.op1);
    } else {
      operandShape = this.op2Shape;
      operand = Number.parseFloat(this.op2);
    }

    if (flag) {
      operandShape = symbol.concat("( ", operandShape, " )");
    }

    this.Calculator.SendOneOperand(opName, operand).subscribe(
      response => {
        if (!response.error) {
          if (!this.opSelect) {
            this.valueInserted = true;
            this.op1 = response.result.toString();
            this.op1Shape = operandShape;
            this.displayedEqn = operandShape;
            this.displayedResult = this.op1;
          } else {
            this.op2Shape = operandShape;
            this.op2 = response.result.toString();
            if (opName == "negate") {
              this.op2Shape = this.op2;
              this.displayedEqn = this.op1Shape.concat(" ", this.operationSymbol, " ");
            }
            let x = this.displayedEqn.split(" ");
            if (x.length > 3) this.displayedEqn = x[0] + " " + x[1] + " ";
            this.displayedEqn = this.displayedEqn.concat(this.op2Shape);
            this.oneOP = true;
            this.displayedResult = this.op2;
          }
        } else {
          this.errorMessage = response.message;
          this.errorFlag = true;
          this.displayedResult = this.errorMessage;
        }
      },
      error => {
        console.log(error);
      }
    )
  }

  calc() {
    if (!this.opSelect) return;
    if (this.op2 == "") {
      this.op2 = this.op1;
      this.op2Shape = this.op1Shape;
    }
    let operand1 = Number.parseFloat(this.op1);
    let operand2 = Number.parseFloat(this.op2);
    this.Calculator.SendEqnAttributes(this.operation, operand1, operand2).subscribe(
      response => {
        if (!response.error) {
          this.valueInserted = true;
          if (this.oneOP) this.displayedEqn = this.displayedEqn.concat(" = ");
          else this.displayedEqn = this.displayedEqn.concat(this.op2Shape, " = ");
          this.displayedResult = response.result.toString();
          this.op1 = response.result.toString();
          this.op1Shape = this.op1;
          if (this.setSecondOP) {
            this.displayedEqn = this.op1Shape.concat(" ", this.operationSymbol, " ");
            this.opSelect = true;
          } else {
            this.opSelect = false;
            this.operation = "";
          }
          this.setSecondOP = false;
          this.op2 = "";
          this.op2Shape = "";
          this.oneOP = false;
        } else {
          this.errorMessage = response.message;
          this.errorFlag = true;
          this.displayedResult = this.errorMessage;
        }
      },
      error => {
        console.log(error);
      }
    )
  }

  delLastEntry() {
    if (this.errorFlag) this.delAll();
    this.displayedResult = "0";
    if (this.opSelect) {
      this.op2 = "";
      this.op2Shape = "";
    } else {
      this.op1 = "0";
      this.op1Shape = "0";
    }
  }

  delAll() {
    this.op1 = "0";
    this.op1Shape = "0";
    this.op2 = "";
    this.op2Shape = "";
    this.valueInserted = true;
    this.operation = "";
    this.errorMessage = "";
    this.errorFlag = false;
    this.oneOP = false;
    this.setSecondOP = false;
    this.displayedResult = "0";
    this.displayedEqn = "";
    this.opSelect = false;
  }

  backSpace() {
    if (this.oneOP) return;
    if (this.errorFlag) this.delAll();
    if (this.displayedResult.length == 1) {
      this.displayedResult = "0";
      if (this.opSelect) {
        this.op2 = "";
        this.op2Shape = "";
        this.oneOP = false;
      } else {
        this.op1 = "0";
        this.op1Shape = "0";
        this.valueInserted = true;
      }
    } else {
      this.displayedResult = this.displayedResult.slice(0, -1);
      if (this.opSelect) {
        this.op2 = this.op2.slice(0, -1);
        this.op2Shape = this.op2;
      } else {
        this.op1 = this.op1.slice(0, -1);
        this.op1Shape = this.op1;
      }
    }
  }
}
