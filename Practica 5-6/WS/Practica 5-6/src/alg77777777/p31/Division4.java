package alg77777777.p31;

public class Division4
{

	static long cont;

	
	/**
	 * a = 2
	 * b = 2
	 * k = 0
	 * 
	 * Complejidad final: O(n^(log b (a))) = O(n^(log 2 (2))) = O(n)
	 */
	public static boolean rec3 (int n)
	{
		if (n<=0) 
			cont++;
		else
		{ 
			cont++ ; // O(1)    
			rec3 (n/2);
			rec3 (n/2);
			rec3 (n/2);
			rec3 (n/2);
		}
		return true;   
	}
	
	@SuppressWarnings("unused")
	public static void main (String arg []) 
	{
		long t1,t2,cont;
		int nVeces= Integer.parseInt (arg [0]);
		boolean b=true;

		for (int n=1;n<=10_000_000;n*=2)
		{
			t1 = System.currentTimeMillis ();

			for (int repeticiones=1; repeticiones<=nVeces;repeticiones++)
			{ 
				cont=0;
				b=rec3 (n);
			} 

			t2 = System.currentTimeMillis ();

			System.out.println (b+" n="+n+ "**TIEMPO="+(t2-t1)+"**nVeces="+nVeces);

		}  // for

	} // main
} //class