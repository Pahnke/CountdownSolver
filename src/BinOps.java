public class BinOps {

  public static BinOp[] generateBinOps() {
    BinOp[] binOps = new BinOp[4];
    binOps[0] = BinOps.add();
    binOps[1] = BinOps.sub();
    binOps[2] = BinOps.mul();
    binOps[3] = BinOps.div();
    return binOps;
  }


  private static BinOp mul() {
    return new BinOp() {

      @Override
      public boolean associative() {
        return true;
      }

      @Override
      public Integer apply(Integer a, Integer b) {
        return a * b;
      }

      @Override
      public String toString() {
        return "*";
      }
    };
  }

  private static BinOp div() {
    return new BinOp() {
      @Override
      public boolean associative() {
        return false;
      }

      @Override
      public Integer apply(Integer a, Integer b) {
        // Looking for ints only for Countdown
        // 0 is ignored as can't be used to progress
        double tempDob = a / (double) b;
        int tempInt = a / b;
        if (tempInt == tempDob) {
          return tempInt;
        }
        return 0;
      }

      @Override
      public String toString() {
        return "/";
      }
    };
  }

  private static BinOp add() {
    return new BinOp() {

      @Override
      public boolean associative() {
        return true;
      }

      @Override
      public Integer apply(Integer a, Integer b) {
        return a + b;
      }

      @Override
      public String toString() {
        return "+";
      }
    };
  }

  private static BinOp sub() {
    return new BinOp() {

      @Override
      public boolean associative() {
        return false;
      }

      @Override
      public Integer apply(Integer a, Integer b) {
        if (a - b < 0) {
          return 0;
        }
        return a - b;
      }

      @Override
      public String toString() {
        return "-";
      }
    };
  }
}
