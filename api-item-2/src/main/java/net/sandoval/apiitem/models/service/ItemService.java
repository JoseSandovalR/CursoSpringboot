package net.sandoval.apiitem.models.service;

import java.util.List;

import net.sandoval.apiitem.models.Item;
import net.sandoval.commons.models.entity.Producto;

public interface ItemService {
	
	public List<Item> buscarTodos();
	public Item buscarPorId(Long id, Integer cantidad);
	public Producto guardar(Producto producto);
	public Producto modificar(Producto producto, Long id);
	public void eliminar(Long id);
}
