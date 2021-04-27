package com.test.farm6.Buyer.MyFarmers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.R;
import com.test.farm6.model.Farmer;

import java.util.List;

public class BuyerMyFarmerAdapter extends RecyclerView.Adapter<BuyerMyFarmerAdapter.MyFarmerViewHolder> {

    private List<Farmer> list;
    private FarmerListClickListener listener;

    public BuyerMyFarmerAdapter(List<Farmer> list, FarmerListClickListener listener){
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BuyerMyFarmerAdapter.MyFarmerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buyer_my_farmer_farmer_row, parent, false);
        return new BuyerMyFarmerAdapter.MyFarmerViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyerMyFarmerAdapter.MyFarmerViewHolder holder, int position) {
        holder.business_name.setText(list.get(position).getBusinessName());
        holder.address.setText(list.get(position).getAddress());
        holder.city.setText(list.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyFarmerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView business_name;
        TextView address;
        TextView city;
        FarmerListClickListener listener;

        public MyFarmerViewHolder(@NonNull View itemView, FarmerListClickListener listener ) {
            super(itemView);
            business_name= itemView.findViewById(R.id.farmer_List_business_name);
            address = itemView.findViewById(R.id.farmer_list_address);
            city = itemView.findViewById(R.id._farmer_list_city);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            if(listener != null){
                listener.onItemClick(list.get(getAdapterPosition()));
            }
        }
    }

    public interface FarmerListClickListener{
        void onItemClick(Farmer farmer);
    }
}
