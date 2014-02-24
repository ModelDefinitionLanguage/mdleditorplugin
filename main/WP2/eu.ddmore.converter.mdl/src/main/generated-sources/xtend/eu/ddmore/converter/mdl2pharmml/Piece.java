package eu.ddmore.converter.mdl2pharmml;

/**
 * A class to collect conditions for conditional statements
 */
@SuppressWarnings("all")
public class Piece {
  public Piece parent = null;
  
  public String condition = null;
  
  public String expression = null;
  
  public Piece(final Piece _parent, final String _expression, final String _condition) {
    this.parent = _parent;
    this.condition = _condition;
    this.expression = _expression;
  }
}
