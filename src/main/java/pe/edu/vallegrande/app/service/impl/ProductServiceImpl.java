package pe.edu.vallegrande.app.service.impl;

import pe.edu.vallegrande.app.model.Product;
import pe.edu.vallegrande.app.repository.ProductRepository;
import pe.edu.vallegrande.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Flux<Product> findAll() {
        log.info("Listando todos los productos activos e inactivos");
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> findById(Integer id) {
        log.info("Buscando producto por ID: {}", id);
        return productRepository.findById(id);
    }

    @Override
    public Mono<Product> save(Product product) {
        log.info("Registrando nuevo producto: {}", product.getName());
        product.setStatus("A"); // Estado Activo por defecto
        return productRepository.save(product);
    }

    @Override
    public Mono<Product> update(Product product) {
        log.info("Actualizando producto ID: {}", product.getId());
        return productRepository.findById(product.getId())
                .flatMap(existingProduct -> {
                    // Mantenemos el estado actual o lo forzamos a Activo si es necesario
                    product.setStatus(existingProduct.getStatus());
                    return productRepository.save(product);
                })
                .doOnSuccess(p -> log.info("Producto actualizado correctamente"));
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        log.warn("Cambiando estado a inactivo del producto ID: {}", id);
        return productRepository.findById(id)
                .flatMap(product -> {
                    product.setStatus("I"); // Eliminación lógica (Inactivo)
                    return productRepository.save(product);
                })
                .then(); // Retornamos Mono<Void> para indicar que terminó
    }
}