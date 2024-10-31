package primitives;

/**
 * Bella & Rachel 
 */
public class Material {
	public Double3 kD = Double3.ZERO;
	public Double3 kS = Double3.ZERO;
	public Double3 kT = Double3.ZERO;
 	public Double3 kR = Double3.ZERO;
	public int nShininess = 0; 
	
	/**
	 * @param kD the kD to set
	 */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}
	
	/**
	 * @param kD the kD to set
	 */
	public Material setKd(Double kD) {
		this.kD = new Double3(kD);
		return this;
	}
	

	/**
	 * @param kS the kS to set
	 */
	public Material setKs(Double3 kS) {
		this.kS = kS;
		return this;
	}
	
	/**
	 * @param kS the kS to set
	 */
	public Material setKs(Double kS) {
		this.kS = new Double3(kS);
		return this;
	}
	
	/**
	 * @param kR the kR to set
	 */
	public Material setkR(Double3 kR) {
		this.kR = kR;
		return this;
	}

	/**
	 * @param kR the kR to set
	 */
	public Material setkR(Double kR) {
		this.kR = new Double3(kR);
		return this;
	}

	/**
	 * @param kT the kT to set
	 */
	public Material setkT(Double3 kT) {
		this.kT = kT;
		return this;
	}
	
	/**
	 * @param kT the kT to set
	 */
	public Material setkT(Double kT) {
		this.kT = new Double3(kT);
		return this;
	}

	/**
	 * @param nShininess the nShininess to set
	 */
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	} 
}
