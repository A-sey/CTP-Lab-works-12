// ����� ����� � ��������� ������������
public class Point3d {
	// ����������� ��������� �����
	private double xCoord;
	private double yCoord;
	private double zCoord;
	// �����������, ����� ���������������� ����� (x,y,z)
	public Point3d (double x, double y, double z){
		xCoord=x;
		yCoord=y;
		zCoord=z;
	}
	// ����������� ��-���������
	public Point3d (){
		this (0,0,0);
	}
	// ������, ������������ ����������:
	public double getX(){
		return xCoord;
	}
	public double getY(){
		return yCoord;
	}
	public double getZ(){
		return zCoord;
	}
	// ������, ����������� ������ ����������:
	public void SetX(double val){
		xCoord=val;
	}
	public void SetY(double val){
		yCoord=val;
	}
	public void SetZ(double val){
		zCoord=val;
	}
	
	// �����, ����������� ��������� ���� �����
	public boolean areEqual (Point3d point) {
		if (xCoord==point.getX() && yCoord==point.getY() && zCoord==point.getZ()) return true;
		return false;
	}
	// �����, ��������� ���������� ����� ����� �������
	public double distanceTo(Point3d p){
		return Math.sqrt(Math.pow(xCoord-p.getX(),2)+Math.pow(yCoord-p.getY(),2)+Math.pow(zCoord-p.getZ(),2));
	}
}