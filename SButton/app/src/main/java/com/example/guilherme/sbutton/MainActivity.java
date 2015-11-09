package com.example.guilherme.sbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button butao= (Button)findViewById(R.id.button);
        butao.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        TextView texto = (TextView) findViewById(R.id.texto);
                        if(texto.getText().toString().equalsIgnoreCase(getResources().getString(R.string.text_message)))
                        texto.setText(getResources().getString(R.string.text_message2));
                        else
                            texto.setText(getResources().getString(R.string.text_message));
                    }
                }
        );

        butao.setOnLongClickListener(
                new Button.OnLongClickListener(){
                    @Override
                    public boolean onLongClick(View v){
                        TextView texto = (TextView) findViewById(R.id.texto);
                        texto.setText("LONGO PA");
                        return true;// tem que ser true se nao ele vai para o click listener assim o que ele diz e o click foi tratado e era de facto um long
                        //se estiver a false ele vai executar o onClick listener acima
                    }
                }
        );
    }


}
