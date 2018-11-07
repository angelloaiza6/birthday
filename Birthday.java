package Birth;

import java.util.Objects;

public class Birthday implements Comparable<Birthday> {
	private final int day;
	private final int month;

	public Birthday(int month, int day) {
		month = clamp(month, 1, 12);

		this.month = month;
		this.day = clamp(day, 1, getDaysInMonth(month));
	}

	@Override
	public int compareTo(Birthday other) {
		if (this.month < other.month) {
			return -1;
		} else if (this.month > other.month) {
			return 1;
		}

		if (this.day < other.day)
			return -1;
		return (this.day > other.day) ? 1 : 0;
	}
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Birthday))
			return false;
		//

		Birthday other = (Birthday)o;
		return day == other.day && month == other.month;
	}
	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	@Override
	public int hashCode() {
		return Objects.hash(month, day);
	}
	@Override
	public String toString() {
		String m = (month > 9) ? Integer.toString(month) : "0" + month;
		String d = (day > 9) ? Integer.toString(day) : "0" + day;
		return  m + "/" + d;
	}

	public static int getDaysInMonth(int month) {
		switch (clamp(month, 1, 12)) {
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				// Not always true, of course, but it is the maximum
				return 29;
			default:
				return 31;
		}
	}
	private static int clamp(int n, int min, int max) {
		if (n < min)
			return min;
		if (n > max)
			return max;
		return n;
	}
}
