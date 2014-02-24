package eu.ddmore.converter.mdl2pharmml;

import com.google.common.base.Objects;
import eu.ddmore.converter.mdlprinting.MdlPrinter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.Block;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.ConditionalStatement;
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
import org.ddmore.mdl.mdl.ParameterDeclaration;
import org.ddmore.mdl.mdl.ParameterObject;
import org.ddmore.mdl.mdl.ParameterObjectBlock;
import org.ddmore.mdl.mdl.RandomVariableDefinitionBlock;
import org.ddmore.mdl.mdl.StructuralBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VariabilityBlockStatement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function0;

@SuppressWarnings("all")
public class ReferenceResolver extends MdlPrinter {
  @Extension
  private Mcl mcl = null;
  
  public ReferenceResolver(final Mcl mcl) {
    this.mcl = mcl;
    this.prepareCollections(mcl);
  }
  
  protected HashSet<String> ind_vars = new Function0<HashSet<String>>() {
    public HashSet<String> apply() {
      HashSet<String> _hashSet = new HashSet<String>();
      return _hashSet;
    }
  }.apply();
  
  protected HashMap<String,HashSet<String>> vm_err_vars = new Function0<HashMap<String,HashSet<String>>>() {
    public HashMap<String,HashSet<String>> apply() {
      HashMap<String,HashSet<String>> _hashMap = new HashMap<String,HashSet<String>>();
      return _hashMap;
    }
  }.apply();
  
  protected HashMap<String,HashSet<String>> vm_mdl_vars = new Function0<HashMap<String,HashSet<String>>>() {
    public HashMap<String,HashSet<String>> apply() {
      HashMap<String,HashSet<String>> _hashMap = new HashMap<String,HashSet<String>>();
      return _hashMap;
    }
  }.apply();
  
  protected HashMap<String,HashSet<String>> cm_vars = new Function0<HashMap<String,HashSet<String>>>() {
    public HashMap<String,HashSet<String>> apply() {
      HashMap<String,HashSet<String>> _hashMap = new HashMap<String,HashSet<String>>();
      return _hashMap;
    }
  }.apply();
  
  protected HashMap<String,HashSet<String>> pm_vars = new Function0<HashMap<String,HashSet<String>>>() {
    public HashMap<String,HashSet<String>> apply() {
      HashMap<String,HashSet<String>> _hashMap = new HashMap<String,HashSet<String>>();
      return _hashMap;
    }
  }.apply();
  
  protected HashMap<String,HashSet<String>> om_vars = new Function0<HashMap<String,HashSet<String>>>() {
    public HashMap<String,HashSet<String>> apply() {
      HashMap<String,HashSet<String>> _hashMap = new HashMap<String,HashSet<String>>();
      return _hashMap;
    }
  }.apply();
  
  protected HashMap<String,HashSet<String>> sm_vars = new Function0<HashMap<String,HashSet<String>>>() {
    public HashMap<String,HashSet<String>> apply() {
      HashMap<String,HashSet<String>> _hashMap = new HashMap<String,HashSet<String>>();
      return _hashMap;
    }
  }.apply();
  
