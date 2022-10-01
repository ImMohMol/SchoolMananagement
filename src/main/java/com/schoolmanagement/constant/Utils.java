package com.schoolmanagement.constant;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Utils {
    public static Double formatDoubleNumber(double number) {
        NumberFormat formatter = new DecimalFormat("#0.000");
        return Double.valueOf(formatter.format(number));
    }
}
