package com.btg.fondos.domain.port.outbound;


import com.btg.fondos.domain.models.ClienteProducto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteProductoRepository extends MongoRepository<ClienteProducto,String> {

}
