package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NMaxSumPair {
    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        Collections.sort(A);
        Collections.sort(B);
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        Set<Pair> set = new HashSet<Pair>();
        int n = A.size();
        pq.add(new Tuple(A.get(n-1)+B.get(n-1),n-1, n-1));
        ArrayList<Integer> output = new ArrayList<Integer>();
        for (int k=0;k<n;k++){
            Tuple max = pq.poll();
            output.add(max.number);
            
            int i = max.getA();
            int j = max.getB();
            
            if (i-1 >= 0){
                Pair p1 = new Pair(i-1,j);
                if (!set.contains(p1)){
                	set.add(p1);
                    pq.add(new Tuple(A.get(i-1)+B.get(j),p1));
                }
            }
            
            if (j-1 >= 0){
                Pair p2 = new Pair(i,j-1);
                if (!set.contains(p2)){
                	set.add(p2);
                    pq.add(new Tuple(A.get(i)+B.get(j-1),p2));
                }
            }
        }
        return output;
    }
    
    class Pair{
        int a;
        int b;
        public Pair(int i, int j){
            a = i;
            b = j;
        }
        
        @Override
        public boolean equals(Object o){
            if (!(o instanceof Pair)){
                return false;
            }
            if (this == o){
                return true;
            }
            Pair obj = (Pair)o;
            if (this.a == obj.a && this.b == obj.b){
                return true;
            }
            return false;
        }
        
        @Override
        public int hashCode(){
            return Objects.hash(this.a, this.b);
        }
    }
    
    class Tuple implements Comparable<Tuple>{
        int number;
        Pair indexPair;
        
        public Tuple(int number, int index1, int index2){
            this.number = number;
            this.indexPair = new Pair(index1, index2);
        }
        
        public Tuple(int number, Pair pair){
            this.number = number;
            this.indexPair = pair;
        }
        
        public int getA(){
            return indexPair.a;
        }
        
        public int getB(){
            return indexPair.b;
        }
        
        @Override
        public int compareTo(Tuple t){
            if (this.number == t.number){
                return 0;
            }
            else if (this.number < t.number){
                return 1;
            }
            else{
                return -1;
            }
        }
        
        @Override
        public String toString() {
        	StringBuilder output = new StringBuilder();
        	output.append(number).append("-> (").append(getA()).append(",").append(getB()).append(")");
        	return output.toString();
        }
    }
    
    public static void main(String[] args) {
		ArrayList<Integer> a = Stream.of(1,2,3,4).collect(Collectors.toCollection(ArrayList::new));
		ArrayList<Integer> b = Stream.of(4,2,3,1).collect(Collectors.toCollection(ArrayList::new));
		
		NMaxSumPair sol = new NMaxSumPair();
		System.out.println(sol.solve(a, b));
	}
}
