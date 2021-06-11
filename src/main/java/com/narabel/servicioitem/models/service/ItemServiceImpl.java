package com.narabel.servicioitem.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.narabel.servicioitem.models.Item;
import com.narabel.servicioitem.models.Producto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ItemServiceImpl implements ItemService {

	private static final String URI_PRODUCTO = "http://localhost:8001/";

	@Autowired
	private RestTemplate clienteRest;

	@Override
	public List<Item> findAll() {
		String uri = String.format("%s%s", URI_PRODUCTO, "listar");
		log.info("URI list Item: {}", uri);

		List<Producto> productos = Arrays.asList(
				this.clienteRest.getForObject(uri, Producto[].class)
			);

		return productos.stream()
				.map(producto -> new Item(producto, 1))
				.collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		String uri = String.format("%s%s", URI_PRODUCTO, "/ver/{id}");
		log.info("URI Item por Id: {}", uri);

		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());

		Producto producto = this.clienteRest.getForObject(uri, Producto.class, pathVariables);
		return new Item(producto, cantidad);
	}

}
