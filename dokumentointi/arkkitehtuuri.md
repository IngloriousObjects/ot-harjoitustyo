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
Kun kirjautumis- ja rekisteröitymisnäkymässä on syötekenttään kirjoitettu käyttäjätunnus sekä salasana ja painetaan loginButton-painiketta etenee sovelluksen kontrolli seuraavasti:
![](https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/sekvenssi_loginMM.png)
LoginButton-painiketta kuunteleva tapahtumankäsittelijä kutsuu UserControl-luokan loginUser-metodilla syötteenä käyttäjän syöttämät käyttäjätunnus ja salasana. UserController taasen kutsuu UserDao-rajapinnan read-metodia, joka lukee tietokannasta DatabaseUserDao-luokan kautta, onko käyttäjätunnus olemassa. Mikäli on palauttaa read-metodi käyttäjä-olion userControllille ja userController palauttaa totuusarvon true käyttöliittymälle. Käyttöliittymä siirtyy kirjautumisnäkymästä sovelluksen päänäkymään.

## Uuden käyttäjän luominen
Kun kirjautumis- ja rekisteröitymisnäkymässä on syötekenttään kirjoitettu käyttäjätunnus sekä salasana ja painetaan registerUserButton-painiketta etenee sovelluksen kontrolli seuraavasti:
![](https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/sekvenssi_register.png)
CreateNewUser-painiketta kuunteleva tapahtumankäsittelijä kutsuu UserControl-luokkaa checkUserName- ja checkPassword-metodilla. Mikäli molemmat edelliset metodit palauttavat käyttöliittymälle totuusarvon true, kutsuu käyttöliittymä UserControl-luokan createUser-metodia, joka saa parametreina käyttäjän antamat käyttäjätunnuksen ja salasanan. Edellinen metodi kutsuu edelleen UserDao-rajapinnan kautta DatabaseUserDaoa, joka tarkistaa read-metodilla onko käyttäjä jo talletettu tietokantaan. Jos ei ole, palauttaa read-metodi arvon null, jolloin taas UserController-luokka kutsuu UserDao-rajapinnan creatUser-metodia parametrina uusi käyttäjä-olio. CreateUser-metodi palauttaa onnistuessaan käyttäjä-olion ja käyttöliittymä saa USerControl-luokan metodilta totuusarvon true, jolloin käyttöliittymä ilmoittaa onnistuneesta rekisteröinnistä Alert-oliolla.

## Muut toiminnalisuudet
Sovelluksen kaikissa toiminnoissa toimii edellä kuvatut periaatteet vastaavasti. Käyttöliittymästä kutsutaan sovelluslogiikaa, joko käyttäjän, tapahtuman tai kategorian osalta. Samoin sovelluslogiikan kutsuu DAO-luokkia käyttäjään, tapahtumiin ja kategoiriohin liittyen, mikäli toiminnossa tulee lukea tai kirjoittaa tietoa tietokannasta/tietokantaan.

