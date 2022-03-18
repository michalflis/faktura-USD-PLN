import {ComputerDto} from "./computer.dto";

export interface InvoiceDto {
invoiceId: string,
computers: Array<ComputerDto>,
}
