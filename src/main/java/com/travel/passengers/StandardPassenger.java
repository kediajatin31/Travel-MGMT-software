package com.travel.passengers;

import java.util.ArrayList;
import java.util.List;

import com.travel.activities.Activity;
import com.travel.activities.ConcreteActivity;

public class StandardPassenger implements Passenger {
    private String name;
    private int passengerNumber;
    private double balance;
    private List<Activity> enrolledActivities = new ArrayList<>();

    public StandardPassenger(String name, int passengerNumber, double balance) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.balance = balance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPassengerNumber() {
        return passengerNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }
    
    @Override
    public List<Activity> getEnrolledActivities() {
        return enrolledActivities;
    }


    @Override
    public void deductBalance(double amount) {
        if (balance >= amount) {
            balance -= amount;
            enrollInActivity(); // Call the method to enroll in the activity
        } else {
            System.out.println("Insufficient balance for passenger " + name);
        }
    }

    private void enrollInActivity() {
        // Create an instance of Activity and enroll the passenger
        Activity activity = new ConcreteActivity("Default Activity", "Default Description", 0.0, 0);
        enrolledActivities.add(activity);
        activity.enroll(this);
    }

    @Override
    public boolean hasEnrolledIn(Activity activity) {
        return enrolledActivities.contains(activity);
    }
}
