package com.load.simple.metadata.target;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class AggMonthlyCarrier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int year;
    private int month;
    private int allArrtime;
    private String uniqueCarrier;
    private int allDistance;
    private int minDistance;
    private int maxDistance;
    private String Description;

    public int maxDistanceF(int i){
        return maxDistance < i ? i : maxDistance;
    }

    public int minDistanceF(int i){
        return minDistance > i ? i : minDistance;
    }

    @Override
    public String toString() {
        return "AggMonthlyCarrier{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", allArrtime=" + allArrtime +
                ", uniqueCarrier='" + uniqueCarrier + '\'' +
                ", allDistance=" + allDistance +
                ", minDistance=" + minDistance +
                ", maxDistance=" + maxDistance +
                ", Description='" + Description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AggMonthlyCarrier that = (AggMonthlyCarrier) o;
        if (id == that.id) return true;
        return year == that.year &&
                month == that.month &&
                allArrtime == that.allArrtime &&
                allDistance == that.allDistance &&
                minDistance == that.minDistance &&
                maxDistance == that.maxDistance &&
                Objects.equals(uniqueCarrier, that.uniqueCarrier) &&
                Objects.equals(Description, that.Description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(year, month, allArrtime, uniqueCarrier, allDistance, minDistance, maxDistance, Description);
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

    public int getAllArrtime() {
        return allArrtime;
    }

    public void setAllArrtime(int allArrtime) {
        this.allArrtime = allArrtime;
    }

    public String getUniqueCarrier() {
        return uniqueCarrier;
    }

    public void setUniqueCarrier(String uniqueCarrier) {
        this.uniqueCarrier = uniqueCarrier;
    }

    public int getAllDistance() {
        return allDistance;
    }

    public void setAllDistance(int allDistance) {
        this.allDistance = allDistance;
    }

    public int getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(int minDistance) {
        this.minDistance = minDistance;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
