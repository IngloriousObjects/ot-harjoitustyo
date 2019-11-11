# Vaatimusmäärittely 

## Sovelluksen tarkoitus

MassiMatti on henkilökohtaisen talouden seurantaan tarkoitettu sovellus, jonka avulla käyttäjä voi pitää kirjaa menoistaan ja tuloistaan sekä tarkastella kirjaamiaan tapahtumia valitsemallaan ajanjaksolla niin tekstimuodossa kuin graafisestikin. Sovellukseen voi rekisteröityä useampi käyttäjä, jotka voivat lisätä ja seurata omia tapahtumiaan.

## Käyttäjät

Sovelluksessa on alkuvaiheessa ainoastaan yksi käyttäjätyyppi eli normaali käyttäjä. Jatkossa sovellukseen mahdollisesti lisätään pääkäyttäjä laajemmin oikeuksin.

## Suunnitellut toiminnallisuudet

### Kirjautuminen / Rekisteröityminen

* Sovellus aukeaa kirjautumisnäkymään, josta käyttäjä voi kirjautua sisään järjestelmään käyttäjänimellään ja salasanallaan. Mikäli käyttäjällä ei vielä ole luotuna tunnuksia, kirjautumisnäkymästä siirrytään painikkeella rekisteröintinäkymään 
  * Rekisteröintinäkymässä käyttäjä luo tunnukset antamalla uniikin käyttäjänimen (vähintään 4 merkkiä) ja salasanan (vähintään 8 merkkiä). Painikkeella tiedot talletetaan ja siirrytään takaisin kirjautumisnäkymään
  
### Järjestelmässä

Järjestelmän päänäkymässä käyttäjä voi:
  * lisätä uuden tapahtuman
    * päänäkymästä siirrytään tapahtumanäkymään ja lisäyksen ((pvm, tyyppi(meno/tulo), summa, kategoria, kuvaus) jälkeen takaisin           päänäkymään
  * lisätä uuden kategorian
    * päänäkymästä siirrytään kategorianäkymään (lisätään kategoria) ja lisäyksen jälkeen takaisin päänäkymään
  * listata tapahtumat
    * päänäkymästä siirrytään listausnäkymään, jossa valitaan ajanjakso, jonka perusteella listataan tulot ja menot vanhimmasta uusimpaan sekä ilmoitetaan niiden erotus erillisessä tulosnäkymässä, joka suljetaan painikkeella
    * paluu päänäkymään
  * Tarkastella tuloja ja menoja kategorioittain
    * päänäkymästä siirrytään kategorian tarkastelunäkymään, jossa valitaan ajanjakso, jonka perusteella uuteen näkymään piirretään pylväskaavio, jossa x-akselilla on kategoriat ja y-akselilla euromäärät. Kaavionäkymä suljetaan painikkeella.
    * paluu päänäkymään
 * Tarkastella tuloja ja menoja kuukausitasolla
    * päänäkymästä siirrytään kuukausitason tarkastelunäkymään, jossa valitaan ajanjakso, jonka peruteella uuteen näkymään piirretään ryhmitelty pylväskaavio tulot ja menot palkein, jossa x-akseli kuvaa aikaa ja y-akseli euromäärää. Kaavionäkymä suljetaan painikkeella
    * paluu päänäkymään
    
 ## Jatkokehitysmahdollisuudet
    
   * Pääkäyttäjän, joka voi mm. hallita ja siivota tietokantaa laajemmilla oikeuksilla, lisääminen
   * Sisäänkirjautumisen tietoturvan parantaminen. Nyt kirjautuminen on tarkoitus toteuttaa vain kosmeettisesti
   * Dataa voidaan esittää monipuolisemmin
   * Käyttäjälle mahdollisuus asettaa sovellukseen taloudellisia tavoitteita, jolloin sovellus on muokattavissa enemmän budjettisovellusta vastaavaksi pelkän taloudellisen seurannan sijaan.
   * Tietokannan skeeman laajentaminen, sillä nyt esim. käyttäjät lisäävät ja käyttävät yhteisiä kategorioita
