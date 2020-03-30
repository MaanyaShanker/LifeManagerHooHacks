package traf7.shankermaanya.lifemanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class JobsRecyclerAdapter extends RecyclerView.Adapter<JobsRecyclerAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    ArrayList<String> mJobs = new ArrayList<>();
    ArrayList<Integer> mPay = new ArrayList<>();
    ArrayList<Integer> mFreq = new ArrayList<>();
    int[] color;
    private Context mContext;

    public JobsRecyclerAdapter(ArrayList<String> mJobs, ArrayList<Integer> mPay, ArrayList<Integer> mFreq, Context mContext) {
        this.mJobs = mJobs;
        this.mPay = mPay;
        this.mFreq = mFreq;
        this.mContext = mContext;
        color = new int[5];
        color[0] = ContextCompat.getColor(mContext, R.color.pink);
        color[1] = ContextCompat.getColor(mContext, R.color.purple);
        color[2] = ContextCompat.getColor(mContext, R.color.blue);
        color[3] = ContextCompat.getColor(mContext, R.color.tealish);
        color[4] = ContextCompat.getColor(mContext, R.color.green);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_layout_recycler_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called"); // for debugging

        holder.job.setText(mJobs.get(position));
        holder.pay.setText("$" + mPay.get(position));
        holder.freq.setText(mFreq.get(position) + " hrs/week");
        holder.number.setText("" + (position + 1));
        holder.number.setTextColor(color[position % 5]);
        holder.job.setTextColor(color[position % 5]);
        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(mContext, "Removing Job", Toast.LENGTH_SHORT).show();
                mJobs.remove(position);
                mPay.remove(position);
                mFreq.remove(position);
                notifyDataSetChanged();
                return true;
            }
        });
    }

//    @Override
//    public boolean onLongClick(View view) {
//        ViewHolder holder = (ViewHolder) view.getTag();
//        if (view.getId() == holder.job.getId()) {
//            mJobs.remove(holder.getPosition());
//            mPay.remove(holder.getPosition());
//            mFreq.remove(holder.getPosition());
//
//            notifyDataSetChanged();
////
////            Toast.makeText(sContext, "Item " + holder.mNameTextView.getText() + " has been removed from list",
////                    Toast.LENGTH_SHORT).show();
//        }
//        return false;
//    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView job, pay, freq, number;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number_in_queue);
            job = itemView.findViewById(R.id.job_name);
            pay = itemView.findViewById(R.id.amount_per_hour);
            freq = itemView.findViewById(R.id.hours_per_week);
            parentLayout = itemView.findViewById(R.id.job_recycler_layout);
        }
    }
}
