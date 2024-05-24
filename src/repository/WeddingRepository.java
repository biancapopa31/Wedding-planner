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

    @Override
    public void updateWedding(model.Wedding wedding) {
        String sqlStatement = "UPDATE wedding SET date = ?, location = ? where id = 1";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setDate(1, Date.valueOf(wedding.getDate()));
            preparedStatement.setString(2, wedding.getLocation());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
