CREATE TABLE public.computers
(
computer_id serial NOT NULL UNIQUE,
nazwa varchar(100) NOT NULL,
data_ksiegowania date NOT NULL,
koszt_USD numeric(10,2) NOT NULL DEFAULT 0,
koszt_PLN numeric(10,2) NOT NULL DEFAULT 0,
invoice_id uuid,
CONSTRAINT invoice_fk FOREIGN KEY(invoice_id) REFERENCES public.invoices(invoice_id),
PRIMARY KEY(computer_id)
)