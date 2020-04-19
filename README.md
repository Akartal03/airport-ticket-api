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

Listeleme işlemleri (GET)

- http://localhost:8080/api/v1/airportCompanies/
- http://localhost:8080/api/v1/airports
- http://localhost:8080/api/v1/flights
- http://localhost:8080/api/v1/passengers
- http://localhost:8080/api/v1/routes
- http://localhost:8080/api/v1/tickets

Veritabanına Tablo Ekleme İşlemleri (POST)

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
    
- http://localhost:8080/api/v1/flights/add
- http://localhost:8080/api/v1/passengers/add
- http://localhost:8080/api/v1/routes/add
- http://localhost:8080/api/v1/tickets/add
