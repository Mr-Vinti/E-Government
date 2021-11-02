DROP TABLE IF EXISTS record;


CREATE TABLE record (
  id IDENTITY PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  phone_number VARCHAR(250) NOT NULL,
  address VARCHAR(250) NOT NULL,
  payment DOUBLE NOT NULL,
  payment_with_vat DOUBLE NOT NULL
);


INSERT INTO record (first_name, last_name, email, phone_number, address, payment, payment_with_vat) VALUES
  ('Marius', 'Vintila', 'marius.vintila@gmail.com', '0728286794', 'Somewhere', 123.5, 146.965);