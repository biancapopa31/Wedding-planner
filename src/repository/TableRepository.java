package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import exceptions.TableDoesntExistException;
import exceptions.TableExistsException;
import model.Table;
import model.Vendor;
import model.Guest;
import model.Person;
import java.sql.*;

public class TableRepository implements ITableRepository{

    private final Connection connection;
    private final IPersonRepository<Person> personRepository;
    private final IPersonRepository<Guest> guestRepository;
    private final IPersonRepository<Vendor> vendorRepository;

    public TableRepository(Connection connection) {
        this.connection = connection;
        personRepository = new PersonRepository(connection);
        guestRepository = new GuestRepository(connection);
        vendorRepository = new VendorRepository(connection);
    }
    
    private List<Person> getMembers(int tableNumber, List<Guest> guests, List<Vendor> vendors) {
        List<Person> members = new ArrayList<>();

        members = Stream.concat(guests.stream().filter(guest -> guest.getTableNumber() == tableNumber), 
                                vendors.stream().filter(vendor -> vendor.getTableNumber() == tableNumber))
                                .collect(Collectors.toList());

        return members;
    }

    @Override
    public Table get(int id) throws TableDoesntExistException{
        Table table = new Table();
        List<Guest> guests = guestRepository.getAll();
        List<Vendor> vendors = vendorRepository.getAll();
        String sqlStatement = "SELECT * FROM tables WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            if (resultSet.next()) {
                table.setTableNumber(resultSet.getInt("id"));
                table.setCapacity(resultSet.getInt("capacity"));
            }
            else{
                throw new TableDoesntExistException(id);
            }

            table.setMembers(getMembers(id, guests, vendors));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return table;
    }

    @Override
    public List<Table> getAll() {
        List<Table> tables = new ArrayList<>();
        String sqlStatement = "SELECT * FROM tables";
        List<Guest> guests = guestRepository.getAll();
        List<Vendor> vendors = vendorRepository.getAll();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            while (resultSet.next()) {
                Table table = new Table();
                table.setTableNumber(resultSet.getInt("id"));
                table.setCapacity(resultSet.getInt("capacity"));
                table.setMembers(getMembers(table.getTableNumber(), guests, vendors));
                
                tables.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return tables;
    }

    @Override
    public void add(Table table) throws TableExistsException{
        String sqlStatement = "INSERT INTO tables (id, capacity) VALUES (?, ?)";
        String verify = "SELECT * FROM tables WHERE id = " + table.getTableNumber();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(verify);
            if (resultSet.next()) {
                throw new TableExistsException(table.getTableNumber());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, table.getTableNumber());
            preparedStatement.setInt(2, table.getCapacity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Table table){
        String sqlStatement = "DELETE FROM tables WHERE id = " + table.getTableNumber();
        List<Person> members = table.getmembers();
        for (Person member : members) {
            member.setTableNumber(0);
            personRepository.update(member);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Table table) {
        // update table number for all members
        List<Person> members = table.getmembers();
        for (Person member : members) {
            personRepository.update(member);
        }

        String sqlStatement = "UPDATE tables SET capacity = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, table.getCapacity());
            preparedStatement.setInt(2, table.getTableNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
