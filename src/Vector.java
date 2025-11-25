import java.util.*;

public class Vector {
    public float x;
    public float y;
    /**
     * Constructor for an empty vector:
     * x, y.
     */
    public Vector() {
    }

    /**
     * Constructor for a  vector
     */
    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This function sets the x and y component of a Vector
     */
    public Vector set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Given a Vector, this copies the component of the vector
     */
    public Vector set(Vector v) {
        x = v.x;
        y = v.y;
        return this;
    }

    /**
     * Calculates the magnitude (length) of the vector and returns the result
     * as a float (this is simply the equation
     *     sqrt(x*x + y*y)
     */
    public float mag()
    {
        return (float) Math.sqrt(x*x + y*y);
    }

    /**
     * Calculates the squared magnitude of the vector and returns the result
     * as a float
     * the equation (x*x + y*y)
     */
    public float magSq() {
        return (x*x + y*y);
    }

    /**
     * Given a vector, this function adds this vector to the caller vector.
     * It adds x, and y components to a vector or adds one vector to another, or
     * adds two independent vectors together.
     */
    public Vector add(Vector v) {
        x += v.x;
        y += v.y;
        return this;
    }

    /**
     * Very similar to the previous function, here the components are given and this function
     * adds these components to the vector.
     */
    public Vector add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    /**
     * Add two vectors into a target vector
     */
    public Vector add(Vector v1, Vector v2, Vector target) {
        if (target == null) {
            target = new Vector(v1.x + v2.x, v1.y + v2.y);
        } else {
            target.set(v1.x + v2.x, v1.y + v2.y);
        }
        return target;
    }


    /**
     * Subtracts x, y components from a vector, subtracts one vector
     * from another, or subtracts two independent vectors. The version of the
     * method that subtracts two vectors is a static method and returns a
     * Vector, the others have no return value -- they act directly on the
     * vector. See the examples for more context.
     */
    public Vector sub(Vector v) {
        x -= v.x;
        y -= v.y;
        return this;
    }


    /**
     * Given x,y component of a vector, subtract from the
     * caller vector.
     */
    public Vector sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    /**
     *
     * Multiplies a vector by a scalar or multiplies one vector by another.
     *
     */
    public Vector mult(float n) {
        x *= n;
        y *= n;
        return this;
    }

    /**
     * given a vector and a scalar this function
     * multiply the vector by the scalar
     */
    public Vector mult(Vector v, float n) {
        return mult(v, n);
    }

    /**
     * Multiply a vector by a scalar, and write the result into a target Vector.
     * @param target Vector in which to store the result
     */
    public Vector mult(Vector v, float n, Vector target) {
        if (target == null) {
            target = new Vector(v.x*n, v.y*n);
        } else {
            target.set(v.x*n, v.y*n);
        }
        return target;
    }

    /**
     *
     * Divides a vector by a scalar or divides one vector by another.
     */
    public Vector div(float n) {
        x /= n;
        y /= n;
        return this;
    }

    /**
     * Divide a vector by a scalar and return the result in a new vector.
     * @param v the vector to divide by the scalar
     * @return a new vector that is v1 / n
     */
    public Vector div(Vector v, float n) {
        return div(v, n, null);
    }

    /**
     * Divide a vector by a scalar and store the result in another vector.
     */
    public Vector div(Vector v, float n, Vector target) {
        if (target == null) {
            target = new Vector(v.x/n, v.y/n);
        } else {
            target.set(v.x/n, v.y/n);
        }
        return target;
    }

    /**
     *
     * Calculates the Euclidean distance between two points (considering a
     * point as a vector object).
     */
    public float dist(Vector v) {
        float dx = x - v.x;
        float dy = y - v.y;
        return (float) Math.sqrt(dx*dx + dy*dy);
    }

    /**
     * Calculates the dot product of two vectors.
     */
    public float dot(Vector v) {
        return x*v.x + y*v.y;
    }

    /**
     * Given x, y component of a vector this function computes
     * the dot product between the caller vector and the x,y component
     */
    public float dot(float x, float y) {

        return this.x*x + this.y*y;
    }

    /**
     * Returns the dot product between two vectors
     */
    public float dot(Vector v1, Vector v2) {

        return v1.x*v2.x + v1.y*v2.y;
    }

    /***
     * Normalize the vector to length 1 (make it a unit vector).
     */
    public Vector normalize() {
        float m = mag();
        if (m != 0 && m != 1) {
            div(m);
        }
        return this;
    }

    /**
     * Vector normalize function.
     */
    public Vector normalize(Vector target) {
        if (target == null) {
            target = new Vector();
        }
        float m = mag();
        if (m > 0) {
            target.set(x/m, y/m);
        } else {
            target.set(x, y);
        }
        return target;
    }


    /**
     * This is the limit implementation from Processing
     * Limit the magnitude of this vector to the value used for the max parameter.
     *
     */
    public Vector limit(float max) {
        if (magSq() > max*max) {
            normalize();
            mult(max);
        }
        return this;
    }

    /**
     * Set the magnitude of this vector to the value used for the length parameter.
     */
    public Vector setMag(float len) {
        normalize();
        mult(len);
        return this;
    }


    /**
     * Sets the magnitude of this vector, storing the result in another vector.
     */
    public Vector setMag(Vector target, float len) {
        target = normalize(target);
        target.mult(len);
        return target;
    }


    /**
     * Calculate the angle of rotation for this vector (only 2D vectors)
     *
     */
    public float heading() {
        float angle = (float) Math.atan2(y, x);
        return angle;
    }

    public String toString() {
        return "[ " + x + ", " + y + " ]";
    }
    /*
    This is the comparator function that compares a Vector with
    the caller Vector
    * */
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector)) {
            return false;
        }
        final Vector p = (Vector) obj;
        return x == p.x && y == p.y;
    }
}