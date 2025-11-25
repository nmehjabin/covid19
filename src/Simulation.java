import java.util.ArrayList;

class Simulation {
    //this class handles all these variables value for the simulation
    ArrayList<Human> human;
    int num_infected_pple;
    int num_lucky_pple;
    int num_normal_pple;
    int MAX_STEPS;

    String env = "";
    float infect_rate_with_cloth_mask;
    float infect_rate_with_sur_mask;
    float infect_rate_with_N95_mask;

    int width;
    int height;

    //constructor
    //The constructor taking input of max of steps, environment type and the size of the environment
    Simulation(int ms, String environment, int w, int h) {
        human = new ArrayList<Human>();
        num_infected_pple = 0;
        MAX_STEPS = ms;
        // these numbers are taken from https://www.cdc.gov/mmwr/volumes/71/wr/mm7106e1.htm
        infect_rate_with_cloth_mask = 0.44F; // this needs to be backed by some data, may be CDC data 56% chance of being safe
        infect_rate_with_sur_mask = 0.34F; // 66 % chance of being safe
        infect_rate_with_N95_mask = 0.17F; // 83% change of safe
        this.env = environment;
        this.width = w;
        this.height = h;
        num_lucky_pple = 0;
    }

    //this method takes the parameter of how many steps you want to run the simulation
    //you can change it if you want
    void run(int sim_step) {

        //looping through Human arraylist
        for (Human h : this.human) {
            h.seperate(this);

            if (this.env == "kline" ){
                // controlling frequency of movement based on where people are.
                // how often you are going to update the location of a human
                // If you are in kline this is updated more frequently sim_step%15 == 0
                // because at kline people are moving more frequently
                if (sim_step%10 == 0){
                    h.update(this.env);
                }
            }
            if (this.env == "lab") {
                //in a lab, people move less, hence update location less frequently
                if (sim_step%80 == 0){
                    h.update(this.env);
                }

            }
            h.boundary(this);
        }
    }
}