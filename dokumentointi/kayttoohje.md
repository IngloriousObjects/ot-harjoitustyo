
# Käyttöohje

Lataa tiedosto [MassiMatti_v0.1-alpha.jar](https://github.com/InglouriousObjects/ot-harjoitustyo/releases/tag/Viikko5) sekä siihen liittyvä tiedosto _config.properties_.

## Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto _config.properties_, joka määrittää tietojen tallentamiseen liittyvän tietokannan. Käynnistettäessä sovellus, sovellus luo tietokannan, mikäli sitä ei ole jo aiemmin luotu, sen hetkiseen työkansioon nimellä massimatti.mv.db. Kun tietokantaan on tallennettu tietoa luodaan tietokannanhallintajärjestelmän toimesta työkansioon myös tiedosto nimellä massimatti.trace.db.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar MassiMatti_v0.1-alpha.jar
```
## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

<img src="https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/login.png" width="400">

Kirjautuminen onnistuu kirjoittamalla olemassaoleva käyttäjätunnus ja salasana syötekenttiin ja painamalla _kirjaudu_.

## Uuden käyttäjän luominen

Kirjautumisnäkymästä on mahdollista siirtyä uuden käyttäjän luomisnäkymään panikkeella _rekisteröidy_.

Uusi käyttäjä luodaan syöttämällä tiedot syötekenttiin ja painamalla _rekisteröidy_

<img src="https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/register.png" width="400">

Jos käyttäjän luominen onnistuu, palataan kirjautumisnäkymään. Mikäli käyttäjä haluaa palata kirjautumisnäkymään ilman tunnuksen luontia, tämä tapahtuu painamalla _palaa_.
