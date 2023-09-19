package kg.arzybek.bots.pincity.data;

import jakarta.transaction.Transactional;
import kg.arzybek.bots.pincity.dto.AddressDto;
import kg.arzybek.bots.pincity.dto.PinDto;
import kg.arzybek.bots.pincity.dto.PinState;
import kg.arzybek.bots.pincity.dto.PlaceType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlacesRepository extends BaseRepository<PlacesEntity> {

    public List<PlaceType> getTypesOfCity(Integer id) {
        return em.createQuery("""
                        select distinct p.type
                        from PlacesEntity p
                        where p.cityId = :id
                        """, PlaceType.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<AddressDto> getAddressesOfType(PlaceType placeType, Long chatId) {
        return em.createQuery("""
                        select new kg.arzybek.bots.pincity.dto.AddressDto(p.id,p.address, p.name)
                        from PlacesEntity p
                        inner join ChatsCitiesEntity c
                        on p.cityId = c.cityId
                        where p.type =: placeType and c.chatId =: chatId
                        """, AddressDto.class)
                .setParameter("placeType", placeType)
                .setParameter("chatId", chatId)
                .getResultList();
    }

    public PinDto findPin(Integer addressId) {
        return em.createQuery("""
                        select new kg.arzybek.bots.pincity.dto.PinDto(p.pin, p.state)
                        from PlacesEntity p
                        where p.id = :id
                        """, PinDto.class)
                .setParameter("id", addressId)
                .getSingleResult();
    }

    @Transactional
    public int updateState(PinState state, Integer addressId, Long userId) {
        return em.createQuery("""
                        update PlacesEntity p
                        set p.state =: state, p.updatedBy =: userId
                        where p.id = :id
                        """)
                .setParameter("id", addressId)
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    public int updatePin(Integer addressId, String pin, Long userId) {
        return em.createQuery("""
                        update PlacesEntity p
                        set p.pin =: pin, p.updatedBy =: userId
                        where p.id = :id
                        """)
                .setParameter("id", addressId)
                .setParameter("pin", pin)
                .setParameter("userId", userId)
                .executeUpdate();
    }
}
