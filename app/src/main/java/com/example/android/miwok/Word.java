package com.example.android.miwok;

public class Word {

    private static final int NO_IMAGE_PROVIDED = -1;
    /* Variables */
    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mImageSrc = NO_IMAGE_PROVIDED;
    private int mAudioSrc;

    /* New Word Object */
    public Word(String defaultTranslation, String miwokTranslation, int audioSrc) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mAudioSrc = audioSrc;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageSrc, int audioSrc) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mImageSrc = imageSrc;
        mAudioSrc = audioSrc;
    }

    /* Getter */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageSrc() {
        return mImageSrc;
    }

    public int getAudioSrc() {
        return mAudioSrc;
    }

    // Test if Image exists
    public boolean hasImage() {
        return mImageSrc != NO_IMAGE_PROVIDED;
    }

}
