

import java.io.Serializable;

public class TST implements Serializable {
    private int N;              
    private Node root;

    private static class Node implements Serializable{
        private char c;
        private Node left, mid, right;
        private String id;  // value associated with string
        
        Node(){}
    }

    public TST() {
    }

    public int size() {
        return N;
    }

    public boolean startsWith(String key){
    	return (get(key) != null );//Dummy impl. It won't work.
    }
    public boolean contains(String key) {
        return ( get(key) !=null);
    }

    public String get(String key) {
        if (key == null){
        	throw new NullPointerException();
        }
        if (key.length() == 0){
        	throw new IllegalArgumentException("key must have length >= 1");
        }
        return get(root, key, (key.length() -1),0);
//        if (x == null){
//        	return null;
//        }
//        return x.id;
    }

    private String get(Node x, String key, int lengthMinusOne, int d) {
        if (x == null){
        	return null;
        }
        char c = key.charAt(d);
        if (c < x.c){
        	return get(x.left,  key,lengthMinusOne, d);
        }else if (c > x.c){
        	return get(x.right, key,lengthMinusOne, d);
        }else if (d < lengthMinusOne){
        	return get(x.mid,   key, lengthMinusOne, d+1); 
        }else{
        	return x.id;
        }
    }

    public void put(String key, String val) {
        if (!contains(key)){
        	N++;
        }
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, String val, int d) {

    	char c = key.charAt(d);
    	if (x == null) {
    		x = new Node();
    		x.c = c;
    		x.id = null;
    	}
    	if (c < x.c){             
    		x.left  = put(x.left,  key, val, d);
    	}else if (c > x.c){               
    		x.right = put(x.right, key, val, d);
    	}else if (d < key.length() - 1){  
    		x.mid   = put(x.mid,   key, val, d+1);
    	}else{    
    		x.id = (val) ;
    	}
    	return x;
    }

    public static void main(String[] args) throws Exception {
    	TST tst = new TST();
    	tst.put("com.yodlee.db.site_display_name.12345.en_us", "This is a result");
    	tst.put("AMAR", "Found Amar");
    	tst.put("AMAR1", "Found Amar");
    	String searchKey = "AMAR";
    	System.out.println("is "+searchKey+" available :"+ tst.contains(searchKey));
    	System.out.println("Value for key "+searchKey+" is :"+ tst.get(searchKey));
    	searchKey = "com.yodlee.db.site_display_name.12345.en_us";
    	System.out.println("is "+searchKey+" available :"+ tst.contains(searchKey));
    	System.out.println("Value for key "+searchKey+" is :"+ tst.get(searchKey));
    	System.out.println(tst.size());
    	while(true){
    		Thread.sleep(5000);
    	}
    }
}
