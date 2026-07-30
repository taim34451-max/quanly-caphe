package com.coffee.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "coffee")
@Entity
public class coffee {
	@Id
	private String id;
	private String name;
	private String description;
	private String photo;
	private double price;
}
