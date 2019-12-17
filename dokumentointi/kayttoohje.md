
# Käyttöohje

Lataa tiedosto [MassiMatti_v0.1-alpha.jar](https://github.com/InglouriousObjects/ot-harjoitustyo/releases/tag/Viikko5) sekä siihen liittyvät tiedostot _config.properties_ ja _categories.txt_.

## Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto _config.properties_, joka määrittää tietojen tallentamiseen liittyvän tietokannan. Käynnistettäessä sovellus, sovellus luo tietokannan, mikäli sitä ei ole jo aiemmin luotu, sen hetkiseen työkansioon nimellä massimatti.mv.db. Kun tietokantaan on tallennettu tietoa luodaan tietokannanhallintajärjestelmän toimesta työkansioon myös tiedosto nimellä massimatti.trace.db. 

Lisäksi ohjelma odottaa käynnistyshakemistosta löytyvän tiedoston categories.txt, jonka avulla ohjelma tietokannan luodessaan luo tietokantaan myös kategorioita valmiiksi.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar MassiMatti_v0.1-alpha.jar
```
## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

<img src="https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/loginv2.png" width="400">

Kirjautuminen onnistuu kirjoittamalla olemassaoleva käyttäjätunnus ja salasana syötekenttiin ja painamalla _kirjaudu_.

## Uuden käyttäjän luominen

Kirjautumisnäkymästä on mahdollista siirtyä uuden käyttäjän luomisnäkymään panikkeella _rekisteröidy_.

Uusi käyttäjä luodaan syöttämällä tiedot syötekenttiin ja painamalla _rekisteröidy_

<img src="https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/registerv2.png" width="400">

Jos käyttäjän luominen onnistuu, palataan kirjautumisnäkymään. Mikäli käyttäjä haluaa palata kirjautumisnäkymään ilman tunnuksen luontia, tämä tapahtuu painamalla _palaa_.

## Päänäkymä

Onnistuneen kirjautumisen jälkeen ohjelma avaa päänäkymän:

<img src="https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/paanakyma.png" width="400">

Päänäkymässä käyttäjä voi valita haluamansa tapahtumien lisäämiseen, poistamiseen tai näyttämiseen liittyvän toiminnon painikkeista sekä kirjautua ulos järjestelmästä _Kirjaudu ulos_ painikkeella.

# Lisää tapahtuma

Painamalla päänäkymässä painiketta _Lisää tapahtuma_ käyttäjälle avautuu päänäkymän rinnalle uusi näkymä:
<img src="https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/lisaatapahtuma.png" width="400">

Näkymässä käyttäjä voi lisätä uuden tapahtuman valitsemalla päivämäärän, tapahtuman tyypin, tapahtuman summan ja kategorian. Sovelluksessa on valittu pienimmäksi syötettäväksi summaksi 0.1 ja suurimmaksi 10000. Desimaaliluvun erottimena käytetään pistettä. Tapahtuman lisääminen tapahtuu painikkeella _Lisää_. Käyttäjä voi jatkaa tapahtumien lisäämistä tai poistua näkymästä sulkemalla ikkunan.

# Poista tapahtuma

Painamalla päänäkymässä painiketta _Poista tapahtuma_ käyttäjälle avautuu päänäkymän rinnalle uusi näkymä:
<img src="https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/poistatapahtuma.png" width="400">

Näkymässä käyttäjä voi poistaa tapahtumia yksi kerrallaan. Näkymään tulostuu listana kaikki tapahtumat ja tapahtuma poistetaan valitsemalla se listasta ja painamalla painiketta _Poista tapahtuma_. Toiminnon jälkeen ohjelma avaa vielä varmistusikkunan, jossa käyttäjä voi joko vahvistaa poiston tai peruuttaa sen. Käyttäjä voi jatkaa tapahtumien poistamista tai poistua näkymästä sulkemalla ikkunan.

# Lisää kategoria

Painamalla päänäkymässä painiketta _Lisää kategoria_ käyttäjälle avautuu päänäkymän rinnalle uusi näkymä:
<img src="https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/lisaakategoria.png" width="400">

Näkymässä käyttäjä voi lisätä ohjelmaan uusia kategorioita antamalla syötekenttään kategorian nimen ja painamalla painiketta _Lisää_. Kategoria lisätään tietokantaan, mikäli se täyttää muotovaatimukset. Kategorian nimi muutetaan isoiksi kirjaimiksi. Käyttäjä voi jatkaa kategorioiden lisäämistä tai poistua näkymästä sulkemalla ikkunan.

# Listaa tapahtumat

Painamalla päänäkymässä painiketta _Lista tapahtumat_ käyttäjälle avautuu päänäkymän rinnalle uusi näkymä:
<img src="https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/listaatapahtumat.png" width="400">

Näkymässä käyttäjä voi listata tekstimuotoon tapahtumiaan. Käyttäjä voi valita haluamansa ajanjakson päivämäärävalikoista ja tulostaa valitun ajanjakson tapahtumat painamalla painiketta _Valitulta ajanjaksolta_ tai käyttäjä voi tulostaa kaikki tapahtumat painikkeella _Kaikki_. Tapahtumista näytetään päivämäärä, kategoria ja summa. Mikäli tapahtuma on meno, on summa negatiivinen, ja tapahtuman ollessa tulo, on summa positiivinen. Käyttäjä voi poistua näkymästä sulkemalla ikkunan.

# Menot / Tulot kategorioittain

Painamalla päänäkymässä painiketta _Menot / Tulot kategorioittain_ käyttäjälle avautuu päänäkymän rinnalle uusi näkymä:
<img src="https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/menottulotkategorioittain.png" width="400">

Näkymässä käyttäjä voi tarkastella tapahtumiaan pylväskaaviossa jaoteltuina kategorioihin, jotka ovat ilmaistu eri väreinä riippuen ovatko ne tuloja vai menoja. Mikäli kategorian sisällä on sekä tulo- että menotapahtumia on kategorian tapahtumat summattu ja se on ilmaistu menona, mikäli summa on negatiivinen ja tulona, jos summa on positiivinen. Otsikkokentässä tulostettu summa (saldo) on valittujen tulojen ja menojen erotus.

Käyttäjä voi valita tarkasteltavat tapahtumat joko valitsemalla halutut päivät päivämäärävalikoista ja painamalla painiketta _Valitulta ajanjaksolta_ tai tarkastella kaikkia tapahtumiaan painamalla painiketta _Kaikki_. Käyttäjä voi poistua näkymästä sulkemalla ikkunan.

# Menot / Tulot vertailu

Painamalla päänäkymässä painiketta _Menot / Tulot vertailu_ käyttjälle avautuu päänäkymän rinnalle uusi näkymä:
<img src="https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/menottulotvertailu.png" width="400">

Näkymässä käyttäjä voi vertailla tapahtumien yhteismenoja ja -tuloja pylväskaavion avulla. Pylväskaavioon tulostuu kaksi palkkia: Menot ja Tulot. Otsikkokenttään tulostuu summa (saldo), joka on tulojen ja menojen erotus pyöristettynä kokonaisluvuksi.

Käyttäjä voi valita tarkasteltavat tapahtumat joko valitsemalla halutut päivät päivämäärävalikosta ja painamalla painiketta _Valitulta ajanjaksolta_ tai tarkastella kaikkia taphtumiaan painamalla painiketta _Kaikki_. Käyttäjä voi poistua näkymästä sulkemalla ikkunan.


 

