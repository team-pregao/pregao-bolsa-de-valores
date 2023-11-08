package com.teampregao.pregaobolsadevalores.ed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapaTest {
    @Test
    void add() {
        Mapa<String, Double> mapa = new Mapa<>();
        mapa.put("Prova 1", 10.0);
        mapa.put("Prova 4", 4.0);
        mapa.put("Prova 2", 7.0);
        mapa.put("Prova 3", 9.0);

        assertEquals(mapa.toString(), "{ Prova 1: 10.0 Prova 4: 4.0 Prova 2: 7.0 Prova 3: 9.0 }");
    }
    @Test
    void addAndEdit() {
        Mapa<String, Double> mapa = new Mapa<>();
        mapa.put("Prova 1", 10.0);
        mapa.put("Prova 4", 4.0);
        mapa.put("Prova 2", 7.0);
        mapa.put("Prova 3", 9.0);
        mapa.put("Prova 4", 90.0);

        System.out.println(mapa);

        assertEquals(mapa.toString(), "{ Prova 1: 10.0 Prova 4: 90.0 Prova 2: 7.0 Prova 3: 9.0 }");
    }

    @Test
    void remove() {
        Mapa<String, Double> mapa = new Mapa<>();
        mapa.put("Prova 1", 10.0);
        mapa.put("Prova 4", 4.0);
        mapa.put("Prova 2", 7.0);
        mapa.put("Prova 3", 9.0);
        mapa.remove("Prova 3");
        mapa.remove("Prova 4");
        mapa.remove("Prova 2");

        assertEquals(mapa.toString(), "{ Prova 1: 10.0 }");
    }

    @Test
    void get() {
        Mapa<String, Double> mapa = new Mapa<>();
        mapa.put("Prova 1", 10.0);
        mapa.put("Prova 4", 4.0);
        mapa.put("Prova 2", 7.0);
        mapa.put("Prova 3", 9.0);
        mapa.put("Prova 4", 90.0);

        System.out.println(mapa);

        assertEquals(mapa.get("Prova 4"), 90.0);
    }

    @Test
    void getKey() {
        Mapa<String, Double> mapa = new Mapa<>();
        mapa.put("Prova 1", 10.0);
        mapa.put("Prova 4", 4.0);
        mapa.put("Prova 2", 7.0);
        mapa.put("Prova 3", 9.0);
        mapa.put("Prova 4", 90.0);

        System.out.println(mapa);

        assertEquals(mapa.getKey(90.0), "Prova 4");
    }
}