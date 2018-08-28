package com.load.simple.metadata.target;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class TfctFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int year;
    private int month;
    private int dayofmonth;
    private int dayofweek;
    private int deptime;
    private int crsdeptime;
    private int arrtime;
    private int crsarrtime;
    private String uniquecarrier;
    private int flightnum;
    private String tailnum;
    private int actualelapsedtime;
    private int crselapsedtime;
    private String airtime;
    private int arrdelay;
    private int depdelay;
    private String origin;
    private String dest;
    private int distance;
    private String taxiin;
    private String taxiout;
    private int cancelled;
    private String cancellationcode;
    private int diverted;
    private String carrierdelay;
    private String weatherdelay;
    private String nasdelay;
    private String securitydelay;
    private String lateaircraftdelay;

    //origin Airport
    private String originairport;
    private String origincity;
    private String originstate;
    private String origincountry;
    private double originlat;
    private double originfild;

    //dest Airport
    private String destairport;
    private String destcity;
    private String deststate;
    private String destcountry;
    private double destlat;
    private double destfild;

    //Carrier
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TfctFlight that = (TfctFlight) o;
        if (id == that.id) return true;
        return year == that.year &&
                month == that.month &&
                dayofmonth == that.dayofmonth &&
                dayofweek == that.dayofweek &&
                deptime == that.deptime &&
                crsdeptime == that.crsdeptime &&
                arrtime == that.arrtime &&
                crsarrtime == that.crsarrtime &&
                flightnum == that.flightnum &&
                actualelapsedtime == that.actualelapsedtime &&
                crselapsedtime == that.crselapsedtime &&
                arrdelay == that.arrdelay &&
                depdelay == that.depdelay &&
                distance == that.distance &&
                cancelled == that.cancelled &&
                diverted == that.diverted &&
                Double.compare(that.originlat, originlat) == 0 &&
                Double.compare(that.originfild, originfild) == 0 &&
                Double.compare(that.destlat, destlat) == 0 &&
                Double.compare(that.destfild, destfild) == 0 &&
                Objects.equals(uniquecarrier, that.uniquecarrier) &&
                Objects.equals(tailnum, that.tailnum) &&
                Objects.equals(airtime, that.airtime) &&
                Objects.equals(origin, that.origin) &&
                Objects.equals(dest, that.dest) &&
                Objects.equals(taxiin, that.taxiin) &&
                Objects.equals(taxiout, that.taxiout) &&
                Objects.equals(cancellationcode, that.cancellationcode) &&
                Objects.equals(carrierdelay, that.carrierdelay) &&
                Objects.equals(weatherdelay, that.weatherdelay) &&
                Objects.equals(nasdelay, that.nasdelay) &&
                Objects.equals(securitydelay, that.securitydelay) &&
                Objects.equals(lateaircraftdelay, that.lateaircraftdelay) &&
                Objects.equals(originairport, that.originairport) &&
                Objects.equals(origincity, that.origincity) &&
                Objects.equals(originstate, that.originstate) &&
                Objects.equals(origincountry, that.origincountry) &&
                Objects.equals(destairport, that.destairport) &&
                Objects.equals(destcity, that.destcity) &&
                Objects.equals(deststate, that.deststate) &&
                Objects.equals(destcountry, that.destcountry) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(year, month, dayofmonth, dayofweek, deptime, crsdeptime, arrtime, crsarrtime, uniquecarrier, flightnum, tailnum, actualelapsedtime, crselapsedtime, airtime, arrdelay, depdelay, origin, dest, distance, taxiin, taxiout, cancelled, cancellationcode, diverted, carrierdelay, weatherdelay, nasdelay, securitydelay, lateaircraftdelay, originairport, origincity, originstate, origincountry, originlat, originfild, destairport, destcity, deststate, destcountry, destlat, destfild, description);
    }

    @Override
    public String toString() {
        return "TfctFlight{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", dayofmonth=" + dayofmonth +
                ", dayofweek=" + dayofweek +
                ", deptime=" + deptime +
                ", crsdeptime=" + crsdeptime +
                ", arrtime=" + arrtime +
                ", crsarrtime=" + crsarrtime +
                ", uniquecarrier='" + uniquecarrier + '\'' +
                ", flightnum=" + flightnum +
                ", tailnum='" + tailnum + '\'' +
                ", actualelapsedtime=" + actualelapsedtime +
                ", crselapsedtime=" + crselapsedtime +
                ", airtime='" + airtime + '\'' +
                ", arrdelay=" + arrdelay +
                ", depdelay=" + depdelay +
                ", origin='" + origin + '\'' +
                ", dest='" + dest + '\'' +
                ", distance=" + distance +
                ", taxiin='" + taxiin + '\'' +
                ", taxiout='" + taxiout + '\'' +
                ", cancelled=" + cancelled +
                ", cancellationcode='" + cancellationcode + '\'' +
                ", diverted=" + diverted +
                ", carrierdelay='" + carrierdelay + '\'' +
                ", weatherdelay='" + weatherdelay + '\'' +
                ", nasdelay='" + nasdelay + '\'' +
                ", securitydelay='" + securitydelay + '\'' +
                ", lateaircraftdelay='" + lateaircraftdelay + '\'' +
                ", originairport='" + originairport + '\'' +
                ", origincity='" + origincity + '\'' +
                ", originstate='" + originstate + '\'' +
                ", origincountry='" + origincountry + '\'' +
                ", originlat=" + originlat +
                ", originfild=" + originfild +
                ", destairport='" + destairport + '\'' +
                ", destcity='" + destcity + '\'' +
                ", deststate='" + deststate + '\'' +
                ", destcountry='" + destcountry + '\'' +
                ", destlat=" + destlat +
                ", destfild=" + destfild +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getDayofmonth() {
        return dayofmonth;
    }

    public void setDayofmonth(int dayofmonth) {
        this.dayofmonth = dayofmonth;
    }

    public int getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(int dayofweek) {
        this.dayofweek = dayofweek;
    }

    public int getDeptime() {
        return deptime;
    }

    public void setDeptime(int deptime) {
        this.deptime = deptime;
    }

    public int getCrsdeptime() {
        return crsdeptime;
    }

    public void setCrsdeptime(int crsdeptime) {
        this.crsdeptime = crsdeptime;
    }

    public int getArrtime() {
        return arrtime;
    }

    public void setArrtime(int arrtime) {
        this.arrtime = arrtime;
    }

    public int getCrsarrtime() {
        return crsarrtime;
    }

    public void setCrsarrtime(int crsarrtime) {
        this.crsarrtime = crsarrtime;
    }

    public String getUniquecarrier() {
        return uniquecarrier;
    }

    public void setUniquecarrier(String uniquecarrier) {
        this.uniquecarrier = uniquecarrier;
    }

    public int getFlightnum() {
        return flightnum;
    }

    public void setFlightnum(int flightnum) {
        this.flightnum = flightnum;
    }

    public String getTailnum() {
        return tailnum;
    }

    public void setTailnum(String tailnum) {
        this.tailnum = tailnum;
    }

    public int getActualelapsedtime() {
        return actualelapsedtime;
    }

    public void setActualelapsedtime(int actualelapsedtime) {
        this.actualelapsedtime = actualelapsedtime;
    }

    public int getCrselapsedtime() {
        return crselapsedtime;
    }

    public void setCrselapsedtime(int crselapsedtime) {
        this.crselapsedtime = crselapsedtime;
    }

    public String getAirtime() {
        return airtime;
    }

    public void setAirtime(String airtime) {
        this.airtime = airtime;
    }

    public int getArrdelay() {
        return arrdelay;
    }

    public void setArrdelay(int arrdelay) {
        this.arrdelay = arrdelay;
    }

    public int getDepdelay() {
        return depdelay;
    }

    public void setDepdelay(int depdelay) {
        this.depdelay = depdelay;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getTaxiin() {
        return taxiin;
    }

    public void setTaxiin(String taxiin) {
        this.taxiin = taxiin;
    }

    public String getTaxiout() {
        return taxiout;
    }

    public void setTaxiout(String taxiout) {
        this.taxiout = taxiout;
    }

    public int getCancelled() {
        return cancelled;
    }

    public void setCancelled(int cancelled) {
        this.cancelled = cancelled;
    }

    public String getCancellationcode() {
        return cancellationcode;
    }

    public void setCancellationcode(String cancellationcode) {
        this.cancellationcode = cancellationcode;
    }

    public int getDiverted() {
        return diverted;
    }

    public void setDiverted(int diverted) {
        this.diverted = diverted;
    }

    public String getCarrierdelay() {
        return carrierdelay;
    }

    public void setCarrierdelay(String carrierdelay) {
        this.carrierdelay = carrierdelay;
    }

    public String getWeatherdelay() {
        return weatherdelay;
    }

    public void setWeatherdelay(String weatherdelay) {
        this.weatherdelay = weatherdelay;
    }

    public String getNasdelay() {
        return nasdelay;
    }

    public void setNasdelay(String nasdelay) {
        this.nasdelay = nasdelay;
    }

    public String getSecuritydelay() {
        return securitydelay;
    }

    public void setSecuritydelay(String securitydelay) {
        this.securitydelay = securitydelay;
    }

    public String getLateaircraftdelay() {
        return lateaircraftdelay;
    }

    public void setLateaircraftdelay(String lateaircraftdelay) {
        this.lateaircraftdelay = lateaircraftdelay;
    }

    public String getOriginairport() {
        return originairport;
    }

    public void setOriginairport(String originairport) {
        this.originairport = originairport;
    }

    public String getOrigincity() {
        return origincity;
    }

    public void setOrigincity(String origincity) {
        this.origincity = origincity;
    }

    public String getOriginstate() {
        return originstate;
    }

    public void setOriginstate(String originstate) {
        this.originstate = originstate;
    }

    public String getOrigincountry() {
        return origincountry;
    }

    public void setOrigincountry(String origincountry) {
        this.origincountry = origincountry;
    }

    public double getOriginlat() {
        return originlat;
    }

    public void setOriginlat(double originlat) {
        this.originlat = originlat;
    }

    public double getOriginfild() {
        return originfild;
    }

    public void setOriginfild(double originfild) {
        this.originfild = originfild;
    }

    public String getDestairport() {
        return destairport;
    }

    public void setDestairport(String destairport) {
        this.destairport = destairport;
    }

    public String getDestcity() {
        return destcity;
    }

    public void setDestcity(String destcity) {
        this.destcity = destcity;
    }

    public String getDeststate() {
        return deststate;
    }

    public void setDeststate(String deststate) {
        this.deststate = deststate;
    }

    public String getDestcountry() {
        return destcountry;
    }

    public void setDestcountry(String destcountry) {
        this.destcountry = destcountry;
    }

    public double getDestlat() {
        return destlat;
    }

    public void setDestlat(double destlat) {
        this.destlat = destlat;
    }

    public double getDestfild() {
        return destfild;
    }

    public void setDestfild(double destfild) {
        this.destfild = destfild;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