  public void prepareCollections(final Mcl m) {
    this.ind_vars.clear();
    this.vm_err_vars.clear();
    this.vm_mdl_vars.clear();
    this.cm_vars.clear();
    super.prepareCollections(m);
    EList<MclObject> _objects = m.getObjects();
    for (final MclObject o : _objects) {
      {
        ModelObject _modelObject = o.getModelObject();
        boolean _notEquals = (!Objects.equal(_modelObject, null));
        if (_notEquals) {
          ModelObject _modelObject_1 = o.getModelObject();
          HashSet<String> indVars = this.getIndependentVars(_modelObject_1);
          int _size = indVars.size();
          boolean _greaterThan = (_size > 0);
          if (_greaterThan) {
            this.ind_vars.addAll(indVars);
          }
          ModelObject _modelObject_2 = o.getModelObject();
          HashSet<String> errorVars = this.getLevelVars(_modelObject_2, "1");
          int _size_1 = errorVars.size();
          boolean _greaterThan_1 = (_size_1 > 0);
          if (_greaterThan_1) {
            ObjectName _identifier = o.getIdentifier();
            String _name = _identifier.getName();
            this.vm_err_vars.put(_name, errorVars);
          }
          ModelObject _modelObject_3 = o.getModelObject();
          HashSet<String> mdlVars = this.getLevelVars(_modelObject_3, "2");
          int _size_2 = mdlVars.size();
          boolean _greaterThan_2 = (_size_2 > 0);
          if (_greaterThan_2) {
            ObjectName _identifier_1 = o.getIdentifier();
            String _name_1 = _identifier_1.getName();
            this.vm_mdl_vars.put(_name_1, mdlVars);
          }
          ModelObject _modelObject_4 = o.getModelObject();
          HashSet<String> covariateVars = this.getCovariateVars(_modelObject_4);
          int _size_3 = covariateVars.size();
          boolean _greaterThan_3 = (_size_3 > 0);
          if (_greaterThan_3) {
            ObjectName _identifier_2 = o.getIdentifier();
            String _name_2 = _identifier_2.getName();
            this.cm_vars.put(_name_2, covariateVars);
          }
          ModelObject _modelObject_5 = o.getModelObject();
          HashSet<String> structuralVars = this.getStructuralVars(_modelObject_5);
          int _size_4 = structuralVars.size();
          boolean _greaterThan_4 = (_size_4 > 0);
          if (_greaterThan_4) {
            ObjectName _identifier_3 = o.getIdentifier();
            String _name_3 = _identifier_3.getName();
            this.sm_vars.put(_name_3, structuralVars);
          }
          ModelObject _modelObject_6 = o.getModelObject();
          HashSet<String> parameters = this.getParameters(_modelObject_6);
          int _size_5 = parameters.size();
          boolean _greaterThan_5 = (_size_5 > 0);
          if (_greaterThan_5) {
            ObjectName _identifier_4 = o.getIdentifier();
            String _name_4 = _identifier_4.getName();
            this.pm_vars.put(_name_4, parameters);
          }
          ModelObject _modelObject_7 = o.getModelObject();
          HashSet<String> observationVars = this.getObservationVars(_modelObject_7);
          int _size_6 = observationVars.size();
          boolean _greaterThan_6 = (_size_6 > 0);
          if (_greaterThan_6) {
            ObjectName _identifier_5 = o.getIdentifier();
            String _name_5 = _identifier_5.getName();
            this.sm_vars.put(_name_5, observationVars);
          }
        }
        ParameterObject _parameterObject = o.getParameterObject();
        boolean _notEquals_1 = (!Objects.equal(_parameterObject, null));
        if (_notEquals_1) {
          ParameterObject _parameterObject_1 = o.getParameterObject();
          HashSet<String> parameters_1 = this.getParameters(_parameterObject_1);
          int _size_7 = parameters_1.size();
          boolean _greaterThan_7 = (_size_7 > 0);
          if (_greaterThan_7) {
            ObjectName _identifier_6 = o.getIdentifier();
            String _name_6 = _identifier_6.getName();
            this.pm_vars.put(_name_6, parameters_1);
          }
        }
      }
    }
  }
  
