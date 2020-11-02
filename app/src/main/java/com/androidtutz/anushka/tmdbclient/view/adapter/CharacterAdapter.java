package com.androidtutz.anushka.tmdbclient.view.adapter;

import android.app.Dialog;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidtutz.anushka.tmdbclient.R;
import com.androidtutz.anushka.tmdbclient.databinding.CharacterListItemBinding;
import com.androidtutz.anushka.tmdbclient.model.Character;
import com.squareup.picasso.Picasso;

public class CharacterAdapter extends PagedListAdapter<Character, CharacterAdapter.CharacterViewHolder> {
    private Context context;

    Dialog popupCharacter;
    ImageView closeDialogCharacter;
    ImageView ivMovieLarge;
    TextView tvCharacterName;
    TextView tvCharacterDescription;

    public CharacterAdapter(Context context) {
        super(Character.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         CharacterListItemBinding characterListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.character_list_item, parent,false);
        return new CharacterViewHolder(characterListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {

        Character character = getItem(position);

        holder.characterListItemBinding.setCharacter(character);
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder {
     private CharacterListItemBinding characterListItemBinding;

        public CharacterViewHolder(@NonNull CharacterListItemBinding characterListItemBinding) {
            super(characterListItemBinding.getRoot());
            this.characterListItemBinding = characterListItemBinding;

            characterListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {

                        Character seletedCharacter = getItem(position);
                        popupCharacter = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                        popupCharacter.setContentView(R.layout.popup_character);
                        closeDialogCharacter = popupCharacter.findViewById(R.id.close_dialog_character);
                        ivMovieLarge = popupCharacter.findViewById(R.id.ivMovieLarge);
                        tvCharacterName = popupCharacter.findViewById(R.id.tvCharacterName);
                        tvCharacterDescription = popupCharacter.findViewById(R.id.tvCharacterDescription);

                        Picasso.get().load(seletedCharacter.getThumbnail().toString()).into(ivMovieLarge);
                        tvCharacterName.setText(seletedCharacter.getName());
                        tvCharacterDescription.setText(seletedCharacter.getDescription());

                        closeDialogCharacter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popupCharacter.dismiss();
                            }
                        });

                        closeDialogCharacter.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        popupCharacter.show();
                    }
                }
            });
        }
    }
}