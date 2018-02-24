//Класс получает слова и проверяет, какие из них являются палиндромами.
public class Palindrome {
// Метод получает массив слов. Далее, он каждое слово сохраняет в строку, потом эту строку разворачивает, используя функцию reverseString(), и сравнивает получившуюся строку с оригиналом.
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			String s = args[i];
			String s1=reverseString(s);
			if (s1.equals(s)) System.out.println("Слово "+s+" - палиндром");
				else System.out.println("Слово "+s+" - не палиндром");
		} 
	}
	// Функция получает строку s, создаёт новую строку rev, посимвольно копирует в обратном порядке из s в rev и возвращает rev.
	public static String reverseString(String s) {
		String rev="";
		for (int i = s.length()-1; i >=0; i--) {
			rev+=s.charAt(i);
		}
		return rev;
	}
}