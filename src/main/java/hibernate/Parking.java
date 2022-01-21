package hibernate;

import hibernate.entity.Owner;
import hibernate.entity.Sedan;
import hibernate.entity.SportCar;
import hibernate.service.OwnerService;
import hibernate.service.OwnerServiceImpl;
import hibernate.util.HibernateSessionFactoryUtil;
import java.util.List;

public class Parking {

    public static void main(String[] args) {
        HibernateSessionFactoryUtil util = new HibernateSessionFactoryUtil();
        OwnerService service = new OwnerServiceImpl(util);
        Owner newOwner = new Owner();
        newOwner.setFirstName("Olga");
        newOwner.setLast_Name("Ivanova");
        newOwner.setAge(22);
        Sedan car = new Sedan();
        car.setColor("Red");
        car.setComfortClass("Business");
        car.setModel("Camry");
        newOwner.addCar(car);

        //save
        Owner savedOwner = service.createOwner(newOwner);
        System.out.println("\n++++++++++++++++++++++++++\n");
        System.out.println("Saved owner: " + savedOwner);
        System.out.println("\n++++++++++++++++++++++++++");

        //update
        savedOwner.setLast_Name("Petrova");
        savedOwner.setAge(30);
        Owner updateOwner = service.updateOwner(savedOwner);
        System.out.println("\n++++++++++++++++++++++++++\n");
        System.out.println("Updated owner: " + updateOwner);
        System.out.println("\n++++++++++++++++++++++++++");

        //findAll
        SportCar car2 = new SportCar();
        car2.setColor("Yellow");
        car2.setModel("Kalina Sport");
        car2.setMaxSpeed(500);
        car2.setPower(300);
        service.buyNewCar(car2, updateOwner.getId());
        List<Owner> all = service.findAll();
        System.out.println("\n++++++++++++++++++++++++++\n");
        System.out.println("All owners: " + all);
        System.out.println("\n++++++++++++++++++++++++++");

        //delete
        service.deleteOwnerById(updateOwner.getId());
        List<Owner> allLast = service.findAll();
        System.out.println("\n++++++++++++++++++++++++++\n");
        System.out.println("Deleted:" + allLast.isEmpty());
        System.out.println("\n++++++++++++++++++++++++++");
    }
}
