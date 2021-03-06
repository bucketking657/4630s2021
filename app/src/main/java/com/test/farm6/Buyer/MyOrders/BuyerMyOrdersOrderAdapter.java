package com.test.farm6.Buyer.MyOrders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.test.farm6.R;
import com.test.farm6.model.Order;
import java.util.List;

public class BuyerMyOrdersOrderAdapter extends RecyclerView.Adapter<BuyerMyOrdersOrderAdapter.MyViewHolder> {
    private List<Order> list;
    private OrderListItemClickListener listener;

    public BuyerMyOrdersOrderAdapter(List<Order> list, OrderListItemClickListener listener){
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BuyerMyOrdersOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buyer_order_row, parent,false);
        return new BuyerMyOrdersOrderAdapter.MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.orderNumber.setText("Order Number: " + (position + 1));
        holder.orderStatus.setText(list.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView orderNumber;
        TextView orderStatus;
        OrderListItemClickListener listener;

        public MyViewHolder(View itemView, OrderListItemClickListener listener){
            super(itemView);
            orderNumber = itemView.findViewById(R.id.buyer_order_number);
            orderStatus = itemView.findViewById(R.id.buyer_order_status);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            if(listener != null){
                listener.onItemClick(getAdapterPosition());
            }
        }
    }
    public interface OrderListItemClickListener{
        void onItemClick(int position);
    }
}
