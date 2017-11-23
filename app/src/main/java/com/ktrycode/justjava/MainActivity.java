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


    public void incrementNumberOfCoffees(View view){
        quantity++;
        display(quantity);
    }

    public void decrementNumberOfCoffees(View view){
        if (quantity>0) {
            quantity--;
            display(quantity);
        }
    }

    public void submitOrder(View view) {
        if (quantity>0) {
            displayPrice(quantity * 5);
        } else{
            String priceMessage = "Free!";
            String costam = " Enjoy! \nCoffee quantity: " + quantity;
            priceMessage=priceMessage+costam;
            displayMessage(priceMessage);
        }

    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        //priceTextView.setText(NumberFormat.getCurrencyInstance().format(price));
        priceTextView.setText(NumberFormat.getCurrencyInstance(Locale.US).format(number));
    }

    private void displayMessage(String text){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(text);
    }

}