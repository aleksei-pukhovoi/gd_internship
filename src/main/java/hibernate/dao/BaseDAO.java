package hibernate.dao;

public interface BaseDAO <T, K> {

    T findById(K id);

    K save (T entity);

    T update(T entity);

    void delete(T entity);
}
