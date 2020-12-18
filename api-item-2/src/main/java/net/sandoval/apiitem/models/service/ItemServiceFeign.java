package net.sandoval.apiitem.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.sandoval.apiitem.clientes.ProductoClienteRest;
import net.sandoval.apiitem.models.Item;
import net.sandoval.commons.models.entity.Producto;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements ItemService {

	@Autowired
	private ProductoClienteRest clienteFeign;
	
	@Override
	public List<Item> buscarTodos() {
		// TODO Auto-generated method stub
		return clienteFeign.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item buscarPorId(Long id, Integer cantidad) {
		
		return new Item(clienteFeign.detalle(id), cantidad);
	}

	@Override
	public Producto guardar(Producto producto) {
		
		return clienteFeign.crear(producto);
	}

	@Override
	public Producto modificar(Producto producto, Long id) {
		return clienteFeign.modificar(producto, id);
		
	}

	@Override
	public void eliminar(Long id) {
		clienteFeign.eliminar(id);
		
	}

}
