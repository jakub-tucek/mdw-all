package cvut.fit.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecordsDAO {


    public List<Record> getAll() {
        try {
            Statement stmt = DB.getConnection().createStatement();
            String query = "SELECT * FROM records";
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Record> records = new ArrayList<>();

            while (rs.next()) {
                Record record = new Record();
                record.setId(rs.getInt("id"))
                        .setType(rs.getString("type"))
                        .setLocation(rs.getString("location"))
                        .setCapacity(rs.getInt("capacity"))
                        .setOccupied(rs.getInt("occupied"))
                        .setTrip(rs.getInt("trip"))
                        .setPerson(rs.getString("person"));

                records.add(record);
            }
            stmt.close();

            return records;
        }catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }
}
