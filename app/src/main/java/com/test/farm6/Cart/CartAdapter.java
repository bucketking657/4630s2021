package com.test.farm6.Cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;
import com.test.farm6.R;
import com.test.farm6.model.OrderLine;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<OrderLine> list;
    private EditItemClickListener listener;
    public CartAdapter (List<OrderLine> list, EditItemClickListener listener ){
        this.list = list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buyer_cart_row, parent,false);
        return new CartAdapter.CartViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        holder.cartStockName.setText(list.get(position).getProduct().getName());
        holder.cartStockQuantity.setText(String.valueOf(list.get(position).getQuantity()));
        holder.cartStockTotal.setText(String.valueOf(list.get(position).calcTotal()));
    }

    @Override
    public int getItemCount() { return list.size();}

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView cartStockName;
        TextView cartStockQuantity;
        TextView cartStockTotal;
        EditItemClickListener listener;

        public CartViewHolder(@NonNull View itemView, EditItemClickListener listener ){
            super(itemView);
            cartStockName = itemView.findViewById(R.id.cart_productNameTextView);
            cartStockQuantity = itemView.findViewById(R.id.cart_product_Quanitity);
            cartStockTotal = itemView.findViewById(R.id.cart_productTotalPriceTextView);
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
    public interface EditItemClickListener{
        void onItemClick(OrderLine orderLine);
    }
}
