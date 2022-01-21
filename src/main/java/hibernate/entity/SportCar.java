package hibernate.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SPORTCAR")
public class SportCar extends Car{
    @Column
    private int power;
    @Column(name = "max_speed")
    private int maxSpeed;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "sportCar: " +
                "power=" + power +
                ", maxSpeed=" + maxSpeed +
                ", " + super.toString();
    }
}
