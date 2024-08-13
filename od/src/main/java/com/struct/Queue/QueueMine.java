package com.struct.Queue;

/**
 * <h3>我的队列</h3>
 * <hr style="width: 200px;text-align: left;">
 * <table BORDER CELLPADDING=3 CELLSPACING=1>
 *   <caption>队列方法总结</caption>
 *   <tr>
 *      <td ALIGN=LEFT><p>方法名</p></td>
 *      <td ALIGN=CENTER><p>集合常用方法</p></td>
 *      <td ALIGN=CENTER><p>队列方法</p></td>
 *  </tr>
 *  <tr>
 *    <td><b>Insert</b></td>
 *    <td><p>{@link java.util.List#add add(e)}</p></td>
 *    <td><p>{@link QueueMine#offer(Object)}</p></td>
 *  </tr>
 *  <tr>
 *   <td><b>Remove</b></td>
 *   <td><p>{@link java.util.List#remove remove(e)}</p></td>
 *   <td><p>{@link QueueMine#poll()}</p></td>
 *  </tr>
 *  <tr>
 *   <td><b>Examine</b></td>
 *   <td><p>{@link java.util.Collection#add element()}</p></td>
 *   <td><p>{@link QueueMine#peek()}</p></td>
 *  </tr>
 * </table>
 * <hr style="width: 200px;text-align: left;">
 * @author RJD
 * @param <E> 本类中参数形式
 */
public interface QueueMine<E> {
  /**
   * 队列尾插入值
   * @param value 待插入值
   * @return
   */
  boolean offer(E value);

  /**
   * 移除队列头的值
   * @return
   */
  E poll();

  /**
   * 获取队列头的值
   * @return
   */
  E peek();

  /**
   * 判空
   * @return
   */
  boolean isEmpty();

  /**
   * 判满
   * @return
   */
  boolean isFull();
}
