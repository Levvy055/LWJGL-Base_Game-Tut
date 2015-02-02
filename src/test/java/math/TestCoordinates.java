package math;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.*;

import pl.grm.game.math.*;

public class TestCoordinates {
	private int	param1	= 5;
	private int	param2	= 10;
	private int	param3	= 30;
	private int	param4	= 40;
	
	@Test
	public final void testCoordinates1D() {
		Coordinates1D coords = new Coordinates1D(param1, param2);
		assertThat(coords.getX1(), is(equalTo(param1)));
		assertThat(coords.getWidth(), is(equalTo(param2 - param1)));
		coords.setWidth(param3);
		assertThat(coords.getX2(), is(equalTo(param1 + param3)));
	}
	
	@Test
	public final void testCoordinates2D() {
		Coordinates2D coords = new Coordinates2D(0, param1, 0, param2);
		assertThat(coords.getY1(), is(equalTo(param1)));
		assertThat(coords.getHeight(), is(equalTo(param2 - param1)));
		coords.setHeight(param3);
		assertThat(coords.getY2(), is(equalTo(param1 + param3)));
	}
	
	@Test
	public final void testCoordinates3D() {
		Coordinates3D coords = new Coordinates3D(0, 0, param1, 0, 0, param2);
		assertThat(coords.getZ1(), is(equalTo(param1)));
		assertThat(coords.getDepth(), is(equalTo(param2 - param1)));
		coords.setDepth(param3);
		assertThat(coords.getZ2(), is(equalTo(param1 + param3)));
	}
	
	@Test
	public final void testSetCoordSystem1D() {
		Coordinates1D coords1 = new Coordinates1D(param1, param2);
		Coordinates1D coords2 = new Coordinates1D(param3, param4);
		coords1.setCoordinateSystem(coords2);
		assertThat(coords1.getRX1(), is(equalTo(param1 + param3)));
		assertThat(coords1.getWidth(), is(equalTo(param2 - param1)));
	}
	
	@Test
	public final void testSetCoordSystem2D() {
		Coordinates2D coords1 = new Coordinates2D(param1, param2, param3, param4);
		Coordinates2D coords2 = new Coordinates2D(param3, param4, param2, param1);
		coords1.setCoordinateSystem(coords2);
		assertThat(coords1.getRX1(), is(equalTo(param1 + param3)));
		assertThat(coords1.getRY1(), is(equalTo(param2 + param4)));
		assertThat(coords1.getWidth(), is(equalTo(param3 - param1)));
		assertThat(coords1.getHeight(), is(equalTo(param4 - param2)));
	}
	
	@Test
	public final void testSetCoordSystem3D() {
		Coordinates3D coords1 = new Coordinates3D(param1, param2, param3, param4, param1, param4);
		Coordinates3D coords2 = new Coordinates3D(param3, param4, param2, param1, param2, param1);
		coords1.setCoordinateSystem(coords2);
		assertThat(coords1.getRX1(), is(equalTo(param1 + param3)));
		assertThat(coords1.getRZ1(), is(equalTo(param3 + param2)));
		assertThat(coords1.getWidth(), is(equalTo(param4 - param1)));
		assertThat(coords1.getDepth(), is(equalTo(param4 - param3)));
	}
	
	@Test
	public final void testDissolveRelativeCoordSystem() {
		Coordinates3D coords1 = new Coordinates3D(param1, param2, param3, param4, param1, param4);
		Coordinates3D coords2 = new Coordinates3D(param3, param4, param2, param1, param2, param1);
		coords1.setCoordinateSystem(coords2);
		coords1.setDefaultCoordSystem();
		assertThat(coords1.getRX1(), is(equalTo(param1)));
		assertThat(coords1.getRZ1(), is(equalTo(param3)));
		assertThat(coords1.getWidth(), is(equalTo(param4 - param1)));
		assertThat(coords1.getDepth(), is(equalTo(param4 - param3)));
	}
	
	@Test
	public final void testGetArea() {
		Coordinates3D coords1 = new Coordinates3D(param1, param2, param3, param4, param1, param4);
		assertThat(coords1.getArea(), is(equalTo(Math.abs((param4 - param1) * (param1 - param2)))));
	}
	
	@Test
	public final void testGetVolume() {
		Coordinates3D coords1 = new Coordinates3D(param1, param2, param3, param4, param1, param4);
		assertThat(coords1.getVolume(),
				is(equalTo(Math.abs((param4 - param1) * (param1 - param2) * (param4 - param3)))));
	}
}
