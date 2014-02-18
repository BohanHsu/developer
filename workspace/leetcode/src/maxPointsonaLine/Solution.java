package maxPointsonaLine;

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

class Line {
	double k;
	double b;

	public Line(double k, double b) {
		super();
		this.k = k;
		this.b = b;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Line) {
			return this.k == ((Line) obj).k && this.b == ((Line) obj).b;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (int) (this.k + this.b);
	}

}

public class Solution {
	public int maxPoints(Point[] points) {
		HashSet<Line> lines = new HashSet<Line>();
		HashSet<Integer> vertical = new HashSet<Integer>();
		int len = points.length;

		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {

				System.out.println(i + "," + j);

				if (!isOverlaped(points[i], points[j])) {
					// not overlaped
					if (!isVertical(points[i], points[j])) {
						// not vertical
						double slope = getSlope(points[i], points[j]);
						double shift = getShift(points[i], points[j]);
						lines.add(new Line(slope, shift));
					}
				} else {
					// overlaped
				}
			}
			vertical.add(points[i].x);
		}

		int max = 0;

		for (Line l : lines) {
			int count = 0;
			for (int j = 0; j < len; j++) {
				if (isInLine(points[j], l.k, l.b)) {
					count++;
				}
			}
			if (count > max) {
				max = count;
			}
		}

		for (Integer i : vertical) {
			int count = 0;
			for (int k = 0; k < len; k++) {
				if (points[k].x == i) {
					count++;
				}
			}
			if (count > max) {
				max = count;
			}
		}
		return max;
	}

	/**
	 * Point, double * 2 -> boolean p : a point k : slope of a line b : shift of
	 * a line return : true iff the given point is in the line, otherwise return
	 * false
	 */
	private boolean isInLine(Point p, double k, double b) {
		return (double) p.y == (double) p.x * k + b;
	}

	/**
	 * Point * 2 -> boolean return : true iff the given two points formed a
	 * vertical line
	 */
	private boolean isVertical(Point a, Point b) {
		if (a.x == b.x) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Point * 2 -> boolean return : true iff the given two points overlaped,
	 * otherwise return false
	 */
	private boolean isOverlaped(Point a, Point b) {
		if (a.x == b.x && a.y == b.y) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Point * 2 -> double return : the slope of the line which formed by the
	 * given two points
	 */
	private double getSlope(Point a, Point b) {
		return (double) (a.y - b.y) / (double) (a.x - b.x);
	}

	/**
	 * Point * 2 -> double return : the shift of the line which formed by the
	 * given two points
	 */
	private double getShift(Point a, Point b) {
		double k = getSlope(a, b);
		return (double) a.y - (double) a.x * k;
	}

	public static void main(String[] args) {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(2, 2);
		Point p3 = new Point(0, 0);

		Solution s = new Solution();
		Point[] points = new Point[] { p1, p2, p3 };
		int max = s.maxPoints(points);
		System.out.println(max);
	}

}