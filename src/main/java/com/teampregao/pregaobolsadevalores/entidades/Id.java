package com.teampregao.pregaobolsadevalores.entidades;

import com.teampregao.pregaobolsadevalores.manager.*;

import java.util.Objects;

public class Id implements Comparable<Id> {
    private final int id;
    private final Type type;

    public Id(int id, Type type) {
        this.id = id;
        this.type = type;
    }

    public Id(Type type) {
        this.id = getIdAutoIncremente(type);
        this.type = type;
    }

    private int getIdAutoIncremente(Type type) {
        SaverManager saverManager = new SaverManager();
        String currenteLine = saverManager.read(type);
        if (currenteLine == null || currenteLine.length() == 0){
            System.out.println("id: 1");
            return 1;
        }
        System.out.println("tipo: " + type.type);
        System.out.println("lengt: " + currenteLine.length());
        System.out.println("max leng: " + type.maxLenght);
        System.out.println("id: " + ((currenteLine.length() / type.maxLenght) + 1));
        return (currenteLine.length() / type.maxLenght) + 1;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Id id1)) return false;
        return getId() == id1.getId() && getType() == id1.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType());
    }

    @Override
    public int compareTo(Id o) {
        int idComparison = Integer.compare(this.getId(), o.getId());

        if (idComparison != 0) {
            return idComparison;
        }

        return this.getType().compareTo(o.getType());
    }
}