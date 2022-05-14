package pw.pcshop.dataModels;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "motherboards")
public class Motherboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "motherboard")
    private List<Computer> computers;

    @Column
    private String manufacturer;

    @Column(unique = true)
    private String series;

    @Column
    private String size;

    @Column
    private String socket;

    @Column
    private int maxRAM;
}
