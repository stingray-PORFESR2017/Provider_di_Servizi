#!/bin/sh
cd $TRAVIS_BUILD_DIR/Servizi_Utente_Ferrovia/Servizio_Supervisione_Stazione/
mvn jacoco:report coveralls:report
