import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CalculatorComponent} from './calculator/calculator.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {ApiService} from "./backend/api.service";
import {CalculatorService} from "./backend/calculator.service";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";

@NgModule({
  declarations: [
    AppComponent,
    CalculatorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FontAwesomeModule,
  ],
  providers: [CalculatorService, HttpClient, ApiService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
