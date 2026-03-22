package pe.edu.vallegrande.app.service;

import pe.edu.vallegrande.app.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> findAll();

    Mono<Product> findById(Integer id);

    Mono<Product> save(Product product);

    Mono<Product> update(Product product);

    Mono<Void> deleteById(Integer id); // Para completar el CRUD solicitado
    
}