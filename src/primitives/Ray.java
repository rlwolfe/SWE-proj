package primitives;

/**
 * Bella & Rachel
 * constructors accept a point and a vector
 * fields include: head, direction
 * public methods consist of: equals
 * Overridden methods is: toSring 
 */
public class Ray {
	final public Point head;
	final public Vector direction;
		
	/**
	 * @param head (head)
	 * @param direction (vector)
	 * constructor that receive a point and a vector and assigns them
	 */
	public Ray(Point head, Vector direction) {
		this.head = head;
		this.direction = direction.normalize();
	}

	/**
	 * @return result if called object equals given object
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return (obj instanceof Ray other &&
				this.head.equals(other.head) &&
				this.direction.equals(other.direction));
	}

	@Override
	public String toString() {
		return "head=" + head + ", direction=" + direction;
	}
	
}
