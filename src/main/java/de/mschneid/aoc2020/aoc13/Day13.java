package de.mschneid.aoc2020.aoc13;

import java.util.ArrayList;
import java.util.List;

public class Day13 {


        public void solve(List<String> inputStrs)
        {



            // PART 2 uses the Chinese Remainder Theorem... I don't know it though...
            // The below was what I got and it works, but is not based off the Chinese Remainder Theorem
            // and is therefore not efficient enough to solve the problem quick enough
            List<Integer> busIDs = new ArrayList<>();
            boolean complete = false;
            int t = 0;
            int largestIndex = 0;
            for(String id : inputStrs.get(1).split(","))
            {
                if(id.equals("x"))
                    busIDs.add(0);
                else
                    busIDs.add(Integer.parseInt(id));

                if(busIDs.get(largestIndex) < busIDs.get(busIDs.size() - 1))
                {
                    largestIndex = busIDs.size() - 1;
                }
            }

            while(!complete)
            {
                t += busIDs.get(largestIndex);
                complete = true;
                for(int i = 0; i < busIDs.size(); i++)
                {
                    if(busIDs.get(i) == 0)
                        continue;

                    if((t - largestIndex + i) % busIDs.get(i) != 0)
                    {
                        complete = false;
                        break;
                    }
                }
            }

            System.out.println(t - largestIndex);
        }
}
