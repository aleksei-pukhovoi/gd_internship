package hibernate.service;

import hibernate.entity.Car;
import hibernate.entity.Owner;

import java.util.List;

public interface OwnerService {

    Owner createOwner(Owner owner);

    Owner findOwnerById(long id);

    List<Owner> findAll();

    Owner updateOwner(Owner owner);

    void deleteOwner(Owner owner);

    void deleteOwnerById(long id);

    Owner buyNewCar(Car car, long ownerId);

    Owner saleCar(Car car, long ownerId);
}
