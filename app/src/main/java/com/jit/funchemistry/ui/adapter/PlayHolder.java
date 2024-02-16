package com.jit.funchemistry.ui.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jit.funchemistry.R;


public class PlayHolder extends RecyclerView.ViewHolder {

    final TextView index;

    public PlayHolder(View itemView) {
        super(itemView);
        index = itemView.findViewById(R.id.play_index);
    }
}
