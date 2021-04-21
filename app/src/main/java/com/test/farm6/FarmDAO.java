package com.test.farm6;

import android.app.Activity;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.test.farm6.model.Farmer;
import com.test.farm6.model.Order;
import com.test.farm6.model.Product;
import com.test.farm6.model.Stock;
import com.test.farm6.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;

public class FarmDAO {


    private final FirebaseDatabase database;

    public FarmDAO() {
        database = FirebaseDatabase.getInstance();
    }

    public void retrieveOrdersForUser() {

    }

    public void saveOrder(Order order) {
        database.getReference("orders").push().setValue(order);
    }

    public void saveUser(User buyer, String uid) {
        database.getReference("users").child(uid).setValue(buyer);
    }

    public void saveFarmer(Farmer farmer, String userid) {
        database.getReference("farmers").child(userid).setValue(farmer);
    }

    public void saveFarmerStock(Stock stock, String uid) {

        database.getReference("farmers").child(uid).child("Stock").push().setValue(stock);
    }

    public void addFarmerToUserList(Farmer farmer, User currentUser) {
        /** List<String> farmers = new ArrayList<>();
         farmers.add(farmer.getId());
         System.out.println("here "+farmer.getId());
         database.getReference("users").child(currentUser.getId()).child("farmers").push().setValue(farmer.getId());
         */
        database.getReference("users").child(currentUser.getId()).child("farmers").push().setValue(farmer.getId());
    }

    public void retrieveUserFarmers(String userId, RetrieveFarmersHandler retrieveFarmersHandler) {
        database.getReference("users").child(userId).child("farmers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Farmer> farmers = new ArrayList<>();
                Iterable<DataSnapshot> children = snapshot.getChildren();
                List<String> farmerIds = new ArrayList();
                for (DataSnapshot farmerId : children) {
                    farmerIds.add(farmerId.getValue(String.class));
                }
                retrieveFarmers(new RetrieveFarmersHandler() {
                    @Override
                    public void farmersRetrieved(List<Farmer> farmers) {
                        List<Farmer> result = new ArrayList<>();
                        for (Farmer farmer: farmers) {
                            if (farmerIds.contains(farmer.getId())){
                                result.add(farmer);
                            }
                        }
                        retrieveFarmersHandler.farmersRetrieved(result);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Working on
    public interface RetrieveStockForFarmerHandler {
        public void stockRetrieved(List<Stock> stocks);
    }

    public void retrieveStockForFarmer(RetrieveStockForFarmerHandler handler) {
        database.getReference("farmers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Stock> stocks = new ArrayList<>();
                Iterable<DataSnapshot> children = snapshot.getChildren();
                for (DataSnapshot stockDataSnapshot : children) {
                    Stock stock = stockDataSnapshot.getValue(Stock.class);
                    stocks.add(stock);
                }
                handler.stockRetrieved(stocks);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public interface RetrieveFarmerHandler {
        public void farmerRetrieved(Farmer farmer);
    }

    public void findFarmerByUid(String uid, RetrieveFarmerHandler retrieveFarmerHandler) {

        database.getReference("farmers").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Farmer farmer = snapshot.getValue(Farmer.class);
                farmer.setId(snapshot.getKey());
                retrieveFarmerHandler.farmerRetrieved(farmer);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public interface RetrieveUserHandler {
        public void userRetrieved(User user);
    }

    public void findUserByUid(String uid, RetrieveUserHandler retrieveUserHandler) {
        database.getReference("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                user.setId(snapshot.getKey());
                retrieveUserHandler.userRetrieved(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void findFarmerByUid(String uid) {
        database.getReference("farmers").child(uid);
    }

    public interface RetrieveFarmersHandler {
        public void farmersRetrieved(List<Farmer> farmers);
    }

    public void retrieveFarmers(RetrieveFarmersHandler handler) {
        database.getReference("farmers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Farmer> farmers = new ArrayList<>();
                Iterable<DataSnapshot> children = snapshot.getChildren();
                for (DataSnapshot farmerDataSnapshot : children) {
                    Farmer farmer = farmerDataSnapshot.getValue(Farmer.class);
                    farmer.setId(farmerDataSnapshot.getKey());
                    farmers.add(farmer);
                }
                handler.farmersRetrieved(farmers);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public interface RetrieveProductsHandler {
        public void productsRetrieved(List<Product> products);
    }

    public void retrieveProducts() {

    }

    public void saveProduct(Product product) {
        database.getReference("products").push().setValue(product);
    }


}
