package com.example.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

/**
 * Created by i on 01.10.2017.
 */

//@SpringUI
@Theme("myStyles")
//@SpringBootApplication
public class CalculatorView extends UI {

    private TextField displayField;

    private Double numbersArray[] = new Double[2];
    private int arrayIndex = 0;
    private String result = "0";
    private StringBuilder stringElement;
    private char sign;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        stringElement = new StringBuilder();
        buildLayout();
    }

    private void buildLayout() {
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth(297, Unit.PIXELS);
        layout.addStyleName("layout-border");

        HorizontalLayout displayRow = buildDisplayLayout();
        HorizontalLayout firstDigitsRow = buildFirstDigitsRow();
        HorizontalLayout secondDigitsRow = buildSecondDigitsRow();
        HorizontalLayout thirdDigitsRow = buildThirdDigitsRow();
        HorizontalLayout fourthDigitsRow = buildFourthDigitsRow();
        HorizontalLayout fifthSignsRow = buildFifthSignsRow();

        layout.addComponents(displayRow, firstDigitsRow, secondDigitsRow,
                thirdDigitsRow, fourthDigitsRow, fifthSignsRow);

        setContent(layout);
    }

    private HorizontalLayout buildDisplayLayout() {
        HorizontalLayout hl = new HorizontalLayout();

        displayField = new TextField();
        displayField.setWidth(227, Unit.PIXELS);
        displayField.setValue("0");
        displayField.setEnabled(false);

        hl.addComponents(displayField);

        return hl;
    }

    private HorizontalLayout buildFirstDigitsRow() {
        HorizontalLayout hl = new HorizontalLayout();

        Button b1 = new Button(" 1 ");
        b1.addClickListener(e -> readEntry('1'));

        Button b2 = new Button(" 2 ");
        b2.addClickListener(e -> readEntry('2'));

        Button b3 = new Button(" 3 ");
        b3.addClickListener(e -> readEntry('3'));

        Button b4 = new Button(" C ");
        b4.addClickListener(e -> clearAll());

        hl.addComponents(b1, b2, b3, b4);

        return hl;
    }

    private HorizontalLayout buildSecondDigitsRow() {
        HorizontalLayout hl = new HorizontalLayout();

        Button b1 = new Button(" 4 ");
        b1.addClickListener(e -> readEntry('4'));

        Button b2 = new Button(" 5 ");
        b2.addClickListener(e -> readEntry('5'));

        Button b3 = new Button(" 6 ");
        b3.addClickListener(e -> readEntry('6'));

        Button b4 = new Button(" + ");
        b4.addClickListener(e -> {
            processSign('+');
            sign = '+';
        });

        hl.addComponents(b1, b2, b3, b4);

        return hl;
    }

    private HorizontalLayout buildThirdDigitsRow() {
        HorizontalLayout hl = new HorizontalLayout();

        Button b1 = new Button(" 7 ");
        b1.addClickListener(e -> readEntry('7'));

        Button b2 = new Button(" 8 ");
        b2.addClickListener(e -> readEntry('8'));

        Button b3 = new Button(" 9 ");
        b3.addClickListener(e -> readEntry('9'));

        Button b4 = new Button("-");
        b4.setWidth(47, Unit.PIXELS);
        b4.addClickListener(e -> {
            processSign('-');
            sign = '-';
        });

        hl.addComponents(b1, b2, b3, b4);

        return hl;
    }

    private HorizontalLayout buildFourthDigitsRow() {
        HorizontalLayout hl = new HorizontalLayout();

        Button b1 = new Button(" 0 ");
        b1.addClickListener(e -> readEntry('0'));

        Button b2 = new Button(" . ");
        b2.setWidth(47, Unit.PIXELS);
        b2.addClickListener(e -> readEntry('.'));

        Button b3 = new Button(" * ");
        b3.addClickListener(e -> {
            processSign('*');
            sign = '*';
        });

        Button b4 = new Button("/");
        b4.setWidth(47, Unit.PIXELS);
        b4.addClickListener(e -> {
            processSign('/');
            sign = '/';
        });

        hl.addComponents(b1, b2, b3, b4);

        return hl;
    }

    private HorizontalLayout buildFifthSignsRow() {
        HorizontalLayout hl = new HorizontalLayout();

        Button b1 = new Button("<<");
        b1.setWidth(47, Unit.PIXELS);
        b1.addClickListener(e -> readEntry('b'));

        Button b2 = new Button("+/-");
        b2.setWidth(47, Unit.PIXELS);
        b2.addClickListener(e -> readEntry('s'));

        Button b3 = new Button("=");
        b3.setWidth(107, Unit.PIXELS);
        b3.addClickListener(e -> {
            processSign(sign);
            sign = ' ';
        });

        hl.addComponents(b1, b2, b3);

        return hl;
    }

    private void readEntry(char entry) {
        if (entry == '+' || entry == '-' || entry == '*' || entry == '/'
                || entry == 's') {
            sign = entry;
            return;
        }

        if (entry == '.' && (stringElement.length() == 0 || stringElement.indexOf("0", 0) == 0)) {
            if (stringElement.indexOf("0", 0) == 0) {
                stringElement.deleteCharAt(0);
            }
            stringElement.append("0.");
            displayField.setValue("0.");
            return;
        }

        if (entry == '.' && stringElement.indexOf(".") > 0) {
            return;
        }

        if (entry == 'b') {
            if (stringElement.length() == 0) {
                return;
            }
            stringElement.deleteCharAt(stringElement.length() - 1);
            displayField.setValue(String.valueOf(stringElement));
            if (stringElement.length() == 0) {
                displayField.setValue("0");
            }
            return;
        }

        if (stringElement.length() == 1 && stringElement.indexOf("0", 0) == 0) {
            stringElement.deleteCharAt(0);
        }

        stringElement.append(entry);
        displayField.setValue(String.valueOf(stringElement));
    }

    private void processSign(char entry) {
        if (sign == 0 || sign == ' ') {
            sign = entry;
        }

        if (stringElement.length() == 0) {
            if (arrayIndex == 0) {
                numbersArray[0] = 0d;
                arrayIndex = 1;
            }
            return;
        }

        numbersArray[arrayIndex] = Double.valueOf(stringElement.toString());

        if (arrayIndex == 0) {
            stringElement.delete(0, stringElement.length());
            arrayIndex = 1;
            return;
        }

        result = CalculationProcessor.calculate(numbersArray[0], numbersArray[1], sign);
        displayField.setValue(result);
        if (CalculationProcessor.ERR_MESSAGE.equals(result)
                || CalculationProcessor.ZERO_DIVISION_MESSAGE.equals(result)) {
            result = "0";
        }
        numbersArray[0] = Double.valueOf(result);
        arrayIndex = 1;
        stringElement.delete(0, stringElement.length());

    }

    private void clearAll() {
        stringElement.delete(0, stringElement.length());
        displayField.setValue("0");
        result = "0";
        arrayIndex = 0;
        sign = ' ';
    }
}
