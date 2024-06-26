package com.palazzo.logisticareversa;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends BaseAdapter {
    private final Context contexto;
    private final List<Item> itens;
    private final ArrayList<Item> listaOriginal;

    public ItemListAdapter(Context contexto2, ArrayList<Item> itens2) {
        this.contexto = contexto2;
        this.itens = itens2;
        this.listaOriginal = new ArrayList<>(itens2);
    }

    public List<Item> getItens() {
        return this.itens;
    }

    public int getCount() {
        return this.itens.size();
    }

    public Object getItem(int i) {
        return this.itens.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.contexto).inflate(R.layout.list_itens, viewGroup, false);
        }
        Item item = this.itens.get(position);
        ((TextView) view.findViewById(R.id.textViewNumeroPedido)).setText(String.valueOf(item.getNumeroPedido()));
        ((TextView) view.findViewById(R.id.textViewQuantidade)).setText(String.valueOf(item.getQuantidade()));
        ((TextView) view.findViewById(R.id.textViewDataEntrega)).setText(String.valueOf(item.getDataEntrega()));
        ((TextView) view.findViewById(R.id.textViewDataDevolucao)).setText(String.valueOf(item.getDataDevolucao()));
        ((TextView) view.findViewById(R.id.textViewStatus)).setText(String.valueOf(item.getStatus()));
        return view;
    }

    public void atualizarLista(List<Item> novaLista) {
        this.itens.clear();
        this.itens.addAll(novaLista);
        this.listaOriginal.clear();
        this.listaOriginal.addAll(novaLista);
        notifyDataSetChanged();
    }

}