package sg.edu.rp.c346.s19024292.billplease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView amountDisplay;
    TextView paxDisplay;
    TextView discountDisplay;
    TextView totalbillDisplay;
    TextView eachpaymentDisplay;
    EditText editAmount;
    EditText editPax;
    EditText editDiscount;
    ToggleButton svstoggle;
    ToggleButton gsttoggle;
    ToggleButton splittoggle;
    ToggleButton resettoggle;
    RadioGroup group;
    RadioButton radioCash;
    RadioButton radioPaynow;

    float svs;
    float gst;
    String amount;
    String pax;
    String payment;
    float calc;
    String disc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountDisplay = findViewById(R.id.amount);
        paxDisplay = findViewById(R.id.paxDisplay);
        discountDisplay = findViewById(R.id.discountDisplay);
        totalbillDisplay = findViewById(R.id.totalDisplay);
        eachpaymentDisplay = findViewById(R.id.eachpaymentDisplay);
        editAmount = findViewById(R.id.editamount);
        editPax = findViewById(R.id.editpax);
        editDiscount = findViewById(R.id.editDiscount);
        svstoggle = findViewById(R.id.svstoggle);
        gsttoggle = findViewById(R.id.gsttoggle);
        splittoggle = findViewById(R.id.splittoggle);
        resettoggle = findViewById(R.id.resettoggle);
        group = findViewById(R.id.group);
        radioCash = findViewById(R.id.cash);
        radioPaynow = findViewById(R.id.paynow);

        int checkedRadioButtonID = group.getCheckedRadioButtonId();
        group=(RadioGroup)findViewById(checkedRadioButtonID);
        payment = String.valueOf(group);

        svstoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (svstoggle.isChecked()) {
                    svstoggle.setText("NO SVS");
                    svs = 0;
                } else {
                    svs = (float) 0.1;
                    svstoggle.setText("SVS");
                }

            }
        });

        gsttoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gsttoggle.isChecked()) {
                    gst = 0;
                    gsttoggle.setText("NO GST");
                } else {
                    gst = (float) 0.07;
                    gsttoggle.setText("GST");
                }
            }
        });

        splittoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = editAmount.getText().toString();
                pax = editPax.getText().toString();
                disc = editDiscount.getText().toString();
                float floatAmt = (float) Double.parseDouble(amount);
                int intPax = (int) Integer.parseInt(pax);
                int discount = (int) Integer.parseInt(disc);

                if (svs == 0 && gst == 0) {
                    calc = (1 - (discount / 100)) * (floatAmt/intPax);
                }
                else {
                    calc = (1 - (discount / 100)) * ((floatAmt * (1 - (svs + gst)))/intPax);
                }

                String val = String.format("%.2f", calc);

                totalbillDisplay.setText("Total Bill: $" + (editAmount.getText().toString()));
                eachpaymentDisplay.setText("Each Pays: $" + val + " in " + payment);
            }
        });

        resettoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });


    }
}