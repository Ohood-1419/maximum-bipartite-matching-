// Program for Maximum Matching in Bipartite BTP_Graph

import java.util.*;
import java.lang.*;
import java.io.*;

public class BTP_Graph
{
    // M = number of applicants
    static final int M = 6; 
    // N = number of Hospitals
    static final int N = 6; 
    //array to store if node visited and matching or not
    static boolean[] visit={false, false, false, false, false, false};
    //store name of Hospitals
    static String  hos[]={"King AbdelazizUniversity" ,"King Fahd",
                          "East Jeddah","King Fahad Armed Forces " ,
                          "King Faisal Specialist" ,"Ministry of National Guard "};
    //store name of applicants 
    static String  app[]={"Ahmed","Mahmoud","Eman","Fatimah","Kamel","Nojood"};
    
    
    public static void main (String[] args)throws java.lang.Exception
    {
        // Let us create a bpGraph shown
        // true = app is important to this hospital
        boolean bpGraph[][] = new boolean[][]{
            //   KAU    KF    EJ     KFAF   KFS   MNG
                //Ahmed
                {true, true, false, false, false, false},
                //mahmoud
                {false, false, false, false, false, true},
                //Eman
                {true, false, false, true, false, false},
                //fatima
                {false, false, true, false, false, false},
                // keamel
                {false, false, false, true, true, false},
                //nojood
                {false, false, false, false, false, true}};
        
        BTP_Graph m = new BTP_Graph();
        System.out.println("\n==> Maximum number of applicants that can"+
                " get a job is --> "+m.maxBPM(bpGraph));
    }
    
    // recursive function get true in return when matching happend to n
    boolean bpm(boolean bpGraph[][], int a, int matchA[])
    {
        // Try every Hospitals N one by one by loop
        for (int h = 0; h < N; h++)
        {
            // If applicant a want to be embloyee 
            // in Hospital h && h is not visited (not matched)
            //System.out.println(">> before: "+v+", "+visit[v]);
            if (bpGraph[a][h] && !visit[h])
            {              
                // make h as visited (matched with a)
                visit[h] = true;
                //System.out.println("  ===> after: "+v+", "+visit[v]);
                // If Hospital 'h' is not assigned to applicant
                //  OR previously assigned applicant for Hospital'h'
                // (which is matchA[h]) has an alternate Hospital available.
                // Since h is marked as visited in the
                // above line, matchR[h] in the following
                // recursive call will not get Hospital'h' again


                if (matchA[h] < 0|| bpm(bpGraph, matchA[h], matchA))
                {
                    //maching applicant and Hspital
                    matchA[h] = a;
                    return true;
             //   }
                }
            }
        }
        return false;
    }

    // Returns maximum number
    // of matching from M to N
    int maxBPM(boolean bpGraph[][])
    {
        // An array to store if applicants assigned to Hospital.
        // value of matchR[i] =  applicant number assigned to Hospital ,
        // value -1 = nobody is assigned.
        int matchA[] = new int[N];

        // Initially all applicants not assign  
        // and all hospital are available
        for(int i = 0; i < N; ++i)
            matchA[i] = -1;

        // Count number of Hospital assigned to applicants
        int total_Hos = 0;
        //loop for applicants
        for (int i = 0; i < M; i++)
        {
            // Mark all Hospital as NOT Visited for next applicant.
            boolean seen[] =new boolean[N] ;
                               
            System.out.println("---------------------------<<< Itreation("+i+") >>>---------------------------");
            
            for(int j = 0; j < N; ++j) {
                seen[j] = false;
                //System.out.println("**"+matchA[j]);
                if (matchA[j] > -1) {

                    System.out.println(">_"+(hos[j]) + " Hospital has a applicant name : " + (app[matchA[j]]));


                } else {

                    System.out.println((hos[j]) + " Hospital has NO applicant(s)");
                }


            }

            // Find if the applicant (a) can be employee in hospital
            if (bpm(bpGraph, i, matchA))
                total_Hos++;
            System.out.println("");
        }
        return total_Hos;
    }

    
    
}

