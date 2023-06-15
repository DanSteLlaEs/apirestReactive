package com.tcs.apirestReactive.Controller;

import com.tcs.apirestReactive.models.document.Producto;
import com.tcs.apirestReactive.models.service.ProductoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @ApiOperation(value="get all productos")
    @ApiResponses({
            @ApiResponse(code=204,message = "No Content"),
            @ApiResponse(code=400,message = "Bad request")
    })
    @GetMapping
    public Mono<ResponseEntity<Flux<Producto>>> listar(){
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.findAll()));
    }
    @ApiOperation(value="get all productos")
    @ApiResponses({
            @ApiResponse(code=204,message = "No Content"),
            @ApiResponse(code=400,message = "Bad request")
    })
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Producto>> ver(@PathVariable Integer id){
        return service.findById(id).map(p -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @ApiOperation(value="get all productos")
    @ApiResponses({
            @ApiResponse(code=204,message = "No Content"),
            @ApiResponse(code=400,message = "Bad request")
    })
    @PostMapping
    public Mono<ResponseEntity<Producto>> crear(@RequestBody Producto Producto){
       return service.save(Producto).map(p -> ResponseEntity
       .created(URI.create("/api/productos".concat(p.getId().toString())))
               .contentType(MediaType.APPLICATION_JSON)
               .body(p)
       );

    }

    @ApiOperation(value="get all productos")
    @ApiResponses({
            @ApiResponse(code=204,message = "No Content"),
            @ApiResponse(code=400,message = "Bad request")
    })
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Producto>> editar(@RequestBody Producto producto,@PathVariable Integer id){
        return service.findById(id).flatMap(p ->{
            p.setNombre(producto.getNombre());
            p.setDescripcion(producto.getDescripcion());
            p.setPrecio(producto.getPrecio());
            p.setCantidad(producto.getCantidad());
            return service.save(p);
        }).map(p -> ResponseEntity.created(URI.create("/api/productos/".concat(p.getId().toString())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @ApiOperation(value="get all productos")
    @ApiResponses({
            @ApiResponse(code=204,message = "No Content"),
            @ApiResponse(code=400,message = "Bad request")
    })
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable Integer id){
        return service.findById(id).flatMap(p -> {
            return service.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

}
