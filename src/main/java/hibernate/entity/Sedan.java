package hibernate.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SEDAN")
public class Sedan extends Car{

    @Column(name = "comfort_Class")
    private String comfortClass;

    public String getComfortClass() {
        return comfortClass;
    }

    public void setComfortClass(String comfortClass) {
        this.comfortClass = comfortClass;
    }

//    @Override
//    public String toString() {
//        return super.toString() + ", comfortClass='" + comfortClass;
//    }


    @Override
    public String toString() {
        return "sedan: " +
                "comfortClass='" + comfortClass + '\'' +
                ", " + super.toString();
    }
}
