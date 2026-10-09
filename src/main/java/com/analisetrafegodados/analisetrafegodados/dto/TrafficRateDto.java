package com.analisetrafegodados.analisetrafegodados.dto;

public class TrafficRateDto {
    private int fromHour;
    private int toHour;
    private double rate;

    public TrafficRateDto() {
    }

    public TrafficRateDto(int fromHour, int toHour, double rate) {
        this.fromHour = fromHour;
        this.toHour = toHour;
        this.rate = rate;
    }

    public int getFromHour() {
        return fromHour;
    }

    public void setFromHour(int fromHour) {
        this.fromHour = fromHour;
    }

    public int getToHour() {
        return toHour;
    }

    public void setToHour(int toHour) {
        this.toHour = toHour;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
