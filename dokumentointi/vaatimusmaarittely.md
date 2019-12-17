# Vaatimusmäärittely 

## Sovelluksen tarkoitus

MassiMatti on henkilökohtaisen talouden seurantaan tarkoitettu sovellus, jonka avulla käyttäjä voi pitää kirjaa menoistaan ja tuloistaan sekä tarkastella kirjaamiaan tapahtumia valitsemallaan ajanjaksolla niin tekstimuodossa kuin graafisestikin. Sovellukseen voi rekisteröityä useampi käyttäjä, jotka voivat lisätä ja seurata omia tapahtumiaan.

## Käyttäjät

Sovelluksessa on alkuvaiheessa ainoastaan yksi käyttäjätyyppi eli normaali käyttäjä. Jatkossa sovellukseen mahdollisesti lisätään pääkäyttäjä laajemmin oikeuksin.

## Suunnitellut toiminnallisuudet

### Kirjautuminen / Rekisteröityminen

* Sovellus aukeaa kirjautumisnäkymään, josta käyttäjä voi kirjautua sisään järjestelmään käyttäjänimellään ja salasanallaan.  Mikäli käyttäjällä ei vielä ole luotuna käyttäjätunnuksia, kirjautumisnäkymästä siirrytään rekisteröi-painikkeella rekisteröintinäkymään 
  * Rekisteröintinäkymässä käyttäjä luo tunnukset antamalla uniikin käyttäjänimen (vähintään 4 merkkiä) ja salasanan (vähintään 8 merkkiä). Painikkeella tiedot talletetaan ja siirrytään takaisin kirjautumisnäkymään. Käyttäjä voi myös palata tallettamatta tietojaan kirjautumisnäkymään painikkeella 'palaa'.
  
### Järjestelmässä

Järjestelmän päänäkymässä käyttäjä voi:
  * lisätä uuden tapahtuman
    * päänäkymästä avautuu 'Lisää tapahtuma'-painikkeella erillinen tapahtumanäkymä ja lisäyksen ((pvm, tyyppi(meno/tulo), summa, kategoria) jälkeen käyttäjä voi lisätä uuden tapahtuman tai poistua näkymästä sulkemalla ikkunan.
  * poistaa tapahtuman
    * päänäkymästä avautuu 'Poista tapahtuma'-painikkeella erillinen näkymä, jossa käyttäjän kaikki tapahtuma ovat listana. Käyttäjä voi valita tapahtumia yksitellen ja poistaa ne. Näkymästä poistutaan sulkemalla ikkuna.
  * lisätä uuden kategorian
    * päänäkymästä valitsemalla 'Lisää kategoria' avautuu erillinen kategorianäkymä, jossa käyttäjä voi lisätä uuden kategorian. Lisäyksen jälkeen käyttäjä voi lisätä uuden kategorian tai poistua näkymästä sulkemalla ikkunan
  * listata tapahtumat
    * päänäkymästä avautuu 'Listaa tapahtumat' -painikkeella erillinen listausnäkymä. Käyttäjä voi valita listataanko kaikki tapahtumat vai tapahtumat halutulta ajanjaksolta. Tapahtumat listataan näkymään. Näkymästä voi poistua sulkemalla ikkunan.
    
  * tarkastella tapahtumia kategorioittain
    * päänäkymästä avautuu painikkeella 'Menot / Tulot kategorioittain näkymään, jossa valitaan joko käyttäjän haluama ajanjakso tai kaikki tapahtumat, jonka perusteella uuteen näkymään piirretään pylväskaavio, jossa x-akselilla on kategoriat ja y-akselilla euromäärät. Kaavion otsikossa tulostettuna saldo, joka on valittujen tapahtumien tulojen ja menojen erotus. Näkymästä voi poistua sulkemalla ikkunan
    
 * tarkastella tapahtumia menoja ja tuloja vertailemalla
    * päänäkymästä avautuu 'Menot/Tulot vertailu'-painikkeella uusi näkymä, jossa valitaan joko käyttäjän haluama ajanjakso tai kaikki tapahtumat, jonka peruteella uuteen näkymään piirretään ryhmitelty pylväskaavio tulot ja menot palkein, jossa x-akseli kuvaa menoja ja tuloja ja y-akseli euromäärää. Kaavion otsikossa on tulostettuna valittujen tapahtumien menojen ja tulojen erotus. Näkymästä voi poistua sulkemalla ikkunan
    
 * kirjautua ulos järjestelmästä
    
 ## Jatkokehitysmahdollisuudet
    
   * Pääkäyttäjän, joka voi mm. hallita ja siivota tietokantaa laajemmilla oikeuksilla, lisääminen
   * Sisäänkirjautumisen tietoturvan parantaminen. Nyt kirjautuminen on tarkoitus toteuttaa vain kosmeettisesti
   * Dataa voidaan esittää monipuolisemmin
   * Käyttäjälle mahdollisuus asettaa sovellukseen taloudellisia tavoitteita, jolloin sovellus on muokattavissa enemmän budjettisovellusta vastaavaksi pelkän taloudellisen seurannan sijaan.
   * Tietokannan monipuolistaminen, sillä nyt esim. käyttäjät lisäävät ja käyttävät yhteisiä kategorioita
   * Käyttöliittymää voi vielä muokata elegantimmaksi - tässä kurssissa käyttöliittymän koodi sai vähäisemmän roolin
   
