import { Component, OnInit, ViewChild, Input} from '@angular/core';
import {InvoiceService} from "../../services/invoice.service";
import {ComputerService} from "../../services/computer.service";
import {InvoiceDto} from "../../dto/invoice.dto";
import {ComputerDto} from "../../dto/computer.dto";
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {ActivatedRoute, Router} from "@angular/router";
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.scss']
})
export class InvoiceComponent implements OnInit {

  @ViewChild(MatSort) sort!: MatSort;
  @Input('ELEMENT_DATA') ELEMENT_DATA!:  ComputerDto[];
  displayedColumns = ['computerId', 'name', 'accountingDate', 'costUSD', 'costPLN'];
  dataSource = new MatTableDataSource<ComputerDto>(this.ELEMENT_DATA);
  accountingDate : string | null

  constructor(private activatedRoot: ActivatedRoute, private invoiceService: InvoiceService, private computerService: ComputerService, private router: Router) {
    this.accountingDate = this.activatedRoot.snapshot.paramMap.get('date')
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
    if (!!this.accountingDate)
    this.computerService.getComputer(this.accountingDate).subscribe(data => {
        this.dataSource.data = data as ComputerDto[];
        this.dataSource.sort = this.sort;
      },
      error => {
        console.log(error)
      }
    )
  }

  getXml(): void {
  if (!!this.accountingDate)
    this.invoiceService.download(this.accountingDate).subscribe((response: any) => {
      let blob:any = new Blob([response], { type: 'text/json; charset=utf-8' });
      const url = window.URL.createObjectURL(blob);
      saveAs(blob, `${this.accountingDate}.xml`);
    }),
    (error: any) => console.log('Error downloading the file'),
    () => console.info('File downloaded successfully');
  }

}
