/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */

import java.util.*;
public class HuffmanCoding {
	/**
	 * This would be a good place to compute and store the tree.
	 */
	private Node root;
	private Map<Character, String> encodingMap  = new HashMap<>(); 
	
	@SuppressWarnings("unused")
	public HuffmanCoding(String text) {
		// TODO fill this in.
		
		Map<Character, Integer> freq = new HashMap<>();
		
		for (var c : text.toCharArray()) {
			freq.put(c, freq.getOrDefault(c,0) +1);	
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
			pq.add(new Node(entry.getKey(),entry.getValue(),null,null));
		}
		
		while (pq.size() > 1) {
			Node left = pq.poll();
			Node right = pq.poll();
			Node parent = new Node('\0', left.freq()+right.freq(), left,right);
			pq.add(parent);
		}
		
		root = pq.poll();
		
		if (root != null) {
			buildMap(root,"");
		}
		
	} 
	
	private void buildMap(Node node, String code) {
		if (node.left() == null && node.right() == null) {
			encodingMap.put(node.c(), code);
			return;
		}
		
		buildMap(node.left(), code + "0");
		buildMap(node.right(), code + "1");
	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encode(String text) {
		// TODO fill this in.
		
		StringBuilder s = new StringBuilder();
		
		for (var c : text.toCharArray()) {
			s.append(encodingMap.get(c));
		}
		return s.toString();
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		// TODO fill this in.
		Node current = root;
		
		StringBuilder decoded = new StringBuilder();
		
		for (var c : encoded.toCharArray()) {
			current = (c == '0') ? current.left() : current.right();
			//check if null? 
			if (current.left() == null && current.right() == null) {
				decoded.append(current.c());
				current = root;
			}
		
		}
		
		
		return decoded.toString();
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't wan to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print
	 * out the encoding tree.
	 */
	public String getInformation() {
		StringBuilder s = new StringBuilder();
		printTree(root, "", s);
		return s.toString();
	}
	
	private void printTree(Node node, String prefix,StringBuilder code) {
		if (node == null) return;
		if (node.left() == null && node.right() == null) {
			code.append("'" + node.c() + "' = ").append(prefix).append("\n");
		}
		
		printTree(node.left(), prefix + "0", code);
		printTree(node.right(), prefix + "1", code);
		
	}

	//Node data-type to create each Node 
	//and assign it data 
	public record Node(char c, int freq, Node left, Node right) implements Comparable<Node>{
		
		@Override
		public int compareTo(Node other) {
			
			int cmp = Integer.compare(this.freq, other.freq);
			if (cmp != 0) return cmp;

			//  tie-break by character if both are leaves
			if (this.isLeaf() && other.isLeaf()) {
				return Character.compare(this.c, other.c);
			}

			// maintain ordering
			return 0;
		}
		
		//see if the node is a leaf
		public boolean isLeaf() {
			return left==null && right==null;
		}
		
	}
}
