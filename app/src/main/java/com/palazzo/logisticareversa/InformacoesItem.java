package com.palazzo.logisticareversa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class InformacoesItem extends AppCompatActivity {

    private TextView textGarrafasQuebradas;
    private TextView textGarrafasReciclagem;
    private TextView textGarrafasReclamacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_item);

        Toolbar toolbar = findViewById(R.id.toolbarInformacoes);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InformacoesItem.this.startActivity(new Intent(InformacoesItem.this, MainActivity.class));
            }
        });

        // Referências dos TextViews
        textGarrafasQuebradas = findViewById(R.id.textGarrafasQuebradas);
        textGarrafasReciclagem = findViewById(R.id.textGarrafasReciclagem);
        textGarrafasReclamacoes = findViewById(R.id.textGarrafasReclamacoes);

        // Get the selected item's data from the intent
        Intent intent = getIntent();
        Item selectedItem = (Item) intent.getSerializableExtra("selectedItem");

        // Update the TextViews with the selected item's data
        textGarrafasQuebradas.setText("Garrafas Quebradas: " + selectedItem.getGarrafasQuebradas());
        textGarrafasReciclagem.setText("Garrafas para Reciclagem: " + selectedItem.getGarrafasReciclagem());
        textGarrafasReclamacoes.setText("Garrafas por Reclamações: " + selectedItem.getGarrafasReclamacoes());


        // Carregar as informações fixas
        carregarInformacoes();
    }

    private void carregarInformacoes() {
        // Valores fixos para as informações
        String garrafasQuebradas = "50";
        String garrafasReciclagem = "30";
        String garrafasReclamacoes = "20";

        // Atualizar os TextViews com os valores fixos
        textGarrafasQuebradas.setText("Garrafas Quebradas: " + garrafasQuebradas);
        textGarrafasReciclagem.setText("Garrafas para Reciclagem: " + garrafasReciclagem);
        textGarrafasReclamacoes.setText("Garrafas por Reclamações: " + garrafasReclamacoes);
    }
}
