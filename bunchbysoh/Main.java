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

    if(presentCapacities.length == 0)
    {
      return counts;
    }
    
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

    //Default Test Case
    int[] presentCapacities = {113, 116, 80, 95, 92, 70};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    assert(counts.healthy == 2);
    assert(counts.exchange == 3);
    assert(counts.failed == 1);

    //Test Case - 1
    int[] testCase1 = {120,90,50};
    counts = countBatteriesByHealth(testCase1);
    assert(counts.healthy == 1); //120 is healthy
    assert(counts.exchange == 1); //90 is exchange
    assert(counts.failed == 1); //50 is failed
    System.out.println("Test Case 1 passed!");

    //Testing boundary conditions
    int[] testCase2 = {99, 63, 62};
    counts = countBatteriesByHealth(testCase2);
    assert(counts.healthy == 0); 
    assert(counts.exchange == 1); // 99 is exchange
    assert(counts.failed == 2);  // 63 and 62 is failed
    System.out.println("Test Case 2 passed!");

    //Empty test case
    int[] testCase3 = {};
    counts = countBatteriesByHealth(testCase3);
    assert(counts.healthy == 0); 
    assert(counts.exchange == 0); // 99 is exchange
    assert(counts.failed == 0);  // 63 and 62 is failed
    System.out.println("Test Case 3 passed!");
    
    System.out.println("Done counting :)\n");
  }

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}
