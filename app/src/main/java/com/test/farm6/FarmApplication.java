package com.test.farm6;

import android.app.Application;

import com.test.farm6.model.Farmer;
import com.test.farm6.model.Order;
import com.test.farm6.model.Stock;
import com.test.farm6.model.User;

import java.util.Objects;

public class FarmApplication extends Application {
    private FarmDAO dao;
    private User currentUser;
    private Order currentOrder = new Order();

    @Override
    public void onCreate() {
        super.onCreate();
        dao = new FarmDAO();
    }

    public FarmDAO getDao() {
        return dao;
    }

    public void setDao(FarmDAO dao) {
        this.dao = dao;
    }

    public User getCurrentUser() { return currentUser; }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Order getCurrentOrder() { return currentOrder; }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public void placeOrder() {
        currentOrder.setOwner(currentUser);
        currentOrder.setStatus("Unprocessed");
        currentOrder.setTotal(currentOrder.calcTotal());

        dao.saveOrder(this.currentOrder);
        dao.saveUserOrder(this.currentUser, this.currentOrder);
        this.currentOrder= new Order();
    }

    public void updateStock(Stock stock, Farmer farmer){
        for ( int i = 0; i < farmer.getStock().size(); i++) {
            String temp1 = stock.getProduct().getName();
            String temp2 = farmer.getStock().get(i).getProduct().getName();
           // if(stock.getProduct().getName().equals(farmer.getStock().get(i).getProduct().getName())){
            System.out.println("This is temp 1 " + temp1);
            System.out.println("This is temp 2 " + temp2);
            if(temp1.equals(temp2)){
                farmer.getStock().get(i).getProduct().setPrice(stock.getProduct().getPrice());
                farmer.getStock().get(i).setWeight(stock.getWeight());
            }
        }
    }
}
