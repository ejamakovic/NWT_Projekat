# Key Card Management System

“Key Card Management System” obuhvata dizajniranje i implementaciju sistema za upravljanje i nadzor pristupa prostorijama putem kartica. Ciljana publika su firme sa jednim ili više odjela, tj. unutrašnjih grupa, pri čemu bi imali mogućnost kreiranja računa za role: administrator, menadžer i zaposlenik. Pri tome, administrator ima potpunu kontrolu i uvid u sistem, dok menadžeri nadgledaju i kontrolišu sistem samo za svoje skupine zaposlenika. 

## Članovi tima:

 - Mirza Hadžić
 - Dženis Kajević
 - Ermin Jamaković

## Backend aplikacije
Mikroservisi koji čine backend aplikacije:
1. Config Server
2. Eureka Service
3. Api Gateway
4. System Events
5. Request Service
6. Room Service
7. Permission Service

## Frontend aplikacije

Link na repozitorij na frontend: https://github.com/mhadzic1/NWT-FE.

Potrebno isti skinuti i postaviti u isti folder zajedno sa backend-om aplikacije.

## Pokretanje projekta

Potrebno je imati instaliran Docker Desktop. Prvo se treba kreirati mreža za kontejnere komandom:

```
docker create network nwt_network
```

Nakon ovoga, projekat se može pokrenuti sljedećom komandom:
```
docker-compose up --build
```
Ovo će raditi dok se ne ugasi hostana baza

Za pokretanje svega lokalno potrebno je izvršiti sljedeću komandu
```
docker-compose -f docker-compose-local.yml up
```

## Video demonstracija aplikacije

Link na video: https://youtu.be/sVxCvKuR13o.
