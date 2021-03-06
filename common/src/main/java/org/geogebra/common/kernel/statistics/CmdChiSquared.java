package org.geogebra.common.kernel.statistics;

import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.StringTemplate;
import org.geogebra.common.kernel.arithmetic.BooleanValue;
import org.geogebra.common.kernel.arithmetic.Command;
import org.geogebra.common.kernel.commands.CommandProcessor;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoFunction;
import org.geogebra.common.kernel.geos.GeoNumberValue;
import org.geogebra.common.main.MyError;

/**
 * Chi Squared Distribution
 */
public class CmdChiSquared extends CommandProcessor {

	/**
	 * Create new command processor
	 * 
	 * @param kernel
	 *            kernel
	 */
	public CmdChiSquared(Kernel kernel) {
		super(kernel);
	}

	@Override
	public GeoElement[] process(Command c) throws MyError {
		int n = c.getArgumentNumber();

		GeoElement[] arg;

		arg = resArgs(c);

		BooleanValue cumulative = null; // default for n=2
		switch (n) {
		case 3:

			if (!arg[1].isGeoFunction()
					|| !((GeoFunction) arg[1]).toString(
							StringTemplate.defaultTemplate).equals("x")) {
				throw argErr(app, c.getName(), arg[1]);
			}

			if (arg[2].isGeoBoolean()) {
				cumulative = (BooleanValue) arg[2];
			} else
				throw argErr(app, c.getName(), arg[2]);

			// fall through
		case 2:
			if (arg[0] instanceof GeoNumberValue) {
				if (arg[1].isGeoFunction()
						&& ((GeoFunction) arg[1]).toString(
								StringTemplate.defaultTemplate).equals("x")) {

					AlgoChiSquaredDF algo = new AlgoChiSquaredDF(cons,
							c.getLabel(), (GeoNumberValue) arg[0], cumulative);
					return algo.getResult().asArray();

				} else if (arg[1] instanceof GeoNumberValue) {

					AlgoChiSquared algo = new AlgoChiSquared(cons,
							c.getLabel(), (GeoNumberValue) arg[0],
							(GeoNumberValue) arg[1]);
					GeoElement[] ret = { algo.getResult() };
					return ret;
				} else
					throw argErr(app, c.getName(), arg[1]);

			}
			throw argErr(app, c.getName(), arg[0]);

		default:
			throw argNumErr(app, c.getName(), n);
		}
	}
}
