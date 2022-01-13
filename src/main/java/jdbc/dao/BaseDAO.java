package jdbc.dao;

import jdbc.entity.Entity;

import java.sql.Connection;
import java.util.List;

public abstract class BaseDAO<K, T extends Entity> {
    protected final Connection connection;

    public BaseDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    public abstract T findById(K id);

    public abstract List<T> findAll();

    public abstract T create(T entity);

    public abstract void delete(K id);

    public abstract T update(T entity);
}
