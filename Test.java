package Birth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Test {
	public static void main(String[] args) {
		performTest(20, 10);
		performTest(50, 10);
		performTest(100, 10);
	}
	public static HashMap<Birthday, Integer> getDuplicateCounts(ArrayList<Birthday> list) {
		int count = 0;
		Birthday last = null;
		Birthday current = null;
		ArrayList<Birthday> copy = new ArrayList<Birthday>(list);
		HashMap<Birthday, Integer> map = new HashMap<Birthday, Integer>();

		Collections.sort(copy);

		for (Birthday b : copy) {
			if (b.equals(current)) {
				count++;
			} else if (b.equals(last)) {
				current = b;
				count += 2;
			} else {
				if (current != null && count > 1)
					map.put(current, count);
				count = 0;
				last = b;
			}
		}

		return map;
	}
	public static ArrayList<Birthday> generateBirthdays(int amount) {
		Random rand = new Random();
		ArrayList<Birthday> list = new ArrayList<Birthday>(amount);

		for (int i = 0; i < amount; i++) {
			int month = rand.nextInt(12) + 1;
			int day = rand.nextInt(Birthday.getDaysInMonth(month) + 1) + 1;
			list.add(new Birthday(month, day));
		}
		
		return list;
	}
	public static void performTest(int sampleSize, int iterations) {
		double successes = 0;
		System.out.printf("Running %d tests with sample size of %d.\n", iterations, sampleSize);

		for (int i = 0; i < iterations; i++) {
			System.out.printf("Test #%d: ", i + 1);

			ArrayList<Birthday> birthdays = generateBirthdays(sampleSize);
			HashMap<Birthday, Integer> duplicates = getDuplicateCounts(birthdays);

			if (duplicates.size() > 0) {
				successes++;
				System.out.println();
				for (Map.Entry<Birthday, Integer> entry : duplicates.entrySet()) {
					Birthday day = entry.getKey();
					int num = entry.getValue();
					System.out.printf("\t%d people were born on %s.\n", num, day);
				}
			} else {
				System.out.println("No birthdays were shared.");
			}

		}

		System.out.printf("Probability in sample size %d: %.2f%%.\n\n", sampleSize, (successes / iterations * 100));
	}
}
