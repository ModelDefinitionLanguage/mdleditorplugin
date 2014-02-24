/**
 * MDL converter toolbox, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to convert mathematical expressions from MDL to PharmML
 */
package eu.ddmore.converter.mdl2pharmml;

import com.google.common.base.Objects;
import eu.ddmore.converter.mdl2pharmml.Piece;
import eu.ddmore.converter.mdl2pharmml.ReferenceResolver;
import eu.ddmore.converter.mdlprinting.MdlPrinter;
import java.util.ArrayList;
import java.util.HashSet;
import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.ConditionalExpression;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName;
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.List;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.MultiplicativeExpression;
import org.ddmore.mdl.mdl.OdeList;
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.ParExpression;
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.Primary;
import org.ddmore.mdl.mdl.UnaryExpression;
import org.ddmore.mdl.mdl.Vector;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class MathPrinter extends MdlPrinter {
  @Extension
  private ReferenceResolver resolver = null;
  
  public MathPrinter(final ReferenceResolver resolver) {
    this.resolver = resolver;
  }
  
  private final String xmlns_math = "http://www.pharmml.org/2013/03/Maths";
  
  private final String xmlns_ct = "http://www.pharmml.org/2013/03/CommonTypes";
  
  private final String TYPE_REAL = "real";
  
  private HashSet<String> standardFunctions = new Function0<HashSet<String>>() {
    public HashSet<String> apply() {
      HashSet<String> _newHashSet = CollectionLiterals.<String>newHashSet("abs", "exp", "factorial", "factl", "gammaln", "ln", "log", 
        "logistic", "logit", "normcdf", "probit", "sqrt", "sin", "cos", "tan", "sec", "csc", "cot", 
        "sinh", "cosh", "tanh", "sech", "csch", "coth", "arcsin", "arccos", "arctan", "arcsec", "arccsc", 
        "arccot", "arcsinh", "arccosh", "arctanh", "arcsech", "arccsch", "arccoth", 
        "floor", "ceiling", "logx", "root", "min", "max");
      return _newHashSet;
    }
  }.apply();
  
  private HashSet<String> specialFunctions = new Function0<HashSet<String>>() {
    public HashSet<String> apply() {
      HashSet<String> _newHashSet = CollectionLiterals.<String>newHashSet("seq", "update", "runif", "PHI");
      return _newHashSet;
    }
  }.apply();
  
  public CharSequence print_mdef_FunctionDefinition(final Expression expr) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> arguments = CollectionLiterals.<String>newHashSet();
      TreeIterator<EObject> iterator = expr.eAllContents();
      boolean _hasNext = iterator.hasNext();
      boolean _while = _hasNext;
      while (_while) {
        {
          EObject obj = iterator.next();
          if ((obj instanceof FullyQualifiedSymbolName)) {
            FullyQualifiedSymbolName ref = ((FullyQualifiedSymbolName) obj);
            String _str = this.toStr(ref);
            arguments.add(_str);
          }
        }
        boolean _hasNext_1 = iterator.hasNext();
        _while = _hasNext_1;
      }
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<FunctionDefinition xmlns:ct=\"");
      _builder.append(this.xmlns_ct, "");
      _builder.append("\"");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("symbId=\"combinedErrorModel\" symbolType=\"");
      _builder.append(this.TYPE_REAL, "	");
      _builder.append("\">");
      _builder.newLineIfNotEmpty();
      {
        for(final String arg : arguments) {
          _builder.append("\t");
          _builder.append("<FunctionArgument symbId=\"");
          _builder.append(arg, "	");
          _builder.append("\" symbolType=\"");
          _builder.append(this.TYPE_REAL, "	");
          _builder.append("\"/>");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.append("<Definition>");
      _builder.newLine();
      _builder.append("\t\t");
      CharSequence _print_Math_Equation = this.print_Math_Equation(expr);
      _builder.append(_print_Math_Equation, "		");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("</Definition>");
      _builder.newLine();
      _builder.append("</FunctionDefinition>");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence print_Math_Expr(final AnyExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Expression _expression = e.getExpression();
      boolean _notEquals = (!Objects.equal(_expression, null));
      if (_notEquals) {
        Expression _expression_1 = e.getExpression();
        CharSequence _print_Math_Expr = this.print_Math_Expr(_expression_1);
        _builder.append(_print_Math_Expr, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      List _list = e.getList();
      boolean _notEquals_1 = (!Objects.equal(_list, null));
      if (_notEquals_1) {
        CharSequence _print_list = this.print_list(e);
        _builder.append(_print_list, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      OdeList _odeList = e.getOdeList();
      boolean _notEquals_2 = (!Objects.equal(_odeList, null));
      if (_notEquals_2) {
        CharSequence _print_odeList = this.print_odeList(e);
        _builder.append(_print_odeList, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print_odeList(final AnyExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    OdeList _odeList = e.getOdeList();
    Arguments _arguments = _odeList.getArguments();
    AnyExpression deriv = this.getAttributeExpression(_arguments, "deriv");
    _builder.newLineIfNotEmpty();
    {
      boolean _notEquals = (!Objects.equal(deriv, null));
      if (_notEquals) {
        CharSequence _print_Math_Expr = this.print_Math_Expr(deriv);
        _builder.append(_print_Math_Expr, "");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print_list(final AnyExpression e) {
    CharSequence _xifexpression = null;
    List _list = e.getList();
    boolean _notEquals = (!Objects.equal(_list, null));
    if (_notEquals) {
      CharSequence _xblockexpression = null;
      {
        List _list_1 = e.getList();
        final Arguments args = _list_1.getArguments();
        final String type = this.getAttribute(args, "type");
        final AnyExpression define = this.getAttributeExpression(args, "define");
        CharSequence _xifexpression_1 = null;
        boolean _and = false;
        boolean _equals = type.equals("categorical");
        if (!_equals) {
          _and = false;
        } else {
          List _list_2 = define.getList();
          boolean _notEquals_1 = (!Objects.equal(_list_2, null));
          _and = (_equals && _notEquals_1);
        }
        if (_and) {
          List _list_3 = define.getList();
          CharSequence _print_Categorical = this.print_Categorical(_list_3);
          _xifexpression_1 = _print_Categorical;
        }
        _xblockexpression = (_xifexpression_1);
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  public CharSequence print_Categorical(final List categories) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Categorical>");
    _builder.newLine();
    {
      Arguments _arguments = categories.getArguments();
      EList<Argument> _arguments_1 = _arguments.getArguments();
      for(final Argument c : _arguments_1) {
        _builder.append("<Category>");
        _builder.newLine();
        _builder.append("\t");
        String _identifier = c.getIdentifier();
        _builder.append(_identifier, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</Category>");
        _builder.newLine();
      }
    }
    _builder.append("</Categorical>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Math_FunctionCall(final FunctionCall call) {
    CharSequence _xifexpression = null;
    FullyQualifiedSymbolName _identifier = call.getIdentifier();
    String _identifier_1 = _identifier.getIdentifier();
    boolean _contains = this.specialFunctions.contains(_identifier_1);
    if (_contains) {
      CharSequence _xifexpression_1 = null;
      FullyQualifiedSymbolName _identifier_2 = call.getIdentifier();
      String _identifier_3 = _identifier_2.getIdentifier();
      boolean _equals = _identifier_3.equals("seq");
      if (_equals) {
        CharSequence _xblockexpression = null;
        {
          Arguments _arguments = call.getArguments();
          final EList<Argument> params = _arguments.getArguments();
          CharSequence _xifexpression_2 = null;
          int _size = params.size();
          boolean _equals_1 = (_size == 3);
          if (_equals_1) {
            Argument _get = params.get(0);
            AnyExpression _expression = _get.getExpression();
            CharSequence _print_Math_Expr = this.print_Math_Expr(_expression);
            String _string = _print_Math_Expr.toString();
            Argument _get_1 = params.get(1);
            AnyExpression _expression_1 = _get_1.getExpression();
            CharSequence _print_Math_Expr_1 = this.print_Math_Expr(_expression_1);
            String _string_1 = _print_Math_Expr_1.toString();
            Argument _get_2 = params.get(2);
            AnyExpression _expression_2 = _get_2.getExpression();
            CharSequence _print_Math_Expr_2 = this.print_Math_Expr(_expression_2);
            String _string_2 = _print_Math_Expr_2.toString();
            return this.print_ct_Sequence(_string, _string_1, _string_2);
          }
          _xblockexpression = (_xifexpression_2);
        }
        _xifexpression_1 = _xblockexpression;
      }
      _xifexpression = _xifexpression_1;
    } else {
      FullyQualifiedSymbolName _identifier_4 = call.getIdentifier();
      String _identifier_5 = _identifier_4.getIdentifier();
      boolean _contains_1 = this.standardFunctions.contains(_identifier_5);
      if (_contains_1) {
        return this.print_Math_FunctionCall_Standard(call);
      } else {
        return this.print_Math_FunctionCall_UserDefined(call);
      }
    }
    return _xifexpression;
  }
  
  public CharSequence print_Math_FunctionCall_Standard(final FunctionCall call) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Arguments _arguments = call.getArguments();
      EList<Argument> _arguments_1 = _arguments.getArguments();
      int _size = _arguments_1.size();
      boolean _equals = (_size == 1);
      if (_equals) {
        _builder.append("<Uniop op=\"");
        FullyQualifiedSymbolName _identifier = call.getIdentifier();
        String _identifier_1 = _identifier.getIdentifier();
        _builder.append(_identifier_1, "");
        _builder.append("\">");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        Arguments _arguments_2 = call.getArguments();
        EList<Argument> _arguments_3 = _arguments_2.getArguments();
        Argument _get = _arguments_3.get(0);
        AnyExpression _expression = _get.getExpression();
        CharSequence _print_Math_Expr = this.print_Math_Expr(_expression);
        _builder.append(_print_Math_Expr, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</Uniop>");
        _builder.newLine();
      } else {
        {
          Arguments _arguments_4 = call.getArguments();
          EList<Argument> _arguments_5 = _arguments_4.getArguments();
          int _size_1 = _arguments_5.size();
          boolean _equals_1 = (_size_1 == 2);
          if (_equals_1) {
            _builder.append("<Binop op=\"");
            FullyQualifiedSymbolName _identifier_2 = call.getIdentifier();
            String _identifier_3 = _identifier_2.getIdentifier();
            _builder.append(_identifier_3, "");
            _builder.append("\">");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            Arguments _arguments_6 = call.getArguments();
            EList<Argument> _arguments_7 = _arguments_6.getArguments();
            Argument _get_1 = _arguments_7.get(0);
            AnyExpression _expression_1 = _get_1.getExpression();
            CharSequence _print_Math_Expr_1 = this.print_Math_Expr(_expression_1);
            _builder.append(_print_Math_Expr_1, "	");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            Arguments _arguments_8 = call.getArguments();
            EList<Argument> _arguments_9 = _arguments_8.getArguments();
            Argument _get_2 = _arguments_9.get(1);
            AnyExpression _expression_2 = _get_2.getExpression();
            CharSequence _print_Math_Expr_2 = this.print_Math_Expr(_expression_2);
            _builder.append(_print_Math_Expr_2, "	");
            _builder.newLineIfNotEmpty();
            _builder.append("</Binop>");
            _builder.newLine();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence print_Math_FunctionCall_UserDefined(final FunctionCall call) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<math:FunctionCall>");
    _builder.newLine();
    _builder.append("\t");
    FullyQualifiedSymbolName _identifier = call.getIdentifier();
    CharSequence _print_ct_SymbolRef = this.print_ct_SymbolRef(_identifier);
    _builder.append(_print_ct_SymbolRef, "	");
    _builder.newLineIfNotEmpty();
    {
      Arguments _arguments = call.getArguments();
      EList<Argument> _arguments_1 = _arguments.getArguments();
      for(final Argument arg : _arguments_1) {
        _builder.append("\t");
        CharSequence _print_Math_FunctionArgument = this.print_Math_FunctionArgument(arg);
        _builder.append(_print_Math_FunctionArgument, "	");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</math:FunctionCall>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Math_FunctionArgument(final Argument arg) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<FunctionArgument");
    {
      String _identifier = arg.getIdentifier();
      boolean _notEquals = (!Objects.equal(_identifier, null));
      if (_notEquals) {
        _builder.append(" symbId=\"");
        String _identifier_1 = arg.getIdentifier();
        _builder.append(_identifier_1, "");
        _builder.append("\"");
      }
    }
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    AnyExpression _expression = arg.getExpression();
    CharSequence _print_Math_Expr = this.print_Math_Expr(_expression);
    _builder.append(_print_Math_Expr, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</FunctionArgument>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Assign(final Expression expr) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ct:Assign>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _print_Math_Equation = this.print_Math_Equation(expr);
    _builder.append(_print_Math_Equation, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</ct:Assign>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Math_Equation(final Expression expr) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Equation xmlns=\"");
    _builder.append(this.xmlns_math, "");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_Math_Expr = this.print_Math_Expr(expr);
    _builder.append(_print_Math_Expr, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</Equation>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Math_Expr(final Expression expr) {
    StringConcatenation _builder = new StringConcatenation();
    ConditionalExpression _conditionalExpression = expr.getConditionalExpression();
    CharSequence _print_Math_LogicOp = this.print_Math_LogicOp(_conditionalExpression);
    _builder.append(_print_Math_LogicOp, "");
    _builder.append(" ");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print_Math_LogicOp(final ConditionalExpression expr) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Expression _expression1 = expr.getExpression1();
      boolean _notEquals = (!Objects.equal(_expression1, null));
      if (_notEquals) {
        _builder.append("<Piecewise>");
        _builder.newLine();
        _builder.append("\t");
        Expression _expression1_1 = expr.getExpression1();
        OrExpression _expression = expr.getExpression();
        CharSequence _print_Math_LogicOr = this.print_Math_LogicOr(_expression, 0);
        String _string = _print_Math_LogicOr.toString();
        CharSequence _print_Math_LogicOpPiece = this.print_Math_LogicOpPiece(_expression1_1, _string);
        _builder.append(_print_Math_LogicOpPiece, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        Expression _expression2 = expr.getExpression2();
        OrExpression _expression_1 = expr.getExpression();
        CharSequence _print_DualExpression = this.print_DualExpression(_expression_1);
        String _string_1 = _print_DualExpression.toString();
        CharSequence _print_Math_LogicOpPiece_1 = this.print_Math_LogicOpPiece(_expression2, _string_1);
        _builder.append(_print_Math_LogicOpPiece_1, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</Piecewise>");
        _builder.newLine();
      } else {
        OrExpression _expression_2 = expr.getExpression();
        CharSequence _print_Math_LogicOr_1 = this.print_Math_LogicOr(_expression_2, 0);
        _builder.append(_print_Math_LogicOr_1, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print_Math_LogicOpPiece(final Expression expr, final String condition) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Piece>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _print_Math_Expr = this.print_Math_Expr(expr);
    _builder.append(_print_Math_Expr, "	");
    _builder.newLineIfNotEmpty();
    {
      boolean _notEquals = (!Objects.equal(condition, null));
      if (_notEquals) {
        _builder.append("\t");
        _builder.append("<Condition>");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append(condition, "		");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("</Condition>");
        _builder.newLine();
      }
    }
    _builder.append("</Piece>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Math_LogicOr(final OrExpression expr, final int startIndex) {
    EList<AndExpression> _expression = expr.getExpression();
    boolean _notEquals = (!Objects.equal(_expression, null));
    if (_notEquals) {
      EList<AndExpression> _expression_1 = expr.getExpression();
      int _size = _expression_1.size();
      int _minus = (_size - startIndex);
      boolean _greaterThan = (_minus > 1);
      if (_greaterThan) {
        EList<AndExpression> _expression_2 = expr.getExpression();
        AndExpression _get = _expression_2.get(startIndex);
        final CharSequence first = this.print_Math_LogicAnd(_get, 0);
        int _plus = (startIndex + 1);
        final CharSequence second = this.print_Math_LogicOr(expr, _plus);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<LogicBinop op=\"or\">");
        _builder.newLine();
        _builder.append("\t");
        _builder.append(first, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(second, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</LogicBinop>");
        _builder.newLine();
        return _builder;
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        EList<AndExpression> _expression_3 = expr.getExpression();
        AndExpression _get_1 = _expression_3.get(startIndex);
        CharSequence _print_Math_LogicAnd = this.print_Math_LogicAnd(_get_1, 0);
        _builder_1.append(_print_Math_LogicAnd, "");
        return _builder_1;
      }
    }
    StringConcatenation _builder_2 = new StringConcatenation();
    return _builder_2;
  }
  
  public CharSequence print_Math_LogicAnd(final AndExpression expr, final int startIndex) {
    EList<LogicalExpression> _expression = expr.getExpression();
    boolean _notEquals = (!Objects.equal(_expression, null));
    if (_notEquals) {
      EList<LogicalExpression> _expression_1 = expr.getExpression();
      int _size = _expression_1.size();
      int _minus = (_size - startIndex);
      boolean _greaterThan = (_minus > 1);
      if (_greaterThan) {
        EList<LogicalExpression> _expression_2 = expr.getExpression();
        LogicalExpression _get = _expression_2.get(startIndex);
        final CharSequence first = this.print_Math_LogicOp(_get, 0);
        int _plus = (startIndex + 1);
        final CharSequence second = this.print_Math_LogicAnd(expr, _plus);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<LogicBinop op=\"and\">");
        _builder.newLine();
        _builder.append("\t");
        _builder.append(first, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(second, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</LogicBinop>");
        _builder.newLine();
        return _builder;
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        EList<LogicalExpression> _expression_3 = expr.getExpression();
        LogicalExpression _get_1 = _expression_3.get(startIndex);
        CharSequence _print_Math_LogicOp = this.print_Math_LogicOp(_get_1, 0);
        _builder_1.append(_print_Math_LogicOp, "");
        return _builder_1;
      }
    }
    StringConcatenation _builder_2 = new StringConcatenation();
    return _builder_2;
  }
  
  public CharSequence print_Math_LogicOp(final LogicalExpression expr, final int startIndex) {
    EList<AdditiveExpression> _expression = expr.getExpression();
    boolean _notEquals = (!Objects.equal(_expression, null));
    if (_notEquals) {
      EList<AdditiveExpression> _expression_1 = expr.getExpression();
      int _size = _expression_1.size();
      int _minus = (_size - startIndex);
      boolean _greaterThan = (_minus > 1);
      if (_greaterThan) {
        EList<String> _operator = expr.getOperator();
        String _get = _operator.get(startIndex);
        final String operator = this.convertOperator(_get);
        EList<AdditiveExpression> _expression_2 = expr.getExpression();
        AdditiveExpression _get_1 = _expression_2.get(startIndex);
        final CharSequence first = this.print_Math_AddOp(_get_1, 0);
        int _plus = (startIndex + 1);
        final CharSequence second = this.print_Math_LogicOp(expr, _plus);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<LogicBinop op=\"");
        _builder.append(operator, "");
        _builder.append("\">");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(first, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(second, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</LogicBinop>");
        _builder.newLine();
        return _builder;
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        EList<AdditiveExpression> _expression_3 = expr.getExpression();
        AdditiveExpression _get_2 = _expression_3.get(startIndex);
        CharSequence _print_Math_AddOp = this.print_Math_AddOp(_get_2, 0);
        _builder_1.append(_print_Math_AddOp, "");
        return _builder_1;
      }
    }
    StringConcatenation _builder_2 = new StringConcatenation();
    return _builder_2;
  }
  
  public CharSequence print_Math_AddOp(final AdditiveExpression expr, final int offset) {
    EList<MultiplicativeExpression> _expression = expr.getExpression();
    boolean _notEquals = (!Objects.equal(_expression, null));
    if (_notEquals) {
      EList<MultiplicativeExpression> _expression_1 = expr.getExpression();
      int _size = _expression_1.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        EList<MultiplicativeExpression> _expression_2 = expr.getExpression();
        int _size_1 = _expression_2.size();
        int _minus = (_size_1 - offset);
        boolean _greaterThan_1 = (_minus > 1);
        if (_greaterThan_1) {
          int _plus = (offset + 1);
          final CharSequence first = this.print_Math_AddOp(expr, _plus);
          EList<String> _operator = expr.getOperator();
          EList<String> _operator_1 = expr.getOperator();
          int _size_2 = _operator_1.size();
          int _minus_1 = (_size_2 - 1);
          int _minus_2 = (_minus_1 - offset);
          String _get = _operator.get(_minus_2);
          final String operator = this.convertOperator(_get);
          EList<MultiplicativeExpression> _expression_3 = expr.getExpression();
          EList<MultiplicativeExpression> _expression_4 = expr.getExpression();
          int _size_3 = _expression_4.size();
          int _minus_3 = (_size_3 - 1);
          int _minus_4 = (_minus_3 - offset);
          MultiplicativeExpression _get_1 = _expression_3.get(_minus_4);
          final CharSequence second = this.print_Math_MultOp(_get_1, 0);
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("<Binop op=\"");
          _builder.append(operator, "");
          _builder.append("\">");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append(first, "	");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append(second, "	");
          _builder.newLineIfNotEmpty();
          _builder.append("</Binop>");
          _builder.newLine();
          return _builder;
        } else {
          StringConcatenation _builder_1 = new StringConcatenation();
          EList<MultiplicativeExpression> _expression_5 = expr.getExpression();
          MultiplicativeExpression _get_2 = _expression_5.get(0);
          CharSequence _print_Math_MultOp = this.print_Math_MultOp(_get_2, 0);
          _builder_1.append(_print_Math_MultOp, "");
          return _builder_1;
        }
      }
    }
    EList<String> _string = expr.getString();
    boolean _notEquals_1 = (!Objects.equal(_string, null));
    if (_notEquals_1) {
      EList<String> _string_1 = expr.getString();
      int _size_4 = _string_1.size();
      boolean _greaterThan_2 = (_size_4 > 0);
      if (_greaterThan_2) {
        String res = "";
        EList<String> _string_2 = expr.getString();
        for (final String s : _string_2) {
          String _plus_1 = (res + s);
          res = _plus_1;
        }
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("<ct:String>");
        _builder_2.append(res, "");
        _builder_2.append("</ct:String>");
        return _builder_2;
      }
    }
    StringConcatenation _builder_3 = new StringConcatenation();
    return _builder_3;
  }
  
  public CharSequence print_Math_MultOp(final MultiplicativeExpression expr, final int offset) {
    EList<PowerExpression> _expression = expr.getExpression();
    boolean _notEquals = (!Objects.equal(_expression, null));
    if (_notEquals) {
      EList<PowerExpression> _expression_1 = expr.getExpression();
      int _size = _expression_1.size();
      int _minus = (_size - offset);
      boolean _greaterThan = (_minus > 1);
      if (_greaterThan) {
        int _plus = (offset + 1);
        final CharSequence first = this.print_Math_MultOp(expr, _plus);
        EList<String> _operator = expr.getOperator();
        EList<String> _operator_1 = expr.getOperator();
        int _size_1 = _operator_1.size();
        int _minus_1 = (_size_1 - 1);
        int _minus_2 = (_minus_1 - offset);
        String _get = _operator.get(_minus_2);
        final String operator = this.convertOperator(_get);
        EList<PowerExpression> _expression_2 = expr.getExpression();
        EList<PowerExpression> _expression_3 = expr.getExpression();
        int _size_2 = _expression_3.size();
        int _minus_3 = (_size_2 - 1);
        int _minus_4 = (_minus_3 - offset);
        PowerExpression _get_1 = _expression_2.get(_minus_4);
        final CharSequence second = this.print_Math_PowerOp(_get_1, 0);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<Binop op=\"");
        _builder.append(operator, "");
        _builder.append("\">");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(first, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(second, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</Binop>");
        _builder.newLine();
        return _builder;
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        EList<PowerExpression> _expression_4 = expr.getExpression();
        PowerExpression _get_2 = _expression_4.get(0);
        CharSequence _print_Math_PowerOp = this.print_Math_PowerOp(_get_2, 0);
        _builder_1.append(_print_Math_PowerOp, "");
        return _builder_1;
      }
    }
    StringConcatenation _builder_2 = new StringConcatenation();
    return _builder_2;
  }
  
  public CharSequence print_Math_PowerOp(final PowerExpression expr, final int startIndex) {
    EList<UnaryExpression> _expression = expr.getExpression();
    boolean _notEquals = (!Objects.equal(_expression, null));
    if (_notEquals) {
      EList<UnaryExpression> _expression_1 = expr.getExpression();
      int _size = _expression_1.size();
      int _minus = (_size - startIndex);
      boolean _greaterThan = (_minus > 1);
      if (_greaterThan) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<Binop op=\"");
        String _convertOperator = this.convertOperator("^");
        _builder.append(_convertOperator, "");
        _builder.append("\">");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        EList<UnaryExpression> _expression_2 = expr.getExpression();
        UnaryExpression _get = _expression_2.get(startIndex);
        CharSequence _print_Math_UniOp = this.print_Math_UniOp(_get);
        _builder.append(_print_Math_UniOp, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        int _plus = (startIndex + 1);
        CharSequence _print_Math_PowerOp = this.print_Math_PowerOp(expr, _plus);
        _builder.append(_print_Math_PowerOp, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</Binop>");
        _builder.newLine();
        return _builder;
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        EList<UnaryExpression> _expression_3 = expr.getExpression();
        UnaryExpression _get_1 = _expression_3.get(startIndex);
        CharSequence _print_Math_UniOp_1 = this.print_Math_UniOp(_get_1);
        _builder_1.append(_print_Math_UniOp_1, "");
        return _builder_1;
      }
    }
    StringConcatenation _builder_2 = new StringConcatenation();
    return _builder_2;
  }
  
  public CharSequence print_Math_UniOp(final UnaryExpression expr) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _operator = expr.getOperator();
      boolean _notEquals = (!Objects.equal(_operator, null));
      if (_notEquals) {
        _builder.append("<Uniop op=\"");
        String _operator_1 = expr.getOperator();
        String _convertOperator = this.convertOperator(_operator_1);
        _builder.append(_convertOperator, "");
        _builder.append("\">");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        UnaryExpression _expression = expr.getExpression();
        CharSequence _print_Math_UniOp = this.print_Math_UniOp(_expression);
        _builder.append(_print_Math_UniOp, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</Uniop>");
        _builder.newLine();
      } else {
        {
          ParExpression _parExpression = expr.getParExpression();
          boolean _notEquals_1 = (!Objects.equal(_parExpression, null));
          if (_notEquals_1) {
            ParExpression _parExpression_1 = expr.getParExpression();
            Expression _expression_1 = _parExpression_1.getExpression();
            CharSequence _print_Math_Expr = this.print_Math_Expr(_expression_1);
            _builder.append(_print_Math_Expr, "");
            _builder.newLineIfNotEmpty();
          } else {
            {
              Primary _primary = expr.getPrimary();
              boolean _notEquals_2 = (!Objects.equal(_primary, null));
              if (_notEquals_2) {
                Primary _primary_1 = expr.getPrimary();
                CharSequence _print_Math_Primary = this.print_Math_Primary(_primary_1);
                _builder.append(_print_Math_Primary, "");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence print_Math_Primary(final Primary p) {
    StringConcatenation _builder = new StringConcatenation();
    {
      FunctionCall _functionCall = p.getFunctionCall();
      boolean _notEquals = (!Objects.equal(_functionCall, null));
      if (_notEquals) {
        FunctionCall _functionCall_1 = p.getFunctionCall();
        CharSequence _print_Math_FunctionCall = this.print_Math_FunctionCall(_functionCall_1);
        _builder.append(_print_Math_FunctionCall, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      String _number = p.getNumber();
      boolean _notEquals_1 = (!Objects.equal(_number, null));
      if (_notEquals_1) {
        String _number_1 = p.getNumber();
        String _print_ct_Value = this.print_ct_Value(_number_1);
        _builder.append(_print_ct_Value, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      FullyQualifiedSymbolName _symbol = p.getSymbol();
      boolean _notEquals_2 = (!Objects.equal(_symbol, null));
      if (_notEquals_2) {
        FullyQualifiedSymbolName _symbol_1 = p.getSymbol();
        CharSequence _print_ct_SymbolRef = this.print_ct_SymbolRef(_symbol_1);
        _builder.append(_print_ct_SymbolRef, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      FullyQualifiedArgumentName _attribute = p.getAttribute();
      boolean _notEquals_3 = (!Objects.equal(_attribute, null));
      if (_notEquals_3) {
        FullyQualifiedArgumentName _attribute_1 = p.getAttribute();
        CharSequence _print_ct_SymbolRef_1 = this.print_ct_SymbolRef(_attribute_1);
        _builder.append(_print_ct_SymbolRef_1, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      Vector _vector = p.getVector();
      boolean _notEquals_4 = (!Objects.equal(_vector, null));
      if (_notEquals_4) {
        Vector _vector_1 = p.getVector();
        CharSequence _print_ct_Vector = this.print_ct_Vector(_vector_1);
        _builder.append(_print_ct_Vector, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print_ct_Vector(final Vector vector) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ct:Vector>");
    _builder.newLine();
    {
      EList<Primary> _values = vector.getValues();
      for(final Primary v : _values) {
        _builder.append("\t");
        CharSequence _print_Math_Primary = this.print_Math_Primary(v);
        _builder.append(_print_Math_Primary, "	");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</ct:Vector>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_ct_Sequence(final String begin, final String stepSize, final String end) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ct:Sequence>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<ct:Begin>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(begin, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("</ct:Begin>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<ct:StepSize>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(stepSize, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("</ct:StepSize>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<ct:End>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(end, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("</ct:End>");
    _builder.newLine();
    _builder.append("</ct:Sequence>");
    _builder.newLine();
    return _builder;
  }
  
  public String print_ct_Value(final String value) {
    try {
      int _indexOf = value.indexOf(".");
      int _minus = (-1);
      boolean _greaterThan = (_indexOf > _minus);
      if (_greaterThan) {
        Double.parseDouble(value);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<ct:Double>");
        _builder.append(value, "");
        _builder.append("</ct:Double>");
        return _builder.toString();
      } else {
        Integer.parseInt(value);
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("<ct:Int>");
        _builder_1.append(value, "");
        _builder_1.append("</ct:Int>");
        return _builder_1.toString();
      }
    } catch (final Throwable _t) {
      if (_t instanceof NumberFormatException) {
        final NumberFormatException e = (NumberFormatException)_t;
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("<ct:Id>");
        _builder_2.append(value, "");
        _builder_2.append("</ct:Id>");
        return _builder_2.toString();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  public CharSequence print_ct_Value(final String value, final String type) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ct:");
    _builder.append(type, "");
    _builder.append(">");
    _builder.append(value, "");
    _builder.append("</ct:");
    _builder.append(type, "");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print_DualExpression(final OrExpression expr) {
    ArrayList<String> _arrayList = new ArrayList<String>();
    ArrayList<String> newAndExprs = _arrayList;
    EList<AndExpression> _expression = expr.getExpression();
    for (final AndExpression andExpr : _expression) {
      {
        ArrayList<String> _arrayList_1 = new ArrayList<String>();
        ArrayList<String> dualLogicalExprs = _arrayList_1;
        EList<LogicalExpression> _expression_1 = andExpr.getExpression();
        for (final LogicalExpression logicalExpr : _expression_1) {
          EList<AdditiveExpression> _expression_2 = logicalExpr.getExpression();
          boolean _notEquals = (!Objects.equal(_expression_2, null));
          if (_notEquals) {
            ArrayList<String> _arrayList_2 = new ArrayList<String>();
            ArrayList<String> newExpressions = _arrayList_2;
            ArrayList<String> _arrayList_3 = new ArrayList<String>();
            ArrayList<String> newOperators = _arrayList_3;
            EList<AdditiveExpression> _expression_3 = logicalExpr.getExpression();
            for (final AdditiveExpression addExpr : _expression_3) {
              CharSequence _print_Math_AddOp = this.print_Math_AddOp(addExpr, 0);
              String _string = _print_Math_AddOp.toString();
              newExpressions.add(_string);
            }
            EList<String> _operator = logicalExpr.getOperator();
            boolean _notEquals_1 = (!Objects.equal(_operator, null));
            if (_notEquals_1) {
              EList<String> _operator_1 = logicalExpr.getOperator();
              for (final String op : _operator_1) {
                String _dualOperator = this.getDualOperator(op);
                String _convertOperator = this.convertOperator(_dualOperator);
                newOperators.add(_convertOperator);
              }
            }
            String _print_Math_LogicOp = this.print_Math_LogicOp(newExpressions, newOperators, 0);
            String _string_1 = _print_Math_LogicOp.toString();
            dualLogicalExprs.add(_string_1);
          } else {
            String _boolean = logicalExpr.getBoolean();
            boolean _notEquals_2 = (!Objects.equal(_boolean, null));
            if (_notEquals_2) {
              String _negation = logicalExpr.getNegation();
              boolean _equals = Objects.equal(_negation, null);
              if (_equals) {
                StringConcatenation _builder = new StringConcatenation();
                _builder.append("<Uniop op=\"not\">");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("<");
                String _boolean_1 = logicalExpr.getBoolean();
                _builder.append(_boolean_1, "	");
                _builder.append("/>");
                _builder.newLineIfNotEmpty();
                _builder.append("</Uniop>");
                _builder.newLine();
                dualLogicalExprs.add(_builder.toString());
              } else {
                StringConcatenation _builder_1 = new StringConcatenation();
                _builder_1.append("<");
                String _boolean_2 = logicalExpr.getBoolean();
                _builder_1.append(_boolean_2, "");
                _builder_1.append("/>");
                _builder_1.newLineIfNotEmpty();
                dualLogicalExprs.add(_builder_1.toString());
              }
            }
          }
        }
        CharSequence _print_Math_LogicAnd = this.print_Math_LogicAnd(dualLogicalExprs, 0);
        String _string_2 = _print_Math_LogicAnd.toString();
        newAndExprs.add(_string_2);
      }
    }
    return this.print_Math_LogicOr(newAndExprs, 0);
  }
  
  private String print_Math_LogicOp(final ArrayList<String> exprs, final ArrayList<String> operators, final int startIndex) {
    boolean _notEquals = (!Objects.equal(exprs, null));
    if (_notEquals) {
      boolean _and = false;
      int _size = exprs.size();
      int _minus = (_size - 1);
      boolean _lessThan = (startIndex < _minus);
      if (!_lessThan) {
        _and = false;
      } else {
        int _size_1 = operators.size();
        boolean _lessThan_1 = (startIndex < _size_1);
        _and = (_lessThan && _lessThan_1);
      }
      if (_and) {
        final String first = exprs.get(startIndex);
        int _plus = (startIndex + 1);
        final CharSequence second = this.print_Math_LogicAnd(exprs, _plus);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<LogicBinop op=\"");
        String _get = operators.get(startIndex);
        _builder.append(_get, "");
        _builder.append("\">");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(first, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(second, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</LogicBinop>");
        _builder.newLine();
        return _builder.toString();
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        String _get_1 = exprs.get(startIndex);
        _builder_1.append(_get_1, "");
        return _builder_1.toString();
      }
    }
    StringConcatenation _builder_2 = new StringConcatenation();
    return _builder_2.toString();
  }
  
  private CharSequence print_Math_LogicOr(final ArrayList<String> exprs, final int startIndex) {
    boolean _notEquals = (!Objects.equal(exprs, null));
    if (_notEquals) {
      int _size = exprs.size();
      int _minus = (_size - 1);
      boolean _lessThan = (startIndex < _minus);
      if (_lessThan) {
        final String first = exprs.get(startIndex);
        int _plus = (startIndex + 1);
        final CharSequence second = this.print_Math_LogicOr(exprs, _plus);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<LogicBinop op=\"or\">");
        _builder.newLine();
        _builder.append("\t");
        _builder.append(first, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(second, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</LogicBinop>");
        _builder.newLine();
        return _builder;
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        String _get = exprs.get(startIndex);
        _builder_1.append(_get, "");
        return _builder_1;
      }
    }
    StringConcatenation _builder_2 = new StringConcatenation();
    return _builder_2;
  }
  
  private CharSequence print_Math_LogicAnd(final ArrayList<String> exprs, final int startIndex) {
    boolean _notEquals = (!Objects.equal(exprs, null));
    if (_notEquals) {
      int _size = exprs.size();
      int _minus = (_size - 1);
      boolean _lessThan = (startIndex < _minus);
      if (_lessThan) {
        final String first = exprs.get(startIndex);
        int _plus = (startIndex + 1);
        final CharSequence second = this.print_Math_LogicAnd(exprs, _plus);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<LogicBinop op=\"and\">");
        _builder.newLine();
        _builder.append("\t");
        _builder.append(first, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(second, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</LogicBinop>");
        _builder.newLine();
        return _builder;
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        String _get = exprs.get(startIndex);
        _builder_1.append(_get, "");
        return _builder_1;
      }
    }
    StringConcatenation _builder_2 = new StringConcatenation();
    return _builder_2;
  }
  
  public CharSequence print_Pieces(final ArrayList<Piece> pieces) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ct:Assign>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<Equation xmlns=\"");
    _builder.append(this.xmlns_math, "	");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("<Piecewise>");
    _builder.newLine();
    _builder.append("\t\t\t");
    ArrayList<Piece> parts = this.assembleConditions(pieces);
    _builder.newLineIfNotEmpty();
    {
      for(final Piece part : parts) {
        _builder.append("\t\t\t");
        CharSequence _print_Math_LogicOpPiece = this.print_Math_LogicOpPiece(part.expression, part.condition);
        _builder.append(_print_Math_LogicOpPiece, "			");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("</Piecewise>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</Equation>");
    _builder.newLine();
    _builder.append("</ct:Assign>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Math_LogicOpPiece(final String expr, final String condition) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Piece>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(expr, "	");
    _builder.newLineIfNotEmpty();
    {
      boolean _notEquals = (!Objects.equal(condition, null));
      if (_notEquals) {
        _builder.append("\t");
        _builder.append("<Condition>");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append(condition, "		");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("</Condition>");
        _builder.newLine();
      }
    }
    _builder.append("</Piece>");
    _builder.newLine();
    return _builder;
  }
  
  public ArrayList<Piece> assembleConditions(final ArrayList<Piece> pieces) {
    ArrayList<Piece> _arrayList = new ArrayList<Piece>();
    ArrayList<Piece> model = _arrayList;
    final Function1<Piece,Boolean> _function = new Function1<Piece,Boolean>() {
        public Boolean apply(final Piece o) {
          boolean _notEquals = (!Objects.equal(o.expression, null));
          return Boolean.valueOf(_notEquals);
        }
      };
    Iterable<Piece> piecesWithExpr = IterableExtensions.<Piece>filter(pieces, _function);
    for (final Piece p : piecesWithExpr) {
      {
        Piece current = p;
        ArrayList<String> _arrayList_1 = new ArrayList<String>();
        ArrayList<String> conditions = _arrayList_1;
        boolean _notEquals = (!Objects.equal(current, null));
        boolean _while = _notEquals;
        while (_while) {
          {
            boolean _notEquals_1 = (!Objects.equal(current.condition, null));
            if (_notEquals_1) {
              conditions.add(current.condition);
            }
            current = current.parent;
          }
          boolean _notEquals_1 = (!Objects.equal(current, null));
          _while = _notEquals_1;
        }
        int _size = conditions.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          CharSequence _print_Math_LogicAnd = this.print_Math_LogicAnd(conditions, 0);
          String condition = _print_Math_LogicAnd.toString();
          Piece _piece = new Piece(null, p.expression, condition);
          Piece assembedPiece = _piece;
          model.add(assembedPiece);
        }
      }
    }
    return model;
  }
  
  public String getDualOperator(final String operator) {
    String _switchResult = null;
    boolean _matched = false;
    if (!_matched) {
      if (Objects.equal(operator,"<")) {
        _matched=true;
        _switchResult = ">=";
      }
    }
    if (!_matched) {
      if (Objects.equal(operator,">")) {
        _matched=true;
        _switchResult = "<=";
      }
    }
    if (!_matched) {
      if (Objects.equal(operator,"<=")) {
        _matched=true;
        _switchResult = ">";
      }
    }
    if (!_matched) {
      if (Objects.equal(operator,">=")) {
        _matched=true;
        _switchResult = "<";
      }
    }
    if (!_matched) {
      if (Objects.equal(operator,"==")) {
        _matched=true;
        _switchResult = "!=";
      }
    }
    if (!_matched) {
      if (Objects.equal(operator,"!=")) {
        _matched=true;
        _switchResult = "==";
      }
    }
    if (!_matched) {
      _switchResult = operator;
    }
    return _switchResult;
  }
  
  public String convertOperator(final String operator) {
    String _switchResult = null;
    boolean _matched = false;
    if (!_matched) {
      if (Objects.equal(operator,"<")) {
        _matched=true;
        _switchResult = "lt";
      }
    }
    if (!_matched) {
      if (Objects.equal(operator,">")) {
        _matched=true;
        _switchResult = "gt";
      }
    }
    if (!_matched) {
      if (Objects.equal(operator,"<=")) {
        _matched=true;
        _switchResult = "leq";
      }
    }
    if (!_matched) {
      if (Objects.equal(operator,">=")) {
        _matched=true;
        _switchResult = "geq";
      }
    }
    if (!_matched) {
      if (Objects.equal(operator,"==")) {
        _matched=true;
        _switchResult = "neq";
      }
    }
    if (!_matched) {
      if (Objects.equal(operator,"!=")) {
        _matched=true;
        _switchResult = "eq";
      }
    }
    if (!_matched) {
      if (Objects.equal(operator,"+")) {
        _matched=true;
        _switchResult = "plus";
      }
    }
    if (!_matched) {
      if (Objects.equal(operator,"-")) {
        _matched=true;
        _switchResult = "minus";
      }
    }
    if (!_matched) {
      if (Objects.equal(operator,"*")) {
        _matched=true;
        _switchResult = "times";
      }
    }
    if (!_matched) {
      if (Objects.equal(operator,"/")) {
        _matched=true;
        _switchResult = "divide";
      }
    }
    if (!_matched) {
      if (Objects.equal(operator,"^")) {
        _matched=true;
        _switchResult = "power";
      }
    }
    if (!_matched) {
      _switchResult = operator;
    }
    return _switchResult;
  }
  
  public CharSequence print_ct_SymbolRef(final FullyQualifiedSymbolName ref) {
    StringConcatenation _builder = new StringConcatenation();
    String blkId = this.resolver.getReferenceBlock(ref);
    _builder.newLineIfNotEmpty();
    _builder.append("<ct:SymbRef");
    {
      int _length = blkId.length();
      boolean _greaterThan = (_length > 0);
      if (_greaterThan) {
        _builder.append(" blkIdRef=\"");
        _builder.append(blkId, "");
        _builder.append("\"");
      }
    }
    _builder.append(" symbIdRef=\"");
    String _identifier = ref.getIdentifier();
    _builder.append(_identifier, "");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print_ct_SymbolRef(final FullyQualifiedArgumentName ref) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Description>MDL reference to an attribute ");
    String _str = this.toStr(ref);
    _builder.append(_str, "");
    _builder.append("</Description>");
    _builder.newLineIfNotEmpty();
    String blkId = "";
    _builder.newLineIfNotEmpty();
    {
      FullyQualifiedSymbolName _parent = ref.getParent();
      boolean _notEquals = (!Objects.equal(_parent, null));
      if (_notEquals) {
        FullyQualifiedSymbolName _parent_1 = ref.getParent();
        String _referenceBlock = this.resolver.getReferenceBlock(_parent_1);
        String _blkId = blkId = _referenceBlock;
        _builder.append(_blkId, "");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("<ct:SymbRef ");
    {
      int _length = blkId.length();
      boolean _greaterThan = (_length > 0);
      if (_greaterThan) {
        _builder.append("blkIdRef=\"");
        _builder.append(blkId, "");
        _builder.append("\"");
      }
    }
    _builder.append(" ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("symbIdRef=\"");
    FullyQualifiedSymbolName _parent_2 = ref.getParent();
    String _identifier = _parent_2.getIdentifier();
    _builder.append(_identifier, "	");
    _builder.append(".");
    String _str_1 = this.toStr(ref);
    _builder.append(_str_1, "	");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
