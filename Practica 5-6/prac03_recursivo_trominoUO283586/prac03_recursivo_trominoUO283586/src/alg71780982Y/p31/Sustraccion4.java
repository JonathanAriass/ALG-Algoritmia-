package alg71780982Y.p31;

public class Sustraccion4
{

	static long cont;

	/**
	 * a = 3
	 * b = 2
	 * k = O(1) = 0
	 * 
	 * Complejidad final: O(a^(n/b)) = O(3^(n/2))
	 */
	public static boolean rec3 (int n)
	{
		if (n<=0) 
			cont++;
		else
		{
			cont++;   // O(1)    
			rec3 (n-2);
			rec3 (n-2);
			rec3 (n-2);
		}
		return true;   
	}

	@SuppressWarnings("unused")
	public static void main (String arg []) 
	{
		long t1,t2,cont;
		int nVeces= Integer.parseInt (arg [0]);
		boolean b=true; 
		for (int n=1;n<=100;n++)
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