package com.leninkumar.cache.policies;

import java.util.HashMap;
import java.util.Map;

import com.leninkumar.ds.dll.DoublyLinkedList;
import com.leninkumar.ds.dll.ListNode;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {

	private final Map<K, ListNode<K>> nodeMap;
	private DoublyLinkedList<K> dll;

	public LRUEvictionPolicy() {
		this.nodeMap = new HashMap<>();
		this.dll = new DoublyLinkedList<>();
	}

	@Override
	public K evictKey() {
		if (this.dll.isEmpty()) {
			return null;
		}
		ListNode<K> frontNode = this.dll.getFirstNode();
		this.dll.deleteNode(frontNode);
		this.nodeMap.remove(frontNode.getElem());
		return frontNode.getElem();
	}

	@Override
	public void keyAccessed(K key) {
		ListNode<K> currNode;
		if (nodeMap.containsKey(key)) {
			currNode = nodeMap.get(key);
			this.dll.deleteNode(currNode);
			this.dll.addNodeAtEnd(currNode);
		} else {
			currNode = this.dll.addKeyAtEnd(key);
			this.nodeMap.put(key, currNode);
		}
	}

}
