import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments;


    public BruteCollinearPoints(Point[] points) {
        Point[] points_cpy = Arrays.copyOf(points, points.length);
        ArrayList<LineSegment> f_segments = new ArrayList<>();
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated entries");
                }
            }
        }
        Arrays.sort(points_cpy);
        for (int p = 0; p < points_cpy.length - 3; p++) {
            for (int q = p + 1; q < points_cpy.length - 2; q++) {
                for (int r = q + 1; r < points_cpy.length - 1; r++) {
                    for (int s = r + 1; s < points_cpy.length; s++) {
                        if (points_cpy[p].slopeTo(points_cpy[q]) == points_cpy[p].slopeTo(points_cpy[r]) &&
                                points_cpy[p].slopeTo(points_cpy[q]) == points_cpy[p].slopeTo(points_cpy[s])) {
                            f_segments.add(new LineSegment(points_cpy[p], points_cpy[s]));
                        }
                    }
                }
            }
        }
        segments = f_segments.toArray(new LineSegment[f_segments.size()]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, numberOfSegments());
    }
}
