# Modulo SIM 

Nell’ambito del progetto STINGRAY, Il modulo dei **S**ervizi di **I**nfo**M**obilità (SIM) si prefigge l’obiettivo di fornire informazioni ad utenti *viaggiatori* circa i mezzi disponibili nei dintorni di una smart station, oltre che a permettere ad utenti *amministratori* operazioni CRUD sulle stesse ed entità correlate.

In particolare il modulo SIM permette all’utente *viaggiatore* di integrare i dati relativi al sistema ferroviario forniti dal modulo per i **S**ervizi per l’**U**tente e la **F**errovia (SUF) con le informazioni accessorie relative a:
* sistemi di mobilità accessori presenti nei dintorni di una stazione ferroviaria;
* meteo.

Il Modulo SIM consta di:
* un'applicazione di back-end **Java Enterprise Edition**, responsabile del recupero e della gestione dell’informazione e dell’esposizione di una suite di servizi REST.
* un’applicazione di front-end **Angular** che, interagendo con l’applicazione di back-end, permette di presentare l’informazione in maniera chiara ed essenziale all’utente finale.

# sim-frontend

L'applicazione **sim-frontend** rappresenta l'interfaccia grafica del modulo SIM e ha quindi il compito di permettere all'utente di interagire con le informazioni fornite dall'applicazione di back-end [sim-backend](https://github.com/STLAB-DINFO/sim-backend).
Si individuano due classi di utenza:
* i *viaggiatori* che possono visualizzare l'informazione offerta riguardo alle opportunità di mobilità disponibili nei dintorni di *smart station* e alle condizioni meteo;
* gli *amministratori* che possono, oltre che visualizzare, creare, modificare ed eliminare *smart station* e le entità di interesse correlate. All'interno di questa classe, si individuano dei *super amministratori* con poteri estesi rispetto agli amministratori semplici; essi possono infatti autorizzare quest'ultimi su determinate *municipalità* e su determinati *servizi di informobilità*.

SimFrontend è stata realizzata grazie al framework [Angular](https://angular.io/), generata tramite [Angular CLI](https://github.com/angular/angular-cli) versione 8.3.20 ed opera scambiando dati in formato **JSON** con l'applicazione di back-end invocando i servizi REST esposti da quest'ultima.

## Requisiti

* Applicazione di back-end [sim-backend](https://github.com/STLAB-DINFO/sim-backend) in esecuzione all'indirizzo specificato (si vedano le sezioni **Server di sviluppo** o **Build**)
* **Node.js** > **12.18.x** (npm > 6.14.x) ([Download Node.js](https://nodejs.org/en/download/))
* **Angular CLI** > **8.3.x** (si veda [Guida all'installazione Angular CLI](https://cli.angular.io/))
* **(SOLO PER BUILD)** Server per esecuzione della build (es. [lite-server](https://www.npmjs.com/package/lite-server))

## Installazione

1. Da terminale, spostarsi nella cartella locale in cui si è clonato il presente repository ed eseguire il comando `npm install`;
2. Sceliere una tra le due opzioni:
    * Eseguire l'applicazione in modalità di sviluppo (si veda sezione **Server di Sviluppo**) ***oppure***
    * Ottenere una build eseguibile ed eseguirla su un server (si veda sezione **Build**).

### Server di sviluppo

Eseguire da terminale il comando `ng serve` per lanciare il server di sviluppo. Aprire un browser all'indirizzo `http://localhost:4200/`.

In questa modalità, l'applicazione di back-end deve essere in esecuzione all'indirizzo specificato nel file [src/environments/environment.ts](https://github.com/STLAB-DINFO/sim-frontend/blob/master/src/environments/environment.ts) (default [http://localhost:8080/sim-backend-1.0](http://localhost:8080/sim-backend-1.0)).


### Build

Eseguire da terminale il comando `ng build` per costruire l'artefatto eseguibile del progetto. Gli artefatti di build verranno salvati nella cartella `dist/`.

In questa modalità, l'applicazione di back-end deve essere in esecuzione all'indirizzo specificato nel file [src/environments/environment.ts](https://github.com/STLAB-DINFO/sim-frontend/blob/master/src/environments/environment.ts) (default [http://localhost:8080/sim-backend-1.0](http://localhost:8080/sim-backend-1.0)).
Per ottenere una build di produzione, eseguire da terminale **invece** il comando` ng build --prod`; in questo caso l'applicazione di back-end deve essere in esecuzione all'indirizzo specificato nel file [src/environments/environment.prod.ts](https://github.com/STLAB-DINFO/sim-frontend/blob/master/src/environments/environment.prod.ts) (default [http://localhost:8080/sim-backend-1.0](http://localhost:8080/sim-backend-1.0)).

La build ottenuta può essere quindi eseguita in un server predisposto per lo scopo(si veda sezione **Requisiti**).

## Supporto aggiuntivo

Per ulteriore supporto su Angular CLI eseguire da terminale il comando `ng help` o consultare [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
