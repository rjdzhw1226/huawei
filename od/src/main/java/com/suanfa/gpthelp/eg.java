package com.suanfa.gpthelp;

//导入不用的类和包 建议删除
import java.io.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

public class eg {
  //静态变量会和类的生命周期一致 难被回收 能不用就不用
  public static String s = new String("static");

  //常量使用static final修饰 大写命名 编译阶段就会进入常量池中
  public static final String CONSTANT = "big";

  public void method() {
    //for循环写法 集合使用尽量用数组 未知大小或动态扩容再使用集合
    int[] list = new int[]{1,2,3};
    for (int i = 0, len = list.length; i < len ; i++) {

    }

    //使用懒加载原则 需要时再创建
    if(true){
      String str = "a";
      System.out.println(str);
    }

    //异常处理 别放循环里头
    try{
      for (int i = 0; i < 10; i++) {

      }
    }catch (Exception e){
      e.printStackTrace();
    }

    //乘除法使用移位操作替换
    int a = 4 >> 1;
    int b = 8 << 1;

    //使用集合和工具类 最好指定初始长度 扩容拷贝比较耗时
    StringBuilder sb = new StringBuilder(16);

    //不要在循环内不断创建对象引用 外层创建 减小内存使用
    Object obj = null;
    for (int i = 0, len = list.length; i < len; i++) {
      obj = new Object();
    }

    //RandomAccess 提供更好的随机访问性能 对于顺序访问使用迭代器效率更高
    List<Integer> lists = new ArrayList<>();

    if (lists instanceof RandomAccess) {
      for (int i = 0; i < lists.size(); i++) {
      }
    } else {
      Iterator<?> iterator = lists.iterator();
      while (iterator.hasNext()) {
        iterator.next();
      }
    }

    //判断避免空指针异常 基础数据判断也建议这么写
    if("123".equals(s)){

    }
    int i = 7;
    if(1 == i){

    }
    //io流分开关闭 并保证关闭
    File fi = new File("");
    FileInputStream fn = null;
    FileOutputStream fo = null;

    try{
      fo = new FileOutputStream(fi);
    } catch (Exception e){
      e.printStackTrace();
      try {
        fo.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    } finally {
      try {
        fo.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }

    try {
      fn = new FileInputStream(fi);
      int read = fn.read();
    } catch (Exception e) {
      e.printStackTrace();
      try {
        fn.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    } finally {
      try {
        fn.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    //TODO 补充

  }
}
