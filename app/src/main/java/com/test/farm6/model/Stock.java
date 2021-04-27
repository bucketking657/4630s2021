package com.test.farm6.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Stock implements Parcelable {
    private String id;
    private Product product;
    private Double weight;

    public Stock() {
        Product product = new Product();
    }

    public Stock(Parcel in) {
        this.id = in.readString();
        this.product = in.readParcelable(Product.class.getClassLoader());
        this.weight = in.readDouble();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Stock> CREATOR = new Creator<Stock>() {
        @Override
        public Stock createFromParcel(Parcel in) {
            return new Stock(in);
        }

        @Override
        public Stock[] newArray(int size) {
            return new Stock[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(product,0);
        dest.writeDouble(weight);
    }
}
