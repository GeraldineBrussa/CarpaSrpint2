package com.grupo2.proyectoDigitalBooking.repository;


import com.grupo2.proyectoDigitalBooking.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFeatureRepository extends JpaRepository<Feature, Long> {

    @Query( value = "select F.* from features F where F.icon=:icon and F.name=:name", nativeQuery = true )
    Optional<Feature> findByFeatureIconAndName(String icon, String name);

    @Query( value = "select F.* from features F where F.id in :ids", nativeQuery = true )
    List<Feature> findByIdsFeatures(@Param("ids") List<Long> featureIdList);

}
