package com.hotel.ena.accessingdatarest;


        import java.math.BigDecimal;
        import java.util.List;

        import org.springframework.data.repository.PagingAndSortingRepository;
        import org.springframework.data.repository.query.Param;
        import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "racun", path = "racun")
public interface RacunRepository extends PagingAndSortingRepository<Racun, Long> {

    List<Racun> findRacun(@Param("user_id") String user_id);

}