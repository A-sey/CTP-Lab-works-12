// ����������� ����� Scanner
import java.util.Scanner;
// �����, ����������� ������� ���������
public class Lab1{
	// ������� �������� ���������� ��� ����� � ������� ������� ������������
	public static void main(String[] args){
		int eqError=0;
		// ������ ������ �� ��� �����
		Point3d[] p = new Point3d[]{new Point3d(),new Point3d(),new Point3d()};
		// ������ ���������� �����
        Scanner scanner = new Scanner(System.in);
        for(int i=1; i<=3; i++)
        {
            System.out.println("������� ���������� "+i+" �����:");
            p[i-1].SetX(Double.parseDouble(scanner.next()));
            p[i-1].SetY(Double.parseDouble(scanner.next()));
            p[i-1].SetZ(Double.parseDouble(scanner.next()));
        }
		// ��������� ����� �� ����������:
		if (p[0].areEqual(p[1]) || p[0].areEqual(p[2]) || p[1].areEqual(p[2])) {
			System.out.println("������� ����� � ����������� ������������!");
			eqError++;
		}
		// ���� ����� �� ���������, �� ������� ������� ������������
		if (eqError<1) {
			double area=computeArea(p[0],p[1],p[2]);
			System.out.println("������� ������������ ����� "+area);
		}		
	}
	// ����� ������� ������� ������������ ��������� ������� ������ 
	public static double computeArea (Point3d p1, Point3d p2, Point3d p3){
		double a=p1.distanceTo(p2);
		double b=p1.distanceTo(p3);
		double c=p2.distanceTo(p3);
		double p=(a+b+c)/2;
		return (Math.sqrt(p*(p-a)*(p-b)*(p-c)));
	}
}