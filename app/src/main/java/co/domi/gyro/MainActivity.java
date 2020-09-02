package co.domi.gyro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    //MVVM
    //Redux
    private SensorManager sensorManager;
    private Sensor sensor;
    private float[] mRotationMatrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        mRotationMatrix = new float[16];
        mRotationMatrix[ 0] = 1;
        mRotationMatrix[ 4] = 1;
        mRotationMatrix[ 8] = 1;
        mRotationMatrix[12] = 1;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float axisX = event.values[0];
        float axisY = event.values[1];
        float axisZ = event.values[2];
        double angle = Math.toDegrees(Math.atan(axisY/axisX));
        Log.e(">>>>" , "Angle acelerometro: "+angle);

        float[] results = new float[3];
        SensorManager.getRotationMatrixFromVector(mRotationMatrix, event.values);
        SensorManager.getOrientation(mRotationMatrix, results);
        double angle2 = Math.toDegrees(results[2]);
        Log.e(">>>>" , "Angle Orientacion: " + angle2);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}