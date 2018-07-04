package com.authentec.java.ptapi.samples.basicsample;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;;

public class MainActivity2 extends Activity {

    @Override

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.login);

        Button boton =(Button) findViewById(R.id.btnIngresar);
        boton.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View view){
                String usuario = ((EditText)findViewById(R.id.txtUsuario)).getText().toString();
                String password = ((EditText)findViewById(R.id.pswusuario)).getText().toString();
                if (usuario.equals("admin")&& password.equals("1234")){

                    Intent nuevoform = new Intent(MainActivity2.this,MainActivity.class);
                    startActivity(nuevoform);
                    finish();
                }
                else{
                 Toast.makeText(getApplicationContext(),"Usuario Incorrecto",Toast.LENGTH_SHORT).show();
                }
            }
            });
    }
}
