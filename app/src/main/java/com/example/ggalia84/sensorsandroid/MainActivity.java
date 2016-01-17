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
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
