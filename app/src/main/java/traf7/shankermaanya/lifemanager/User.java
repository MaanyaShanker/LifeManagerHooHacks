package traf7.shankermaanya.lifemanager;

import java.util.ArrayList;
import java.util.Date;

public class User {
    String email, name;
    private ArrayList<String> mJobs = new ArrayList<>();
    private ArrayList<Integer> mPay = new ArrayList<>();
    private ArrayList<Integer> mFreq = new ArrayList<>();
    private ArrayList<String> mBills = new ArrayList<>();
    private ArrayList<Integer> mAmount = new ArrayList<>();
    ArrayList<String> mGoals = new ArrayList<>();
    ArrayList<Date> mDate = new ArrayList<>();
    ArrayList<Integer> mGoalAmount = new ArrayList<>();

    public User() {
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public User(String email, String name, ArrayList<String> mJobs, ArrayList<Integer> mPay, ArrayList<Integer> mFreq, ArrayList<String> mBills, ArrayList<Integer> mAmount, ArrayList<String> mGoals, ArrayList<Date> mDate, ArrayList<Integer> mGoalAmount) {
        this.email = email;
        this.name = name;
        this.mJobs = mJobs;
        this.mPay = mPay;
        this.mFreq = mFreq;
        this.mBills = mBills;
        this.mAmount = mAmount;
        this.mGoals = mGoals;
        this.mDate = mDate;
        this.mGoalAmount = mGoalAmount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getmJobs() {
        return mJobs;
    }

    public void setmJobs(ArrayList<String> mJobs) {
        this.mJobs = mJobs;
    }

    public ArrayList<Integer> getmPay() {
        return mPay;
    }

    public void setmPay(ArrayList<Integer> mPay) {
        this.mPay = mPay;
    }

    public ArrayList<Integer> getmFreq() {
        return mFreq;
    }

    public void setmFreq(ArrayList<Integer> mFreq) {
        this.mFreq = mFreq;
    }

    public ArrayList<String> getmBills() {
        return mBills;
    }

    public void setmBills(ArrayList<String> mBills) {
        this.mBills = mBills;
    }

    public ArrayList<Integer> getmAmount() {
        return mAmount;
    }

    public void setmAmount(ArrayList<Integer> mAmount) {
        this.mAmount = mAmount;
    }

    public ArrayList<String> getmGoals() {
        return mGoals;
    }

    public void setmGoals(ArrayList<String> mGoals) {
        this.mGoals = mGoals;
    }

    public ArrayList<Date> getmDate() {
        return mDate;
    }

    public void setmDate(ArrayList<Date> mDate) {
        this.mDate = mDate;
    }

    public ArrayList<Integer> getmGoalAmount() {
        return mGoalAmount;
    }

    public void setmGoalAmount(ArrayList<Integer> mGoalAmount) {
        this.mGoalAmount = mGoalAmount;
    }
}