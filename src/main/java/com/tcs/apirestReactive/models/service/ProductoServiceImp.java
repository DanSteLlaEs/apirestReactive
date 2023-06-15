package com.tcs.apirestReactive.models.service;

import com.tcs.apirestReactive.models.document.Producto;
import com.tcs.apirestReactive.models.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Properties;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    private ProductoRepository productoRepositoryy;

    @Override
    public Flux<Producto> findAll() {
        return productoRepositoryy.findAll();
    }

    @Override
    public Mono<Producto> findById(Integer id) {
        return productoRepositoryy.findById(id);
    }

    @Override
    public Mono<Producto> save(Producto producto) {
        return productoRepositoryy.save(producto);
    }

    @Override
    public Mono<Void> delete(Producto producto) {
        return productoRepositoryy.delete(producto);
    }
}
