package cvut.fit.mdw;

import cvut.fit.mdw.service.Currency;
import cvut.fit.mdw.service.CurrencyConverterImpl;

public class Main {

    public static void main(String[] args) {
        CurrencyConverterImpl converter = new CurrencyConverterImpl();


        double convertEur = converter.convert(Currency.EUR, Currency.EUR, 100);
        System.out.println("100 EUR to EUR is = " + convertEur);


        double convertUsd = converter.convert(Currency.USD, Currency.GBP, 100);
        System.out.println("100 USD to GBP is = " + convertUsd);
    }
}
