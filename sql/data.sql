-- Necessary

INSERT INTO public.roles(name) VALUES('ROLE_USER');
INSERT INTO public.roles(name) VALUES('ROLE_ADMIN');

INSERT INTO public.vat_type(name) VALUES('23%');
INSERT INTO public.vat_type(name) VALUES('22%');
INSERT INTO public.vat_type(name) VALUES('8%');
INSERT INTO public.vat_type(name) VALUES('7%');
INSERT INTO public.vat_type(name) VALUES('5%');
INSERT INTO public.vat_type(name) VALUES('3%');
INSERT INTO public.vat_type(name) VALUES('0%');
INSERT INTO public.vat_type(name) VALUES('zw.');
INSERT INTO public.vat_type(name) VALUES('np.');
INSERT INTO public.vat_type(name) VALUES('o.o.');

INSERT INTO public.invoice_type(name) VALUES('Faktura');
INSERT INTO public.invoice_type(name) VALUES('Proforma');
INSERT INTO public.invoice_type(name) VALUES('Faktura Marża');
INSERT INTO public.invoice_type(name) VALUES('Faktura Zaliczkowa');
INSERT INTO public.invoice_type(name) VALUES('Faktura Korygująca');
INSERT INTO public.invoice_type(name) VALUES('Faktura MOSS');

INSERT INTO public.payment_type(name) VALUES('Gotówka');
INSERT INTO public.payment_type(name) VALUES('Przelew');
INSERT INTO public.payment_type(name) VALUES('Karta płatnicza');
INSERT INTO public.payment_type(name) VALUES('Barter');
INSERT INTO public.payment_type(name) VALUES('Czek');
INSERT INTO public.payment_type(name) VALUES('Za pobraniem');
INSERT INTO public.payment_type(name) VALUES('Inny');
INSERT INTO public.payment_type(name) VALUES('Kompensata');
INSERT INTO public.payment_type(name) VALUES('Akredytywa');
INSERT INTO public.payment_type(name) VALUES('PayU');
INSERT INTO public.payment_type(name) VALUES('PayPal');
INSERT INTO public.payment_type(name) VALUES('Weksel');
INSERT INTO public.payment_type(name) VALUES('Sprzedaż ratalna');


insert into users (name, username, email, password, active)
values ('Łukasz Bieńkowski', 'lukasz', 'lukcxz1@wp.pl', '$2a$10$txxWrBvyPV1fK6eaoDJwsesL/eqcNtvy6kY25nz8iiPiqCzkekjXe', true);

INSERT INTO public.clients(
	city, name, nip, postcode, street, user_id)
	VALUES
	('Wadowice', 'ABAB', '8854123547', '01-860', 'Puławska 51', 5),
	('Stasi Las', 'XYZ ABC', '8774235484', '005-140', 'Główna 21', 1),
	('Leszczynowo', 'MOLINEO', '9638527417', '04-560', 'Dobra 18', 1);
