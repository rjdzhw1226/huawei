package com.struct.Queue;

import java.util.Comparator;
import java.util.PriorityQueue;

class Entry {
  String value;
  int priority;
  long sequenceNumber;

  public Entry(String value, int priority, long sequenceNumber) {
    this.value = value;
    this.priority = priority;
    this.sequenceNumber = sequenceNumber;
  }

}

class PriorityComparator implements Comparator<Entry> {
  public int compare(Entry e1, Entry e2) {
    if (e1.priority != e2.priority) return Integer.compare(e2.priority, e1.priority);
    return Long.compare(e1.sequenceNumber, e2.sequenceNumber);
  }
}

public class Main {
  static PriorityQueue<Entry> queue = new PriorityQueue<>(new PriorityComparator());
  static long seqNumber = 0;

  static void push(String value, int priority) {
    queue.offer(new Entry(value, priority, seqNumber++));
  }

  static Entry get() {
    return queue.poll();
  }

  public static void main(String[] args) {
    push("a", 1);
    push("d", 3);
    push("e", 1);
    push("f", 2);
    while (!queue.isEmpty()) {
      Entry now = get();
      System.out.println(now.value + "," + now.priority);
    }

  }

}
