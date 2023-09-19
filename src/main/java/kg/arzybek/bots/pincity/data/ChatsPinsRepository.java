package kg.arzybek.bots.pincity.data;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class ChatsPinsRepository extends BaseRepository<ChatsPinsEntity> {

    public Integer findByChatId(Long chatId) {
        return em.createQuery("""
                        select c.placeId
                        from ChatsPinsEntity c
                        where c.chatId =: chatId
                        """, Integer.class)
                .setParameter("chatId", chatId)
                .getResultStream().findFirst().orElse(null);
    }

    public int deleteByChatId(Long chatId) {
        return em.createQuery("""
                        delete from ChatsPinsEntity c where c.chatId =: chatId
                        """)
                .setParameter("chatId", chatId)
                .executeUpdate();
    }

}
