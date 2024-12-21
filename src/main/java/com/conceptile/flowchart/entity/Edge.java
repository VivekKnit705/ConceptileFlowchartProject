package com.conceptile.flowchart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Edge{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long source;
	private long destination;
	
	@ManyToOne
	@JoinColumn(name = "flowchart_id")
	private Flowchart flowchart;
	
}
