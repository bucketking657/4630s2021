package com.test.farm6.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.test.farm6.FarmApplication;

import java.util.ArrayList;
import java.util.List;

public class Order implements Parcelable {

    private String id;
    private User owner;
    private List<OrderLine> orderLines = new ArrayList<>();
    private String status;
    private Double total;
    private Integer position = 0;
    private Farmer farmer;


    public Order() {
        this.orderLines = new ArrayList<>();
        this.status = orderStatus();
        this.total = calcTotal();
    }

    protected Order(Parcel in) {
        id = in.readString();
        owner = in.readParcelable(User.class.getClassLoader());
        Integer size = in.readInt();
        orderLines.clear();
        for(int i=0;i<size;i++){
            orderLines.add(in.readParcelable(OrderLine.class.getClassLoader()));
        }
        status = in.readString();
        total = in.readDouble();
        position = in.readInt();
        farmer = in.readParcelable(Farmer.class.getClassLoader());
    }

    public User getOwner() { return owner; }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public String getStatus() { return status; }

    public Double getOrderTotalString() { return total; }

    public String getList() {
        String temp = "";
        for (OrderLine orderLine : orderLines) {
            temp = temp + orderLine.toString() + '\n';
        }
        return temp;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(owner, 0);
        dest.writeInt(orderLines.size());
        for(OrderLine orderLine: orderLines){
            dest.writeParcelable(orderLine,0);
        }
        dest.writeString(status);
        dest.writeDouble(calcTotal());
        dest.writeInt(position);
        dest.writeParcelable(farmer, 0);
    }

    public double calcTotal() {
        double total = 0;

        for (OrderLine orderLine : orderLines) {
            total = total + orderLine.calcTotal();
        }
        return total;
    }

    public String orderStatus() {
        String state = "Processed";
        return state;
    }

    @Override
    public String toString() {
        String return_string = "This Order is order status " + orderStatus() + "\n";
        for (OrderLine orderLine : orderLines) {
            return_string = return_string + orderLine.toString() + "\n";
        }
        return_string = return_string + "This is you total " + calcTotal();

        return return_string;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Farmer getFarmer() { return farmer; }

    public void setFarmer(Farmer farmer) { this.farmer = farmer; }


}
