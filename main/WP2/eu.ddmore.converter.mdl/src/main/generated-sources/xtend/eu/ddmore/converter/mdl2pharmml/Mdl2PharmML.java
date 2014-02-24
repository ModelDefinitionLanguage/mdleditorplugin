package eu.ddmore.converter.mdl2pharmml;

import com.google.common.base.Objects;
import eu.ddmore.converter.mdl2pharmml.DistributionPrinter;
import eu.ddmore.converter.mdl2pharmml.MathPrinter;
import eu.ddmore.converter.mdl2pharmml.Piece;
import eu.ddmore.converter.mdl2pharmml.ReferenceResolver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.Block;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.ConditionalStatement;
import org.ddmore.mdl.mdl.DistributionArguments;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName;
import org.ddmore.mdl.mdl.GroupVariablesBlock;
import org.ddmore.mdl.mdl.GroupVariablesBlockStatement;
import org.ddmore.mdl.mdl.IndividualVariablesBlock;
import org.ddmore.mdl.mdl.InputVariablesBlock;
import org.ddmore.mdl.mdl.List;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.ModelObject;
import org.ddmore.mdl.mdl.ModelObjectBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlockStatement;
import org.ddmore.mdl.mdl.ObjectName;
import org.ddmore.mdl.mdl.ObservationBlock;
import org.ddmore.mdl.mdl.OdeBlock;
import org.ddmore.mdl.mdl.OdeList;
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.ParameterDeclaration;
import org.ddmore.mdl.mdl.ParameterObject;
import org.ddmore.mdl.mdl.ParameterObjectBlock;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.RandomVariableDefinitionBlock;
import org.ddmore.mdl.mdl.StructuralBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.SymbolModification;
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VariabilityBlockStatement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class Mdl2PharmML {
  private final String xsi = "http://www.w3.org/2001/XMLSchema-instance";
  
  private final String xsi_schemaLocation = "http://www.pharmml.org/2013/03/PharmML http://www.pharmml.org/2013/03/PharmML";
  
  private final String xmlns_pharmML = "http://www.pharmml.org/2013/03/PharmML";
  
  private final String xmlns_math = "http://www.pharmml.org/2013/03/Maths";
  
  private final String xmlns_ct = "http://www.pharmml.org/2013/03/CommonTypes";
  
  private final String xmlns_mdef = "http://www.pharmml.org/2013/03/ModelDefinition";
  
  private final String xmlns_mstep = "http://www.pharmml.org/2013/03/ModellingSteps";
  
  private final String xmlns_design = "http://www.pharmml.org/2013/03/TrialDesign";
  
  private final String xmlns_ds = "http://www.pharmml.org/2013/08/Dataset";
  
  private final String writtenVersion = "0.1";
  
  private final String TYPE_INT = "int";
  
  private final String TYPE_REAL = "real";
  
  private Mcl mcl = null;
  
  private DistributionPrinter distributionPrinter = null;
  
  private ReferenceResolver referenceResolver = null;
  
  private MathPrinter mathPrinter = null;
  
  public CharSequence convertToPharmML(final Mcl m) {
    CharSequence _xblockexpression = null;
    {
      this.mcl = m;
      DistributionPrinter _distributionPrinter = new DistributionPrinter();
      this.distributionPrinter = _distributionPrinter;
      ReferenceResolver _referenceResolver = new ReferenceResolver(this.mcl);
      this.referenceResolver = _referenceResolver;
      MathPrinter _mathPrinter = new MathPrinter(this.referenceResolver);
      this.mathPrinter = _mathPrinter;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      _builder.newLine();
      _builder.append("<PharmML ");
      _builder.newLine();
      _builder.append("\t");
      CharSequence _print_PharmML_NameSpaces = this.print_PharmML_NameSpaces();
      _builder.append(_print_PharmML_NameSpaces, "	");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("writtenVersion=\"");
      _builder.append(this.writtenVersion, "	");
      _builder.append("\">");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("<ct:Name>\"");
      Resource _eResource = m.eResource();
      String _fileName = this.referenceResolver.fileName(_eResource);
      _builder.append(_fileName, "	");
      _builder.append("\"</ct:Name>");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_mdef_IndependentVariables = this.print_mdef_IndependentVariables();
      _builder.append(_print_mdef_IndependentVariables, "	");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_mdef_ModelDefinition = this.print_mdef_ModelDefinition();
      _builder.append(_print_mdef_ModelDefinition, "	");
      _builder.newLineIfNotEmpty();
      _builder.append("</PharmML>");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence print_PharmML_NameSpaces() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("xmlns:xsi=\"");
    _builder.append(this.xsi, "");
    _builder.append("\" ");
    _builder.newLineIfNotEmpty();
    _builder.append("xmlns=\"");
    _builder.append(this.xmlns_pharmML, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("xsi:schemaLocation=\"");
    _builder.append(this.xsi_schemaLocation, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("xmlns:math=\"");
    _builder.append(this.xmlns_math, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("xmlns:ct=\"");
    _builder.append(this.xmlns_ct, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("xmlns:ds=\"");
    _builder.append(this.xmlns_ds, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("xmlns:mdef=\"");
    _builder.append(this.xmlns_mdef, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("xmlns:mstep=\"");
    _builder.append(this.xmlns_mstep, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("xmlns:design=\"");
    _builder.append(this.xmlns_design, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  protected CharSequence print_mdef_ModelDefinition() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ModelDefinition xmlns=\"");
    _builder.append(this.xmlns_mdef, "");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _print_mdef_VariabilityModel = this.print_mdef_VariabilityModel();
    _builder.append(_print_mdef_VariabilityModel, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _print_mdef_CovariateModel = this.print_mdef_CovariateModel();
    _builder.append(_print_mdef_CovariateModel, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _print_mdef_ParameterModel = this.print_mdef_ParameterModel();
    _builder.append(_print_mdef_ParameterModel, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _print_mdef_StructuralModel = this.print_mdef_StructuralModel();
    _builder.append(_print_mdef_StructuralModel, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _print_mdef_ObservationModel = this.print_mdef_ObservationModel();
    _builder.append(_print_mdef_ObservationModel, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</ModelDefinition>");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence print_mdef_IndependentVariables() {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final String s : this.referenceResolver.ind_vars) {
        _builder.append("<IndependentVariable symbId=\"");
        _builder.append(s, "");
        _builder.append("\"/>");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  protected String print_mdef_VariabilityModel() {
    String model = "";
    EList<MclObject> _objects = this.mcl.getObjects();
    for (final MclObject o : _objects) {
      ModelObject _modelObject = o.getModelObject();
      boolean _notEquals = (!Objects.equal(_modelObject, null));
      if (_notEquals) {
        ObjectName _identifier = o.getIdentifier();
        String _name = _identifier.getName();
        HashSet<String> errorVars = this.referenceResolver.vm_err_vars.get(_name);
        boolean _notEquals_1 = (!Objects.equal(errorVars, null));
        if (_notEquals_1) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("<VariabilityModel blkId=\"vm_err.");
          ObjectName _identifier_1 = o.getIdentifier();
          String _name_1 = _identifier_1.getName();
          _builder.append(_name_1, "");
          _builder.append("\" type = \"error\">");
          _builder.newLineIfNotEmpty();
          {
            for(final String s : errorVars) {
              _builder.append("\t");
              _builder.append("<Level symbId=\"");
              _builder.append(s, "	");
              _builder.append("\"/>");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("</VariabilityModel>");
          _builder.newLine();
          String _plus = (model + _builder);
          model = _plus;
        }
        ObjectName _identifier_2 = o.getIdentifier();
        String _name_2 = _identifier_2.getName();
        HashSet<String> mdlVars = this.referenceResolver.vm_mdl_vars.get(_name_2);
        boolean _notEquals_2 = (!Objects.equal(mdlVars, null));
        if (_notEquals_2) {
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("<VariabilityModel blkId=\"vm_mdl.");
          ObjectName _identifier_3 = o.getIdentifier();
          String _name_3 = _identifier_3.getName();
          _builder_1.append(_name_3, "");
          _builder_1.append("\" type = \"model\">");
          _builder_1.newLineIfNotEmpty();
          {
            for(final String s_1 : mdlVars) {
              _builder_1.append("\t");
              _builder_1.append("<Level symbId=\"");
              _builder_1.append(s_1, "	");
              _builder_1.append("\"/>");
              _builder_1.newLineIfNotEmpty();
            }
          }
          _builder_1.append("</VariabilityModel>");
          _builder_1.newLine();
          String _plus_1 = (model + _builder_1);
          model = _plus_1;
        }
      }
    }
    return model;
  }
  
  protected String print_mdef_CovariateModel() {
    String model = "";
    EList<MclObject> _objects = this.mcl.getObjects();
    for (final MclObject o : _objects) {
      ModelObject _modelObject = o.getModelObject();
      boolean _notEquals = (!Objects.equal(_modelObject, null));
      if (_notEquals) {
        ObjectName _identifier = o.getIdentifier();
        String _name = _identifier.getName();
        HashSet<String> covariateVars = this.referenceResolver.cm_vars.get(_name);
        boolean _notEquals_1 = (!Objects.equal(covariateVars, null));
        if (_notEquals_1) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("<CovariateModel blkId=\"cm.");
          ObjectName _identifier_1 = o.getIdentifier();
          String _name_1 = _identifier_1.getName();
          _builder.append(_name_1, "");
          _builder.append("\">");
          _builder.newLineIfNotEmpty();
          {
            for(final String s : covariateVars) {
              _builder.append("\t");
              CharSequence _print_mdef_CovariateModel = this.print_mdef_CovariateModel(s);
              _builder.append(_print_mdef_CovariateModel, "	");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("</CovariateModel>");
          _builder.newLine();
          String _plus = (model + _builder);
          model = _plus;
        }
      }
    }
    return model;
  }
  
  protected CharSequence print_mdef_CovariateModel(final String symbId) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Covariate symbId=\"");
    _builder.append(symbId, "");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<Continuous>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<Transformation>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<math:Equation>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<ct:SymbRef symbIdRef=\"");
    _builder.append(symbId, "				");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("</math:Equation>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</Transformation>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</Continuous>\t");
    _builder.newLine();
    _builder.append("</Covariate>");
    _builder.newLine();
    return _builder;
  }
  
  protected String print_mdef_ParameterModel() {
    String model = "";
    EList<MclObject> _objects = this.mcl.getObjects();
    for (final MclObject o : _objects) {
      {
        String statements = "";
        ParameterObject _parameterObject = o.getParameterObject();
        boolean _notEquals = (!Objects.equal(_parameterObject, null));
        if (_notEquals) {
          ParameterObject _parameterObject_1 = o.getParameterObject();
          EList<ParameterObjectBlock> _blocks = _parameterObject_1.getBlocks();
          for (final ParameterObjectBlock b : _blocks) {
            {
              StructuralBlock _structuralBlock = b.getStructuralBlock();
              boolean _notEquals_1 = (!Objects.equal(_structuralBlock, null));
              if (_notEquals_1) {
                StructuralBlock _structuralBlock_1 = b.getStructuralBlock();
                EList<ParameterDeclaration> _parameters = _structuralBlock_1.getParameters();
                for (final ParameterDeclaration id : _parameters) {
                  StringConcatenation _builder = new StringConcatenation();
                  _builder.append("<SimpleParameter symbId = \"");
                  String _identifier = id.getIdentifier();
                  _builder.append(_identifier, "");
                  _builder.append("\"/>");
                  String _plus = (statements + _builder);
                  statements = _plus;
                }
              }
              VariabilityBlock _variabilityBlock = b.getVariabilityBlock();
              boolean _notEquals_2 = (!Objects.equal(_variabilityBlock, null));
              if (_notEquals_2) {
                VariabilityBlock _variabilityBlock_1 = b.getVariabilityBlock();
                EList<VariabilityBlockStatement> _statements = _variabilityBlock_1.getStatements();
                for (final VariabilityBlockStatement st : _statements) {
                  ParameterDeclaration _parameter = st.getParameter();
                  boolean _notEquals_3 = (!Objects.equal(_parameter, null));
                  if (_notEquals_3) {
                    StringConcatenation _builder_1 = new StringConcatenation();
                    _builder_1.append("<SimpleParameter symbId = \"");
                    ParameterDeclaration _parameter_1 = st.getParameter();
                    String _identifier_1 = _parameter_1.getIdentifier();
                    _builder_1.append(_identifier_1, "");
                    _builder_1.append("\"/>");
                    String _plus_1 = (statements + _builder_1);
                    statements = _plus_1;
                  }
                }
              }
            }
          }
        }
        ModelObject _modelObject = o.getModelObject();
        boolean _notEquals_1 = (!Objects.equal(_modelObject, null));
        if (_notEquals_1) {
          ModelObject _modelObject_1 = o.getModelObject();
          EList<ModelObjectBlock> _blocks_1 = _modelObject_1.getBlocks();
          for (final ModelObjectBlock b_1 : _blocks_1) {
            {
              GroupVariablesBlock _groupVariablesBlock = b_1.getGroupVariablesBlock();
              boolean _notEquals_2 = (!Objects.equal(_groupVariablesBlock, null));
              if (_notEquals_2) {
                GroupVariablesBlock _groupVariablesBlock_1 = b_1.getGroupVariablesBlock();
                EList<GroupVariablesBlockStatement> _statements = _groupVariablesBlock_1.getStatements();
                for (final GroupVariablesBlockStatement st : _statements) {
                  BlockStatement _statement = st.getStatement();
                  boolean _notEquals_3 = (!Objects.equal(_statement, null));
                  if (_notEquals_3) {
                    BlockStatement _statement_1 = st.getStatement();
                    this.print_BlockStatement(_statement_1, "SimpleParameter", Boolean.valueOf(false));
                  }
                }
              }
              RandomVariableDefinitionBlock _randomVariableDefinitionBlock = b_1.getRandomVariableDefinitionBlock();
              boolean _notEquals_4 = (!Objects.equal(_randomVariableDefinitionBlock, null));
              if (_notEquals_4) {
                RandomVariableDefinitionBlock _randomVariableDefinitionBlock_1 = b_1.getRandomVariableDefinitionBlock();
                EList<SymbolDeclaration> _variables = _randomVariableDefinitionBlock_1.getVariables();
                for (final SymbolDeclaration s : _variables) {
                  String _identifier = s.getIdentifier();
                  boolean _isIndependentVariable = this.referenceResolver.isIndependentVariable(_identifier);
                  if (_isIndependentVariable) {
                    CharSequence _print_mdef_RandomVariable = this.print_mdef_RandomVariable(s);
                    String _plus = (statements + _print_mdef_RandomVariable);
                    statements = _plus;
                  }
                }
              }
              IndividualVariablesBlock _individualVariablesBlock = b_1.getIndividualVariablesBlock();
              boolean _notEquals_5 = (!Objects.equal(_individualVariablesBlock, null));
              if (_notEquals_5) {
                IndividualVariablesBlock _individualVariablesBlock_1 = b_1.getIndividualVariablesBlock();
                EList<BlockStatement> _statements_1 = _individualVariablesBlock_1.getStatements();
                for (final BlockStatement s_1 : _statements_1) {
                  CharSequence _print_BlockStatement = this.print_BlockStatement(s_1, "IndividualParameter", Boolean.valueOf(false));
                  String _plus_1 = (statements + _print_BlockStatement);
                  statements = _plus_1;
                }
              }
            }
          }
        }
        int _length = statements.length();
        boolean _greaterThan = (_length > 0);
        if (_greaterThan) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("<ParameterModel blkId=\"pm.");
          ObjectName _identifier = o.getIdentifier();
          String _name = _identifier.getName();
          _builder.append(_name, "");
          _builder.append("\">");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append(statements, "	");
          _builder.newLineIfNotEmpty();
          _builder.append("</ParameterModel>");
          _builder.newLine();
          String _plus = (model + _builder);
          model = _plus;
        }
      }
    }
    return model;
  }
  
  protected String print_ConditionalStatement(final ConditionalStatement s, final String tag) {
    HashMap<String,ArrayList<Piece>> _hashMap = new HashMap<String,ArrayList<Piece>>();
    HashMap<String,ArrayList<Piece>> symbols = _hashMap;
    HashMap<String,Integer> _hashMap_1 = new HashMap<String,Integer>();
    HashMap<String,Integer> symbolOrders = _hashMap_1;
    Piece parent = null;
    this.prepareConditionalSymbols(s, parent, symbols);
    this.defineOrderOfConditionalSymbols(s, symbolOrders, Integer.valueOf(0));
    int max = 0;
    Set<Entry<String,Integer>> _entrySet = symbolOrders.entrySet();
    for (final Entry<String,Integer> o : _entrySet) {
      Integer _value = o.getValue();
      boolean _lessThan = (max < (_value).intValue());
      if (_lessThan) {
        Integer _value_1 = o.getValue();
        max = (_value_1).intValue();
      }
    }
    String model = "";
    IntegerRange _upTo = new IntegerRange(0, max);
    for (final Integer i : _upTo) {
      Set<Entry<String,Integer>> _entrySet_1 = symbolOrders.entrySet();
      for (final Entry<String,Integer> o_1 : _entrySet_1) {
        Integer _value_2 = o_1.getValue();
        boolean _equals = Objects.equal(i, _value_2);
        if (_equals) {
          String _key = o_1.getKey();
          final ArrayList<Piece> pieces = symbols.get(_key);
          boolean _notEquals = (!Objects.equal(pieces, null));
          if (_notEquals) {
            String _key_1 = o_1.getKey();
            CharSequence _print_Pieces = this.print_Pieces(_key_1, tag, pieces, true);
            String _plus = (model + _print_Pieces);
            model = _plus;
          }
        }
      }
    }
    return model;
  }
  
  protected void prepareConditionalSymbols(final ConditionalStatement s, final Piece parent, final HashMap<String,ArrayList<Piece>> symbols) {
    BlockStatement _ifStatement = s.getIfStatement();
    boolean _notEquals = (!Objects.equal(_ifStatement, null));
    if (_notEquals) {
      OrExpression _expression = s.getExpression();
      CharSequence _print_Math_LogicOr = this.mathPrinter.print_Math_LogicOr(_expression, 0);
      final String mainExpr = _print_Math_LogicOr.toString();
      BlockStatement _ifStatement_1 = s.getIfStatement();
      this.addConditionalSymbol(_ifStatement_1, mainExpr, parent, symbols);
    }
    BlockStatement _elseStatement = s.getElseStatement();
    boolean _notEquals_1 = (!Objects.equal(_elseStatement, null));
    if (_notEquals_1) {
      OrExpression _expression_1 = s.getExpression();
      CharSequence _print_DualExpression = this.mathPrinter.print_DualExpression(_expression_1);
      final String dualExpr = _print_DualExpression.toString();
      BlockStatement _elseStatement_1 = s.getElseStatement();
      this.addConditionalSymbol(_elseStatement_1, dualExpr, parent, symbols);
    }
    Block _ifBlock = s.getIfBlock();
    boolean _notEquals_2 = (!Objects.equal(_ifBlock, null));
    if (_notEquals_2) {
      OrExpression _expression_2 = s.getExpression();
      CharSequence _print_Math_LogicOr_1 = this.mathPrinter.print_Math_LogicOr(_expression_2, 0);
      final String mainExpr_1 = _print_Math_LogicOr_1.toString();
      Block _ifBlock_1 = s.getIfBlock();
      EList<BlockStatement> _statements = _ifBlock_1.getStatements();
      for (final BlockStatement b : _statements) {
        this.addConditionalSymbol(b, mainExpr_1, parent, symbols);
      }
    }
    Block _elseBlock = s.getElseBlock();
    boolean _notEquals_3 = (!Objects.equal(_elseBlock, null));
    if (_notEquals_3) {
      OrExpression _expression_3 = s.getExpression();
      CharSequence _print_DualExpression_1 = this.mathPrinter.print_DualExpression(_expression_3);
      final String dualExpr_1 = _print_DualExpression_1.toString();
      Block _elseBlock_1 = s.getElseBlock();
      EList<BlockStatement> _statements_1 = _elseBlock_1.getStatements();
      for (final BlockStatement b_1 : _statements_1) {
        this.addConditionalSymbol(b_1, dualExpr_1, parent, symbols);
      }
    }
  }
  
  protected void addConditionalSymbol(final BlockStatement s, final String condition, final Piece parent, final HashMap<String,ArrayList<Piece>> symbols) {
    SymbolDeclaration _symbol = s.getSymbol();
    boolean _notEquals = (!Objects.equal(_symbol, null));
    if (_notEquals) {
      SymbolDeclaration _symbol_1 = s.getSymbol();
      AnyExpression _expression = _symbol_1.getExpression();
      boolean _notEquals_1 = (!Objects.equal(_expression, null));
      if (_notEquals_1) {
        SymbolDeclaration _symbol_2 = s.getSymbol();
        AnyExpression _expression_1 = _symbol_2.getExpression();
        Expression _expression_2 = _expression_1.getExpression();
        boolean _notEquals_2 = (!Objects.equal(_expression_2, null));
        if (_notEquals_2) {
          SymbolDeclaration _symbol_3 = s.getSymbol();
          String _identifier = _symbol_3.getIdentifier();
          ArrayList<Piece> pieces = symbols.get(_identifier);
          boolean _equals = Objects.equal(pieces, null);
          if (_equals) {
            ArrayList<Piece> _arrayList = new ArrayList<Piece>();
            pieces = _arrayList;
          }
          SymbolDeclaration _symbol_4 = s.getSymbol();
          AnyExpression _expression_3 = _symbol_4.getExpression();
          Expression _expression_4 = _expression_3.getExpression();
          CharSequence _print_Math_Expr = this.mathPrinter.print_Math_Expr(_expression_4);
          String _string = _print_Math_Expr.toString();
          Piece _piece = new Piece(parent, _string, condition);
          Piece piece = _piece;
          pieces.add(piece);
          SymbolDeclaration _symbol_5 = s.getSymbol();
          String _identifier_1 = _symbol_5.getIdentifier();
          symbols.put(_identifier_1, pieces);
        }
      }
    }
    ConditionalStatement _statement = s.getStatement();
    boolean _notEquals_3 = (!Objects.equal(_statement, null));
    if (_notEquals_3) {
      Piece _piece_1 = new Piece(parent, null, condition);
      Piece newParent = _piece_1;
      ConditionalStatement _statement_1 = s.getStatement();
      this.prepareConditionalSymbols(_statement_1, newParent, symbols);
    }
  }
  
  public CharSequence print_Pieces(final String symbol, final String tag, final ArrayList<Piece> pieces, final boolean printType) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<");
    _builder.append(tag, "");
    _builder.append(" symbId=\"");
    _builder.append(symbol, "");
    _builder.append("\"");
    {
      if (printType) {
        _builder.append(" symbolType=\"");
        _builder.append(this.TYPE_REAL, "");
        _builder.append("\"");
      }
    }
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_Pieces = this.mathPrinter.print_Pieces(pieces);
    _builder.append(_print_Pieces, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</");
    _builder.append(tag, "");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public void defineOrderOfConditionalSymbols(final ConditionalStatement s, final HashMap<String,Integer> symbolOrders, final Integer base) {
    BlockStatement _ifStatement = s.getIfStatement();
    boolean _notEquals = (!Objects.equal(_ifStatement, null));
    if (_notEquals) {
      BlockStatement _ifStatement_1 = s.getIfStatement();
      this.addOrderOfConditionalSymbol(_ifStatement_1, symbolOrders, base, Integer.valueOf(0));
    }
    BlockStatement _elseStatement = s.getElseStatement();
    boolean _notEquals_1 = (!Objects.equal(_elseStatement, null));
    if (_notEquals_1) {
      BlockStatement _elseStatement_1 = s.getElseStatement();
      this.addOrderOfConditionalSymbol(_elseStatement_1, symbolOrders, base, Integer.valueOf(0));
    }
    Block _ifBlock = s.getIfBlock();
    boolean _notEquals_2 = (!Objects.equal(_ifBlock, null));
    if (_notEquals_2) {
      int i = 0;
      Block _ifBlock_1 = s.getIfBlock();
      EList<BlockStatement> _statements = _ifBlock_1.getStatements();
      for (final BlockStatement b : _statements) {
        {
          this.addOrderOfConditionalSymbol(b, symbolOrders, base, Integer.valueOf(i));
          int _plus = (i + 1);
          i = _plus;
        }
      }
    }
    Block _elseBlock = s.getElseBlock();
    boolean _notEquals_3 = (!Objects.equal(_elseBlock, null));
    if (_notEquals_3) {
      int i_1 = 0;
      Block _elseBlock_1 = s.getElseBlock();
      EList<BlockStatement> _statements_1 = _elseBlock_1.getStatements();
      for (final BlockStatement b_1 : _statements_1) {
        {
          this.addOrderOfConditionalSymbol(b_1, symbolOrders, base, Integer.valueOf(i_1));
          int _plus = (i_1 + 1);
          i_1 = _plus;
        }
      }
    }
  }
  
  public void addOrderOfConditionalSymbol(final BlockStatement s, final HashMap<String,Integer> symbolOrders, final Integer base, final Integer order) {
    SymbolDeclaration _symbol = s.getSymbol();
    boolean _notEquals = (!Objects.equal(_symbol, null));
    if (_notEquals) {
      SymbolDeclaration _symbol_1 = s.getSymbol();
      String _identifier = _symbol_1.getIdentifier();
      Integer prev = symbolOrders.get(_identifier);
      boolean _equals = Objects.equal(prev, null);
      if (_equals) {
        prev = Integer.valueOf(0);
      }
      int _plus = ((base).intValue() + (order).intValue());
      boolean _lessEqualsThan = ((prev).intValue() <= _plus);
      if (_lessEqualsThan) {
        SymbolDeclaration _symbol_2 = s.getSymbol();
        String _identifier_1 = _symbol_2.getIdentifier();
        int _plus_1 = ((base).intValue() + (order).intValue());
        symbolOrders.put(_identifier_1, Integer.valueOf(_plus_1));
      }
    }
    ConditionalStatement _statement = s.getStatement();
    boolean _notEquals_1 = (!Objects.equal(_statement, null));
    if (_notEquals_1) {
      ConditionalStatement _statement_1 = s.getStatement();
      int _plus_2 = ((base).intValue() + (order).intValue());
      this.defineOrderOfConditionalSymbols(_statement_1, symbolOrders, Integer.valueOf(_plus_2));
    }
  }
  
  public CharSequence print_mdef_RandomVariable(final SymbolDeclaration s) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<RandomVariable symbId=\"");
    String _identifier = s.getIdentifier();
    _builder.append(_identifier, "");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_VariabilityReference = this.print_VariabilityReference(s);
    _builder.append(_print_VariabilityReference, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    RandomList _randomList = s.getRandomList();
    CharSequence _print_uncert_Distribution = this.distributionPrinter.print_uncert_Distribution(_randomList);
    _builder.append(_print_uncert_Distribution, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</RandomVariable>");
    _builder.newLine();
    return _builder;
  }
  
  public String print_mdef_StructuralModel() {
    String model = "";
    EList<MclObject> _objects = this.mcl.getObjects();
    for (final MclObject o : _objects) {
      ModelObject _modelObject = o.getModelObject();
      boolean _notEquals = (!Objects.equal(_modelObject, null));
      if (_notEquals) {
        String variables = "";
        ModelObject _modelObject_1 = o.getModelObject();
        EList<ModelObjectBlock> _blocks = _modelObject_1.getBlocks();
        for (final ModelObjectBlock b : _blocks) {
          ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
          boolean _notEquals_1 = (!Objects.equal(_modelPredictionBlock, null));
          if (_notEquals_1) {
            ModelPredictionBlock _modelPredictionBlock_1 = b.getModelPredictionBlock();
            EList<ModelPredictionBlockStatement> _statements = _modelPredictionBlock_1.getStatements();
            for (final ModelPredictionBlockStatement st : _statements) {
              BlockStatement _statement = st.getStatement();
              boolean _notEquals_2 = (!Objects.equal(_statement, null));
              if (_notEquals_2) {
                StringConcatenation _builder = new StringConcatenation();
                BlockStatement _statement_1 = st.getStatement();
                CharSequence _print_BlockStatement = this.print_BlockStatement(_statement_1, "ct:Variable", Boolean.valueOf(true));
                _builder.append(_print_BlockStatement, "");
                String _plus = (variables + _builder);
                variables = _plus;
              } else {
                OdeBlock _odeBlock = st.getOdeBlock();
                boolean _notEquals_3 = (!Objects.equal(_odeBlock, null));
                if (_notEquals_3) {
                  OdeBlock _odeBlock_1 = st.getOdeBlock();
                  EList<BlockStatement> _statements_1 = _odeBlock_1.getStatements();
                  for (final BlockStatement s : _statements_1) {
                    StringConcatenation _builder_1 = new StringConcatenation();
                    CharSequence _print_BlockStatement_1 = this.print_BlockStatement(s, "ct:Variable", Boolean.valueOf(true));
                    _builder_1.append(_print_BlockStatement_1, "");
                    String _plus_1 = (variables + _builder_1);
                    variables = _plus_1;
                  }
                }
              }
            }
          }
        }
        StringConcatenation _builder_2 = new StringConcatenation();
        {
          int _length = variables.length();
          boolean _greaterThan = (_length > 0);
          if (_greaterThan) {
            _builder_2.append("<StructuralModel blkId=\"sm.");
            ObjectName _identifier = o.getIdentifier();
            String _name = _identifier.getName();
            _builder_2.append(_name, "");
            _builder_2.append("\">");
            _builder_2.newLineIfNotEmpty();
            _builder_2.append("\t");
            {
              int _length_1 = variables.length();
              boolean _greaterThan_1 = (_length_1 > 0);
              if (_greaterThan_1) {
                _builder_2.append(variables, "	");
              }
            }
            _builder_2.newLineIfNotEmpty();
            _builder_2.append("</StructuralModel>");
            _builder_2.newLine();
          }
        }
        String _plus_2 = (model + _builder_2);
        model = _plus_2;
      }
    }
    return model;
  }
  
  public String print_mdef_ObservationModel() {
    String model = "";
    EList<MclObject> _objects = this.mcl.getObjects();
    for (final MclObject o : _objects) {
      ModelObject _modelObject = o.getModelObject();
      boolean _notEquals = (!Objects.equal(_modelObject, null));
      if (_notEquals) {
        String statements = "";
        ModelObject _modelObject_1 = o.getModelObject();
        EList<ModelObjectBlock> _blocks = _modelObject_1.getBlocks();
        for (final ModelObjectBlock b : _blocks) {
          ObservationBlock _observationBlock = b.getObservationBlock();
          boolean _notEquals_1 = (!Objects.equal(_observationBlock, null));
          if (_notEquals_1) {
            ObservationBlock _observationBlock_1 = b.getObservationBlock();
            EList<BlockStatement> _statements = _observationBlock_1.getStatements();
            for (final BlockStatement st : _statements) {
              SymbolDeclaration _symbol = st.getSymbol();
              boolean _notEquals_2 = (!Objects.equal(_symbol, null));
              if (_notEquals_2) {
                StringConcatenation _builder = new StringConcatenation();
                SymbolDeclaration _symbol_1 = st.getSymbol();
                CharSequence _print_mdef_ObservationModel = this.print_mdef_ObservationModel(_symbol_1);
                _builder.append(_print_mdef_ObservationModel, "");
                String _plus = (statements + _builder);
                statements = _plus;
              }
            }
          }
        }
        StringConcatenation _builder_1 = new StringConcatenation();
        {
          int _length = statements.length();
          boolean _greaterThan = (_length > 0);
          if (_greaterThan) {
            _builder_1.append("<ObservationModel blkId=\"om.");
            ObjectName _identifier = o.getIdentifier();
            String _name = _identifier.getName();
            _builder_1.append(_name, "");
            _builder_1.append("\">");
            _builder_1.newLineIfNotEmpty();
            _builder_1.append("\t");
            _builder_1.append(statements, "	");
            _builder_1.newLineIfNotEmpty();
            _builder_1.append("</ObservationModel>");
            _builder_1.newLine();
          }
        }
        String _plus_1 = (model + _builder_1);
        model = _plus_1;
      }
    }
    return model;
  }
  
  public CharSequence print_VariabilityReference(final SymbolDeclaration s) {
    StringConcatenation _builder = new StringConcatenation();
    {
      RandomList _randomList = s.getRandomList();
      boolean _notEquals = (!Objects.equal(_randomList, null));
      if (_notEquals) {
        RandomList _randomList_1 = s.getRandomList();
        DistributionArguments _arguments = _randomList_1.getArguments();
        final String level = this.referenceResolver.getAttribute(_arguments, "level");
        _builder.newLineIfNotEmpty();
        {
          int _length = level.length();
          boolean _greaterThan = (_length > 0);
          if (_greaterThan) {
            _builder.append("<ct:VariabilityReference>");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("<ct:SymbRef symbIdRef=\"");
            _builder.append(level, "	");
            _builder.append("\"/>");
            _builder.newLineIfNotEmpty();
            _builder.append("</ct:VariabilityReference>");
            _builder.newLine();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence print_mdef_ObservationModel(final SymbolDeclaration s) {
    StringConcatenation _builder = new StringConcatenation();
    {
      AnyExpression _expression = s.getExpression();
      boolean _notEquals = (!Objects.equal(_expression, null));
      if (_notEquals) {
        {
          AnyExpression _expression_1 = s.getExpression();
          Expression _expression_2 = _expression_1.getExpression();
          boolean _notEquals_1 = (!Objects.equal(_expression_2, null));
          if (_notEquals_1) {
            AnyExpression _expression_3 = s.getExpression();
            Expression expr = _expression_3.getExpression();
            _builder.newLineIfNotEmpty();
            HashSet<String> randomVars = this.referenceResolver.getReferencesToRandomVars(expr);
            _builder.newLineIfNotEmpty();
            {
              int _size = randomVars.size();
              boolean _greaterThan = (_size > 0);
              if (_greaterThan) {
                {
                  for(final String ss : randomVars) {
                    final SymbolDeclaration ref = this.defineDistribution(ss);
                    _builder.newLineIfNotEmpty();
                    {
                      boolean _notEquals_2 = (!Objects.equal(ref, null));
                      if (_notEquals_2) {
                        CharSequence _print_mdef_RandomVariable = this.print_mdef_RandomVariable(ref);
                        _builder.append(_print_mdef_RandomVariable, "");
                        _builder.append("\t");
                        _builder.newLineIfNotEmpty();
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    _builder.append("<General symbId=\"");
    String _identifier = s.getIdentifier();
    _builder.append(_identifier, "");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    {
      AnyExpression _expression_4 = s.getExpression();
      Expression _expression_5 = _expression_4.getExpression();
      boolean _notEquals_3 = (!Objects.equal(_expression_5, null));
      if (_notEquals_3) {
        _builder.append("\t");
        AnyExpression _expression_6 = s.getExpression();
        Expression _expression_7 = _expression_6.getExpression();
        CharSequence _print_Assign = this.mathPrinter.print_Assign(_expression_7);
        _builder.append(_print_Assign, "	");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</General>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_mdef_ErrorModel(final Expression expr) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ErrorModel>");
    _builder.newLine();
    {
      boolean _notEquals = (!Objects.equal(expr, null));
      if (_notEquals) {
        _builder.append("\t");
        CharSequence _print_Assign = this.mathPrinter.print_Assign(expr);
        _builder.append(_print_Assign, "	");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</ErrorModel>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_InitialCondition(final SymbolDeclaration s) {
    StringConcatenation _builder = new StringConcatenation();
    {
      AnyExpression _expression = s.getExpression();
      boolean _notEquals = (!Objects.equal(_expression, null));
      if (_notEquals) {
        {
          AnyExpression _expression_1 = s.getExpression();
          OdeList _odeList = _expression_1.getOdeList();
          boolean _notEquals_1 = (!Objects.equal(_odeList, null));
          if (_notEquals_1) {
            AnyExpression _expression_2 = s.getExpression();
            OdeList _odeList_1 = _expression_2.getOdeList();
            Arguments _arguments = _odeList_1.getArguments();
            AnyExpression init = this.referenceResolver.getAttributeExpression(_arguments, "init");
            _builder.newLineIfNotEmpty();
            {
              boolean _notEquals_2 = (!Objects.equal(init, null));
              if (_notEquals_2) {
                {
                  Expression _expression_3 = init.getExpression();
                  boolean _notEquals_3 = (!Objects.equal(_expression_3, null));
                  if (_notEquals_3) {
                    _builder.append("<InitialCondition symbID=\"");
                    String _identifier = s.getIdentifier();
                    _builder.append(_identifier, "");
                    _builder.append("\">");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    Expression _expression_4 = init.getExpression();
                    CharSequence _print_Math_Expr = this.mathPrinter.print_Math_Expr(_expression_4);
                    _builder.append(_print_Math_Expr, "	");
                    _builder.newLineIfNotEmpty();
                    _builder.append("</InitialCondition>");
                    _builder.newLine();
                  }
                }
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public SymbolDeclaration defineDistribution(final String ref) {
    EList<MclObject> _objects = this.mcl.getObjects();
    for (final MclObject o : _objects) {
      ModelObject _modelObject = o.getModelObject();
      boolean _notEquals = (!Objects.equal(_modelObject, null));
      if (_notEquals) {
        ModelObject _modelObject_1 = o.getModelObject();
        EList<ModelObjectBlock> _blocks = _modelObject_1.getBlocks();
        for (final ModelObjectBlock b : _blocks) {
          RandomVariableDefinitionBlock _randomVariableDefinitionBlock = b.getRandomVariableDefinitionBlock();
          boolean _notEquals_1 = (!Objects.equal(_randomVariableDefinitionBlock, null));
          if (_notEquals_1) {
            RandomVariableDefinitionBlock _randomVariableDefinitionBlock_1 = b.getRandomVariableDefinitionBlock();
            EList<SymbolDeclaration> _variables = _randomVariableDefinitionBlock_1.getVariables();
            for (final SymbolDeclaration s : _variables) {
              String _identifier = s.getIdentifier();
              boolean _equalsIgnoreCase = _identifier.equalsIgnoreCase(ref);
              if (_equalsIgnoreCase) {
                return s;
              }
            }
          }
        }
      }
    }
    return null;
  }
  
  public CharSequence print_design_TrialDesign() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<TrialDesign>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _print_design_Structure = this.print_design_Structure();
    _builder.append(_print_design_Structure, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_design_Population = this.print_design_Population();
    _builder.append(_print_design_Population, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_design_IndividualDosing = this.print_design_IndividualDosing();
    _builder.append(_print_design_IndividualDosing, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</TrialDesign>\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_design_Structure() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Structure>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _print_design_Epoch = this.print_design_Epoch();
    _builder.append(_print_design_Epoch, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_design_Arms = this.print_design_Arms();
    _builder.append(_print_design_Arms, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_design_Cells = this.print_design_Cells();
    _builder.append(_print_design_Cells, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_design_Segments = this.print_design_Segments();
    _builder.append(_print_design_Segments, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_design_Activities = this.print_design_Activities();
    _builder.append(_print_design_Activities, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</Structure>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_design_Epoch() {
    return this.print_design_Epoch("", "", "", "");
  }
  
  public CharSequence print_design_Epoch(final String name, final String start, final String end, final String order) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Epoch oid=\"");
    _builder.append(name, "");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_design_Start = this.print_design_Start(start);
    _builder.append(_print_design_Start, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_design_End = this.print_design_End(start);
    _builder.append(_print_design_End, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<Order>");
    _builder.append(order, "	");
    _builder.append("</Order>");
    _builder.newLineIfNotEmpty();
    _builder.append("</Epoch>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_design_Start(final String value) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Start>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<ct:Real");
    _builder.append(value, "	");
    _builder.append("></ct:Real>");
    _builder.newLineIfNotEmpty();
    _builder.append("</Start>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_design_End(final String value) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<End>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<ct:Real");
    _builder.append(value, "	");
    _builder.append("></ct:Real>");
    _builder.newLineIfNotEmpty();
    _builder.append("</End>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_design_Arms() {
    CharSequence _print_design_Arm = this.print_design_Arm("");
    return _print_design_Arm;
  }
  
  public CharSequence print_design_Arm(final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Arm oid=");
    _builder.append(name, "");
    _builder.append("/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print_design_Cells() {
    CharSequence _print_design_Cell = this.print_design_Cell("", "", "", "");
    return _print_design_Cell;
  }
  
  public CharSequence print_design_Cell(final String name, final String epochRef, final String armRef, final String segmentRef) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Cell oid=\"");
    _builder.append(name, "");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<EpochRef oidRef=\"");
    _builder.append(epochRef, "	");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<ArmRef oidRef=\"");
    _builder.append(armRef, "	");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<SegmentRef oidRef=\"");
    _builder.append(segmentRef, "	");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    _builder.append("</Cell>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_design_Segments() {
    CharSequence _print_design_Segment = this.print_design_Segment("", "");
    return _print_design_Segment;
  }
  
  public CharSequence print_design_Segment(final String name, final String activityRef) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Segment oid=\"");
    _builder.append(name, "");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    {
      boolean _equals = activityRef.equals("");
      boolean _not = (!_equals);
      if (_not) {
        _builder.append("\t");
        _builder.append("<ActivityRef oidRef=\"");
        _builder.append(activityRef, "	");
        _builder.append("\"/>");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</Segment>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_design_Activities() {
    CharSequence _print_design_Activity = this.print_design_Activity("");
    return _print_design_Activity;
  }
  
  public CharSequence print_design_Activity(final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Activity oid=\"");
    _builder.append(name, "");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_design_Bolus = this.print_design_Bolus();
    _builder.append(_print_design_Bolus, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</Activity>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_design_Bolus() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Bolus>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _print_design_DoseAmount = this.print_design_DoseAmount();
    _builder.append(_print_design_DoseAmount, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_design_DosingTimes = this.print_design_DosingTimes(null);
    _builder.append(_print_design_DosingTimes, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_design_SteadyState = this.print_design_SteadyState(null, null);
    _builder.append(_print_design_SteadyState, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</Bolus>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_design_DoseAmount() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<DoseAmount inputType=\"target\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<ct:SymbRef symbIdRef=\"\" blkIdRef=\"\"/>");
    _builder.newLine();
    _builder.append("</DoseAmount>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_design_DosingTimes(final SymbolDeclaration s) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<DosingTimes>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _print_Assign = this.print_Assign(s, "");
    _builder.append(_print_Assign, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</DosingTimes>\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Assign(final SymbolDeclaration s, final String blkIdRef) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _notEquals = (!Objects.equal(s, null));
      if (_notEquals) {
        _builder.append("<ct:SymbRef symbIdRef=\"");
        String _identifier = s.getIdentifier();
        _builder.append(_identifier, "");
        {
          int _length = blkIdRef.length();
          boolean _greaterThan = (_length > 0);
          if (_greaterThan) {
            _builder.append(" blkIdRef=\"");
            _builder.append(blkIdRef, "");
            _builder.append("\"");
          }
        }
        _builder.append("\"/>");
        _builder.newLineIfNotEmpty();
        {
          AnyExpression _expression = s.getExpression();
          Expression _expression_1 = _expression.getExpression();
          boolean _notEquals_1 = (!Objects.equal(_expression_1, null));
          if (_notEquals_1) {
            AnyExpression _expression_2 = s.getExpression();
            Expression _expression_3 = _expression_2.getExpression();
            CharSequence _print_Assign = this.mathPrinter.print_Assign(_expression_3);
            _builder.append(_print_Assign, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence print_Assign(final SymbolModification s) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _notEquals = (!Objects.equal(s, null));
      if (_notEquals) {
        FullyQualifiedSymbolName _identifier = s.getIdentifier();
        CharSequence _print_ct_SymbolRef = this.mathPrinter.print_ct_SymbolRef(_identifier);
        _builder.append(_print_ct_SymbolRef, "");
        _builder.newLineIfNotEmpty();
        List _list = s.getList();
        Arguments _arguments = _list.getArguments();
        final AnyExpression value = this.referenceResolver.getAttributeExpression(_arguments, "value");
        _builder.newLineIfNotEmpty();
        {
          boolean _notEquals_1 = (!Objects.equal(value, null));
          if (_notEquals_1) {
            {
              Expression _expression = value.getExpression();
              boolean _notEquals_2 = (!Objects.equal(_expression, null));
              if (_notEquals_2) {
                Expression _expression_1 = value.getExpression();
                CharSequence _print_Assign = this.mathPrinter.print_Assign(_expression_1);
                _builder.append(_print_Assign, "");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence print_design_SteadyState(final SymbolDeclaration endTime, final SymbolDeclaration interval) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<SteadyState>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _print_design_EndTime = this.print_design_EndTime(endTime);
    _builder.append(_print_design_EndTime, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_design_Interval = this.print_design_Interval(interval);
    _builder.append(_print_design_Interval, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</SteadyState>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_design_EndTime(final SymbolDeclaration s) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<EndTime>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _print_Assign = this.print_Assign(s, "");
    _builder.append(_print_Assign, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</EndTime>\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_design_Interval(final SymbolDeclaration s) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Interval>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _print_Assign = this.print_Assign(s, "");
    _builder.append(_print_Assign, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</Interval>\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_design_Population() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Population>");
    _builder.newLine();
    _builder.append("\t");
    String _print_design_IndividualTemplate = this.print_design_IndividualTemplate();
    _builder.append(_print_design_IndividualTemplate, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_design_DataSet = this.print_design_DataSet();
    _builder.append(_print_design_DataSet, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</Population>");
    _builder.newLine();
    return _builder;
  }
  
  public String print_design_IndividualTemplate() {
    String mappings = "";
    EList<MclObject> _objects = this.mcl.getObjects();
    for (final MclObject obj : _objects) {
      ModelObject _modelObject = obj.getModelObject();
      boolean _notEquals = (!Objects.equal(_modelObject, null));
      if (_notEquals) {
        ModelObject _modelObject_1 = obj.getModelObject();
        EList<ModelObjectBlock> _blocks = _modelObject_1.getBlocks();
        for (final ModelObjectBlock block : _blocks) {
          InputVariablesBlock _inputVariablesBlock = block.getInputVariablesBlock();
          boolean _notEquals_1 = (!Objects.equal(_inputVariablesBlock, null));
          if (_notEquals_1) {
            InputVariablesBlock _inputVariablesBlock_1 = block.getInputVariablesBlock();
            EList<SymbolDeclaration> _variables = _inputVariablesBlock_1.getVariables();
            for (final SymbolDeclaration s : _variables) {
              AnyExpression _expression = s.getExpression();
              boolean _notEquals_2 = (!Objects.equal(_expression, null));
              if (_notEquals_2) {
                AnyExpression _expression_1 = s.getExpression();
                List _list = _expression_1.getList();
                boolean _notEquals_3 = (!Objects.equal(_list, null));
                if (_notEquals_3) {
                  AnyExpression _expression_2 = s.getExpression();
                  List _list_1 = _expression_2.getList();
                  Arguments _arguments = _list_1.getArguments();
                  String use = this.referenceResolver.getAttribute(_arguments, "use");
                  int _length = use.length();
                  boolean _greaterThan = (_length > 0);
                  if (_greaterThan) {
                    boolean _equals = use.equals("id");
                    if (_equals) {
                      String _identifier = s.getIdentifier();
                      CharSequence _print_design_Mapping = this.print_design_Mapping("IndividualMapping", _identifier);
                      String _plus = (mappings + _print_design_Mapping);
                      mappings = _plus;
                    }
                    boolean _equals_1 = use.equals("amt");
                    if (_equals_1) {
                      String _identifier_1 = s.getIdentifier();
                      CharSequence _print_design_Mapping_1 = this.print_design_Mapping("ArmMapping", _identifier_1);
                      String _plus_1 = (mappings + _print_design_Mapping_1);
                      mappings = _plus_1;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<IndividualTemplate>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(mappings, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</IndividualTemplate>");
    _builder.newLine();
    return _builder.toString();
  }
  
  public CharSequence print_design_Mapping(final String mappingType, final String ref) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<");
    _builder.append(mappingType, "");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<ColumnRef xmlns=\"");
    _builder.append(this.xmlns_ds, "	");
    _builder.append("\" columnIdRef=\"");
    _builder.append(ref, "	");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    _builder.append("</");
    _builder.append(mappingType, "");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print_design_DataSet() {
    CharSequence _xblockexpression = null;
    {
      String[] names = null;
      String[] types = null;
      String definition = this.print_Columns(names, types);
      String table = "";
      CharSequence _print_DataSet = this.print_DataSet(definition, table);
      _xblockexpression = (_print_DataSet);
    }
    return _xblockexpression;
  }
  
  public CharSequence print_design_IndividualDosing() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<IndividualDosing>");
    _builder.newLine();
    _builder.append("</IndividualDosing>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_msteps_ModellingSteps() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ModellingSteps>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _print_msteps_EstimationStep = this.print_msteps_EstimationStep();
    _builder.append(_print_msteps_EstimationStep, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_msteps_SimulationStep = this.print_msteps_SimulationStep();
    _builder.append(_print_msteps_SimulationStep, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _print_msteps_StepDependencies = this.print_msteps_StepDependencies();
    _builder.append(_print_msteps_StepDependencies, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</ModellingSteps>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_msteps_EstimationStep() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<EstimationStep>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<Description>MDL source?</Description>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _print_msteps_ObjectiveDataSet = this.print_msteps_ObjectiveDataSet();
    _builder.append(_print_msteps_ObjectiveDataSet, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</EstimationStep>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_msteps_SimulationStep() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<SimulationStep>\t\t");
    _builder.newLine();
    _builder.append("\t");
    Object _print_msteps_VariableAssignments = this.print_msteps_VariableAssignments();
    _builder.append(_print_msteps_VariableAssignments, "	");
    _builder.append("\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    Object _print_msteps_Observations = this.print_msteps_Observations();
    _builder.append(_print_msteps_Observations, "	");
    _builder.append("\t");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("</SimulationStep>");
    _builder.newLine();
    return _builder;
  }
  
  public Object print_msteps_VariableAssignments() {
    return null;
  }
  
  public CharSequence print_msteps_VariableAssignment(final SymbolDeclaration s, final String blockId) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ct:VariableAssignment>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _print_Assign = this.print_Assign(s, "");
    _builder.append(_print_Assign, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</ct:VariableAssignment>");
    _builder.newLine();
    return _builder;
  }
  
  public Object print_msteps_Observations() {
    return null;
  }
  
  public CharSequence print_msteps_Observation(final String ref, final String blockID) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Observations>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<Timepoints>");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _print_ct_Sequence = this.mathPrinter.print_ct_Sequence("", "", "");
    _builder.append(_print_ct_Sequence, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("</Timepoints>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<Continuous>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<ct:SymbRef symbIdRef=\"");
    _builder.append(ref, "		");
    _builder.append("\"");
    {
      int _length = blockID.length();
      boolean _greaterThan = (_length > 0);
      if (_greaterThan) {
        _builder.append(" blkIdRef=\"");
        _builder.append(blockID, "		");
        _builder.append("\"");
      }
    }
    _builder.append("/>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("</Continuous>");
    _builder.newLine();
    _builder.append("</Observations>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_msteps_StepDependencies() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<StepDependencies>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<Description>MDL source?</Description>");
    _builder.newLine();
    _builder.append("</StepDependencies>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_msteps_Step(final String ref) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Step>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<ct:OidRef oidRef=\"");
    _builder.append(ref, "	");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    _builder.append("</Step>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_msteps_ObjectiveDataSet() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ObjectiveDataSet dataSetRef=\"\">");
    _builder.newLine();
    {
      EList<MclObject> _objects = this.mcl.getObjects();
      for(final MclObject o : _objects) {
        _builder.append("\t");
        {
          ModelObject _modelObject = o.getModelObject();
          boolean _notEquals = (!Objects.equal(_modelObject, null));
          if (_notEquals) {
            ModelObject _modelObject_1 = o.getModelObject();
            CharSequence _print_msteps_DataSet = this.print_msteps_DataSet(_modelObject_1);
            _builder.append(_print_msteps_DataSet, "	");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</ObjectiveDataSet>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_msteps_DataSet(final ModelObject obj) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<String> _arrayList = new ArrayList<String>();
      ArrayList<String> names = _arrayList;
      ArrayList<String> _arrayList_1 = new ArrayList<String>();
      ArrayList<String> types = _arrayList_1;
      EList<ModelObjectBlock> _blocks = obj.getBlocks();
      for (final ModelObjectBlock b : _blocks) {
        InputVariablesBlock _inputVariablesBlock = b.getInputVariablesBlock();
        boolean _notEquals = (!Objects.equal(_inputVariablesBlock, null));
        if (_notEquals) {
          InputVariablesBlock _inputVariablesBlock_1 = b.getInputVariablesBlock();
          EList<SymbolDeclaration> _variables = _inputVariablesBlock_1.getVariables();
          boolean _notEquals_1 = (!Objects.equal(_variables, null));
          if (_notEquals_1) {
            InputVariablesBlock _inputVariablesBlock_2 = b.getInputVariablesBlock();
            EList<SymbolDeclaration> _variables_1 = _inputVariablesBlock_2.getVariables();
            for (final SymbolDeclaration s : _variables_1) {
              {
                String _identifier = s.getIdentifier();
                names.add(_identifier);
                types.add(this.TYPE_INT);
              }
            }
          }
        }
      }
      String fileName = this.referenceResolver.getDataSource(this.mcl);
      CharSequence _xifexpression = null;
      int _length = fileName.length();
      boolean _greaterThan = (_length > 0);
      if (_greaterThan) {
        CharSequence _xblockexpression_1 = null;
        {
          ArrayList<String[]> values = this.getDataFileContent(fileName);
          boolean _equals = Objects.equal(values, null);
          if (_equals) {
            final int dotIndex = fileName.indexOf(".");
            String fileExtension = "";
            boolean _greaterThan_1 = (dotIndex > 0);
            if (_greaterThan_1) {
              int _plus = (dotIndex + 1);
              String _substring = fileName.substring(_plus);
              fileExtension = _substring;
            }
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("<Description>Source file not found (");
            _builder.append(fileName, "");
            _builder.append(")!</Description>");
            _builder.newLineIfNotEmpty();
            _builder.append("<ExternalSource url=\"file=///");
            _builder.append(fileName, "");
            _builder.append("\" format=\"");
            _builder.append(fileExtension, "");
            _builder.append("\"/>\t");
            _builder.newLineIfNotEmpty();
            return _builder.toString();
          }
          CharSequence _print_DataSet = this.print_DataSet(names, types, values);
          _xblockexpression_1 = (_print_DataSet);
        }
        _xifexpression = _xblockexpression_1;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public ArrayList<String[]> getDataFileContent(final String fileName) {
    try {
      ArrayList<String[]> _arrayList = new ArrayList<String[]>();
      ArrayList<String[]> values = _arrayList;
      BufferedReader fileReader = null;
      Resource _eResource = this.mcl.eResource();
      URI _uRI = _eResource.getURI();
      String modelPath = _uRI.toPlatformString(true);
      File _file = new File(modelPath);
      File file = _file;
      String _parent = file.getParent();
      String _plus = (_parent + "\\");
      String dataPath = (_plus + fileName);
      try {
        FileReader _fileReader = new FileReader(dataPath);
        BufferedReader _bufferedReader = new BufferedReader(_fileReader);
        fileReader = _bufferedReader;
      } catch (final Throwable _t) {
        if (_t instanceof FileNotFoundException) {
          final FileNotFoundException e = (FileNotFoundException)_t;
          String _parent_1 = file.getParent();
          String _plus_1 = (_parent_1 + "\\data\\");
          String _plus_2 = (_plus_1 + fileName);
          dataPath = _plus_2;
          try {
            FileReader _fileReader_1 = new FileReader(dataPath);
            BufferedReader _bufferedReader_1 = new BufferedReader(_fileReader_1);
            fileReader = _bufferedReader_1;
          } catch (final Throwable _t_1) {
            if (_t_1 instanceof FileNotFoundException) {
              final FileNotFoundException e1 = (FileNotFoundException)_t_1;
              return null;
            } else {
              throw Exceptions.sneakyThrow(_t_1);
            }
          }
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      boolean _notEquals = (!Objects.equal(fileReader, null));
      if (_notEquals) {
        boolean _ready = fileReader.ready();
        if (_ready) {
          String line = "";
          String _readLine = fileReader.readLine();
          String _line = line = _readLine;
          boolean _notEquals_1 = (!Objects.equal(_line, null));
          boolean _while = _notEquals_1;
          while (_while) {
            {
              final String[] atoms = line.split("\\s{1,}|,|;");
              values.add(atoms);
            }
            String _readLine_1 = fileReader.readLine();
            String _line_1 = line = _readLine_1;
            boolean _notEquals_2 = (!Objects.equal(_line_1, null));
            _while = _notEquals_2;
          }
          fileReader.close();
        }
      }
      return values;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public CharSequence print_DataSet(final ArrayList<String> names, final ArrayList<String> types, final ArrayList<String[]> values) {
    CharSequence _xblockexpression = null;
    {
      String table = "";
      boolean _notEquals = (!Objects.equal(values, null));
      if (_notEquals) {
        for (final String[] row : values) {
          String _print_Row = this.print_Row(row);
          String _plus = (table + _print_Row);
          table = _plus;
        }
      }
      String definition = this.print_Columns(((String[])Conversions.unwrapArray(names, String.class)), ((String[])Conversions.unwrapArray(types, String.class)));
      CharSequence _print_DataSet = this.print_DataSet(definition, table);
      _xblockexpression = (_print_DataSet);
    }
    return _xblockexpression;
  }
  
  public String print_Columns(final String[] names, final String[] types) {
    boolean _equals = Objects.equal(names, null);
    if (_equals) {
      return "";
    }
    String definition = "";
    int _size = ((java.util.List<String>)Conversions.doWrapArray(names)).size();
    int _minus = (_size - 1);
    IntegerRange _upTo = new IntegerRange(0, _minus);
    for (final Integer i : _upTo) {
      {
        String type = "";
        boolean _notEquals = (!Objects.equal(type, null));
        if (_notEquals) {
          int _size_1 = ((java.util.List<String>)Conversions.doWrapArray(types)).size();
          boolean _greaterThan = (_size_1 > (i).intValue());
          if (_greaterThan) {
            String _get = types[(i).intValue()];
            type = _get;
          }
        }
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<ds:Column columnId=\"");
        String _get_1 = names[(i).intValue()];
        _builder.append(_get_1, "");
        _builder.newLineIfNotEmpty();
        _builder.append("\"");
        {
          int _length = type.length();
          boolean _greaterThan_1 = (_length > 0);
          if (_greaterThan_1) {
            _builder.append(" valueType=\"");
            _builder.append(type, "");
            _builder.append("\"");
          }
        }
        _builder.append(" columnNum=\"");
        _builder.append(i, "");
        _builder.append("\"/>");
        _builder.newLineIfNotEmpty();
        String _plus = (definition + _builder);
        definition = _plus;
      }
    }
    return definition;
  }
  
  public String print_Row(final String[] atoms) {
    String row = "";
    Iterator<String> iterator = ((java.util.List<String>)Conversions.doWrapArray(atoms)).iterator();
    int _size = IteratorExtensions.size(iterator);
    int _minus = (_size - 1);
    IntegerRange _upTo = new IntegerRange(0, _minus);
    for (final Integer i : _upTo) {
      String _next = iterator.next();
      String _print_ct_Value = this.mathPrinter.print_ct_Value(_next);
      String _plus = (row + _print_ct_Value);
      row = _plus;
    }
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Row>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(row, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</Row>");
    _builder.newLine();
    row = _builder.toString();
    return row;
  }
  
  public CharSequence print_DataSet(final String definition, final String table) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<DataSet>");
    _builder.newLine();
    {
      int _length = definition.length();
      boolean _greaterThan = (_length > 0);
      if (_greaterThan) {
        _builder.append("\t");
        _builder.append("<ds:Definition>");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append(definition, "		");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("</ds:Definition>");
        _builder.newLine();
      }
    }
    {
      int _length_1 = table.length();
      boolean _greaterThan_1 = (_length_1 > 0);
      if (_greaterThan_1) {
        _builder.append("\t");
        _builder.append("<ds:Table>\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append(table, "		");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("</ds:Table>");
        _builder.newLine();
      }
    }
    _builder.append("</DataSet>\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_XS_Comment(final String text) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<!--");
    _builder.append(text, "");
    _builder.append("-->");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print_ct_Annotation(final String text) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Description>");
    _builder.append(text, "");
    _builder.append("</Description>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print_BlockStatement(final BlockStatement st, final String tag, final Boolean printType) {
    StringConcatenation _builder = new StringConcatenation();
    {
      SymbolDeclaration _symbol = st.getSymbol();
      boolean _notEquals = (!Objects.equal(_symbol, null));
      if (_notEquals) {
        _builder.append("<");
        _builder.append(tag, "");
        _builder.append(" symbId=\"");
        SymbolDeclaration _symbol_1 = st.getSymbol();
        String _identifier = _symbol_1.getIdentifier();
        _builder.append(_identifier, "");
        _builder.append("\"");
        {
          if ((printType).booleanValue()) {
            _builder.append(" symbolType=\"");
            _builder.append(this.TYPE_REAL, "");
            _builder.append("\"");
          }
        }
        _builder.append(">");
        _builder.newLineIfNotEmpty();
        {
          SymbolDeclaration _symbol_2 = st.getSymbol();
          AnyExpression _expression = _symbol_2.getExpression();
          boolean _notEquals_1 = (!Objects.equal(_expression, null));
          if (_notEquals_1) {
            {
              SymbolDeclaration _symbol_3 = st.getSymbol();
              AnyExpression _expression_1 = _symbol_3.getExpression();
              Expression _expression_2 = _expression_1.getExpression();
              boolean _notEquals_2 = (!Objects.equal(_expression_2, null));
              if (_notEquals_2) {
                _builder.append("\t");
                SymbolDeclaration _symbol_4 = st.getSymbol();
                AnyExpression _expression_3 = _symbol_4.getExpression();
                Expression _expression_4 = _expression_3.getExpression();
                CharSequence _print_Assign = this.mathPrinter.print_Assign(_expression_4);
                _builder.append(_print_Assign, "	");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("</");
        _builder.append(tag, "");
        _builder.append(">");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      ConditionalStatement _statement = st.getStatement();
      boolean _notEquals_3 = (!Objects.equal(_statement, null));
      if (_notEquals_3) {
        ConditionalStatement _statement_1 = st.getStatement();
        String _print_ConditionalStatement = this.print_ConditionalStatement(_statement_1, tag);
        _builder.append(_print_ConditionalStatement, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
}
