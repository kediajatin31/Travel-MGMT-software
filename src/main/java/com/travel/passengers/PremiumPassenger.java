package com.travel.passengers;

import java.util.ArrayList;
import java.util.List;

import com.travel.activities.Activity;
import com.travel.activities.ConcreteActivity;

public class PremiumPassenger implements Passenger {
    private String name;
    private int passengerNumber;
    private List<Activity> enrolledActivities = new ArrayList<>();

    public PremiumPassenger(String name, int passengerNumber) {
        this.name = name;
        this.passengerNumber = passengerNumber;
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
        return 0; // Premium passengers have unlimited balance (free activities)
    }

    @Override
    public void deductBalance(double amount) {
        enrollInActivity(); // Premium passengers enroll for free
    }
    
    @Override
    public List<Activity> getEnrolledActivities() {
        return enrolledActivities;
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
