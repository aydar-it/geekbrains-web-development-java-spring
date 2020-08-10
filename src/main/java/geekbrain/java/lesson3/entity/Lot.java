package geekbrain.java.lesson3.entity;

import javax.persistence.*;

@Entity
@Table(name = "lots")
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "bet")
    private Integer bet;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Version
    long version;

    public Lot() {
    }

    public Lot(String name, Integer bet, User user) {
        this.name = name;
        this.bet = bet;
        this.user = user;
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

    public Integer getBet() {
        return bet;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bet=" + bet +
                ", user=" + user +
                '}';
    }
}
