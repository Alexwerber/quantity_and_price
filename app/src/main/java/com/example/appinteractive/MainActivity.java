package com.example.appinteractive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        EditText personName = (EditText) findViewById(R.id.name_of_client);
        String nameOfPerson = personName.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolade_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        String orderMassage = createOrderSummary(nameOfPerson, calculatePrice(quantity, 5, hasChocolate, hasWhippedCream), hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just interactive" + nameOfPerson);
        intent.putExtra(Intent.EXTRA_SUBJECT, orderMassage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view) {
        if (quantity == 100) {
            return;
        }

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            return;
        }

        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private int calculatePrice (int quantity, int value, boolean chocolateCheck, boolean whippedCreamCheck) {
        int price = quantity * value;
        if (chocolateCheck) {
            price = price + 2 * quantity;
        }

        if (whippedCreamCheck) {
            price = price + quantity;
        }

        return price;
    }

    private String createOrderSummary (String name, int price, boolean toppingWhippedCream, boolean chocolate) {
        String massage = getString(R.string.name_java_file) + name
                + "\n" + getString(R.string.add_whipped_cream) + toppingWhippedCream
                + "\n" + getString(R.string.add_chocolate) + chocolate
                + "\n" + getString(R.string.quantity_java_file) + quantity
                + "\n" + getString(R.string.total) + price
                + "$\n" + getString(R.string.thank_you);
        return massage;
    }

}