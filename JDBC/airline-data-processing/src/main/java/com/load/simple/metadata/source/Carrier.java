package com.load.simple.metadata.source;

import java.util.Objects;

public class Carrier {
    private String code;
    private String description;

    @Override
    public String toString() {
        return "Carrier{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrier carriers = (Carrier) o;
        return Objects.equals(code, carriers.code) &&
                Objects.equals(description, carriers.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, description);
    }

    public String getCode() { return code; }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
