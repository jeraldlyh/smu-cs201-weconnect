# WeConnect

Algorithmic friend finder application that returns the list of suggested friends upon adding another user as friend via Breadth-first search (BFS)

## Data Structures
This application compares the differences between using two different data structures, namely:
- Adjacency Matrix
- Adjacency List

References can be found [here](https://www.baeldung.com/java-graphs)

## Metrics for Comparison
Comparison between both data structures will be shown via the dashboard:
1. Time taken to create the graph with each data structure
2. Time taken to retrieve list of suggested friends with each data structure

## Visiualization
Visualization of data that are stored in [testing.json](backend/src/main/resources/testing.json) can be viewed [here](https://whimsical.com/cs201-VP8nGTbSL8uzL7MQJrgjGr)

**Example**

When Apple adds Grape as a friend on the dashboard, BFS will traverse from:

_Apple -> Pear -> Pineapple -> Orange -> Grape_

which returns us a degree of relationship of 3 as it requires a traversal of 3 users before reaching Grape. Subsequently, it'll return a list of friends that are connected to Grape which in this case will be Orange.
