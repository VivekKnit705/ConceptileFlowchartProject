# ConceptileFlowchartProject

A Spring Boot project for managing flowcharts.

## Requirements

- Java 11 or higher
- Maven

## Setup

1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/ConceptileFlowchartProject.git
   cd ConceptileFlowchartProject
2. Build the project using Maven:
   ```sh
   mvn clean install
3. Run the application
   ```sh
   mvn spring-boot:run
4. Access Swagger UI for API documentation: Open your browser and navigate to
   ```markdown 
   http://localhost:8080/swagger-ui.html

## EndPoints

1. ```POST /flowcharts:``` Create a new flowchart

2. ```GET /flowcharts/{id}:``` Fetch details of a flowchart by its ID

3. ```PUT /flowcharts/{id}:``` Update an existing flowchart

4. ```DELETE /flowcharts/{id}:``` Delete an existing flowchart

5. ```GET /flowcharts/{id}/validate:``` Validate the flowchart

6. ```GET /flowcharts/{id}/nodes/{nodeId}/outgoing:``` Fetch all outgoing nodes for a given node

7. ```GET /flowcharts/{id}/nodes/{nodeId}/connected:``` Query all nodes connected to a specific node (directly or indirectly)
