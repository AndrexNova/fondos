package com.btg.fondos.domain.port.outbound;


import com.btg.fondos.domain.models.Transaccion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionesRepository extends MongoRepository<Transaccion,String> {

}
