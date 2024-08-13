package com.struct.graph;

import java.util.Arrays;
import java.util.List;

/**
 * 顶点
 */
public class Vertex {
  String name;
  List<Edge> edges;
  boolean visited;

  public Vertex(String name) {
    this(name, false);
  }

  public Vertex(String name, boolean visited) {
    this.name = name;
    this.visited = visited;
  }

  public String getName() {
    return name;
  }

  public static void main(String[] args) {
    Vertex a = new Vertex("A");
    Vertex b = new Vertex("B");
    Vertex c = new Vertex("C");
    Vertex d = new Vertex("D");
    Vertex e = new Vertex("E");

    a.edges = Arrays.asList(new Edge(b), new Edge(c));
    b.edges = Arrays.asList(new Edge(d));
    c.edges = Arrays.asList(new Edge(d));
    d.edges = Arrays.asList(new Edge(e));
    e.edges = Arrays.asList();

    //dfsUnR(a);
//    bfs(a, a.edges);
  }

//  private static void dfs(Vertex v){
//    v.visited = true;
//    System.out.println(v.name);
//    for (Edge edge : v.edges) {
//      if (!edge.linked.visited) {
//        dfs(edge.linked);
//      }
//    }
//  }
//
//  private static void dfsUnR(Vertex v){
//    LinkedList<Vertex> stack = new LinkedList<>();
//    stack.push(v);
//    while (!stack.isEmpty()){
//      Vertex pop = stack.pop();
//      pop.visited = true;
//      System.out.println(pop.name);
//      for (Edge edge : pop.edges) {
//        if(!edge.linked.visited){
//          stack.push(edge.linked);
//        }
//      }
//    }
//  }
//
//  private static void bfs(Vertex head, List<Edge> v){
//    if(!head.visited) {
//      System.out.println(head.name);
//    }
//    head.visited = true;
//    for (Edge edge : v) {
//      if (!edge.linked.visited) {
//        System.out.println(edge.linked.name);
//        edge.linked.visited = true;
//      }
//    }
//    for (Edge edge : v) {
//      bfs(edge.linked, edge.linked.edges);
//    }
//  }
//
//  private static void bfsUnR(Vertex v){
//    LinkedList<Vertex> queue = new LinkedList<>();
//    queue.offer(v);
//    v.visited = true;
//    while (!queue.isEmpty()){
//      Vertex poll = queue.poll();
//      System.out.println(poll.name);
//      for (Edge edge : poll.edges) {
//        if (!edge.linked.visited) {
//          edge.linked.visited = true;
//          queue.offer(edge.linked);
//        }
//      }
//    }
//  }


}
