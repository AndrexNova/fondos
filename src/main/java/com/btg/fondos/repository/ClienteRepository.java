package com.btg.fondos.repository;


import com.btg.fondos.models.Cliente;
import com.btg.fondos.models.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente,String> {

}
