package com.hotel.ena.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hotel.ena.accessingdatarest.Racun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotel.ena.models.RacunEntity;


@Repository
public interface RacunRepository extends JpaRepository<RacunEntity, Long> {

    /*static String save(Racun racun) {
        List<Racun> lista = new ArrayList<Racun>();

        lista.add(racun);
        lista.forEach(System.out::println);

    }*/

    List<RacunEntity> findRacun(@Param("user_id") String user_id);


    boolean existsByIDRacuna(String id);
}