  public String getReferenceBlock(final FullyQualifiedSymbolName ref) {
    ObjectName _object = ref.getObject();
    boolean _notEquals = (!Objects.equal(_object, null));
    if (_notEquals) {
      ObjectName _object_1 = ref.getObject();
      String _name = _object_1.getName();
      HashSet<String> source = this.vm_err_vars.get(_name);
      boolean _notEquals_1 = (!Objects.equal(source, null));
      if (_notEquals_1) {
        String _identifier = ref.getIdentifier();
        boolean _contains = source.contains(_identifier);
        if (_contains) {
          ObjectName _object_2 = ref.getObject();
          String _name_1 = _object_2.getName();
          return ("vm_err." + _name_1);
        }
      }
      ObjectName _object_3 = ref.getObject();
      String _name_2 = _object_3.getName();
      HashSet<String> _get = this.vm_mdl_vars.get(_name_2);
      source = _get;
      boolean _notEquals_2 = (!Objects.equal(source, null));
      if (_notEquals_2) {
        String _identifier_1 = ref.getIdentifier();
        boolean _contains_1 = source.contains(_identifier_1);
        if (_contains_1) {
          ObjectName _object_4 = ref.getObject();
          String _name_3 = _object_4.getName();
          return ("vm_mdl." + _name_3);
        }
      }
      ObjectName _object_5 = ref.getObject();
      String _name_4 = _object_5.getName();
      HashSet<String> _get_1 = this.cm_vars.get(_name_4);
      source = _get_1;
      boolean _notEquals_3 = (!Objects.equal(source, null));
      if (_notEquals_3) {
        String _identifier_2 = ref.getIdentifier();
        boolean _contains_2 = source.contains(_identifier_2);
        if (_contains_2) {
          ObjectName _object_6 = ref.getObject();
          String _name_5 = _object_6.getName();
          return ("cm." + _name_5);
        }
      }
      ObjectName _object_7 = ref.getObject();
      String _name_6 = _object_7.getName();
      HashSet<String> _get_2 = this.om_vars.get(_name_6);
      source = _get_2;
      boolean _notEquals_4 = (!Objects.equal(source, null));
      if (_notEquals_4) {
        String _identifier_3 = ref.getIdentifier();
        boolean _contains_3 = source.contains(_identifier_3);
        if (_contains_3) {
          ObjectName _object_8 = ref.getObject();
          String _name_7 = _object_8.getName();
          return ("om." + _name_7);
        }
      }
      ObjectName _object_9 = ref.getObject();
      String _name_8 = _object_9.getName();
      HashSet<String> _get_3 = this.sm_vars.get(_name_8);
      source = _get_3;
      boolean _notEquals_5 = (!Objects.equal(source, null));
      if (_notEquals_5) {
        String _identifier_4 = ref.getIdentifier();
        boolean _contains_4 = source.contains(_identifier_4);
        if (_contains_4) {
          ObjectName _object_10 = ref.getObject();
          String _name_9 = _object_10.getName();
          return ("sm." + _name_9);
        }
      }
      ObjectName _object_11 = ref.getObject();
      String _name_10 = _object_11.getName();
      HashSet<String> _get_4 = this.pm_vars.get(_name_10);
      source = _get_4;
      boolean _notEquals_6 = (!Objects.equal(source, null));
      if (_notEquals_6) {
        String _identifier_5 = ref.getIdentifier();
        boolean _contains_5 = source.contains(_identifier_5);
        if (_contains_5) {
          ObjectName _object_12 = ref.getObject();
          String _name_11 = _object_12.getName();
          return ("sm." + _name_11);
        }
      }
      ObjectName _object_13 = ref.getObject();
      return _object_13.getName();
    } else {
      Set<Entry<String,HashSet<String>>> _entrySet = this.vm_err_vars.entrySet();
      for (final Entry<String,HashSet<String>> set : _entrySet) {
        HashSet<String> _value = set.getValue();
        String _identifier_6 = ref.getIdentifier();
        boolean _contains_6 = _value.contains(_identifier_6);
        if (_contains_6) {
          String _key = set.getKey();
          return ("vm_err." + _key);
        }
      }
      Set<Entry<String,HashSet<String>>> _entrySet_1 = this.vm_mdl_vars.entrySet();
      for (final Entry<String,HashSet<String>> set_1 : _entrySet_1) {
        HashSet<String> _value_1 = set_1.getValue();
        String _identifier_7 = ref.getIdentifier();
        boolean _contains_7 = _value_1.contains(_identifier_7);
        if (_contains_7) {
          String _key_1 = set_1.getKey();
          return ("vm_mdl." + _key_1);
        }
      }
      Set<Entry<String,HashSet<String>>> _entrySet_2 = this.cm_vars.entrySet();
      for (final Entry<String,HashSet<String>> set_2 : _entrySet_2) {
        HashSet<String> _value_2 = set_2.getValue();
        String _identifier_8 = ref.getIdentifier();
        boolean _contains_8 = _value_2.contains(_identifier_8);
        if (_contains_8) {
          String _key_2 = set_2.getKey();
          return ("cm." + _key_2);
        }
      }
      Set<Entry<String,HashSet<String>>> _entrySet_3 = this.om_vars.entrySet();
      for (final Entry<String,HashSet<String>> set_3 : _entrySet_3) {
        HashSet<String> _value_3 = set_3.getValue();
        String _identifier_9 = ref.getIdentifier();
        boolean _contains_9 = _value_3.contains(_identifier_9);
        if (_contains_9) {
          String _key_3 = set_3.getKey();
          return ("om." + _key_3);
        }
      }
      Set<Entry<String,HashSet<String>>> _entrySet_4 = this.sm_vars.entrySet();
      for (final Entry<String,HashSet<String>> set_4 : _entrySet_4) {
        HashSet<String> _value_4 = set_4.getValue();
        String _identifier_10 = ref.getIdentifier();
        boolean _contains_10 = _value_4.contains(_identifier_10);
        if (_contains_10) {
          String _key_4 = set_4.getKey();
          return ("sm." + _key_4);
        }
      }
      Set<Entry<String,HashSet<String>>> _entrySet_5 = this.pm_vars.entrySet();
      for (final Entry<String,HashSet<String>> set_5 : _entrySet_5) {
        HashSet<String> _value_5 = set_5.getValue();
        String _identifier_11 = ref.getIdentifier();
        boolean _contains_11 = _value_5.contains(_identifier_11);
        if (_contains_11) {
          String _key_5 = set_5.getKey();
          return ("sm." + _key_5);
        }
      }
      return "";
    }
  }
  
