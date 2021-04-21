package com.test.farm6.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Stock implements Parcelable {
    private Product product;
    private Integer amount;

    public Stock() {
    }

    public Stock(Parcel in) {
        this.product = in.readParcelable(Product.class.getClassLoader());
        this.amount = in.readInt();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(product,0);
        dest.writeInt(amount);

    }
}
