//  Класс выводит все простые числа в диапазоне от 2 до 100
public class Primes {
	// Метод перебирает все числа заданного диапазона и, используя нижеописанную функцию, проверяет, являются ли они простыми. Если да, то выводит их в консоль.
	public static void main(String[] args) {
		for (int j=2;j<=100;j++)
		{
			if (isPrime(j)) System.out.println(j+" ");
		}
	}
	// Функция проверяет, является ли полученное ею число простым
	public static boolean isPrime(int n)
{
		for (int i=2;i<n;i++)
		{
			if (n%i==0) return false;
		}
		return true;
	}
}
