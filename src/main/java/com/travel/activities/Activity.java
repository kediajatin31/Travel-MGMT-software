package com.travel.activities;

import com.travel.passengers.Passenger;

public interface Activity {
    String getName();
    String getDescription();
    double getCost();
    int getCapacity();
    boolean isEnrolled(Passenger passenger);
    void enroll(Passenger passenger);
	void setDestination(String destination);
	void updateCapacity(int delta);
	String getDestination();
}