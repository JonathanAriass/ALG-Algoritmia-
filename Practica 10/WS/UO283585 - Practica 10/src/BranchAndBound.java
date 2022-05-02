
import java.util.ArrayList;


/**
 * Main class to solve problems using the Branch and Bound technique
 * We need to extend it for any specific problem
 * @author viceg
 */
public abstract class BranchAndBound {
	protected Heap ds; //Nodes to be explored (not used nodes)
	protected NodeAvg bestNode; //To save the final node of the best solution
	protected Node rootNode; //Initial node
	protected double pruneLimit; //To prune nodes above this value
	       
	/**
	 * Constructor for BrancAndBount objects
	 */
	public BranchAndBound() {
		ds = new Heap(); //We create an instance of the Heap class to save the nodes
	}
	      

	protected int counter = 0;
	
	/**
	 * Manages all the process, from the beginning to the end
	 * @param rootNode Starting state of the problem
	 */
	public void branchAndBound(Node rootNode) { 
		ds.insert(rootNode); //First node to be explored
	
		pruneLimit = rootNode.initialValuePruneLimit();

		while (!ds.empty() && ds.estimateBest() < pruneLimit) {
			Node node = ds.extractBestNode();	
			
			ArrayList<NodeAvg> children = node.expand(); 
			counter += children.size();
			
			for (NodeAvg child : children)
				if (child.isSolution()) {
//					counter++;
//					printSolution(child);
					double cost = child.getHeuristicValue();
					if (cost < pruneLimit) {
						pruneLimit = cost;
						bestNode = child;
					} 
				}
				else
					if (child.getHeuristicValue() < pruneLimit) {
						ds.insert(child);
					}
		} //while
		System.out.println(counter);
	}	
	
	/*
	 * private void printSolution(NodeAvg node) { for (Integer el : node.sol) {
	 * System.out.print(el + ", "); } System.out.println(); }
	 */
		
	/**
	 * Gets the root node
	 * @return The root node
	 */
    public Node getRootNode() {
    	return rootNode;
    }
    
	/**
	 * Gets the best node
	 * @return The best node
	 */
    public Node getBestNode() {
    	return bestNode;
    }

    
    public void printSolutionTrace() {
    	if (bestNode == null) {
			System.out.println("Original:");
			System.out.println(rootNode.toString());
    		System.out.println("THERE IS NO SOLUTION");
    	} 
    	else {
    		//Extract the path of the used nodes from bestNode to the rootNode
            ArrayList<Node> result = ds.extractUsedNodesFrom(bestNode);

            for (int i = 0; i < result.size();  i++) {
    			if (i == 0) 
    				System.out.println("Original:");
    			else 
    				System.out.println("Step " + i + ":");
				System.out.println(result.get(result.size()-i-1).toString());
            }
            System.out.println("\nSolution with " + bestNode.getDepth() + " step(s).");
    	}
    }
}
