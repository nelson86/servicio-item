package com.narabel.servicioitem.service;

import java.util.List;

import com.narabel.servicioitem.models.Item;

public interface ItemService {

	List<Item> findAll();
	Item findById( Long id, Integer cantidad );

}
