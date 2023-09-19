package kg.arzybek.bots.pincity.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "chats_pins")
@AllArgsConstructor
@NoArgsConstructor
public class ChatsPinsEntity {

    @Id
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "place_id")
    private Integer placeId;

}
