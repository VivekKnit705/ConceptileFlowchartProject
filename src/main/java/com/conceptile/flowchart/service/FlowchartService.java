package com.conceptile.flowchart.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conceptile.flowchart.entity.Edge;
import com.conceptile.flowchart.entity.Flowchart;
import com.conceptile.flowchart.entity.Node;
import com.conceptile.flowchart.repository.FlowchartRepository;

@Service
public class FlowchartService {

	@Autowired
	private FlowchartRepository flowchartRepository;

	public Flowchart createFlowchart(Flowchart flowchart) {
		return flowchartRepository.save(flowchart);
	}

	public Optional<Flowchart> getFlowchart(long id) {
		return flowchartRepository.findById(id);
	}

	public Flowchart updateFlowchart(Flowchart flowchart) {
		return flowchartRepository.save(flowchart);
	}

	public void deleteFlowchart(long id) {
		flowchartRepository.deleteById(id);

	}

	public boolean validateFlowchart(Long flowchartId) {
		Optional<Flowchart> flowchartOpt = flowchartRepository.findById(flowchartId);
		if (flowchartOpt.isPresent()) {
			Flowchart flowchart = flowchartOpt.get();
			return !hasCycle(flowchart) && isConnected(flowchart);
		}
		return false;
	}

	private boolean hasCycle(Flowchart flowchart) {
		Set<Long> visited = new HashSet<>();
		Set<Long> recStack = new HashSet<>();
		for (Node node : flowchart.getNodes()) {
			if (hasCycleUtil(node.getId(), visited, recStack, flowchart)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasCycleUtil(Long nodeId, Set<Long> visited, Set<Long> recStack, Flowchart flowchart) {
		if (recStack.contains(nodeId)) {
			return true;
		}
		if (visited.contains(nodeId)) {
			return false;
		}
		visited.add(nodeId);
		recStack.add(nodeId);
		for (Edge edge : flowchart.getEdges()) {
			if (edge.getSource() == (nodeId)) {
				if (hasCycleUtil(edge.getDestination(), visited, recStack, flowchart)) {
					return true;
				}
			}
		}
		recStack.remove(nodeId);
		return false;
	}

	private boolean isConnected(Flowchart flowchart) {
		if (flowchart.getNodes().isEmpty()) {
			return true;
		}
		Set<Long> visited = new HashSet<>();
		Queue<Long> queue = new LinkedList<>();
		Long startNodeId = flowchart.getNodes().iterator().next().getId();
		queue.add(startNodeId);
		while (!queue.isEmpty()) {
			Long nodeId = queue.poll();
			if (!visited.contains(nodeId)) {
				visited.add(nodeId);
				for (Edge edge : flowchart.getEdges()) {
					if (edge.getSource() == (nodeId)) {
						queue.add(edge.getDestination());
					}
				}
			}
		}
		return visited.size() == flowchart.getNodes().size();
	}

	public Set<Node> getOutgoingNode(long id, long nodeId) {
		Optional<Flowchart> optFlowchart = flowchartRepository.findById(id);
		if (optFlowchart.isPresent()) {
			Flowchart flowchart = optFlowchart.get();
			return flowchart
					.getEdges().stream().filter(edge -> edge.getSource() == nodeId).map(edge -> flowchart.getNodes()
							.stream().filter(node -> node.getId() == (edge.getDestination())).findFirst().orElse(null))
					.filter(Objects::nonNull).collect(Collectors.toSet());
		}
		return Collections.emptySet();
	}

	public Set<Node> getConnectedNode(long id, long nodeId) {
		Optional<Flowchart> flowchartOpt = flowchartRepository.findById(id);
		if (flowchartOpt.isPresent()) {
			Flowchart flowchart = flowchartOpt.get();
			Set<Node> visited = new HashSet<>();
			Queue<Node> queue = new LinkedList<>();
			Node startNode = flowchart.getNodes().stream().filter(node -> node.getId() == (nodeId)).findFirst()
					.orElse(null);
			if (startNode != null) {
				queue.add(startNode);
				while (!queue.isEmpty()) {
					Node current = queue.poll();
					if (!visited.contains(current)) {
						visited.add(current);
						flowchart.getEdges().stream().filter(edge -> edge.getSource() == (current.getId()))
								.map(edge -> flowchart.getNodes().stream()
										.filter(node -> node.getId() == (edge.getDestination())).findFirst()
										.orElse(null))
								.filter(Objects::nonNull).forEach(queue::add);
					}
				}
			}
			return visited;
		}
		return Collections.emptySet();
	}
}
