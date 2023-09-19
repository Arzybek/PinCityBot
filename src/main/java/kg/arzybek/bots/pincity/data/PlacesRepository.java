package kg.arzybek.bots.pincity.data;

import kg.arzybek.bots.pincity.dto.AddressDto;
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

    public String findPin(Integer addressId) {
        return em.createQuery("""
                        select p.pin
                        from PlacesEntity p
                        where p.id = :id
                        """, String.class)
                .setParameter("id", addressId)
                .getSingleResult();
    }
}
