package com.example.demo;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by i on 15.10.2017.
 */
public class CalculationProcessorTest {
    @Test
    public void testCalculate() throws Exception {
        Double firstNumber = 1d;
        Double secondNumber = 2d;
        char actionSign = '+';

        Assert.assertEquals("Should be 3!",
                "3.0", CalculationProcessor.calculate(firstNumber, secondNumber, actionSign));
        actionSign = '-';
        Assert.assertEquals("Should be -1!",
                "-1.0", CalculationProcessor.calculate(firstNumber, secondNumber, actionSign));
        actionSign = '*';
        Assert.assertEquals("Should be 2!",
                "2.0", CalculationProcessor.calculate(firstNumber, secondNumber, actionSign));
        actionSign = '/';
        Assert.assertEquals("Should be 0.5!",
                "0.5", CalculationProcessor.calculate(firstNumber, secondNumber, actionSign));
        secondNumber = 0d;
        Assert.assertEquals("Should be the Division by zero error!",
                "Error! Division by zero!", CalculationProcessor.calculate(firstNumber, secondNumber, actionSign));
    }

    @Test
    public void testUnexpectedError() {
        Double firstNumber = null;
        Double secondNumber = 2d;
        char actionSign = '+';

        Assert.assertEquals("Should be the Unexpected error message!",
                "Unexpected error!", CalculationProcessor.calculate(firstNumber, secondNumber, actionSign));
        firstNumber = 1d;
        actionSign = 0;
        Assert.assertEquals("Should be the Unexpected error message!",
                "Unexpected error!", CalculationProcessor.calculate(firstNumber, secondNumber, actionSign));
    }

}