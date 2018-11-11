package com.example.guojialin.worker.ja;

import java.util.Stack;

public class StackQueue {

    //用两个栈实现一个队列，完成队列的Push和Pop操作
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
