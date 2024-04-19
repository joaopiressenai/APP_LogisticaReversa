package com.palazzo.logisticareversa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InformacoesItem extends AppCompatActivity {

    private TextView textGarrafasQuebradas;
    private TextView textGarrafasReciclagem;
    private TextView textGarrafasReclamacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_item);

        // Referências dos TextViews
        textGarrafasQuebradas = findViewById(R.id.textGarrafasQuebradas);
        textGarrafasReciclagem = findViewById(R.id.textGarrafasReciclagem);
        textGarrafasReclamacoes = findViewById(R.id.textGarrafasReclamacoes);

        // Carregar as informações fixas
        carregarInformacoesFixas();
    }

    private void carregarInformacoesFixas() {
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
