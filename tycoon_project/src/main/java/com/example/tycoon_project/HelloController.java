package com.example.tycoon_project;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private Label cogsLabel, shopname, username, dayofweek, weekspassed, customers, moneymade, bills, netprofit, alltimeprofit, balanceLabel, shopLabel, billLabel1, billLabel2, billLabel3;

    @FXML
    private AnchorPane startPane, setupPane, gamePane, winScreen, loseScreen;

    @FXML
    private Button investmentButton;

    @FXML
    private ListView flavorsMenu, boughtFlavorsList, equipmentListview, gameScreenFlavorBoughtListview, gameScreenEquipmentBuyListview, gameScreenFlavorListview;

    @FXML
    private ImageView back1, icecream1, icecream2, shopscreen, utility1, utility2, utility3, summarypic, winBack, loseBack;

    public List<Flavors> allFlavors = new ArrayList<Flavors>();
    public List <Flavors> boughtFlavors = new ArrayList<Flavors>();
    public List <Equipment> allEquipment = new ArrayList<>();
    public List <RandomEvent> events = new ArrayList<RandomEvent>();

    private int weeks;
    private int day;
    private int customerMultiplier = 1;
    private int weeklyCustomers = 1;
    private int shopSize = 0; //1 is small, 2 is medium, 3 is large

    private long customerTime;
    private long weekTime;
    private long dayTime;
    private long startTime;

    private long netProfit = 0;
    private long alltimemoney = 0;
    private long money = 100000;
    private long moneymadeWeek = 0;
    private long earningPerCustomer = 5;
    private long earnings = 0;
    private long allProfit = 0;
    private long allCosts = 0;
    private long investment = 0;
    private long waterBill = 200;
    private long elecBill = 150;
    private long rent = 300;
    private long cogs = 0;

    private String shopName = "";
    private String userName = "";

    private boolean bankruptcy = false;

    public void addStuff() {
        allFlavors.add(new Flavors("Chocolate", 40));
        allFlavors.add(new Flavors("Cookie Dough", 40));
        allFlavors.add(new Flavors("Strawberry", 40));
        allFlavors.add(new Flavors("Cookies and Cream", 40));
        allFlavors.add(new Flavors("Mango", 70));
        allFlavors.add(new Flavors("Moose Tracks", 70));
        allFlavors.add(new Flavors("New York Cheesecake", 70));
        allFlavors.add(new Flavors("Black Cherry Dark Chocolate", 70));
        allFlavors.add(new Flavors("Caramel Espresso Chip", 70));
        allFlavors.add(new Flavors("Chocolate Chip", 100));
        allFlavors.add(new Flavors("Butter Pecan", 100));
        allFlavors.add(new Flavors("Pumpkin Spice", 100));
        allFlavors.add(new Flavors("Peppermint Chocolate", 100));
        allFlavors.add(new Flavors("Pistachio", 100));
        allFlavors.add(new Flavors("Rocky Road", 125));
        allFlavors.add(new Flavors("Chocolate Peanut Butter", 125));
        allFlavors.add(new Flavors("Sea Salt Caramel", 125));
        allFlavors.add(new Flavors("Raspberry Vanilla", 125));
        allFlavors.add(new Flavors("Mint Chocolate Chip", 125));

        boughtFlavors.add(new Flavors("Vanilla", 40));

        allEquipment.add(new Equipment("Freezer", 400, 1));
        allEquipment.add(new Equipment("Ice Cream Bowls", 150, 1));
        allEquipment.add(new Equipment("Spoons", 150, 1));
        allEquipment.add(new Equipment("Blast Chiller", 1000, 1));
        allEquipment.add(new Equipment("Scoopers", 150, 1));
        allEquipment.add(new Equipment("Soft Serve Machine", 3500, 1));
        allEquipment.add(new Equipment("Glass Dipping Cabinet", 4000, 1));

        events.add(new RandomEvent("You won the lottery and invested the money in your shop! You gained $20,000 in profits!", 20000));
        events.add(new RandomEvent("Your shop sent promotional coupons to the local elementary school. You gained $350 in profits!", 350));
        events.add(new RandomEvent("Your shop catered ice cream to an event. You gained $500 in profits!", 500));
        events.add(new RandomEvent("You got gifted custom made decorations. More people are posting about your shop! You gained $700 in profits!", 700));
        events.add(new RandomEvent("An influencer with millions of followers posted about your shop. You gained new customers and $12,000 in profits!", 12000));
        events.add(new RandomEvent("A library opened next to you! There is a constant flow of customers in you shop! You gained $10,500 in profits!", 10500));
        events.add(new RandomEvent("Oh no! There was a tropical storm that caused your shop to lose electricity. The electrician scammed you and you lost $2,000.", (-2000)));
        events.add(new RandomEvent("Oh no! You accidentally bought 30 boxes of pickle flavored ice cream while you were sleepwalking! You lost $3000.", (-3000)));
        events.add(new RandomEvent("Oh no! Another ice cream shop opened by you! You had to spend an extra $5,000 on advertisements because you lost 1/4 of your customers.", (-5000)));
        events.add(new RandomEvent("Oh no! There was an uprising of COVID-19 and your shop closed for 1 week! You will lose this week's profits.", 0));
        events.add(new RandomEvent("Oh no! An employee robbed the safe at the shop! They stole $5,000! Don't leave your safe unlocked.", -5000));
        events.add(new RandomEvent("Oh no! There was a tornado in your town and it completely knocked over your shop. You have to rebuild from scratch :(", -1000000));
        events.add(new RandomEvent("Oh no! Your freezer broke and you lost all your ice cream. A new freezer costs $400 and you lost the money you spent on ice cream.", ((-1000 + (-allCosts)))));
    }

    @FXML
    protected void setBackground() {
        try {
            FileInputStream input1 = new FileInputStream("src/main/resources/pics/whiteback.jpg");
            FileInputStream input2 = new FileInputStream("src/main/resources/pics/icecream1.png");
            FileInputStream input3 = new FileInputStream("src/main/resources/pics/icecream2.png");

            icecream2.setImage(new Image(input3));
            icecream1.setImage(new Image(input2));
            back1.setImage(new Image(input1));
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void playAgain() {
        startPane.setVisible(true);
        winScreen.setVisible(false);
        loseScreen.setVisible(false);
        setBackground();
        flavorsMenu.getItems().clear();
        boughtFlavorsList.getItems().clear();
        equipmentListview.getItems().clear();
        gameScreenFlavorBoughtListview.getItems().clear();
        gameScreenEquipmentBuyListview.getItems().clear();
        gameScreenFlavorListview.getItems().clear();
        allFlavors.clear();
        boughtFlavors.clear();
        allEquipment.clear();
        events.clear();
        weeks = 0;
        day = 0;
        customerMultiplier = 1;
        weeklyCustomers = 1;
        shopSize = 0; //1 is small, 2 is medium, 3 is large

        customerTime = 0;
        weekTime = 0;
        dayTime = 0;
        startTime = 0;

        netProfit = 0;
        alltimemoney = 0;
        money = 100000;
        moneymadeWeek = 0;
        earningPerCustomer = 5;
        earnings = 0;
        allProfit = 0;
        allCosts = 0;
        investment = 0;
        waterBill = 200;
        elecBill = 150;
        rent = 300;
        cogs = 0;

        shopName = "";
        userName = "";

        bankruptcy = false;
    }
    @FXML
    protected void gameStart() {
        startPane.setVisible(false);
        setupPane.setVisible(true);
        loseScreen.setVisible(false);
        winScreen.setVisible(false);
        updateMoney(0);
        balanceLabel.setVisible(true);

        addStuff();

        JTextField shopname = new JTextField();
        JTextField username = new JTextField();
        Object[] message = {
                "Shop Name:", shopname,
                "Username:", username
        };

        JOptionPane.showMessageDialog(null, message, "~ Info ~", JOptionPane.OK_OPTION);
        shopName = shopname.getText();
        userName = username.getText();

        flavorsMenu.setCellFactory(param -> new ListCell<Flavors>() {
            @Override
            protected void updateItem(Flavors item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName() + "   Cost: $" + item.getCost());
                }
            }
        });
        boughtFlavorsList.setCellFactory(param -> new ListCell<Flavors>() {
            @Override
            protected void updateItem(Flavors item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        equipmentListview.setCellFactory(param -> new ListCell<Equipment>() {
            @Override
            protected void updateItem(Equipment item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName() + "   Cost: $" + item.getCost() + "   Quantity: " + item.getQuantity());
                }
            }
        });

        flavorsMenu.getItems().addAll(allFlavors);
        boughtFlavorsList.getItems().addAll(boughtFlavors);
        equipmentListview.getItems().addAll(allEquipment);
        boughtFlavorsList.setStyle("-fx-font-size: 14px; -fx-font-family: 'SketchFlow Print'; -fx-control-inner-background: c5ff96;");
        flavorsMenu.setStyle("-fx-font-size: 14px; -fx-font-family: 'SketchFlow Print'; -fx-control-inner-background: c5ff96;");
        equipmentListview.setStyle("-fx-font-size: 14px; -fx-font-family: 'SketchFlow Print'; -fx-control-inner-background: c5ff96;");
        billLabel1.setText("Weekly Water Bill: $" + waterBill);
        billLabel2.setText("Weekly Electricity Bill: $" + elecBill);
        billLabel3.setText("Weekly Rent: $" + rent);

        for (Flavors f : boughtFlavors) {
            allCosts += f.getCost();
        }
        try {
            FileInputStream input1 = new FileInputStream("src/main/resources/pics/icecream3.jpg");
            FileInputStream input2 = new FileInputStream("src/main/resources/pics/watericon.png");
            FileInputStream input3 = new FileInputStream("src/main/resources/pics/elecicon.png");
            FileInputStream input4 = new FileInputStream("src/main/resources/pics/renticon.png");

            shopscreen.setImage(new Image(input1));
            utility1.setImage(new Image(input2));
            utility2.setImage(new Image(input3));
            utility3.setImage(new Image(input4));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void flavorBought(MouseEvent x) {
        Flavors F = (Flavors) flavorsMenu.getSelectionModel().getSelectedItem();
        updateMoney(-(F.getCost()));
        cogs += F.getCost() * shopSize;
        flavorsMenu.getItems().remove(F);
        allFlavors.remove(F);
        boughtFlavorsList.getItems().clear();
        boughtFlavors.add(F);
        earningPerCustomer++;
        boughtFlavorsList.getItems().addAll(boughtFlavors);
    }

    @FXML
    protected void equipmentBought(MouseEvent x) {
        Equipment E = (Equipment) equipmentListview.getSelectionModel().getSelectedItem();
        updateMoney(-(E.getCost()));
        cogs += E.getCost() * shopSize;
        equipmentListview.getItems().clear();
        E.setQuantity(E.getQuantity() + 1);
        equipmentListview.getItems().addAll(allEquipment);
    }

    @FXML
    protected void smallShop(ActionEvent event) {
        if(money >= 80000) {
            flavorsMenu.setVisible(true);
            equipmentListview.setVisible(true);
            boughtFlavorsList.setVisible(true);
            shopSize = 1;
            waterBill = 200;
            elecBill = 150;
            rent = 300;
            shopLabel.setText("Shop Size:  Small Shop");
        }
        updateMoney(-80000);
    }
    @FXML
    protected void mediumShop(ActionEvent event) {
        if (money >= 200000) {
            flavorsMenu.setVisible(true);
            equipmentListview.setVisible(true);
            boughtFlavorsList.setVisible(true);
            shopSize = 2;
            waterBill = 400;
            elecBill = 300;
            rent = 600;
            shopLabel.setText("Shop Size:  Medium Shop");
        }
        updateMoney(-200000);
    }
    @FXML
    protected void largeShop(ActionEvent event) {
        if (money >= 500000) {
            flavorsMenu.setVisible(true);
            equipmentListview.setVisible(true);
            boughtFlavorsList.setVisible(true);
            shopSize = 3;
            waterBill = 4000;
            elecBill = 3000;
            rent = 6000;
            shopLabel.setText("Shop Size:  Large Shop");
        }
        updateMoney(-500000);
    }

    public void updateMoney(long amt) {
        if (money + amt >= 0) {
            money += amt;
            balanceLabel.setText("Bank Account Balance: $" + money);
        }
        else {
            balanceLabel.setText("NOT ENOUGH MONEY");
        }
        if (money >= 10000000) {
            winScreen.setVisible(true);
            gamePane.setVisible(false);
            try {
                FileInputStream input1 = new FileInputStream("src/main/resources/pics/winBack.jpg");

                winBack.setImage(new Image(input1));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            startTime = -1;
            customerTime = -1;
            weekTime = -1;
            dayTime = -1;
        }
    }

    public void eventMoney(long amt) {
        money += amt;
        balanceLabel.setText("Bank Account Balance: $" + money);
        if(money < 0) {
            bankruptcy = true;
            loseScreen.setVisible(true);
            gamePane.setVisible(false);
            try {
                FileInputStream input1 = new FileInputStream("src/main/resources/pics/loseBack.jpg");

                loseBack.setImage(new Image(input1));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            startTime = -1;
            customerTime = -1;
            weekTime = -1;
            dayTime = -1;
        }
        else if (money >= 10000000) {
            winScreen.setVisible(true);
            gamePane.setVisible(false);
            try {
                FileInputStream input1 = new FileInputStream("src/main/resources/pics/winBack.jpg");

                winBack.setImage(new Image(input1));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        startTime = -1;
        customerTime = -1;
        weekTime = -1;
        dayTime = -1;
        }
    }

    public void eventChooser(){
        int eventIndex = (int)(Math.random() * events.size());
        events.get(9).setMoney(-moneymadeWeek);
        events.get(12).setMoney(-1000 + (-cogs));

        JFrame parent = new JFrame();
        String multiLineMsg[] = {events.get(eventIndex).getName(), "$ " + events.get(eventIndex).getMoney()};
        JOptionPane.showMessageDialog(parent, multiLineMsg);
        eventMoney((long) events.get(eventIndex).getMoney());
    }


    public void weeklySummary() {
        long allBills = elecBill + waterBill + rent;
        netProfit = moneymadeWeek - allBills - cogs;

        weekspassed.setText("Weeks Passed:   " + weeks + " Weeks");
        customers.setText("Customers this Week:   " + weeklyCustomers);
        moneymade.setText("Money Made this Week:   $" + moneymadeWeek);
        bills.setText("Bills:   $" + allBills);
        cogsLabel.setText("COGS:   $" + cogs);
        netprofit.setText("Net Profit:   $" + netProfit);
        alltimeprofit.setText("All Time Profit:   $" + allProfit);
    }

    public void setDay(int day) {
        if(day % 7 == 0) {
            dayofweek.setText("Day of Week:   Monday");
        }
        else if(day % 7 == 1) {
            dayofweek.setText("Day of Week:   Tuesday");
        }
        else if(day % 7 == 2) {
            dayofweek.setText("Day of Week:   Wednesday");
        }
        else if(day % 7 == 3) {
            dayofweek.setText("Day of Week:   Thursday");
        }
        else if(day % 7 == 4) {
            dayofweek.setText("Day of Week:   Friday");
        }
        else if(day % 7 == 5) {
            dayofweek.setText("Day of Week:   Saturday");
        }
        else if(day % 7 == 6) {
            dayofweek.setText("Day of Week:   Sunday");
        }
    }

    @FXML
    public void gamePane() {
        gamePane.setVisible(true);
        setupPane.setVisible(false);
        weeklySummary();

        gameScreenFlavorListview.setCellFactory(param -> new ListCell<Flavors>() {
            @Override
            protected void updateItem(Flavors item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName() + "   Cost: $" + item.getCost());
                }
            }
        });
        gameScreenFlavorBoughtListview.setCellFactory(param -> new ListCell<Flavors>() {
            @Override
            protected void updateItem(Flavors item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        gameScreenEquipmentBuyListview.setCellFactory(param -> new ListCell<Equipment>() {
            @Override
            protected void updateItem(Equipment item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName() + "   Cost: $" + item.getCost() + "   Quantity: " + item.getQuantity());
                }
            }
        });

        try {
            FileInputStream input1 = new FileInputStream("src/main/resources/pics/summarypic.png");

            summarypic.setImage(new Image(input1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        gameScreenFlavorListview.getItems().addAll(allFlavors);
        gameScreenFlavorBoughtListview.getItems().addAll(boughtFlavors);
        gameScreenEquipmentBuyListview.getItems().addAll(allEquipment);
        gameScreenFlavorListview.setStyle("-fx-font-size: 14px; -fx-font-family: 'SketchFlow Print'; -fx-control-inner-background: bd82cf;");
        gameScreenFlavorBoughtListview.setStyle("-fx-font-size: 14px; -fx-font-family: 'SketchFlow Print'; -fx-control-inner-background: bd82cf;");
        gameScreenEquipmentBuyListview.setStyle("-fx-font-size: 14px; -fx-font-family: 'SketchFlow Print'; -fx-control-inner-background: bd82cf;");
        investmentButton.setStyle("-fx-background-color: #de9eff; -fx-border-color: #c23fff; -fx-border-width: 2px;");

        shopname.setText(shopName);
        username.setText(userName + "'s Ice Cream Shop");

        startTime = System.nanoTime();
        dayTime = System.nanoTime();
        weekTime = System.nanoTime();
        customerTime = System.nanoTime();

        start();
    }

    @FXML
    protected void investmentCalc() {
        long newInvest = Integer.parseInt(JOptionPane.showInputDialog("How much money would you like to invest? 10% of your investment will be added to your weekly profits. If you changed about investing, you can just enter 0."));
        updateMoney(-newInvest);
        investment = (long) (newInvest/10.0);
        System.out.println(investment);
    }

    @FXML
    protected void gameScreenFlavorBought(MouseEvent x) {
        Flavors F = (Flavors) gameScreenFlavorListview.getSelectionModel().getSelectedItem();
        updateMoney(-(F.getCost()));
        cogs += F.getCost() * shopSize;
        gameScreenFlavorListview.getItems().remove(F);
        allFlavors.remove(F);
        gameScreenFlavorBoughtListview.getItems().clear();
        boughtFlavors.add(F);
        earningPerCustomer++;
        gameScreenFlavorBoughtListview.getItems().addAll(boughtFlavors);
    }

    @FXML
    protected void gameScreenEquipmentBought(MouseEvent x) {
        Equipment E = (Equipment) gameScreenEquipmentBuyListview.getSelectionModel().getSelectedItem();
        updateMoney(-(E.getCost()));
        cogs += E.getCost() * shopSize;
        gameScreenEquipmentBuyListview.getItems().clear();
        E.setQuantity(E.getQuantity() + 1);
        gameScreenEquipmentBuyListview.getItems().addAll(allEquipment);
    }

    public void start() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(startTime>0){
                    int x = (int)(Math.floor(startTime/1000000000));
                    int y  = (int)(now/1000000000);
                    double z = now - startTime;
                    if(dayTime > 0){
                        if(now - dayTime > 1000000000.0) {
                            dayTime = 0;
                            dayTime = System.nanoTime();
                            day++;
                            int allquantity = 0;
                            for (Equipment r : allEquipment) {
                                allquantity += r.getQuantity();
                            }
                            customerMultiplier = (boughtFlavors.size() * 2)* allquantity * shopSize;
                            int multiplier = (int)(Math.random() * (customerMultiplier + 3)) + (customerMultiplier - 3);
                            weeklyCustomers +=  multiplier;
                            moneymadeWeek = (weeklyCustomers * earningPerCustomer) + investment;
                            setDay(day);
                            if (shopSize == 1) {
                                while(weeklyCustomers > 150 * (day + 1)) {
                                    System.out.println(weeklyCustomers);
                                    weeklyCustomers--;
                                }
                            }
                            else if (shopSize == 2) {
                                while(weeklyCustomers > 300 * (day + 1)) {
                                    weeklyCustomers--;
                                }
                            }
                            else if (shopSize == 3) {
                                while(weeklyCustomers > 700 * (day + 1)) {
                                    weeklyCustomers--;
                                }
                            }
                            weeklySummary();
                        }
                    }
                    if(customerTime > 0){
                        if(now - customerTime > 21000000000.0) {
                            customerTime = 0;
                            customerTime = System.nanoTime();
                            eventMoney(moneymadeWeek);
                            System.out.println(moneymadeWeek);
                            eventChooser();
                            moneymadeWeek = 0;
                            weeklySummary();
                        }
                    }
                    if(weekTime > 0){
                        if(now - weekTime > 7000000000.0) {
                            weekTime = 0;
                            weekTime = System.nanoTime();
                            weeks++;
                            if (weeks % 3 == 0) {
                                earningPerCustomer++;
                            }
                            else {
                                eventMoney(moneymadeWeek);
                                moneymadeWeek = 0;
                            }
                            weeklyCustomers = 1;
                            allProfit = allProfit + netProfit;
                            weeklySummary();
                        }
                    }
                }
            }
        }.start();
    }
}