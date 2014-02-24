package maxPointsonaLine;

import java.awt.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.HashSet;

//  Definition for a point.
class Point {
	int x;
	int y;

	Point() {
		x = 0;
		y = 0;
	}

	Point(int a, int b) {
		x = a;
		y = b;
	}
}

public class Solution {
	public int maxPoints(Point[] points) {

		int max = 0;
		int len = points.length;
		if (len < 3) {
			return len;
		}
		for (int i = 0; i < len - 1; i++) {
			int vertical = 0;
			int samePosition = 0;
//			double[] slopes = new double[len - i - 1];
			LinkedList<Double> slopes = new LinkedList<Double>();
			for (int j = i + 1; j < len; j++) {
				if (samePosition(points[i], points[j])) {
					// those two points at the same position
					samePosition++;
				} else if (points[i].x == points[j].x) {
					// two points at same vertical line, can't compute slope
					vertical++;
				} else {
//					slopes[j - i - 1] = getSlope(points[i], points[j]);
					slopes.add(getSlope(points[i], points[j]));
				}
			}

//			java.util.Arrays.sort(slopes);
			
			java.util.Collections.sort(slopes);
			
			int maxSlopes = getMode(slopes);
			if (maxSlopes + 1 > max - samePosition) {
				max = maxSlopes + samePosition + 1;
			}
			if (vertical + 1 > max - samePosition) {
				max = vertical + samePosition + 1;
			}
		}

		return max;
	}

	/**
	 * double[] -> int given a double array return the count of mode in this
	 * array
	 */
	private int getMode(double[] a) {
		int count = 1;
		int max = 0;
		double prev = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] - prev < 0.000001) {
				count++;
			} else {
				if (count > max) {
					max = count;
				}
				count = 1;
			}
			prev = a[i];
		}
		if (count > max) {
			max = count;
		}
		return max;
	}

	private int getMode(LinkedList<Double> a){
		if (a.size() <= 1){
			return a.size();
		}
		int count = 1;
		int max = 0;
		double prev = a.get(0);
//		for (int i = 1; i < a.length; i++) {
//			if (a[i] - prev < 0.000001) {
//				count++;
//			} else {
//				if (count > max) {
//					max = count;
//				}
//				count = 1;
//			}
//			prev = a[i];
//		}
		
		Iterator<Double> it = a.iterator();
		it.next();
		
		while(it.hasNext()){
			Double d = it.next();
			
			if (d - prev < 0.000001) {
				count++;
			} else {
				if (count > max) {
					max = count;
				}
				count = 1;
			}
			prev = d;
		}
		
		if (count > max) {
			max = count;
		}
		return max;
	}
	
	/**
	 * Point * 2 -> boolean given two points return true iff those two points
	 * are at same position
	 */
	private boolean samePosition(Point a, Point b) {
		return a.x == b.x && a.y == b.y;
	}

	/**
	 * Points * 2 -> double given two points return the slope
	 */
	private double getSlope(Point a, Point b) {
		return (double) (a.y - b.y) / (double) (a.x - b.x);
	}

	public static void main(String[] args) {
		Solution s = new Solution();

		Point p1 = new Point();
		p1.x = 0;
		p1.y = 0;

		Point p2 = new Point();
		p2.x = 1;
		p2.y = 1;

		Point p3 = new Point();
		p3.x = 1;
		p3.y = -1;

		Point[] points = new Point[] { p1, p2, p3 };
		int max = s.maxPoints(points);
		System.out.println(max);
	}
}