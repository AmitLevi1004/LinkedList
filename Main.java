

public class Main {
	public static void main(String[]args) {
		StringList s1 = new StringList("aabbbacddd");
		StringList s2 = new StringList("abcae");
		StringList s3 = new StringList("abcae");
		System.out.println("s1: "+s1);
		System.out.println("s2: "+s2);
		System.out.println("s3: "+s3);

		System.out.println("*******************s1*******************");
		
		System.out.println(s1.substring(1,3));
		System.out.println(s1.length());
		System.out.println(s1.charAt(1));
		System.out.println(s1.indexOf ('a'));
		System.out.println(s1.indexOf ('a',2));
		System.out.println(s1.indexOf('d'));
		System.out.println(s1.compareTo (s1));
		System.out.println(s1.compareTo(s3));
		
		System.out.println("*******************s2*******************");

		System.out.println(s2.substring(1,3));
		System.out.println(s2.length());
		System.out.println(s2.charAt(1));
		System.out.println(s2.indexOf ('a'));
		System.out.println(s2.indexOf ('a',2));
		System.out.println(s2.indexOf('d'));
		System.out.println(s2.compareTo (s1));
		System.out.println(s2.compareTo(s3));

	}

}
