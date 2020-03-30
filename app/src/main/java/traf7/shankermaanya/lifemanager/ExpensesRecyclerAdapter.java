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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ExpensesRecyclerAdapter extends RecyclerView.Adapter<ExpensesRecyclerAdapter.ViewHolder> {
    private static final String TAG = "ExpensesRecyclerAdapter";

    ArrayList<String> mBills = new ArrayList<>();
    ArrayList<Integer> mAmount = new ArrayList<>();
    int[] color;
    private Context mContext;

    public ExpensesRecyclerAdapter(ArrayList<String> mBills, ArrayList<Integer> mAmount, Context mContext) {
        this.mBills = mBills;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenses_layout_recycler_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called"); // for debugging

        holder.bill.setText(mBills.get(position));
        holder.amount.setText("$" + mAmount.get(position) + "/month");
        holder.number.setText("" + (position + 1));
        holder.number.setTextColor(color[position % 5]);
        holder.bill.setTextColor(color[position % 5]);
        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(mContext, "Removing Expense", Toast.LENGTH_SHORT).show();
                mBills.remove(position);
                mAmount.remove(position);
                notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBills.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView bill, amount, number;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number_in_queue);
            bill = itemView.findViewById(R.id.bill_name);
            amount = itemView.findViewById(R.id.bill_amount);
            parentLayout = itemView.findViewById(R.id.expenses_recycler_layout);
        }
    }
}
