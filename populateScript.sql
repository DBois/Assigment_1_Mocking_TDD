INSERT INTO public.bank (cvr, name) VALUES ('12345678', 'Danske Bank');
INSERT INTO public.bank (cvr, name) VALUES ('87654321', 'Nordea');

INSERT INTO public.customer (cpr, name) VALUES ('1234560001', 'Emil');
INSERT INTO public.customer (cpr, name) VALUES ('1234560002', 'Adam');
INSERT INTO public.customer (cpr, name) VALUES ('1234560003', 'Sebastian');
INSERT INTO public.customer (cpr, name) VALUES ('1234560004', 'Michael');
INSERT INTO public.customer (cpr, name) VALUES ('1234560005', 'Anders');

INSERT INTO public.account (number, balance, customer_cpr, bank_cvr) VALUES ('0000000000', 10000, '1234560001', '12345678');
INSERT INTO public.account (number, balance, customer_cpr, bank_cvr) VALUES ('1111111111', 10000, '1234560001', '12345678');
INSERT INTO public.account (number, balance, customer_cpr, bank_cvr) VALUES ('2222222222', 10000, '1234560002', '12345678');
INSERT INTO public.account (number, balance, customer_cpr, bank_cvr) VALUES ('3333333333', 10000, '1234560002', '12345678');
INSERT INTO public.account (number, balance, customer_cpr, bank_cvr) VALUES ('4444444444', 10000, '1234560003', '12345678');
INSERT INTO public.account (number, balance, customer_cpr, bank_cvr) VALUES ('5555555555', 10000, '1234560003', '12345678');
INSERT INTO public.account (number, balance, customer_cpr, bank_cvr) VALUES ('6666666666', 10000, '1234560004', '87654321');
INSERT INTO public.account (number, balance, customer_cpr, bank_cvr) VALUES ('7777777777', 10000, '1234560004', '87654321');
INSERT INTO public.account (number, balance, customer_cpr, bank_cvr) VALUES ('8888888888', 10000, '1234560005', '87654321');
INSERT INTO public.account (number, balance, customer_cpr, bank_cvr) VALUES ('9999999999', 10000, '1234560005', '87654321');

