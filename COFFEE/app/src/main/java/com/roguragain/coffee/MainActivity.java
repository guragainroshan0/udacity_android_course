package com.roguragain.coffee;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {

            Toast.makeText(this, "You cannot order more than 100 coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 0) {

            Toast.makeText(this, "You cannot order less than 1 coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Get user's name
        CheckBox whippedCream = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCream.isChecked();

        CheckBox chocolate = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate= chocolate.isChecked();

        EditText nameEditText = findViewById(R.id.name_edit_text);
        String name=  nameEditText.getText().toString();
        //TextView orderSummary = findViewById(R.id.order_summary_text_view);
        //orderSummary.setText(createOrderSummary(name,hasWhippedCream,hasChocolate));
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:9813966073"));
        //intent.putExtra(Intent.EXTRA_EMAIL,"guragain_r@yahoo.com");
        intent.putExtra("sms_body",createOrderSummary(name,hasWhippedCream,hasChocolate));
        //intent.setData(Uri.parse("geo:111.11,-11.11"));
        if(intent.resolveActivity(getPackageManager())!=null){
            Toast.makeText(this,"vayo",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"NULL",Toast.LENGTH_LONG).show();
        }





    }


    /**
     * Create summary of the order.
     *
     * @param name            on the order
     * @param price           of the order
     * @param addWhippedCream is whether or not to add whipped cream to the coffee
     * @param addChocolate    is whether or not to add chocolate to the coffee
     * @return text summary
     */
    private String createOrderSummary(String name,boolean addWhippedCream,boolean addChocolate) {
        String priceMessage = name;
        priceMessage+="\nAdd whipped cream ? "+ addWhippedCream;
        priceMessage+="\nAdd Chocolate ? "+ addChocolate;
        priceMessage += "\n" + "Quantity: " + String.valueOf(quantity);
        priceMessage += "\n" + "Total: $" + String.valueOf(calculatePrice(quantity,addChocolate,addWhippedCream));
        priceMessage += "\n "+ "Thank you!";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    private int calculatePrice( int quantity, boolean hasChocolate, boolean hasWhippedCream) {
        int basePrice=5;
        if(hasChocolate)
        {
            basePrice+=2;
        }
        if(hasWhippedCream)
        {
            basePrice++;
        }

        return quantity* basePrice;
    }

}
