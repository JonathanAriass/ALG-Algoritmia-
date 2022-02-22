package practica3;

public class Bucle5 {

	public static long bucle5(int n)
	{
		long cont = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				for (int k = 1; k <= n; k++)
					for (int c = 1; c <= n; c*=2)
						cont++;
//		for (int i=0;i<n*n*n;i++)
//			for (int j=1;j<=n;j*=2)
//				cont++;
		
		return cont;
	}

	public static void main(String arg[]) {
		long t1, t2;
		int nVeces = Integer.parseInt(arg[0]);

		for (int n = 8; n <= 100_000; n *= 2) {
			long c = 0;
			t1 = System.currentTimeMillis();

			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				c += bucle5(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println(c + "**n=" + n + "**TIEMPO=" + (t2 - t1)
					+ "**nVeces=" + nVeces);
		} // for

	} // main
} // class