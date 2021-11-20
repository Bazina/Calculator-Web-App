# Calculator

- This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 12.2.12, and Spring Boot version 2.5.7 [Spring Boot](https://start.spring.io/).

- Standard calculator allows calculations for one operation at a time.

## Table of Contents

- [Development-Server](#Development-Server)
- [Build](#Build)
- [User-Manual](#User-Manual)
- [Instructions](#Instructions)
- [Errors](#Errors)

## Development Server

- Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.
- Backend is running on `http://localhost:8080/`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## User Manual

- This calculator is almost the windows calculator so you will be familiar with it if you used the windows calculator.
- The Calculator evaluates one operation at a time.
- The Result is displayed with the bold and bigger font in the middle-bottom of the screen and the equation which was evaluated at top of the screen.
- The operands are to be assigned so any operation can be done.
- The operations that need 2 operands will not evaluate if there is no previous result and just one operand was entered.
- To use the result of the last operation and do another operation on it, then after writing the equation just press the button that indicates the next operation that will be done on the result of the last equation, so this result will be considered as the first operand if the following operation was one that needs 2 operands and if it’s one operand operation then the new calculation will be evaluated instantly.
- The `CE` button clears the last inserted value, `C` clears all the equations and results, `Backspace` button deletes the element by an element of the value that is being entered now.
- No division by zero, or square root for a negative number, and the calculator will give an error if an expression is evaluated without its operands, for example: `sqrt ()`.
- If the expression was `6 +` and the equal button was clicked the calculator will add the number to itself and this holds for other operations.

## Errors

- Division by zero isn’t allowed.
- Take the root of a negative number isn’t allowed.
