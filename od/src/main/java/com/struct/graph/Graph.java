package com.struct.graph;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
  List<Vertex> graphList = new ArrayList<>();

  //顶点
  static class Vertex {
    String name;
    List<Edge> edges;
    int inDegree; //入度
    int status; // 0 未访问 1 访问中 2 访问过
    int dist = INF; //距离
    static final Integer INF = Integer.MAX_VALUE;
    Vertex prev = null;
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

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Vertex vertex = (Vertex) o;

      return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
      return name != null ? name.hashCode() : 0;
    }
  }

  //边
  class Edge implements Comparable<Edge>{
    List<Vertex> vertices;
    int start;
    int end;

    Vertex linked;
    int weight;
    public Edge(Vertex linked) {
      this(linked , 1);
    }
    public Edge(Vertex linked, int weight) {
      this.linked = linked;
      this.weight = weight;
    }

    public Edge(List<Vertex> vertices, int start, int end, int weight){
      this.vertices = vertices;
      this.start = start;
      this.end = end;
      this.weight = weight;
    }

    public Edge(int start, int end, int weight){
      this.start = start;
      this.end = end;
      this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
      return Integer.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
      return "Edge{" +
        "linked=" + linked +
        ", weight=" + weight +
        '}';
    }
  }

  //递归深搜
  private static void dfs(Vertex v){
    v.visited = true;
    System.out.println(v.name);
    for (Edge edge : v.edges) {
      if (!edge.linked.visited) {
        dfs(edge.linked);
      }
    }
  }

  //非递归深搜
  private static void dfsUnR(Vertex v){
    LinkedList<Vertex> stack = new LinkedList<>();
    stack.push(v);
    while (!stack.isEmpty()){
      Vertex pop = stack.pop();
      pop.visited = true;
      System.out.println(pop.name);
      for (Edge edge : pop.edges) {
        if(!edge.linked.visited){
          stack.push(edge.linked);
        }
      }
    }
  }

  //递归广搜
  private static void bfs(Vertex head, List<Edge> v){
    if(!head.visited) {
      System.out.println(head.name);
    }
    head.visited = true;
    for (Edge edge : v) {
      if (!edge.linked.visited) {
        System.out.println(edge.linked.name);
        edge.linked.visited = true;
      }
    }
    for (Edge edge : v) {
      bfs(edge.linked, edge.linked.edges);
    }
  }

  //非递归广搜
  private static void bfsUnR(Vertex v){
    LinkedList<Vertex> queue = new LinkedList<>();
    queue.offer(v);
    v.visited = true;
    while (!queue.isEmpty()){
      Vertex poll = queue.poll();
      System.out.println(poll.name);
      for (Edge edge : poll.edges) {
        if (!edge.linked.visited) {
          edge.linked.visited = true;
          queue.offer(edge.linked);
        }
      }
    }
  }

  //拓扑排序
  public void findDegree(){
    for (Vertex vertex : this.graphList) {
      for (Edge edge : vertex.edges) {
        edge.linked.inDegree++;
      }
    }
    LinkedList<Vertex> queue = new LinkedList<>();
    for (Vertex vertex : graphList) {
      if(vertex.inDegree == 0){
        queue.offer(vertex);
      }
    }
    while (!queue.isEmpty()){
      Vertex poll = queue.poll();
      System.out.println(poll.name);
      for (Edge edge : poll.edges) {
        edge.linked.inDegree--;
        if(edge.linked.inDegree == 0){
          queue.offer(edge.linked);
        }
      }
    }
  }

  public void findDegreeForDfs(){
    LinkedList<String> stack = new LinkedList<>();
    for (Vertex vertex : graphList) {
      dfs(vertex, stack);
    }
  }

  private void dfs(Vertex vertex, LinkedList<String> stack) {
    if(vertex.status == 2){
      return;
    }
    if(vertex.status == 1){
      throw new RuntimeException("发现环");
    }
    vertex.status = 1;
    for (Edge edge : vertex.edges) {
      dfs(edge.linked, stack);
    }
    vertex.status = 2;
    stack.push(vertex.name);
  }

  public void Dijkstra(List<Vertex> graphList, Vertex v) {
    List<Vertex> list = new ArrayList<>(graphList);
    v.dist = 0;
    while (!list.isEmpty()){
      Vertex cur = chooseMinDistVertex(list);
      updateNeighbourDist(cur, list);
      list.remove(cur);
      cur.visited = true;
    }
    for (Vertex vertex : graphList) {
      System.out.println(vertex.name);
    }
  }

  private void updateNeighbourDist(Vertex cur, List<Vertex> list) {
    for (Edge edge : cur.edges) {
      Vertex n = edge.linked;
//      if (list.contains(n)) {
//        int dist = cur.dist + edge.weight;
//        if (dist < n.dist) {
//          n.dist = dist;
//          n.prev = cur;
//        }
//      }
      if (!n.visited) {
        int dist = cur.dist + edge.weight;
        if (dist < n.dist) {
          n.dist = dist;
          n.prev = cur;
        }
      }
    }
  }

  private Vertex chooseMinDistVertex(List<Vertex> list) {
    Vertex min = list.get(0);
    for (int i = 1; i < list.size(); i++) {
      if(list.get(i).dist < min.dist){
        min = list.get(i);
      }
    }
    return min;
  }

  private static void bellmanFord(List<Vertex> graph, Vertex v){
    v.dist = 0;
    int size = graph.size();
    for (int i = 0; i < size - 1; i++) {
      for (Vertex s : graph) {
        for (Edge edge : s.edges) {
          Vertex e = edge.linked;
          if (s.dist != Integer.MAX_VALUE && s.dist + edge.weight < e.dist) {
            e.dist = e.dist + edge.weight;
          }
        }
      }
    }
    for (Vertex vt : graph) {
      System.out.println(vt);
    }

  }

  static void floydWarshall(List<Vertex> graph){
    int size = graph.size();
    int[][] dist = new int[size][size];
    for (int i = 0; i < size; i++) {
      Vertex v = graph.get(i);
      Map<Vertex, Integer> map = v.edges.stream().collect(Collectors.toMap(e -> e.linked, e -> e.weight));
      for (int j = 0; j < size; j++) {
        Vertex u = graph.get(j);
        if(v == u){
          dist[i][j] = 0;
        } else {
          dist[i][j] = map.getOrDefault(u, Integer.MAX_VALUE);
        }
      }
    }
    print(dist);
  }

  static void print(int[][] dist){
    System.out.println("------------");
    for (int[] row : dist) {
      System.out.println(Arrays.stream(row).boxed()
        .map(x -> x == Integer.MAX_VALUE ? "∞" : String.valueOf(x))
        .map(s -> String.format("%2s", s))
        .collect(Collectors.joining(",", "[", "]")));
    }
  }

  /**
   * 最小生成树prim算法 类似上面Dijkstra找各点之间最短距离算法
   */
  public void prim(List<Vertex> graphList, Vertex v){
    List<Vertex> list = new ArrayList<>(graphList);
    v.dist = 0;
    while (!list.isEmpty()){
      Vertex cur = chooseMinDistVertex(list);
      updateNeighbourDistPrim(cur);
      list.remove(cur);
      cur.visited = true;
    }
    for (Vertex vertex : graphList) {
      System.out.println(vertex.name);
    }
  }

  private void updateNeighbourDistPrim(Vertex cur) {
    for (Edge edge : cur.edges) {
      Vertex n = edge.linked;
      if (!n.visited) {
        int dist = edge.weight;
        if (dist < n.dist) {
          n.dist = dist;
          n.prev = cur;
        }
      }
    }
  }

  static void kruskal(int size, PriorityQueue<Edge> queue){
    List<Edge> list = new ArrayList<>();
    while (list.size() < size -1){
      Edge poll = queue.poll();

    }
  }



}
