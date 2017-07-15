package Tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


//左边面板，记录分数，等级，时间等..
public class LeftPanel extends JPanel{
	JLabel scoreLabel,timeLabel,delineNumLabel,rankLabel;
	JLabel scoreValue,timeValue,delineNumValue,rankValue;
	JLabel nextOnce;
	Font font;
	Image image;
	int shape[][] = new int[4][4]; 
	final int CELL = 30;    
	final int widthHeight = 35;
	String shapeColor;
	int score = 0;
	int deLineNum = 0;
	int rank = 0;
	public LeftPanel(){
		setLayout(null);
		setBackground(Color.BLACK);
		font = new Font("宋体", Font.BOLD, 20);
		
		scoreLabel = new JLabel("分数");
		scoreValue = new JLabel("0");
		timeLabel = new JLabel("时间");
		timeValue = new JLabel("00:00:00");
		delineNumLabel = new JLabel("消行");
		delineNumValue = new JLabel("0");
		rankLabel = new JLabel("等级");
		rankValue = new JLabel("0");
		nextOnce = new JLabel("下一个");
		
		scoreLabel.setBounds(20, 20, 100, 30);
		scoreValue.setBounds(80, 20, 100, 30);
		timeLabel.setBounds(20, 70, 100, 30);
		timeValue.setBounds(80, 70, 100, 30);
		delineNumLabel.setBounds(20, 120, 100, 30);
		delineNumValue.setBounds(80, 120, 100, 30);
		rankLabel.setBounds(20, 170, 100, 30);
		rankValue.setBounds(80, 170, 100, 30);
		nextOnce.setBounds(50, 300, 100, 30);
		
		scoreLabel.setForeground(Color.white);
		scoreValue.setForeground(Color.white);
		timeLabel.setForeground(Color.white);
		timeValue.setForeground(Color.white);
		delineNumLabel.setForeground(Color.white);
		delineNumValue.setForeground(Color.white);
		rankLabel.setForeground(Color.white);
		rankValue.setForeground(Color.white);
		nextOnce.setForeground(Color.white);
		
		
		scoreLabel.setFont(font);
		scoreValue.setFont(font);
		timeLabel.setFont(font);
		timeValue.setFont(font);
		delineNumLabel.setFont(font);
		delineNumValue.setFont(font);
		rankLabel.setFont(font);
		rankValue.setFont(font);
		nextOnce.setFont(font);
		
		scoreValue.setForeground(new Color(191,85,236));
		timeValue.setForeground(new Color(191,85,236));
		delineNumValue.setForeground(new Color(191,85,236));
		rankValue.setForeground(new Color(191,85,236));
		nextOnce.setForeground(new Color(191,85,236));
		
		
		add(scoreLabel);
		add(scoreValue);
		add(timeLabel);
		add(timeValue);
		add(delineNumLabel);
		add(delineNumValue);
		add(rankLabel);
		add(rankValue);
		add(nextOnce);
		
	}
	@Override
	public void paint(Graphics g) {
		// TODO 自动生成的方法存根
		super.paint(g);
		for(int row = 0; row < 4; row++){
			for(int col = 0; col < 4; col++){
				if(shape[row][col] == 1){
					image = this.getToolkit().getImage("src/Tetris/"+shapeColor+".png");
			        g.drawImage(image, CELL*(col+1), CELL*(row+13), widthHeight, widthHeight, this);
				}
			}
		}
	}
	//设置下一个方块的坐标和颜色
	public void setBlock(int [] block,String shapeColor){
		this.shapeColor = shapeColor;
		int index = 0;
		for(int row = 0; row < 4; row++){
			for(int col = 0; col < 4; col++){
				shape[row][col] = block[index];
				index++;
			}
		}
	}
	//积分加分
	public void addScore(){
		score += 100;
		deLineNum++;
		scoreValue.setText(String.valueOf(score));
		delineNumValue.setText(String.valueOf(deLineNum));
		rankValue.setText(String.valueOf(score/1000));
	}
	//计时器
	public void setTime(String time){
		timeValue.setText(time);
	}
	//重置数据
	public void dataReset(){
		scoreValue.setText("0");
		timeValue.setText("00:00:00");
		delineNumValue.setText("0");
		rankValue.setText("0");
		score = 0;
		deLineNum = 0;
		rank = 0;
	}
}
