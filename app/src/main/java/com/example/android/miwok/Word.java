package com.example.android.miwok;

public class Word {

    /* Variables */
    private String mMiwokTranslation;
    private String mDefaultTranslation;

    /* New Word Object */
    public Word(String defaultTranslation, String miwokTranslation) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
    }

    /* Getter */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

}
