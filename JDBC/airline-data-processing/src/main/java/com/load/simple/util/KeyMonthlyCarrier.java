package com.load.simple.util;

import java.util.Objects;

public class KeyMonthlyCarrier {

    private int year;
    private int month;
    private String uniqueCarrier;


    @Override
    public String toString() {
        return "KeyMonthlyCarrier{" +
                "year=" + year +
                ", month=" + month +
                ", uniqueCarrier='" + uniqueCarrier + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyMonthlyCarrier that = (KeyMonthlyCarrier) o;
        return year == that.year &&
                month == that.month &&
                Objects.equals(uniqueCarrier, that.uniqueCarrier);
    }

    @Override
    public int hashCode() {

        return Objects.hash(year, month, uniqueCarrier);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getUniqueCarrier() {
        return uniqueCarrier;
    }

    public void setUniqueCarrier(String uniqueCarrier) {
        this.uniqueCarrier = uniqueCarrier;
    }
}
