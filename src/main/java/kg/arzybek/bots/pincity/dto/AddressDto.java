package kg.arzybek.bots.pincity.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AddressDto {

    private final Integer id;

    private final String address;

    private final String name;

    @Override
    public String toString(){
        return name == null ? address : address + " - " + name;
    }
}
