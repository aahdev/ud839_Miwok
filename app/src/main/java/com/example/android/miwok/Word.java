package com.example.android.miwok;

public class Word {

    private static final int NO_IMAGE_PROVIDED = -1;
    /* Variables */
    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mImageSrc = NO_IMAGE_PROVIDED;

    /* New Word Object */
    public Word(String defaultTranslation, String miwokTranslation) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageSrc) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mImageSrc = imageSrc;
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

    public boolean hasImage() {
        return mImageSrc != NO_IMAGE_PROVIDED;
    }

}
