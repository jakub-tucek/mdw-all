package cvut.fit.mdw.service;

public enum  Currency {
    EUR(1.0), USD(0.846098), GBP(1.12008);

    /**
     * Rate to convert currency to euro
     */
    private double rate;

    Currency(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
