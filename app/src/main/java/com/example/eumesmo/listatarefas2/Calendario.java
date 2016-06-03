package com.example.eumesmo.listatarefas2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class Calendario extends AppCompatActivity {
    private CalendarView calendario;
    private Button bt_compromisso;
    private Button bt_agendar;
    private String data_compromisso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        calendario = (CalendarView) findViewById(R.id.calendario);
        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int ano, int mes, int dia) {
                Integer.toString(dia);
                int meses=mes+1;
                Integer.toString(meses);
                Integer.toString(ano);
                data_compromisso = dia + "/" + meses + "/" + ano;
                Toast.makeText(getApplicationContext(), data_compromisso, Toast.LENGTH_SHORT).show();
            }
        });

        bt_agendar=(Button)findViewById(R.id.bt_agendar);
        bt_agendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(data_compromisso!=null){
                    Intent intent = new Intent(Calendario.this, Tela_de_cadastro.class);
                    intent.putExtra("data_compromisso", data_compromisso);
                    startActivity(intent);
                    finish();}

                else{
                    Toast.makeText(getApplicationContext(),"Para cadastrar tem que selecionar uma Data!", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}