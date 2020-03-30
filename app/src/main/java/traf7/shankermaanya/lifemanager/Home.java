package traf7.shankermaanya.lifemanager;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class Home extends AppCompatActivity {
    private ArrayList<String> mJobs = new ArrayList<>();
    private ArrayList<Integer> mPay = new ArrayList<>();
    private ArrayList<Integer> mFreq = new ArrayList<>();
    private ArrayList<String> mBills = new ArrayList<>();
    private ArrayList<Integer> mAmount = new ArrayList<>();
    ArrayList<String> mGoals = new ArrayList<>();
    ArrayList<Date> mDate = new ArrayList<>();
    ArrayList<Integer> mGoalAmount = new ArrayList<>();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    TextView app_header, welcome_header, expenses_remaining;
    RecyclerView jobs_recycler_view, expenses_recycler_view, goals_recycler_view;
    EditText job_name, job_pay, job_freq, bill_name, bill_amount, bill_freq, goal_amount, goal_month, goal_year, goal_name, input_which_goal, input_amount_to_goal;
    Button job_input, enter_job, enter_bill, bill_input, enter_goal, goal_input, goal_input_money, enter_money_for_goal;
    ImageView logo;
    EditText[] editTexts;
    Button[] buttons;
    static int remaining, applied_to_goal = 0;
    JobsRecyclerAdapter jobsadapter;
    ExpensesRecyclerAdapter billsadapter;
    GoalsRecyclerAdapter goalsadapter;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Iterable<DataSnapshot> placeholder;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    make_invisible();
                    logo.setVisibility(View.VISIBLE);
                    welcome_header.setVisibility(View.VISIBLE);
                    app_header.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_jobs:
                    make_invisible();
                    jobs_recycler_view.setVisibility(View.VISIBLE);
                    job_input.setVisibility(View.VISIBLE);
                    jobs_recycler_method();
                    return true;
                case R.id.navigation_expenses:
                    make_invisible();
                    expenses_recycler_view.setVisibility(View.VISIBLE);
                    bill_input.setVisibility(View.VISIBLE);
                    expenses_remaining.setText("$" + money_remaining() + " remaining");
                    expenses_remaining.setVisibility(View.VISIBLE);
                    expenses_recycler_method();
                    return true;
                case R.id.navigation_goals:
                    make_invisible();
                    goals_recycler_view.setVisibility(View.VISIBLE);
                    goal_input.setVisibility(View.VISIBLE);
                    goal_input_money.setVisibility(View.VISIBLE);
                    goals_recycler_method();
                    return true;
            }
            return false;
        }
    };

    private void jobs_recycler_method() {
        jobs_recycler_view.setLayoutManager(new LinearLayoutManager(this));
    }
    private void expenses_recycler_method() {
        expenses_recycler_view.setLayoutManager(new LinearLayoutManager(this));
    }
    private void goals_recycler_method(){
        goals_recycler_view.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        mJobs.add("Wegmans");
//        mPay.add(15);
//        mFreq.add(30);
//        mJobs.add("Babysitting");
//        mPay.add(15);
//        mFreq.add(30);
//        mBills.add("Rent");
//        mAmount.add(1000);
//        mBills.add("Rent");
//        mAmount.add(1000);
//        Date old = new Date(20, 2, 28);
//        mGoals.add("Car");
//        mDate.add(old);
//        mGoalAmount.add(500);
//        mGoals.add("Car");
//        mDate.add(old);
//        mGoalAmount.add(500);



        FirebaseUser user = auth.getCurrentUser();
        String name = user.getDisplayName();

        app_header = findViewById(R.id.app_header);
        logo = findViewById(R.id.logo);
        welcome_header = findViewById(R.id.welcome_header);
        welcome_header.setText(welcome_header.getText() + ",\n " + name);
        welcome_header.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        jobs_recycler_view = findViewById(R.id.jobs_recycler_view);
        job_name = findViewById(R.id.input_job_name);
        job_pay = findViewById(R.id.input_job_pay);
        job_freq = findViewById(R.id.input_job_hours);
        jobsadapter = new JobsRecyclerAdapter(mJobs, mPay, mFreq, this);
        jobs_recycler_view.setAdapter(jobsadapter);
        job_input = findViewById(R.id.job_input);
        enter_job = findViewById(R.id.enter_new_job);

        expenses_recycler_view = findViewById(R.id.expenses_recycler_view);
        bill_name = findViewById(R.id.bill_name);
        bill_amount = findViewById(R.id.bill_amount);
        bill_freq = findViewById(R.id.bill_freq);
        enter_bill = findViewById(R.id.enter_new_bill);
        bill_input = findViewById(R.id.expenses_input);
        expenses_remaining = findViewById(R.id.expenses_remaining);


        goals_recycler_view = findViewById(R.id.goals_recycler_view);
        goalsadapter = new GoalsRecyclerAdapter(mGoals, mDate, mGoalAmount, this);
        goals_recycler_view.setAdapter(goalsadapter);
        enter_goal = findViewById(R.id.enter_new_goal);
        goal_name = findViewById(R.id.input_goal_name);
        goal_amount = findViewById(R.id.input_goal_amount);
        goal_month = findViewById(R.id.input_goal_month);
        goal_year = findViewById(R.id.input_goal_year);
        goal_input = findViewById(R.id.goal_input);
        goal_input_money = findViewById(R.id.goal_input_money);
        input_which_goal = findViewById(R.id.input_which_goal);
        input_amount_to_goal = findViewById(R.id.input_amount_to_goal);
        enter_money_for_goal = findViewById(R.id.enter_money_for_goal);

        make_invisible();
        logo.setVisibility(View.VISIBLE);
        welcome_header.setVisibility(View.VISIBLE);
        app_header.setVisibility(View.VISIBLE);
        billsadapter = new ExpensesRecyclerAdapter(mBills, mAmount, this);
        expenses_recycler_view.setAdapter(billsadapter);

        remaining = money_remaining();


    }

    public void input_job(View view) {
        make_invisible();
        job_name.setVisibility(View.VISIBLE);
        job_pay.setVisibility(View.VISIBLE);
        job_freq.setVisibility(View.VISIBLE);
        enter_job.setVisibility(View.VISIBLE);
    }

    public void enter_new_job(View view) {
        mJobs.add(job_name.getText().toString());
        mPay.add(Integer.parseInt(job_pay.getText().toString()));
        mFreq.add(Integer.parseInt(job_freq.getText().toString()));
        jobsadapter.notifyDataSetChanged();
        remaining += (Integer.parseInt(job_pay.getText().toString()) * Integer.parseInt(job_freq.getText().toString()) * 4);
        job_name.getText().clear();
        job_pay.getText().clear();
        job_freq.getText().clear();
        make_invisible();
        jobs_recycler_view.setVisibility(View.VISIBLE);
        job_input.setVisibility(View.VISIBLE);

    }

    public void input_expense(View view) {
        make_invisible();
        bill_name.setVisibility(View.VISIBLE);
        bill_amount.setVisibility(View.VISIBLE);
        bill_freq.setVisibility(View.VISIBLE);
        enter_bill.setVisibility(View.VISIBLE);
    }


    public void enter_new_bill(View view) {
        mBills.add(bill_name.getText().toString());
        int freq = Integer.parseInt(bill_freq.getText().toString());
        int am = Integer.parseInt(bill_amount.getText().toString());
        int temp = (am / freq);
        remaining -= temp;
        mAmount.add(temp);
        billsadapter.notifyDataSetChanged();
        make_invisible();
        bill_name.getText().clear();
        bill_amount.getText().clear();
        bill_freq.getText().clear();
        expenses_recycler_view.setVisibility(View.VISIBLE);
        bill_input.setVisibility(View.VISIBLE);
        expenses_remaining.setText("$" + money_remaining() + " remaining");
        expenses_remaining.setVisibility(View.VISIBLE);
    }

    public void input_goal(View view) {
        make_invisible();
        enter_goal.setVisibility(View.VISIBLE);
        goal_name.setVisibility(View.VISIBLE);
        goal_amount.setVisibility(View.VISIBLE);
        goal_month.setVisibility(View.VISIBLE);
        goal_year.setVisibility(View.VISIBLE);
    }
    public void input_money_to_goal(View view) {
        make_invisible();
        input_which_goal.setVisibility(View.VISIBLE);
        input_amount_to_goal.setVisibility(View.VISIBLE);
        enter_money_for_goal.setVisibility(View.VISIBLE);
    }
    public void enter_the_money_for_the_goal(View view) {
        int goal = Integer.parseInt(input_which_goal.getText().toString());
        mGoalAmount.set(goal - 1,mGoalAmount.get(goal - 1) - Integer.parseInt(input_amount_to_goal.getText().toString()));
        make_invisible();
        goals_recycler_view.setVisibility(View.VISIBLE);
        goal_input.setVisibility(View.VISIBLE);
        goal_input_money.setVisibility(View.VISIBLE);
        goalsadapter.notifyDataSetChanged();
        String String_temp = input_amount_to_goal.getText().toString();
        int temp = Integer.parseInt(String_temp);
        remaining = remaining - temp;
        applied_to_goal += temp;
        input_which_goal.getText().clear();
        input_amount_to_goal.getText().clear();
        goal_month.getText().clear();
        goal_year.getText().clear();
    }

    public void enter_new_goal(View view) {
        mGoals.add(goal_name.getText().toString());
        mGoalAmount.add(Integer.parseInt(goal_amount.getText().toString()));
        int temp_month = Integer.parseInt(goal_month.getText().toString()) - 1;
        int temp_year = Integer.parseInt(goal_year.getText().toString());
        Date temp = new Date(temp_year, temp_month, 3);
        mDate.add(temp);
        goalsadapter.notifyDataSetChanged();
        make_invisible();
        goals_recycler_view.setVisibility(View.VISIBLE);
        goal_input.setVisibility(View.VISIBLE);
        goal_input_money.setVisibility(View.VISIBLE);
        goal_name.getText().clear();
        goal_amount.getText().clear();
        goal_month.getText().clear();
        goal_year.getText().clear();
    }
    public int money_remaining(){
        int totalMade = 0;
        for(int x = 0; x < mPay.size(); x++){
            totalMade += (mPay.get(x) * mFreq.get(x) * 4);
        }
        int totalNeeded = 0;
        for(int y : mAmount){
            totalNeeded += y;
        }
        return (totalMade - totalNeeded - applied_to_goal);

    }
    public void make_invisible(){
        goal_input.setVisibility(View.GONE);
        enter_goal.setVisibility(View.GONE);
        goal_name.setVisibility(View.GONE);
        goal_amount.setVisibility(View.GONE);
        goal_month.setVisibility(View.GONE);
        goal_year.setVisibility(View.GONE);
        goal_input_money.setVisibility(View.GONE);
        input_which_goal.setVisibility(View.GONE);
        goals_recycler_view.setVisibility(View.GONE);
        input_amount_to_goal.setVisibility(View.GONE);
        enter_money_for_goal.setVisibility(View.GONE);

        jobs_recycler_view.setVisibility(View.GONE);
        job_name.setVisibility(View.GONE);
        job_pay.setVisibility(View.GONE);
        job_freq.setVisibility(View.GONE);
        job_input.setVisibility(View.GONE);
        enter_job.setVisibility(View.GONE);
        expenses_recycler_view.setVisibility(View.GONE);

        bill_name.setVisibility(View.GONE);
        bill_amount.setVisibility(View.GONE);
        bill_freq.setVisibility(View.GONE);
        enter_bill.setVisibility(View.GONE);
        bill_input.setVisibility(View.GONE);
        expenses_remaining.setVisibility(View.GONE);

        app_header.setVisibility(View.GONE);
        logo.setVisibility(View.GONE);
        welcome_header.setVisibility(View.GONE);
    }

    public void update_remaining(){
        remaining = money_remaining();
        expenses_remaining.setText("$" + remaining + " remaining");
    }
}
