package pw.pcshop.dataModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "motherboards")
public class Motherboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "motherboard")
    private Computer computer;

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
