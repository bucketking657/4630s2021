package com.test.farm6.Farmer.Order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.R;
import com.test.farm6.model.OrderLine;

import java.util.List;

public class FarmerOrderDisplayAdapter extends RecyclerView.Adapter<FarmerOrderDisplayAdapter.OrderViewHolder> {
    private List<OrderLine> orderLineList;

    public FarmerOrderDisplayAdapter(List<OrderLine> orderLineList){
        this.orderLineList = orderLineList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.farmer_order_display_row,parent, false);
        return new FarmerOrderDisplayAdapter.OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.productName.setText(orderLineList.get(position).getProduct().getName());
        holder.productPrice.setText(String.format("%.2f$",orderLineList.get(position).getQuantity()));
        holder.productQuantity.setText(String.format("%.2f$",orderLineList.get(position).calcTotal()));
    }

    @Override
    public int getItemCount() {
        return orderLineList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView productName;
        TextView productPrice;
        TextView productQuantity;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.farmer_order_productNameTextView);
            productPrice = itemView.findViewById(R.id.farmer_order_product_Quanitity);
            productQuantity = itemView.findViewById(R.id.farmer_order_productTotalPriceTextView);

        }
    }
}
