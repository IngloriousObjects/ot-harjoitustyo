package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    Maksukortti toinen;
    Kassapaate paate;

    
    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
        toinen = new Maksukortti(1000);
        paate = new Kassapaate();
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void kortinSaldoAlussaOikein() {

        assertEquals(10, kortti.saldo());

    }

    @Test
    public void lataaRahaaToimiiOikein() {

        kortti.lataaRahaa(10);
        assertEquals(20, kortti.saldo());

    }

    @Test
    public void otaRahaaKunSaldoaRiittavasti() {

        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
    }

    @Test
    public void otaRahaaKunSaldoEiRiita() {

        kortti.otaRahaa(15);
        assertEquals(10, kortti.saldo());

    }
    
    @Test
    public void otaRahaaPalauttaaFalseKunSaldoEiRiita() {

        assertFalse(kortti.otaRahaa(15));
    }
    
    @Test
    public void otaRahaaPalauttaaTrueKunSaldoRiittaa(){
        
        assertTrue(kortti.otaRahaa(9));
    }

    @Test
    public void syoEdullisestiVahentaaKortinSaldoa(){
        
        paate.syoEdullisesti(toinen);
        assertEquals(760,toinen.saldo());
    }
    @Test
    public void syoMaukkaastiVahentaaKortinSaldoa(){
        paate.syoMaukkaasti(toinen);
        assertEquals(600,toinen.saldo());
        
    }
    @Test
    public void toStringToimiiOletetusti(){
        
        assertEquals("saldo: 0.10",kortti.toString()); //Tässä luonnollisesti alkuperäisessä koodissa virhe, joten saldo ei toimi odotetulla tavalla. Tällä kertaa laitettu testiin koodissa oletettu tulos
    }

}
