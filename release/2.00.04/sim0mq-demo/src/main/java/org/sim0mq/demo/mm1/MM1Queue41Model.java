package org.sim0mq.demo.mm1;

import java.io.Serializable;
import java.rmi.RemoteException;

import nl.tudelft.simulation.dsol.SimRuntimeException;
import nl.tudelft.simulation.dsol.formalisms.Resource;
import nl.tudelft.simulation.dsol.formalisms.flow.Delay;
import nl.tudelft.simulation.dsol.formalisms.flow.Generator;
import nl.tudelft.simulation.dsol.formalisms.flow.Release;
import nl.tudelft.simulation.dsol.formalisms.flow.Seize;
import nl.tudelft.simulation.dsol.formalisms.flow.StationInterface;
import nl.tudelft.simulation.dsol.formalisms.flow.statistics.Utilization;
import nl.tudelft.simulation.dsol.model.AbstractDSOLModel;
import nl.tudelft.simulation.dsol.simtime.SimTimeDouble;
import nl.tudelft.simulation.dsol.simtime.dist.DistContinuousSimTime;
import nl.tudelft.simulation.dsol.simtime.dist.DistContinuousSimulationTime;
import nl.tudelft.simulation.dsol.simulators.DEVSSimulatorInterface;
import nl.tudelft.simulation.dsol.statistics.SimTally;
import nl.tudelft.simulation.jstats.distributions.DistConstant;
import nl.tudelft.simulation.jstats.distributions.DistDiscreteConstant;
import nl.tudelft.simulation.jstats.distributions.DistExponential;
import nl.tudelft.simulation.jstats.streams.MersenneTwister;
import nl.tudelft.simulation.jstats.streams.StreamInterface;

/**
 * The M/M/1 example as published in Simulation Modeling and Analysis by A.M. Law &amp; W.D. Kelton section 1.4 and 2.4.
 * <p>
 * (c) copyright 2015-2020 <a href="http://www.simulation.tudelft.nl">Delft University of Technology </a>, the Netherlands. <br>
 * See for project information <a href="http://www.simulation.tudelft.nl">www.simulation.tudelft.nl </a> <br>
 * License of use: <a href="http://www.gnu.org/copyleft/lesser.html">Lesser General Public License (LGPL) </a>, no warranty.
 * @version 2.0 21.09.2003 <br>
 * @author <a href="https://www.linkedin.com/in/peterhmjacobs">Peter Jacobs </a>
 */
public class MM1Queue41Model extends AbstractDSOLModel.TimeDouble<DEVSSimulatorInterface.TimeDouble>
{
    /** The default serial version UID for serializable classes. */
    private static final long serialVersionUID = 1L;

    /** tally dN. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    SimTally.TimeDouble dN;

    /** tally qN. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    SimTally.TimeDouble qN;

    /** utilization uN. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    Utilization<Double, Double, SimTimeDouble> uN;

    /** PARAMETER iat. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    public double iat = Double.NaN;

    /** PARAMETER serviceTime. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    public double serviceTime = Double.NaN;

    /** PARAMETER seed. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    public long seed = 1;

    /**
     * Construct the model.
     * @param simulator the simulator
     */
    public MM1Queue41Model(final DEVSSimulatorInterface.TimeDouble simulator)
    {
        super(simulator);
    }

    /** {@inheritDoc} */
    @Override
    public final void constructModel() throws SimRuntimeException
    {
        if (Double.isNaN(this.iat))
        {
            throw new SimRuntimeException("Parameter iat not defined for model");
        }
        if (Double.isNaN(this.serviceTime))
        {
            throw new SimRuntimeException("Parameter servicetime not defined for model");
        }

        StreamInterface defaultStream = new MersenneTwister(this.seed);

        // The Generator
        Generator.TimeDouble generator = new Generator.TimeDouble("generator", getSimulator(), Object.class, null);
        generator.setInterval(new DistContinuousSimulationTime.TimeDouble(new DistExponential(defaultStream, this.iat)));
        generator.setStartTime(new DistContinuousSimTime.TimeDouble(new DistConstant(defaultStream, 0.0)));
        generator.setBatchSize(new DistDiscreteConstant(defaultStream, 1));
        generator.setMaxNumber(1000);

        // The queue, the resource and the release
        Resource<Double, Double, SimTimeDouble> resource = new Resource<>(getSimulator(), "resource", 1.0);

        // created a resource
        StationInterface.TimeDouble queue = new Seize.TimeDouble("queue", getSimulator(), resource);
        StationInterface.TimeDouble release = new Release.TimeDouble("release", getSimulator(), resource, 1.0);

        // The server
        DistContinuousSimulationTime.TimeDouble serviceTimeDistribution =
                new DistContinuousSimulationTime.TimeDouble(new DistExponential(defaultStream, this.serviceTime));
        StationInterface.TimeDouble server = new Delay.TimeDouble("delay", getSimulator(), serviceTimeDistribution);

        // The flow
        generator.setDestination(queue);
        queue.setDestination(server);
        server.setDestination(release);

        // Statistics
        try
        {
            this.dN = new SimTally.TimeDouble("d(n)", getSimulator(), queue, Seize.DELAY_TIME);
            this.qN = new SimTally.TimeDouble("q(n)", getSimulator(), queue, Seize.QUEUE_LENGTH_EVENT);
            this.uN = new Utilization<Double, Double, SimTimeDouble>("u(n)", getSimulator(), server);
        }
        catch (RemoteException exception)
        {
            exception.printStackTrace();
        }
    }

    /** {@inheritDoc} */
    @Override
    public Serializable getSourceId()
    {
        return "MM1Queue41Model";
    }

}
