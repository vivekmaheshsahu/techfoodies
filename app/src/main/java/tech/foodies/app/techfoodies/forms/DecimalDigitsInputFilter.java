package tech.foodies.app.techfoodies.forms;


import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */
public class DecimalDigitsInputFilter implements InputFilter {

    Pattern mPattern;

    public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
        mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?");
//        mPattern=Pattern.compile("[0-9]{0," + (digitsBeforeZero) + "}+((\\:[0-9]{0," + (digitsAfterZero) + "})?)||(\\.)?");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        Matcher matcher = mPattern.matcher(dest);
        if (!matcher.matches())
            return "";
        return null;
    }

}
