package com.kimi.queen;

import com.kimi.domain.Hero;

import java.util.Iterator;

public class CircleArrayQueen<T> implements Iterable<T> {

    private int front;
    private int rear;
    private int maxSize;
    private T[] arr;

    public CircleArrayQueen(int maxSize) {
        this.maxSize = maxSize + 1;
        arr = (T[]) new Object[maxSize + 1];
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }


    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }


    public boolean add(T t) {
        if (isFull()) {
            throw new RuntimeException("队列满了,操作失败");
        }
        arr[rear] = t;
        rear = (rear + 1) % maxSize;
        return true;
    }

    /**
     * 队首元素出队
     *
     * @return
     */
    public T poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空了");
        }
        T t = arr[front];
        front = (front + 1) % maxSize;
        return t;
    }


    /**
     * 看一下队首元素
     *
     * @return
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return arr[front];
    }


    public void show() {
        int size = size();
        System.out.println("内容:");
        for (int i = front; i < front + size; i++) {
            System.out.print(arr[i] + "\t");
        }
    }

    public static void main(String[] args) {
        CircleArrayQueen<Hero> queen = new CircleArrayQueen<>(3);
        queen.add(new Hero( "张三", 15));
        queen.add(new Hero( "李四", 16));
        queen.add(new Hero( "王五", 18));
        queen.poll();
        queen.poll();
        queen.add(new Hero( "1五", 18));
        for (Hero hero : queen) {
            System.out.println(hero);
        }
        System.out.println("-----------");
        queen.add(new Hero( "周六", 12));
        for (Hero hero : queen) {
            System.out.println(hero);
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int front0=front;
            private int rear0=rear;

            @Override
            public boolean hasNext() {
                return front0 != rear0;
            }

            @Override
            public T next() {
                T t = arr[front0];
                front0 = (front0 + 1) % maxSize;
                return t;
            }
        };
    }
}
