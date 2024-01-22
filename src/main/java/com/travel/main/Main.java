package com.travel.main;

import com.travel.activities.Activity;
import com.travel.activities.ConcreteActivity;
import com.travel.packages.ConcreteTravelPackage;
import com.travel.packages.TravelPackage;
import com.travel.passengers.GoldPassenger;
import com.travel.passengers.Passenger;
import com.travel.passengers.PremiumPassenger;
import com.travel.passengers.StandardPassenger;

public class Main {
    public static void main(String[] args) {
        // Create activities
        Activity activity1 = new ConcreteActivity("Hiking", "Explore scenic trails", 50.0, 3);
        Activity activity2 = new ConcreteActivity("City Tour", "Guided tour of the city", 75.0, 15);

        // Create a travel package
        TravelPackage travelPackage = new ConcreteTravelPackage("Adventure Package", 30);

        // Add activities to the package with destinations
        travelPackage.addActivity(activity1, "Mountain Region");
        travelPackage.addActivity(activity1, "Coastal Area");
        travelPackage.addActivity(activity2, "City Center");

        // Create passengers
        Passenger standardPassenger = new StandardPassenger("John", 1, 500.0);
        Passenger goldPassenger = new GoldPassenger("Alice", 2, 700.0);
        Passenger premiumPassenger = new PremiumPassenger("Bob", 3);

        // Enroll passengers in activities
        travelPackage.enrollPassenger(standardPassenger, activity1);
        travelPackage.enrollPassenger(goldPassenger, activity2);
        travelPackage.enrollPassenger(premiumPassenger, activity1);
        travelPackage.enrollPassenger(goldPassenger, activity1);
       

        // Print details of passengers
        travelPackage.printPassengerDetails(standardPassenger);
        travelPackage.printPassengerDetails(goldPassenger);
        travelPackage.printPassengerDetails(premiumPassenger);

        // Print available activities
        travelPackage.printAvailableActivities();
        
     // Print itinerary
      System.out.println("Travel Package Itinerary:");
      for (Activity activity : travelPackage.getItinerary()) {
          System.out.println(" - " + activity.getName() + ": " + activity.getDescription() +
                  " (Cost: $" + activity.getCost() + ", Capacity: " + activity.getCapacity() + ")");
      }

      // Print passenger list
      System.out.println("\nPassenger List:");
      System.out.println("Package Name: " + travelPackage.getName());
      System.out.println("Passenger Capacity: " + travelPackage.getPassengerCapacity());
      System.out.println("Number of Passengers Enrolled: " + travelPackage.getPassengers().size());

      for (Passenger passenger : travelPackage.getPassengers()) {
          System.out.println(" - " + passenger.getName() + " (ID: " + passenger.getPassengerNumber() +
                  ", Balance: $" + passenger.getBalance() + ")");
      }
    }
}
