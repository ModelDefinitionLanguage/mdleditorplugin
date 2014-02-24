/**
 * MDL converter toolbox, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to convert distributions from MDL to PharmML
 */
package eu.ddmore.converter.mdl2pharmml;

import com.google.common.base.Objects;
import eu.ddmore.converter.mdlprinting.MdlPrinter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.ddmore.mdl.mdl.DistributionArgument;
import org.ddmore.mdl.mdl.DistributionArguments;
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName;
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.Primary;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.Vector;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Pair;

@SuppressWarnings("all")
public class DistributionPrinter extends MdlPrinter {
  private final String xmlns_uncert = "http://www.uncertml.org/3.0";
  
  private final String definition = "http://www.uncertml.org/distributions/";
  
  private final HashMap<String,HashSet<String>> distribution_attrs = new Function0<HashMap<String,HashSet<String>>>() {
    public HashMap<String,HashSet<String>> apply() {
      HashSet<String> _newHashSet = CollectionLiterals.<String>newHashSet("probabilities");
      Pair<String,HashSet<String>> _mappedTo = Pair.<String, HashSet<String>>of("Bernoulli", _newHashSet);
      HashSet<String> _newHashSet_1 = CollectionLiterals.<String>newHashSet("alpha", "beta");
      Pair<String,HashSet<String>> _mappedTo_1 = Pair.<String, HashSet<String>>of("Beta", _newHashSet_1);
      HashSet<String> _newHashSet_2 = CollectionLiterals.<String>newHashSet("numberOfTrials", "probabilityOfSuccess");
      Pair<String,HashSet<String>> _mappedTo_2 = Pair.<String, HashSet<String>>of("Binomial", _newHashSet_2);
      HashSet<String> _newHashSet_3 = CollectionLiterals.<String>newHashSet();
      Pair<String,HashSet<String>> _mappedTo_3 = Pair.<String, HashSet<String>>of("Categorical", _newHashSet_3);
      HashSet<String> _newHashSet_4 = CollectionLiterals.<String>newHashSet("location", "scale");
      Pair<String,HashSet<String>> _mappedTo_4 = Pair.<String, HashSet<String>>of("Cauchy", _newHashSet_4);
      HashSet<String> _newHashSet_5 = CollectionLiterals.<String>newHashSet("degreesOfFreedom");
      Pair<String,HashSet<String>> _mappedTo_5 = Pair.<String, HashSet<String>>of("ChiSquare", _newHashSet_5);
      HashSet<String> _newHashSet_6 = CollectionLiterals.<String>newHashSet("concentration");
      Pair<String,HashSet<String>> _mappedTo_6 = Pair.<String, HashSet<String>>of("Dirichlet", _newHashSet_6);
      HashSet<String> _newHashSet_7 = CollectionLiterals.<String>newHashSet("rate");
      Pair<String,HashSet<String>> _mappedTo_7 = Pair.<String, HashSet<String>>of("Exponential", _newHashSet_7);
      HashSet<String> _newHashSet_8 = CollectionLiterals.<String>newHashSet("denominator, numerator");
      Pair<String,HashSet<String>> _mappedTo_8 = Pair.<String, HashSet<String>>of("FDistribution", _newHashSet_8);
      HashSet<String> _newHashSet_9 = CollectionLiterals.<String>newHashSet("shape", "scale");
      Pair<String,HashSet<String>> _mappedTo_9 = Pair.<String, HashSet<String>>of("Gamma", _newHashSet_9);
      HashSet<String> _newHashSet_10 = CollectionLiterals.<String>newHashSet("probability");
      Pair<String,HashSet<String>> _mappedTo_10 = Pair.<String, HashSet<String>>of("Geometric", _newHashSet_10);
      HashSet<String> _newHashSet_11 = CollectionLiterals.<String>newHashSet("numberOfSuccesses", "numberOfTrials", "populationSize");
      Pair<String,HashSet<String>> _mappedTo_11 = Pair.<String, HashSet<String>>of("Hypergeometric", _newHashSet_11);
      HashSet<String> _newHashSet_12 = CollectionLiterals.<String>newHashSet("shape", "scale");
      Pair<String,HashSet<String>> _mappedTo_12 = Pair.<String, HashSet<String>>of("InverseGamma", _newHashSet_12);
      HashSet<String> _newHashSet_13 = CollectionLiterals.<String>newHashSet("location", "scale");
      Pair<String,HashSet<String>> _mappedTo_13 = Pair.<String, HashSet<String>>of("Laplace", _newHashSet_13);
      HashSet<String> _newHashSet_14 = CollectionLiterals.<String>newHashSet("location", "scale");
      Pair<String,HashSet<String>> _mappedTo_14 = Pair.<String, HashSet<String>>of("Logistic", _newHashSet_14);
      HashSet<String> _newHashSet_15 = CollectionLiterals.<String>newHashSet("logScale", "shape");
      Pair<String,HashSet<String>> _mappedTo_15 = Pair.<String, HashSet<String>>of("LogNormal", _newHashSet_15);
      HashSet<String> _newHashSet_16 = CollectionLiterals.<String>newHashSet("numberOfTrials", "probabilities");
      Pair<String,HashSet<String>> _mappedTo_16 = Pair.<String, HashSet<String>>of("Multinomial", _newHashSet_16);
      HashSet<String> _newHashSet_17 = CollectionLiterals.<String>newHashSet("mean", "covarianceMatrix");
      Pair<String,HashSet<String>> _mappedTo_17 = Pair.<String, HashSet<String>>of("MultivariateNormal", _newHashSet_17);
      HashSet<String> _newHashSet_18 = CollectionLiterals.<String>newHashSet("mean", "covarianceMatrix");
      Pair<String,HashSet<String>> _mappedTo_18 = Pair.<String, HashSet<String>>of("MultivariateStudentT", _newHashSet_18);
      HashSet<String> _newHashSet_19 = CollectionLiterals.<String>newHashSet("numberOfFailures", "probability");
      Pair<String,HashSet<String>> _mappedTo_19 = Pair.<String, HashSet<String>>of("NegativeBinomial", _newHashSet_19);
      HashSet<String> _newHashSet_20 = CollectionLiterals.<String>newHashSet("mean", "variance", "standardDeviation");
      Pair<String,HashSet<String>> _mappedTo_20 = Pair.<String, HashSet<String>>of("Normal", _newHashSet_20);
      HashSet<String> _newHashSet_21 = CollectionLiterals.<String>newHashSet("mean", "varianceScaling", "shape", "scale");
      Pair<String,HashSet<String>> _mappedTo_21 = Pair.<String, HashSet<String>>of("NormalInverseGamma", _newHashSet_21);
      HashSet<String> _newHashSet_22 = CollectionLiterals.<String>newHashSet("scale", "shape");
      Pair<String,HashSet<String>> _mappedTo_22 = Pair.<String, HashSet<String>>of("Pareto", _newHashSet_22);
      HashSet<String> _newHashSet_23 = CollectionLiterals.<String>newHashSet("rate");
      Pair<String,HashSet<String>> _mappedTo_23 = Pair.<String, HashSet<String>>of("Poisson", _newHashSet_23);
      HashSet<String> _newHashSet_24 = CollectionLiterals.<String>newHashSet("location", "scale", "degreesOfFreedom");
      Pair<String,HashSet<String>> _mappedTo_24 = Pair.<String, HashSet<String>>of("StudentT", _newHashSet_24);
      HashSet<String> _newHashSet_25 = CollectionLiterals.<String>newHashSet("minimum", "maximum");
      Pair<String,HashSet<String>> _mappedTo_25 = Pair.<String, HashSet<String>>of("Uniform", _newHashSet_25);
      HashSet<String> _newHashSet_26 = CollectionLiterals.<String>newHashSet("scale", "shape");
      Pair<String,HashSet<String>> _mappedTo_26 = Pair.<String, HashSet<String>>of("Weibull", _newHashSet_26);
      HashSet<String> _newHashSet_27 = CollectionLiterals.<String>newHashSet("degreesOfFreedom", "scaleMatrix");
      Pair<String,HashSet<String>> _mappedTo_27 = Pair.<String, HashSet<String>>of("Wishart", _newHashSet_27);
      HashMap<String,HashSet<String>> _newHashMap = CollectionLiterals.<String, HashSet<String>>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2, _mappedTo_3, _mappedTo_4, _mappedTo_5, _mappedTo_6, _mappedTo_7, _mappedTo_8, _mappedTo_9, _mappedTo_10, _mappedTo_11, _mappedTo_12, _mappedTo_13, _mappedTo_14, _mappedTo_15, _mappedTo_16, _mappedTo_17, _mappedTo_18, _mappedTo_19, _mappedTo_20, _mappedTo_21, _mappedTo_22, _mappedTo_23, _mappedTo_24, _mappedTo_25, _mappedTo_26, _mappedTo_27);
      return _newHashMap;
    }
  }.apply();
  
  private final HashSet<String> matrix_attrs = new Function0<HashSet<String>>() {
    public HashSet<String> apply() {
      HashSet<String> _newHashSet = CollectionLiterals.<String>newHashSet("covarianceMatrix", "scaleMatrix");
      return _newHashSet;
    }
  }.apply();
  
  public CharSequence print_uncert_Distribution(final RandomList randomList) {
    CharSequence _xifexpression = null;
    boolean _notEquals = (!Objects.equal(randomList, null));
    if (_notEquals) {
      CharSequence _xifexpression_1 = null;
      DistributionArguments _arguments = randomList.getArguments();
      boolean _notEquals_1 = (!Objects.equal(_arguments, null));
      if (_notEquals_1) {
        CharSequence _xblockexpression = null;
        {
          DistributionArguments _arguments_1 = randomList.getArguments();
          String type = this.getAttribute(_arguments_1, "type");
          CharSequence _xifexpression_2 = null;
          int _length = type.length();
          boolean _greaterThan = (_length > 0);
          if (_greaterThan) {
            CharSequence _xblockexpression_1 = null;
            {
              boolean _equals = type.equals("FDistribution");
              if (_equals) {
                type = "F";
              }
              CharSequence _switchResult = null;
              boolean _matched = false;
              if (!_matched) {
                boolean _equals_1 = type.equals("MixtureModel");
                if (_equals_1) {
                  _matched=true;
                  CharSequence _print_MixtureModel = this.print_MixtureModel(randomList);
                  _switchResult = _print_MixtureModel;
                }
              }
              if (!_matched) {
                CharSequence _print_DistributionDefault = this.print_DistributionDefault(randomList, type);
                _switchResult = _print_DistributionDefault;
              }
              _xblockexpression_1 = (_switchResult);
            }
            _xifexpression_2 = _xblockexpression_1;
          }
          _xblockexpression = (_xifexpression_2);
        }
        _xifexpression_1 = _xblockexpression;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  protected CharSequence print_DistributionDefault(final RandomList randomList, final String type) {
    CharSequence _xblockexpression = null;
    {
      final HashSet<String> recognizedArgs = this.distribution_attrs.get(type);
      boolean _equals = Objects.equal(recognizedArgs, null);
      if (_equals) {
        return "";
      }
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<");
      _builder.append(type, "");
      _builder.append("Distribution xmlns=\"");
      _builder.append(this.xmlns_uncert, "");
      _builder.append("\" definition=\"");
      String _uRLExtension = this.getURLExtension(type);
      _builder.append(_uRLExtension, "");
      _builder.append("\">");
      _builder.newLineIfNotEmpty();
      {
        DistributionArguments _arguments = randomList.getArguments();
        EList<DistributionArgument> _arguments_1 = _arguments.getArguments();
        for(final DistributionArgument arg : _arguments_1) {
          {
            String _identifier = arg.getIdentifier();
            boolean _notEquals = (!Objects.equal(_identifier, null));
            if (_notEquals) {
              {
                String _identifier_1 = arg.getIdentifier();
                boolean _contains = recognizedArgs.contains(_identifier_1);
                if (_contains) {
                  {
                    String _identifier_2 = arg.getIdentifier();
                    boolean _contains_1 = this.matrix_attrs.contains(_identifier_2);
                    if (_contains_1) {
                      _builder.append("\t");
                      int dimension = this.defineDimension(randomList, arg);
                      _builder.newLineIfNotEmpty();
                      _builder.append("\t");
                      _builder.append("<");
                      String _identifier_3 = arg.getIdentifier();
                      _builder.append(_identifier_3, "	");
                      _builder.append(" dimension=\"");
                      _builder.append(dimension, "	");
                      _builder.append("\">");
                      _builder.newLineIfNotEmpty();
                      _builder.append("\t");
                      _builder.append("\t");
                      _builder.append("<values>");
                      Primary _value = arg.getValue();
                      String _str = this.toStr(_value);
                      _builder.append(_str, "		");
                      _builder.append("</values>");
                      _builder.newLineIfNotEmpty();
                      _builder.append("\t");
                      _builder.append("</");
                      String _identifier_4 = arg.getIdentifier();
                      _builder.append(_identifier_4, "	");
                      _builder.append(">");
                      _builder.newLineIfNotEmpty();
                    } else {
                      _builder.append("\t");
                      _builder.append("<");
                      String _identifier_5 = arg.getIdentifier();
                      _builder.append(_identifier_5, "	");
                      _builder.append(">");
                      _builder.newLineIfNotEmpty();
                      _builder.append("\t");
                      _builder.append("\t");
                      String _valueToStr = this.valueToStr(arg);
                      _builder.append(_valueToStr, "		");
                      _builder.newLineIfNotEmpty();
                      _builder.append("\t");
                      _builder.append("</");
                      String _identifier_6 = arg.getIdentifier();
                      _builder.append(_identifier_6, "	");
                      _builder.append(">");
                      _builder.newLineIfNotEmpty();
                    }
                  }
                }
              }
            }
          }
        }
      }
      _builder.append("</");
      _builder.append(type, "");
      _builder.append("Distribution>");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  protected int defineDimension(final RandomList randomList, final DistributionArgument matrixAttr) {
    DistributionArguments _arguments = randomList.getArguments();
    String dimension = this.getAttribute(_arguments, "dimension");
    int _length = dimension.length();
    boolean _greaterEqualsThan = (_length >= 0);
    if (_greaterEqualsThan) {
      try {
        Integer.parseInt(dimension);
      } catch (final Throwable _t) {
        if (_t instanceof NumberFormatException) {
          final NumberFormatException e = (NumberFormatException)_t;
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    }
    Primary _value = matrixAttr.getValue();
    boolean _notEquals = (!Objects.equal(_value, null));
    if (_notEquals) {
      Primary _value_1 = matrixAttr.getValue();
      Vector _vector = _value_1.getVector();
      boolean _notEquals_1 = (!Objects.equal(_vector, null));
      if (_notEquals_1) {
        Primary _value_2 = matrixAttr.getValue();
        final Vector matrix = _value_2.getVector();
        EList<Primary> _values = matrix.getValues();
        return _values.size();
      }
    }
    return 0;
  }
  
  protected String getURLExtension(final String type) {
    try {
      String _replaceAll = type.replaceAll("(.)([A-Z])", "$1-$2");
      String _lowerCase = _replaceAll.toLowerCase();
      return (this.definition + _lowerCase);
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        String _lowerCase_1 = type.toLowerCase();
        return (this.definition + _lowerCase_1);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  public CharSequence print_MixtureModel(final RandomList randomList) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<MixtureModelDistribution xmlns=\"");
    _builder.append(this.xmlns_uncert, "");
    _builder.append("\" definition=\"");
    _builder.append(this.definition, "");
    _builder.append("mixture-model\">");
    _builder.newLineIfNotEmpty();
    {
      DistributionArguments _arguments = randomList.getArguments();
      EList<DistributionArgument> _arguments_1 = _arguments.getArguments();
      for(final DistributionArgument arg : _arguments_1) {
        {
          RandomList _component = arg.getComponent();
          boolean _notEquals = (!Objects.equal(_component, null));
          if (_notEquals) {
            _builder.append("\t");
            RandomList _component_1 = arg.getComponent();
            DistributionArguments _arguments_2 = _component_1.getArguments();
            final String weight = this.getAttribute(_arguments_2, "weight");
            _builder.newLineIfNotEmpty();
            {
              int _length = weight.length();
              boolean _greaterThan = (_length > 0);
              if (_greaterThan) {
                _builder.append("\t");
                _builder.append("<component weight=\"");
                _builder.append(weight, "	");
                _builder.append("\">");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                RandomList _component_2 = arg.getComponent();
                CharSequence _print_uncert_Distribution = this.print_uncert_Distribution(_component_2);
                _builder.append(_print_uncert_Distribution, "		");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("</component>");
                _builder.newLine();
              }
            }
          }
        }
      }
    }
    _builder.append("</MixtureModelDistribution>");
    _builder.newLine();
    return _builder;
  }
  
  public String toStr(final Primary p) {
    String _xblockexpression = null;
    {
      String _number = p.getNumber();
      boolean _notEquals = (!Objects.equal(_number, null));
      if (_notEquals) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<rVal>");
        String _number_1 = p.getNumber();
        _builder.append(_number_1, "");
        _builder.append("</rVal>");
        return _builder.toString();
      }
      FullyQualifiedSymbolName _symbol = p.getSymbol();
      boolean _notEquals_1 = (!Objects.equal(_symbol, null));
      if (_notEquals_1) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("<var varId=\"");
        FullyQualifiedSymbolName _symbol_1 = p.getSymbol();
        String _str = this.toStr(_symbol_1);
        _builder_1.append(_str, "");
        _builder_1.append("\"/>");
        return _builder_1.toString();
      }
      Vector _vector = p.getVector();
      boolean _notEquals_2 = (!Objects.equal(_vector, null));
      if (_notEquals_2) {
        Vector _vector_1 = p.getVector();
        return this.toStr(_vector_1);
      }
      FunctionCall _functionCall = p.getFunctionCall();
      boolean _notEquals_3 = (!Objects.equal(_functionCall, null));
      if (_notEquals_3) {
        return "";
      }
      String _xifexpression = null;
      FullyQualifiedArgumentName _attribute = p.getAttribute();
      boolean _notEquals_4 = (!Objects.equal(_attribute, null));
      if (_notEquals_4) {
        return "";
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public String valueToStr(final Primary p) {
    String _xblockexpression = null;
    {
      String _number = p.getNumber();
      boolean _notEquals = (!Objects.equal(_number, null));
      if (_notEquals) {
        return p.getNumber();
      }
      FullyQualifiedSymbolName _symbol = p.getSymbol();
      boolean _notEquals_1 = (!Objects.equal(_symbol, null));
      if (_notEquals_1) {
        FullyQualifiedSymbolName _symbol_1 = p.getSymbol();
        return this.toStr(_symbol_1);
      }
      String _xifexpression = null;
      Vector _vector = p.getVector();
      boolean _notEquals_2 = (!Objects.equal(_vector, null));
      if (_notEquals_2) {
        Vector _vector_1 = p.getVector();
        return this.toStr(_vector_1);
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public String toStr(final Vector v) {
    String res = "";
    EList<Primary> _values = v.getValues();
    Iterator<Primary> iterator = _values.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      Primary _next = iterator.next();
      String _valueToStr = this.valueToStr(_next);
      String _plus = (res + _valueToStr);
      res = _plus;
    }
    boolean _hasNext_1 = iterator.hasNext();
    boolean _while = _hasNext_1;
    while (_while) {
      {
        String _plus_1 = (res + " ");
        res = _plus_1;
        Primary _next_1 = iterator.next();
        String _valueToStr_1 = this.valueToStr(_next_1);
        String _plus_2 = (res + _valueToStr_1);
        res = _plus_2;
      }
      boolean _hasNext_2 = iterator.hasNext();
      _while = _hasNext_2;
    }
    return res;
  }
  
  public String toStr(final FullyQualifiedSymbolName s) {
    return s.getIdentifier();
  }
}
