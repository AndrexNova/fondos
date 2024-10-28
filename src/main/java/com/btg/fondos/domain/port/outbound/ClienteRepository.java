package com.btg.fondos.domain.port.outbound;


import com.btg.fondos.domain.models.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente,String> {

}