  public HashSet<String> getIndependentVars(final ModelObject obj) {
    HashSet<String> _hashSet = new HashSet<String>();
    HashSet<String> independentVars = _hashSet;
    EList<ModelObjectBlock> _blocks = obj.getBlocks();
    for (final ModelObjectBlock block : _blocks) {
      InputVariablesBlock _inputVariablesBlock = block.getInputVariablesBlock();
      boolean _notEquals = (!Objects.equal(_inputVariablesBlock, null));
      if (_notEquals) {
        InputVariablesBlock _inputVariablesBlock_1 = block.getInputVariablesBlock();
        EList<SymbolDeclaration> _variables = _inputVariablesBlock_1.getVariables();
        for (final SymbolDeclaration s : _variables) {
          AnyExpression _expression = s.getExpression();
          boolean _notEquals_1 = (!Objects.equal(_expression, null));
          if (_notEquals_1) {
            AnyExpression _expression_1 = s.getExpression();
            List _list = _expression_1.getList();
            boolean _notEquals_2 = (!Objects.equal(_list, null));
            if (_notEquals_2) {
              AnyExpression _expression_2 = s.getExpression();
              List _list_1 = _expression_2.getList();
              Arguments _arguments = _list_1.getArguments();
              String use = this.getAttribute(_arguments, "use");
              boolean _and = false;
              boolean _equalsIgnoreCase = use.equalsIgnoreCase("idv");
              if (!_equalsIgnoreCase) {
                _and = false;
              } else {
                String _identifier = s.getIdentifier();
                boolean _contains = independentVars.contains(_identifier);
                boolean _not = (!_contains);
                _and = (_equalsIgnoreCase && _not);
              }
              if (_and) {
                String _identifier_1 = s.getIdentifier();
                independentVars.add(_identifier_1);
              }
            }
          }
        }
      }
    }
    return independentVars;
  }
  
  public HashSet<String> getCovariateVars(final ModelObject obj) {
    HashSet<String> _hashSet = new HashSet<String>();
    HashSet<String> covariateVars = _hashSet;
    EList<ModelObjectBlock> _blocks = obj.getBlocks();
    for (final ModelObjectBlock b : _blocks) {
      InputVariablesBlock _inputVariablesBlock = b.getInputVariablesBlock();
      boolean _notEquals = (!Objects.equal(_inputVariablesBlock, null));
      if (_notEquals) {
        InputVariablesBlock _inputVariablesBlock_1 = b.getInputVariablesBlock();
        EList<SymbolDeclaration> _variables = _inputVariablesBlock_1.getVariables();
        for (final SymbolDeclaration s : _variables) {
          AnyExpression _expression = s.getExpression();
          boolean _notEquals_1 = (!Objects.equal(_expression, null));
          if (_notEquals_1) {
            AnyExpression _expression_1 = s.getExpression();
            List _list = _expression_1.getList();
            boolean _notEquals_2 = (!Objects.equal(_list, null));
            if (_notEquals_2) {
              AnyExpression _expression_2 = s.getExpression();
              List _list_1 = _expression_2.getList();
              Arguments _arguments = _list_1.getArguments();
              String use = this.getAttribute(_arguments, "use");
              boolean _equalsIgnoreCase = use.equalsIgnoreCase("covariate");
              if (_equalsIgnoreCase) {
                String _identifier = s.getIdentifier();
                boolean _contains = covariateVars.contains(_identifier);
                boolean _not = (!_contains);
                if (_not) {
                  String _identifier_1 = s.getIdentifier();
                  covariateVars.add(_identifier_1);
                }
              }
            }
          }
        }
      }
    }
    return covariateVars;
  }
  
