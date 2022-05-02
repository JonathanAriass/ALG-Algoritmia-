import java.util.ArrayList;

public class NodeAvg extends Node {


	private Imagen[] dataset;
	protected ArrayList<Integer> sol;
	protected Imagen half_avg, half1, half2;
	
	protected int WIDTH;
	protected int HEIGHT;
	
	// Construimos nodo
	public NodeAvg(Imagen[] dataset, ArrayList<Integer> sol, int width, int height) {
		super();
		this.WIDTH = width;
		this.HEIGHT = height;
		this.sol = new ArrayList<Integer>(sol);
		this.dataset = dataset;
		
		this.half1 = new Imagen(WIDTH, HEIGHT);
		this.half2 = new Imagen(WIDTH, HEIGHT);
		
		for (int i=0;i<sol.size();i++) {
			if (sol.get(i) == 1) {
				half1.addSignal(dataset[i]);
			} else if(sol.get(i) == 2) {
				half2.addSignal(dataset[i]);
			}
		}
		
		calculateHeuristicValue();
	}
	
	@Override
	public void calculateHeuristicValue() {
		if (isSolution()) {
			this.heuristicValue = -1 * half1.zncc(half2);			
		} else {
			this.heuristicValue = -2;
		}
	}

	@Override
	public ArrayList<NodeAvg> expand() {
		ArrayList<NodeAvg> listaNodos = new ArrayList<NodeAvg>();
		
		sol.add(0);
		listaNodos.add(new NodeAvg(dataset, new ArrayList<Integer>(sol), WIDTH, HEIGHT));
		sol.remove(sol.size() - 1);
			
		sol.add(1);
		listaNodos.add(new NodeAvg(dataset, new ArrayList<Integer>(sol), WIDTH, HEIGHT));
		sol.remove(sol.size() - 1);
		
		sol.add(2);
		listaNodos.add(new NodeAvg(dataset, new ArrayList<Integer>(sol), WIDTH, HEIGHT));
		sol.remove(sol.size() - 1);
		
		return listaNodos;
	}

	@Override
	public boolean isSolution() {
		if (sol.size() == dataset.length) {
			return true;
		}
		else
			return false;
	}

}
