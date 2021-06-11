package com.narabel.servicioitem.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Producto {

	private Long id;
	private String nombre;
	private BigDecimal precio;
	private LocalDate createAt;

}
