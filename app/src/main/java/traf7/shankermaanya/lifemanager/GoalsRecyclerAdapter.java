package traf7.shankermaanya.lifemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GoalsRecyclerAdapter extends RecyclerView.Adapter<GoalsRecyclerAdapter.ViewHolder> {
    ArrayList<String> mGoals = new ArrayList<>();
    ArrayList<Date> mDate = new ArrayList<>();
    ArrayList<Integer> mAmount = new ArrayList<>();
    int[] color;
    Context mContext;

    public GoalsRecyclerAdapter(ArrayList<String> mGoals, ArrayList<Date> date, ArrayList<Integer> mAmount, Context mContext) {
        this.mGoals = mGoals;
        this.mDate = date;
        this.mAmount = mAmount;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_layout_recycler_view, parent, false);
        GoalsRecyclerAdapter.ViewHolder holder = new GoalsRecyclerAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.goal.setText(mGoals.get(position));
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yy");
        String strDate= formatter.format(mDate.get(position));
        holder.date.setText("" + strDate);
        holder.amount.setText("$" + mAmount.get(position));
        holder.number.setText("" + (position + 1));
        holder.number.setTextColor(color[position % 5]);
        holder.goal.setTextColor(color[position % 5]);
        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(mContext, "Removing Goal", Toast.LENGTH_SHORT).show();
                mGoals.remove(position);
                mDate.remove(position);
                mAmount.remove(position);
                notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGoals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView goal, date, amount, number;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number_in_queue);
            goal = itemView.findViewById(R.id.goal_name);
            date = itemView.findViewById(R.id.goal_date);
            amount = itemView.findViewById(R.id.goal_amount);
            parentLayout = itemView.findViewById(R.id.goals_recycler_layout);
        }
    }
}
