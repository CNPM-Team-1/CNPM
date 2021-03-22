package utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class NumberHelper {

    public static String addComma(String number) {
        double amount = Double.parseDouble(number);
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }

    public static String removeComma(String number) throws ParseException {
        return number.replaceAll("[^\\d.]", "");
    }
}
