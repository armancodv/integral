package com.mathlibrary.polynomial;

import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.function.Complex;


/**
 * 
 * @author Sergio Besada
 *
 */
public class Polynomial {
	
	public static final double EPSS=1.0e-7;
	public static final int MR= 8;
	public static final int MT= 10;
	public static final int MAXIT=(MT*MR);
	public static final double EPS=2.0e-6;
	public static final int MAXM= 100;

	/**
	 * Polynomial
	 */
	public Polynomial() {  }
	

	/**
	 * Laguerre
	 * 
	 * @param a
	 * @param m
	 * @param x
	 * @throws CalculatorException
	 */
	private static void laguerre(Complex[] a, int m, Complex x) throws CalculatorException{
		int iter,j;
		double abx,abp,abm,err;
		Complex dx,x1,b,d,f,g,h,sq,gp,gm,g2;
		final double[] frac = {0.0,0.5,0.25,0.75,0.13,0.38,0.62,0.88,1.0};

		for (iter=1;iter<=MAXIT;iter++) {
			b=a[m];
			err=Complex.abs(b);
			d=f=new Complex(0.0,0.0);
			abx=Complex.abs(x);
			for (j=m-1;j>=0;j--) {
				f=Complex.add(Complex.mul(x,f),d);
				d=Complex.add(Complex.mul(x,d),b);
				b=Complex.add(Complex.mul(x,b),a[j]);
				err=Complex.abs(b)+abx*err;
			}
			err *= EPSS;
			if (Complex.abs(b) <= err){
	     return ;
	    }
			g=Complex.div(d,b);
			g2=Complex.mul(g,g);
			h=Complex.sub(g2,Complex.mul(2.0,Complex.div(f,b)));
			sq=Complex.sqrt(Complex.mul((double) (m-1),
	            Complex.sub(Complex.mul((double) m,h),g2)));
			gp=Complex.add(g,sq);
			gm=Complex.sub(g,sq);
			abp=Complex.abs(gp);
			abm=Complex.abs(gm);
			if (abp < abm) gp=gm;
			dx=((FMAX(abp,abm) > 0.0 ? Complex.div(new Complex((double) m,0.0),gp)
				: Complex.mul(1+abx,new Complex(Math.cos((double)iter),
	                 Math.sin((double)iter)))));
			x1=Complex.sub(x,dx);
			if (x.r == x1.r && x.i == x1.i){
	     return ;
	    }
			if (iter % MT!=0){
	        x.r=x1.r;
	        x.i=x1.i;
	    }	else{
	     Complex temp=Complex.sub(x,Complex.mul(frac[iter/MT],dx));
	     x.r=temp.r;
	     x.i=temp.i;
	    }
	 	}
		throw new CalculatorException("Too many iterations in laguerre");
	
	}
	
	/**
	 * FMAX
	 * @param a
	 * @param b
	 * @return
	 */
	private static  double FMAX(double a, double b){
	    return ((a>b) ?a: b);
	}


	/**
	 * zroots
	 * 
	 * @param a
	 * @param m
	 * @param polish
	 * @return
	 * @throws CalculatorException
	 */
	private static Complex[] zroots(Complex[] a, int m, boolean polish) throws CalculatorException{
		int i, j,jj;
		Complex x,b,c;
	  Complex[] roots=new Complex[a.length];
		for (i=0;i<a.length;i++){
	        roots[i]=new Complex();
	  }
	  Complex[] ad=new Complex[MAXM];
	  for(j=0; j<MAXM; j++){
	    ad[j]=new Complex();
	  }

		for (j=0;j<=m;j++) ad[j]=a[j];
		for (j=m;j>=1;j--) {
			x=new Complex();
			laguerre(ad,j, x);
			if (Math.abs(x.i) <= 2.0*EPS*Math.abs(x.r)) x.i=0.0;
			roots[j]=x;
			b=ad[j];
			for (jj=j-1;jj>=0;jj--) {
				c=ad[jj];
				ad[jj]=b;
				b=Complex.add(Complex.mul(x,b),c);
			}
		}
		if (polish)
			for (j=1;j<=m;j++)
				laguerre(a,m,roots[j]);
		for (j=2;j<=m;j++) {
			x=roots[j];
			for (i=j-1;i>=1;i--) {
				if (roots[i].r <= x.r) break;
				roots[i+1]=roots[i];
			}
			roots[i+1]=x;
		}
	  return roots;
	}
	
	
	/**
	 * rootCalculation
	 * @param coef
	 * @throws CalculatorException
	 */
	public static void rootCalc(double[] coef) throws CalculatorException{
	        Complex[] a=new Complex[coef.length];
	        for(int i=0; i<coef.length; i++){
	            a[coef.length-1-i]=new Complex(coef[i], 0.0);
	        }
		      Complex[] roots=zroots(a,a.length-1, true);
		      for (int i=1;i<roots.length;i++){
		    	double real =roots[i].r;
		    	double imaginary = roots[i].i;
		    	if (Math.abs(real)< 1e-12 ){
		    		real = 0.0;
		    	}
		    	if (Math.abs(imaginary)< 1e-12 ){
		    		imaginary = 0.0;
		    	}
 			    System.out.println(i+"   "+real+"   "+imaginary+"i");
		      }
	 }
	
	
	/**
	 * rootCalc
	 * @param a
	 * @throws CalculatorException
	 */
	public static void rootCalc(Complex[] a) throws CalculatorException{
		      Complex[] roots=zroots(a,a.length-1, true);
		      for (int i=1;i<roots.length;i++){
		    	    double real =roots[i].r;
			    	double imaginary = roots[i].i;
			    	if (Math.abs(real)< 1e-12 ){
			    		real = 0.0;
			    	}
			    	if (Math.abs(imaginary)< 1e-12 ){
			    		imaginary = 0.0;
			    	}
		    	  
		    	  System.out.println(i+"   "+real+"   "+imaginary+"i");
		      }
	 }
}

	
	

