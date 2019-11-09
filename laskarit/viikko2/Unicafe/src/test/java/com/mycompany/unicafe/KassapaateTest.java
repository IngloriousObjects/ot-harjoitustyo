package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kelpoKortti;
    Maksukortti huonoKortti;
    

    @Before
    public void setUp() {

        kelpoKortti = new Maksukortti(400);
        huonoKortti = new Maksukortti(100);

        paate = new Kassapaate();
    }

    @Test

    public void paateOnOlemassa() {

        assertTrue(paate != null);
    }

    @Test
    public void kassassaOnAlussaRahaa() {

        assertEquals(100000, paate.kassassaRahaa()); 

/*Vaikka tehtävänanossa oli annettu arvoksi 1000, on testeissä kaytetty "typo-oletuksen" vuoksi alkusaldona lukua 100000
  Mikäli kyseessä ei ollut typo, olisi kassan saldon muutosta koskevien muiden testien oikeellisuus selvitettyä luomalla testiin muuttuja
  kassan silloisella saldolla, jota testissä olisi verrattu muuttuneeseen saldoon viimeisen esimerkin tapaan.
*/

    }

    @Test

    public void alussaLounaitaMyytyNolla() {

        int lounaita = paate.maukkaitaLounaitaMyyty() + paate.edullisiaLounaitaMyyty(); //..lounaitaMyyty-metodit olisi varmastikin parempi tarkistaa erikseen

        assertEquals(0, lounaita);

    }

    @Test
    public void kateisOstoToimiiEdullinenKassa() {

        paate.syoEdullisesti(250);
        assertEquals(100240, paate.kassassaRahaa());

    }

    @Test
    public void kateisOstoToimiiEdullinenVaihtoraha() {

        assertEquals(10, paate.syoEdullisesti(250));
    }

    @Test
    public void kateisOstoToimiiMaukasKassa() {

        paate.syoMaukkaasti(410);
        assertEquals(100400, paate.kassassaRahaa());

    }

    @Test
    public void kateisOstoToimiiMaukasVaihtoraha() {

        assertEquals(10, paate.syoMaukkaasti(410));
    }

    @Test
    public void lounaidenMaaraKasvaaEdullinen() {

        paate.syoEdullisesti(240);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void lounaidenMaaraKasvaaMaukas() {

        paate.syoMaukkaasti(400);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());

    }

    @Test
    public void syoEdullisestiEiRiittavastiRahaaKassa() {

        paate.syoEdullisesti(230);
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiEiRiittavastiRahaaKassa() {

        paate.syoMaukkaasti(390);
        assertEquals(100000, paate.kassassaRahaa());
    }

    @Test
    public void syoEdullisestiEiRiittavastiRahaaVaihtoraha() {

        assertEquals(200, paate.syoEdullisesti(200));
    }

    @Test
    public void syoMaukkaastiEiRiittavastiRahaaVaihtoraha() {

        assertEquals(350, paate.syoMaukkaasti(350));
    }

    @Test
    public void syoEdullisestiEiRiittavastiRahaaNolla() {

        assertEquals(0, paate.syoEdullisesti(0));
    }

    @Test
    public void syoMaukkaastiEiRiittavastiRahaaNolla() {

        assertEquals(0, paate.syoMaukkaasti(0));
    }

    @Test
    public void syoEdullisestiEiRiittavastiRahaaLounaitaMyyty() {

        paate.syoEdullisesti(200);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiEiRiittavastiRahaaLounaitaMyyty() {
        paate.syoMaukkaasti(300);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kortillaRahaaEdullinen() {

        assertTrue(paate.syoEdullisesti(kelpoKortti));

    }
    @Test
    public void kortillaRahaaMaukas() {

        assertTrue(paate.syoMaukkaasti(kelpoKortti));

    }
    @Test
    public void kortillaTarpeeksiSaldoaEdullinenLounaitaMyyty(){
        
        paate.syoEdullisesti(kelpoKortti);
        assertEquals(1,paate.edullisiaLounaitaMyyty());
    }
    @Test
    public void kortillaTarpeeksiSaldoaMaukasLounaitaMyyty() {

        paate.syoMaukkaasti(kelpoKortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test 
    public void kortillaEisaldoaRahamaaraEiMuutuEdullinen(){
        
        paate.syoEdullisesti(huonoKortti);
        
        assertEquals(100,huonoKortti.saldo());
    }
    @Test
    public void kortillaEisaldoaRahamaaraEiMuutuMaukas() {

        paate.syoMaukkaasti(huonoKortti);

        assertEquals(100, huonoKortti.saldo());
    }
    @Test
    public void kortillaEiSaldoaLounastaEiMyytyEdullinen(){
        paate.syoEdullisesti(huonoKortti);
        assertEquals(0,paate.edullisiaLounaitaMyyty());
    }
    @Test
    public void kortillaEiSaldoaLounastaEiMyytyMaukas() {
        paate.syoMaukkaasti(huonoKortti);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }

    
    
    @Test
    public void kortillaEiSaldoaEdullinen(){
        
      assertFalse(paate.syoEdullisesti(huonoKortti));
        
    }
    
    @Test
    public void kortillaEiSaldoaMaukas() {

        assertFalse(paate.syoMaukkaasti(huonoKortti));

    }


    
    
    @Test
    public void kassanSaldoEiMuutuKortillaEdullinen(){
        
        paate.syoEdullisesti(kelpoKortti);
        assertEquals(100000,paate.kassassaRahaa());
    }
    @Test
    public void kassanSaldoEiMuutuKortillaMaukas() {

        paate.syoMaukkaasti(kelpoKortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    @Test
    public void kortinSaldoMuuttuuRahaaLadattaessa(){
        
        huonoKortti.lataaRahaa(100);
        assertEquals(200,huonoKortti.saldo());
    }
    
   
    
    @Test 
    public void kassanSaldoMuuttuuKortilleRahaaLadattaessa(){
        
        int rahaa = paate.kassassaRahaa();
        paate.lataaRahaaKortille(huonoKortti, 100);
        
        assertEquals(rahaa+100,paate.kassassaRahaa());
    }
  
  @Test
  public void kortinSaldoEiMuutuLadattaessaNolla(){
      paate.lataaRahaaKortille(kelpoKortti, 0);
      assertEquals(400, kelpoKortti.saldo());
  }
  @Test
  public void kortinSaldoEiMuutuNegatiivisella(){
      paate.lataaRahaaKortille(kelpoKortti, -100);
      assertEquals(400,kelpoKortti.saldo());
  }
}
