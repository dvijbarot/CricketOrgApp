package Cricket;

public class Cricketer {
    // Attributes
    private final long cricketerID;
    private String cricketerName;
    private float battingAvg;
    private float bowlingAvg;
    private float strikeRate;
    private float economyRate;
    private boolean isAvailable;
    private static int numberOfCricketers = 0;

    public Cricketer() {
        // Default constructor initializes attributes with valid default values
        cricketerID = 0;
        cricketerName = "";
        battingAvg = 0.0f;
        bowlingAvg = 0.0f;
        strikeRate = 0.0f;
        economyRate = 0.0f;
        isAvailable = false;
        numberOfCricketers++; // Increment the count of created Cricketer objects
    }

    // Add Cricketer to the database (array of Cricketers)
    public Cricketer(long cricketerID, String cricketerName, float battingAvg, float bowlingAvg,
                     float strikeRate, float economyRate, boolean isAvailable) {
        // Parameterized constructor to initialize all attributes
        this.cricketerID = cricketerID;
        this.cricketerName = cricketerName;
        this.battingAvg = battingAvg;
        this.bowlingAvg = bowlingAvg;
        this.strikeRate = strikeRate;
        this.economyRate = economyRate;
        this.isAvailable = isAvailable;
        numberOfCricketers++; // Increment the count of created Cricketer objects
    }

    public static void displayAllRounders(Cricketer[] cricketerDatabase, float minStrikeRate, float maxEconomyRate) {
        System.out.println("All-Rounders with Strike Rate greater than " + minStrikeRate +
                " and Economy Rate less than " + maxEconomyRate + ":");
        for (Cricketer cricketer : cricketerDatabase) {
            if (cricketer != null && cricketer.isAvailable() &&
                    cricketer.getStrikeRate() > minStrikeRate && cricketer.getEconomyRate() < maxEconomyRate) {
                System.out.println(cricketer);
            }
        }
    }

    public static void displayCricketersByBowlingAvg(Cricketer[] cricketerDatabase, float maxBowlingAvg) {
        System.out.println("Cricketers with Bowling Average less than " + maxBowlingAvg + ":");
        for (Cricketer cricketer : cricketerDatabase) {
            if (cricketer != null && cricketer.isAvailable() && cricketer.getBowlingAvg() < maxBowlingAvg) {
                System.out.println(cricketer);
            }
        }
    }


    // Accessors (getters) and Mutators (setters) for attributes
    public long getCricketerID() {
        return cricketerID;
    }

    public String getCricketerName() {
        return cricketerName;
    }

    public void setCricketerName(String cricketerName) {
        this.cricketerName = cricketerName;
    }

    public float getBattingAvg() {
        return battingAvg;
    }

    public void setBattingAvg(float battingAvg) {
        this.battingAvg = battingAvg;
    }

    public float getBowlingAvg() {
        return bowlingAvg;
    }

    public void setBowlingAvg(float bowlingAvg) {
        this.bowlingAvg = bowlingAvg;
    }

    public float getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(float strikeRate) {
        this.strikeRate = strikeRate;
    }

    public float getEconomyRate() {
        return economyRate;
    }

    public void setEconomyRate(float economyRate) {
        this.economyRate = economyRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // Static method to get the number of Cricketer objects created
    public static int getNumberOfCricketers() {
        return numberOfCricketers;
    }

    // Override the toString() method to display all information of the Cricketer object
    @Override
    public String toString() {
        return "Cricketer ID: " + cricketerID +
                "\nCricketer Name: " + cricketerName +
                "\nBatting Avg: " + battingAvg +
                "\nBowling Avg: " + bowlingAvg +
                "\nStrike Rate: " + strikeRate +
                "\nEconomy Rate: " + economyRate +
                "\nIs Available: " + isAvailable;
    }

    // Override the equals() method to compare two Cricketer objects for equality
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cricketer cricketer = (Cricketer) obj;
        return cricketerID == cricketer.cricketerID && isAvailable == cricketer.isAvailable;
    }
}
