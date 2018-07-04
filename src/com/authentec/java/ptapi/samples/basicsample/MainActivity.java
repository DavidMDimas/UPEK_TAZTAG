package com.authentec.java.ptapi.samples.basicsample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
//import com.journeyapps.barcodescanner.BarcodeEncoder;

import android.R.*;

public class MainActivity extends Activity{

    public Button button,buttonQR,buttonFacial,buttonFirma,buttonDactilar,buttonNextFacial;
    public TextView contentTxt;
    public ImageView Image;
    public TextView trampa;


    String text2Qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =(Button) this.findViewById(R.id.button);
        buttonQR =(Button) this.findViewById(R.id.btnQR);
        buttonFacial =(Button) this.findViewById(R.id.btnFacial);
        buttonFirma =(Button) this.findViewById(R.id.btnFirma);
        buttonDactilar =(Button) this.findViewById(R.id.btnDactilar);
        contentTxt =(TextView) findViewById(R.id.scan_content);
        buttonNextFacial =(Button) findViewById(R.id.btnNextFacial);

        /*buttonQR.setBackgroundColor(Color.parseColor("#222222"));
        buttonFacial.setBackgroundColor(Color.parseColor("#222222"));
        buttonFirma.setBackgroundColor(Color.parseColor("#222222"));
        buttonDactilar.setBackgroundColor(Color.parseColor("#222222"));
        buttonNextFacial.setBackgroundColor(Color.parseColor("#222222"));*/

        buttonQR.setTextColor(Color.GREEN);
//        buttonFacial.setEnabled(false);
//        buttonFirma.setEnabled(false);
//        buttonDactilar.setEnabled(false);

        final Activity activity = this;

        buttonNextFacial.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View c) {

                Intent capturaFacial = new Intent(MainActivity.this, fac2.class);
                startActivity(capturaFacial);
                finish();
                //Toast.makeText(getApplicationContext(), "hola", Toast.LENGTH_SHORT).show();
            }
        }));

/*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Escanear");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
        
        
        Image =(ImageView) findViewById(R.id.image);
        
       */ 
        
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        trampa =(TextView) findViewById(R.id.textView2);
        contentTxt =(TextView) findViewById(R.id.scan_content);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Escaneo Cancelado");
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Escaneo");
                String scanContent = result.getContents();
                contentTxt.setText(scanContent);
                if (scanContent.equals("C4ahGgCf+ALg75FXkqqS3NvoTgegr43efwQ3X9hM+ioC9nchYunaR5l9gFNTXYNIb+b+Xk6sjiiAmSYbA/FFqjkleAzjSmXCMsvhzYdSw3tVxoXzFBYtI9E5/1WhXBeQ+") ){
                    trampa.setText("ID de solicitud: 12345678\nTipo de persona: Padre o Tutór\nSexo: Masculino\nCURP: YABF630725HVZXDL03\nNombre(s): FLORENCIO\nApellido Paterno: YAÑEZ\nApellido Materno: BADILLO");//|1010|3|
                    buttonNextFacial.setVisibility(View.VISIBLE);
                    button.setVisibility(View.INVISIBLE);
                }else if (scanContent.equals("8/1VzHCXUWpxvMlEz/aOIbLDLnwT4YlQ+t/8zllacWlYDXrAb/DVHfMcMHsBF2xeqi0QtQHyWEKciDDE5dBI8FkvvYOIBcyPdBEUAmeDVN7ep3TfIgKXlmuYXIRXWpDu9") ){
                    trampa.setText("ID de solicitud: 12345678\nTipo de persona: Solicitante\nSexo: Masculino\nCURP: ROBR410808HVZDDM07\nNombre(s): RAMIRO\nApellido Paterno: RODRIGUEZ\nApellido Materno: BADILLO");//|1010|3|
                    buttonNextFacial.setVisibility(View.VISIBLE);
                    button.setVisibility(View.INVISIBLE);
                }else if (scanContent.equals("pfN7/WQ4XriNhkRHnW7FUBGYFJ6vpG8VQS0LzmmPCkC5eMKAE343WW5AtQMW6slo8eqqyAClRcnMbRSLG/SuOoSNapNceMC2mcp8m3Y0S7v0=") ){
                    trampa.setText("ID de solicitud: 12345678\nTipo de persona: Madre o Tutór\nSexo: Femenino\nCURP: MENJ470823MVZNRN09\nNombre(s): JUANA\nApellido Paterno: MENDOZA\nApellido Materno: NERI");//|1010|3|
                    buttonNextFacial.setVisibility(View.VISIBLE);
                    button.setVisibility(View.INVISIBLE);
                }
                else {
                    trampa.setText("No se puede leer el codigo...");
                }
                
                //aqui se genera el codigo qr
                text2Qr = contentTxt.getText().toString().trim();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
