package ru.stqa.javaCourse.sandbox;

public class MyFirstProgram{

	public static void main(String[] args){
		hello("World");
		hello("User");
		hello("Vladislav");

		double l = 5;
		System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));

		double a = 4;
		double b = 6;
		System.out.println("Площадь прямоугольника со сторонами " + a +" и " + b + " = " + area(a, b));
	}

	public static void hello(String name){
		System.out.println("Hello, " + name + "!");
	}

	public static double area(double l){
		return l * l;
	}

	public static double area(double a, double b){
		return a * b;
	}
}