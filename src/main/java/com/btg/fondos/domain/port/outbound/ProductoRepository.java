package com.btg.fondos.domain.port.outbound;


import com.btg.fondos.domain.models.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends MongoRepository<Producto,String> {

}
