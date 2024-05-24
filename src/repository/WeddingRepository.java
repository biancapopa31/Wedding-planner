package repository;

import java.sql.*;


import model.Wedding;

public class WeddingRepository implements IWeddingRepository{

    private final Connection connection;

    public WeddingRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public model.Wedding getWedding() {
        Wedding wedding = new Wedding();
        String sqlStatement = "SELECT * FROM wedding ";
        IPersonRepository personRepository = new PersonRepository(connection);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            while (resultSet.next()) {
                wedding.setGroom(personRepository.getPersonById(resultSet.getInt("groomId")));
                wedding.setBride(personRepository.getPersonById(resultSet.getInt("brideId")));
                wedding.setGodfather(personRepository.getPersonById(resultSet.getInt("godfatherId")));
                wedding.setGodmother(personRepository.getPersonById(resultSet.getInt("godmotherId")));
                wedding.setDate(resultSet.getDate("date").toLocalDate());
                wedding.setLocation(resultSet.getString("location"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wedding;
    }

}
