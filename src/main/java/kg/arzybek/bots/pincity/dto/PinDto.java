package kg.arzybek.bots.pincity.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class PinDto {

    private final String pin;

    private final PinState pinState;

}
