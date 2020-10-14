# Modulo SIM

Nell’ambito del progetto STINGRAY, Il modulo dei **S**ervizi di **I**nfo**M**obilità (SIM) si prefigge l’obiettivo 
di fornire informazioni ad utenti *viaggiatori* circa i mezzi disponibili nei dintorni di una *smart station*, oltre 
che a permettere ad utenti *amministratori* operazioni CRUD sulle stesse ed entità correlate.

In particolare il modulo SIM permette all’utente *viaggiatore* di integrare i dati relativi al sistema ferroviario
 forniti dal modulo per i **S**ervizi per l’**U**tente e la **F**errovia (SUF) con le informazioni accessorie 
 relative a:
* sistemi di mobilità accessori presenti nei dintorni di una stazione ferroviaria;
* meteo. 

Il Modulo SIM consta di: 
* un'applicazione di back-end **Java Enterprise Edition**, responsabile del 
recupero e della gestione dell’informazione e dell’esposizione di una suite di servizi REST.
* un’applicazione di front-end **Angular** che, interagendo con l’applicazione di back-end, permette di presentare 
l’informazione in maniera chiara ed essenziale all’utente finale.


# sim-backend

Il modulo ***sim-backend*** si occupa di gestire le informazioni relative alle *smart station* ed i servizi di infomobilità, 
nonché degli utenti amministratori del sistema o di singoli servizi e delle municipalità dove le *smart station* sono 
locate. Si interfaccia con un **DBMS** per la persistenza delle informazioni su un database relazionale ed espone le 
proprie funzionalità nella forma di servizi REST, fruiti poi dall’applicazione di front-end. 

Il modulo, inoltre, si interfaccia col componente **SUF** per l’ottenimento delle informazioni relative al meteo nelle 
varie *smart station* e l’elenco dei treni in partenza dalle stazioni ferroviarie. Allo stesso modo, tramite servizi 
REST, si interfaccia con altre piattaforme esterne di infomobilità per ottenere la lista dei mezzi disponibili per 
ognuna di esse.

## Installazione

In questa sezione riportiamo i requisiti ed i passi necessari all’avviamento del modulo sim-backend.

### Requisiti

1. Una macchina server, con qualsiasi sistema operativo (*Windows Server*, *Linux*) 
2. Installazione di un **J**ava **D**evelopment **K**it, [nella sua versione almeno 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
3. Installazione di un **D**ata**B**ase **M**anagement **S**ystem di tipo relazionale (es. MySQL, [MariaDB](https://mariadb.org/download/))
4. Installazione di un application server compatibile (es. [*Wildfly*](https://www.wildfly.org/downloads/))
5. [Configurazione del connettore **JDBC** nell’installazione di _Wildfly_](https://synaptiklabs.com/posts/adding-the-mysql-jdbc-driver-into-wildfly/)
6. Il war dell’applicativo: _sim-backend-1.0.war_
 
Lo sviluppo dell’applicativo è avvenuto con la seguente configurazione:
* Microsoft Windows 10 Home
* Java JDK 13.0.1
* MySQL 5.7
* Wildfly 16.0.0

### Passi necessari

1. Tramite utente root, [creare un nuovo database](https://www.digitalocean.com/community/tutorials/how-to-create-and-manage-databases-in-mysql-and-mariadb-on-a-cloud-server) 
nel **DBMS**, di nome _stingray_
2. Importare nel database _stingray_ il dump in `\sim-backend\dump\dump-stingray.sql`
3. Tramite utente root, [creare un nuovo utente](https://www.digitalocean.com/community/tutorials/how-to-create-a-new-user-and-grant-permissions-in-mysql)
 nel **DBMS**, con nome e password specificati nel file 
`\sim-backend\src\main\webapp\WEB-INF\sim-backend-ds.xml`, avente pieni diritti di utilizzo sul database stingray 
appena creato;
4. Avviare il server e rilasciare l'applicazione.
