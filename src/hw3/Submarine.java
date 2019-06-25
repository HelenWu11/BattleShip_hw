package hw3;

import java.awt.Color;

public class Submarine {
	private int Submarine_num;
	private Color Submarine_color;
	private int Submarine_size = 5;
	private String Submarine_name = "Submarine";
	
	public Submarine() {
		
		Submarine_num = 5; 
		Submarine_color = Color.green;
		Submarine_size = 1;
	}
	
	public Submarine(int num, Color color, int size) {
		Submarine_num = num;
		Submarine_color = color;
		Submarine_size = size;
	}
	
	public String getName() {
		return Submarine_name;
	}
	
	public int getNum() {
		return Submarine_num;
	}
	
	public Color getColor() {
		return Submarine_color;
	}
	
	public int getSize() {
		return Submarine_size;
	}
	
	public void setNum(int num) {
		Submarine_num = num;
	}
	
	public void setColor(Color color) {
		Submarine_color = color;
	}
	
	public void setSize(int size) {
		Submarine_size = size;
	}
}