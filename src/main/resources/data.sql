DROP TABLE IF EXISTS DOCUMENTS;
DROP TABLE IF EXISTS INVOICES;
DROP TABLE IF EXISTS CONTRACTS;
DROP TABLE IF EXISTS VENDOR;


CREATE TABLE VENDOR (
  vendor_id INT AUTO_INCREMENT  PRIMARY KEY,
  company_name VARCHAR(200) NOT NULL
);


CREATE TABLE CONTRACTS (
  contract_id INT AUTO_INCREMENT  PRIMARY KEY,
  contract_number BIGINT NOT NULL,
  to_bill DOUBLE NOT NULL,
  status VARCHAR(100) DEFAULT NULL,
  vendor_id INT,
  TEST INT,
  FOREIGN KEY (vendor_id) REFERENCES VENDOR(vendor_id)
);



CREATE TABLE INVOICES (
  invoice_id INT AUTO_INCREMENT  PRIMARY KEY,
  invoice_number BIGINT NOT NULL,
  value DOUBLE NOT NULL,
  contract_id INT,
  FOREIGN KEY (contract_id) REFERENCES CONTRACTS(contract_id)
);



CREATE TABLE DOCUMENTS (
  document_id INT AUTO_INCREMENT  PRIMARY KEY,
  document_type VARCHAR(200),
  invoice_id INT,
  FOREIGN KEY (invoice_id) REFERENCES INVOICES(invoice_id)
);


INSERT INTO VENDOR (company_name) VALUES
	('Nestle SA'),
	('Procter & Gamble'),
	('PepsiCo'),
	('Unilever N.V.'),
	('Anheuser-Busch InBev'),
	('Christian Dior'),  
	('JBS S.A.');

INSERT INTO CONTRACTS (contract_number, to_bill, status, vendor_id) VALUES
  (1, 1000, 'APPROVED', 1),
  (2, 2000, 'APPROVED', 2),
  (3, 3000, 'APPROVED', 3),
  (4, 4000, 'APPROVED', 4),
  (5, 5000, 'APPROVED', 5);

INSERT INTO INVOICES (invoice_number, value, contract_id) VALUES
  (1, 100, 1),
  (2, 200, 2),
  (3, 300, 3),
  (4, 400, 4),
  (5, 500, 5);
  
  
INSERT INTO DOCUMENTS (document_type) VALUES
  ('ANNUAL_STATEMENT'),
  ('QUARTER_STATEMENT'),
  ('BORROWER_LETTER');





