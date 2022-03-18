import {Component, OnInit, ViewChild, Input} from '@angular/core';
import {Router} from "@angular/router";
import {InvoiceService} from "../../services/invoice.service";
import {ComputerService} from "../../services/computer.service";
import {InvoiceDto} from "../../dto/invoice.dto";
import {ComputerDto} from "../../dto/computer.dto";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';

@Component({
  selector: 'app-invoice-list',
  templateUrl: './invoice-list.component.html',
  styleUrls: ['./invoice-list.component.scss']
})
export class InvoiceListComponent implements OnInit {

  public computer1: ComputerDto = {
    "computerId": '',
    "name": 'komputer 1',
    "accountingDate": '',
    "costUSD": '345',
    "costPLN": '',
  }

  public computer2: ComputerDto = {
    "computerId": '' ,
    "name": 'komputer 2',
    "accountingDate": '',
    "costUSD": '543',
    "costPLN": '',
  }

  public computer3: ComputerDto = {
    "computerId": '',
    "name": 'komputer 3',
    "accountingDate": '',
    "costUSD": '346',
    "costPLN": '',
  }

  public invoice: InvoiceDto = {
    "invoiceId": '',
    "computers": [this.computer1, this.computer2, this.computer3]
  }
   @ViewChild(MatSort) sort!: MatSort;

   @Input('ELEMENT_DATA') ELEMENT_DATA!:  ComputerDto[];

   formGroup: FormGroup;
   displayedColumns = ['computerId', 'name', 'accountingDate', 'costUSD', 'costPLN', 'button'];
   dataSource = new MatTableDataSource<ComputerDto>(this.ELEMENT_DATA);

  constructor(private invoiceService: InvoiceService, private computerService: ComputerService, private router: Router) {
      this.formGroup = new FormGroup(
        {
          accountingDate: new FormControl('',[Validators.required, Validators.pattern("/^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/")])
        }
      )
  }

  applyFilter(event: Event): void {
    const filter = (event.target as HTMLInputElement).value.trim().toLocaleLowerCase();
    this.dataSource.filter = filter;
    }

  filterTable() {
    this.dataSource.filterPredicate = (data: ComputerDto, filter: string): boolean => {
      return (
        data.name.toLocaleLowerCase().includes(filter)
      )
    }
  }

  ngOnInit(): void {
    this.computerService.getComputerList().subscribe(data => {
        this.dataSource.data = data as ComputerDto[];
        this.dataSource.sort = this.sort;
      },
      error => {
        console.log(error)
      }
    )
  }

  goToDetails(accountingDate: string): void {
this.router.navigate([accountingDate])
  }

  save(): void {
    this.computer1.accountingDate = this.formGroup.controls['accountingDate'].value
    this.computer2.accountingDate = this.formGroup.controls['accountingDate'].value
    this.computer3.accountingDate = this.formGroup.controls['accountingDate'].value
    this.invoiceService.saveInvoice({
       ... this.invoice
      }
    ).subscribe(() => {
    this.ngOnInit()
      },
      error => {
        console.log(error)
      }
    )
  }


}
