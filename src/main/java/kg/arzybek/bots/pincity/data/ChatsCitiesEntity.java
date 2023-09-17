package kg.arzybek.bots.pincity.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chats_cities")
public class ChatsCitiesEntity {

    @Id
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "city_id")
    private UUID cityId;

}
