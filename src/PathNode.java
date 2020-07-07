public class PathNode {

  private final int a;
  private final int b;
  private final int result;
  private final BinOp binOp;

  public PathNode(int a, int b, int result, BinOp binOp) {
    this.a = a;
    this.b = b;
    this.result = result;
    this.binOp = binOp;
  }

  public int getResult() {
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof PathNode)) {
      return false;
    }

    PathNode other = (PathNode) obj;
    if (this.getResult() != other.getResult()) {
      return false;
    }

    if (!this.getBinOp().equals(other.getBinOp())) {
      return false;
    }

    if (this.getA() == other.getA() && this.getB() == other.getB()) {
      return true;
    }

    if (!this.getBinOp().associative()) {
      return false;
    }
    return this.getA() == other.getB() && this.getB() == other.getA();
  }

  @Override
  public String toString() {
    return a + " " + binOp.toString() + " " + b + " = " + result;
  }

  public int getA() {
    return a;
  }

  public int getB() {
    return b;
  }

  public BinOp getBinOp() {
    return binOp;
  }
}
