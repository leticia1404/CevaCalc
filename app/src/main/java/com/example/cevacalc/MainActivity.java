package com.example.cevacalc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botao = findViewById(R.id.button);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
            }
        });
    }

    public boolean isEmpty(int id_elemento) {
        return ((EditText) findViewById(id_elemento)).getText().length() == 0;
    }

    public void calcular() {
        float precoA;
        float precoB;
        float volumeA;
        float volumeB;
        float precoPorLitroA;
        float precoPorLitroB;
        String maisBarata;

        if (isEmpty(R.id.edtPrecoCerveja1) ||
                isEmpty(R.id.edtPrecoCerveja2) ||
                isEmpty(R.id.edtVolumeCerveja1) ||
                isEmpty(R.id.edtVolumeCerveja2)) {
            return;
        }

        precoA = Float.valueOf(((EditText) findViewById(R.id.edtPrecoCerveja1)).getText().toString());
        precoB = Float.valueOf(((EditText) findViewById(R.id.edtPrecoCerveja2)).getText().toString());
        volumeA = Float.valueOf(((EditText) findViewById(R.id.edtVolumeCerveja1)).getText().toString());
        volumeB = Float.valueOf(((EditText) findViewById(R.id.edtVolumeCerveja2)).getText().toString());

        precoPorLitroA = precoA / volumeA;
        precoPorLitroB = precoB / volumeB;

        if (precoPorLitroA > precoPorLitroB) {
            maisBarata = getResources().getString(R.string.beer_a);
        } else {
            maisBarata = getResources().getString(R.string.beer_b);
        }

        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);

        // Set the message show for the Alert time
        builder.setMessage(getResources().getString(R.string.price_per_liter_a) + String.format("%.2f", precoPorLitroA) + "\n" +
                getResources().getString(R.string.price_per_liter_b) + String.format("%.2f", precoPorLitroB) + "\n" +
                getResources().getString(R.string.the_cheapest_bear) + maisBarata);

        // Set Alert Title
        builder.setTitle(getResources().getString(R.string.comparsion));

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder
                .setPositiveButton(
                        getResources().getString(R.string.exit),
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                // When the user click yes button
                                // then app will close
                                finish();
                            }
                        });

        // Set the Negative button with No name
        // OnClickListener method is use
        // of DialogInterface interface.
        builder
                .setNegativeButton(
                        getResources().getString(R.string.back),
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                // If user click no
                                // then dialog box is canceled.
                                dialog.cancel();
                            }
                        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }


}
