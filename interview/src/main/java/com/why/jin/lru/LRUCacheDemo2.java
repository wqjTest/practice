package com.why.jin.lru;

/**
 * @author Jin
 * dateTime 2021-07-13-14:46
 */
public class LRUCacheDemo2 {

    //map负责查找，构建一个虚拟的双向链表，它里面安装的就是一个个Node节点，作为数据载体。
    //1.构建一个Node节点，作为数据载体
    class Node<K,V>{
        K key;
        V value;
        Node<K,V> prev;
        Node<K,V> next;

        public Node(Node<K, V> prev, Node<K, V> next) {
            this.prev =  this.next = null;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev =  this.next = null;
        }
    }

     //2.构建一个虚拟的双向链表，里面安放的就是我们的Node
     class DoubleLinkedList<K,V>{
        Node<K,V> head;
        Node<K,V> tail;

         public DoubleLinkedList() {
             head = new Node<>();
             tail = new Node<>();
             head.next = tail;
             tail.prev = head;
         }

         //2.1 添加到头
         public void addHead(Node<K,V> node){
             node.next = head.next;
             node.prev = head;
             head.next.prev = node;
             head.next = node;
         }
         //2.3 删除节点
         public void removeNode(Node<K,V> node){
             node.next.prev = node.prev;
             node.prev.next = node.next;
             node.prev = null;
             node.next = null;
         }
         //2.4 获的最后一个节点
         public Node getLast(){
             return tail.prev;
         }
     }
}
