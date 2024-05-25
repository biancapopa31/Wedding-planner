package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Guest;

public class GuestRepository implements IPersonRepository<model.Guest> {

    private final IPersonRepository<model.Person> personRepository;

    private final Connection connection;

    public GuestRepository(Connection connection) {
        this.connection = connection;
        personRepository = new PersonRepository(connection);
    }

    @Override
    public Guest getById(int id) {
        Guest guest = new Guest();
        guest = (Guest) personRepository.getById(id);

        String sqlStatement = "SELECT * FROM guest WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            while (resultSet.next()) {
                guest.setTableNumber(resultSet.getInt("tableNumber"));
                guest.setInviteStatus(resultSet.getString("invite_status"));
                guest.setRole(resultSet.getString("role"));
                guest.setSide(resultSet.getString("side"));
                guest.setRelationship(resultSet.getString("relationship"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return guest;
    }
    
    @Override
    public List <Guest> getAll() {
        List<Guest> guests = new ArrayList<>();

        String sqlStatement = "SELECT * FROM guest join person on guest.id = person.id";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            while(resultSet.next()){
                Guest guest = new Guest();
                guest.setId(resultSet.getInt("id"));
                guest.setLastName(resultSet.getString("lastName"));
                guest.setFirstName(resultSet.getString("firstName"));
                guest.setTelephone(resultSet.getString("telephone"));
                guest.setTableNumber(resultSet.getInt("table_id"));
                guest.setInviteStatus(resultSet.getString("invite_status"));
                guest.setRole(resultSet.getString("role"));
                guest.setSide(resultSet.getString("side"));
                guest.setRelationship(resultSet.getString("relationship"));
                guests.add(guest);
            }

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

        return guests;
        
    }


    @Override
    public int add(Guest person) {
        int id = personRepository.add(person);
        String sqlStatementGuest = "INSERT INTO guest (id, invite_status, role, side, relationship) VALUES (?, ?, ?, ?, ?)";
        
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatementGuest);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, person.getInviteStatus().toString());
            preparedStatement.setString(3, person.getRole().toString());
            preparedStatement.setString(4, person.getSide().toString());
            preparedStatement.setString(5, person.getRelationship().toString());
            preparedStatement.executeUpdate();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public void update(Guest person) {
        personRepository.update(person);
        String sqlStatement = "UPDATE guest SET invite_status = ?, role = ?, side = ?, relationship = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, person.getInviteStatus().toString());
            preparedStatement.setString(2, person.getRole().toString());
            preparedStatement.setString(3, person.getSide().toString());
            preparedStatement.setString(4, person.getRelationship().toString());
            preparedStatement.setInt(5, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Guest person) {
        String sqlStatement = "DELETE FROM guest WHERE id = " + person.getId();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        personRepository.delete(person);
    }
}
