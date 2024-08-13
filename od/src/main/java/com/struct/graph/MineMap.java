package com.struct.graph;

public class MineMap {
  private String[] Vertex;// 存储顶点名字

  private int[][] Edge;// 存储边信息

  private int numOfVertex;// 顶点个数

  private int maxOfVertex;// 最大顶点个数

  private int MAX = Integer.MAX_VALUE / 2;//无穷大表示值

  private static volatile MineMap mineMap;

  private MineMap(int maxOfVertex) {
    numOfVertex = 0;
    this.maxOfVertex = maxOfVertex;
    Vertex = new String[maxOfVertex];
    Edge = new int[maxOfVertex][maxOfVertex];
    init(maxOfVertex);
  }

  private static MineMap getInstance(int maxOfVertex) {
    if(mineMap == null){
      synchronized (MineMap.class){
        if(mineMap == null){
          mineMap = new MineMap(maxOfVertex);
        }
      }
    }
    return mineMap;
  }

  public void init(int maxOfVertex) {
    maxOfVertex = this.maxOfVertex;
    for (int i = 0; i < maxOfVertex; i++) {
      for (int j = 0; j < maxOfVertex; j++) {
        if(i != j){
          Edge[i][j] = MAX;
        } else {
          Edge[i][j] = 0;
        }
      }
    }
  }

  public boolean insertVertex(String name){
    if(numOfVertex >= maxOfVertex){
      return false;
    }
    Vertex[numOfVertex++] = name;
    return true;
  }

  public int findByName(String name){
    for (int i = 0; i < numOfVertex; i++) {
      if(Vertex[i].equals(name)){
        return i;
      }
    }
    return -1;
  }

  public boolean insertEdge(String begin, String end, int weight){
    int v1 = findByName(begin);
    int v2 = findByName(end);

    if(v1 < 0 || v2 < 0){
      return false;
    }

    Edge[v1][v2] = weight;
    Edge[v2][v1] = weight;

    return true;
  }

  public boolean deleteVertex(String name){
    int v = findByName(name);

    if(v < 0){
      return false;
    }

    Vertex[v] = Vertex[numOfVertex - 1];

    for (int i = 0; i < numOfVertex; i++) {
      Edge[v][i] = Edge[numOfVertex - 1][i];
      Edge[i][v] = Edge[i][numOfVertex - 1];
      Edge[numOfVertex - 1][i] = MAX;
      Edge[i][numOfVertex - 1] = MAX;
    }

    Vertex[--numOfVertex] = null;

    return true;
  }

  public boolean deleteEdge(String begin, String end){
    int v1 = findByName(begin);
    int v2 = findByName(end);

    if(v1 < 0 || v2 < 0){
      return false;
    }

    Edge[v1][v2] = MAX;
    Edge[v2][v1] = MAX;

    return true;
  }



}
