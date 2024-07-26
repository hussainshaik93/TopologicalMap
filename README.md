# DAG Longest Path Problem

## Overview

This project implements a solution to calculate the longest directed path in a Directed Acyclic Graph (DAG) starting from a given vertex. The solution utilizes topological sorting and dynamic programming to determine the longest path efficiently.

## Problem Statement

Given a Directed Acyclic Graph (DAG) and a starting vertex, calculate the longest directed path from that vertex. The longest path is the maximum number of edges in any path that starts from the given vertex.

## Implementation

The project includes the following classes:

- **Vertex**: Represents a vertex in the graph with an `id`.
- **Edge**: Represents a directed edge from one vertex to another.
- **DAG**: Represents the graph and includes methods for adding vertices and edges, performing topological sorting, and calculating the longest path.
- **Main**: Contains the `main` method with test cases to verify the correctness of the implementation.

### Vertex Class

The `Vertex` class represents a graph vertex. It includes an `id` and overrides `toString()`, `equals()`, and `hashCode()` methods for proper functionality in collections.

### Edge Class

The `Edge` class represents a directed edge between two vertices (`from` and `to`).

### DAG Class

The `DAG` class provides methods to:
- Add vertices and edges.
- Perform topological sorting using Kahn's algorithm.
- Calculate the longest path from a given vertex using dynamic programming.

#### Key Methods

- **`addVertex(Vertex vertex)`**: Adds a vertex to the graph.
- **`addEdge(Vertex from, Vertex to)`**: Adds a directed edge between two vertices.
- **`topologicalSort()`**: Performs topological sorting of the graph nodes.
- **`getLongestPath(Vertex start)`**: Calculates the longest path from the specified start vertex.

### Main Class

The `Main` class demonstrates the usage of the `DAG` class and includes test cases to validate the implementation. It creates a sample graph, adds vertices and edges, and prints the longest path from various starting vertices.

## Running the Code

1. **Compile**: 
   ```bash
   javac Main.java

Run  
java Main
