package com.btg.fondos.repository;


import com.btg.fondos.models.Cliente;
import com.btg.fondos.models.ClienteProducto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteProductoRepository extends MongoRepository<ClienteProducto,String> {

}
