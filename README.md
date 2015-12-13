# endeca-reference-app

## TL;DR

My own spin on implementing an Oracle Endeca reference application built as an opportunity to learn the Endeca API.
Requires in lib folder following jar files:
* commons-lang3.3.1.jar
* endeca_navigation-6.4.0.jar
* jstl-1.2.jar

Example URL: http://localhost:8080/EndecaReferenceApp/EndecaServlet?N=0

Still in progress... Not all functionality is built out nor is code cleaned up.

## Goals

This app was built to learn how to use the Endeca API.  Initially a command line program was built.  That was moved to
a web app in order to more easily test different query URLs.

## Application Server

I run this application on JBoss EAP 6.4.0, but any application server should work.

## Endeca

The endeca_navigation-6.4.0.jar file needs to be added to the project and lib folder for this project to work.

This is tested against:
 * MDEX 6.4.1.2
 * Platform Services 6.1.3
 * CAS 3.1.1
 * JBoss 6.4
 * JDK 1.8_20

## Next Steps

This is still a work in progress and the remaining functionality around record aggregation and filtering needs to be built.

Add the following:
* log4j
* configuration settings in a property file
* code to build a war file
* add automated UI tests
