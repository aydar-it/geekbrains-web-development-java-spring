package geekbrain.java.lesson2.entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "present_value")
    private Integer presentValue;

    public Product() {
    }

    public Product(String name, Integer presentValue) {
        this.name = name;
        this.presentValue = presentValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPresentValue() {
        return presentValue;
    }

    public void setPresentValue(Integer presentValue) {
        this.presentValue = presentValue;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", presentValue=" + presentValue +
                '}';
    }
}
