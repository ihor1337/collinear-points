import java.util.*;

public class FastCollinearPoints {
    private List<LineSegment> segments = new ArrayList<>();
    private HashMap<Double, List<Point>> f_segments = new HashMap<>();

    public FastCollinearPoints(Point[] points) {
        Point[] points_cpy = Arrays.copyOf(points, points.length);

        for (Point s_point : points) {
            Arrays.sort(points_cpy, s_point.slopeOrder());
            List<Point> slope_points = new ArrayList<>();
            double slope = 0;
            double previousSlope = Double.NEGATIVE_INFINITY;

            for (int i = 1; i < points_cpy.length; i++) {
                slope = s_point.slopeTo(points_cpy[i]);
                if (slope == previousSlope) {
                    slope_points.add(points_cpy[i]);
                } else {
                    if (slope_points.size() >= 3) {
                        slope_points.add(s_point);
                        add_new_segment(slope_points, previousSlope);
                    }
                    slope_points.clear();
                    slope_points.add(points_cpy[i]);
                }
                previousSlope = slope;
            }
            if (slope_points.size() >= 3) {
                slope_points.add(s_point);
                add_new_segment(slope_points, slope);
            }
        }
    }

    private void add_new_segment(List<Point> slope_points, double slope) {
        List<Point> e_points = f_segments.get(slope);
        Collections.sort(slope_points);

        Point s_point = slope_points.get(0);
        Point e_point = slope_points.get(slope_points.size() - 1);

        if (e_points == null) {
            e_points = new ArrayList<>();
            e_points.add(e_point);
            f_segments.put(slope, e_points);
            segments.add(new LineSegment(s_point, e_point));
        } else {
            for (Point current_e_point : e_points) {
                if (curr_e_point.compareTo(e_point) == 0) {
                    return;
                }
            }
            e_points.add(e_point);
            segments.add(new LineSegment(s_point, e_point));
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }

}
