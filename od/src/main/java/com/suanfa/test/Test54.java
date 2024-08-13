package com.suanfa.test;

import java.util.*;
//开源项目热榜
public class Test54 {


  public static void main(String[] args) {
    //输入描述
    //第一行输入为 N，表示开源项目的个数，0 < N < 100。
    //
    //第二行输入为权重值列表，一共 5 个整型值，分别对应关注、收藏、fork、issue、MR 的权重，权重取值 0 < M ≤ 50。
    //
    //第三行开始接下来的 N 行为开源项目的统计维度，每一行的格式为：
    //
    //name nr_watch nr_star nr_fork nr_issue nr_mr
    //其中 name 为开源项目的名字，由英文字母组成，长度 ≤50，其余 5 个整型值分别为该开源项目关注、收藏、fork、issue、MR 的数量，数量取值 0 < nr ≤ 1000。
    //4
    //8 6 2 8 6
    //camila 66 70 46 158 80
    //victoria 94 76 86 189 211
    //anthony 29 17 83 21 48
    //emily 53 97 1 19 218
    Scanner in = new Scanner(System.in);
    int count = in.nextInt();
    in.nextLine();
    int[] subs = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    String[] strs = new String[count];
    for (int i = 0; i < count; i++) {
      strs[i] = in.nextLine();
    }
    System.out.println(count);
    System.out.println(Arrays.toString(subs));
    System.out.println(Arrays.toString(strs));

    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < strs.length; i++) {
      String[] split = strs[i].split(" ");
      String name = split[0];
      int sum = 0;
      for (int j = 1; j < split.length - 1; j++) {
        sum += subs[j - 1] * Integer.parseInt(split[j]);
      }
      map.put(name, sum);
    }

    List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet()); // 转换为list
    list.sort(new Comparator<Map.Entry<String, Integer>>() {
      @Override
      public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return o2.getValue() - o1.getValue();
      }
    });
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
    }
    System.out.println(map);

  }


}
