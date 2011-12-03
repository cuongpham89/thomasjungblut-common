package de.jungblut.clustering.model;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.WritableComparable;

public final class Vector implements WritableComparable<Vector> {

    private double[] vector;

    public Vector() {
	super();
    }

    public Vector(Vector v) {
	super();
	int l = v.vector.length;
	this.vector = new double[l];
	System.arraycopy(v.vector, 0, this.vector, 0, l);
    }

    public Vector(double x, double y) {
	super();
	this.vector = new double[] { x, y };
    }

    public Vector(double... vec) {
	this.vector = vec;
    }

    @Override
    public void write(DataOutput out) throws IOException {
	out.writeInt(vector.length);
	for (int i = 0; i < vector.length; i++)
	    out.writeDouble(vector[i]);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
	int size = in.readInt();
	vector = new double[size];
	for (int i = 0; i < size; i++)
	    vector[i] = in.readDouble();
    }

    @Override
    public int compareTo(Vector o) {

	boolean equals = true;
	for (int i = 0; i < vector.length; i++) {
	    if (vector[i] != o.vector[i]) {
		equals = false;
		return (int) (vector[i] - o.vector[i]);
	    }
	}
	if (equals) {
	    return 0;
	} else {
	    return 1;
	}
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Arrays.hashCode(vector);
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Vector other = (Vector) obj;
	if (!Arrays.equals(vector, other.vector))
	    return false;
	return true;
    }

    public double[] getVector() {
	return vector;
    }

    public void setVector(double[] vector) {
	this.vector = vector;
    }

    @Override
    public String toString() {
	return "Vector [vector=" + Arrays.toString(vector) + "]";
    }

}