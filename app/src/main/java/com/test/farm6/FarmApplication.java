package com.test.farm6;

import android.app.Application;
import com.test.farm6.FarmDAO;
import com.test.farm6.model.Order;
import com.test.farm6.model.Product;
import com.test.farm6.model.User;

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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }
}
