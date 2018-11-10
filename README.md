[![Build Status](https://travis-ci.org/stingray-PORFESR2017/Provider_di_Servizi.svg?branch=master)](https://travis-ci.org/stingray-PORFESR2017/Provider_di_Servizi)

# Provider_di_Servizi_-PRV-
Provider di Servizi (PRV) del Progetto della Regione Toscana SmarT INtelliGent RAilwaY (STINGRAY) POR FESR Toscana 2014-2020
ASSE 1 - AZIONE 1.1.5 – Sub-azione a1 “Progetti strategici di ricerca e sviluppo" (Bando 1) - Decreto 24.05.2017 n. 7165


## Partners of Stingray

|  #  | Name                                         | Short name    | Country     | 
| --- | -------------------------------------------- | ------------- | ----------- | 
|  1  | ECM                                          | ECM           | Pistoia     | 
|  2  | CONSIGLIO NAZIONALE DELLE RICERCHE           | ISTI-CNR      | Pisa        | 
|  3  | Dipartimento di Ingegneria dell'Informazione | UNIFI         | Firenze     | 
|  4  | Dipartimento di Ingegneria dell'Informazione | UNIPI         | Pisa        | 
|  5  | DMG Engineering s.r.l.                       | DMG           | Pistoia     | 
|  6  | ELFI s.r.l.                                  | ELFI          | Pistoia     | 
|  7  | C.T. Elettronica S.r.l.                      | CTE           | Firenze     | 

## Information

Information   | Value
------------- | --------
Component     | Provider di Servizi (PRV)
Partner       | ISTI-CNR
WP            | 4
Responsible   | Giorgio O. Spagnolo <spagnolo at isti.cnr.it>
CoResponsible | Stefania Gnesi  <gnesi at isti.cnr.it>
Roadmap       | 
Wiki          | 

# The platform
## Main Dependencies
Be sure the following tools have been installed on your system before to start :
 * git
 * maven
 * curl
 * unzip
 * build-essential (in Ubuntu, or similar)
 * Java 8
 * Bash Shell

## Build
First of all, clone the repository.

```
git clone https://github.com/stingray-PORFESR2017/Provider_di_Servizi.git
```

Then, once cloned, you can trigger a build with the `build` script in the root directory.
```
./build
```

## Run it!
After the build.  You should be able to run the platform with the
following command

```
bash launch start
```

You can also stop it with the following command.
```
bash launch stop
```
or restart it (it will stop every component then start it again)
```
bash launch restart
```

<!---Once the platform is started, access it on `http://localhost:9090/xwiki` in your web-browser.-->

## Components
PRV platform is a set of components, each one is in an independent
directory.  To know the exact list of components in the platform, you can look
into the `components` file.