  public HashSet<String> getParameters(final ModelObject obj) {
    HashSet<String> _hashSet = new HashSet<String>();
    HashSet<String> parameters = _hashSet;
    EList<ModelObjectBlock> _blocks = obj.getBlocks();
    for (final ModelObjectBlock b : _blocks) {
      {
        GroupVariablesBlock _groupVariablesBlock = b.getGroupVariablesBlock();
        boolean _notEquals = (!Objects.equal(_groupVariablesBlock, null));
        if (_notEquals) {
          GroupVariablesBlock _groupVariablesBlock_1 = b.getGroupVariablesBlock();
          EList<GroupVariablesBlockStatement> _statements = _groupVariablesBlock_1.getStatements();
          for (final GroupVariablesBlockStatement st : _statements) {
            BlockStatement _statement = st.getStatement();
            boolean _notEquals_1 = (!Objects.equal(_statement, null));
            if (_notEquals_1) {
              BlockStatement _statement_1 = st.getStatement();
              HashSet<String> _symbols = this.getSymbols(_statement_1);
              parameters.addAll(_symbols);
            }
          }
        }
        RandomVariableDefinitionBlock _randomVariableDefinitionBlock = b.getRandomVariableDefinitionBlock();
        boolean _notEquals_2 = (!Objects.equal(_randomVariableDefinitionBlock, null));
        if (_notEquals_2) {
          RandomVariableDefinitionBlock _randomVariableDefinitionBlock_1 = b.getRandomVariableDefinitionBlock();
          EList<SymbolDeclaration> _variables = _randomVariableDefinitionBlock_1.getVariables();
          for (final SymbolDeclaration s : _variables) {
            String _identifier = s.getIdentifier();
            Object _get = this.eps_vars.get(_identifier);
            boolean _notEquals_3 = (!Objects.equal(_get, null));
            if (_notEquals_3) {
              String _identifier_1 = s.getIdentifier();
              parameters.add(_identifier_1);
            }
          }
        }
        IndividualVariablesBlock _individualVariablesBlock = b.getIndividualVariablesBlock();
        boolean _notEquals_4 = (!Objects.equal(_individualVariablesBlock, null));
        if (_notEquals_4) {
          IndividualVariablesBlock _individualVariablesBlock_1 = b.getIndividualVariablesBlock();
          EList<BlockStatement> _statements_1 = _individualVariablesBlock_1.getStatements();
          for (final BlockStatement s_1 : _statements_1) {
            HashSet<String> _symbols_1 = this.getSymbols(s_1);
            parameters.addAll(_symbols_1);
          }
        }
      }
    }
    return parameters;
  }
  
  public HashSet<String> getParameters(final ParameterObject obj) {
    HashSet<String> _hashSet = new HashSet<String>();
    HashSet<String> parameters = _hashSet;
    EList<ParameterObjectBlock> _blocks = obj.getBlocks();
    for (final ParameterObjectBlock b : _blocks) {
      {
        StructuralBlock _structuralBlock = b.getStructuralBlock();
        boolean _notEquals = (!Objects.equal(_structuralBlock, null));
        if (_notEquals) {
          StructuralBlock _structuralBlock_1 = b.getStructuralBlock();
          EList<ParameterDeclaration> _parameters = _structuralBlock_1.getParameters();
          for (final ParameterDeclaration id : _parameters) {
            String _identifier = id.getIdentifier();
            parameters.add(_identifier);
          }
        }
        VariabilityBlock _variabilityBlock = b.getVariabilityBlock();
        boolean _notEquals_1 = (!Objects.equal(_variabilityBlock, null));
        if (_notEquals_1) {
          VariabilityBlock _variabilityBlock_1 = b.getVariabilityBlock();
          EList<VariabilityBlockStatement> _statements = _variabilityBlock_1.getStatements();
          for (final VariabilityBlockStatement st : _statements) {
            ParameterDeclaration _parameter = st.getParameter();
            boolean _notEquals_2 = (!Objects.equal(_parameter, null));
            if (_notEquals_2) {
              ParameterDeclaration _parameter_1 = st.getParameter();
              String _identifier_1 = _parameter_1.getIdentifier();
              parameters.add(_identifier_1);
            }
          }
        }
      }
    }
    return parameters;
  }
  
