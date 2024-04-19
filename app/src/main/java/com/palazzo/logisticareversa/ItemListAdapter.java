package com.palazzo.logisticareversa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ItemListAdapter extends BaseAdapter {
    private Context contexto;
    private ArrayList<Item> itens;
    private ArrayList<Item> listaOriginal;

    public ItemListAdapter(Context contexto2, ArrayList<Item> itens2) {
        this.contexto = contexto2;
        this.itens = itens2;
    }

    public ArrayList<Item> getItens() {
        return this.itens;
    }

    public int getCount() {
        return this.itens.size();
    }

    public Object getItem(int i) {
        return this.itens.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.contexto).inflate(R.layout.list_itens, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.textViewNumeroPedido)).setText(String.valueOf(this.itens.get(position).getNumeroPedido()));
        ((TextView) view.findViewById(R.id.textViewQuantidade)).setText(String.valueOf(this.itens.get(position).getQuantidade()));
        ((TextView) view.findViewById(R.id.textViewDataEntrega)).setText(String.valueOf(this.itens.get(position).getDataEntrega()));
        ((TextView) view.findViewById(R.id.textViewDataDevolucao)).setText(String.valueOf(this.itens.get(position).getDataDevolucao()));
        ((TextView) view.findViewById(R.id.textViewStatus)).setText(String.valueOf(this.itens.get(position).getStatus()));
        return view;
    }

    public void atualizarLista(ArrayList<Item> novaLista) {
        this.itens.clear();
        this.itens.addAll(novaLista);
        notifyDataSetChanged();
    }
}
