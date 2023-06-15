package com.tcs.apirestReactive.models.repository;

import com.tcs.apirestReactive.models.document.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


public interface ProductoRepository extends ReactiveMongoRepository<Producto,Integer> {

}
