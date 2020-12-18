package net.sandoval.apiproductos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sandoval.commons.models.entity.Producto;
import net.sandoval.apiproductos.models.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private ProductoRepository repoProducto;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> buscarTodos() {
		// TODO Auto-generated method stub
		return  (List<Producto> )repoProducto.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public Producto buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return repoProducto.findById(id).orElse(null);
	}


	@Override
	@Transactional
	public Producto save(Producto producto) {
		return repoProducto.save(producto);
		
	}


	@Override
	@Transactional
	public void deleteById(Long id) {
		repoProducto.deleteById(id);
	}

}
