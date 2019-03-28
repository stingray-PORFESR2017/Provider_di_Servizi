#!/bin/sh
cd $TRAVIS_BUILD_DIR/Provider_di_Servizi/Servizi_Utente_Ferrovia/Servizio_Supervisione_Stazione/
mvn jacoco:report coveralls:report
