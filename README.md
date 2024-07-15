# Users Aggregator
The aggregation service to gather users information via multiple databases.

## Features:
* API Code generation by a specification [users-api.yaml](users-aggregator%2Fsrc%2Fmain%2Fresources%2Fusers-api.yaml);
* Flexible data-sources configuration with [data-sources.yaml](users-aggregator%2Fsrc%2Fmain%2Fresources%2Fdata-sources.yaml);
* Dynamic columns mapping;

## Available API:
/GET users - looks for all users;\
/GET users?column=val - applies filtering before users retrieval.

## Requirements:
* Java 8+;
* Maven 3;
* Docker.

## Setup:
* pull the project;
* make sure your data sources are available;
* run mvn clean install;
* navigate to target directory with the command prompt and run java -jar users-aggregator-0.0.1-SNAPSHOT.jar.