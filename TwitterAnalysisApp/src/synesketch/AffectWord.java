/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package synesketch;

/**
 *
 * @author Mia
 */
public class AffectWord {
    private String word;

        private String id_tweet ;
    
	private double totalBobot = 0.0;

	private double totalValence = 0.0;

	private double bobotGembira = 0.0;

	private double bobotSedih = 0.0;

	private double bobotMarah = 0.0;

	private double bobotTakut = 0.0;

	private double bobotJijik = 0.0;

	private double bobotTerkejut = 0.0;

	
	/**
	 * Class constructor which sets the affect word
	 * 
	 * @param word
	 *            String representing the word
	 */
	public AffectWord() {		
	}

	/**
	 * Class constructor which sets the affect word and it's weights. Valence is
	 * calculated as a function of different emotion type weights.
	 */
	public AffectWord(String id_tweet , String word, 
			double bobotMarah , double bobotJijik, double bobotTakut,
			double bobotGembira, double bobotSedih, double bobotTerkejut) {
                this.id_tweet = id_tweet;
		this.word = word;
		this.bobotMarah = bobotMarah;
		this.bobotJijik = bobotJijik;
		this.bobotTakut = bobotTakut ;
		this.bobotGembira = bobotGembira;
		this.bobotSedih = bobotSedih;
		this.bobotTerkejut = bobotTerkejut;
        
                
                
	}

	
	/**
	 * Getter for the anger weight.
	 */
	public void setWord(String Word) {
		this.word = word;
	}
 
         public String getWord() {
		return word;
	}
         
         
         /**
	 * Getter for the anger weight.
	 */
	public void setId(String id_tweet) {
		this.id_tweet = id_tweet;
	}
 
         public String getId() {
		return id_tweet;
	}
        
	/**
	 * Getter for the anger weight.
	 */
	public double getBobotMarah() {
		return bobotMarah;
	}

	/**
	 * Getter for the anger weight.
	 */
	public void setBobotMarah(double bobotMarah) {
		this.bobotMarah = bobotMarah;
	}

        /**
	 * Getter for the HAPPY weight.
	 */
        public double getBobotGembira() {
		return bobotGembira;
	}

	/**
	 * setter for the anger weight.
	 */
	public void setBobotGembira(double bobotGembira) {
		this.bobotGembira = bobotGembira;
	}
        
         /**
	 * Getter for the HAPPY weight.
	 */
        public double getBobotJijik() {
		return bobotJijik;
	}

	/**
	 * setter for the anger weight.
	 */
	public void setBobotJijik(double bobotJijik) {
		this.bobotJijik = bobotJijik;
	}
	
        /**
	 * Getter for the HAPPY weight.
	 */
        public double getBobotTakut() {
		return bobotTakut;
	}

	/**
	 * setter for the anger weight.
	 */
	public void setBobotTakut(double bobotTakut) {
		this.bobotTakut = bobotTakut;
	}
        
         /**
	 * Getter for the HAPPY weight.
	 */
        public double getBobotSedih() {
		return bobotSedih;
	}

	/**
	 * setter for the anger weight.
	 */
	public void setBobotSedih(double bobotSedih) {
		this.bobotSedih = bobotSedih;
	}
       
         /**
	 * Getter for the HAPPY weight.
	 */
        public double getBobotTerkejut() {
		return bobotTerkejut;
	}

	/**
	 * setter for the anger weight.
	 */
	public void setBobotTerkejut(double bobotTerkejut) {
		this.bobotTerkejut = bobotTerkejut;
	}
        
	
        /**
	 * Getter for the HAPPY weight.
	 */
        public double getTotalBobot() {
		return totalBobot;
	}

	/**
	 * setter for the anger weight.
	 */
	public void setTotalBobot(double totalBobot) {
		this.totalBobot = totalBobot;
	}
        
        /**
	 * Getter for the HAPPY weight.
	 */
        public double getTotalValence() {
		return totalValence;
	}

	/**
	 * setter for the anger weight.
	 */
	public void setTotalValence(double totalValence) {
		this.totalValence = totalValence;
	}
	
	/**
	 * Returns a string representation of the object.
	 * 
	 * @return a string representation of the object
	 */
	public String toString() {
		return word + " " + totalBobot + " " + bobotGembira + " "
				+ bobotSedih + " " + bobotMarah + " " + bobotTakut + " "
				+ bobotJijik + " " + bobotTerkejut;
	}

	private double getValenceSum() {
		return bobotGembira -  (bobotSedih + bobotMarah + bobotTakut + bobotJijik);
	}

	private double getWeightSum() {
		return bobotGembira + bobotSedih + bobotMarah + bobotTakut + bobotJijik + bobotTerkejut;
	}

}


