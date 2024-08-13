package com.struct.graph;

/**
 * 边
 */
public class Edge {
  Vertex linked;
  int weight;

  public Edge(Vertex linked) {
    this(linked , 1);
  }

  public Edge(Vertex linked, int weight) {
    this.linked = linked;
    this.weight = weight;
  }
}
