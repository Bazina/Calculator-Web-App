export class Result {
  public error: boolean;
  public result: number;
  public message: string;

  constructor(error: boolean, result: number, message: string) {
    this.error = error;
    this.result = result;
    this.message = message;
  }
}
