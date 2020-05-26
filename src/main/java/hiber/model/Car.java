package hiber.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series")
    private int series;
    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "car")
    private User owner;

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public Car setId(Long id) {
        this.id = id;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Car setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public Car(int series, String name, User owner) {
        this.series = series;
        this.name = name;
        this.owner = owner;
    }

    public int getSeries() {
        return series;
    }

    public Car setSeries(int series) {
        this.series = series;
        return this;
    }

    public String getName() {
        return name;
    }

    public Car setName(String name) {
        this.name = name;
        return this;
    }
}
