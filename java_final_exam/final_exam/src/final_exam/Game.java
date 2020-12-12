package final_exam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.*;

interface Calculate {
	int setXY(int X, int Y);
}

public class Game extends JFrame implements GameComponent {
	private Map map; // ��
	private Human thief; // ����
	private Human police; // ����

	private List<JPanel> panelList; // ĭ ����Ʈ
	private Room[][] map1; // ����, ���� ���� ����

	private int thiefX, thiefY;
	private int policeX, policeY;

	public Game() {
		setTitle("������ ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		init();

		map.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (map1[thiefX][thiefY].getLeft() == 0) {
						System.out.println("����");
						panelList.get(thiefX * 9 + thiefY).remove(thief);

						panelList.get(thiefX * 9 + thiefY).repaint();

						thiefY -= 1;
						panelList.get(thiefX * 9 + thiefY).add(thief);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (map1[thiefX][thiefY].getRight() == 0) {
						System.out.println("������");
						panelList.get(thiefX * 9 + thiefY).remove(thief);

						panelList.get(thiefX * 9 + thiefY).repaint();

						thiefY += 1;
						panelList.get(thiefX * 9 + thiefY).add(thief);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					if (map1[thiefX][thiefY].getUp() == 0) {
						System.out.println("��");
						panelList.get(thiefX * 9 + thiefY).remove(thief);

						panelList.get(thiefX * 9 + thiefY).repaint();

						thiefX -= 1;
						panelList.get(thiefX * 9 + thiefY).add(thief);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (map1[thiefX][thiefY].getDown() == 0) {
						System.out.println("�Ʒ�");
						panelList.get(thiefX * 9 + thiefY).remove(thief);

						panelList.get(thiefX * 9 + thiefY).repaint();

						thiefX += 1;
						panelList.get(thiefX * 9 + thiefY).add(thief);
					}
				}
				movePoliceUI();
				getThief();
				arriveDetination();
			}
		});

		this.add(map, BorderLayout.CENTER);

		setBounds(600, 150, 700, 700); // ������ ��ġ, ũ�� ����
		setResizable(false); // â ũ�� ��ȯX
		setVisible(true);
		map.setFocusable(true); // map Panel�� Ű���� �̺�Ʈ Ȱ��ȭ
	}

	public void init() {
		// ��ü �ʱ�ȭ
		map = new Map();
		thief = new Human();
		police = new Human();

		panelList = map.getPanelList(); // �� ĭ ��ü ����Ʈ ���
		// ������ ���� ��� ���� ��ü ����Ʈ
		map1 = map.getLevel1();

		thief.setColor(new Color(255, 0, 0));
		police.setColor(new Color(0, 0, 255));

		// ���� �ʱ� ��ġ
		thiefX = 1;
		thiefY = 2;

		// ���� �ʱ� ��ġ
		policeX = 0;
		policeY = 1;

//		// ���� �ʱ� ��ġ
//		thiefX = 0;
//		thiefY = 1;
//
//		// ���� �ʱ� ��ġ
//		policeX = 4;
//		policeY = 3;

		// ����, ���� UI �߰�
		System.out.println(panelList.get(thiefX * 9 + thiefY).getClass());
		panelList.get(thiefX * 9 + thiefY).add(thief);
		panelList.get(policeX * 9 + policeY).add(police);

	}

	@Override
	public void arriveDetination() {
		if (thiefX == 0 && thiefY == 0) {
			JOptionPane.showMessageDialog(null, "Ż��~");
			initPosition();
		}
	}

	// ������ ���� ���� ��.
	@Override
	public void getThief() {
		if ((thiefX == policeX) && (thiefY == policeY)) {
			JOptionPane.showMessageDialog(null, "��Ҵ�!!����!!");
			initPosition();
		}
	}

	// ����, ���� ��ġ �ʱ�ȭ
	@Override
	public void initPosition() {
		// ����, ���� UI �߰�
		panelList.get(thiefX * 9 + thiefY).remove(thief);
		panelList.get(thiefX * 9 + thiefY).repaint();
		panelList.get(policeX * 9 + policeY).remove(police);
		panelList.get(policeX * 9 + policeY).repaint();

		// ���� �ʱ� ��ġ
		thiefX = 1;
		thiefY = 2;

		// ���� �ʱ� ��ġ
		policeX = 0;
		policeY = 1;

		panelList.get(thiefX * 9 + thiefY).add(thief);
		panelList.get(policeX * 9 + policeY).add(police);

	}

	// ���� UI ��ġ ����
	public void movePoliceUI() {
		panelList.get(policeX * 9 + policeY).remove(police);
		panelList.get(policeX * 9 + policeY).repaint();
		movePolice();
		movePolice();
		panelList.get(policeX * 9 + policeY).add(police);
	}

	// ���� X,Y�� ����.
	@Override
	public void movePolice() {
		int dis = distance();
		int dis2;
		int temp;

		if (map1[policeX][policeY].getLeft() == 0) {
			temp = policeY;
			policeY -= 1;
			dis2 = distance();
			if (dis2 > dis) // �ȿ����� ��
				policeY = temp;
			else // ������ ��
				return;
		}
		if (map1[policeX][policeY].getRight() == 0) {
			temp = policeY;
			policeY += 1;
			dis2 = distance();
			if (dis2 > dis)
				policeY = temp;
			else
				return;
		}
		if (map1[policeX][policeY].getUp() == 0) {
			temp = policeX;
			policeX -= 1;
			dis2 = distance();
			if (dis2 > dis)
				policeX = temp;
			else
				return;
		}
		if (map1[policeX][policeY].getDown() == 0) {
			temp = policeX;
			policeX += 1;

			dis2 = distance();
			if (dis2 > dis)
				policeX = temp;
			else
				return;
		}

	}

	@Override
	public int distance() {
		// TODO Auto-generated method stub
		return Math.abs(thiefX - policeX) + Math.abs(thiefY - policeY);
	}

	public static void main(String[] args) {
		new Game();
	}
}
