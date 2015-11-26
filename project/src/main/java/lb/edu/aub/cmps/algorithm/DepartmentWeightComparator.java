package lb.edu.aub.cmps.algorithm;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import lb.edu.aub.cmps.classes.Department;

public class DepartmentWeightComparator implements Comparator<Department>{

	public int compare(Department d1, Department d2) {
		int ret = d1.getCourses_offered().size() - d2.getCourses_offered().size();
		if(ret!=0) return ret;
		else return d1.getId() - d2.getId();
	}

	public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
			Function<? super T, ? extends U> arg0) {
		return null;
	}

	public static <T, U> Comparator<T> comparing(
			Function<? super T, ? extends U> arg0, Comparator<? super U> arg1) {
		return null;
	}

	public static <T> Comparator<T> comparingDouble(
			ToDoubleFunction<? super T> arg0) {
		return null;
	}

	public static <T> Comparator<T> comparingInt(ToIntFunction<? super T> arg0) {
		return null;
	}

	public static <T> Comparator<T> comparingLong(ToLongFunction<? super T> arg0) {
		return null;
	}

	public static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
		return null;
	}

	public static <T> Comparator<T> nullsFirst(Comparator<? super T> arg0) {
		return null;
	}

	public static <T> Comparator<T> nullsLast(Comparator<? super T> arg0) {
		return null;
	}

	public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
		return null;
	}

	public Comparator<Department> reversed() {
		return null;
	}

	public Comparator<Department> thenComparing(
			Comparator<? super Department> arg0) {
		return null;
	}

	public <U extends Comparable<? super U>> Comparator<Department> thenComparing(
			Function<? super Department, ? extends U> arg0) {
		return null;
	}

	public <U> Comparator<Department> thenComparing(
			Function<? super Department, ? extends U> arg0,
			Comparator<? super U> arg1) {
		return null;
	}

	public Comparator<Department> thenComparingDouble(
			ToDoubleFunction<? super Department> arg0) {
		return null;
	}

	public Comparator<Department> thenComparingInt(
			ToIntFunction<? super Department> arg0) {
		return null;
	}

	public Comparator<Department> thenComparingLong(
			ToLongFunction<? super Department> arg0) {
		return null;
	}

}
