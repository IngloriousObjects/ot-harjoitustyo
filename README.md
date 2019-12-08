# **MassiMatti**

MassiMatti on henkilökohtaisen talouden seurantaan tarkoitettu sovellus, jonka avulla käyttäjä voi pitää kirjaa menoistaan ja tuloistaan sekä tarkastella kirjaamiaan tapahtumia valitsemallaan ajanjaksolla niin tekstimuodossa kuin graafisestikin. Sovellukseen voi rekisteröityä useampi käyttäjä, jotka voivat lisätä ja seurata omia tapahtumiaan.

## **Dokumentaatio**

[Käyttöohje](https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

## **Releaset**
[Viikko 5](https://github.com/InglouriousObjects/ot-harjoitustyo/releases/tag/Viikko5)

## **Komentorivitoiminnot**

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _MassiMatti-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```
Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
