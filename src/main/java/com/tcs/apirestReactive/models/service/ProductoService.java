package com.tcs.apirestReactive.models.service;

import com.tcs.apirestReactive.models.document.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {

    public Flux<Producto> findAll();

    public Mono<Producto> findById(Integer id);

    public Mono<Producto>  save(Producto producto);

    public Mono<Void> delete(Producto producto);
}
