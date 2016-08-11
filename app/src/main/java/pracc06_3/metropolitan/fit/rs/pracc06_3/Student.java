package pracc06_3.metropolitan.fit.rs.pracc06_3;

/**
 * Created by Milan on 8/11/2016.
 */
public class Student {
    private String name, index, points;

    public Student() {
    }

    public Student(String name, String index, String points) {
        this.name   = name;
        this.index  = index;
        this.points = points;
    }

    public Student(Student studentCopy) {
        this.name   = studentCopy.name;
        this.index  = studentCopy.index;
        this.points = studentCopy.points;
    }

    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }

    public String getPoints() {
        return points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
