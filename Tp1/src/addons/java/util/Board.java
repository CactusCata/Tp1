package addons.java.util;

public class Board<E> {

	private int size = 0;
	private int length;
	private Object[] items;

	public Board(Board<E> array) {
		this.items = array.toArray();
		length = array.length;
		size = array.size;
	}

	public Board() {
		length = 1;
		items = new Object[length];
	}

	private void enlarge() {
		if (size < length)
			return;
		Object[] items = this.items;
		length = size + 1;
		this.items = new Object[length];

		int index = 0;
		for (Object e : items) {
			if (index > length || e == null)
				continue;
			this.items[index] = e;
			index++;
		}
	}

	public Board<E> put(E item) {
		items[size] = item;
		size++;
		this.enlarge();
		return this;
	}

	public Board<E> remove(int index) {
		items[index] = null;
		size--;
		return this;
	}

	@SuppressWarnings("unchecked")
	public E poll() {
		E e = (E) items[0];
		this.remove(0);
		return e;
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		if (index > size || index < 0)
			return (E) null;
		return (E) items[index];
	}

	@SuppressWarnings("unchecked")
	public boolean contains(E e) {
		for (Object o : items) {
			if ((E) o == e)
				return true;
		}
		return false;
	}

	public Board<E> clear() {
		length = 1;
		size = 0;
		items = new Object[length];
		return this;
	}

	public boolean isFull() {
		return items.length + 1 > length;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return size;
	}

	public Object[] toArray() {
		return items;
	}

	public Board<E> copy() {
		return new Board<E>(this);
	}

}