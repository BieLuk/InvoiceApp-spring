INSERT INTO public.users(email, name, phone, postcode, street, city, active)
  VALUES
  ('abv@wp.pl', 'ABC XYZ', '123456789', '05-140', 'Warszawska 35', 'Serock', true),
  ('jankowal@op.pl', 'JAN KOWALSKI', '987654321', '01-120', 'Polna 44', 'Warszawa', true),
  ('rafal@gmail.com', 'Rafał Falega', '147258369', '05-120', 'Józefa Piłsudskiego 78', 'Legionowo', false),
  ('barbara@ksiegowoscinter.com', 'Inter Ksiegowosc Barbara Malinowska', '445678912', '25-457', 'Prosta 12', 'Pcim Dolny', true),
  ('gt@gmail.com', 'Gran Turismo', '987654321', '01-424', 'Prymasa Tysiąclecia 97', 'Warszawa', true);



INSERT INTO public.clients(
	city, name, nip, postcode, street, user_id)
	VALUES
	('Wadowice', 'ABAB', '8854123547', '01-860', 'Puławska 51', 5),
	('Stasi Las', 'XYZ ABC', '8774235484', '005-140', 'Główna 21', 5),
	('Leszczynowo', 'MOLINEO', '9638527417', '04-560', 'Dobra 18', 1);