# Testausdokumentti
Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus
### Sovelluslogiikka
Sovelluslogiikan sisältävän massimatti.domain-pakkauksen luokkia ja sovelluslogiikkaa testaavat ennen kaikkea UserControllerTest, EntryControllerTest ja CategoryControllerTest testiluokat, joiden testit pyrkivät jäljittelemään sovelluksen toimintoja. UserControllerTest- ja EntryControllerTest-luokat käyttävät integraatiotesteihin DAO-rajapintojensa keskusmuistitoteuksia FakeUserDao- ja FakeEntryDao-luokkia.

### DAO-luokat
Dao-luokkia on testatttu DatabaseUserDaoTest-, DatabaseEntryDaoTest ja DatabaseCategoryDaoTest-luokilla. Kyseisissä testeissä on luotu väliaikainen tietokanta testikansioon, joka poistetaan automaattisesti testauksen lopuksi.

### Testauskattavuus
Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 94% ja haarautumakattavuus 91%

[]!(https://github.com/InglouriousObjects/ot-harjoitustyo/blob/master/dokumentointi/kuvat/testidokumenttiv2.png)
