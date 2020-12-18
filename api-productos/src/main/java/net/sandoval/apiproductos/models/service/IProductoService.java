package net.sandoval.apiproductos.models.service;

import java.util.List;

import net.sandoval.commons.models.entity.Producto;

public interface IProductoService {

	public List<Producto> buscarTodos();
	
	public Producto buscarPorId(Long id);
	
	public Producto save(Producto producto);
	
	public void deleteById(Long id);
}
