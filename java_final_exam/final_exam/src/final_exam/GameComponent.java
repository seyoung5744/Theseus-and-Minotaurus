package final_exam;

// 게임에 필요한 규칙들 method로 모아서 정의
public interface GameComponent{
	public int distance(); // 도둑과 경찰의 거리 계산
	public void movePolice();
	public void getThief();
	public void arriveDetination();
	public void initPosition();
//	public int setXY(int X, int Y);
//	public int calc(int a, int b);
}
