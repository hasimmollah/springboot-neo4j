# springboot-neo4j
Springboot project with neo4j deployed in kubernetes using helm

### neo4j data model
- Employee -[:REPORTS_TO] -> Employee
- Employee -[:IS_PART_OF] -> Department
- Employee -[:WITH_DESIGNATION] -> Position

### Commands
- Prepare image
```` 
docker build -t springboot-neo4j:latest .
docker tag springboot-neo4j:latest hasim601/springboot-neo4j:latest
docker push hasim601/springboot-neo4j
````
- Deploy neo4j
```` 
helm install neo4j ./Kubernetes/resources/neo4j-chart -f ./Kubernetes/resources/neo4j-chart/values.yaml
````
- Deploy application
```` 
helm install app ./Kubernetes/resources/app-chart -f ./Kubernetes/resources/app-chart/values.yaml

