package com.travel.packages;


import com.travel.activities.Activity;
import com.travel.passengers.GoldPassenger;
import com.travel.passengers.Passenger;
import com.travel.passengers.StandardPassenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConcreteTravelPackage implements TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Activity> itinerary;
    private List<Passenger> passengers;
//    private Map<Activity, String> activityDestinations = new HashMap<>();
    private Map<String, List<Activity>> destinationActivities = new HashMap<>();
    private Map<String, Integer> activityAvailableSpaces;

    public ConcreteTravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.itinerary = new ArrayList<>();
        this.passengers = new ArrayList<>();
        this.activityAvailableSpaces = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public List<Passenger> getPassengers() {
        return passengers;
    }
    
    @Override
    public Map<String, List<Activity>> getDestinationActivities() {
        return destinationActivities;
    }

    @Override
    public void addActivity(Activity activity, String destination) {
    	 destinationActivities.computeIfAbsent(destination, k -> new ArrayList<>()).add(activity);
         activityAvailableSpaces.put(activity.getName(), activity.getCapacity());
    }
    

    public void enrollPassenger(Passenger passenger, Activity activity) {
        try {
            // Check activity capacity before enrolling
            if (activity.getCapacity() <= 0) {
                throw new IllegalStateException("Activity " + activity.getName() + " is full.");
            }

            // Proceed with enrollment logic if capacity is available
            if (passengers.size() >= passengerCapacity) {
                System.out.println("Unable to enroll passenger " + passenger.getName() +
                        " in activity " + activity.getName() + ". The package is full.");
                return;
            }

            if (canEnroll(passenger, activity)) {
                passengers.add(passenger);
                activity.enroll(passenger);

                if (!updateSpacesAvailable(activity, -1)) {
                    throw new Exception("Error updating spaces for activity " + activity.getName());
                }

                System.out.println("Enrollment successful.");
            } else {
                System.out.println("Unable to enroll passenger " + passenger.getName() +
                        " in activity " + activity.getName() +
                        ". Activity has reached its capacity.");
            }
        } catch (Exception e) {
            System.err.println();
        }
    }
	
    private boolean canEnroll(Passenger passenger, Activity activity) {
        long enrolledCount = passengers.stream()
                .filter(p -> p.getEnrolledActivities().contains(activity))
                .count();

        return enrolledCount < activity.getCapacity();
    }
   

    private boolean updateSpacesAvailable(Activity activity, int delta) {
        String activityName = activity.getName();
        int currentSpaces = activityAvailableSpaces.getOrDefault(activityName, activity.getCapacity());
        int newSpaces = currentSpaces + delta;

        if (newSpaces >= 0 && newSpaces <= activity.getCapacity()) {
            activityAvailableSpaces.put(activityName, newSpaces);
//            System.out.println("Updated spaces for activity " + activityName + ": " + currentSpaces + " -> " + newSpaces);
            return true;
        } else {
//            System.out.println("Invalid space update for activity " + activityName + ": " + newSpaces + " (current: " + currentSpaces + ", delta: " + delta + ")");
            // Consider logging the error here for better tracking
            return false;
        }
    }


    @Override
    public void printItinerary() {
        System.out.println("Itinerary for " + name + ":");
        for (Map.Entry<String, List<Activity>> entry : destinationActivities.entrySet()) {
            String destination = entry.getKey();
            List<Activity> activities = entry.getValue();
            for (Activity activity : activities) {
                System.out.println(destination + " - " + activity.getName() +
                        " (Description: " + activity.getDescription() +
                        ", Cost: " + activity.getCost() +
                        ", Capacity: " + activity.getCapacity() + ")");
            }
        }
    }

    
    @Override
    public List<Activity> getItinerary() {
        List<Activity> itinerary = new ArrayList<>();
        for (List<Activity> activities : destinationActivities.values()) {
            itinerary.addAll(activities);
        }
        return itinerary;
    }
    
    @Override
    public void printPassengerList() {
        System.out.println("Passenger list for " + name + ":");
        for (Passenger passenger : passengers) {
            System.out.println(passenger.getName() + " (Passenger Number: " + passenger.getPassengerNumber() + ")");
        }
    }

    @Override
    public void printPassengerDetails(Passenger passenger) {
    	System.out.println("\nDetails of Passenger " + passenger.getName() + ":");
        System.out.println("Passenger Number: " + passenger.getPassengerNumber());
        System.out.println("Balance: $" + passenger.getBalance());

        System.out.println("Activities Enrolled:");
        for (Map.Entry<String, List<Activity>> entry : destinationActivities.entrySet()) {
            String destination = entry.getKey();
            List<Activity> activities = entry.getValue();

            System.out.println("Destination: " + destination);
            for (Activity activity : activities) {
                if (activity.isEnrolled(passenger)) {
                    double pricePaid = calculatePricePaid(passenger, activity);
                    System.out.println(" - " + activity.getName() +
                            " (Price Paid: $" + pricePaid + ")");
                }
            }
        }
    }

    private double calculatePricePaid(Passenger passenger, Activity activity) {
        if (passenger instanceof StandardPassenger) {
            // For StandardPassenger, just return the activity cost
            return activity.getCost();
        } else if (passenger instanceof GoldPassenger) {
            // For GoldPassenger, apply a 10% discount
            return 0.9 * activity.getCost();
        } else {
            // PremiumPassenger, activities are free for them
            return 0.0;
        }
    }
    
    public void printAvailableActivities() {
        System.out.println("Available Activities with Spaces:");
        for (Map.Entry<String, Integer> entry : activityAvailableSpaces.entrySet()) {
            System.out.println(" - Activity: " + entry.getKey() + ", Spaces Available: " + entry.getValue());
        }
    }
    private int getEnrollmentCount(Activity activity) {
        // Count how many passengers are enrolled in the given activity
        return (int) passengers.stream()
                .filter(passenger -> passenger.getEnrolledActivities().contains(activity))
                .count();
    }
    public int getAvailableSpacesForActivity(String activityName) {
        return activityAvailableSpaces.getOrDefault(activityName, 0);
    }

}
