CREATE TABLE public.invoices
(
invoice_id uuid NOT NULL UNIQUE,
data varchar(100) NOT NULL,
PRIMARY KEY (invoice_id)
)