package com.ktrycode.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

import static java.net.Proxy.Type.HTTP;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int quantity = 1;
    private int price = 0;
    private final int cupCost = 5;
    private final int chocolate = 2;
    private final int cream = 1;

    public void incrementNumberOfCoffees(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot order more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQuantity(quantity);

    }

    public void decrementNumberOfCoffees(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQuantity(quantity);

    }

    public void submitOrder(View view) {
 /*       if (quantity > 0) {
            CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
            boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
            Log.v("MainActivity", "Has whipped cream? -> " + hasWhippedCream);

            CheckBox chocolateToppingCheckBox = (CheckBox) findViewById(R.id.chocolate_topping_checkbox);
            boolean hasChocolate = chocolateToppingCheckBox.isChecked();
            Log.v("MainActivity", "Has chocolate? ->  " + hasChocolate);

            EditText nameOfCustomerEditTextView = (EditText) findViewById(R.id.name_of_customer_edit_text_view);
            String customerName = nameOfCustomerEditTextView.getText().toString();

            price = calculatePrice(hasWhippedCream, hasChocolate);
            displayMessage(createOrderSummary(customerName, price, hasWhippedCream, hasChocolate));
        } else {
            String priceMessage = "Free!";
            displayMessage(priceMessage);
        }*/

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateToppingCheckBox = (CheckBox) findViewById(R.id.chocolate_topping_checkbox);
        boolean hasChocolate = chocolateToppingCheckBox.isChecked();

        TextView customerNameTextView = (TextView) findViewById(R.id.name_of_customer_edit_text_view);
        String customerName = customerNameTextView.getText().toString();

        price = calculatePrice(hasWhippedCream, hasChocolate);

        String emailSubject = "Order summary for " + customerName;
        String messageBody = createOrderSummary(customerName, price, hasWhippedCream, hasChocolate);
        composeEmail(emailSubject, messageBody);

    }

    public void composeEmail(String subject, String orderSummary) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
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

//    private void displayMessage(String text) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(text);
//    }

    private void calculatePrice(int quantity1, int costPerCup) {
        this.price = quantity1 * costPerCup;
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolateTopping) {
        int totalCost = 0;
        totalCost += cupCost;
        if (addWhippedCream) {
            totalCost += cream;
        }
        if (addChocolateTopping) {
            totalCost += chocolate;
        }

        return quantity * totalCost;
    }

    private void calculatePrice(int quantity1) {
        this.price = quantity1 * 10;
    }


    private String createOrderSummary(String customerName, int cost, boolean addWhippedCream, boolean addChocolateTopping) {
        String message;
        message = "Name: " + customerName +
                "\nAdd whipped cream? " + String.valueOf(addWhippedCream) +
                "\nAdd chocolate? " + String.valueOf(addChocolateTopping) +
                "\nQuantity: " + quantity +
                "\nTotal: " + displayPrice(cost) +
                "\nThank you!";
        return message;
    }

}