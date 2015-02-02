package pl.grm.game.math;

public class Coordinates2D extends Coordinates1D implements Coordinates {
	protected int		y1;
	protected int		y2;
	private Vector2D	coordinateSystem	= new Vector2D(0, 0);
	
	public Coordinates2D(int x1, int y1, int x2, int y2) {
		super(x1, x2);
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public Coordinates2D() {
		this(0, 0, 64, 64);
	}
	
	public int getArea() {
		return Math.abs(getWidth() * getHeight());
	}
	
	@Override
	public String toString() {
		return "Coordinates2D [x1=" + this.x1 + ", x2=" + this.x2 + ", y1=" + this.y1 + ", y2="
				+ this.y2 + "]";
	}
	
	@Override
	public void setCoordinateSystem(Coordinates relativeCoords) {
		super.setCoordinateSystem(relativeCoords);
		if (relativeCoords instanceof Coordinates2D) {
			coordinateSystem.setY(((Coordinates2D) relativeCoords).getY1());
		}
	}
	
	@Override
	public void setDefaultCoordSystem() {
		super.setDefaultCoordSystem();
		coordinateSystem.setY(0);
	}
	
	public int getY1() {
		return this.y1;
	}
	
	public int getRY1() {
		return this.coordinateSystem.getY() + this.y1;
	}
	
	public void setY1(int y1) {
		this.y1 = y1;
	}
	
	public int getY2() {
		return this.y2;
	}
	
	public int getRY2() {
		return this.coordinateSystem.getY() + this.y2;
	}
	
	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	public int getHeight() {
		return this.y2 - this.y1;
	}
	
	public void setHeight(int height) {
		this.y2 = this.y1 + height;
	}
	
}