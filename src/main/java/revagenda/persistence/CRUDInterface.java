package revagenda.persistence;

import revagenda.models.Model;

import java.util.List;

//like CrudInterface<TestTableModel extends Model>
public interface CRUDInterface<T extends Model> {
    //CRUD - create, read, update, delete
    public T create(T model);
    public T read(int id);
    public void update(T model);
    public void delete(int id);
    public void delete(T model);
    public List<T> getAll();

}
