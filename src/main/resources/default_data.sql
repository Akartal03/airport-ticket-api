
//AIRPORTS
insert into airport(id,iatacode,name,countryisocode) values (1,'DXB','Dubai International Airport','AE');
insert into airport(id,iatacode,name,countryisocode) values (2,'HEL','Helsinki Vantaa Airport','FI');
insert into airport(id,iatacode,name,countryisocode) values (3,'GRU','Guarulhos - Governador Andr̩ Franco Montoro International Airport','BR');
insert into airport(id,iatacode,name,countryisocode) values (4,'HEK','Heihe Airport','CN');
insert into airport(id,iatacode,name,countryisocode) values (5,'IND','Indianapolis International Airport','US');
insert into airport(id,iatacode,name,countryisocode) values (6,'COK','Cochin International Airport','IN');
insert into airport(id,iatacode,name,countryisocode) values (7,'GRT','Gujrat Airport','PK');
insert into airport(id,iatacode,name,countryisocode) values (8,'INJ','Injune Airport','AU');
insert into airport(id,iatacode,name,countryisocode) values (9,'AUH','Abu Dhabi International Airport','AE');
insert into airport(id,iatacode,name,countryisocode) values (10,'JAA','Jalalabad Airport','AF');

//AIRLINECOMPANIES
insert into airlinecompany(id, name) values (1,'PEGASUS');
insert into airlinecompany(id, name) values (2,'TURK HAVA YOLLARI');
insert into airlinecompany(id, name) values (3,'AMERICAN AIRLINES');
insert into airlinecompany(id, name) values (4,'ATLAS JET');

//ROUTES
insert into route(id, from_airport_id, to_airport_id) values(1,1,2);
insert into route(id, from_airport_id, to_airport_id) values(2,1,4);
insert into route(id, from_airport_id, to_airport_id) values(3,2,5);
insert into route(id, from_airport_id, to_airport_id) values(4,3,4);
insert into route(id, from_airport_id, to_airport_id) values(5,3,10);
insert into route(id, from_airport_id, to_airport_id) values(6,9,8);
insert into route(id, from_airport_id, to_airport_id) values(7,6,7);
insert into route(id, from_airport_id, to_airport_id) values(8,8,1);
insert into route(id, from_airport_id, to_airport_id) values(9,10,2);

//FLIGHTS
insert into flight(id, quota, numberofsoldtickets, priceincreaseratio, ticketprice, airlinecompany_id, route_id, departuredate, arrivaldate)values(1, 20, 0,10, 10, 1, 1, '2020-05-14 12:30', '2020-05-14 18:15');
insert into flight(id, quota, numberofsoldtickets, priceincreaseratio, ticketprice, airlinecompany_id, route_id, departuredate, arrivaldate)values(2, 120, 0,10, 109.9, 2, 9, '2020-05-14 12:30', '2020-05-14 18:15');
insert into flight(id, quota, numberofsoldtickets, priceincreaseratio, ticketprice, airlinecompany_id, route_id, departuredate, arrivaldate)values(3, 80, 0,10, 129.9, 4, 4, '2020-05-14 12:30', '2020-05-14 18:15');
insert into flight(id, quota, numberofsoldtickets, priceincreaseratio, ticketprice, airlinecompany_id, route_id, departuredate, arrivaldate)values(4, 120, 0,10, 89.9, 3, 5, '2020-05-14 12:30', '2020-05-14 18:15');
insert into flight(id, quota, numberofsoldtickets, priceincreaseratio, ticketprice, airlinecompany_id, route_id, departuredate, arrivaldate)values(5, 100, 0,10, 99.9, 1, 6, '2020-05-14 12:30', '2020-05-14 18:15');
insert into flight(id, quota, numberofsoldtickets, priceincreaseratio, ticketprice, airlinecompany_id, route_id, departuredate, arrivaldate)values(6, 110, 0,10, 159.9, 2, 8, '2020-04-14 12:30', '2020-04-14 18:15');

//PASSENGERS

insert into passenger(id, firstname, lastname, email) values (1, 'abdullah','kartal','akartal67@gmail.com');
insert into passenger(id, firstname, lastname, email) values (2, 'beyza','kartal','b3kartal@gmail.com');