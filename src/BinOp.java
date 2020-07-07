import java.util.function.BinaryOperator;

public abstract class BinOp implements BinaryOperator<Integer> {

  abstract boolean associative();

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof BinOp)) {
      return false;
    }
    BinOp other = (BinOp) obj;
    return this.toString().equals(other.toString());
  }
}
