# Testausdokumentti
Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus
### Sovelluslogiikka
Sovelluslogiikan sisältävän massimatti.domain-pakkauksen luokkia ja sovelluslogiikkaa testaavat ennen kaikkea UserControllerTest, EntryControllerTest ja CategoryControllerTest testiluokat, joiden testit pyrkivät jäljittelemään sovelluksen toimintoja. UserControllerTest- ja EntryControllerTest-luokat käyttävät integraatiotesteihin DAO-rajapintojensa keskusmuistitoteuksia FakeUserDao- ja FakeEntryDao-luokkia.

### DAO-luokat
Dao-luokkia on testatttu DatabaseUserDaoTest-, DatabaseEntryDaoTest ja DatabaseCategoryDaoTest-luokilla. Kyseisissä testeissä on luotu väliaikainen tietokanta testikansioon, joka poistetaan automaattisesti testauksen lopuksi.

### Testauskattavuus
Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 94% ja haarautumakattavuus 91%. Testaamatta jäivät lähinnä toiminnalisuudet, jossa tietokanta heittää SQL-poikkeuksen.

![](https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/testidokumenttiv2.png)

## Järjestelmätestaus
Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi
Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md) kuvaamalla tavalla Linux-ympäristössä siten, että sovelluksen käynnistyshakemistossa on ollut käyttöohjeen kuvauksen mukainen config.properties-tiedosto.

Sovellusta on testattu luomalla käyttäjiä ja niihin liittyviä tapahtumia, sekä erikseen kategorioita ja käyttöliitymän kautta tutkimalla sovelluslogiikkaa. Tilanteet ovat varioineet tyhjästä tietokannasta jo olemassaolevaan.

### Toiminnallisuudet
Kaikki [vaatimusmäärittelydokumentissa](https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md) listatut toiminnalisuudet on käyty läpi. Kaikkien syötekenttien osalta on käytetty erilaisia syötteitä tyhjästä erkoismerkkeihin. 

## Sovellukseen jääneet laatuongelmat
'Menot / Tulot kategorioittain'-näkymässä, kun valittu ajanjakso ei sisällä tapahtumia, kasvattaa 'valitulta ajanjaksolta'-painikkeen painaminen X-akselin arvoskaalaa. Tämä ongelma ilmenee vain, kun valittu ajanjakso ei sisällä tapahtumia.
