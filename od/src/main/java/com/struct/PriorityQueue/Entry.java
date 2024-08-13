package com.struct.PriorityQueue;

/**
 * 优先级队列构造
 */
public class Entry implements Priority{
  String value;
  int priority;

  public Entry(String value, int priority) {
    this.value = value;
    this.priority = priority;
  }

  @Override
  public int priority() {
    return priority;
  }

  @Override
  public String toString() {
    return "Entry{" +
      "value='" + value + '\'' +
      ", priority=" + priority +
      '}';
  }
}
