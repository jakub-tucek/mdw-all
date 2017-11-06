package cvut.fit.service;

public class DAOSingleton {

    private static RecordsDAO recordsDAO;

    public static RecordsDAO getRecordsDAO() {
        if (recordsDAO == null) {
            recordsDAO = new RecordsDAO();
        }

        return recordsDAO;
    }
}
