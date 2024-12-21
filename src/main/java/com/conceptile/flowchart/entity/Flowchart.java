package com.conceptile.flowchart.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flowchart implements Serializable{


	private static final long serialVersionUID = -6606281658399814077L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(mappedBy = "flowchart", cascade = CascadeType.ALL)
	private Set<Node> nodes;
	
	@OneToMany(mappedBy = "flowchart", cascade = CascadeType.ALL)
	private Set<Edge> edges;

}
