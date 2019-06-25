package hw3;

import java.awt.Color;

public class Destroyer {
	private int Destroyer_num;
	private Color Destroyer_color;
	private int Destroyer_size = 2;
	private String Destroyer_name;
	
	public Destroyer() {
		
		Destroyer_num = 3; 
		Destroyer_color = Color.yellow;
		Destroyer_size = 2;
		Destroyer_name = "Destroyer";
		
	}
	
	public Destroyer(int num, Color color, int size) {
		Destroyer_num = num;
		Destroyer_color = color;
		Destroyer_size = size;
	}
	
	public String getName() {
		return Destroyer_name;
	}
	
	public int getNum() {
		return Destroyer_num;
	}
	
	public Color getColor() {
		return Destroyer_color;
	}
	
	public int getSize() {
		return Destroyer_size;
	}
	
	public void setNum(int num) {
		Destroyer_num = num;
	}
	
	public void setColor(Color color) {
		Destroyer_color = color;
	}
	
	public void setSize(int size) {
		Destroyer_size = size;
	}
}