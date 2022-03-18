import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {InvoiceListComponent} from "./components/invoice-list/invoice-list.component";
import {InvoiceComponent} from "./components/invoice/invoice.component";

const routes: Routes = [
  {
    path: '',
    component: InvoiceListComponent
  },
  {
    path: ':date',
    component: InvoiceComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
