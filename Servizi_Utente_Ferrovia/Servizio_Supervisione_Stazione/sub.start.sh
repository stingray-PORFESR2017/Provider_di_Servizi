#!/bin/sh
mvn clean install
cp src/main/webapp/WEB-INF/beans.xml target/classes/META-INF/
mvn exec:java
