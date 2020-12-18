package net.sandoval.apiitem.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.sandoval.apiitem.models.Item;
import net.sandoval.commons.models.entity.Producto;

@Service("serviceRestTemplate")
public class itemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate clienteRest;
	@Override
	public List<Item> buscarTodos() {
		List<Producto> productos = Arrays.asList( clienteRest.getForObject("http://api-productos/listar", Producto[].class));
		
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item buscarPorId(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Producto producto = clienteRest.getForObject("http://api-productos/ver/{id}", Producto.class, pathVariables);
		return new Item(producto, cantidad);
	}

	@Override
	public Producto guardar(Producto producto) {
		HttpEntity<Producto>body= new HttpEntity<Producto>(producto);
		
		ResponseEntity<Producto> response=clienteRest.exchange("http://api-productos/crear", HttpMethod.POST, body, Producto.class);
		Producto productoResponse=response.getBody();
		return  productoResponse;
	}

	@Override
	public Producto modificar(Producto producto, Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		ResponseEntity<Producto> response = clienteRest.exchange("http://api-productos/editar/{id}", HttpMethod.PUT,body, Producto.class,pathVariables);
		return response.getBody();
	}

	@Override
	public void eliminar(Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		clienteRest.delete("http://api-productos/eliminar/{id}", pathVariables);
		
	}

}
