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

//22行10列
class GamePanel extends JPanel implements KeyListener{
	String[] shapeColor = {"red","green","blue","tan","yellow"};
	int[][] panelCoord = new int[25][16]; 		//全局坐标数组，记录边界和固定方块
	int[][] panelColor = new int[25][16]; 		//全局坐标数组，记录每一个坐标方块的颜色
	int[][] carrentShape = new int[25][16]; 	//记录当前方块
	final int widthHeight = 35;
	final int CELL = 30;
	int nectShapeType = 0;
	int nextShapeTurn = 0;
	int shapeTurn = (int)(Math.random()*1000)%4;  	//图形的第二个坐标
	int shapeType = (int)(Math.random()*1000)%7;  	//图形的第一个坐标
	int left = 4;			//与左边边界的距离
	Graphics graphics;
	Image image;
	Toolkit tool;
	JFrame frame;
	int top = 0;		//与定边边界的距离
	Timer timer;
	int hour = 0;
	int minute = 0;
	int second = 0;
	StringBuffer timeString = new StringBuffer();
	LeftPanel leftPanel;
	RightPabel rightPabel;
	//存储图形
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
	//重画面板函数
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
	//获取随机数,随机产生图形的下标
	public void getRandom(){
		nectShapeType = (int)(Math.random()*1000)%7;
		nextShapeTurn = (int)(Math.random()*1000)%4;
		leftPanel.setBlock(shapes[nectShapeType][nextShapeTurn],shapeColor[getColor(nectShapeType)]);
		leftPanel.repaint();
	}
	//获取随机数
	public void setRandom(){
		shapeType = nectShapeType;
		shapeTurn = nextShapeTurn;
	}
	//画边界
	public void drawBorder(){
		//画左边界
		for(int row = 0; row < 25; row++){
			panelCoord[row][0] = 2;
		}
		//画右边界
		for(int row = 0; row < 25; row++){
			panelCoord[row][11] = 2;
		}
		//画底边界
		for(int col = 0; col < 16; col++){
			panelCoord[22][col] = 2;
		}
	}
	//获取颜色
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
	// 定时器监听,延迟1秒下落
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(moveDown()){
				top++;
			 }else{
				 //画固定块
				 for(int row = top; row < top + 4; row++){
					for(int col = left; col < left + 4; col++){
						if(carrentShape[row][col] == 1 ){
							panelCoord[row][col] = 2;
							panelColor[row][col] = getColor(shapeType);
						}
					}
				}
				//消行
				delLine();
				if(top == 0){
					//游戏结束
					overGame();
				}
				//新生方块的初始位置坐标
				left = 4;
				top = 0;
				//一定要在这里生产随机数，因为这里是方块不能下落的终点
				setRandom();
				getRandom();
			 }
			repaint();
			//计时，并设置左面板的计时条
			setLeftTime();
		}
	}
	//重新开始游戏
	public void newGame(){
		resetPanel();
		timer.start();
	}
	//游戏结束函数
	public void overGame(){
		JOptionPane.showMessageDialog(frame, "GAME OVER!");
		resetPanel();
		timer.stop();
		repaint();
	}
	//重置面板数据
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
		//清空左面板的数据
		leftPanel.dataReset();
		//新生方块的初始位置坐标
		left = 4;
		top = 0;
		setRandom();
		getRandom();
	}
	/*
	 * 计时，并设置左面板的计时条
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
	//判断是否可以旋转
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
	//判断是否可以向下移动
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
	//判断是否可以向左移动
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
	//判断是否可以向右移动
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
	//消行
	public void delLine(){
		for(int row = 0; row < 22; row++){
			int index = 0;
			for(int col = 1; col < 11; col++){
				if(panelCoord[row][col] == 2){
					index++;
				}
			}
			//满行，满行的后面行所有坐标向下移动一行
			if(index == 10){
				for(int i = row; i > 0; i--){
					for(int j = 1; j < 11; j++){
						panelCoord[i][j] = panelCoord[i-1][j];
						panelColor[i][j] = panelColor[i-1][j];
					}
				}
				//第一行置零
				for(int j = 1; j < 11; j++){
					panelCoord[0][j] = 0;
					panelColor[0][j] = 0;
				}
				//左面板的积分加分
				leftPanel.addScore();
			}
		}
	}
	//画固定的方块
	public void setBarrier(){
		for(int i = 0; i < 22; i++){
			for(int k = 1; k < 11; k++){
				if(panelCoord[i][k] == 2){
					image = this.getToolkit().getImage("src/Tetris/"+ shapeColor[panelColor[i][k]] +".png");
			        graphics.drawImage(image, CELL*k, CELL*i, widthHeight, widthHeight, this);
				}
			}
		}
		//画左边界
		for(int row = 0; row < 25; row++){
			image = this.getToolkit().getImage("src/Tetris/grey.png");
	        graphics.drawImage(image, 0, CELL*row, widthHeight, widthHeight, this);
		}
		//画右边界
		for(int row = 0; row < 25; row++){
			image = this.getToolkit().getImage("src/Tetris/grey.png");
	        graphics.drawImage(image, CELL*11, CELL*row, widthHeight, widthHeight, this);
		}
		//画底边界
		for(int col = 0; col < 16; col++){
			image = this.getToolkit().getImage("src/Tetris/grey.png");
	        graphics.drawImage(image, CELL*col, CELL*22, widthHeight, widthHeight, this);
		}
	}
	/*
	 * （鼠标事件）
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
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
			case KeyEvent.VK_DOWN: //不能下落后要把坐标加入全局坐标数组
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
