package cvut.fit.mdw;

import cvut.fit.mdw.service.Currency;
import cvut.fit.mdw.service.CurrencyConverter;
import cvut.fit.mdw.service.CurrencyConverterImpl;

import java.rmi.Naming;

public class Client {

    public static void main(String[] args) {
        try {
            CurrencyConverter converter = (CurrencyConverter) Naming.lookup("//localhost/currency");

            double convertEur = converter.convert(Currency.EUR, Currency.EUR, 100);
            System.out.println("100 EUR to EUR is = " + convertEur);


            double convertUsd = converter.convert(Currency.USD, Currency.GBP, 100);
            System.out.println("100 USD to GBP is = " + convertUsd);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
