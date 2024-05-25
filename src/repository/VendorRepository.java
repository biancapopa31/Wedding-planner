package repository;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import model.Vendor;

public class VendorRepository implements IPersonRepository<Vendor>{

    private final Connection connection;

    private final IPersonRepository<model.Person> personRepository;


    public VendorRepository(Connection connection) {
        this.connection = connection;
        personRepository = new PersonRepository(connection);
    }

    @Override
    public Vendor getById(int id) {
        Vendor vendor = new Vendor();
        vendor = (Vendor) personRepository.getById(id);

        String sqlStatement = "SELECT * FROM vendor WHERE id = " + id;
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            while(resultSet.next()){
                vendor.setPrice(resultSet.getInt("price"));
                vendor.setServiceType(resultSet.getString("service_type"));
                vendor.setNotes(resultSet.getString("notes"));
                vendor.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return vendor;
    }

    @Override
    public List<Vendor> getAll() {
        List<Vendor> vendors = new ArrayList<>();

        String sqlStatement = "SELECT * FROM vendor join person on vendor.id = person.id";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            while (resultSet.next()) {
                Vendor vendor = new Vendor();
                vendor.setId(resultSet.getInt("id"));
                vendor.setLastName(resultSet.getString("lastName"));
                vendor.setFirstName(resultSet.getString("firstName"));
                vendor.setTelephone(resultSet.getString("telephone"));
                vendor.setPrice(resultSet.getInt("price"));
                vendor.setServiceType(resultSet.getString("service_type"));
                vendor.setNotes(resultSet.getString("notes"));
                vendor.setEmail(resultSet.getString("email"));
                vendors.add(vendor);
            }

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return vendors;
        
    }

    @Override
    public int add(Vendor person) {

        int id = personRepository.add(person);

        String sqlStatement = "INSERT INTO vendor (id, email, price, service_type, notes) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setDouble(3, person.getPrice());
            preparedStatement.setString(4, person.getServiceType());
            preparedStatement.setString(5, person.getNotes());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(Vendor person) {
        personRepository.update(person);

        String sqlStatement = "UPDATE vendor SET email = ?, price = ?, service_type = ?, notes = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, person.getEmail());
            preparedStatement.setDouble(2, person.getPrice());
            preparedStatement.setString(3, person.getServiceType());
            preparedStatement.setString(4, person.getNotes());
            preparedStatement.setInt(5, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Vendor person) {
        String sqlStatement = "DELETE FROM vendor WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        personRepository.delete(person);

    }

}
