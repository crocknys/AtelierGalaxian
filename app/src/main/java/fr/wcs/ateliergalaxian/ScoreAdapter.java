package fr.wcs.ateliergalaxian;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ScoreAdapter extends ArrayAdapter<Model> {

    public ScoreAdapter(Context context, ArrayList<Model> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Model player = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.player_model_activity, parent, false);
        }

        TextView nom = convertView.findViewById(R.id.nom);
        TextView score = convertView.findViewById(R.id.score);

        nom.setText(player.getJoueur());
        score.setText(player.getScore());

        return convertView;
    }
}
