package com.example.mygooglemap;

public class Steps {
    private String distance ;
    private String duration;
    private String htmlInstruction;
    private double startLocationLat;

    private double startLocationLng;
    private double endLocationLat;
    private double endLocationLng;


    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getHtmlInstruction() {
        return htmlInstruction;
    }

    public void setHtmlInstruction(String htmlInstruction) {
        this.htmlInstruction = htmlInstruction;
    }

    public double getStartLocationLat() {
        return startLocationLat;
    }

    public void setStartLocationLat(double startLocationLat) {
        this.startLocationLat = startLocationLat;
    }

    public double getStartLocationLng() {
        return startLocationLng;
    }

    public void setStartLocationLng(double startLocationLng) {
        this.startLocationLng = startLocationLng;
    }

    public double getEndLocationLat() {
        return endLocationLat;
    }

    public void setEndLocationLat(double endLocationLat) {
        this.endLocationLat = endLocationLat;
    }

    public double getEndLocationLng() {
        return endLocationLng;
    }

    public void setEndLocationLng(double endLocationLng) {
        this.endLocationLng = endLocationLng;
    }
}
