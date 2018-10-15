package com.jiuzhang.structures;

import java.util.Stack;

public class MyQueueWithStack {
    //first in last out
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    /*
    first in first out

     */
    public MyQueueWithStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(Integer i) {
        //stack1 is having the first in value
        //push -> last in stack 1
        //all in stack 1
        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(i);
        //stack 1 has all last is last -> stack 2
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }

    }

    public Integer pop() {
        return stack2.pop();
    }

    public Integer top() {
        return stack2.peek();
    }
}
