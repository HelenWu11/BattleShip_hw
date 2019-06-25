package hw3;

import java.awt.Color;

public class Battleship {
	private int Battle_num;
	private Color Battle_color;
	private int Battle_size = 5;
	private String Battle_name;
	
	public Battleship() {
		
		Battle_name = "Battleship";
		Battle_num = 1; 
		Battle_color = Color.red;
		Battle_size = 5;
	}
	
	public Battleship(int num, Color color, int size) {
		Battle_num = num;
		Battle_color = color;
		Battle_size = size;
	}
	
	public String getName() {
		return Battle_name;
	}
	
	public int getNum() {
		return Battle_num;
	}
	
	public Color getColor() {
		return Battle_color;
	}
	
	public int getSize() {
		return Battle_size;
	}
	
	public void setNum(int num) {
		Battle_num = num;
	}
	
	public void setColor(Color color) {
		Battle_color = color;
	}
	
	public void setSize(int size) {
		Battle_size = size;
	}
}