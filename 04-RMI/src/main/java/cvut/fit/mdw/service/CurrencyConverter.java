package cvut.fit.mdw.service;

@FunctionalInterface
public interface CurrencyConverter {

    double convert(Currency from, Currency to, double amount);

}
