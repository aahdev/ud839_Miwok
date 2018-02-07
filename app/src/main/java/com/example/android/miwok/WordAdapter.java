package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorRes;


    public WordAdapter(Activity context, ArrayList<Word> words, int colorRes) {
        super(context, 0, words);
        mColorRes = colorRes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
        }

        Word currentWord = getItem(position);

        // Image
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);

        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageSrc());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        // Default Translation
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Miwok Word
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        View textArea = listItemView.findViewById(R.id.textarea);
        int color = ContextCompat.getColor(getContext(), mColorRes);
        textArea.setBackgroundColor(color);

        return listItemView;
    }
}
