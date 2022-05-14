package pw.pcshop.dataModels;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Computers")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "owner", joinColumns = @JoinColumn(name = "computer_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "processor_id")
    private Processor processor;

    @ManyToOne
    @JoinColumn(name = "graphicsCard_id")
    private GraphicsCard graphicsCard;

    @ManyToOne
    @JoinColumn(name = "motherboard_id")
    private Motherboard motherboard;

    @Column(unique = true)
    private String model;

    @Column
    private String operatingSystem;

    @Column
    private int RAM;

    @Column
    private int length;

    @Column
    private int width;

    @Column
    private int height;

    @Column
    private int memory;

    @Column
    private double wattage;

    @Column
    private double price;

    public void addUser(User user) {
        users.add(user);
    }
}
