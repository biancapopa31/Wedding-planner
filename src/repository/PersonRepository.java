package repository;

import java.sql.*;


import model.Person;

public class PersonRepository implements IPersonRepository{

    private final Connection connection;

    public PersonRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Person getPersonById(int id) {
        Person person = new Person();

        String sqlStatement = "SELECT * FROM person WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            while (resultSet.next()) {
                person.setLastName(resultSet.getString("lastName"));
                person.setFirstName(resultSet.getString("firstName"));
                person.setTelephone(resultSet.getString("telephone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return person;
    }

}
