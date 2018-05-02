package generics;

public interface OrderedList<E> {
	OrderedList<E> add(E e);
	E get(int i);
	E remove(int i);
	int size();
}
