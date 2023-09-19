package kg.arzybek.bots.pincity.data;

import jakarta.persistence.*;
import kg.arzybek.bots.pincity.dto.PinState;
import kg.arzybek.bots.pincity.dto.PlaceType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "places")
public class PlacesEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Id
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PlaceType type;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "pin")
    private String pin;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private PinState state;

}
