package btcircle;
import java.util.Scanner;

public class circle {
	private double radius;
	private String color;
	
	public circle() {
		radius = 1.0;
		color = "red";
	}
	
	public circle(double r) {
		radius = r;
		color = "red";
	}
	
	public circle(double r, String color) {
		radius = r;
		color = color;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setRadius(double newRadius) {
		radius = newRadius;
	}
	
	public double getColor() {
		return color;
	}
	
	public void setColor(String newColor) {
		color = newColor;
	}
	
	public double getArea() {
		return radius*radius*Math.PI;
	}

	public String toString() {
		return "circle [radius=" + radius + ", color=" + color + ", area=" + getArea() + "]";
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		circle c1 = new circle();
		System.out.println(c1.toString());
		circle c2 = new circle(2.0,"yellow");
		System.out.println(c2.toString());
}
}


asdasd