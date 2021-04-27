package com.test.farm6.Farmer.Order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.test.farm6.R;
import com.test.farm6.model.Order;

import java.util.List;

public class FarmerOrderAdapter extends RecyclerView.Adapter<FarmerOrderAdapter.MyViewHolder> {
    private List<Order> list;
    private ListItemClickListener listener;

    public FarmerOrderAdapter(List<Order> list, ListItemClickListener listener){
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FarmerOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.farmer_order_row,parent,false);
       return new MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.orderNumber.setText("Order Number: " + (position +1));
        holder.orderStatus.setText(list.get(position).getStatus());
        holder.orderNumber.setOnClickListener(v -> listener.onItemClick(position));
        holder.orderStatus.setOnClickListener(v -> listener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView orderNumber;
        TextView orderStatus;
        ListItemClickListener listener;

        public MyViewHolder(View itemView, ListItemClickListener listener){
            super(itemView);
            orderNumber = itemView.findViewById(R.id.order_number);
            orderStatus = itemView.findViewById(R.id.order_status);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            if(listener !=null) {
                listener.onItemClick(getAdapterPosition());
            }
        }
    }

    public interface ListItemClickListener{
        void onItemClick(int position);
    }
}
