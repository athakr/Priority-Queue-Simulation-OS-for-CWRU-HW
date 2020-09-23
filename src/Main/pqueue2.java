package Main;
import java.util.*;
import java.lang.*;
public class pqueue2 {
	
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		// TODO Auto-generated method stub
		//1-50 is priority 1
		//51 < x is priority 2
		Scanner input = new Scanner(System.in);
		ArrayList<Integer> processes = new ArrayList<Integer>();
		for(int i = 0; i < 100; i++) {
			processes.add(i +1);
		}
		int p1_end = 50;
		int p2_end = 99;
		long totalresptime = 0;
		long totcontextswitchprice = 0;
		for(int i = 0; i < processes.size(); i++) {
			long processArvtime = System.nanoTime();
			if(i % 10 ==0) {
				System.out.println("New job processed");
				System.out.println("Add a process (number)");
				
				int userin = input.nextInt();
				if(i <50) {
					if(userin > processes.get(i)) {
						if(userin < p1_end) {
							processes.add(p1_end, userin);
							p1_end++;
							p2_end++;
						}
						else {
						processes.add(p2_end+1,userin);
						p2_end++;
						}
					}
					else {
						totcontextswitchprice = totcontextswitchprice + System.nanoTime() - processArvtime;
					processes.add(i, userin);
					p1_end++;
					p2_end++;
					}
				}
				else {
					if(userin < processes.get(i)) {
						totcontextswitchprice = totcontextswitchprice + System.nanoTime() - processArvtime;
						processes.add(i,userin);
						p2_end++;
					}
					else {
						processes.add(p2_end, userin);
						p2_end++;
					}
						
				}
			}
			long processStart   = System.nanoTime();
			System.out.println(processes.get(i));
			long resptime = processStart - processArvtime;
			totalresptime = totalresptime + resptime;
		}
		long avgresptime = totalresptime/processes.size();
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		double burstcomptime = totalTime/1000000000;
		double contextswitchperc = (double)totcontextswitchprice/(double)totalTime * 100;
		System.out.println("Avg burst completion time is " + burstcomptime/processes.size() + " seconds");
		System.out.println("Avg response time is " + avgresptime + " nano seconds");
		System.out.println(totcontextswitchprice);
		System.out.println(totalTime);
		System.out.println("Context switch percentage is " + contextswitchperc + "%");
	}
	
}