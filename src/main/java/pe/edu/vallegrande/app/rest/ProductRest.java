package pe.edu.vallegrande.app.rest;

import pe.edu.vallegrande.app.model.Product;
import pe.edu.vallegrande.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/product")
public class ProductRest {

    private final ProductService productService;

    @Autowired
    public ProductRest(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Flux<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> findById(@PathVariable Integer id) { // Cambiado a Integer
        return productService.findById(id);
    }

    // Estándar REST: POST a la raíz para guardar
    @PostMapping
    public Mono<Product> save(@RequestBody Product product) {
        return productService.save(product);
    }

    // Estándar REST: PUT con ID en la URL para actualizar
    @PutMapping("/{id}")
    public Mono<Product> update(@PathVariable Integer id, @RequestBody Product product) {
        product.setId(id); // Asegura que se actualice el ID correcto
        return productService.update(product);
    }

    // Agregamos el Delete que pide el CRUD
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return productService.deleteById(id);
    }
}