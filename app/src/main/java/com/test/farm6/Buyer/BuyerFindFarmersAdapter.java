package com.test.farm6.Buyer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.farm6.R;
import com.test.farm6.model.Farmer;

import java.util.List;

public class BuyerFindFarmersAdapter extends RecyclerView.Adapter<BuyerFindFarmersAdapter.FindFarmerViewHolder> {

    private List<Farmer> list;
    private FindFarmerClickListener listener;

    public BuyerFindFarmersAdapter(List<Farmer> list, FindFarmerClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BuyerFindFarmersAdapter.FindFarmerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buyer_find_farmer_row, parent, false);
        return new BuyerFindFarmersAdapter.FindFarmerViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FindFarmerViewHolder holder, int position) {
        holder.business_name.setText(list.get(position).getBusinessName());
        holder.address.setText(list.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FindFarmerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView business_name;
        TextView address;
        FindFarmerClickListener listener;


        public FindFarmerViewHolder(@NonNull View itemView, FindFarmerClickListener listener) {
            super(itemView);

            business_name = itemView.findViewById(R.id.find_farmer_List_business_name);
            address = itemView.findViewById(R.id.find_farmer_list_address);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(list.get(getAdapterPosition()));
            }
        }
    }

    public interface FindFarmerClickListener {
        void onItemClick(Farmer farmer);
    }
}
