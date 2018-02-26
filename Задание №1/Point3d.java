// Класс точки в трёхмерном пространстве
public class Point3d {
	// Переменные координат точки
	private double xCoord;
	private double yCoord;
	private double zCoord;
	// Конструктор, чтобы инициализировать точку (x,y,z)
	public Point3d (double x, double y, double z){
		xCoord=x;
		yCoord=y;
		zCoord=z;
	}
	// Конструктор по умолчанию
	public Point3d (){
		this (0,0,0);
	}
	// Методы, возвращающие координаты:
	public double getX(){
		return xCoord;
	}
	public double getY(){
		return yCoord;
	}
	public double getZ(){
		return zCoord;
	}
	// Методы, позволяющие ввести координаты:
	public void SetX(double val){
		xCoord=val;
	}
	public void SetY(double val){
		yCoord=val;
	}
	public void SetZ(double val){
		zCoord=val;
	}
	
	// Метод, проверяющий равенство двух точек
	public boolean areEqual (Point3d point) {
		if (xCoord==point.getX() && yCoord==point.getY() && zCoord==point.getZ()) return true;
		return false;
	}
	// Метод, находящий расстояние между двумя точками
	public double distanceTo(Point3d p){
		return Math.sqrt(Math.pow(xCoord-p.getX(),2)+Math.pow(yCoord-p.getY(),2)+Math.pow(zCoord-p.getZ(),2));
	}
}
