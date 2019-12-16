# Rakenne
Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:
![](https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/rakenne_pakkaus.png)

Pakkaus massimatti.ui sisältää JavaFX:llä toteutetun käyttöliittymän massimatti.domain sovelluslogiikan ja massimatti.dao tietojen pysyväistallennuksesta vastaavan koodin.

# Käyttöliittymä

# Sovelluslogiikka
![](https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/rak_ark_v2.png)

Luokat User,Entry ja Category muodostavat sovelluksen loogisen datamallin kuvaten järjestelmän käyttäjää, käyttäjän luomia tapahtumia sekä käyttäjien luomia kategorioita. Sovelluslogiikan luokat UserController, EntryController ja CategoryController vastaavat taasen kaikista käyttöliittymän tarvitsemista sovellustoiminnoista.

Daopakkauksen luokat DatabaseUserDao, DatabaseEntryDao ja DatabaseCategoryDao toteuttava rajapinnat UserDao, EntryDao ja CategoryDao. Edellä mainituttujen rajapintojen toteuttavat luokat siis mahdollistavat pääsyn käyttäjiin, tapahtumiin ja kategorioihin. Luokkien toteutus injektoidaan sovelluslogiikalle konstruktorikutsun yhteydessä.

# Tietojen pysyväistallennus

# Päätoiminnnallisuudet

## Kirjautuminen
![](https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/sekvenssi_loginMM.png)

## Uuden käyttäjän luominen
![](https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/sekvenssi_register.png)
