package com.test.farm6.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.test.farm6.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Farmer extends User  {

    private String address;
    private String city;
    private String businessName;
    private Map<String,Stock> Stock = new HashMap<>();

    public Farmer(){
        super();
    }

    public Farmer(String first_name, String last_name, String email, String password, String address, String city, String businessName){
        super(first_name, last_name, email, password);
        this.address = address;
        this.city = city;
        this.businessName = businessName;
    }

    protected Farmer(Parcel in) {
        super(in);
        address = in.readString();
        city = in.readString();
        businessName = in.readString();
        Stock = new HashMap<>();
        int size = in.readInt();
        for(int i = 0; i < size; i++){
            String key = in.readString();
            Stock value = in.readParcelable(com.test.farm6.model.Stock.class.getClassLoader());
            Stock.put(key,value);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(businessName);
        if (Stock != null) {
            dest.writeInt(Stock.size());
            for (Map.Entry<String, Stock> entry : Stock.entrySet()) {
                dest.writeString(entry.getKey());
                dest.writeParcelable(entry.getValue(), 0);
            }
        }else{
            dest.writeInt(0);
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Farmer> CREATOR = new Creator<Farmer>() {
        @Override
        public Farmer createFromParcel(Parcel in) {
            return new Farmer(in);
        }

        @Override
        public Farmer[] newArray(int size) {
            return new Farmer[size];
        }
    };

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Map<String, Stock> getStock() {
        return Stock;
    }

    public void setStock(Map<String, com.test.farm6.model.Stock> stock) {
        Stock = stock;
    }
    public Stock getStockItem(int i ){
        return Stock.get(i);
    }

    public Farmer basicFarmer() {
        Farmer farmer = new Farmer();
        farmer.setId(this.id);
        farmer.setFirstName(this.firstName);
        farmer.setLastName(this.lastName);
        return farmer;
    }

}
