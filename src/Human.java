import java.lang.Math;
import java.util.ArrayList;

class Human {
    Vector location;
    Vector velocity;
    Vector acceleration;
    float r ; //radius of the object
    float maxforce;  //max steering force
    float maxspeed;  //maxspeed
    // a boolean that keep track of if a human is infected or not
    boolean infected;

    boolean mask;
    String mask_type;

    int step_count =0 ;

    //constructor
    Human(float x, float y, boolean infectedFlag, boolean mask_flag, String msk_type) {
        location = new Vector(x, y);
        velocity = new Vector (getRandomNumber(-9, 9), getRandomNumber(-9, 9) );
        acceleration = new Vector (0, 0);
        r = 8;
        maxforce= 0.5F;
        maxspeed= 3;

        infected = infectedFlag;
        mask = mask_flag;
        mask_type = msk_type;
    }

    public float getRandomNumber(int min, int max) {
        return (float) ((Math.random() * (max - min)) + min);
    }

    //method to update position which takes environment parameter
    //movement notion is different in different environment
    public void update(String env) {
        if (env.equals("kline")) {
            velocity = velocity.mult(8);  //increasing velocity
        }
        if (env.equals("lab")) {
            velocity = velocity.mult(1.5F);  //slowing down human's velocity
        }

        velocity.add(acceleration);
        velocity.limit(maxspeed); //limit speed
        location.add(velocity);
        acceleration.mult(0); // Reset acceleration to 0 each cycle
    }

    //wrap within boundary
    public void boundary(Simulation s) {
        if (location.x> s.width-r) {
            location.x = s.width-r;
            velocity.x *= -1;
        } else if (location.x<r) {
            location.x =r;
            velocity.x*= -1;
        } else if (location.y > s.height-r) {
            location.y=s.height-r;
            velocity.y *=-1;
        } else if (location.y<r) {
            location.y =r;
            velocity.y *= -1;
        }
    }

    // Newton’s second law; we could divide by mass if we wanted.
    public void applyForce(Vector force) {
        acceleration.add(force);
    }

    public float distance(Vector v1, Vector v2) {
        //this method calculates distance
        float dx = v1.x - v2.x;
        float dy = v1.y - v2.y;
        return (float) Math.sqrt(dx*dx + dy*dy);
    }

    public Vector subtract(Vector v1, Vector v2) {
        //calculating target vector
        Vector target = new Vector(v1.x - v2.x, v1.y - v2.y);
        return target;
    }

    //this method takes three parameter: human non-infected, chance of their safety and the
    //simulation s which takes location, steps and the size of the environment
    public void infectHumanWithSurgMask(Human normal_h, float chance_of_safety, Simulation s) {
        if (chance_of_safety < s.infect_rate_with_sur_mask) {
            //checks the condition for the surgical mask to get infected
            normal_h.infected = true;
            s.num_infected_pple ++;    //increasing infected people
            s.num_normal_pple -- ;     //healthy people decreasing
        } else {
            //there will a lucky case where the human is not infected
            System.out.println("LUCKY!! got luckily and didnt get inf");
            s.num_lucky_pple++;
        }
    }

    //this method takes three parameter: human non-infected, chance of their safety and the
    //simulation s which takes location, steps and the size of the environment

    public void infectHumanWithN95Mask(Human normal_h, float chance_of_safety, Simulation s) {
        if (chance_of_safety < s.infect_rate_with_N95_mask) {
            //checks the condition for the N95 mask to get infected
            normal_h.infected = true;
            s.num_infected_pple ++;      //increasing infected people
            s.num_normal_pple -- ;       //healthy people decreasing
        } else {
            //there will a lucky case where the human is not infected
            System.out.println("LUCKY!! got luckly and didnt get inf");
            s.num_lucky_pple++;
        }
    }

    //this method controls how the human is moving and checking the distance from one and other

    public void seperate(Simulation s) {
        ArrayList<Human> all_human = s.human;
        float desiredSeperation = r*2.1F; //how close is too close r*2.6 ~= 6 feet

        Vector sum= new Vector();
        int count =0;

        for (Human other : all_human) {
            // first we have to check if the other human is not colliding with itself
            // 'this' is the human who is calling the 'seperate' method
            if (this!=other) {

                //distance between me(human) to other human
                float d = distance(this.location, other.location);

                //if the distance is greater than 0 and less than our radius dist
                if ((d > 0 ) && (d < desiredSeperation)) {

                    if (this.infected && !other.infected) {
                        float chance_of_safety = (float)Math.random();

                        // the blue ones are colliding with the red ones! that
                        // means the code is coming here.

                        //calling the methods here
                        if ((other.mask == true) &&  other.mask_type == "Surgical") {
                            infectHumanWithSurgMask(other, chance_of_safety, s );
                        }
                        if ((other.mask == true) &&  other.mask_type == "N95") {
                            infectHumanWithN95Mask(other, chance_of_safety, s );
                        }
                        if ((other.mask == false) && (other.infected == false)) {
                            // person is not wearing mask, and it is not infected
                            //and has been close to an infected person. SAD :(
                            other.infected = true;
                            s.num_infected_pple ++;
                            s.num_normal_pple -- ;
                        }

                    }
                    //calculate vector pointing away from neighbor
                    Vector diff = subtract(location, other.location);
                    diff.normalize();
                    diff.div(d);  //weight by distance
                    sum.add(diff);
                    count++;  //keep track of how many
                    d = 0;
                }
            }
            if (count > 0) {
                sum.div(count);

                // Scale average to maxspeed
                // (this becomes desired).
                sum.setMag(maxspeed);

                // Reynolds’s steering formula
                Vector steer = subtract(sum, velocity);
                steer.limit(maxforce);

                // Apply the force to the human’s
                // acceleration.
                applyForce(steer);
            }
        }
    }
}