package com.example.demo;

/**
 * Created by i on 15.10.2017.
 */
public class CalculationProcessor {

    public static final String ERR_MESSAGE = "Unexpected error!";

    public static String calculate(Double firstNumber, Double secondNumber, char actionSign) {

        if (firstNumber == null || secondNumber == null || actionSign == 0) {
            return ERR_MESSAGE;
        }

        switch (actionSign) {
            case '+':
                return String.valueOf(firstNumber + secondNumber);
            case '-':
                return String.valueOf(firstNumber - secondNumber);
            case '*':
                return String.valueOf(firstNumber * secondNumber);
            case '/':
                if (secondNumber == 0d) {
                    return "Error! Division by zero!";
                }
                return String.valueOf(firstNumber / secondNumber);
        }

        return ERR_MESSAGE;
    }
}
