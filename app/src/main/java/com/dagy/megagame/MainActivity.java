package com.dagy.megagame;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // recuperation des differents wiget
    private EditText txtNumber;
    private Button btnCompare;
    private TextView lblResult;
    private ProgressBar pgbScore;
    private TextView lblHistory;

    private int searchedValue;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // recuperation des id
        txtNumber = findViewById( R.id.txtNumber);
        btnCompare = findViewById(R.id.btnCompare);
        lblResult = findViewById(R.id.lblResult);
        pgbScore = findViewById(R.id.pgbScore);
        lblHistory = findViewById(R.id.lblHistory);
        btnCompare.setOnClickListener( btnCompareListener );
        init();
    }

    // pour initialiser les proprietes
    private void init(){
        score = 0;
        searchedValue = 1 + (int) (Math.random() * 100);
        Log.i("DEBUG", "Valeur Cherchée" +searchedValue);

        txtNumber.setText( "" );
        pgbScore.setProgress( score );
        lblResult.setText( "" );
        lblHistory.setText( "" );

        txtNumber.requestFocus();
    }

    //
    private void congratulation(){
        lblResult.setText(getString(R.string.strCongratulation));

        AlertDialog retryAlert = new AlertDialog.Builder(this).create();
        retryAlert.setTitle(R.string.app_name);
        retryAlert.setMessage(getString(R.string.strMessage, score));

        retryAlert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.strYes), new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                init();
            }
        });

        retryAlert.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.strNo), new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             finish();
            }
        });

        retryAlert.show();
    }

    //permet d'ecouter les clics sur les boutons
    private View.OnClickListener btnCompareListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Log.i( "DEBUG", "Bouton clique");

            String aNumber = txtNumber.getText().toString();
            if (aNumber.equals( "" )) return;

            lblHistory.append( aNumber + "\r\n");
            pgbScore.incrementProgressBy(1);
            score++;

            int enteredValue = Integer.parseInt(aNumber);
            if( enteredValue == searchedValue){
                congratulation();
            }else if (enteredValue < searchedValue){
                lblResult.setText(getString(R.string.strGreater));
            }else{
                lblResult.setText(getString(R.string.srtLower));
            }

            txtNumber.setText( "" );
            txtNumber.requestFocus();
        }
    };
}
