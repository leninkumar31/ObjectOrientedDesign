package com.leninkumar.ds.dll;

public class ListNode<E> {
	private final E elem;
	private ListNode<E> left;
	private ListNode<E> right;

	ListNode(E key) {
		this.elem = key;
	}

	public ListNode<E> getLeft() {
		return left;
	}

	public void setLeft(ListNode<E> left) {
		this.left = left;
	}

	public ListNode<E> getRight() {
		return right;
	}

	public void setRight(ListNode<E> right) {
		this.right = right;
	}

	public E getElem() {
		return elem;
	}

}
