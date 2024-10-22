package bunchbysoh;

public class Main {
  static class CountsBySoH 
  {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();
    int ratedCapacity = 120;
    for(int presentCapacity : presentCapacities)
    {
        //Calculate the state-of-health(SoH) of each battery  
        double sohPercentage = (100 * presentCapacity)/ratedCapacity;

        if(sohPercentage>83) //SoH more than 83%, up to 100%: classified as healthy
          counts.healthy++;
        else if(sohPercentage>=63) //SoH between 83% and 63%: classified as exchange
          counts.exchange++;
        else //SoH below 63%: classified as failed
          counts.failed++;
      
    }
    return counts;
  }

  static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");
    int[] presentCapacities = {113, 116, 80, 95, 92, 70};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    assert(counts.healthy == 2);
    assert(counts.exchange == 3);
    assert(counts.failed == 1);
    System.out.println("Done counting :)\n");
  }

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}
