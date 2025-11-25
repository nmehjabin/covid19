import java.util.*;
import java.io.*;
import java.io.FileWriter;

public class SimRunner {
    /*
    Give a min and max range, this function returns a
    random number between min and max. This is a helper function.
    Return type: float
    * */
    public static float getRandomNumber(int min, int max) {
        return (float) ((Math.random() * (max - min)) + min);
    }

    /*
    This is the main runner function.
    * */
    public static void main(String[] args) {

        // UNCOMMENT THESE FOR Kline environment
        //kline is large so the size is 1200x1200
       // int w = 1200;
       // int h = 1200;
        //String location = "kline";
        //int n = 40;


        // UNCOMMENT THESE FOR LAB environment
        // labs are smaller than kline so w and h is reflecting that.
        
        int w = 800;
        int h = 400;
        String location = "lab";
        int n = 20;


       String outFileName = "/Users/nadiamehjabin/Desktop/moderation_project/Final_Project/project_code/src/newdata" + location + "_result_with_Surgicalmask.tsv";
       // String outFileName = "/Users/nadiamehjabin/Desktop/moderation_project/Final_Project/project_code/src/newdata" + location + "_result_with_N95mask.tsv";

        //writing data file
        try {
            FileWriter fileWriter = new FileWriter(outFileName);
            String header = "steps" + "\t" + "infected" + "\t" + "normal" + "\t" + "lucky";
            fileWriter.write(header);
            fileWriter.write("\n");
            int simSteps = 20000;    //running for this step; it's changable

            Simulation s = new Simulation(simSteps, location, w, h);    //creating simualtion obj
            int steps_so_far = 0;
            for (int i = 0; i < n; i++) {
                // infected human
                if (i % 10 == 0) {
                    boolean inf = true;
                    boolean msk = false;
                    Human human = new Human(
                            getRandomNumber(1, s.width),
                            getRandomNumber(1, s.height),
                            inf,
                            msk,
                            null);
                    s.human.add(human);
                } else if (i % 4 == 0) {
                    // not infected and with mask
                    boolean inf = false;
                    boolean msk = true;

                    // CHANGE THIS AS NEEDED.
                    String mask_type = "Surgical";
                    //uncomment this if you want run for N95 mask
                    // String mask_type = "N95";

                    Human human = new Human(
                            getRandomNumber(1, s.width),
                            getRandomNumber(1, s.height),
                            inf,
                            msk,
                            mask_type
                    );
                    s.human.add(human);
                } else {
                    System.out.println(i);
                    // not infected and with no mask
                    boolean inf = false;
                    boolean msk = false;
                    Human human = new Human(
                            getRandomNumber(1, s.width),
                            getRandomNumber(1, s.height),
                            inf,
                            msk,
                            null
                    );
                    s.human.add(human);
                }
                s.num_normal_pple++;
                System.out.println(s.human.get(i).mask);
            }

            while (steps_so_far < simSteps) {
                s.run(steps_so_far);
                String outputLine = steps_so_far + "\t" + s.num_infected_pple + "\t" + s.num_normal_pple + "\t" + s.num_lucky_pple;
                System.out.println(outputLine);

                fileWriter.write(outputLine);
                fileWriter.write("\n");
                steps_so_far++;
            }


            fileWriter.close();
            //done writing the file and closing it

        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
