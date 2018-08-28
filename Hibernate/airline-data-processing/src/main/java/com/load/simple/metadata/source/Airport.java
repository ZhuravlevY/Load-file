package com.load.simple.metadata.source;

import java.util.Objects;

public class Airport {
    private String iata;
    private String airport;
    private String city;
    private String state;
    private String country;
    private double lat;
    private double longFild;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Double.compare(airport.lat, lat) == 0 &&
                Double.compare(airport.longFild, longFild) == 0 &&
                Objects.equals(iata, airport.iata) &&
                Objects.equals(this.airport, airport.airport) &&
                Objects.equals(city, airport.city) &&
                Objects.equals(state, airport.state) &&
                Objects.equals(country, airport.country);
    }

    @Override
    public int hashCode() {

        return Objects.hash(iata, airport, city, state, country, lat, longFild);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "iata='" + iata + '\'' +
                ", airport='" + airport + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", lat=" + lat +
                ", longFild=" + longFild +
                '}';
    }

    public String getIata() { return iata; }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongFild() {
        return longFild;
    }

    public void setLongFild(double longFild) {
        this.longFild = longFild;
    }
}
