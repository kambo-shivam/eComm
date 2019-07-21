package com.eComm.utility;

import android.text.InputFilter;
import android.text.Spanned;


public class WhiteSpaceFilter implements InputFilter {


    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned spanned, int i2, int i3) {
        for (int i = start; i < end; i++) {
            if (Character.isWhitespace(source.charAt(i))) {
                return "";
            }
        }
        return null;
    }
}