  public HashSet<String> getObservationVars(final ModelObject obj) {
    HashSet<String> _hashSet = new HashSet<String>();
    HashSet<String> observationVars = _hashSet;
    EList<ModelObjectBlock> _blocks = obj.getBlocks();
    for (final ModelObjectBlock b : _blocks) {
      ObservationBlock _observationBlock = b.getObservationBlock();
      boolean _notEquals = (!Objects.equal(_observationBlock, null));
      if (_notEquals) {
        ObservationBlock _observationBlock_1 = b.getObservationBlock();
        EList<BlockStatement> _statements = _observationBlock_1.getStatements();
        for (final BlockStatement st : _statements) {
          SymbolDeclaration _symbol = st.getSymbol();
          boolean _notEquals_1 = (!Objects.equal(_symbol, null));
          if (_notEquals_1) {
            SymbolDeclaration _symbol_1 = st.getSymbol();
            String _identifier = _symbol_1.getIdentifier();
            observationVars.add(_identifier);
            SymbolDeclaration _symbol_2 = st.getSymbol();
            AnyExpression _expression = _symbol_2.getExpression();
            boolean _notEquals_2 = (!Objects.equal(_expression, null));
            if (_notEquals_2) {
              SymbolDeclaration _symbol_3 = st.getSymbol();
              AnyExpression _expression_1 = _symbol_3.getExpression();
              Expression _expression_2 = _expression_1.getExpression();
              boolean _notEquals_3 = (!Objects.equal(_expression_2, null));
              if (_notEquals_3) {
                SymbolDeclaration _symbol_4 = st.getSymbol();
                AnyExpression _expression_3 = _symbol_4.getExpression();
                Expression _expression_4 = _expression_3.getExpression();
                HashSet<String> _referencesToRandomVars = this.getReferencesToRandomVars(_expression_4);
                observationVars.addAll(_referencesToRandomVars);
              }
            }
          }
        }
      }
    }
    return observationVars;
  }
  
  public HashSet<String> getStructuralVars(final ModelObject obj) {
    HashSet<String> _hashSet = new HashSet<String>();
    HashSet<String> structuralVars = _hashSet;
    EList<ModelObjectBlock> _blocks = obj.getBlocks();
    for (final ModelObjectBlock b : _blocks) {
      ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
      boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
      if (_notEquals) {
        ModelPredictionBlock _modelPredictionBlock_1 = b.getModelPredictionBlock();
        EList<ModelPredictionBlockStatement> _statements = _modelPredictionBlock_1.getStatements();
        for (final ModelPredictionBlockStatement st : _statements) {
          BlockStatement _statement = st.getStatement();
          boolean _notEquals_1 = (!Objects.equal(_statement, null));
          if (_notEquals_1) {
            BlockStatement _statement_1 = st.getStatement();
            HashSet<String> _symbols = this.getSymbols(_statement_1);
            structuralVars.addAll(_symbols);
          } else {
            OdeBlock _odeBlock = st.getOdeBlock();
            boolean _notEquals_2 = (!Objects.equal(_odeBlock, null));
            if (_notEquals_2) {
              OdeBlock _odeBlock_1 = st.getOdeBlock();
              EList<BlockStatement> _statements_1 = _odeBlock_1.getStatements();
              for (final BlockStatement s : _statements_1) {
                HashSet<String> _symbols_1 = this.getSymbols(s);
                structuralVars.addAll(_symbols_1);
              }
            }
          }
        }
      }
    }
    return structuralVars;
  }
  
