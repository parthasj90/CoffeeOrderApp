package com.partha_sarathi_jena.coffeeorderapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int quantity = 0;
    private int coffeePrice = 5;
    private boolean haswhippedCream = false;
    private boolean hasChocolate = false;
    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void submitOrder(View view) {
        EditText text = (EditText) findViewById(R.id.username);
        name = text.getText().toString();
        CheckBox cb_cream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        haswhippedCream = cb_cream.isChecked();
        CheckBox cb_choc = (CheckBox) findViewById(R.id.chocolate_checkbox);
        hasChocolate = cb_choc.isChecked();
        String message = createOrderSummary();
        displayMessage(message);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"parthasj90@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order for: " + name);
        intent.putExtra(Intent.EXTRA_TEXT   , message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Send mail..."));
        }
    }

    private String createOrderSummary()
    {
        String message = "Name : "+ name +"\nAdd Whipped Cream? : "+ haswhippedCream
                +"\nAdd Chocolate? : "+ hasChocolate +
                "\nQuantity : "+ quantity + "\nTotal: $" + (quantity * coffeePrice) + "\nThank you !!" ;
        return message;
    }
    public void increment(View view)
    {   quantity += 1;
        displayQuantity(quantity);
    }

    public void decrement(View view)
    {
        if(quantity > 0) {
            quantity -= 1;
            displayQuantity(quantity);
        }
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String s) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(s);
    }
}
