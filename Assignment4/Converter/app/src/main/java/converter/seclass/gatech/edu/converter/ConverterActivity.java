package converter.seclass.gatech.edu.converter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ConverterActivity extends AppCompatActivity {

    //Default from radio button instance.
    private RadioButton rbFromMile;
    private RadioButton rbFromKm;
    private RadioButton rbFromFeet;
    private RadioButton rbFromInches;
    private RadioButton rbFromMeters;
    private RadioButton rbFromCm;

    // Default to radio button instance.
    private RadioButton rbToMile;
    private RadioButton rbToKm;
    private RadioButton rbToFeet;
    private RadioButton rbToInches;
    private RadioButton rbToMeters;
    private RadioButton rbToCm;

    // Default Text Editor Instance
    private EditText etValue;
    private EditText etResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter_activity);

        rbFromMile = (RadioButton) findViewById(R.id.rbFromMile);
        rbFromKm = (RadioButton) findViewById(R.id.rbFromKm);
        rbFromMeters = (RadioButton) findViewById(R.id.rbFromMeter);
        rbFromFeet = (RadioButton) findViewById(R.id.rbFromFeet);
        rbFromInches = (RadioButton) findViewById(R.id.rbFromInches);
        rbFromCm = (RadioButton) findViewById(R.id.rbFromCm);

        rbToMile = (RadioButton) findViewById(R.id.rbToMile);
        rbToKm = (RadioButton) findViewById(R.id.rbToKm);
        rbToMeters = (RadioButton) findViewById(R.id.rbToMeters);
        rbToFeet = (RadioButton) findViewById(R.id.rbToFeet);
        rbToInches = (RadioButton) findViewById(R.id.rbToInches);
        rbToCm = (RadioButton) findViewById(R.id.rbToCm);

        etValue = (EditText) findViewById(R.id.etValue);
        etResult = (EditText) findViewById(R.id.etResult);
    }

    /**
     * This is the method called to change the states of the application.
     * @param view
     */
    public void handleClick(View view){

        switch (view.getId()){
            case R.id.bConvert:
                String value = etValue.getText().toString();
                if(value.length()>0 && !"".contains(value)){
                    double cm = convertToCm(value);
                    String result = convertToDesiredValue(cm);
                    etResult.setText(result);
                }else {
                    Context context = getApplicationContext();
                    CharSequence text = "Empty value!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context,text,duration);
                    toast.show();
                }
                break;
            case R.id.bReset:
                etValue.setText("");
                etResult.setText("");
                rbFromMile.setChecked(true);
                rbToMile.setChecked(true);
                break;
        }
    }

    /**
     * This method will convert an entered value to cm based on the configurations chosen by the user.
     * @param value: string value entered.
     * @return result: the value as double data type.
     */
    public double convertToCm(String value){
        double valueUnit = Double.parseDouble(value);
        double cm;
        if(rbFromMile.isChecked()){
            cm = valueUnit*160934.4;
        }else if(rbFromKm.isChecked()) {
            cm = valueUnit * 100000;
        }else if(rbFromMeters.isChecked()) {
            cm = valueUnit * 100;
        } else if(rbFromFeet.isChecked()) {
            cm = valueUnit * 30.48;
        } else if(rbFromInches.isChecked()){
            cm = valueUnit*2.54;
        } else {
            cm = valueUnit;
        }
        return cm;
    }

    /**
     * This method will convert the value in centimeters to the respective data type.
     * Based on the choose configurations.
     * @param cm: double value entered.
     * @return result: teh value in the appropriate units.
     */
    public String convertToDesiredValue(double cm){
        DecimalFormat format = new DecimalFormat("#.##");
        double result;
        if(rbToMile.isChecked()){
           result = cm/160934.4;
        }else if(rbToKm.isChecked()) {
            result = cm / 100000;
        } else if(rbToMeters.isChecked()) {
            result = cm/100;
        } else if(rbToFeet.isChecked()) {
            result = cm*0.0328084;
        } else if(rbToInches.isChecked()){
            result = cm*0.393701;
        }else {
            result = cm;
        }
        return String.valueOf(format.format(result));
    }
}
