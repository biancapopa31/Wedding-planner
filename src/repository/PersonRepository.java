package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                person.setTableNumber(resultSet.getInt("table_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return person;
    }

    @Override
    public int add(Person person) {
        int id = 0;
        String sqlStatement;
        if (person.getTableNumber() == 0){
            sqlStatement = "INSERT INTO person (lastName, firstName, telephone) VALUES (?, ?, ?)";
        }else
            sqlStatement = "INSERT INTO person (lastName, firstName, telephone, table_id) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, person.getLastName());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getTelephone());
            if (person.getTableNumber() != 0)
                preparedStatement.setInt(4, person.getTableNumber());
            preparedStatement.executeUpdate();

            PreparedStatement idStatement = connection.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet idResult = idStatement.executeQuery();

            if (idResult.next()) {
                long newPersonId = idResult.getLong(1);
                id = (int) newPersonId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(Person person) {
        String sqlStatement;
        String sqlStatement2 = "";
        if (person.getTableNumber() == 0){
            sqlStatement = "UPDATE person SET lastName = ?, firstName = ?, telephone = ? WHERE id = " + person.getId();
            sqlStatement2 = "UPDATE person SET table_id = NULL WHERE id = " + person.getId();
        }else 
            sqlStatement = "UPDATE person SET lastName = ?, firstName = ?, telephone = ?, table_id =? WHERE id = "+ person.getId();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, person.getLastName());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getTelephone());
            if (person.getTableNumber() != 0)
                preparedStatement.setInt(4, person.getTableNumber());
            preparedStatement.executeUpdate();

            if(person.getTableNumber() == 0){
                PreparedStatement preparedStatement2 = connection.prepareStatement(sqlStatement2);
                preparedStatement2.executeUpdate();
            }
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();

        String sqlStatement = "SELECT * FROM person";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setLastName(resultSet.getString("lastName"));
                person.setFirstName(resultSet.getString("firstName"));
                person.setTelephone(resultSet.getString("telephone"));
                person.setTableNumber(resultSet.getInt("table_id"));
                persons.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return persons;
    }

	@Override
	public void delete(Person person) {
        String sqlStatement = "DELETE FROM person WHERE id = " + person.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}
