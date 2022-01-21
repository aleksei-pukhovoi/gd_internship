package hibernate.service;

import hibernate.dao.OwnerDAO;
import hibernate.dao.OwnerDAOImpl;
import hibernate.entity.Car;
import hibernate.entity.Owner;
import hibernate.util.HibernateSessionFactoryUtil;

import java.util.List;

public class OwnerServiceImpl implements OwnerService{

    private final OwnerDAO ownerDao;
    protected final HibernateSessionFactoryUtil util;

    public OwnerServiceImpl(HibernateSessionFactoryUtil util) {
        this.util = util;
        ownerDao = new OwnerDAOImpl(util.getSessionFactory());
    }

    @Override
    public Owner createOwner(Owner owner) {
        long saveId = ownerDao.save(owner);
        return ownerDao.findById(saveId);
    }

    @Override
    public Owner findOwnerById(long id) {
        return ownerDao.findById(id);
    }

    @Override
    public List<Owner> findAll() {
        return ownerDao.findAll();
    }

    @Override
    public Owner updateOwner(Owner owner) {
        return ownerDao.update(owner);
    }

    @Override
    public void deleteOwner(Owner owner) {
        ownerDao.delete(owner);
    }

    @Override
    public void deleteOwnerById(long id) {
        Owner byId = ownerDao.findById(id);
        ownerDao.delete(byId);
    }

    @Override
    public Owner buyNewCar(Car car, long ownerId) {
        Owner owner = ownerDao.findById(ownerId);
        owner.addCar(car);
        return ownerDao.update(owner);
    }

    @Override
    public Owner saleCar(Car car, long ownerId) {
        Owner owner = ownerDao.findById(ownerId);
        owner.removeCar(car);
        return ownerDao.update(owner);
    }
}
