package com.test.farm6.Buyer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.Farmer.FarmerStockAdapter;
import com.test.farm6.R;
import com.test.farm6.model.Stock;

import java.util.List;

public class BuyerViewFarmerStockAdapter extends RecyclerView.Adapter<BuyerViewFarmerStockAdapter.MyViewHolder> {
    private List<Stock> list;
    private ListItemClickListener listener;

    public BuyerViewFarmerStockAdapter(List<Stock> list, ListItemClickListener listener){
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.farmer_stock_row, parent, false);
        return new BuyerViewFarmerStockAdapter.MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.productName.setText(list.get(position).getProduct().getName());
        holder.productPrice.setText(String.valueOf(list.get(position).getProduct().getPrice()));
        holder.productQuantity.setText(""+list.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ListItemClickListener listener;
        TextView productName;
        TextView productPrice;
        TextView productQuantity;

        public MyViewHolder(@NonNull View itemView, ListItemClickListener listener) {
            super(itemView);
            productName = itemView.findViewById(R.id.stock_row_name);
            productPrice = itemView.findViewById(R.id.stock_row_price);
            productQuantity = itemView.findViewById(R.id.stock_row_quantity);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            if(listener != null) {
                listener.onItemClick(list.get(getAdapterPosition()));
            }
        }
    }

    public interface ListItemClickListener{
        void onItemClick(Stock stock);
    }
}