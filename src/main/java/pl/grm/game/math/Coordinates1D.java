package pl.grm.game.math;

public class Coordinates1D implements Coordinates {
	protected int		x1;
	protected int		x2;
	private Vector1D	coordinateSystem	= new Vector1D(0);
	
	public Coordinates1D(int x1, int x2) {
		this.x1 = x1;
		this.x2 = x2;
	}
	
	public Coordinates1D() {
		this(0, 64);
	}
	
	@Override
	public String toString() {
		return "Coordinates1D [x1=" + this.x1 + ", x2=" + this.x2 + "]";
	}
	
	public void setCoordinateSystem(Coordinates relativeCoords) {
		if (relativeCoords instanceof Coordinates1D) {
			coordinateSystem.setX(((Coordinates1D) relativeCoords).getX1());
		}
	}
	
	public void setDefaultCoordSystem() {
		coordinateSystem.setX(0);
	}
	
	public int getX1() {
		return this.x1;
	}
	
	public int getRX1() {
		return this.coordinateSystem.getX() + this.x1;
	}
	
	public void setX1(int x1) {
		this.x1 = x1;
	}
	
	public int getX2() {
		return this.x2;
	}
	
	public int getRX2() {
		return this.coordinateSystem.getX() + this.x2;
	}
	
	public void setX2(int x2) {
		this.x2 = x2;
	}
	
	public int getWidth() {
		return this.x2 - this.x1;
	}
	
	public void setWidth(int width) {
		this.x2 = this.x1 + width;
	}
	
	public Vector1D getCoordinateSystem() {
		return coordinateSystem;
	}
	
	public void setCoordinateSystem(Vector1D coordinateSystem) {
		this.coordinateSystem = coordinateSystem;
	}
}