package final_exam;

/*
 *  ���� �� ĭ �� ���� ����
 */
public class Room {
	private int left, right, up, down;

	public Room(int left,int right,int up,int down) {
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
	}

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;
	}

	public int getUp() {
		return up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}
	
	
}
