import java.text.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * A class that simulates a train line with passengers.
 *
 * @author Robert Aroutiounian
 * @version 10/9/2015
 */
public class TrainSimulation
{
    // an array that will hold all stations
    private Station[] allStations;
    // a queue that will hold all passengers, so we can print statistics
    // when the simulation is over
    private Queue<Passenger> allPassengers;
    // a queue that will hold all trains
    private Queue<Train> allTrains;
    // keeps track of the number of trains en route
    private int trainCount;
    // total number of passengers created
    private int passengersCreated;
    // total number of passengers on the trains
    private int passengersOnTrains;
    // total number of passengers off the trains
    private int passengersDelivered;
    // number of stations for the simulation
    private final int STATIONS = 10;
    // frequency of trains departing from the station 0
    private final int TRAIN_INTERVAL = 10;
    // max number of passengers per train
    private final int TRAIN_CAPACITY = 20;
    // simulation time
    private final int DURATION = 50;
    // min number of simulation ticks between two stations
    private final int MIN_TIME_TO_NEXT_STATION = 5;
    // max number of passengers to be randomly generated in one simulation tick
    private final int MAX_NUM_OF_PASSENGERS = 10;

    public Random generator;

    public TrainSimulation()
    {
        // create an array that will hold all stations
        this.allStations = new Station[STATIONS];
        // create a queue that will hold all trains
        this.allTrains = new LinkedBlockingQueue<>();
        // create a queue that will hold all passengers
        this.allPassengers = new LinkedBlockingQueue<>();
        // initialize counters
        this.trainCount = 0;
        this.passengersCreated = 0;
        this.passengersOnTrains = 0;
        this.passengersDelivered = 0;
        // create Random object to be used for generating random values
        this.generator = new Random();
        // generate all stations
        generateStations();
    }

    public void generateStations()
    {
        // Step #1

        // fill the allStation array with Station objects where the value
        // of "time to next station" is randomly generated.

        // for each created station print the station's "time to next"


        for (int i = 0; i < this.allStations.length; i++)
        {
            this.allStations[i] = new Station(this.generator.nextInt(this.STATIONS - this.MIN_TIME_TO_NEXT_STATION) + this.MIN_TIME_TO_NEXT_STATION);
            System.out.println("Created station " + i + " time to next is " + this.allStations[i].getTimeToNextStation());
        }
        System.out.println("--> Successfully created " + STATIONS + " stations");
    }

    public void startNewTrain(int simulationTime)
    {
        if ((simulationTime % TRAIN_INTERVAL) == 0)
        {
            // Step #2
            // create new train object and add it to the allTrains queue

            // increment the train count by 1
            this.allTrains.add(new Train(TRAIN_CAPACITY));
            this.trainCount++;
        }
    }

    public void generatePassengers(int clock)
    {
        // randomly generate number of new passengers
        int newPassengers = this.generator.nextInt(MAX_NUM_OF_PASSENGERS + 1);

        // Step #3
        // create the calculated number of passenger objects. For each new passenger
        // randomly generate the start station and the destination station. Remember
        // that the start station must be smaller than the destination station

        // add each passenger to the allPassengers queue and increment the number
        // of passengers created - you will need this information for the statistics

        // add each passenger to its appropriate start station - use allStations array

        int destination;
        int start;
        for (int i = 1; i <= newPassengers; i++)
        {
            destination = this.generator.nextInt(STATIONS - 1) + 1;
            start = this.generator.nextInt(destination);
            Passenger rider = new Passenger(start, destination, clock);
            this.allPassengers.add(rider);
            this.passengersCreated++;
            this.allStations[start].addPassenger(rider);
            System.out.println("\tCreated passenger at station " + start + " heading to " + destination);
        }
    }

    public void moveTrains(int clock)
    {
        // Move each train
        int trainsToCheck = this.trainCount;
        Train train;

        for (int i = 0; i < trainsToCheck; i++)
        {
            // Step #4
            // 1. remove train from the allTrains queue
            // 2. move the train (see the Train class)
            // 3. if the train's time to the next station is 0 the train is at the station
            //    a. unload the passengers from the train
            //    b. load the waiting passengers on the train
            //    c. update the passenger counters accordingly
            //    d. update train's next station data (see the Trains class)
            // 4. if the train's next station is not the final station add the train
            //         back to the queue.
            //    Otherwise decrement the count of the trains


            //removing trains
            train = this.allTrains.remove();
            train.move();
            int unloaded;
            int loaded;
            if (train.timeToNext() == 0)
            {
                unloaded = train.unloadPassengers(train.nextStation());
                this.passengersDelivered += unloaded;

                this.passengersOnTrains -= unloaded;

                loaded = train.loadPassengers(this.allStations[train.nextStation()], clock);
                this.passengersOnTrains += loaded;

                train.updateStation(this.allStations[i].getTimeToNextStation());
            }
            if (train.timeToNext() > 0)
            {
                this.allTrains.add(train);
            }
            else
            {
                this.trainCount--;
            }
        }
        //THE AVERAGE WAIT TIME FOR PASSENGERS SHOULD BE BETWEEN 8.5 AND 14
    }

    public void report(int simulationTime)
    {
        int passengersWaiting = this.passengersCreated - this.passengersOnTrains - this.passengersDelivered;

        System.out.println("-->Time Marker " + simulationTime + "\t passengers waiting: " + passengersWaiting +
                "\t passengers on trains: " + this.passengersOnTrains);
        System.out.println();
    }

    public void finalReport(int clock)
    {
        DecimalFormat df = new DecimalFormat("#0.00");
        System.out.println("*****************  Final Report  ***************");
        System.out.println("The total number of passengers is " + this.passengersCreated);
        System.out.println("The number of passengers currently on a train " + this.passengersOnTrains);
        System.out.println("The number of passengers delivered is " + this.passengersDelivered);
        int passengersWaiting = passengersCreated - passengersOnTrains - passengersDelivered;
        System.out.println("The number of passengers waiting is " + passengersWaiting);

        int waitBoardedSum = 0;
        Passenger passenger = null;
        for (int i = 0; i < this.passengersCreated; i++)
        {
            passenger = this.allPassengers.poll();
            if (passenger.boarded())
            {
                waitBoardedSum += passenger.waitTime(clock);
            }
        }

        System.out.print("The average wait time for passengers that have boarded is ");
        System.out.println(df.format((double) waitBoardedSum / (this.passengersOnTrains + this.passengersDelivered)));
    }

    public static void main(String args[])
    {
        System.out.println("**************  TRAIN SIMULATION  **************\n");
        TrainSimulation simulator = new TrainSimulation();

        System.out.println("***********  Starting the simulation  **********\n");
        for (int clock = 0; clock < simulator.DURATION; clock++)
        {
            simulator.startNewTrain(clock);
            simulator.generatePassengers(clock);
            simulator.moveTrains(clock);
            simulator.report(clock);
        }
        simulator.finalReport(simulator.DURATION);
    }
}
