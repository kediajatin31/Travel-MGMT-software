package com.travel.packages;

import com.travel.activities.Activity;
import com.travel.passengers.Passenger;

import java.util.List;
import java.util.Map;

public interface TravelPackage {
    String getName();
    int getPassengerCapacity();
    List<Activity> getItinerary();
    List<Passenger> getPassengers();
    void addActivity(Activity activity, String destination);
    void enrollPassenger(Passenger passenger, Activity activity);
    void printItinerary();
    void printPassengerList();
    void printPassengerDetails(Passenger passenger);
    void printAvailableActivities();
	Map<String, List<Activity>> getDestinationActivities();
	
}
