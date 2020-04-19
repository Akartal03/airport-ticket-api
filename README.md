## BİLETLEME UYGULAMASI

- Havayolları biletleme sistemi için bir back-end servis uygulaması

- Havayolu şirketi eklenebilir, aranabilir

- Rota şirketi eklenebilir, aranabilir

- Havayolu şirketine uçuş tanımlanabilir, aranabilir

- Bilet satın alınabilir, bilet numarası ile arama yapılabilir ve bilet iptali
  yapılabilir
  
- Herbir bilet satışında uçuş kontenjanının %10, %20, %30 ... kontenjanları
 dolduğunda bilet fiyatı %10 artış gösterir





#### Detaylar
- Spring boot, Java 11, postgresql, maven, hibernate, jpa ve Restful Web Servic(JSON) teknolojileri kullanılmıştır.

- Docker kullanılarak yükleme işlemi yapılabilir.

#### Dockerize

Adımları;

- jar dosyası oluşturma : mvn clean install
- docker image oluşturma : docker build -t ticketapi-img-1 .
- container başlatma : docker run --name ticketapi --net=host ticketapi-img-1

#### Veritabanı Modeli

6 tablodan oluşmaktadır.

- FLIGHT
- TICKET
- PASSENGER
- ROUTE
- AIRPORT
- AIRLINECOMPANY

#### Örnek İşlemler

###  Listeleme işlemleri (GET)

- http://localhost:8080/api/v1/airportCompanies/
- http://localhost:8080/api/v1/airports
- http://localhost:8080/api/v1/flights
- http://localhost:8080/api/v1/passengers
- http://localhost:8080/api/v1/routes
- http://localhost:8080/api/v1/tickets

### Veritabanına Tablo Ekleme İşlemleri (POST)

 http://localhost:8080/api/v1/airportCompanies/add
-   {
        "name": "ONUR AIR"
    }
    
 http://localhost:8080/api/v1/airports/add
 - {
     "iataCode": "BLC",
     "name": "Bali Airport",
     "countryIsoCode": "CM"
    }
    
http://localhost:8080/api/v1/flights/add
- {       "quota": 1000,
          "ticketPrice": 129.00,
          "routeId": 3,
          "departureDate": "2020-05-14T12:30:00",
          "arrivalDate": "2020-05-14T18:15:00",
          "airlineCompanyId": 3
      }
      
 http://localhost:8080/api/v1/passengers/add
-  {
          "firstName": "serdar",
          "lastName": "ata",
          "email": "akar7@gmail.com"
      }
      
 http://localhost:8080/api/v1/routes/add
- {  "fromId": 1,
      "toId": 3
     }

 http://localhost:8080/api/v1/tickets/buy

- {  "passengerId": 1,
      "flightId": 3
    }
    
 http://localhost:8080/api/v1/tickets/cancel/{ticketNumber}

### Search İşlemleri (GET)

- http://localhost:8080/api/v1/airportCompanies/{id}
- http://localhost:8080/api/v1/airports/{id}
- http://localhost:8080/api/v1/flights/{id}
- http://localhost:8080/api/v1/passengers/{id}
- http://localhost:8080/api/v1/routes/{id}
- http://localhost:8080/api/v1/flights/companies/{airlineCompanyId}

### Gelişmiş Search İşlemleri

- AND  : ,
- OR   : ;

- EQUAL : ==
- NOT_EQUAL : !=
- GREATER_THAN : >
- GREATER_THAN_OR_EQUAL : >=
- LESS_THAN : <
- LESS_THAN_OR_EQUAL : <=
- IN : =in=
- NOT_IN : =out=


### Search Örnekleri

uçuş numarası 100100' e eşit olan uçuşlar
- http://localhost:8080/api/v1/flight?search=flightNumber==100100

fiyatı 100 den büyük ve kontenjanı 120 den küçük uçuşlar 
- http://localhost:8080/api/v1/flight?search=ticketPrice>100;quota<120

namei "Dubai" harfi ile başlayan airports
- http://localhost:8080/api/v1/airport?search=name==Dubai*

name içinde "AIR" geçen ve countryIsoCode AE olan airports
- http://localhost:8080/api/v1/airport?search=name==*Air*;countryIsoCode==AE

name içinde "AIR" geçen veya countryIsoCode AE olan airports
- http://localhost:8080/api/v1/airport?search=name==*Air*,countryIsoCode==AE