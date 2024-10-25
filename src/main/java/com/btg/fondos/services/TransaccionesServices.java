package com.btg.fondos.services;

import com.btg.fondos.exceptions.TransaccionesNotFoundException;
import com.btg.fondos.models.Transaccion;
import com.btg.fondos.repository.TransaccionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionesServices {

    @Autowired
    private TransaccionesRepository transaccionesRepository;

    public List<Transaccion> getAll() {
        return transaccionesRepository.findAll();
    }

    public Transaccion getId(String id) {
        return transaccionesRepository.findById(id).orElseThrow(() -> new TransaccionesNotFoundException(id));
    }

    public Transaccion add(Transaccion transaccion) {
        return transaccionesRepository.save(transaccion);
    }

    public Transaccion update(String id, Transaccion transaccion) {
        if (!transaccionesRepository.existsById(id)) {
            throw new TransaccionesNotFoundException(id);
        }
        transaccion.setId(id);
        return transaccionesRepository.save(transaccion);
    }

    public void delete(String id) {
        if (!transaccionesRepository.existsById(id)) {
            throw new TransaccionesNotFoundException(id);
        }
        transaccionesRepository.deleteById(id);
    }

}
