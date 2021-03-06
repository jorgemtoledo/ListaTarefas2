package com.example.eumesmo.listatarefas2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Formulario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(toolbar != null){
            toolbar.setTitle("Voltar");
            toolbar.setNavigationIcon(R.drawable.imagem_voltar);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavUtils.navigateUpFromSameTask(Formulario.this);

                }
            });

        }
    }


//    Cria o botao confirmar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }

//    Dando acao ao botao confirmar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_formulario_ok:

                finish();

                return false;

            case R.id.menu_formulario_config:

                Intent intent = new Intent(Formulario.this, Evento.class);
                startActivity(intent);



                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
