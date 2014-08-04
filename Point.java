/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.*;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new PointComp();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate
    private static class PointComp implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            return p1.compareTo(p2);
        }
    }
    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        double xdiff = that.x - this.x;
        double ydiff = that.y - this.y;
        
        if (xdiff == 0) {
            if (ydiff == 0) return -Double.NaN;
            return Double.NaN;
        }
        
        return ydiff/xdiff;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        return (this.y < that.y) || ((this.y == that.y) && (this.x < that.x)) ? -1 : 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    public static void sort(Point[] a, Comparator<Point> comparator) {
        int N = a.length;
        for (int i = 0; i < N; i++) 
            for (int j = i; j > 0 && less(comparator, a[j], a[j-1]); j--)  
                 exch(a, j, j-1);
    }
    
    private static boolean less(Comparator<Point> c, Point v, Point w) {
        return c.compare(v, w) < 0;
    }
    
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        int x, y, numPoints = StdIn.readInt();
        if (numPoints > 0) {
            Point[] pointArr = new Point[numPoints];
            for (int i = 1; i <= numPoints; i++) {
                x = StdIn.readInt();
                y = StdIn.readInt();
                pointArr[i - 1] = new Point(x, y);
                StdOut.printf("new point is %s\n", pointArr[i - 1].toString());
            }
//            Arrays.sort(pointArr);
//            for (int i = 0; i < pointArr.length - 1; i++) {
//                StdOut.printf("point : %s\n", pointArr[i].toString());
//                pointArr[i].drawTo(pointArr[i + 1]);
//            }
            int N = pointArr.length;
            Point p, q, r, s;
            for (int i = 0; i < N; i++) {
                for (int j = i; j < N; j++) { 
                    for (int k = j; k < N; k++) {
                        for (int m = k; m < N; m++) {
                            if ((i != j) && (j != k) && (k != m) && (k != i)) {
                                p = pointArr[i]; 
                                q = pointArr[j]; 
                                r = pointArr[k];
                                s = pointArr[m];
                                if ((p.slopeTo(q) == p.slopeTo(r)) && (p.slopeTo(q) == p.slopeTo(s)))
                                    StdOut.printf("p = %s, q = %s, r = %s, s = %s\n", p.toString(), q.toString(), r.toString(), s.toString());
                            }
                        }
                    }
                }
            }
        }
    }
}