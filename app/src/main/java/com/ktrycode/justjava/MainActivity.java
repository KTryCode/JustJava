package com.ktrycode.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int quantity =0;
    private int price=0;
    private final int cupCost=5;
    String nameOfCustomer="Krystian";

    public void incrementNumberOfCoffees(View view){
        quantity++;
        displayQuantity(quantity);
    }

    public void decrementNumberOfCoffees(View view){
        if (quantity>0) {
            quantity--;
            displayQuantity(quantity);
        }
    }

    public void submitOrder(View view) {
        if (quantity>0) {
            price=  calculatePrice();
            displayMessage(createOrderSummary(price));
        } else{
            String priceMessage = "Free!";
            displayMessage(priceMessage);
        }

    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    private String displayPrice(int number) {
        //TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        //priceTextView.setText(NumberFormat.getCurrencyInstance().format(price));
        //priceTextView.setText(NumberFormat.getCurrencyInstance(Locale.US).format(number));
        return NumberFormat.getCurrencyInstance(Locale.US).format(number);
    }

    private void displayMessage(String text){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(text);
    }
    private void calculatePrice(int quantity1,int costPerCup){
        this.price=quantity1*costPerCup;
    }
    private int calculatePrice(){
        return quantity*cupCost;
    }
    private void calculatePrice(int quantity1){
        this.price=quantity1*10;
    }

    private String createOrderSummary(int cost){
        String message;
        message = "Name: " + nameOfCustomer +
                "\nQuantity: " + quantity +
                "\nTotal: " + displayPrice(cost) +
                "\nThank you!";
        return message;
    }

}