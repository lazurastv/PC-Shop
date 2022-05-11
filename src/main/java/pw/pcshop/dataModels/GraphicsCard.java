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
@Table(name = "graphicsCards")
public class GraphicsCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "graphicsCard")
    private Computer computer;

    @Column
    private String manufacturer;

    @Column
    private String series;

    @Column
    private int vRAM;

    @Column
    private String memoryType;

    @Column
    private int length;

    @Column
    private int width;

    @Column
    private int height;
}
