package com.analisetrafegodados.analisetrafegodados.model;

public class TrafficPoint {
    private int hour;
    private double mbps;

    public TrafficPoint() {
    }

    public TrafficPoint(int hour, double mbps) {
        this.hour = hour;
        this.mbps = mbps;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public double getMbps() {
        return mbps;
    }

    public void setMbps(double mbps) {
        this.mbps = mbps;
    }
}
