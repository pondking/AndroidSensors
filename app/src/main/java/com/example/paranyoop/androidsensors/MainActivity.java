package com.example.paranyoop.androidsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.Semaphore;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;
import static android.hardware.Sensor.TYPE_LINEAR_ACCELERATION;
import static android.hardware.Sensor.TYPE_ORIENTATION;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mLinerAccelerometer, mOrientation, mAccelerometer;
    private SensorEventListener mSensorListener;
    private TextView axisX, axisY, axisZ, azimuth, pitch, roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        axisX = (TextView) findViewById(R.id.axisX);
        axisY = (TextView) findViewById(R.id.axisY);
        axisZ = (TextView) findViewById(R.id.axisZ);
        azimuth = (TextView) findViewById(R.id.axisAzi);
        pitch = (TextView) findViewById(R.id.axisPit);
        roll = (TextView) findViewById(R.id.axisRoll);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLinerAccelerometer = mSensorManager.getDefaultSensor(TYPE_LINEAR_ACCELERATION);
        mOrientation = mSensorManager.getDefaultSensor(TYPE_ORIENTATION);

        mSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                if(sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) { //Can put another sensor if you want

                    axisX.setText("axisX : " + sensorEvent.values[0]);
                    axisY.setText("axisY : " + sensorEvent.values[1]);
                    axisZ.setText("axisZ : " + sensorEvent.values[2]);
                }
                else if(sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {

                    azimuth.setText("Azimuth : " + sensorEvent.values[0]);
                    pitch.setText("Pitch : " + sensorEvent.values[1]);
                    roll.setText("Roll : " + sensorEvent.values[2]);
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mLinerAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mSensorListener, mOrientation, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorListener);
    }
}
