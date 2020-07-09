import java.util.function.BinaryOperator;

public abstract class BinOp implements BinaryOperator<Integer> {

  abstract boolean associative();

  // Each binary operator (used) has a unique character
  // representing it so hash and equals are based of it

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

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
