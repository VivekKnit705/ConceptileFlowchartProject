package com.conceptile.flowchart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conceptile.flowchart.entity.Flowchart;

@Repository
public interface FlowchartRepository extends JpaRepository<Flowchart, Long>{

}
