package com.teampregao.pregaobolsadevalores.entidades;

import com.teampregao.pregaobolsadevalores.manager.*;

import java.util.Objects;

public class Id {
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
        if (currenteLine == null){
            return 1;
        }
        System.out.println(currenteLine);
        System.out.println(currenteLine.length());
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
}