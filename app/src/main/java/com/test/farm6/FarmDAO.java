package com.test.farm6;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.test.farm6.model.Farmer;
import com.test.farm6.model.Order;
import com.test.farm6.model.Product;
import com.test.farm6.model.Stock;
import com.test.farm6.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FarmDAO {


    private final FirebaseDatabase database;

    public FarmDAO() {
        database = FirebaseDatabase.getInstance();
    }

    public void saveOrder(Order order) {
        order.setOwner(order.getOwner().basicUser());
        order.setFarmer(order.getFarmer().basicFarmer());
        database.getReference("farmers").child(order.getFarmer().getId()).child("orders").push().setValue(order);
    }

    public void saveUserOrder(User user, Order order) {
        database.getReference("users").child(user.getId()).child("orders").push().setValue(order);
    }

    public void saveUser(User buyer, String uid) {
        database.getReference("users").child(uid).setValue(buyer);
    }

    public void saveFarmer(Farmer farmer, String userid) {
        database.getReference("farmers").child(userid).setValue(farmer);
    }

    boolean flag = false;
    public void saveFarmerStock(Stock stock, String uid) {
        DatabaseReference dataref = database.getReference("farmers").child(uid);

        dataref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild("Stock")) {
                    flag = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if (!flag) {
            dataref.push().setValue("Stock");
        }
        DatabaseReference push = database.getReference("farmers").child(uid).child("Stock").push();
        push.setValue(stock);
        stock.setId(push.getKey());
    }

    public void addFarmerToUserList(Farmer farmer, User currentUser) {
        DatabaseReference dataref = database.getReference("users").child(currentUser.getId());

        dataref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild("farmers")) {
                    flag = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (!flag) {
            dataref.push().setValue("farmers");
        }
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
                        for (Farmer farmer : farmers) {
                            if (farmerIds.contains(farmer.getId())) {
                                result.add(farmer);
                                Map<String, Stock> stocks = farmer.getStock();
                                for (String id : stocks.keySet()) {
                                    stocks.get(id).setId(id);
                                }
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

    public void updateStock(User farmer, Stock stock) {
        System.out.println(stock.getProduct().getPrice());
        System.out.println(stock.getWeight());
        database.getReference("farmers").child(farmer.getId()).child("Stock").child(stock.getId()).setValue(stock);
    }

    public interface ProcessOrderHandler {
        public void orderProcess(List<Order> orders);
    }

    public void processOrder(Order order) {
        database.getReference("farmers").child(order.getFarmer().getId()).child("orders").child(order.getId()).child("status").setValue("processed");
        database.getReference("users").child(order.getOwner().getId()).child("orders").child(order.getId()).child("status").setValue("processed");
    }

    private void processOrderHandler(ProcessOrderHandler processOrderHandler) {
    }

    public interface RetrieverOrderHandler {
        public void orderRetrieved(List<Order> orders);
    }

    public void retrieveOrderForFarmer(String uid, RetrieverOrderHandler retrieverOrderhandler) {
        database.getReference("farmers").child(uid).child("orders").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Order> orders = new ArrayList<>();
                Iterable<DataSnapshot> children = snapshot.getChildren();
                for (DataSnapshot orderSnapShot : children) {
                    Order order = orderSnapShot.getValue(Order.class);
                    order.setId(orderSnapShot.getKey());
                    orders.add(order);
                }
                retrieverOrderhandler.orderRetrieved(orders);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void retrieveOrderForUser(String uid, RetrieverOrderHandler retrieverOrderHandler) {
        database.getReference("users").child(uid).child("orders").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Order> orders = new ArrayList<>();
                Iterable<DataSnapshot> children = snapshot.getChildren();
                for (DataSnapshot orderSnapShot : children) {
                    Order order = orderSnapShot.getValue(Order.class);
                    orders.add(order);
                }
                retrieverOrderHandler.orderRetrieved(orders);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

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
                Map<String, Stock> stocks = farmer.getStock();
                for (String id : stocks.keySet()) {
                    stocks.get(id).setId(id);
                }
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

    public void saveProduct(Product product) {
        database.getReference("products").push().setValue(product);
    }
}
