package repository;

import java.util.List;

import exceptions.TableDoesntExistException;
import exceptions.TableExistsException;
import exceptions.TableIsFullException;
import model.Table;

public interface ITableRepository {
    Table get(int id) throws TableDoesntExistException;

    List<Table> getAll();

    void add(Table table) throws TableExistsException;

    void delete(Table table);

    void update(Table table) throws TableIsFullException;
}
