import java.util.List;

public class Path {

  private final List<PathNode> nodes;

  public Path(List<PathNode> nodes) {
    this.nodes = nodes;
  }

  public int invertedDistFromTarget(int target) {
    return Math.abs(Numbers.RADIUS
        - distFromTarget(target));
  }

  public int distFromTarget(int target) {
    return Math.abs(this.getResult() - target);
  }

  // The value at the end of the path
  public int getResult() {
    return nodes.get(nodes.size() - 1).getResult();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Path)) {
      return false;
    }

    Path other = (Path) obj;
    if (this.getResult() != other.getResult()) {
      return false;
    }

    if (this.getNodes().size() != other.getNodes().size()) {
      return false;
    }

    //TODO Node by Node check for path comparision

    return false;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (PathNode node : nodes) {
      builder.append(node.toString());
      builder.append("\n");
    }
    return builder.toString();
  }

  public List<PathNode> getNodes() {
    return nodes;
  }
}
