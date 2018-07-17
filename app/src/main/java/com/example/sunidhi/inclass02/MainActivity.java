package com.example.sunidhi.inclass02;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    TextView textViewAge, textViewWeight, textViewHeight,  textViewResult, textViewBMILabel, textViewBMIValue, textViewBMIType, textViewRangeLabel, textViewMessage;
    EditText editTextAge, editTextWeight, editTextFeet, editTextInches;
    Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        setTitle( "BMI Calculator" );

        textViewAge = findViewById( R.id.textViewAge );
        textViewWeight = findViewById( R.id.textViewWeight );
        textViewHeight = findViewById( R.id.textViewHeight );
        editTextAge = findViewById( R.id.editTextAge );
        editTextWeight = findViewById( R.id.editTextWeight );
        editTextFeet = findViewById( R.id.editTextFeet );
        editTextInches = findViewById( R.id.editTextInches );
        textViewResult = findViewById( R.id.textViewResult );
        textViewBMILabel = findViewById( R.id.textViewBMILabel );
        textViewBMIValue = findViewById( R.id.textViewBMIValue );
        textViewBMIType = findViewById( R.id.textViewBMIType );
        textViewRangeLabel = findViewById( R.id.textViewRangeLabel );
        textViewMessage = findViewById( R.id.textViewMessage );

        textViewResult.setVisibility( View.INVISIBLE);
        textViewBMILabel.setVisibility( View.INVISIBLE);
        textViewBMIValue.setVisibility( View.INVISIBLE);
        textViewBMIType.setVisibility( View.INVISIBLE);
        textViewRangeLabel.setVisibility( View.INVISIBLE);
        textViewMessage.setVisibility( View.INVISIBLE);

        buttonCalculate = findViewById( R.id.buttonCalculate );


        buttonCalculate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ageValue = editTextAge.getText().toString();
                String weightValue = editTextWeight.getText().toString();
                String feetValue = editTextFeet.getText().toString();
                String inchesValue = editTextInches.getText().toString();
                Double age;

                    if ( (ageValue.matches("")) || (weightValue.matches("")) || (feetValue.matches("")) || (inchesValue.matches(""))) {
                        Toast.makeText( MainActivity.this, "Enter Input", Toast.LENGTH_SHORT ).show();
                    }
                    else if( Double.parseDouble( ageValue ) < 18){
                        Toast.makeText( MainActivity.this, "Age should be more than 18", Toast.LENGTH_SHORT ).show();
                        age = Double.parseDouble( ageValue );
                    }
                    else {
                        double weight = Double.parseDouble( weightValue );
                        double feet = Double.parseDouble( feetValue );
                        double inches = Double.parseDouble( inchesValue );

                        double feetToInches = feet * 12;
                        double totalHeight = feetToInches + inches;

                        Double BMI;
                        BMI = 703 * ( (weight / totalHeight ) / totalHeight);

                        if (BMI < 18.5) {
                            Double difference = 0.0, weightDiff = 0.0;
                            textViewResult.setVisibility( View.VISIBLE );
                            textViewBMILabel.setVisibility( View.VISIBLE );
                            textViewBMIValue.setVisibility( View.VISIBLE );
                            textViewBMIType.setVisibility( View.VISIBLE );
                            textViewRangeLabel.setVisibility( View.VISIBLE );
                            textViewMessage.setVisibility( View.VISIBLE );

                            difference = (18.5 * totalHeight * totalHeight ) / 703 ;
                            weightDiff = difference - weight;

                            textViewBMIValue.setText( String.format( "%.2f", BMI));
                            textViewBMIType.setTextColor( Color.RED );
                            textViewBMIType.setText( "Underweight" );
                            textViewMessage.setText( "You need to gain " + String.format( "%.2f", weightDiff) + " lbs to reach a BMI of 18.5" );
                        }
                        else if ((BMI < 25) && (BMI >= 18.5)) {
                            textViewResult.setVisibility( View.VISIBLE );
                            textViewBMILabel.setVisibility( View.VISIBLE );
                            textViewBMIValue.setVisibility( View.VISIBLE );
                            textViewBMIType.setVisibility( View.VISIBLE );
                            textViewRangeLabel.setVisibility( View.VISIBLE );
                            textViewMessage.setVisibility( View.VISIBLE );

                            textViewBMIValue.setText( String.format( "%.2f", BMI));
                            textViewBMIType.setTextColor( Color.GREEN );
                            textViewBMIType.setText( "Normal" );
                            textViewMessage.setText( "Keep up the good work!" );
                        }
                        else if ((BMI < 30) && (BMI >= 25)) {
                            Double difference = 0.0, weightDiff = 0.0;

                            textViewResult.setVisibility( View.VISIBLE );
                            textViewBMILabel.setVisibility( View.VISIBLE );
                            textViewBMIValue.setVisibility( View.VISIBLE );
                            textViewBMIType.setVisibility( View.VISIBLE );
                            textViewRangeLabel.setVisibility( View.VISIBLE );
                            textViewMessage.setVisibility( View.VISIBLE );

                            difference = (25 * totalHeight * totalHeight ) / 703 ;
                            weightDiff = weight - difference;

                            textViewBMIValue.setText( String.format( "%.2f", BMI));
                            textViewBMIType.setTextColor( 0xFFF06D2F );
                            textViewBMIType.setText( "Overweight" );
                            textViewMessage.setText( "You will need to lose " + String.format( "%.2f", weightDiff) + " lbs to reach a BMI of 25" );
                        }
                        else if (BMI >= 30) {
                            Double difference = 0.0, weightDiff = 0.0;

                            textViewResult.setVisibility( View.VISIBLE );
                            textViewBMILabel.setVisibility( View.VISIBLE );
                            textViewBMIValue.setVisibility( View.VISIBLE );
                            textViewBMIType.setVisibility( View.VISIBLE );
                            textViewRangeLabel.setVisibility( View.VISIBLE );
                            textViewMessage.setVisibility( View.VISIBLE );

                            difference = (25 * totalHeight * totalHeight ) / 703 ;
                            weightDiff = weight - difference;

                            textViewBMIValue.setText( String.format( "%.2f", BMI));
                            textViewBMIType.setTextColor( 0xFFF06D2F );
                            textViewBMIType.setText( "Obese" );
                            textViewMessage.setText( "You will need to lose " + String.format( "%.2f", weightDiff) + " lbs to reach a BMI of 25" );
                        }
                    }
            }
        } );

    }
}
