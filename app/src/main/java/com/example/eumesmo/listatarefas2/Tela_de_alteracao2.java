package com.example.eumesmo.listatarefas2;



import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Tela_de_alteracao2 extends AppCompatActivity {


    private Spinner spinner2;
    private  Spinner spinner3;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private EditText editText;
    private  EditText editText2;
    private ArrayAdapter<String> ocorrencias;
    private ArrayAdapter<String> repeticao;
    private Button bt_alterar;
    private  Button bt_voltar;
    private Button bt_deletar;
    private  Banco_de_dados banco_de_dados;
    private String codigo;
    private EditText edit_participantes;
    Mascaras mask=new Mascaras() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_alteracao2);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        edit_participantes=(EditText)findViewById(R.id.edit_participantes);
        bt_alterar = (Button) findViewById(R.id.bt_alterar);
        bt_voltar = (Button) findViewById(R.id.bt_voltar);
        bt_deletar=(Button)findViewById(R.id.bt_deletar);
        radioButton1=(RadioButton)findViewById(R.id.radioButton1);
        radioButton2=(RadioButton)findViewById(R.id.radioButton2);
        radioButton3=(RadioButton)findViewById(R.id.radioButton3);
        editText2.addTextChangedListener(mask.insert("##/##/####", editText2));


        spinner2=(Spinner)findViewById(R.id.spinner2);
        ocorrencias=new ArrayAdapter<String>(Tela_de_alteracao2.this,android.R.layout.simple_spinner_item);
        ocorrencias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(ocorrencias);


        spinner3=(Spinner)findViewById(R.id.spinner3);
        repeticao=new ArrayAdapter<String>(Tela_de_alteracao2.this,android.R.layout.simple_spinner_item);
        repeticao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(repeticao);



        banco_de_dados=new Banco_de_dados(getBaseContext());
        codigo = this.getIntent().getStringExtra("codigo");
        Cursor cursor = banco_de_dados.carregaDadoById2(Integer.parseInt(codigo));
        edit_participantes.setText(cursor.getString(cursor.getColumnIndexOrThrow(Manter_bd.participantes)));
        editText.setText(cursor.getString(cursor.getColumnIndexOrThrow(Manter_bd.num_ocorrencias)));
        editText2.setText(cursor.getString(cursor.getColumnIndexOrThrow(Manter_bd.data_final)));
        ocorrencias.add(cursor.getString(cursor.getColumnIndexOrThrow(Manter_bd.ocorrencias)));
        ocorrencias.add("diarimente");
        ocorrencias.add("semanalmente");
        ocorrencias.add("mensalmente");
        ocorrencias.add("anualmente");

        repeticao.add(cursor.getString(cursor.getColumnIndexOrThrow(Manter_bd.qntd_ocorrencias)));
        repeticao.add("1 dia");
        repeticao.add("1 semana");
        repeticao.add("1 mês");
        repeticao.add("1 ano");








        bt_alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                final String data_inicio = bundle.getString("data_inicio");
                final String hora_ini = bundle.getString("hora_ini");
                final String hora_fim = bundle.getString("hora_fim");
                final String local = bundle.getString("local");
                final String descricao = bundle.getString("descricao");
                final String tipo_de_evento = bundle.getString("tipo_de_evento");


                String qnt_participantes = edit_participantes.getText().toString();
                String num_ocorrencias = editText.getText().toString();
                String data_final = editText2.getText().toString();
                String ocorrencias = spinner2.getSelectedItem().toString();
                String repeticao = spinner3.getSelectedItem().toString();
                String ocorrencia_selecionada = ocorrencias;
                String repeticoes_selecionada = repeticao;


                String radio;
                if (qnt_participantes.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Existe algum campo vazio!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (radioButton1.isChecked()) {
                        if (num_ocorrencias.trim().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Preencha o número de ocorrências !!", Toast.LENGTH_SHORT).show();
                        } else {
                            radio = "1";
                            banco_de_dados.alteraRegistro(Integer.parseInt(codigo), data_inicio, hora_ini, hora_fim, local, descricao, tipo_de_evento, qnt_participantes, ocorrencia_selecionada, repeticoes_selecionada, radio,num_ocorrencias,data_final);
                            Toast.makeText(getApplicationContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Tela_de_alteracao2.this, Calendario.class);
                            startActivity(intent);
                            finish();


                        }

                    } else if (radioButton2.isChecked()) {
                        radio = "2";
                        banco_de_dados.alteraRegistro(Integer.parseInt(codigo), data_inicio, hora_ini, hora_fim, local, descricao, tipo_de_evento, qnt_participantes, ocorrencia_selecionada, repeticoes_selecionada, radio,num_ocorrencias,data_final);
                        Toast.makeText(getApplicationContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Tela_de_alteracao2.this, Calendario.class);
                        startActivity(intent);
                        finish();


                    } else if (radioButton3.isChecked()) {
                        if (data_final.trim().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Preencha com a data final !!", Toast.LENGTH_SHORT).show();

                        } else {


                            radio = "3";
                            banco_de_dados.alteraRegistro(Integer.parseInt(codigo), data_inicio, hora_ini, hora_fim, local, descricao, tipo_de_evento, qnt_participantes, ocorrencia_selecionada, repeticoes_selecionada, radio,num_ocorrencias,data_final);
                            Toast.makeText(getApplicationContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Tela_de_alteracao2.this, Calendario.class);
                            startActivity(intent);
                            finish();


                        }
                    } else {

                        Toast.makeText(getApplicationContext(), "Marque alguma opção acima !!!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });



        /*bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_de_alteracao2.this, Calendario.class);
                startActivity(intent);
                finish();
            }
            });*/

        bt_deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banco_de_dados.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(Tela_de_alteracao2.this, Calendario.class);
                startActivity(intent);
                finish();

            }
        });



    }
}