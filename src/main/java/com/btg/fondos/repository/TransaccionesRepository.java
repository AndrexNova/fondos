package com.btg.fondos.repository;


import com.btg.fondos.models.Transaccion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionesRepository extends MongoRepository<Transaccion,String> {

}
