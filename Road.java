import java.util.Scanner;

class Road{

    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter number of cycles: ");
        int cycles = keyboard.nextInt();

        System.out.print("Enter number of segments: ");
        int nMax = keyboard.nextInt();

        System.out.print("Initialize max number of allowed entry cars(k): ");
        int k = keyboard.nextInt();

        System.out.print("Enter percent of ready cars: ");
        int percent = keyboard.nextInt();

        Segment[][] road = new Segment[nMax][nMax];
        String[] names = new String[nMax];

        keyboard.nextLine();

        int indexOfNames = nMax - 1;

        //Create Segments and Entries
        for (int i = 0; i < nMax; i++){

            System.out.print("Enter name of segment number "+i+": ");
            names[indexOfNames] = keyboard.nextLine();

            for (int j = nMax - 1; j >= 0; j--){

                if (j == 0){
                    road[i][j] = new Segment(true, k, percent); //Creating Entry
                } else {
                    road[i][j] = new Segment(); //Creating segment
                }
            }

            indexOfNames -= 1;
        }

        System.out.println("ROAD OPERATING !!!!");

        for (int n = 0; n < cycles; n++){

            System.out.println("====================CYCLE " + (n+1)+"====================");
        
            for (int i = nMax - 1; i >= 0; i--){

                for (int j = nMax - 1; j >= 0; j--){

                    int entryCars = 0; //Cars for entry

                    if (j == 0) { //if Entry

                        //Caclulate entry cars
                        entryCars = road[i][j].enter(); //calculate entry cars

                        //pass the entry cars to segment and store the cars that didnt make it
                        int cars_dont_pass = road[i][j].pass(entryCars);
                        //calculate and store the cars that passed
                        int cars_passed = entryCars - cars_dont_pass;

                        //if the cars that are waiting to enter
                        //are more than the cars than entered
                        if (road[i][j].carsWaitingEntry > cars_passed) {
                            System.out.print("\n!--Delays at the entry of " + names[i]+"--!\n");
                        }

                        //if its not the first segment then pass to the entry
                        //the cars of the previous segment
                        //and then exit some cars
                        if(i != 0){
                            //store the currentCars before passing new ones
                            int beforePass = road[i][j].currentCars;

                            //pass the cars and store cars that didnt make it
                            int carsWaiting =  road[i][j].pass(road[i-1][nMax-1].currentCars);

                            //Cars that passed is the difference between the currentCars
                            //and the currentCars before making the pass
                            int carsThatPassed = road[i][j].currentCars - beforePass;

                            //if the cars that didnt make it are more than the cars that passed
                            if (carsWaiting > carsThatPassed) {

                                //Print that there is a delay
                                System.out.print("\n!--Delays after the entry of " + names[i]+"--!\n");
                            }

                            //exit some cars
                            road[i][j].exit();
                        }
                        
                        //if the cars that are waiting to enter are more then 0
                        if (road[i][j].carsWaitingEntry > 0) {
                            System.out.print("Waiting to enter: -1 -> ");
                        }

                    } else if (!(i == nMax - 1 && j == nMax - 1)){ //if Segment excluding final exit

                        //if the entry cars are not 0 pass them
                        if (entryCars != 0) {
                            road[i][j].pass(entryCars);
                        }

                        //pass the cars from prev segment and store them
                        int carsWaiting = road[i][j].pass(road[i][j-1].currentCars);

                        //make the currentCars of prev segment equal to the cars that didnt make it
                        road[i][j-1].currentCars = carsWaiting;

                    } else { //Final exit

                        //pass the cars to final exit
                        road[i][j].pass(road[i][j-1].currentCars); 

                        //remove them
                        road[i][j].exit();
                    }
                        
                }

                for (int j = 0; j < nMax; j++){
                  System.out.print(names[i] + (j+1) +": " + road[i][j].currentCars+ " | ");
                }  
                
                System.out.println();
            }
        }

        keyboard.close();
    }
}