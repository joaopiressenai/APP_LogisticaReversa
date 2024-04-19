package com.palazzo.logisticareversa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CriarItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText editTextDataDevolucao;
    private EditText editTextDataEntrega;
    private EditText editTextNumeroPedido;
    private EditText editTextQuantidade;
    private Item i = new Item();


    public void onCreate(Bundle savedInstanceState) {
        CriarItemActivity.super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_item_activity);
        Toolbar toolbar = findViewById(R.id.toolbarCriar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CriarItemActivity.this.startActivity(new Intent(CriarItemActivity.this, MainActivity.class));
            }
        });
        List<String> status = new ArrayList<>(Arrays.asList(
                EnumStatus.ENTREGUE.getStatus(),
                EnumStatus.TRANSITO.getStatus(),
                EnumStatus.ESPERA.getStatus(),
                EnumStatus.COLETADO.getStatus(),
                EnumStatus.ATRASADO.getStatus()
        ));
        Spinner spinnerStatus = (Spinner) findViewById(R.id.spinnerStatus);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                status);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter);

        spinnerStatus.setAdapter(adapter);
        spinnerStatus.setOnItemSelectedListener(this);
        this.editTextNumeroPedido = (EditText) findViewById(R.id.editTextNumeroPedido);
        this.editTextQuantidade = (EditText) findViewById(R.id.editTextQuantidade);
        this.editTextDataEntrega = (EditText) findViewById(R.id.editTextDataEntrega);
        this.editTextDataDevolucao = (EditText) findViewById(R.id.editTextDataDevolucao);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_criar_item, menu);
        return true;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.i.setStatus(parent.getItemAtPosition(position).toString());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_criar_item) {
            int numeroPedido = Integer.parseInt(this.editTextNumeroPedido.getText().toString());
            int quantidade = Integer.parseInt(this.editTextQuantidade.getText().toString());
            String dataEntrega = this.editTextDataEntrega.getText().toString();
            String dataDevolucao = this.editTextDataDevolucao.getText().toString();
            this.i.setNumeroPedido(numeroPedido);
            this.i.setQuantidade(quantidade);
            this.i.setDataEntrega(dataEntrega);
            this.i.setDataDevolucao(dataDevolucao);
            FirebaseApi fireBaseApi = new FirebaseApi(this);
            fireBaseApi.criarItem(i, "Receita criada com sucesso");

            finish();
            return true; // Indicando que o evento foi tratado com sucesso
        } else {
            // Se o ID do item do menu não corresponder, retorne false para indicar que o evento não foi tratado
            return super.onOptionsItemSelected(item);
        }
    }


    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
