package hw3;

import java.awt.Color;

public class Cruiser {
	private int Cruiser_num;
	private Color Cruiser_color;
	private int Cruiser_size = 3;
	private String Cruiser_name;
	
	public Cruiser() {
		
		Cruiser_num = 2; 
		Cruiser_color = Color.orange;
		Cruiser_size = 3;
		Cruiser_name = "Cruiser";
	}
	
	public Cruiser(int num, Color color, int size) {
		Cruiser_num = num;
		Cruiser_color = color;
		Cruiser_size = size;
	}
	
	public String getName() {
		return Cruiser_name;
	}
	
	public int getNum() {
		return Cruiser_num;
	}
	
	public Color getColor() {
		return Cruiser_color;
	}
	
	public int getSize() {
		return Cruiser_size;
	}
	
	public void setNum(int num) {
		Cruiser_num = num;
	}
	
	public void setColor(Color color) {
		Cruiser_color = color;
	}
	
	public void setSize(int size) {
		Cruiser_size = size;
	}
}