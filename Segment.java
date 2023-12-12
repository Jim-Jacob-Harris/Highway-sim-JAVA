import java.util.Random;

public class Segment {

    int nMax;

    // --- Max capacity of cars per segment --- 
    final int CAPACITY = 20;

    // --- Random cars spawn at entry ---
    final int generateCarEntry = 5;

    //Variables for Entry 
    boolean isEntry = false;
    int readyCars = 0;
    int k;
    int entryCars = 0;
    int percent;
    int entryCapacity;
    int carsWaitingEntry;

    //Variables for Segments
    int currentCars;
    
    public Segment(){

    }

    public Segment(boolean isEntry, int k, int percent) {
        this.isEntry = isEntry;
        this.k = k;
        this.percent = percent;
    }

    //Pass(), used for passing cars AND for entry cars
    public int pass(int carsWaiting){
        Random rand = new Random();

        int cars_wanting_to_enter = rand.nextInt(carsWaiting+1); //Random cars enter-pass

        //if the current cars plus the cars that want to enter-pass
        //are more than capacity
        if (currentCars + cars_wanting_to_enter > CAPACITY) {

            //calculate the cars that can enter (cars that leave)
            int cars_that_left = CAPACITY - currentCars;

            //remove the cars that left from the cars that want to enter-pass
            cars_wanting_to_enter -= cars_that_left;

            //make the current cars equal to capacity
            currentCars = CAPACITY; 

            //and make the cars waiting minus the cars that left
            carsWaiting -= cars_that_left;
        } else {
            //add the cars that want to enter to the current cars
            currentCars += cars_wanting_to_enter;
            //and remove them from the cars waiting to enter-pass
            carsWaiting -= cars_wanting_to_enter;
            cars_wanting_to_enter = 0;
        }
        
        //return the cars waiting to enter-pass
        return carsWaiting;
    }

    //Enter generates new cars | It only increases-decreases K
    public int enter(){
        Random rand = new Random();
        entryCars = rand.nextInt(generateCarEntry);

        entryCapacity = k + k * 2;

        if (entryCars >= entryCapacity){
            increase_k();

            int cars_dont_enter = entryCars - entryCapacity;

            //carsWaitingEntry += cars_dont_enter;

            /*
            !!---- IMPORTANT MESSEGE ----!!

            edw kanonika, mallon, thelei += kai oxi sketo =,
            diladi kathe fora na min kanoun "reset" ta amaksia pou perimenoun na mpoun
            alla emena mou aresan ta apotelesmata kalutera etsi !!
            an thelete kante uncomment tin panw grammi kai comment tin kato gia na deite !!

            !!---- END OF IMPORTANT MESSAGE ----!!
             */
            carsWaitingEntry = cars_dont_enter; 

            entryCars -= cars_dont_enter;

        } else {
            decrease_k();
        }

        return entryCars; //return the entry cars and use pass() on the segment that it was called on
    }

    public void increase_k(){
        k += 1;
    }

    public void decrease_k(){
        if (k > 0){
            k -= 1;
        }
    }

    public void exit(){

        //exit percent of cars
        readyCars = (int)((percent / 100) * currentCars);
        currentCars -= readyCars;
    }
}
