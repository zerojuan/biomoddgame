package com.biomodd.util;



public class MathUtil {

	public static double radiansToDegrees(double angle){
		return angle * (180 / Math.PI);
	}
	
	public static double degreesToRadians(double angle){
		return angle * (Math.PI / 180);
	}
	
	/**
	 * Take an angle in degrees and return it's sine
	 * @param angle in degrees
	 * @return
	 */
	public static double sinD(double angle){
		return Math.sin(degreesToRadians(angle));
	}
	/**
	 * Take an angle in degrees and return it's cos
	 * @param angle in degrees
	 * @return
	 */
	public static double cosD(double angle){
		return Math.cos(degreesToRadians(angle));
	}
	/**
	 * Take an angle in degrees and return it's tangent
	 * @param angle in degrees
	 * @return
	 */
	public static double tanD(double angle){
		return Math.tan(degreesToRadians(angle));
	}
	/**
	 * Calculate the angle in degrees
	 * @param x
	 * @param y
	 * @return
	 */
	public static double atan2D(double x, double y){
		return radiansToDegrees(Math.atan2(y, x));
	}
	/**
	 * Gets the angle of a line specified by the points
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double angleOfLine(double x1, double y1, double x2, double y2){
		return atan2D(y2-y1,x2-x1);
	}
	/**
	 * Inverse cosine in degrees. Usually used to find the angle of 2 vectors
	 * @param cosRatio
	 * @return
	 */
	public static double acos2D(double cosRatio){
		return radiansToDegrees(Math.acos(cosRatio));
	}
	/**
	 * Ensure that the angle is between 0 and 360
	 * @param angle
	 * @return
	 */
	public static double fixAngle(double angle){
		angle %= 360;
		return (angle < 0) ? angle + 360 : angle;
	}
	/**
	 * Convert the cartesian coordinate to polar coordinate
	 * @param p the cartesian point
	 * @return the x point is the radius, the y is the angle in degrees
	 */
	public static PointF cartesianToPolar(PointF p){		
		double radius = Math.sqrt(p.x*p.x+p.y*p.y);
		double theta = atan2D(p.x, p.y);
		
		return new PointF((float)radius, (float)theta);
	}
	/**
	 * Convert the polar coordinate to cartesian coordinate
	 * @param p the x is the radius, the y is the angle in degrees
	 * @return the point in cartesian form
	 */
	public static PointF polarToCartesian(PointF p){
		double x = p.x * cosD(p.y);
		double y = p.x * sinD(p.y);
		
		return new PointF((float)x, (float)y);
	}
	
	
}
