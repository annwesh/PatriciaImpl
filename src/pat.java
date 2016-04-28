import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//import com.googlecode.util.Patricia_node;

//import com.googlecode.util.pat;

//import com.googlecode.util.pat;

//import com.googlecode.util.Patricia_node;

class Patricia_node
{
      int bit_number;
      String data;
      BitSet key;
      Patricia_node left_child;
      Patricia_node right_child;
      public Patricia_node(BitSet k){
    	  this.bit_number=-1;
    	  data="base";
    	  key=k;
    	  left_child=this;
    	  right_child=null;
      }
      public Patricia_node(int b,String d,BitSet k,Patricia_node l,Patricia_node r) {
		// TODO Auto-generated constructor stub
    	  this.bit_number=b;
    	  this.data=d;
    	  this.key=k;
    	  left_child=l;
    	  right_child=r;
	}
     
}

public class pat {
       int node;
       static Patricia_node head=null;
       //static HashMap<String,String> h1=null;
       static List<BitSet> lrr=new ArrayList<BitSet>();
       public static void main(String args[])
       {
    	   try {
			Thread.sleep(3000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	   
    	   String HEADER="com.yodlee.";
    	  // String binary3=new BigInteger(HEADER.getBytes()).toString(2);
    	   byte[] bytes = HEADER.getBytes();
    	   StringBuilder binary = new StringBuilder();
    	   for (byte b : bytes)
    	   {
    	      int val = b;
    	      for (int i = 0; i < 8; i++)
    	      {
    	         binary.append((val & 128) == 0 ? 0 : 1);
    	         val <<= 1;
    	      }
    	   }
    	   String new1=binary.toString();
    	   System.out.println(new1);
    	   BitSet b3=convertToBitSet(new1);
    	   System.out.println(b3);
    	   //create the head node for the creation of patricia
    	   head=new Patricia_node(b3);
    	  // System.out.println();
    	   head.left_child=head;
    	   head.data="com.yodlee";
    	   
    	   //else System.out.println("notpreesent");
    	   
    	   //insert(b2,"abc");
    	   //insert(b1,"a");
    	   //Patricia_node kkk=search(testerbit);
    	   File f=new File("D:\\drive\\messagekeys.csv");
    	   BufferedReader br=null;
    	   FileInputStream fi=null;
    	   // h1=new HashMap<String, String>();
    	   try {
			fi=new FileInputStream(f);
			br = new BufferedReader(new InputStreamReader(fi));
			 
			String line = null;
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				String arr[]=line.split("\"#");
				//System.out.println(arr[0].split("\"")[1]+" "+arr[2]);
				//h1.put(arr[0].split("\"")[1], arr[2]);
				/*-------------------------------------*/
				
				/*--------------------------------------*/
				lrr.add(convertToBitSet(arr[0].split("\"")[1]));
				insert(convertToBitSet(arr[0].split("\"")[1]), arr[2]);
				
			}
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			br.close();
			fi.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String FIND1="com.yodlee.db.display_name.site_drop_down.252989";
		String FIND2="com.yodlee.db.description.login_field_row.15283";
		String FIND3="com.yodlee.db.base_url.site.10";
		//System.out.println(convertToBitSet(FIND1));
		//System.out.println(convertToBitSet1(FIND1));
		//System.out.println(search11(FIND2));
		{
			
		}
		
		System.out.println("STARTING SEARCH");
		
  	   
    	 /*  BitSet br1=convertToBitSet(FIND1);
    	   Patricia_node CHECKER=search(br1);*/
		/*Patricia_node CHECKER=get(FIND1);
    	   br1.xor(CHECKER.key);
    	   if(br1.nextSetBit(0)==-1)System.out.println("present "+""+CHECKER.data);
    	   else System.out.println("NOT FOUND");*/
		  System.out.println(get(FIND1));
		  System.out.println(get(FIND2));
		  System.out.println(get(FIND3));
    	   System.out.println("SEARCH COMPLETE");
    	 //  fullprint();
    	   TestPATPerformance();
    	   
    	   
       }
       public static void fullprint()
       {
    	   Queue<Patricia_node> q=new LinkedList<Patricia_node>();
    	   Patricia_node kkk=head.left_child;
    	   q.add(kkk);
    	   while(q.isEmpty()==false)
    	   {
    		   kkk=q.poll();
    		   System.out.println(kkk.data +"chck");
    		   if(kkk.bit_number<kkk.left_child.bit_number && kkk!=kkk.left_child)q.add(kkk.left_child);
    		   //else System.out.println(kkk.left_child.data);
    		   if(kkk.bit_number<kkk.right_child.bit_number && kkk!=kkk.right_child)q.add(kkk.right_child);
    		   //else System.out.println(kkk.right_child.data);
    	   }
       }
       public static void insert(BitSet key,String data)
       {
    	   
    	   
    	   
    	   Patricia_node y =search(key);
    	   //System.out.println(key);
    	   //System.out.println(y.key);
    	   BitSet temp=(BitSet)key.clone();
    	   temp.xor(y.key);
    	   if(temp.nextSetBit(0)==-1){System.out.println("Already present\n");return;}
    	   int count=0;
    	   
    	   count=temp.nextSetBit(0);
    	   Patricia_node p=head;
    	   Patricia_node s=head.left_child;
    	   while(s.bit_number>p.bit_number && s.bit_number<count){
    		   p=s;
    		   s=(key.get(s.bit_number)) ? s.right_child : s.left_child;
    	   }
    	   Patricia_node z=new Patricia_node(count, data, key, null, null);
    	   z.left_child=(z.key.get(count)) ? s :z;
    	   z.right_child=(z.key.get(count))?z :s;
    	   if(p.left_child==s)p.left_child=z;
    	   else p.right_child=z;
       }
       public static Patricia_node search(BitSet key)
       {
    	   Patricia_node parent=head;
    	   Patricia_node child=head.left_child;
    	   while(child.bit_number>parent.bit_number)
    	   {
    		   parent=child;
    		   if(key.get(child.bit_number))child=child.right_child;
    		   else child=child.left_child;
    	   }
    	   return child;
       }
       
       public static String get(String key_string)
       {
    	   BitSet br=convertToBitSet(key_string);
    	   Patricia_node parent=head;
    	   Patricia_node child=head.left_child;
    	   while(child.bit_number>parent.bit_number)
    	   {
    		   parent=child;
    		   if(br.get(child.bit_number))child=child.right_child;
    		   else child=child.left_child;
    	   }
    	   br.xor(child.key);
    	   if(br.nextSetBit(0)==-1) return child.data;
    	   else return "NOT FOUND";
    	    
       }
    
       public static BitSet convertToBitSet11(String key_String){
    	   /*------------HOME GROWN----------------------*/
    	   byte[] bytes1 = key_String.getBytes();
    	   StringBuilder binary1 = new StringBuilder();
    	   for (byte b : bytes1)
    	   {
    	      int val = b;
    	      for (int i = 0; i < 8; i++)
    	      {
    	         binary1.append((val & 128) == 0 ? 0 : 1);
    	         val <<= 1;
    	      }
    	   }
    	   String new11=binary1.toString();
    	   /*--------------------------------------------*/
    	   
		BitSet temp=new BitSet(256);
		//System.out.println(binary.length());
		for(int i=0;i<new11.length();i++){
    	  if(new11.charAt(i)=='1')temp.set(i);
    	 // System.out.println("set");
    	   }
		return temp;
       }
       
       public static BitSet convertToBitSet(String key_String){
    	   /*------------HOME GROWN----------------------*/
    	   byte[] bytes1 = key_String.getBytes();
    	  // StringBuilder binary1 = new StringBuilder();
    	   BitSet temp=new BitSet(128);
    	   short counter=0;
    	   for (byte b : bytes1)
    	   {
    	      int val = b;
    	      for (int i = 0; i < 8; i++)
    	      {
    	        // binary1.append((val & 128) == 0 ? 0 : 1);
    	    	  if((val&128)!=0)temp.set(counter);
    	    	  counter++;
    	         val <<= 1;
    	      }
    	   }
    	
		return temp;
       }
       
       public static String get11(BitSet br)
       {
    	   
    	   Patricia_node parent=head;
    	   Patricia_node child=head.left_child;
    	   while(child.bit_number>parent.bit_number)
    	   {
    		   parent=child;
    		   if(br.get(child.bit_number))child=child.right_child;
    		   else child=child.left_child;
    	   }
    	   //br.xor(child.key);
    	   //if(br.nextSetBit(0)==-1) return child.data;
    	   if(br.equals(child.key)) return child.data;
    	   else return "NOT FOUND";
    	    
       }
       
       public static void TestPATPerformance() {
   		int maxIterations = 1;
   		long allDiffs = 0;
   		
   		
   		//System.out.println(arr.size());
   		//System.exit(0);
   		
   		//System.out.println(pat.get("com.yodlee."));
   		for (int i = 0; i < maxIterations; i++) {
   			long start = System.currentTimeMillis();
   			for (BitSet key :lrr) {
   			     pat.get11(key);
   				// String storedValue = pair.getValue();
   				// String retrievedValue =
   				//ConnectToDB.messageCacheTST.get(key);
   				
   				// if(!retrievedValue.equals(storedValue)){
   				// System.out.println("Mismatch in values TST");
   				// }
   			}
   			long diff = System.currentTimeMillis() - start;
   			System.out.println("diff ::" + diff);
   			allDiffs = allDiffs + diff;
   		}
   		
   		System.out.println("Average time taken : " + (allDiffs / maxIterations)
   				+ " milliseconds.");
   		// return (allDiffs/maxIterations);
   	}

       
}
