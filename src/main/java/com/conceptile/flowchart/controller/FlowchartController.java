package com.conceptile.flowchart.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conceptile.flowchart.entity.Flowchart;
import com.conceptile.flowchart.entity.Node;
import com.conceptile.flowchart.service.FlowchartService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/flowchart")
public class FlowchartController {

	@Autowired
	private FlowchartService flowchartService;

	@Operation(summary = "Create Flowchart", description = "Creating a new Flowchart")
	@PostMapping
	public ResponseEntity<Flowchart> createFlowchart(@RequestBody Flowchart flowchart) {
		return ResponseEntity.ok(flowchartService.createFlowchart(flowchart));
	}

	@Operation(summary = "Get the Flowchart", description = "Get the Flowchart for a Id return the valid Response code if Not present")
	@GetMapping("/{id}")
	public ResponseEntity<Flowchart> getFlowchart(@PathVariable("id") long id) {
		Optional<Flowchart> flowchart = flowchartService.getFlowchart(id);
		return flowchart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Operation(summary="Update the Flowchart", description = "Update the FLowchart with Id")
	@PutMapping("/{id}")
	public ResponseEntity<Flowchart> updateFlowchart(@PathVariable("id") long id,
			@RequestBody Flowchart flowchart) {
		flowchart.setId(id);
		return ResponseEntity.ok(flowchartService.updateFlowchart(flowchart));
	}

	@Operation(summary = "Delete Flowchart", description = "Delete the flowchart with given Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFlowchart(@PathVariable("id") long id) {
		flowchartService.deleteFlowchart(id);
		return ResponseEntity.ok().build();
	}
	
	@Operation(summary = "Validate the flowchart", description = "Balidate the flowchart")
	@GetMapping("/{id}/validate")
	public ResponseEntity<Boolean> validateFlowchart(@PathVariable("id") long id){
		return ResponseEntity.ok(flowchartService.validateFlowchart(id));
	}
	
	
	@Operation(summary = "Get all Outgoing Node", description = "Get all outgoing node for a Node")
	@GetMapping("/{id}/nodes/{nodeId}/outgoing")
	public ResponseEntity<Set<Node>> getOutgoingNode(@PathVariable("id") long id, @PathVariable("nodeId") long nodeId){
		return ResponseEntity.ok(flowchartService.getOutgoingNode(id, nodeId));
	}
	
	@Operation(summary = "Get all Coonnected Node", description = "Get all Connected node for a Node")
	@GetMapping("/{id}/nodes/{nodeId}/connected")
	public ResponseEntity<Set<Node>> getConnectedNode(@PathVariable("id") long id, @PathVariable("nodeId") long nodeId){
		return ResponseEntity.ok(flowchartService.getConnectedNode(id, nodeId));
	}
}
