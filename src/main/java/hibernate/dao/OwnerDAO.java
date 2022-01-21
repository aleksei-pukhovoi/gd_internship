package hibernate.dao;

import hibernate.entity.Owner;

import java.util.List;

public interface OwnerDAO extends BaseDAO<Owner, Long> {
    List<Owner> findAll();
}
