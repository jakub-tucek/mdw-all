package cvut.fit.mdw.service;

public class CurrencyConverterImpl implements CurrencyConverter {

    @Override
    public double convert(Currency from, Currency to, double amount) {
        return from.getRate() * amount / to.getRate();
    }
}
