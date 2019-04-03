package addons.java;

import addons.java.util.Board;

public class Test {

	public static void main(String[] args) {

		Board<String> board = new Board<>();

		board.put("Axel");

		board.contains("Coezard");

		board.get(0);

		board.isEmpty();

		board.isFull();

		board.size();

		board.toArray();

		board.poll();

		board.copy();

		board.clear();

	}

}
