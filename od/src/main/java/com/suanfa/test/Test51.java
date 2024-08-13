package com.suanfa.test;

import java.util.*;
//分配土地
public class Test51 {
  //题目描述
  //从前有个村庄，村民们喜欢在各种田地上插上小旗子，旗子上标识了各种不同的数字。某天集体村民决定将
  // 覆盖相同数字
  // 的
  // 最小矩阵形的土地
  // 分配给村里做出巨大贡献的村民，
  // 请问此次分配土地，做出贡献的村民种最大会分配多大面积?
  //输入描述
  //第一行输入 m 和 n,
  //m 代表村子的土地的长
  //n 代表土地的宽
  //第二行开始输入地图上的具体标识
  //输出描述
  //此次分配士地，做出贡献的村民种最大会分配多大面积
  //备注
  //旗子上的数字为1~500，土地边长不超过500
  //未插旗子的土地用0标识
  // 1 0 1
  // 0 0 0
  // 0 1 0
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int row = in.nextInt();
    int col = in.nextInt();
    int[][] nums = new int[row][col];
    Hashtable<Integer, List<Integer>> hash = new Hashtable<>();
    Hashtable<Integer, List<Integer>> hashMin = new Hashtable<>();
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        int n = in.nextInt();
        if(n != 0) {
          if(hash.get(n) == null) {
            hash.put(n, new ArrayList<>(Arrays.asList(i, j)));
          } else {
            if(hash.get(n).size() > 0){
              if (hash.get(n).get(0) < i) {
                hash.put(n, new ArrayList<>(Arrays.asList(i, hash.get(n).get(1))));
              }
              if (hash.get(n).get(1) < j) {
                hash.put(n, new ArrayList<>(Arrays.asList(hash.get(n).get(0), j)));
              }
            } else {
              throw new RuntimeException("哈希表元素异常");
            }
          }
          if (hashMin.get(n) == null) {
            hashMin.put(n, new ArrayList<>(Arrays.asList(i, j)));
          } else {
            if(hashMin.get(n).size() > 0){
              if (hashMin.get(n).get(0) >= i) {
                hashMin.put(n, new ArrayList<>(Arrays.asList(i, hashMin.get(n).get(1))));
              }
              if (hashMin.get(n).get(1) >= j) {
                hashMin.put(n, new ArrayList<>(Arrays.asList(hashMin.get(n).get(0), j)));
              }
            } else {
              throw new RuntimeException("哈希表元素异常");
            }
          }
        }
        nums[i][j] = n;
      }
    }
    System.out.println(hash);
    System.out.println(hashMin);
    List<Integer> ans = new ArrayList<>();
    if (hash.size() > 0 && hashMin.size() > 0) {
      for (Integer integer : hash.keySet()) {
        int len = hash.get(integer).get(0) - hashMin.get(integer).get(0) == 0 ? 1 : hash.get(integer).get(0) + 1 - hashMin.get(integer).get(0);
        int cub = hash.get(integer).get(1) - hashMin.get(integer).get(1) == 0 ? 1 : hash.get(integer).get(1) + 1 - hashMin.get(integer).get(1);
        ans.add(len * cub);
      }
    } else {
      System.out.println(0);
    }
    System.out.println(ans);
  }

  private static void print(int[][] nums) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums[i].length; j++) {
        System.out.printf("%d, ", nums[i][j]);
      }
      System.out.println();
    }
  }

}
