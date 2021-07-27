package com.leninkumar.multilevelcache.dll;


public class DoublyLinkedList<E> {
	ListNode<E> dummyHead;
	ListNode<E> dummyTail;

	public DoublyLinkedList() {
		dummyHead = new ListNode<>(null);
		dummyTail = new ListNode<>(null);
		dummyHead.setRight(dummyTail);
		dummyTail.setLeft(dummyHead);
	}

	public boolean isEmpty() {
		return dummyHead.getRight() == dummyTail;
	}

	public ListNode<E> getFirstNode() {
		return dummyHead.getRight();
	}

	public void deleteNode(ListNode<E> frontNode) {
		ListNode<E> prevNode = frontNode.getLeft();
		prevNode.setRight(frontNode.getRight());
		frontNode.getRight().setLeft(prevNode);
		frontNode.setRight(null);
		frontNode.setLeft(null);
	}

	public void addNodeAtEnd(ListNode<E> node) {
		dummyTail.getLeft().setRight(node);
		node.setLeft(dummyTail.getLeft());
		node.setRight(dummyTail);
		dummyTail.setLeft(node);
	}

	public ListNode<E> addKeyAtEnd(E elem) {
		ListNode<E> node = new ListNode<>(elem);
		addNodeAtEnd(node);
		return node;
	}
}
