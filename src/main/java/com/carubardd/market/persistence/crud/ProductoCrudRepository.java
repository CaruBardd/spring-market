package com.carubardd.market.persistence.crud;

import com.carubardd.market.persistence.entity.Producto;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    // Recuperar productos pertenecientes a una categoria
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    // Recuperar productos con poco Stock (Less than) y actualmente están siendo vendidos (estado)
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);



       /* CREAR UN MÉTODO CON QUERY DE MANERA NATIVA
    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    List<Producto> getByCategoria(int idCategoria);
    */

}
