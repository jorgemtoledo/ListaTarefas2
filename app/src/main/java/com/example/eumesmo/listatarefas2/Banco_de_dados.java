package com.example.eumesmo.listatarefas2;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Kayo on 10/05/2016.
 */
public class Banco_de_dados {

    private Manter_bd manterbd, manterbd2;
    private Bundle bundle;
    private Context ctx;

    public Banco_de_dados(Context context) {//método construtor que recebe um objeto  da classe Manterbd
        manterbd = new Manter_bd(context);
        ctx = context;
    }


    public String insere_dados_tabela(String data_inicio, String hora_inicio, String hora_fim, String local, String descricao, String tipo_de_evento, String participantes, String ocorrencias, String qntd_ocorrencias, String radio,String num_ocorrencias,String data_final) {

        SQLiteDatabase db = manterbd.getWritableDatabase();// á variavel db recebe um método que da a permissão de escrita no banco
        ContentValues inserindo = new ContentValues();//váriavel que insere os valores da classe dos atributos da classe Livro no banco
        inserindo.put(Manter_bd.data_inicio, data_inicio);
        inserindo.put(Manter_bd.hora_inicio, hora_inicio);
        inserindo.put(Manter_bd.hora_fim, hora_fim);
        inserindo.put(Manter_bd.local, local);
        inserindo.put(Manter_bd.descricao, descricao);
        inserindo.put(Manter_bd.tipo_de_evento, tipo_de_evento);
        inserindo.put(Manter_bd.participantes, participantes);
        inserindo.put(Manter_bd.ocorrencias, ocorrencias);
        inserindo.put(Manter_bd.qntd_ocorrencias, qntd_ocorrencias);
        inserindo.put(Manter_bd.temp, radio);
        inserindo.put(Manter_bd.num_ocorrencias, num_ocorrencias);
        inserindo.put(Manter_bd.data_final, data_final);


        Long resultado;//essa variável armazenará o  resultado do banco, caso a inserção de certo ou não
        resultado = db.insert(Manter_bd.Tabela, null, inserindo);//inserindo no banco
        db.close();//fecha a conexão do bd depois de inserir
        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {manterbd.Id, manterbd.data_inicio, manterbd.hora_inicio, manterbd.hora_fim, manterbd.local, manterbd.descricao, manterbd.tipo_de_evento, manterbd.ocorrencias, manterbd.qntd_ocorrencias, manterbd.participantes, manterbd.temp,manterbd.num_ocorrencias,manterbd.data_final};
        SQLiteDatabase db = manterbd.getReadableDatabase();
        cursor = db.query(manterbd.Tabela, campos, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public Cursor carregaDadoById(int id) {
        Cursor cursor;
        String[] campos = {manterbd.Id, manterbd.data_inicio, manterbd.hora_inicio, manterbd.hora_fim, manterbd.local, manterbd.descricao, manterbd.tipo_de_evento};
        String where = Manter_bd.Id + "=" + id;
        SQLiteDatabase db = manterbd.getReadableDatabase();
        cursor = db.query(Manter_bd.Tabela, campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public Cursor carregaDadoById2(int id) {
        Cursor cursor;
        String[] campos = {manterbd.Id, manterbd.participantes, manterbd.ocorrencias, manterbd.qntd_ocorrencias, manterbd.temp,manterbd.num_ocorrencias,manterbd.data_final};
        String where = Manter_bd.Id + "=" + id;
        SQLiteDatabase db = manterbd.getReadableDatabase();
        cursor = db.query(Manter_bd.Tabela, campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public void alteraRegistro(int id, String data_inicio, String hora_inicio, String hora_fim, String local, String descricao, String tipo_de_evento, String participantes, String ocorrencias, String repeticoes, String temp,String num_ocorrencias,String data_final) {
        ContentValues valores;
        String where;
        SQLiteDatabase db = manterbd.getWritableDatabase();
        where = Manter_bd.Id + "=" + id;
        valores = new ContentValues();
        valores.put(Manter_bd.data_inicio, data_inicio);
        valores.put(Manter_bd.hora_inicio, hora_inicio);
        valores.put(Manter_bd.hora_fim, hora_fim);
        valores.put(Manter_bd.local, local);
        valores.put(Manter_bd.descricao, descricao);
        valores.put(Manter_bd.tipo_de_evento, tipo_de_evento);
        valores.put(Manter_bd.participantes, participantes);
        valores.put(Manter_bd.ocorrencias, ocorrencias);
        valores.put(Manter_bd.qntd_ocorrencias, repeticoes);
        valores.put(Manter_bd.temp, temp);
        valores.put(Manter_bd.num_ocorrencias, num_ocorrencias);
        valores.put(Manter_bd.data_final, data_final);

        db.update(Manter_bd.Tabela, valores, where, null);
        db.close();
    }

    public void deletaRegistro(int id) {
        String where = Manter_bd.Id + "=" + id;
        SQLiteDatabase db = manterbd.getReadableDatabase();
        db.delete(Manter_bd.Tabela, where, null);
        db.close();
    }



}








