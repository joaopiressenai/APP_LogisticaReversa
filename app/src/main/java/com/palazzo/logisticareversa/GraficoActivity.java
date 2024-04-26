package com.palazzo.logisticareversa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraficoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);

        Toolbar toolbar = findViewById(R.id.toolbarGrafico);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GraficoActivity.this.startActivity(new Intent(GraficoActivity.this, MainActivity.class));
            }
        });

        Button buttonAtualizar = findViewById(R.id.buttonAtualizar);
        buttonAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizarGrafico();
            }
        });

        atualizarGrafico();
    }

    private void atualizarGrafico() {
        AnyChartView anyChartView = findViewById(R.id.any_chart_view);

        Cartesian barChart = AnyChart.bar(); // Alteração aqui de column() para bar()

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Distribuidora A", 50));
        data.add(new ValueDataEntry("Distribuidora B", 70));
        data.add(new ValueDataEntry("Distribuidora C", 30));
        data.add(new ValueDataEntry("Distribuidora D", 90));
        data.add(new ValueDataEntry("Distribuidora E", 85));
        data.add(new ValueDataEntry("Distribuidora F", 10));
        data.add(new ValueDataEntry("Distribuidora G", 17));
        data.add(new ValueDataEntry("Distribuidora H", 43));
        data.add(new ValueDataEntry("Distribuidora I", 97));
        data.add(new ValueDataEntry("Distribuidora J", 24));

        barChart.data(data);

        anyChartView.setChart(barChart);
    }
}