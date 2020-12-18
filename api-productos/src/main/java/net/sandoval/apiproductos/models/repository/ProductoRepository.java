package net.sandoval.apiproductos.models.repository;

import org.springframework.data.repository.CrudRepository;

import net.sandoval.commons.models.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto , Long> {

}
