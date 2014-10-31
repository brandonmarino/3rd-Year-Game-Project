/**
 * AlphaBetaMiniMax Class is used to set up all needed functions for Alpha Beta Prunning
 * 
 * @author (Lina El Sadek) 
 * @version (version 1 , 28/10/2014)
 */
public class AlphaBetaMiniMax {
	 private int alpha;
     private int beta;
     
     /**
      * Constructor for objects of AlphaBetaMiniMax for specific parameters
      */
     public AlphaBetaMiniMax(int alphaParam, int betaParam) {
             alpha = alphaParam;
             beta = betaParam;
     }
     
     /**
      * Constructor for objects of AlphaBetaMiniMax
      */
     public AlphaBetaMiniMax() {
         alpha = 0 ;
         beta = 0;
     }
     
     
     /**
      * Getter function for Alpha parameter
      * 
      * @param  	void   
      * @return     alpha 
      */
     public int getAlpha() {
             return alpha;
     }
     
     /**
      * Getter function for Beta parameter
      * 
      * @param  	void   
      * @return     beta 
      */
     public int getBeta() {
         return beta;
 }

     /**
      * Setter function for Alpha parameter
      * 
      * @param  	int alphaParameter   
      * @return     void 
      */
     public void setAlpha(int alphaParam) {
             alpha = alphaParam;
     }
     
     /**
      * Setter function for Beta parameter
      * 
      * @param  	int betaParameter   
      * @return     void 
      */
     public void setBeta(int betaParam) {
             beta = betaParam;
     }
     
     /**
      * Creates a copy or a clone of current AlphaBetaMiniMax object
      * 
      * @param  	void   
      * @return     AlphaBetaMiniMax 
      */
     public AlphaBetaMiniMax MakeCopy() {
             return new AlphaBetaMiniMax(alpha, beta);
     }
}
