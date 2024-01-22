package com.travel.passengers;

import java.util.Collection;
import java.util.List;

import com.travel.activities.Activity;

public interface Passenger {
    String getName();
    int getPassengerNumber();
    double getBalance();
    void deductBalance(double amount);
    boolean hasEnrolledIn(Activity activity);
    List<Activity> getEnrolledActivities();
}