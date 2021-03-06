package com.example.ggalia84.sensorsandroid;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    //Variables
    private SensorManager sensorManager = null;

    private Sensor sensorAcelerometro = null;
    private Sensor sensorDeOrientacion = null;

    private TextView textViewAcelerometro = null;
    private TextView textViewOrientacion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Iniciant els sensors.
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorDeOrientacion = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        //Comprovar si hi ha sensors al dispositiu
        if(sensorAcelerometro == null){
            Toast.makeText(getApplicationContext(), "No hi ha sensor de acceleració.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Hi ha sensor de acceleració.", Toast.LENGTH_SHORT).show();
            sensorManager.registerListener(this, sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(sensorDeOrientacion == null){
            Toast.makeText(getApplicationContext(), "No hi ha sensor de Orientació", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Hi ha sensor de Orientació", Toast.LENGTH_SHORT).show();
            sensorManager.registerListener(this, sensorDeOrientacion, SensorManager.SENSOR_DELAY_NORMAL);
        }

        //Inicialitzar els textview.
        setContentView(R.layout.activity_main);

        textViewAcelerometro = (TextView) findViewById(R.id.sensorDeMovimiento);
        textViewAcelerometro.setTextSize(30);

        textViewOrientacion = (TextView) findViewById(R.id.sensorDeOrientacion);
        textViewOrientacion.setTextSize(30);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent arg0) {
        synchronized (this){
            float[] masData;
            float x;
            float y;
            float z;

            switch(arg0.sensor.getType()){
                case Sensor.TYPE_PROXIMITY:
                    masData = arg0.values;
                    if(masData[0]==0){
                        textViewAcelerometro.setTextSize(textViewAcelerometro.getTextSize()+10);
                    }
                    else{
                        textViewAcelerometro.setTextSize(textViewAcelerometro.getTextSize()-10);
                    }
                    break;
                case Sensor.TYPE_ACCELEROMETER:
                    masData = arg0.values;
                    x = masData[0];
                    y = masData[1];
                    z = masData[2];
                    textViewAcelerometro.setText("x: " + x + "\ny: "+y + "\nz: "+z);
                    break;
                case Sensor.TYPE_ORIENTATION:
                    masData = arg0.values;
                    x = masData[0];
                    y = masData[1];
                    textViewOrientacion.setText("x: " + x + "\ny: "+y);
                    break;
                default:
                    break;
            }
        }
    }

    //Apagar els sensors quan la aplicació no estigui visible.
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
