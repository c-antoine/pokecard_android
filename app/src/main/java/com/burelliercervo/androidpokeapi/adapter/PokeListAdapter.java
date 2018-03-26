package com.burelliercervo.androidpokeapi.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.model.Pokemon;

import java.util.List;

/**
 * Created by iem on 15/11/2017.
 */

public class PokeListAdapter extends BaseAdapter {
    final List<Pokemon> pokemons;
    private Context context;

    public PokeListAdapter(List<Pokemon> pokemons, Context context) {
        this.pokemons = pokemons;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Object getItem(int position) {
        return pokemons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pokemons.get(position).getId();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ItemHolder itemHolder;
        if (view == null) {
            // Liaison XML avec l'objet View
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_item_pokemon, parent, false);

            //  Liaison tampon view
            itemHolder = new ItemHolder();
            itemHolder.name = (TextView) view.findViewById(R.id.pokeName);
            itemHolder.xp = (TextView) view.findViewById(R.id.pokeXp);
            itemHolder.type = (TextView) view.findViewById(R.id.pokeType);
            itemHolder.sprite = (ImageView) view.findViewById(R.id.pokemonVSprite);
            view.setTag(itemHolder);
        } else {
            //Recyclage avec revcupération du holder
            itemHolder = (ItemHolder) view.getTag();
        }
        // Attributions des données
        itemHolder.name.setText(pokemons.get(position).getName());
        itemHolder.xp.setText(pokemons.get(position).getXp());
        itemHolder.type.setText(pokemons.get(position).getType());
//        itemHolder.sprite.setText(pokemons.get(position).getType());
//        itemHolder.sprite.set
        Glide.with(context)
                .load(pokemons.get(position).getSprite())
                .into(itemHolder.sprite);

        return view;
    }

    private class ItemHolder {
        public TextView name;
        public TextView xp;
        public TextView type;
        public ImageView sprite;

    }

    public interface OnItemClickListener {
        void onItemClick(String pageId, String name);
        void onModifClickItem();
        void onRemoveClickItem(String pageId);
    }
}
