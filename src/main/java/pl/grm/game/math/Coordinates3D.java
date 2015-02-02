package pl.grm.game.math;

public class Coordinates3D extends Coordinates2D implements Coordinates {
	protected int		z1;
	protected int		z2;
	private Vector3D	coordinateSystem	= new Vector3D(0, 0, 0);
	
	public Coordinates3D(int x1, int y1, int z1, int x2, int y2, int z2) {
		super(x1, y1, x2, y2);
		this.z1 = z1;
		this.z2 = z2;
	}
	
	public Coordinates3D() {
		this(0, 0, 0, 64, 64, 64);
	}
	
	public int getVolume() {
		return Math.abs(getArea() * getDepth());
	}
	
	@Override
	public String toString() {
		return "Coordinates3D [z1=" + this.z1 + ", z2=" + this.z2 + ", y1=" + this.y1 + ", y2="
				+ this.y2 + ", x1=" + this.x1 + ", x2=" + this.x2 + "]";
	}
	
	@Override
	public void setCoordinateSystem(Coordinates relativeCoords) {
		super.setCoordinateSystem(relativeCoords);
		if (relativeCoords instanceof Coordinates3D) {
			coordinateSystem.setZ(((Coordinates3D) relativeCoords).getZ1());
		}
	}
	
	@Override
	public void setDefaultCoordSystem() {
		super.setDefaultCoordSystem();
		coordinateSystem.setZ(0);
	}
	
	public int getZ1() {
		return this.z1;
	}
	
	public int getRZ1() {
		return this.coordinateSystem.getZ() + this.z1;
	}
	
	public void setZ1(int z1) {
		this.z1 = z1;
	}
	
	public int getZ2() {
		return this.z2;
	}
	
	public int getRZ2() {
		return this.coordinateSystem.getZ() + this.z2;
	}
	
	public void setZ2(int z2) {
		this.z2 = z2;
	}
	
	public int getDepth() {
		return this.z2 - this.z1;
	}
	
	public void setDepth(int depth) {
		this.z2 = this.z1 + depth;
	}
}