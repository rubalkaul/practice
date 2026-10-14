package Practice;

import java.util.HashSet;
import java.util.Set;

public class sep08 {

	public static void main(String[] args) {
		System.out.println(annagram());
	}

	private static String annagram() {
		String s1 = "listen";
		String s2 = "silent";

		if (s1.length() != s2.length())
			return "NO";

		Set<Character> set1 = new HashSet<>();
		Set<Character> set2 = new HashSet<>();

		for (char c : s1.toCharArray()) {
			set1.add(c);
		}

		for (char c : s2.toCharArray()) {
			set2.add(c);
		}

		if (set1.equals(set2))
			return "yes";
		else
			return "NO";

	}

}
