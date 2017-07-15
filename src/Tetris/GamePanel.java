package Tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

//22��10��
class GamePanel extends JPanel implements KeyListener{
	String[] shapeColor = {"red","green","blue","tan","yellow"};
	int[][] panelCoord = new int[25][16]; 		//ȫ���������飬��¼�߽�͹̶�����
	int[][] panelColor = new int[25][16]; 		//ȫ���������飬��¼ÿһ�����귽�����ɫ
	int[][] carrentShape = new int[25][16]; 	//��¼��ǰ����
	final int widthHeight = 35;
	final int CELL = 30;
	int nectShapeType = 0;
	int nextShapeTurn = 0;
	int shapeTurn = (int)(Math.random()*1000)%4;  	//ͼ�εĵڶ�������
	int shapeType = (int)(Math.random()*1000)%7;  	//ͼ�εĵ�һ������
	int left = 4;			//����߽߱�ľ���
	Graphics graphics;
	Image image;
	Toolkit tool;
	JFrame frame;
	int top = 0;		//�붨�߽߱�ľ���
	Timer timer;
	int hour = 0;
	int minute = 0;
	int second = 0;
	StringBuffer timeString = new StringBuffer();
	LeftPanel leftPanel;
	RightPabel rightPabel;
	//�洢ͼ��
	final int shapes[][][] = new int[][][] {
		// i
				{ { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
						{ 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } },
				// s
				{ { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
						{ 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
				// z
				{ { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
				// j
				{ { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
						{ 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
				// o
				{ { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
				// l
				{ { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
				// t
				{ { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
						{ 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }} };
	
	public GamePanel(JFrame frame,LeftPanel leftPanel){
		this.frame = frame;
		this.leftPanel = leftPanel;
		
		drawBorder();
		getRandom();
		timer = new Timer(1000, new TimerListener());
		timer.start();
		
		
	}
	//�ػ���庯��
	public void paint(Graphics g){
		super.paint(g);
		graphics = g;
		setBackground(Color.black);
		
		int index = 0;
		for(int row = top; row < top + 4; row++){
			for(int col = left; col < left + 4; col++){
				carrentShape[row][col] = shapes[shapeType][shapeTurn][index];
				index++;
			}
		}
		for(int row = top; row < top + 4; row++){
			for(int col = left; col < left + 4; col++){
				if(carrentShape[row][col] == 1 ){
					image = this.getToolkit().getImage("src/Tetris/"+ shapeColor[getColor(shapeType)] +".png");
			        graphics.drawImage(image, CELL*col, CELL*row, widthHeight, widthHeight, this);
				}
			}
		}
		setBarrier();
		addKeyListener(this);
	}
	//��ȡ�����,�������ͼ�ε��±�
	public void getRandom(){
		nectShapeType = (int)(Math.random()*1000)%7;
		nextShapeTurn = (int)(Math.random()*1000)%4;
		leftPanel.setBlock(shapes[nectShapeType][nextShapeTurn],shapeColor[getColor(nectShapeType)]);
		leftPanel.repaint();
	}
	//��ȡ�����
	public void setRandom(){
		shapeType = nectShapeType;
		shapeTurn = nextShapeTurn;
	}
	//���߽�
	public void drawBorder(){
		//����߽�
		for(int row = 0; row < 25; row++){
			panelCoord[row][0] = 2;
		}
		//���ұ߽�
		for(int row = 0; row < 25; row++){
			panelCoord[row][11] = 2;
		}
		//���ױ߽�
		for(int col = 0; col < 16; col++){
			panelCoord[22][col] = 2;
		}
	}
	//��ȡ��ɫ
	public int getColor(int shapeType){
		int color = -1;
		switch (shapeType) {
		case 0:
			color = 0;
			break;
		case 1:
		case 2:
			color = 1;
			break;
		case 3:
		case 5:
			color = 2;
			break;
		case 4:
			color = 3;
			break;
		case 6:
			color = 4;
			break;

		default:
			break;
		}
		return color;
	}
	// ��ʱ������,�ӳ�1������
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(moveDown()){
				top++;
			 }else{
				 //���̶���
				 for(int row = top; row < top + 4; row++){
					for(int col = left; col < left + 4; col++){
						if(carrentShape[row][col] == 1 ){
							panelCoord[row][col] = 2;
							panelColor[row][col] = getColor(shapeType);
						}
					}
				}
				//����
				delLine();
				if(top == 0){
					//��Ϸ����
					overGame();
				}
				//��������ĳ�ʼλ������
				left = 4;
				top = 0;
				//һ��Ҫ�������������������Ϊ�����Ƿ��鲻��������յ�
				setRandom();
				getRandom();
			 }
			repaint();
			//��ʱ�������������ļ�ʱ��
			setLeftTime();
		}
	}
	//���¿�ʼ��Ϸ
	public void newGame(){
		resetPanel();
		timer.start();
	}
	//��Ϸ��������
	public void overGame(){
		JOptionPane.showMessageDialog(frame, "GAME OVER!");
		resetPanel();
		timer.stop();
		repaint();
	}
	//�����������
	public void resetPanel(){
		hour = 0;
		minute = 0;
		second = 0;
		for(int row = 0; row < 22; row++){
			for(int col = 1; col < 11; col++){
				panelCoord[row][col] = 0;
				panelColor[row][col] = 0;
				carrentShape[row][col] = 0;
			}
		}
		//�������������
		leftPanel.dataReset();
		//��������ĳ�ʼλ������
		left = 4;
		top = 0;
		setRandom();
		getRandom();
	}
	/*
	 * ��ʱ�������������ļ�ʱ��
	 */
	public void setLeftTime(){
		timeString.delete(0, timeString.length());
		second++;
		if(second == 60){
			second = 0;
			minute++;
			if(minute == 60){
				hour++;
				if(hour == 24){
					hour = 0;
				}
			}
		}
		if(hour < 10){
			timeString.append(0);
		}
		timeString.append(hour);
		timeString.append(":");
		if(minute < 10){
			timeString.append(0);
		}
		timeString.append(minute);
		timeString.append(":");
		if(second < 10){
			timeString.append(0);
		}
		timeString.append(second);
		leftPanel.setTime(timeString.toString());
	}
	//�ж��Ƿ������ת
	public boolean isRotate(){
		drawBorder();
		int index = 0;
		for(int row = top; row < top + 4; row++){
			for(int col = left; col < left + 4; col++){
				if((shapes[shapeType][shapeTurn][index] == 1 && panelCoord[row][col] == 2)){
					return false;
				}
				index++;
			}
		}
		return true;
	}
	//�ж��Ƿ���������ƶ�
	public boolean moveDown(){
		drawBorder();
		for(int row = top; row < top + 4; row++){
			for(int col = left; col < left + 4; col++){
				if(carrentShape[row][col] == 1 && panelCoord[row+1][col] == 2){
					return false;
				}
			}
		}
		return true;
	}
	//�ж��Ƿ���������ƶ�
	public boolean moveLeft(){
		drawBorder();
		for(int row = top; row < top + 4; row++){
			for(int col = left; col < left + 4; col++){
				if((carrentShape[row][col] == 1 && panelCoord[row][col-1] == 2)){
					return false;
				}
			}
		}
		return true;
	}
	//�ж��Ƿ���������ƶ�
	public boolean moveRight(){
		drawBorder();
		for(int row = top; row < top + 4; row++){
			for(int col = left; col < left + 4; col++){
				if((carrentShape[row][col] == 1 && panelCoord[row][col+1] == 2)){
					return false;
				}
			}
		}
		return true;
	}
	//����
	public void delLine(){
		for(int row = 0; row < 22; row++){
			int index = 0;
			for(int col = 1; col < 11; col++){
				if(panelCoord[row][col] == 2){
					index++;
				}
			}
			//���У����еĺ������������������ƶ�һ��
			if(index == 10){
				for(int i = row; i > 0; i--){
					for(int j = 1; j < 11; j++){
						panelCoord[i][j] = panelCoord[i-1][j];
						panelColor[i][j] = panelColor[i-1][j];
					}
				}
				//��һ������
				for(int j = 1; j < 11; j++){
					panelCoord[0][j] = 0;
					panelColor[0][j] = 0;
				}
				//�����Ļ��ּӷ�
				leftPanel.addScore();
			}
		}
	}
	//���̶��ķ���
	public void setBarrier(){
		for(int i = 0; i < 22; i++){
			for(int k = 1; k < 11; k++){
				if(panelCoord[i][k] == 2){
					image = this.getToolkit().getImage("src/Tetris/"+ shapeColor[panelColor[i][k]] +".png");
			        graphics.drawImage(image, CELL*k, CELL*i, widthHeight, widthHeight, this);
				}
			}
		}
		//����߽�
		for(int row = 0; row < 25; row++){
			image = this.getToolkit().getImage("src/Tetris/grey.png");
	        graphics.drawImage(image, 0, CELL*row, widthHeight, widthHeight, this);
		}
		//���ұ߽�
		for(int row = 0; row < 25; row++){
			image = this.getToolkit().getImage("src/Tetris/grey.png");
	        graphics.drawImage(image, CELL*11, CELL*row, widthHeight, widthHeight, this);
		}
		//���ױ߽�
		for(int col = 0; col < 16; col++){
			image = this.getToolkit().getImage("src/Tetris/grey.png");
	        graphics.drawImage(image, CELL*col, CELL*22, widthHeight, widthHeight, this);
		}
	}
	/*
	 * ������¼���
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO �Զ����ɵķ������
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case 87:
				if(timer.isRunning()){
					shapeTurn++;
					shapeTurn %= 4;
					if(!isRotate()){
						shapeTurn--;
						if(shapeTurn == -1){
							shapeTurn = 3;
						}
					}
				}
				break;
			case KeyEvent.VK_DOWN: //���������Ҫ���������ȫ����������
			case 83: 
				 if(moveDown() && timer.isRunning()){
					top++;
				 }
				break;
			case KeyEvent.VK_LEFT:
			case 65:
				if(left == 0 && timer.isRunning()){
					break;
				}
				if(moveLeft() && timer.isRunning()){
					left--;
				}
				break;
			case KeyEvent.VK_RIGHT:
			case 68:
				if(moveRight() && timer.isRunning()){
					left++;
				}
				break;
			default:
				break;
		}
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {}
}
