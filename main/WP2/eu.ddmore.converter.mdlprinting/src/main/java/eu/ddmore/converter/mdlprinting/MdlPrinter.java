/**
 * MDL converter toolbox
 * @DDMoRe
 * Author: Natallia Kokash, LIACS, 2012
 */
package eu.ddmore.converter.mdlprinting;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.Block;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.Categorical;
import org.ddmore.mdl.mdl.ConditionalExpression;
import org.ddmore.mdl.mdl.ConditionalStatement;
import org.ddmore.mdl.mdl.Continuous;
import org.ddmore.mdl.mdl.Covariate;
import org.ddmore.mdl.mdl.DataObject;
import org.ddmore.mdl.mdl.DataObjectBlock;
import org.ddmore.mdl.mdl.DiagBlock;
import org.ddmore.mdl.mdl.Distribution;
import org.ddmore.mdl.mdl.EnumType;
import org.ddmore.mdl.mdl.EstimateTask;
import org.ddmore.mdl.mdl.EstimationBlock;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.FileBlock;
import org.ddmore.mdl.mdl.FileBlockStatement;
import org.ddmore.mdl.mdl.FormalArgument;
import org.ddmore.mdl.mdl.FormalArguments;
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName;
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.FunctionCallStatement;
import org.ddmore.mdl.mdl.GroupVariablesBlock;
import org.ddmore.mdl.mdl.GroupVariablesBlockStatement;
import org.ddmore.mdl.mdl.HeaderBlock;
import org.ddmore.mdl.mdl.ImportBlock;
import org.ddmore.mdl.mdl.IndividualVariablesBlock;
import org.ddmore.mdl.mdl.LibraryBlock;
import org.ddmore.mdl.mdl.Likelyhood;
import org.ddmore.mdl.mdl.List;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.MatrixBlock;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.MixtureBlock;
import org.ddmore.mdl.mdl.ModelObject;
import org.ddmore.mdl.mdl.ModelObjectBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlockStatement;
import org.ddmore.mdl.mdl.MultiplicativeExpression;
import org.ddmore.mdl.mdl.ObjectName;
import org.ddmore.mdl.mdl.ObservationBlock;
import org.ddmore.mdl.mdl.OdeBlock;
import org.ddmore.mdl.mdl.OdeList;
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.OutputVariablesBlock;
import org.ddmore.mdl.mdl.ParExpression;
import org.ddmore.mdl.mdl.ParameterDeclaration;
import org.ddmore.mdl.mdl.ParameterObject;
import org.ddmore.mdl.mdl.ParameterObjectBlock;
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.Primary;
import org.ddmore.mdl.mdl.PriorParametersBlock;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.RandomVariableDefinitionBlock;
import org.ddmore.mdl.mdl.SameBlock;
import org.ddmore.mdl.mdl.Selector;
import org.ddmore.mdl.mdl.SimulationBlock;
import org.ddmore.mdl.mdl.StructuralBlock;
import org.ddmore.mdl.mdl.StructuralParametersBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.SymbolModification;
import org.ddmore.mdl.mdl.TELObject;
import org.ddmore.mdl.mdl.TargetBlock;
import org.ddmore.mdl.mdl.TargetLanguage;
import org.ddmore.mdl.mdl.TaskObject;
import org.ddmore.mdl.mdl.TaskObjectBlock;
import org.ddmore.mdl.mdl.UnaryExpression;
import org.ddmore.mdl.mdl.UseType;
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VariabilityBlockStatement;
import org.ddmore.mdl.mdl.Vector;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class MdlPrinter {
  protected HashMap<String,HashMap<String,String>> externalFunctions = new Function0<HashMap<String,HashMap<String,String>>>() {
    public HashMap<String,HashMap<String,String>> apply() {
      HashMap<String,HashMap<String,String>> _hashMap = new HashMap<String,HashMap<String,String>>();
      return _hashMap;
    }
  }.apply();
  
  protected HashMap<String,ArrayList<String>> externalCodeStart = new Function0<HashMap<String,ArrayList<String>>>() {
    public HashMap<String,ArrayList<String>> apply() {
      HashMap<String,ArrayList<String>> _hashMap = new HashMap<String,ArrayList<String>>();
      return _hashMap;
    }
  }.apply();
  
  protected HashMap<String,ArrayList<String>> externalCodeEnd = new Function0<HashMap<String,ArrayList<String>>>() {
    public HashMap<String,ArrayList<String>> apply() {
      HashMap<String,ArrayList<String>> _hashMap = new HashMap<String,ArrayList<String>>();
      return _hashMap;
    }
  }.apply();
  
  protected HashMap<Object,Object> eta_vars = new Function0<HashMap<Object,Object>>() {
    public HashMap<Object,Object> apply() {
      HashMap<Object,Object> _newHashMap = CollectionLiterals.<Object, Object>newHashMap();
      return _newHashMap;
    }
  }.apply();
  
  protected HashMap<Object,Object> eps_vars = new Function0<HashMap<Object,Object>>() {
    public HashMap<Object,Object> apply() {
      HashMap<Object,Object> _newHashMap = CollectionLiterals.<Object, Object>newHashMap();
      return _newHashMap;
    }
  }.apply();
  
  protected HashMap<Object,Object> theta_vars = new Function0<HashMap<Object,Object>>() {
    public HashMap<Object,Object> apply() {
      HashMap<Object,Object> _newHashMap = CollectionLiterals.<Object, Object>newHashMap();
      return _newHashMap;
    }
  }.apply();
  
  protected HashMap<Object,Object> dadt_vars = new Function0<HashMap<Object,Object>>() {
    public HashMap<Object,Object> apply() {
      HashMap<Object,Object> _newHashMap = CollectionLiterals.<Object, Object>newHashMap();
      return _newHashMap;
    }
  }.apply();
  
  protected HashMap<Object,Object> init_vars = new Function0<HashMap<Object,Object>>() {
    public HashMap<Object,Object> apply() {
      HashMap<Object,Object> _newHashMap = CollectionLiterals.<Object, Object>newHashMap();
      return _newHashMap;
    }
  }.apply();
  
  protected HashMap<String,Integer> namedOmegaBlocks = new Function0<HashMap<String,Integer>>() {
    public HashMap<String,Integer> apply() {
      HashMap<String,Integer> _hashMap = new HashMap<String,Integer>();
      return _hashMap;
    }
  }.apply();
  
  protected HashMap<String,Integer> namedSigmaBlocks = new Function0<HashMap<String,Integer>>() {
    public HashMap<String,Integer> apply() {
      HashMap<String,Integer> _hashMap = new HashMap<String,Integer>();
      return _hashMap;
    }
  }.apply();
  
  protected void clearCollections() {
    this.init_vars.clear();
    this.dadt_vars.clear();
    this.theta_vars.clear();
    this.eta_vars.clear();
    this.eps_vars.clear();
    this.namedOmegaBlocks.clear();
    this.namedSigmaBlocks.clear();
  }
  
  protected void prepareCollections(final Mcl m) {
    this.clearCollections();
    EList<MclObject> _objects = m.getObjects();
    for (final MclObject o : _objects) {
      ModelObject _modelObject = o.getModelObject();
      boolean _notEquals = (!Objects.equal(_modelObject, null));
      if (_notEquals) {
        ModelObject _modelObject_1 = o.getModelObject();
        this.setRandomVariables(_modelObject_1);
        ModelObject _modelObject_2 = o.getModelObject();
        this.setStructuralParameters(_modelObject_2);
        ModelObject _modelObject_3 = o.getModelObject();
        this.setModelPredictionVariables(_modelObject_3);
        ModelObject _modelObject_4 = o.getModelObject();
        this.setInitialConditions(_modelObject_4);
      }
    }
  }
  
  protected void setInitialConditions(final ModelObject o) {
    int i = 1;
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock b : _blocks) {
      ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
      boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
      if (_notEquals) {
        ModelPredictionBlock _modelPredictionBlock_1 = b.getModelPredictionBlock();
        EList<ModelPredictionBlockStatement> _statements = _modelPredictionBlock_1.getStatements();
        for (final ModelPredictionBlockStatement s : _statements) {
          OdeBlock _odeBlock = s.getOdeBlock();
          boolean _notEquals_1 = (!Objects.equal(_odeBlock, null));
          if (_notEquals_1) {
            OdeBlock _odeBlock_1 = s.getOdeBlock();
            EList<BlockStatement> _statements_1 = _odeBlock_1.getStatements();
            for (final BlockStatement ss : _statements_1) {
              SymbolDeclaration _symbol = ss.getSymbol();
              boolean _notEquals_2 = (!Objects.equal(_symbol, null));
              if (_notEquals_2) {
                SymbolDeclaration _symbol_1 = ss.getSymbol();
                AnyExpression _expression = _symbol_1.getExpression();
                boolean _notEquals_3 = (!Objects.equal(_expression, null));
                if (_notEquals_3) {
                  SymbolDeclaration _symbol_2 = ss.getSymbol();
                  AnyExpression _expression_1 = _symbol_2.getExpression();
                  OdeList _odeList = _expression_1.getOdeList();
                  boolean _notEquals_4 = (!Objects.equal(_odeList, null));
                  if (_notEquals_4) {
                    SymbolDeclaration _symbol_3 = ss.getSymbol();
                    AnyExpression _expression_2 = _symbol_3.getExpression();
                    OdeList _odeList_1 = _expression_2.getOdeList();
                    Arguments _arguments = _odeList_1.getArguments();
                    final String initCond = this.getAttribute(_arguments, "init");
                    boolean _equals = initCond.equals("");
                    boolean _not = (!_equals);
                    if (_not) {
                      this.init_vars.put(Integer.valueOf(i), initCond);
                    } else {
                      this.init_vars.put(Integer.valueOf(i), "0");
                    }
                    int _plus = (i + 1);
                    i = _plus;
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  protected void setModelPredictionVariables(final ModelObject o) {
    int i = 1;
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock b : _blocks) {
      ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
      boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
      if (_notEquals) {
        ModelPredictionBlock _modelPredictionBlock_1 = b.getModelPredictionBlock();
        EList<ModelPredictionBlockStatement> _statements = _modelPredictionBlock_1.getStatements();
        for (final ModelPredictionBlockStatement s : _statements) {
          OdeBlock _odeBlock = s.getOdeBlock();
          boolean _notEquals_1 = (!Objects.equal(_odeBlock, null));
          if (_notEquals_1) {
            OdeBlock _odeBlock_1 = s.getOdeBlock();
            EList<BlockStatement> _statements_1 = _odeBlock_1.getStatements();
            for (final BlockStatement ss : _statements_1) {
              {
                SymbolDeclaration x = ss.getSymbol();
                boolean _notEquals_2 = (!Objects.equal(x, null));
                if (_notEquals_2) {
                  AnyExpression _expression = x.getExpression();
                  boolean _notEquals_3 = (!Objects.equal(_expression, null));
                  if (_notEquals_3) {
                    AnyExpression _expression_1 = x.getExpression();
                    OdeList _odeList = _expression_1.getOdeList();
                    boolean _notEquals_4 = (!Objects.equal(_odeList, null));
                    if (_notEquals_4) {
                      String id = x.getIdentifier();
                      Object _get = this.dadt_vars.get(id);
                      boolean _equals = Objects.equal(_get, null);
                      if (_equals) {
                        this.dadt_vars.put(id, Integer.valueOf(i));
                        int _plus = (i + 1);
                        i = _plus;
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
  }
  
  protected void setStructuralParameters(final ModelObject o) {
    int i = 1;
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock b : _blocks) {
      StructuralParametersBlock _structuralParametersBlock = b.getStructuralParametersBlock();
      boolean _notEquals = (!Objects.equal(_structuralParametersBlock, null));
      if (_notEquals) {
        StructuralParametersBlock _structuralParametersBlock_1 = b.getStructuralParametersBlock();
        EList<FullyQualifiedSymbolName> _parameters = _structuralParametersBlock_1.getParameters();
        for (final FullyQualifiedSymbolName id : _parameters) {
          String _str = this.toStr(id);
          Object _get = this.theta_vars.get(_str);
          boolean _equals = Objects.equal(_get, null);
          if (_equals) {
            String _str_1 = this.toStr(id);
            this.theta_vars.put(_str_1, Integer.valueOf(i));
            int _plus = (i + 1);
            i = _plus;
          }
        }
      }
    }
  }
  
  protected void setRandomVariables(final ModelObject o) {
    int i = 1;
    int j = 1;
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock b : _blocks) {
      RandomVariableDefinitionBlock _randomVariableDefinitionBlock = b.getRandomVariableDefinitionBlock();
      boolean _notEquals = (!Objects.equal(_randomVariableDefinitionBlock, null));
      if (_notEquals) {
        RandomVariableDefinitionBlock _randomVariableDefinitionBlock_1 = b.getRandomVariableDefinitionBlock();
        EList<SymbolDeclaration> _variables = _randomVariableDefinitionBlock_1.getVariables();
        for (final SymbolDeclaration s : _variables) {
          RandomList _randomList = s.getRandomList();
          boolean _notEquals_1 = (!Objects.equal(_randomList, null));
          if (_notEquals_1) {
            RandomList _randomList_1 = s.getRandomList();
            Arguments _arguments = _randomList_1.getArguments();
            String level = this.getAttribute(_arguments, "level");
            final String id = s.getIdentifier();
            boolean _equals = level.equals("ID");
            if (_equals) {
              Object _get = this.eta_vars.get(id);
              boolean _equals_1 = Objects.equal(_get, null);
              if (_equals_1) {
                this.eta_vars.put(id, Integer.valueOf(i));
                int _plus = (i + 1);
                i = _plus;
              }
            }
            boolean _equals_2 = level.equals("DV");
            if (_equals_2) {
              Object _get_1 = this.eps_vars.get(id);
              boolean _equals_3 = Objects.equal(_get_1, null);
              if (_equals_3) {
                this.eps_vars.put(id, Integer.valueOf(j));
                int _plus_1 = (j + 1);
                j = _plus_1;
              }
            }
          }
        }
      }
    }
  }
  
  protected void prepareExternals(final Mcl mcl) {
    this.externalFunctions.clear();
    this.externalCodeStart.clear();
    this.externalCodeEnd.clear();
    EList<MclObject> _objects = mcl.getObjects();
    for (final MclObject o : _objects) {
      {
        ModelObject _modelObject = o.getModelObject();
        boolean _notEquals = (!Objects.equal(_modelObject, null));
        if (_notEquals) {
          ModelObject _modelObject_1 = o.getModelObject();
          EList<ModelObjectBlock> _blocks = _modelObject_1.getBlocks();
          for (final ModelObjectBlock block : _blocks) {
            {
              ImportBlock _importBlock = block.getImportBlock();
              boolean _notEquals_1 = (!Objects.equal(_importBlock, null));
              if (_notEquals_1) {
                ImportBlock _importBlock_1 = block.getImportBlock();
                ObjectName _identifier = o.getIdentifier();
                String _name = _identifier.getName();
                this.prepareExternalFunctions(_importBlock_1, _name);
              }
              TargetBlock _targetBlock = block.getTargetBlock();
              boolean _notEquals_2 = (!Objects.equal(_targetBlock, null));
              if (_notEquals_2) {
                TargetBlock _targetBlock_1 = block.getTargetBlock();
                this.prepareExternalCode(_targetBlock_1);
              }
            }
          }
        }
        ParameterObject _parameterObject = o.getParameterObject();
        boolean _notEquals_1 = (!Objects.equal(_parameterObject, null));
        if (_notEquals_1) {
          ParameterObject _parameterObject_1 = o.getParameterObject();
          EList<ParameterObjectBlock> _blocks_1 = _parameterObject_1.getBlocks();
          for (final ParameterObjectBlock block_1 : _blocks_1) {
            {
              ImportBlock _importBlock = block_1.getImportBlock();
              boolean _notEquals_2 = (!Objects.equal(_importBlock, null));
              if (_notEquals_2) {
                ImportBlock _importBlock_1 = block_1.getImportBlock();
                ObjectName _identifier = o.getIdentifier();
                String _name = _identifier.getName();
                this.prepareExternalFunctions(_importBlock_1, _name);
              }
              TargetBlock _targetBlock = block_1.getTargetBlock();
              boolean _notEquals_3 = (!Objects.equal(_targetBlock, null));
              if (_notEquals_3) {
                TargetBlock _targetBlock_1 = block_1.getTargetBlock();
                this.prepareExternalCode(_targetBlock_1);
              }
            }
          }
        }
        DataObject _dataObject = o.getDataObject();
        boolean _notEquals_2 = (!Objects.equal(_dataObject, null));
        if (_notEquals_2) {
          DataObject _dataObject_1 = o.getDataObject();
          EList<DataObjectBlock> _blocks_2 = _dataObject_1.getBlocks();
          for (final DataObjectBlock block_2 : _blocks_2) {
            {
              ImportBlock _importBlock = block_2.getImportBlock();
              boolean _notEquals_3 = (!Objects.equal(_importBlock, null));
              if (_notEquals_3) {
                ImportBlock _importBlock_1 = block_2.getImportBlock();
                ObjectName _identifier = o.getIdentifier();
                String _name = _identifier.getName();
                this.prepareExternalFunctions(_importBlock_1, _name);
              }
              TargetBlock _targetBlock = block_2.getTargetBlock();
              boolean _notEquals_4 = (!Objects.equal(_targetBlock, null));
              if (_notEquals_4) {
                TargetBlock _targetBlock_1 = block_2.getTargetBlock();
                this.prepareExternalCode(_targetBlock_1);
              }
            }
          }
        }
        TaskObject _taskObject = o.getTaskObject();
        boolean _notEquals_3 = (!Objects.equal(_taskObject, null));
        if (_notEquals_3) {
          TaskObject _taskObject_1 = o.getTaskObject();
          EList<TaskObjectBlock> _blocks_3 = _taskObject_1.getBlocks();
          for (final TaskObjectBlock block_3 : _blocks_3) {
            {
              ImportBlock _importBlock = block_3.getImportBlock();
              boolean _notEquals_4 = (!Objects.equal(_importBlock, null));
              if (_notEquals_4) {
                ImportBlock _importBlock_1 = block_3.getImportBlock();
                ObjectName _identifier = o.getIdentifier();
                String _name = _identifier.getName();
                this.prepareExternalFunctions(_importBlock_1, _name);
              }
              TargetBlock _targetBlock = block_3.getTargetBlock();
              boolean _notEquals_5 = (!Objects.equal(_targetBlock, null));
              if (_notEquals_5) {
                TargetBlock _targetBlock_1 = block_3.getTargetBlock();
                this.prepareExternalCode(_targetBlock_1);
              }
            }
          }
        }
        TELObject _telObject = o.getTelObject();
        boolean _notEquals_4 = (!Objects.equal(_telObject, null));
        if (_notEquals_4) {
          TELObject _telObject_1 = o.getTelObject();
          EList<BlockStatement> _statements = _telObject_1.getStatements();
          for (final BlockStatement block_4 : _statements) {
            TargetBlock _targetBlock = block_4.getTargetBlock();
            boolean _notEquals_5 = (!Objects.equal(_targetBlock, null));
            if (_notEquals_5) {
              TargetBlock _targetBlock_1 = block_4.getTargetBlock();
              this.prepareExternalCode(_targetBlock_1);
            }
          }
        }
      }
    }
  }
  
  protected void prepareExternalCode(final TargetBlock block) {
  }
  
  protected void prepareExternalFunctions(final ImportBlock block, final String string) {
  }
  
  protected String getExternalCodeStart(final String sectionName) {
    String res = "";
    final ArrayList<String> snippets = this.externalCodeStart.get(sectionName);
    boolean _notEquals = (!Objects.equal(snippets, null));
    if (_notEquals) {
      for (final String x : snippets) {
        String _plus = (res + "\n");
        String _plus_1 = (_plus + x);
        res = _plus_1;
      }
    }
    return res;
  }
  
  protected String getExternalCodeEnd(final String sectionName) {
    String res = "";
    final ArrayList<String> snippets = this.externalCodeEnd.get(sectionName);
    boolean _notEquals = (!Objects.equal(snippets, null));
    if (_notEquals) {
      for (final String x : snippets) {
        String _plus = (res + "\n");
        String _plus_1 = (_plus + x);
        res = _plus_1;
      }
    }
    return res;
  }
  
  protected HashMap<String,String> getExternalFunctionAttributes(final FullyQualifiedSymbolName ref) {
    ObjectName _object = ref.getObject();
    boolean _notEquals = (!Objects.equal(_object, null));
    if (_notEquals) {
      ObjectName _object_1 = ref.getObject();
      String _name = _object_1.getName();
      String _plus = (_name + "$");
      String _identifier = ref.getIdentifier();
      String _plus_1 = (_plus + _identifier);
      return this.externalFunctions.get(_plus_1);
    } else {
      Set<Entry<String,HashMap<String,String>>> _entrySet = this.externalFunctions.entrySet();
      for (final Entry<String,HashMap<String,String>> pair : _entrySet) {
        {
          String _key = pair.getKey();
          final String str = ((String) _key);
          boolean _notEquals_1 = (!Objects.equal(str, null));
          if (_notEquals_1) {
            int _indexOf = str.indexOf("$");
            int _plus_2 = (_indexOf + 1);
            final String functID = str.substring(_plus_2);
            String _identifier_1 = ref.getIdentifier();
            boolean _equals = functID.equals(_identifier_1);
            if (_equals) {
              return pair.getValue();
            }
          }
        }
      }
    }
    return null;
  }
  
  public String fileNameUpperCase(final Mcl m) {
    Resource _eResource = m.eResource();
    String _fileName = this.fileName(_eResource);
    String _upperCase = _fileName.toUpperCase();
    return _upperCase;
  }
  
  public String fileName(final Resource resource) {
    String _xblockexpression = null;
    {
      URI _uRI = resource.getURI();
      String fileName = _uRI.lastSegment();
      int _lastIndexOf = fileName.lastIndexOf(".");
      String _substring = fileName.substring(0, _lastIndexOf);
      _xblockexpression = (_substring);
    }
    return _xblockexpression;
  }
  
  public boolean isTrue(final AnyExpression e) {
    Expression _expression = e.getExpression();
    boolean _notEquals = (!Objects.equal(_expression, null));
    if (_notEquals) {
      Expression _expression_1 = e.getExpression();
      ConditionalExpression _conditionalExpression = _expression_1.getConditionalExpression();
      boolean _notEquals_1 = (!Objects.equal(_conditionalExpression, null));
      if (_notEquals_1) {
        Expression _expression_2 = e.getExpression();
        ConditionalExpression _conditionalExpression_1 = _expression_2.getConditionalExpression();
        final OrExpression orExpr = _conditionalExpression_1.getExpression();
        EList<AndExpression> _expression_3 = orExpr.getExpression();
        final AndExpression andExpr = _expression_3.get(0);
        EList<LogicalExpression> _expression_4 = andExpr.getExpression();
        final LogicalExpression logicalExpr = _expression_4.get(0);
        String _boolean = logicalExpr.getBoolean();
        boolean _notEquals_2 = (!Objects.equal(_boolean, null));
        if (_notEquals_2) {
          boolean _and = false;
          String _negation = logicalExpr.getNegation();
          boolean _equals = Objects.equal(_negation, null);
          if (!_equals) {
            _and = false;
          } else {
            String _boolean_1 = logicalExpr.getBoolean();
            boolean _equals_1 = _boolean_1.equals("true");
            _and = (_equals && _equals_1);
          }
          if (_and) {
            return true;
          }
          boolean _and_1 = false;
          String _negation_1 = logicalExpr.getNegation();
          boolean _notEquals_3 = (!Objects.equal(_negation_1, null));
          if (!_notEquals_3) {
            _and_1 = false;
          } else {
            String _boolean_2 = logicalExpr.getBoolean();
            boolean _equals_2 = _boolean_2.equals("false");
            _and_1 = (_notEquals_3 && _equals_2);
          }
          if (_and_1) {
            return true;
          }
        }
      }
    }
    boolean _or = false;
    boolean _or_1 = false;
    String _str = this.toStr(e);
    boolean _equalsIgnoreCase = _str.equalsIgnoreCase("yes");
    if (_equalsIgnoreCase) {
      _or_1 = true;
    } else {
      String _str_1 = this.toStr(e);
      boolean _equalsIgnoreCase_1 = _str_1.equalsIgnoreCase("true");
      _or_1 = (_equalsIgnoreCase || _equalsIgnoreCase_1);
    }
    if (_or_1) {
      _or = true;
    } else {
      String _str_2 = this.toStr(e);
      boolean _equals_3 = _str_2.equals("1");
      _or = (_or_1 || _equals_3);
    }
    return _or;
  }
  
  public boolean isAttributeTrue(final Arguments a, final String attrName) {
    EList<Argument> _arguments = a.getArguments();
    for (final Argument arg : _arguments) {
      String _identifier = arg.getIdentifier();
      boolean _equals = _identifier.equals(attrName);
      if (_equals) {
        AnyExpression _expression = arg.getExpression();
        return this.isTrue(_expression);
      }
    }
    return false;
  }
  
  public String getAttribute(final Arguments a, final String attrName) {
    EList<Argument> _arguments = a.getArguments();
    for (final Argument arg : _arguments) {
      boolean _and = false;
      String _identifier = arg.getIdentifier();
      boolean _notEquals = (!Objects.equal(_identifier, null));
      if (!_notEquals) {
        _and = false;
      } else {
        String _identifier_1 = arg.getIdentifier();
        boolean _equals = _identifier_1.equals(attrName);
        _and = (_notEquals && _equals);
      }
      if (_and) {
        AnyExpression _expression = arg.getExpression();
        return this.toStr(_expression);
      }
    }
    return "";
  }
  
  public AnyExpression getAttributeExpression(final Arguments a, final String attrName) {
    EList<Argument> _arguments = a.getArguments();
    for (final Argument arg : _arguments) {
      boolean _and = false;
      String _identifier = arg.getIdentifier();
      boolean _notEquals = (!Objects.equal(_identifier, null));
      if (!_notEquals) {
        _and = false;
      } else {
        String _identifier_1 = arg.getIdentifier();
        boolean _equals = _identifier_1.equals(attrName);
        _and = (_notEquals && _equals);
      }
      if (_and) {
        return arg.getExpression();
      }
    }
    return null;
  }
  
  public String getDataSource(final Mcl m) {
    EList<MclObject> _objects = m.getObjects();
    for (final MclObject obj : _objects) {
      DataObject _dataObject = obj.getDataObject();
      boolean _notEquals = (!Objects.equal(_dataObject, null));
      if (_notEquals) {
        DataObject _dataObject_1 = obj.getDataObject();
        EList<DataObjectBlock> _blocks = _dataObject_1.getBlocks();
        for (final DataObjectBlock b : _blocks) {
          FileBlock _fileBlock = b.getFileBlock();
          boolean _notEquals_1 = (!Objects.equal(_fileBlock, null));
          if (_notEquals_1) {
            FileBlock _fileBlock_1 = b.getFileBlock();
            EList<FileBlockStatement> _statements = _fileBlock_1.getStatements();
            for (final FileBlockStatement s : _statements) {
              {
                final String dataSource = this.getDataSource(s);
                int _length = dataSource.length();
                boolean _greaterThan = (_length > 0);
                if (_greaterThan) {
                  return dataSource;
                }
              }
            }
          }
        }
      }
    }
    return "";
  }
  
  public String getDataSource(final Resource resource) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<Mcl> _filter = Iterables.<Mcl>filter(_iterable, Mcl.class);
    for (final Mcl m : _filter) {
      {
        String source = this.getDataSource(m);
        int _length = source.length();
        boolean _greaterThan = (_length > 0);
        if (_greaterThan) {
          return source;
        }
      }
    }
    return "";
  }
  
  public String getDataSource(final FileBlockStatement s) {
    SymbolDeclaration _variable = s.getVariable();
    boolean _notEquals = (!Objects.equal(_variable, null));
    if (_notEquals) {
      SymbolDeclaration _variable_1 = s.getVariable();
      String _identifier = _variable_1.getIdentifier();
      boolean _equals = _identifier.equals("data");
      if (_equals) {
        SymbolDeclaration _variable_2 = s.getVariable();
        AnyExpression _expression = _variable_2.getExpression();
        boolean _notEquals_1 = (!Objects.equal(_expression, null));
        if (_notEquals_1) {
          SymbolDeclaration _variable_3 = s.getVariable();
          AnyExpression _expression_1 = _variable_3.getExpression();
          List _list = _expression_1.getList();
          boolean _notEquals_2 = (!Objects.equal(_list, null));
          if (_notEquals_2) {
            SymbolDeclaration _variable_4 = s.getVariable();
            AnyExpression _expression_2 = _variable_4.getExpression();
            List _list_1 = _expression_2.getList();
            Arguments _arguments = _list_1.getArguments();
            return this.getAttribute(_arguments, "source");
          }
        }
      }
    }
    return "";
  }
  
  public boolean isHeaderDefined(final DataObject obj) {
    EList<DataObjectBlock> _blocks = obj.getBlocks();
    for (final DataObjectBlock b : _blocks) {
      HeaderBlock _headerBlock = b.getHeaderBlock();
      boolean _notEquals = (!Objects.equal(_headerBlock, null));
      if (_notEquals) {
        HeaderBlock _headerBlock_1 = b.getHeaderBlock();
        EList<SymbolModification> _variables = _headerBlock_1.getVariables();
        int _size = _variables.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean isPriorDefined(final ParameterObject obj) {
    EList<ParameterObjectBlock> _blocks = obj.getBlocks();
    for (final ParameterObjectBlock b : _blocks) {
      PriorParametersBlock _priorBlock = b.getPriorBlock();
      boolean _notEquals = (!Objects.equal(_priorBlock, null));
      if (_notEquals) {
        PriorParametersBlock _priorBlock_1 = b.getPriorBlock();
        EList<BlockStatement> _statements = _priorBlock_1.getStatements();
        int _size = _statements.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean isStructuralDefined(final ParameterObject obj) {
    EList<ParameterObjectBlock> _blocks = obj.getBlocks();
    for (final ParameterObjectBlock b : _blocks) {
      StructuralBlock _structuralBlock = b.getStructuralBlock();
      boolean _notEquals = (!Objects.equal(_structuralBlock, null));
      if (_notEquals) {
        StructuralBlock _structuralBlock_1 = b.getStructuralBlock();
        EList<ParameterDeclaration> _parameters = _structuralBlock_1.getParameters();
        int _size = _parameters.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean isVariabilityDefined(final ParameterObject obj) {
    EList<ParameterObjectBlock> _blocks = obj.getBlocks();
    for (final ParameterObjectBlock b : _blocks) {
      VariabilityBlock _variabilityBlock = b.getVariabilityBlock();
      boolean _notEquals = (!Objects.equal(_variabilityBlock, null));
      if (_notEquals) {
        VariabilityBlock _variabilityBlock_1 = b.getVariabilityBlock();
        EList<VariabilityBlockStatement> _statements = _variabilityBlock_1.getStatements();
        int _size = _statements.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean isVariabilitySubBlocksDefined(final ParameterObject obj) {
    EList<ParameterObjectBlock> _blocks = obj.getBlocks();
    for (final ParameterObjectBlock b : _blocks) {
      VariabilityBlock _variabilityBlock = b.getVariabilityBlock();
      boolean _notEquals = (!Objects.equal(_variabilityBlock, null));
      if (_notEquals) {
        VariabilityBlock _variabilityBlock_1 = b.getVariabilityBlock();
        EList<VariabilityBlockStatement> _statements = _variabilityBlock_1.getStatements();
        for (final VariabilityBlockStatement bb : _statements) {
          boolean _or = false;
          boolean _or_1 = false;
          DiagBlock _diagBlock = bb.getDiagBlock();
          boolean _notEquals_1 = (!Objects.equal(_diagBlock, null));
          if (_notEquals_1) {
            _or_1 = true;
          } else {
            MatrixBlock _matrixBlock = bb.getMatrixBlock();
            boolean _notEquals_2 = (!Objects.equal(_matrixBlock, null));
            _or_1 = (_notEquals_1 || _notEquals_2);
          }
          if (_or_1) {
            _or = true;
          } else {
            SameBlock _sameBlock = bb.getSameBlock();
            boolean _notEquals_3 = (!Objects.equal(_sameBlock, null));
            _or = (_or_1 || _notEquals_3);
          }
          if (_or) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public boolean isErrorDefined(final ModelObject o) {
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock mob : _blocks) {
      {
        ModelPredictionBlock _modelPredictionBlock = mob.getModelPredictionBlock();
        boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
        if (_notEquals) {
          ModelPredictionBlock _modelPredictionBlock_1 = mob.getModelPredictionBlock();
          EList<ModelPredictionBlockStatement> _statements = _modelPredictionBlock_1.getStatements();
          for (final ModelPredictionBlockStatement s : _statements) {
            BlockStatement _statement = s.getStatement();
            boolean _notEquals_1 = (!Objects.equal(_statement, null));
            if (_notEquals_1) {
              return true;
            }
          }
        }
        ObservationBlock _observationBlock = mob.getObservationBlock();
        boolean _notEquals_2 = (!Objects.equal(_observationBlock, null));
        if (_notEquals_2) {
          ObservationBlock _observationBlock_1 = mob.getObservationBlock();
          EList<BlockStatement> _statements_1 = _observationBlock_1.getStatements();
          int _size = _statements_1.size();
          boolean _greaterThan = (_size > 0);
          if (_greaterThan) {
            return true;
          }
        }
        SimulationBlock _simulationBlock = mob.getSimulationBlock();
        boolean _notEquals_3 = (!Objects.equal(_simulationBlock, null));
        if (_notEquals_3) {
          SimulationBlock _simulationBlock_1 = mob.getSimulationBlock();
          EList<BlockStatement> _statements_2 = _simulationBlock_1.getStatements();
          int _size_1 = _statements_2.size();
          boolean _greaterThan_1 = (_size_1 > 0);
          if (_greaterThan_1) {
            return true;
          }
        }
        EstimationBlock _estimationBlock = mob.getEstimationBlock();
        boolean _notEquals_4 = (!Objects.equal(_estimationBlock, null));
        if (_notEquals_4) {
          EstimationBlock _estimationBlock_1 = mob.getEstimationBlock();
          EList<BlockStatement> _statements_3 = _estimationBlock_1.getStatements();
          int _size_2 = _statements_3.size();
          boolean _greaterThan_2 = (_size_2 > 0);
          if (_greaterThan_2) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public boolean isODEDefined(final ModelObject o) {
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock mob : _blocks) {
      ModelPredictionBlock _modelPredictionBlock = mob.getModelPredictionBlock();
      boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
      if (_notEquals) {
        ModelPredictionBlock b = mob.getModelPredictionBlock();
        EList<ModelPredictionBlockStatement> _statements = b.getStatements();
        for (final ModelPredictionBlockStatement s : _statements) {
          OdeBlock _odeBlock = s.getOdeBlock();
          boolean _notEquals_1 = (!Objects.equal(_odeBlock, null));
          if (_notEquals_1) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public boolean isGroupOrIndividualDefined(final ModelObject o) {
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock mob : _blocks) {
      {
        GroupVariablesBlock _groupVariablesBlock = mob.getGroupVariablesBlock();
        boolean _notEquals = (!Objects.equal(_groupVariablesBlock, null));
        if (_notEquals) {
          GroupVariablesBlock _groupVariablesBlock_1 = mob.getGroupVariablesBlock();
          boolean _notEquals_1 = (!Objects.equal(_groupVariablesBlock_1, null));
          if (_notEquals_1) {
            GroupVariablesBlock _groupVariablesBlock_2 = mob.getGroupVariablesBlock();
            EList<GroupVariablesBlockStatement> _statements = _groupVariablesBlock_2.getStatements();
            for (final GroupVariablesBlockStatement st : _statements) {
              BlockStatement _statement = st.getStatement();
              boolean _notEquals_2 = (!Objects.equal(_statement, null));
              if (_notEquals_2) {
                return true;
              }
            }
          }
        }
        IndividualVariablesBlock _individualVariablesBlock = mob.getIndividualVariablesBlock();
        boolean _notEquals_3 = (!Objects.equal(_individualVariablesBlock, null));
        if (_notEquals_3) {
          IndividualVariablesBlock _individualVariablesBlock_1 = mob.getIndividualVariablesBlock();
          EList<BlockStatement> _statements_1 = _individualVariablesBlock_1.getStatements();
          int _size = _statements_1.size();
          boolean _greaterThan = (_size > 0);
          if (_greaterThan) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  /**
   * def isIndividualDefined(Mcl m){
   * for (o: m.objects){
   * if (o.modelObject != null){
   * for (mob: o.modelObject.blocks){
   * if (mob.individualVariablesBlock != null){
   * if (mob.individualVariablesBlock.statements.size > 0){
   * return true;
   * }
   * }
   * }
   * }
   * }
   * return false;
   * }
   */
  public boolean isMixDefined(final ModelObject o) {
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock mob : _blocks) {
      GroupVariablesBlock _groupVariablesBlock = mob.getGroupVariablesBlock();
      boolean _notEquals = (!Objects.equal(_groupVariablesBlock, null));
      if (_notEquals) {
        GroupVariablesBlock _groupVariablesBlock_1 = mob.getGroupVariablesBlock();
        boolean _notEquals_1 = (!Objects.equal(_groupVariablesBlock_1, null));
        if (_notEquals_1) {
          GroupVariablesBlock _groupVariablesBlock_2 = mob.getGroupVariablesBlock();
          EList<GroupVariablesBlockStatement> _statements = _groupVariablesBlock_2.getStatements();
          for (final GroupVariablesBlockStatement st : _statements) {
            MixtureBlock _mixtureBlock = st.getMixtureBlock();
            boolean _notEquals_2 = (!Objects.equal(_mixtureBlock, null));
            if (_notEquals_2) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
  
  public boolean isLibraryDefined(final ModelObject o) {
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock mob : _blocks) {
      ModelPredictionBlock _modelPredictionBlock = mob.getModelPredictionBlock();
      boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
      if (_notEquals) {
        ModelPredictionBlock _modelPredictionBlock_1 = mob.getModelPredictionBlock();
        EList<ModelPredictionBlockStatement> _statements = _modelPredictionBlock_1.getStatements();
        for (final ModelPredictionBlockStatement s : _statements) {
          LibraryBlock _libraryBlock = s.getLibraryBlock();
          boolean _notEquals_1 = (!Objects.equal(_libraryBlock, null));
          if (_notEquals_1) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public int getNumberOfCompartments(final ModelObject o) {
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock mob : _blocks) {
      ModelPredictionBlock _modelPredictionBlock = mob.getModelPredictionBlock();
      boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
      if (_notEquals) {
        ModelPredictionBlock _modelPredictionBlock_1 = mob.getModelPredictionBlock();
        EList<ModelPredictionBlockStatement> _statements = _modelPredictionBlock_1.getStatements();
        for (final ModelPredictionBlockStatement s : _statements) {
          LibraryBlock _libraryBlock = s.getLibraryBlock();
          boolean _notEquals_1 = (!Objects.equal(_libraryBlock, null));
          if (_notEquals_1) {
            LibraryBlock _libraryBlock_1 = s.getLibraryBlock();
            EList<FunctionCallStatement> _statements_1 = _libraryBlock_1.getStatements();
            for (final FunctionCallStatement ss : _statements_1) {
              {
                FunctionCall _expression = ss.getExpression();
                Arguments _arguments = _expression.getArguments();
                String nmct = this.getAttribute(_arguments, "ncmt");
                boolean _equals = nmct.equals("");
                boolean _not = (!_equals);
                if (_not) {
                  try {
                    return Integer.parseInt(nmct);
                  } catch (final Throwable _t) {
                    if (_t instanceof NumberFormatException) {
                      final NumberFormatException e = (NumberFormatException)_t;
                      return 0;
                    } else {
                      throw Exceptions.sneakyThrow(_t);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return 0;
  }
  
  public boolean isOutputVariablesDefined(final ModelObject o) {
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock b : _blocks) {
      OutputVariablesBlock _outputVariablesBlock = b.getOutputVariablesBlock();
      boolean _notEquals = (!Objects.equal(_outputVariablesBlock, null));
      if (_notEquals) {
        OutputVariablesBlock _outputVariablesBlock_1 = b.getOutputVariablesBlock();
        EList<FullyQualifiedSymbolName> _variables = _outputVariablesBlock_1.getVariables();
        int _size = _variables.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean isCovarianceDefined(final EstimateTask b) {
    EList<BlockStatement> _statements = b.getStatements();
    for (final BlockStatement s : _statements) {
      SymbolDeclaration _symbol = s.getSymbol();
      boolean _notEquals = (!Objects.equal(_symbol, null));
      if (_notEquals) {
        SymbolDeclaration _symbol_1 = s.getSymbol();
        String _identifier = _symbol_1.getIdentifier();
        boolean _equals = _identifier.equals("cov");
        if (_equals) {
          SymbolDeclaration _symbol_2 = s.getSymbol();
          AnyExpression _expression = _symbol_2.getExpression();
          boolean _notEquals_1 = (!Objects.equal(_expression, null));
          if (_notEquals_1) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public boolean isTargetDefined(final String sectionName) {
    boolean _or = false;
    boolean _containsKey = this.externalCodeStart.containsKey(sectionName);
    if (_containsKey) {
      _or = true;
    } else {
      boolean _containsKey_1 = this.externalCodeEnd.containsKey(sectionName);
      _or = (_containsKey || _containsKey_1);
    }
    return _or;
  }
  
  public boolean isInlineTargetDefined(final String targetName, final EList<BlockStatement> list) {
    for (final BlockStatement s : list) {
      TargetBlock _targetBlock = s.getTargetBlock();
      boolean _notEquals = (!Objects.equal(_targetBlock, null));
      if (_notEquals) {
        TargetBlock _targetBlock_1 = s.getTargetBlock();
        Arguments _arguments = _targetBlock_1.getArguments();
        final String target = this.getAttribute(_arguments, "target");
        boolean _notEquals_1 = (!Objects.equal(target, null));
        if (_notEquals_1) {
          boolean _equals = target.equals(targetName);
          if (_equals) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public String convertID(final String id) {
    return id;
  }
  
  public String convertOperator(final String op) {
    return op;
  }
  
  public String toStr(final FullyQualifiedSymbolName name) {
    String res = "";
    ObjectName _object = name.getObject();
    boolean _notEquals = (!Objects.equal(_object, null));
    if (_notEquals) {
      ObjectName _object_1 = name.getObject();
      String _name = _object_1.getName();
      String _plus = (_name + "$");
      res = _plus;
    }
    String _identifier = name.getIdentifier();
    String _convertID = this.convertID(_identifier);
    String _plus_1 = (res + _convertID);
    res = _plus_1;
    return res;
  }
  
  public String toStr(final SymbolDeclaration v) {
    String res = "";
    String _function = v.getFunction();
    boolean _notEquals = (!Objects.equal(_function, null));
    if (_notEquals) {
      String _function_1 = v.getFunction();
      String _convertID = this.convertID(_function_1);
      String _plus = (res + _convertID);
      String _plus_1 = (_plus + "(");
      res = _plus_1;
    }
    String _identifier = v.getIdentifier();
    boolean _notEquals_1 = (!Objects.equal(_identifier, null));
    if (_notEquals_1) {
      String _identifier_1 = v.getIdentifier();
      String _convertID_1 = this.convertID(_identifier_1);
      String _plus_2 = (res + _convertID_1);
      res = _plus_2;
    }
    String _function_2 = v.getFunction();
    boolean _notEquals_2 = (!Objects.equal(_function_2, null));
    if (_notEquals_2) {
      String _plus_3 = (res + ")");
      res = _plus_3;
    }
    String expr = "";
    AnyExpression _expression = v.getExpression();
    boolean _notEquals_3 = (!Objects.equal(_expression, null));
    if (_notEquals_3) {
      AnyExpression _expression_1 = v.getExpression();
      String _str = this.toStr(_expression_1);
      expr = _str;
    }
    RandomList _randomList = v.getRandomList();
    boolean _notEquals_4 = (!Objects.equal(_randomList, null));
    if (_notEquals_4) {
      RandomList _randomList_1 = v.getRandomList();
      String _str_1 = this.toStr(_randomList_1);
      expr = _str_1;
    }
    String _trim = expr.trim();
    boolean _equals = _trim.equals("");
    boolean _not = (!_equals);
    if (_not) {
      String _plus_4 = (res + " = ");
      String _plus_5 = (_plus_4 + expr);
      res = _plus_5;
    }
    return res;
  }
  
  public String toStr(final SymbolModification v) {
    String res = "";
    FullyQualifiedSymbolName _identifier = v.getIdentifier();
    boolean _notEquals = (!Objects.equal(_identifier, null));
    if (_notEquals) {
      FullyQualifiedSymbolName _identifier_1 = v.getIdentifier();
      this.toStr(_identifier_1);
    }
    List _list = v.getList();
    boolean _notEquals_1 = (!Objects.equal(_list, null));
    if (_notEquals_1) {
      String _plus = (res + " = ");
      List _list_1 = v.getList();
      String _str = this.toStr(_list_1);
      String _plus_1 = (_plus + _str);
      res = _plus_1;
    }
    return res;
  }
  
  public String toStr(final AnyExpression e) {
    String res = "";
    Expression _expression = e.getExpression();
    boolean _notEquals = (!Objects.equal(_expression, null));
    if (_notEquals) {
      Expression _expression_1 = e.getExpression();
      String _str = this.toStr(_expression_1);
      String _plus = (res + _str);
      res = _plus;
    }
    List _list = e.getList();
    boolean _notEquals_1 = (!Objects.equal(_list, null));
    if (_notEquals_1) {
      List _list_1 = e.getList();
      String _str_1 = this.toStr(_list_1);
      String _plus_1 = (res + _str_1);
      res = _plus_1;
    }
    OdeList _odeList = e.getOdeList();
    boolean _notEquals_2 = (!Objects.equal(_odeList, null));
    if (_notEquals_2) {
      OdeList _odeList_1 = e.getOdeList();
      String _str_2 = this.toStr(_odeList_1);
      String _plus_2 = (res + _str_2);
      res = _plus_2;
    }
    EnumType _type = e.getType();
    boolean _notEquals_3 = (!Objects.equal(_type, null));
    if (_notEquals_3) {
      EnumType _type_1 = e.getType();
      String _str_3 = this.toStr(_type_1);
      String _plus_3 = (res + _str_3);
      res = _plus_3;
    }
    return res;
  }
  
  public String toStr(final EnumType type) {
    String _xblockexpression = null;
    {
      Categorical _categorical = type.getCategorical();
      boolean _notEquals = (!Objects.equal(_categorical, null));
      if (_notEquals) {
        String res = "";
        Categorical _categorical_1 = type.getCategorical();
        FormalArguments _arguments = _categorical_1.getArguments();
        boolean _notEquals_1 = (!Objects.equal(_arguments, null));
        if (_notEquals_1) {
          Categorical _categorical_2 = type.getCategorical();
          FormalArguments _arguments_1 = _categorical_2.getArguments();
          String _str = this.toStr(_arguments_1);
          res = _str;
        }
        Categorical _categorical_3 = type.getCategorical();
        String _identifier = _categorical_3.getIdentifier();
        String _plus = (_identifier + "(");
        String _plus_1 = (_plus + res);
        return (_plus_1 + ")");
      }
      Continuous _continuous = type.getContinuous();
      boolean _notEquals_2 = (!Objects.equal(_continuous, null));
      if (_notEquals_2) {
        Continuous _continuous_1 = type.getContinuous();
        return _continuous_1.getIdentifier();
      }
      Covariate _covariate = type.getCovariate();
      boolean _notEquals_3 = (!Objects.equal(_covariate, null));
      if (_notEquals_3) {
        Covariate _covariate_1 = type.getCovariate();
        return _covariate_1.getIdentifier();
      }
      Distribution _distribution = type.getDistribution();
      boolean _notEquals_4 = (!Objects.equal(_distribution, null));
      if (_notEquals_4) {
        Distribution _distribution_1 = type.getDistribution();
        return this.toStr(_distribution_1);
      }
      UseType _use = type.getUse();
      boolean _notEquals_5 = (!Objects.equal(_use, null));
      if (_notEquals_5) {
        UseType _use_1 = type.getUse();
        return this.toStr(_use_1);
      }
      Likelyhood _likelyhood = type.getLikelyhood();
      boolean _notEquals_6 = (!Objects.equal(_likelyhood, null));
      if (_notEquals_6) {
        Likelyhood _likelyhood_1 = type.getLikelyhood();
        return _likelyhood_1.getIdentifier();
      }
      String _xifexpression = null;
      TargetLanguage _target = type.getTarget();
      boolean _notEquals_7 = (!Objects.equal(_target, null));
      if (_notEquals_7) {
        TargetLanguage _target_1 = type.getTarget();
        return _target_1.getIdentifier();
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public String toStr(final Distribution d) {
    return d.getIdentifier();
  }
  
  public String toStr(final UseType l) {
    return l.getIdentifier();
  }
  
  public String toStr(final RandomList l) {
    Arguments _arguments = l.getArguments();
    boolean _notEquals = (!Objects.equal(_arguments, null));
    if (_notEquals) {
      String _plus = ("~" + "(");
      Arguments _arguments_1 = l.getArguments();
      String _str = this.toStr(_arguments_1);
      String _plus_1 = (_plus + _str);
      return (_plus_1 + ")");
    }
    return "";
  }
  
  public String toStr(final List l) {
    Arguments _arguments = l.getArguments();
    boolean _notEquals = (!Objects.equal(_arguments, null));
    if (_notEquals) {
      String _plus = ("list" + "(");
      Arguments _arguments_1 = l.getArguments();
      String _str = this.toStr(_arguments_1);
      String _plus_1 = (_plus + _str);
      return (_plus_1 + ")");
    }
    return "";
  }
  
  public String toStr(final OdeList l) {
    String _xifexpression = null;
    Arguments _arguments = l.getArguments();
    boolean _notEquals = (!Objects.equal(_arguments, null));
    if (_notEquals) {
      String _plus = ("ode" + "(");
      Arguments _arguments_1 = l.getArguments();
      String _str = this.toStr(_arguments_1);
      String _plus_1 = (_plus + _str);
      return (_plus_1 + ")");
    }
    return _xifexpression;
  }
  
  public String toStr(final Expression e) {
    ConditionalExpression _conditionalExpression = e.getConditionalExpression();
    return this.toStr(_conditionalExpression);
  }
  
  public String toStr(final ConditionalExpression e) {
    OrExpression _expression = e.getExpression();
    String res = this.toStr(_expression);
    Expression _expression1 = e.getExpression1();
    boolean _notEquals = (!Objects.equal(_expression1, null));
    if (_notEquals) {
      String _plus = (res + "?");
      Expression _expression1_1 = e.getExpression1();
      Object _str = this.toStr(_expression1_1);
      String _plus_1 = (_plus + _str);
      String _plus_2 = (_plus_1 + ":");
      Expression _expression2 = e.getExpression2();
      Object _str_1 = this.toStr(_expression2);
      String _plus_3 = (_plus_2 + _str_1);
      res = _plus_3;
    }
    return res;
  }
  
  public String toStr(final OrExpression e) {
    String res = "";
    EList<AndExpression> _expression = e.getExpression();
    Iterator<AndExpression> iterator = _expression.iterator();
    EList<String> _operator = e.getOperator();
    Iterator<String> operatorIterator = _operator.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      AndExpression _next = iterator.next();
      String _str = this.toStr(_next);
      res = _str;
    }
    boolean _and = false;
    boolean _hasNext_1 = iterator.hasNext();
    if (!_hasNext_1) {
      _and = false;
    } else {
      boolean _hasNext_2 = operatorIterator.hasNext();
      _and = (_hasNext_1 && _hasNext_2);
    }
    boolean _while = _and;
    while (_while) {
      String _next_1 = operatorIterator.next();
      String _convertOperator = this.convertOperator(_next_1);
      String _plus = (res + _convertOperator);
      AndExpression _next_2 = iterator.next();
      String _str_1 = this.toStr(_next_2);
      String _plus_1 = (_plus + _str_1);
      res = _plus_1;
      boolean _and_1 = false;
      boolean _hasNext_3 = iterator.hasNext();
      if (!_hasNext_3) {
        _and_1 = false;
      } else {
        boolean _hasNext_4 = operatorIterator.hasNext();
        _and_1 = (_hasNext_3 && _hasNext_4);
      }
      _while = _and_1;
    }
    return res;
  }
  
  public String toStr(final AndExpression e) {
    String res = "";
    EList<LogicalExpression> _expression = e.getExpression();
    Iterator<LogicalExpression> iterator = _expression.iterator();
    EList<String> _operator = e.getOperator();
    Iterator<String> operatorIterator = _operator.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      LogicalExpression _next = iterator.next();
      String _str = this.toStr(_next);
      res = _str;
    }
    boolean _and = false;
    boolean _hasNext_1 = iterator.hasNext();
    if (!_hasNext_1) {
      _and = false;
    } else {
      boolean _hasNext_2 = operatorIterator.hasNext();
      _and = (_hasNext_1 && _hasNext_2);
    }
    boolean _while = _and;
    while (_while) {
      String _next_1 = operatorIterator.next();
      String _convertOperator = this.convertOperator(_next_1);
      String _plus = (res + _convertOperator);
      LogicalExpression _next_2 = iterator.next();
      String _str_1 = this.toStr(_next_2);
      String _plus_1 = (_plus + _str_1);
      res = _plus_1;
      boolean _and_1 = false;
      boolean _hasNext_3 = iterator.hasNext();
      if (!_hasNext_3) {
        _and_1 = false;
      } else {
        boolean _hasNext_4 = operatorIterator.hasNext();
        _and_1 = (_hasNext_3 && _hasNext_4);
      }
      _while = _and_1;
    }
    return res;
  }
  
  public String toStr(final LogicalExpression e) {
    String res = "";
    String _negation = e.getNegation();
    boolean _notEquals = (!Objects.equal(_negation, null));
    if (_notEquals) {
      String _negation_1 = e.getNegation();
      String _plus = (res + _negation_1);
      res = _plus;
    }
    String _boolean = e.getBoolean();
    boolean _notEquals_1 = (!Objects.equal(_boolean, null));
    if (_notEquals_1) {
      String _boolean_1 = e.getBoolean();
      String _string = _boolean_1.toString();
      String _plus_1 = (res + _string);
      res = _plus_1;
    }
    EList<AdditiveExpression> _expression = e.getExpression();
    Iterator<AdditiveExpression> iterator = _expression.iterator();
    EList<String> _operator = e.getOperator();
    Iterator<String> operatorIterator = _operator.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      AdditiveExpression _next = iterator.next();
      String _str = this.toStr(_next);
      res = _str;
    }
    boolean _and = false;
    boolean _hasNext_1 = iterator.hasNext();
    if (!_hasNext_1) {
      _and = false;
    } else {
      boolean _hasNext_2 = operatorIterator.hasNext();
      _and = (_hasNext_1 && _hasNext_2);
    }
    boolean _while = _and;
    while (_while) {
      String _next_1 = operatorIterator.next();
      String _convertOperator = this.convertOperator(_next_1);
      String _plus_2 = (res + _convertOperator);
      AdditiveExpression _next_2 = iterator.next();
      String _str_1 = this.toStr(_next_2);
      String _plus_3 = (_plus_2 + _str_1);
      res = _plus_3;
      boolean _and_1 = false;
      boolean _hasNext_3 = iterator.hasNext();
      if (!_hasNext_3) {
        _and_1 = false;
      } else {
        boolean _hasNext_4 = operatorIterator.hasNext();
        _and_1 = (_hasNext_3 && _hasNext_4);
      }
      _while = _and_1;
    }
    return res;
  }
  
  public String toStr(final AdditiveExpression e) {
    String res = "";
    EList<MultiplicativeExpression> _expression = e.getExpression();
    boolean _notEquals = (!Objects.equal(_expression, null));
    if (_notEquals) {
      EList<MultiplicativeExpression> _expression_1 = e.getExpression();
      Iterator<MultiplicativeExpression> iterator = _expression_1.iterator();
      EList<String> _operator = e.getOperator();
      Iterator<String> operatorIterator = _operator.iterator();
      boolean _hasNext = iterator.hasNext();
      if (_hasNext) {
        MultiplicativeExpression _next = iterator.next();
        String _str = this.toStr(_next);
        res = _str;
      }
      boolean _and = false;
      boolean _hasNext_1 = iterator.hasNext();
      if (!_hasNext_1) {
        _and = false;
      } else {
        boolean _hasNext_2 = operatorIterator.hasNext();
        _and = (_hasNext_1 && _hasNext_2);
      }
      boolean _while = _and;
      while (_while) {
        String _next_1 = operatorIterator.next();
        String _convertOperator = this.convertOperator(_next_1);
        String _plus = (res + _convertOperator);
        MultiplicativeExpression _next_2 = iterator.next();
        String _str_1 = this.toStr(_next_2);
        String _plus_1 = (_plus + _str_1);
        res = _plus_1;
        boolean _and_1 = false;
        boolean _hasNext_3 = iterator.hasNext();
        if (!_hasNext_3) {
          _and_1 = false;
        } else {
          boolean _hasNext_4 = operatorIterator.hasNext();
          _and_1 = (_hasNext_3 && _hasNext_4);
        }
        _while = _and_1;
      }
    }
    EList<String> _string = e.getString();
    boolean _notEquals_1 = (!Objects.equal(_string, null));
    if (_notEquals_1) {
      EList<String> _string_1 = e.getString();
      Iterator<String> iterator_1 = _string_1.iterator();
      boolean _hasNext_3 = iterator_1.hasNext();
      if (_hasNext_3) {
        String _next_1 = iterator_1.next();
        res = _next_1;
      }
      boolean _hasNext_4 = iterator_1.hasNext();
      boolean _while_1 = _hasNext_4;
      while (_while_1) {
        String _next_2 = iterator_1.next();
        String _plus = (res + _next_2);
        res = _plus;
        boolean _hasNext_5 = iterator_1.hasNext();
        _while_1 = _hasNext_5;
      }
    }
    return res;
  }
  
  public String toStr(final MultiplicativeExpression e) {
    String res = "";
    EList<PowerExpression> _expression = e.getExpression();
    Iterator<PowerExpression> iterator = _expression.iterator();
    EList<String> _operator = e.getOperator();
    Iterator<String> operatorIterator = _operator.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      PowerExpression _next = iterator.next();
      String _str = this.toStr(_next);
      res = _str;
    }
    boolean _hasNext_1 = iterator.hasNext();
    boolean _while = _hasNext_1;
    while (_while) {
      String _next_1 = operatorIterator.next();
      String _convertOperator = this.convertOperator(_next_1);
      String _plus = (res + _convertOperator);
      PowerExpression _next_2 = iterator.next();
      String _str_1 = this.toStr(_next_2);
      String _plus_1 = (_plus + _str_1);
      res = _plus_1;
      boolean _hasNext_2 = iterator.hasNext();
      _while = _hasNext_2;
    }
    return res;
  }
  
  public String toStr(final PowerExpression e) {
    String res = "";
    EList<UnaryExpression> _expression = e.getExpression();
    Iterator<UnaryExpression> iterator = _expression.iterator();
    EList<String> _operator = e.getOperator();
    Iterator<String> operatorIterator = _operator.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      UnaryExpression _next = iterator.next();
      String _str = this.toStr(_next);
      res = _str;
    }
    boolean _hasNext_1 = iterator.hasNext();
    boolean _while = _hasNext_1;
    while (_while) {
      String _next_1 = operatorIterator.next();
      String _convertOperator = this.convertOperator(_next_1);
      String _plus = (res + _convertOperator);
      UnaryExpression _next_2 = iterator.next();
      String _str_1 = this.toStr(_next_2);
      String _plus_1 = (_plus + _str_1);
      res = _plus_1;
      boolean _hasNext_2 = iterator.hasNext();
      _while = _hasNext_2;
    }
    return res;
  }
  
  public String toStr(final UnaryExpression e) {
    String res = "";
    UnaryExpression _expression = e.getExpression();
    boolean _notEquals = (!Objects.equal(_expression, null));
    if (_notEquals) {
      String _operator = e.getOperator();
      String _convertOperator = this.convertOperator(_operator);
      String _plus = (res + _convertOperator);
      UnaryExpression _expression_1 = e.getExpression();
      Object _str = this.toStr(_expression_1);
      String _plus_1 = (_plus + _str);
      res = _plus_1;
    }
    ParExpression _parExpression = e.getParExpression();
    boolean _notEquals_1 = (!Objects.equal(_parExpression, null));
    if (_notEquals_1) {
      ParExpression _parExpression_1 = e.getParExpression();
      String _str_1 = this.toStr(_parExpression_1);
      String _plus_2 = (res + _str_1);
      res = _plus_2;
    }
    Primary _primary = e.getPrimary();
    boolean _notEquals_2 = (!Objects.equal(_primary, null));
    if (_notEquals_2) {
      Primary _primary_1 = e.getPrimary();
      String _str_2 = this.toStr(_primary_1);
      String _plus_3 = (res + _str_2);
      res = _plus_3;
    }
    return res;
  }
  
  public String toStr(final FunctionCall call) {
    FullyQualifiedSymbolName _identifier = call.getIdentifier();
    String _str = this.toStr(_identifier);
    String _plus = (_str + "(");
    Arguments _arguments = call.getArguments();
    String _str_1 = this.toStr(_arguments);
    String _plus_1 = (_plus + _str_1);
    return (_plus_1 + ")");
  }
  
  public String toStr(final Primary p) {
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
      FunctionCall _functionCall = p.getFunctionCall();
      boolean _notEquals_2 = (!Objects.equal(_functionCall, null));
      if (_notEquals_2) {
        FunctionCall _functionCall_1 = p.getFunctionCall();
        return this.toStr(_functionCall_1);
      }
      Vector _vector = p.getVector();
      boolean _notEquals_3 = (!Objects.equal(_vector, null));
      if (_notEquals_3) {
        Vector _vector_1 = p.getVector();
        return this.toStr(_vector_1);
      }
      String _xifexpression = null;
      FullyQualifiedArgumentName _attribute = p.getAttribute();
      boolean _notEquals_4 = (!Objects.equal(_attribute, null));
      if (_notEquals_4) {
        FullyQualifiedArgumentName _attribute_1 = p.getAttribute();
        return this.toStr(_attribute_1);
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public String toStr(final FullyQualifiedArgumentName name) {
    FullyQualifiedSymbolName _parent = name.getParent();
    String res = this.toStr(_parent);
    EList<Selector> _selectors = name.getSelectors();
    for (final Selector s : _selectors) {
      String _str = this.toStr(s);
      String _plus = (res + _str);
      res = _plus;
    }
    return res;
  }
  
  public String toStr(final Selector s) {
    String _xblockexpression = null;
    {
      FormalArgument _identifier = s.getIdentifier();
      boolean _notEquals = (!Objects.equal(_identifier, null));
      if (_notEquals) {
        FormalArgument _identifier_1 = s.getIdentifier();
        String _identifier_2 = _identifier_1.getIdentifier();
        return ("." + _identifier_2);
      }
      String _xifexpression = null;
      String _selector = s.getSelector();
      boolean _notEquals_1 = (!Objects.equal(_selector, null));
      if (_notEquals_1) {
        String _selector_1 = s.getSelector();
        String _plus = ("[" + _selector_1);
        return (_plus + "]");
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public String toStr(final Vector v) {
    String _identifier = v.getIdentifier();
    String res = (_identifier + "(");
    EList<Expression> _values = v.getValues();
    Iterator<Expression> iterator = _values.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      Expression _next = iterator.next();
      String _str = this.toStr(_next);
      String _plus = (res + _str);
      res = _plus;
    }
    boolean _hasNext_1 = iterator.hasNext();
    boolean _while = _hasNext_1;
    while (_while) {
      {
        String _plus_1 = (res + ", ");
        res = _plus_1;
        Expression _next_1 = iterator.next();
        String _str_1 = this.toStr(_next_1);
        String _plus_2 = (res + _str_1);
        res = _plus_2;
      }
      boolean _hasNext_2 = iterator.hasNext();
      _while = _hasNext_2;
    }
    return (res + ")");
  }
  
  public String toStr(final ParExpression e) {
    Expression _expression = e.getExpression();
    String _str = this.toStr(_expression);
    String _plus = ("(" + _str);
    return (_plus + ")");
  }
  
  public String toStr(final Arguments arg) {
    String res = "";
    EList<Argument> _arguments = arg.getArguments();
    Iterator<Argument> iterator = _arguments.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      Argument a = iterator.next();
      String _identifier = a.getIdentifier();
      boolean _notEquals = (!Objects.equal(_identifier, null));
      if (_notEquals) {
        String _identifier_1 = a.getIdentifier();
        String _plus = (res + _identifier_1);
        String _plus_1 = (_plus + " = ");
        res = _plus_1;
      }
      AnyExpression _expression = a.getExpression();
      boolean _notEquals_1 = (!Objects.equal(_expression, null));
      if (_notEquals_1) {
        AnyExpression _expression_1 = a.getExpression();
        Object _str = this.toStr(_expression_1);
        String _plus_2 = (res + _str);
        res = _plus_2;
      }
    }
    boolean _hasNext_1 = iterator.hasNext();
    boolean _while = _hasNext_1;
    while (_while) {
      {
        String _plus_3 = (res + ", ");
        res = _plus_3;
        Argument a_1 = iterator.next();
        String _identifier_2 = a_1.getIdentifier();
        boolean _notEquals_2 = (!Objects.equal(_identifier_2, null));
        if (_notEquals_2) {
          String _identifier_3 = a_1.getIdentifier();
          String _plus_4 = (res + _identifier_3);
          String _plus_5 = (_plus_4 + " = ");
          res = _plus_5;
        }
        AnyExpression _expression_2 = a_1.getExpression();
        boolean _notEquals_3 = (!Objects.equal(_expression_2, null));
        if (_notEquals_3) {
          AnyExpression _expression_3 = a_1.getExpression();
          Object _str_1 = this.toStr(_expression_3);
          String _plus_6 = (res + _str_1);
          res = _plus_6;
        }
      }
      boolean _hasNext_2 = iterator.hasNext();
      _while = _hasNext_2;
    }
    return res;
  }
  
  public String toStr(final FormalArguments arg) {
    String res = "";
    EList<FormalArgument> _arguments = arg.getArguments();
    Iterator<FormalArgument> iterator = _arguments.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      FormalArgument a = iterator.next();
      String _identifier = a.getIdentifier();
      boolean _notEquals = (!Objects.equal(_identifier, null));
      if (_notEquals) {
        String _identifier_1 = a.getIdentifier();
        String _plus = (res + _identifier_1);
        res = _plus;
      }
    }
    boolean _hasNext_1 = iterator.hasNext();
    boolean _while = _hasNext_1;
    while (_while) {
      {
        String _plus_1 = (res + ", ");
        res = _plus_1;
        FormalArgument a_1 = iterator.next();
        String _identifier_2 = a_1.getIdentifier();
        boolean _notEquals_1 = (!Objects.equal(_identifier_2, null));
        if (_notEquals_1) {
          String _identifier_3 = a_1.getIdentifier();
          String _plus_2 = (res + _identifier_3);
          res = _plus_2;
        }
      }
      boolean _hasNext_2 = iterator.hasNext();
      _while = _hasNext_2;
    }
    return res;
  }
  
  public String toStr(final TargetBlock b) {
    String _externalCode = b.getExternalCode();
    String _externalCode_1 = b.getExternalCode();
    int _length = _externalCode_1.length();
    int _minus = (_length - 3);
    return _externalCode.substring(3, _minus);
  }
  
  public CharSequence print(final TargetBlock b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _externalCode = b.getExternalCode();
      boolean _notEquals = (!Objects.equal(_externalCode, null));
      if (_notEquals) {
        String _externalCode_1 = b.getExternalCode();
        String _externalCode_2 = b.getExternalCode();
        int _length = _externalCode_2.length();
        int _minus = (_length - 3);
        String printedCode = _externalCode_1.substring(3, _minus);
        _builder.newLineIfNotEmpty();
        _builder.append(printedCode, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print(final Block b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<BlockStatement> _statements = b.getStatements();
      for(final BlockStatement st : _statements) {
        CharSequence _print = this.print(st);
        _builder.append(_print, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print(final ObservationBlock b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<BlockStatement> _statements = b.getStatements();
      for(final BlockStatement s : _statements) {
        CharSequence _print = this.print(s);
        _builder.append(_print, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print(final SimulationBlock b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<BlockStatement> _statements = b.getStatements();
      for(final BlockStatement s : _statements) {
        CharSequence _print = this.print(s);
        _builder.append(_print, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print(final EstimationBlock b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<BlockStatement> _statements = b.getStatements();
      for(final BlockStatement s : _statements) {
        CharSequence _print = this.print(s);
        _builder.append(_print, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print(final BlockStatement st) {
    StringConcatenation _builder = new StringConcatenation();
    {
      SymbolDeclaration _symbol = st.getSymbol();
      boolean _notEquals = (!Objects.equal(_symbol, null));
      if (_notEquals) {
        SymbolDeclaration _symbol_1 = st.getSymbol();
        CharSequence _print = this.print(_symbol_1);
        _builder.append(_print, "");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      FunctionCall _functionCall = st.getFunctionCall();
      boolean _notEquals_1 = (!Objects.equal(_functionCall, null));
      if (_notEquals_1) {
        FunctionCall _functionCall_1 = st.getFunctionCall();
        CharSequence _print_1 = this.print(_functionCall_1);
        _builder.append(_print_1, "");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      ConditionalStatement _statement = st.getStatement();
      boolean _notEquals_2 = (!Objects.equal(_statement, null));
      if (_notEquals_2) {
        ConditionalStatement _statement_1 = st.getStatement();
        CharSequence _print_2 = this.print(_statement_1);
        _builder.append(_print_2, "");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      TargetBlock _targetBlock = st.getTargetBlock();
      boolean _notEquals_3 = (!Objects.equal(_targetBlock, null));
      if (_notEquals_3) {
        TargetBlock _targetBlock_1 = st.getTargetBlock();
        CharSequence _print_3 = this.print(_targetBlock_1);
        _builder.append(_print_3, "");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print(final ConditionalStatement s) {
    StringConcatenation _builder = new StringConcatenation();
    {
      ParExpression _parExpression = s.getParExpression();
      boolean _notEquals = (!Objects.equal(_parExpression, null));
      if (_notEquals) {
        _builder.append("if ");
        ParExpression _parExpression_1 = s.getParExpression();
        CharSequence _print = this.print(_parExpression_1);
        _builder.append(_print, "");
        _builder.newLineIfNotEmpty();
        {
          BlockStatement _ifStatement = s.getIfStatement();
          boolean _notEquals_1 = (!Objects.equal(_ifStatement, null));
          if (_notEquals_1) {
            _builder.append("\t");
            BlockStatement _ifStatement_1 = s.getIfStatement();
            Object _print_1 = this.print(_ifStatement_1);
            _builder.append(_print_1, "	");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          Block _ifBlock = s.getIfBlock();
          boolean _notEquals_2 = (!Objects.equal(_ifBlock, null));
          if (_notEquals_2) {
            _builder.append("\t");
            Block _ifBlock_1 = s.getIfBlock();
            Object _print_2 = this.print(_ifBlock_1);
            _builder.append(_print_2, "	");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          boolean _or = false;
          BlockStatement _elseStatement = s.getElseStatement();
          boolean _notEquals_3 = (!Objects.equal(_elseStatement, null));
          if (_notEquals_3) {
            _or = true;
          } else {
            Block _elseBlock = s.getElseBlock();
            boolean _notEquals_4 = (!Objects.equal(_elseBlock, null));
            _or = (_notEquals_3 || _notEquals_4);
          }
          if (_or) {
            _builder.append("else ");
            _builder.newLine();
            {
              BlockStatement _elseStatement_1 = s.getElseStatement();
              boolean _notEquals_5 = (!Objects.equal(_elseStatement_1, null));
              if (_notEquals_5) {
                _builder.append("\t");
                BlockStatement _elseStatement_2 = s.getElseStatement();
                Object _print_3 = this.print(_elseStatement_2);
                _builder.append(_print_3, "	");
                _builder.newLineIfNotEmpty();
              }
            }
            {
              Block _elseBlock_1 = s.getElseBlock();
              boolean _notEquals_6 = (!Objects.equal(_elseBlock_1, null));
              if (_notEquals_6) {
                _builder.append("\t");
                Block _elseBlock_2 = s.getElseBlock();
                Object _print_4 = this.print(_elseBlock_2);
                _builder.append(_print_4, "	");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence print(final FunctionCall call) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(call);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final SymbolDeclaration v) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(v);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final SymbolModification v) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(v);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final AnyExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final RandomList l) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(l);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final List l) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(l);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final OdeList l) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(l);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final Expression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final ConditionalExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final OrExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final AndExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final LogicalExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final AdditiveExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final MultiplicativeExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final PowerExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final UnaryExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final Primary p) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(p);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final ParExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final Arguments arg) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(arg);
    _builder.append(_str, "");
    return _builder;
  }
}
