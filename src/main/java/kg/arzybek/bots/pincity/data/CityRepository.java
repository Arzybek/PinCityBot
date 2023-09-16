package kg.arzybek.bots.pincity.data;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityRepository extends BaseRepository<CityEntity> {

    public List<CityEntity> findAll() {
        return em.createQuery("""
                              select c
                              from CityEntity c
                              """, CityEntity.class)
                .getResultList();
    }


}
