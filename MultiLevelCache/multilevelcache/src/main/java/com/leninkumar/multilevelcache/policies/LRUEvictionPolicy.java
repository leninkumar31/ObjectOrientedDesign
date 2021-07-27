package com.leninkumar.multilevelcache.policies;

import java.util.HashMap;
import java.util.Map;

import com.leninkumar.multilevelcache.dll.DoublyLinkedList;
import com.leninkumar.multilevelcache.dll.ListNode;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {

	private final Map<K, ListNode<K>> nodeMap;
	private final DoublyLinkedList<K> dll;

	public LRUEvictionPolicy() {
		this.nodeMap = new HashMap<>();
		this.dll = new DoublyLinkedList<>();
	}

	@Override
	public void keyAccessed(K key) {
		ListNode<K> currNode;
		if (nodeMap.containsKey(key)) {
			currNode = nodeMap.get(key);
			this.dll.deleteNode(currNode);
			this.dll.addNodeAtEnd(currNode);
		} else {
			this.dll.addKeyAtEnd(key);
		}
	}

	@Override
	public K evictKey() {
		if (this.dll.isEmpty()) {
			return null;
		}
		ListNode<K> evictedNode = this.dll.getFirstNode();
		this.dll.deleteNode(evictedNode);
		this.nodeMap.remove(evictedNode.getElem());
		return evictedNode.getElem();
	}

}
