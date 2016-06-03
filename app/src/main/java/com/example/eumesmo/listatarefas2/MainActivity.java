package com.example.eumesmo.listatarefas2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private  String data_compromisso;
    private ListView lista_de_compromissos;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Banco_de_dados banco_de_dados = new Banco_de_dados(getBaseContext());
        cursor = banco_de_dados.carregaDados();

        String[] nomeCampos = new String[] {Manter_bd.data_inicio,Manter_bd.hora_inicio, Manter_bd.descricao,Manter_bd.tipo_de_evento,Manter_bd.ocorrencias,Manter_bd.qntd_ocorrencias,Manter_bd.participantes,Manter_bd.temp};
        int[] idViews = new int[] {R.id.v_data_inicio,R.id.v_horario_inicial, R.id.v_descricao,R.id.v_tipo_do_evento,R.id.spinner2,R.id.spinner3,R.id.participantes,R.id.radio};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this,R.layout.activity_grid_layout,cursor,nomeCampos,idViews, 0);
        lista_de_compromissos = (ListView)findViewById(R.id.lista_de_compromissos);
        lista_de_compromissos.setAdapter(adaptador);


        lista_de_compromissos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;

                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(Manter_bd.Id));
                Intent intent = new Intent(MainActivity.this, Tela_de_alteracao.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Calendario.class);
                startActivity(intent);

            }
        });
    }

}
