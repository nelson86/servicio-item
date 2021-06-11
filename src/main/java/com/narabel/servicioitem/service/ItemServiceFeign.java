package com.narabel.servicioitem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narabel.servicioitem.cliente.ProductoClienteRest;
import com.narabel.servicioitem.models.Item;

@Service("serviceFeign")
public class ItemServiceFeign implements ItemService {

	@Autowired
	private ProductoClienteRest clienteFeign;

	@Override
	public List<Item> findAll() {
		return this.clienteFeign.listar().stream()
				.map(producto -> new Item(producto, 2))
				.collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item( this.clienteFeign.detalle(id), cantidad );
	}

}
