package com.tcs.apirestReactive;

import com.tcs.apirestReactive.models.document.Producto;
import com.tcs.apirestReactive.models.service.ProductoService;
import com.tcs.apirestReactive.models.service.ProductoServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;



@SpringBootApplication
public class ApirestReactiveApplication  implements CommandLineRunner {

	@Autowired
	private ProductoService service;

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	private static final Logger log=  LoggerFactory.getLogger(ApirestReactiveApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApirestReactiveApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection("productos").subscribe();

				Flux.just(new Producto(1,"TV Panasonic Pantalla LCD"," ",456.89,10),
						new Producto(2,"Sony camara HD"," ",177.89,10),
						new Producto(3,"apple ipod"," ",46.89,10),
						new Producto(4,"Sony Notebook"," ",846.89,10),
						new Producto(5,"Hewlett Packard Multifuncional"," ",200.89,10),
						new Producto(6,"Bianchi bicicleta"," ",70.89,10),
						new Producto(7,"HP Notebook Owen 13"," ",2500.89,10),
						new Producto(8,"mica comoda 5 cajones"," ",150.89,10),
						new Producto(9,"TV Sony Bravia OLED 4K Ultra HD"," ",2255.89,10)
				)
						.flatMap(producto -> {
							return  service.save(producto);
						})
				.subscribe(producto -> log.info("insert : " +producto.getId() +" "+ producto.getNombre()));
	}
}
