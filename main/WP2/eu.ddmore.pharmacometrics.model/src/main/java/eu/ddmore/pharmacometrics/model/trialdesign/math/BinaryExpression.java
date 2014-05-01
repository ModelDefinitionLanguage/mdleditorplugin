package eu.ddmore.pharmacometrics.model.trialdesign.math;


public class BinaryExpression implements Expression {
    private Expression left;
    private Expression right;
    private Operator operator;
    
    private enum Operator {
        TIMES("TIMES"), DIVIDE("DIVIDE"), MINUS("MINUS"), PLUS("PLUS"); 
        private String op;
        
        private Operator(String op) {
            this.op = op;
        }
        
        public String getOp() {
            return op;
        }
    }

    public BinaryExpression(Expression left, Expression right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public double evaluate() {
        if (operator.equals(Operator.TIMES)) {
            return left.evaluate() * right.evaluate();
        } else if (operator.equals(Operator.DIVIDE)) {
            return left.evaluate() / right.evaluate();
        } else if (operator.equals(Operator.MINUS)) {
            return left.evaluate() - right.evaluate();
        } else {
            return left.evaluate() + right.evaluate();
        }
    }

    @Override
    public String toString() {
        return String.format("BinaryExpression [left=%s, right=%s, operator=%s]", left, right, operator);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((left == null) ? 0 : left.hashCode());
        result = prime * result + ((operator == null) ? 0 : operator.hashCode());
        result = prime * result + ((right == null) ? 0 : right.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BinaryExpression other = (BinaryExpression) obj;
        if (left == null) {
            if (other.left != null) {
                return false;
            }
        } else if (!left.equals(other.left)) {
            return false;
        }
        if (operator != other.operator) {
            return false;
        }
        if (right == null) {
            if (other.right != null) {
                return false;
            }
        } else if (!right.equals(other.right)) {
            return false;
        }
        return true;
    }

}
