import static org.junit.Assert.*;

import org.junit.Test;



public class Tests {

	@Test
	public void test() {

		Schedule schedule = new Schedule();
		schedule.insert(8); //adds job 0 with time 8
		Schedule.Job j1 = schedule.insert(3); //adds job 1 with time 3
		schedule.insert(5); //adds job 2 with time 5
		assertEquals(schedule.finish(), 8);
		schedule.get(0).requires(schedule.get(2)); //job 2 must precede job 0
		assertEquals(schedule.finish(), 13);

		schedule.get(0).requires(j1); //job 1 must precede job 0
		assertEquals(schedule.finish(), 13);

		schedule.get(0).start(); //should return 5
		assertEquals(schedule.get(0).start(), 5);
		
		j1.start(); //should return 0
		schedule.get(2).start(); //should return 0
		assertEquals(schedule.get(2).start(), 0);
		j1.requires(schedule.get(2)); //job 2 must precede job 1
		assertEquals(schedule.finish(), 16);

		assertEquals(schedule.get(0).start(), 8);
		assertEquals(schedule.get(1).start(), 5);
		assertEquals(schedule.get(2).start(), 0);
		schedule.get(1).requires(schedule.get(0)); //job 0 must precede job 1 (creates loop)
		assertEquals(schedule.finish(), -1);
		
		assertEquals(schedule.get(0).start(), -1);
		assertEquals(schedule.get(1).start(), -1);
		assertEquals(schedule.get(2).start(), 0);
		
	}
	@Test
	public void test2() {
		Schedule s = new Schedule();
		
		Schedule.Job j1 = s.insert(1);
		Schedule.Job j2 = s.insert(2);
		Schedule.Job j3 = s.insert(3);
		Schedule.Job j4 = s.insert(4);
		
		j1.requires(j2);
		j2.requires(j3);
		j2.requires(j4);
		
		assertEquals(j1.start(), 6);
		assertEquals(j2.start(), 4);
		assertEquals(j3.start(), 0);
		assertEquals(j4.start(), 0);
		assertEquals(s.finish(), 7);
		
		Schedule.Job j5 = s.insert(5);
		assertEquals(s.finish(), 7);

		j4.requires(j1);
		assertEquals(j5.start(), 0);
		assertEquals(j4.start(), -1);

	}
	
	@Test
	public void test3() {
		Schedule s = new Schedule();
		
		Schedule.Job j1 = s.insert(1);
		Schedule.Job j2 = s.insert(2);
		Schedule.Job j3 = s.insert(3);
		Schedule.Job j4 = s.insert(4);
		
		
		j1.requires(j2);
		j2.requires(j3);
		j2.requires(j4);
		
		assertEquals(j1.start(), 6);
		assertEquals(j2.start(), 4);
		assertEquals(j3.start(), 0);
		assertEquals(s.finish(), 7);
		assertEquals(s.finish(), 7);

		j4.requires(j2);
		assertEquals(s.finish(), -1);
		assertEquals(j1.start(), -1);
		
	}
	@Test
	public void test4() {
		Schedule s = new Schedule();
		
		Schedule.Job j1 = s.insert(1);
		Schedule.Job j2 = s.insert(2);
		Schedule.Job j3 = s.insert(3);
		Schedule.Job j4 = s.insert(4);
		
		
		j1.requires(j2);
		j2.requires(j3);
		j2.requires(j4);
		j4.requires(j2);
		j3.requires(j4);
		
		assertEquals(s.finish(), -1);
		assertEquals(j1.start(), -1);
		
	}
	@Test 
	public void test5() {
		Schedule s = new Schedule();
		
		Schedule.Job j1 = s.insert(1);
		Schedule.Job j2 = s.insert(2);
		Schedule.Job j3 = s.insert(3);
		Schedule.Job j4 = s.insert(4);
		Schedule.Job j100 = s.insert(100);
		Schedule.Job j1000 = s.insert(1000);

		j1.requires(j2);
		j2.requires(j3);
		j2.requires(j4);
		j2.requires(j1000);
		j2.requires(j100);
		
		s.finish();
		
		Schedule s2 = new Schedule();
		assertEquals(0, s2.finish());
	}
	@Test
	public void test6() {
		Schedule s = new Schedule();
		assertEquals(0, s.finish());
		Schedule.Job j1 = s.insert(1);
		Schedule.Job j3 = s.insert(3);
		Schedule.Job j4 = s.insert(4);
		Schedule.Job j5 = s.insert(5);
		Schedule.Job j100 = s.insert(100);
		Schedule.Job j7 = s.insert(7);
		
		j7.requires(j3);
		j7.requires(j100);
		j3.requires(j1);
		j100.requires(j5);
		j1.requires(j4);
		j5.requires(j4);
		
		assertEquals(j1, s.get(0));
		
		assertEquals(j4.start(), 0);
		assertEquals(j1.start(), 4);
		assertEquals(j5.start(), 4);
		assertEquals(j3.start(), 5);
		assertEquals(j100.start(), 9);
		assertEquals(j7.start(), 109);
	}
	@Test
	public void testStart() {
		Schedule s = new Schedule();
		
		Schedule.Job j4 = s.insert(4);
		Schedule.Job j6 = s.insert(6);
		Schedule.Job j2 = s.insert(2);
		Schedule.Job j1 = s.insert(1);
		
		j4.requires(j1);
		j1.requires(j2);
		j4.requires(j6);
		
		assertEquals(6, j4.start());
	}
	@Test
	public void cycles() {
		Schedule s = new Schedule();

		for(int n = 0; n < 20; n++) {
			s.insert(n);
		}
		
		s.get(4).requires(s.get(5));
		s.get(5).requires(s.get(4));
		
		assertEquals(0, s.get(0).start());
		assertEquals(-1, s.get(4).start());
		assertEquals(-1, s.get(5).start());
		assertEquals(0, s.get(6).start());
		
		s.get(5).requires(s.get(0));
		assertEquals(0, s.get(0).start());
		assertEquals(0, s.get(0).start());

		assertEquals(-1, s.get(5).start());
		assertEquals(-1, s.get(5).start());

		
		s.get(11).requires(s.get(19));
		s.get(19).requires(s.get(12));
		s.get(12).requires(s.get(11));
		
		assertEquals(-1, s.get(11).start());
	}
	
	@Test
	public void moreCycles() {
		Schedule s = new Schedule();
		s.insert(0);
		for(int n = 1; n < 11; n++) {
			s.insert(n);
		}
		
		s.get(10).requires(s.get(9));
		s.get(9).requires(s.get(8));
		s.get(8).requires(s.get(6));
		s.get(6).requires(s.get(4));
		s.get(4).requires(s.get(2));
		s.get(2).requires(s.get(1));
		
		assertEquals(s.finish(), 40);
		assertEquals(s.finish(), 40);


		
		s.get(7).requires(s.get(5));
		s.get(5).requires(s.get(3));
		s.get(3).requires(s.get(7));
		
		s.insert(11);
		s.get(11).requires(s.get(10));

		assertEquals(s.finish(), -1);
		assertEquals(s.finish(), -1);
		assertEquals(s.get(11).start(), 40);
		assertEquals(s.get(11).start(), 40);

	}
	
	
	@Test
	public void efficiency() { 
		Schedule s = new Schedule();
		Schedule.Job j1 = s.insert(0);
		
		for (int i = 0; i < 500; i++) {
		    Schedule.Job j2 = s.insert(i);
		    j1.requires(j2);
		    
		    j1.start();
		    s.finish();
		    j1.start();
		    s.finish();
		    
		    if( i %1000 == 0)
		    	System.out.println(i);
		}
	}
}
