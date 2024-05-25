package repository;

import java.sql.*;


import model.Person;

public class PersonRepository implements IPersonRepository<Person>{

    private final Connection connection;

    public PersonRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Person getById(int id) {
        Person person = new Person();

        String sqlStatement = "SELECT * FROM person WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            while (resultSet.next()) {
                person.setId(resultSet.getInt("id"));
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

    @Override
    public void add(Person person) {
        String sqlStatement = "INSERT INTO person (lastName, firstName, telephone) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, person.getLastName());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getTelephone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Person person) {

        String sqlStatement = "UPDATE person SET lastName = ?, firstName = ?, telephone = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, person.getLastName());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getTelephone());
            preparedStatement.setInt(4, person.getId());
            preparedStatement.executeUpdate();
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}
