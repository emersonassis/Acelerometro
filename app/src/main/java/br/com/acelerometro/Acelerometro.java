package br.com.acelerometro;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class Acelerometro extends Activity implements SensorEventListener{

    private TextView textViewX;
    private TextView textViewY;
    private TextView textViewZ;
    private TextView textViewDetail;
    private TextView textViewDetail1;
    private TextView textViewDetail2;
    private TextView textViewDetail3;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private Button button;

    private CameraManager cam = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acelerometro);

        textViewX = (TextView) findViewById(R.id.text_view_x);
        textViewY = (TextView) findViewById(R.id.text_view_y);
        textViewZ = (TextView) findViewById(R.id.text_view_z);

        textViewDetail  = (TextView) findViewById(R.id.text_view_detail);
        textViewDetail1 = (TextView) findViewById(R.id.text_view_detail1);
        textViewDetail2 = (TextView) findViewById(R.id.text_view_detail2);
        textViewDetail3 = (TextView) findViewById(R.id.text_view_detail3);

        /**
         * Criando uma instancia de SensorManager para usar o metodo registerListener(...)
         * */
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        button = (Button) findViewById(R.id.buttonFlashlight);
        button.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick (View arg0) {

            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    /**
     * Acionado quando o sensor identificar alguma mudanca na posicao do dispositivo.
     * */
    @Override
    public void onSensorChanged(SensorEvent event) {
        Float x = event.values[0];
        Float y = event.values[1];
        Float z = event.values[2];


        textViewX.setText("Posicao X: " + x.intValue() + " Float: " + x);
        textViewY.setText("Posicao Y: " + y.intValue() + " Float: " + y);
        textViewZ.setText("Posicao Z: " + z.intValue() + " Float: " + z);


        if(y < 0){

            if(x > 0)
                textViewDetail.setText("Virando para ESQUERDA ficando INVERTIDO");

            if(x < 0)
                textViewDetail.setText("Virando para DIREITA ficando INVERTIDO");

        }else if(y > 0){

            if (x > 0)
                textViewDetail.setText("Virando para a ESQUERDA");

            if(x < 0)
                textViewDetail.setText("Virando para a DIREITA");
        }

        if(z < 0){
            textViewDetail1.setText("Mudando Z < 0: " + z);

        }else if(z > 0){
            textViewDetail2.setText("Mundando Z > 0: " + z);

        }

    }

    /**
     * Chamado quando a precisao do sensor do acelerometro mudar.
     * */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        textViewDetail3.setText("Mudando Accuracy: " + accuracy);
    }

}