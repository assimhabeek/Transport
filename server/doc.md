#Facture

###GET

```
chttp://localhost:8080 http://localhost:8080/rest/facture
```

```
chttp://localhost:8080 http://localhost:8080/rest/facture/1
```

###POST
```
chttp://localhost:8080 --header "Content-Type: application/json" \
     --request POST \
     --data '{"id":0,"dateEmission":"2018-12-13","total":25000.0,"reglee":true,"rReservation":  {"id": 3, "dateReservation": "2018-12-11","rVoyageurs": [], "rTransports": [],"rFacture": null,"rAdresse": {"id": 3,"rue": "Ritage Moul","codePostal": "Constantine","ville": "25000"}}}' \
     http://localhost:8080/rest/facture

```


###PUT

```
chttp://localhost:8080 --header "Content-Type: application/json" \
     --request PUT \
     --data '{"id":4,"dateEmission":"2018-12-13","total":25000.0,"reglee":true,"rReservation":  {"id": 3, "dateReservation": "2018-12-11","rVoyageurs": [], "rTransports": [],"rFacture": null,"rAdresse": {"id": 3,"rue": "Ritage Moul","codePostal": "Constantine","ville": "25000"}}}' \
     http://localhost:8080/rest/facture
```


###DELETE

```
chttp://localhost:8080 --header "Content-Type: application/json" \
     --request DELETE \
     --data '{"id":4,"dateEmission":"2018-12-13","total":25000.0,"reglee":true,"rReservation":  {"id": 3, "dateReservation": "2018-12-11","rVoyageurs": [], "rTransports": [],"rFacture": null,"rAdresse": {"id": 3,"rue": "Ritage Moul","codePostal": "Constantine","ville": "25000"}}}' \
     http://localhost:8080/rest/facture
```

#Available Entities
####Avion
```
http://localhost:8080/rest/avion
```

####Train
```
http://localhost:8080/rest/train
```
####Facture
```
http://localhost:8080/rest/facture
```
####Reservation
```
http://localhost:8080/rest/reservation
```
####Voyageur
```
http://localhost:8080/rest/voyaguer
```