  public HashSet<String> getSymbols(final BlockStatement b) {
    HashSet<String> _hashSet = new HashSet<String>();
    HashSet<String> symbols = _hashSet;
    SymbolDeclaration _symbol = b.getSymbol();
    boolean _notEquals = (!Objects.equal(_symbol, null));
    if (_notEquals) {
      SymbolDeclaration _symbol_1 = b.getSymbol();
      String _identifier = _symbol_1.getIdentifier();
      boolean _contains = symbols.contains(_identifier);
      boolean _not = (!_contains);
      if (_not) {
        SymbolDeclaration _symbol_2 = b.getSymbol();
        String _identifier_1 = _symbol_2.getIdentifier();
        symbols.add(_identifier_1);
      }
    }
    ConditionalStatement _statement = b.getStatement();
    boolean _notEquals_1 = (!Objects.equal(_statement, null));
    if (_notEquals_1) {
      final ConditionalStatement s = b.getStatement();
      BlockStatement _ifStatement = s.getIfStatement();
      boolean _notEquals_2 = (!Objects.equal(_ifStatement, null));
      if (_notEquals_2) {
        BlockStatement _ifStatement_1 = s.getIfStatement();
        HashSet<String> _symbols = this.getSymbols(_ifStatement_1);
        symbols.addAll(_symbols);
      }
      BlockStatement _elseStatement = s.getElseStatement();
      boolean _notEquals_3 = (!Objects.equal(_elseStatement, null));
      if (_notEquals_3) {
        BlockStatement _elseStatement_1 = s.getElseStatement();
        HashSet<String> _symbols_1 = this.getSymbols(_elseStatement_1);
        symbols.addAll(_symbols_1);
      }
      Block _ifBlock = s.getIfBlock();
      boolean _notEquals_4 = (!Objects.equal(_ifBlock, null));
      if (_notEquals_4) {
        Block _ifBlock_1 = s.getIfBlock();
        EList<BlockStatement> _statements = _ifBlock_1.getStatements();
        for (final BlockStatement bb : _statements) {
          HashSet<String> _symbols_2 = this.getSymbols(bb);
          symbols.addAll(_symbols_2);
        }
      }
      Block _elseBlock = s.getElseBlock();
      boolean _notEquals_5 = (!Objects.equal(_elseBlock, null));
      if (_notEquals_5) {
        Block _elseBlock_1 = s.getElseBlock();
        EList<BlockStatement> _statements_1 = _elseBlock_1.getStatements();
        for (final BlockStatement bb_1 : _statements_1) {
          HashSet<String> _symbols_3 = this.getSymbols(bb_1);
          symbols.addAll(_symbols_3);
        }
      }
    }
    return symbols;
  }
  
  public boolean isDataVariable(final String s) {
    Object _get = this.eps_vars.get(s);
    return (!Objects.equal(_get, null));
  }
  
  public boolean isIndependentVariable(final String s) {
    Object _get = this.eta_vars.get(s);
    return (!Objects.equal(_get, null));
  }
  
  public HashSet<String> getReferencesToRandomVars(final Expression expr) {
    HashSet<String> _hashSet = new HashSet<String>();
    HashSet<String> classifiedVars = _hashSet;
    TreeIterator<EObject> iterator = expr.eAllContents();
    boolean _hasNext = iterator.hasNext();
    boolean _while = _hasNext;
    while (_while) {
      {
        EObject obj = iterator.next();
        if ((obj instanceof FullyQualifiedSymbolName)) {
          FullyQualifiedSymbolName ref = ((FullyQualifiedSymbolName) obj);
          String _str = this.toStr(ref);
          boolean _contains = classifiedVars.contains(_str);
          boolean _not = (!_contains);
          if (_not) {
            String _str_1 = this.toStr(ref);
            Object _get = this.eps_vars.get(_str_1);
            boolean _notEquals = (!Objects.equal(_get, null));
            if (_notEquals) {
              String _str_2 = this.toStr(ref);
              classifiedVars.add(_str_2);
            }
          }
        }
      }
      boolean _hasNext_1 = iterator.hasNext();
      _while = _hasNext_1;
    }
    return classifiedVars;
  }
}
