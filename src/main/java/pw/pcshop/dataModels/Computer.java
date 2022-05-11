package pw.pcshop.dataModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Computers")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
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
}
