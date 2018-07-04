package com.authentec.java.ptapi.samples.basicsample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
//import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.app.Activity;


public class huellaclase extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.huellas);

        mostrarmsj();


    }

    public void mostrarmsj(){
        try{

        }catch (Exception e){
            dislayMessage("libjniPtapi.so not loaded");
        }
    }

    public void dislayMessage(String text)
    {
        mHandler.sendMessage(mHandler.obtainMessage(0, 0, 0, text));
    }

    /**
     * Transfer messages to the main activity thread.
     */
    private Handler mHandler = new Handler()
    {
        public void handleMessage(Message aMsg)
        {
            ((TextView)findViewById(R.id.EnrollmentTextView)).setText((String) aMsg.obj);
        }
    };
}
