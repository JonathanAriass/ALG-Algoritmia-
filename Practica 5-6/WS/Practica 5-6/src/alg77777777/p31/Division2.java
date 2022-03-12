package alg77777777.p31;

public class Division2
{

	static long cont;

	
	/**
	 * a = 2
	 * b = 2
	 * k = 1
	 * 
	 * Complejidad final: O(n^k * logn) = O(n^2 * log(n))
	 */
	public static boolean rec2 (int n)
	{
		if (n<=0) cont++;
		else
		{ 
			for (int i=1;i<n;i++) cont++ ;  //O(n)  
			rec2 (n/2);
			rec2 (n/2);
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
				b=rec2 (n);
			} 

			t2 = System.currentTimeMillis ();

			System.out.println(b+" n="+n+ "**TIEMPO="+(t2-t1)+"**nVeces="+nVeces);
		}  // for
	} // main
} //class