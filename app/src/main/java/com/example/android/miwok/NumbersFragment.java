package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    // Global Variables
    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;
    /**
     * Losing Audio Focus
     *
     */
    private AudioManager.OnAudioFocusChangeListener mFocusListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    mMediaPlayer.start();
                    break;

                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMediaPlayer();
                    break;

                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;

                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;
            }
        }
    };
    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };




    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_sub, container, false);


        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //**************************************************
        // Main
        //*
        //**************************************************

        // Create an array of words 1–10
        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word(getActivity().getString(R.string.number_one), "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word(getActivity().getString(R.string.number_two), "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word(getActivity().getString(R.string.number_three), "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word(getActivity().getString(R.string.number_four), "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word(getActivity().getString(R.string.number_five), "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word(getActivity().getString(R.string.number_six), "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word(getActivity().getString(R.string.number_seven), "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word(getActivity().getString(R.string.number_eight), "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word(getActivity().getString(R.string.number_nine), "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word(getActivity().getString(R.string.number_ten), "na’aacha", R.drawable.number_ten, R.raw.number_ten));


        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word word = words.get(position);

                // Release currently active MediaPlayer
                releaseMediaPlayer();

                // Request AudioFocus for playback
                int result = mAudioManager.requestAudioFocus(mFocusListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioSrc());
                    mMediaPlayer.start();

                    // Do Stuff after the files has finished playing
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });



        return rootView;
    }


    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Abandon AudioFocus
            mAudioManager.abandonAudioFocus(mFocusListener);
        }
    }

}
