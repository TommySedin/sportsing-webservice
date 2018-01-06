# sportsing-webservice

Detta projekt är uppsatt att använda Vagrant + Docker.
- git clone
- mvn package
- vagrant up
- http://localhost:18080/webservice/matches

Detta projekt öppnar även upp portar för remote debugging. För att sätta upp Eclipse:
Run -> Debug configurations
Markera entryt "Remote Java applications" och tryck "New configuration"-knappen
Ge den ett vettigt Name, välj rätt Project
Connection type: Standard (Socket attach)
Host: localhost
Port: 19009

Nu efter du startat med "vagrant up" kan du trycka Debug för att koppla mot applikationen inne i Docker.

