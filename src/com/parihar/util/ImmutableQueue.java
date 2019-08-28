package com.parihar.util;

public class ImmutableQueue<T> implements Queue<T> {

	class QNode {

		private T data;

		private QNode next = null;

		QNode(T data, QNode next) {
			this.data = data;
			this.next = next;
		}

		QNode(T data) {
			this(data, null);
		}

	}

	private QNode front = null;
	private QNode rear = null;

	public ImmutableQueue() {
		// empty constructor
	}

	ImmutableQueue(ImmutableQueue<T> other) {
		addAll(other);
	}

	/**
	 * Adding all elements to Linked List.
	 * 
	 * @param other
	 * @return
	 */
	private Queue<T> addAll(ImmutableQueue<T> other) {
		QNode next = other.front;
		while (next != null) {
			add(next.data);
			next = next.next;
		}
		return this;
	}

	static <T> ImmutableQueue<T> copy(final ImmutableQueue<T> other) {
		return new ImmutableQueue<T>(other);
	}

	private void add(T data) {
		if (this.front == null) {
			this.front = new QNode(data);
			return;
		}
		QNode next = front, prev = null;
		while (next != null) {
			prev = next;
			next = prev.next;
		}
		prev.next = new QNode(data);
	}

	private void removeHead() {
		if (this.front == null)
			return;
		this.front = front.next;
	}

	@Override
	public Queue<T> enQueue(T t) {
		final ImmutableQueue<T> copy = copy(this);
		copy.add(t);
		return copy;
	}

	@Override
	public Queue<T> deQueue() {
		final ImmutableQueue<T> copy = copy(this);
		copy.removeHead();
		return copy;
	}

	@Override
	public T head() {
		return front == null ? null : front.data;
	}

	@Override
	public boolean isEmpty() {
		return front == null;
	}

}
