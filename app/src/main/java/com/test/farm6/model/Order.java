package com.test.farm6.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Order implements Parcelable {
    private ArrayList<OrderLine> orderLines;
    private boolean status;
    private String orderStatus;
    private String orderTotal;
    private String items;


    public Order(){
        this.status = false;
        orderLines = new ArrayList<>();
        this.orderStatus = orderStatus();
        this.orderTotal = String.valueOf(calcTotal());
        this.items = getList();
    }

    protected Order(Parcel in) {
        status = in.readByte() != 0;
        orderStatus = in.readString();
        orderTotal = in.readString();
        items = in.readString();

    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() { return 0; }

    public String getOrderStatus(){ return orderStatus; }

    public String getOrderTotalString(){ return orderTotal; }

    String temp = "";
    public String getList(){
        for (OrderLine orderLine : orderLines) {
            temp = temp + orderLine.toString() + '\n';
        }
        return temp;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (status ? 1 : 0));
        dest.writeString(orderStatus);
        dest.writeString(String.valueOf(calcTotal()));
        dest.writeString((items));
    }

    public double calcTotal(){
        double total = 0;

        for (OrderLine orderLine : orderLines) {
            total = total + orderLine.getProduct().getPrice()*orderLine.getQuantity();
        }

        return total;
    }

    public String orderStatus(){
        String state = "Processed";
        if (!status){
            state = "Unprocessed";
        }
        return state;
    }

    @Override
    public String toString() {
        String return_string = "This Order is order status " + orderStatus() +"\n";
        for (OrderLine orderLine: orderLines) {
            return_string = return_string + orderLine.toString() + "\n";
        }

        return_string =  return_string + "This is you total " + calcTotal();

        return return_string;
    }


    public OrderLine get(int position){ return orderLines.get(position); }

    public int getPositionNumber(int position){
        return position;
    };

    public void addProduct(Product p){ /*orderLines.add(p);*/ }

    public boolean getStatus(){ return status; }

    public void setStatus(boolean newValue){ status = newValue; }

    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
