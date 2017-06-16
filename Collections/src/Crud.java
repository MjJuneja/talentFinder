import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Crud {
	static int sum2=0;
	public static void main(String[] args) {
		/*
		Object o = 100;
		o="hello";
		o= true;   //o is general as we can enter anything
		ArrayList list = new ArrayList();
		list.add(100);
		list.add(90.20);
		list.add("hello");
		int t = (Integer)list.get(0);  //auto boxing as object is converted in primitive
		*/
//		List<String> songList = new ArrayList<>();  // making it generic
		//polymorphism
//		songList = new Vector<>();
//		songList = new LinkedList<>();
		ArrayList<String> songList = new ArrayList<>();
		songList.add("boom boom");
		songList.add("bang bang");
		songList.add(0, "fav song");
		System.out.println(songList);  //songList converts to songList.toString
//		songList.remove(0);   //return true or false
//		songList.remove("song 2");
		songList.set(1, "updated song");  // update
//		System.out.println(songList);
		
		Collections.sort(songList);
		System.out.println(songList);
		
		if(songList.contains("fav song")){
			System.out.println("song found");
		}
		else{
			System.out.println("Song not found");
		}
		
		Integer x[] = {10,20,30,40,50};
		List<Integer> l = Arrays.asList(x); //2nd method convert array in list.
		System.out.println(l);
		Integer sum = 0;
		//traversing 1st way
//		for(int i = 0 ; i<l.size();i++){
//			sum+= l.get(i);
//		}
		
//		Iterator<Integer> i = l.iterator(); //unidirectional {java 1.4}
//		while(i.hasNext()){
//			sum+=i.next();   //next give u current value and then shift to next while has next just check whether next value exist or not	
//		}
		
		// 3rd method bidirectional iterator
//		ListIterator<Integer> li = l.listIterator();
//		while(li.hasNext()){
//			sum+= li.next();
//		}
//		
//		while(li.hasPrevious()){
//			sum+= li.previous();
//		}
		//4th way enhanced for loop {java 1.5}
//		for(I i: l){
//			sum+= i;
//		}
//		System.out.println(sum);
		//5th 1.8
		l.forEach((q)->{
			sum2+=q;
		});
		System.out.println(sum2);
	}

}
