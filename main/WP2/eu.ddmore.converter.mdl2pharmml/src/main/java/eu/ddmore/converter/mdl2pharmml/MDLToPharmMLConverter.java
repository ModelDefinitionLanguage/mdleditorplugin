package eu.ddmore.converter.mdl2pharmml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.ddmore.mdl.MdlStandaloneSetup;
import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.ConditionalExpression;
import org.ddmore.mdl.mdl.ConditionalStatement;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName;
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.GroupVariablesBlock;
import org.ddmore.mdl.mdl.GroupVariablesBlockStatement;
import org.ddmore.mdl.mdl.IndividualVariablesBlock;
import org.ddmore.mdl.mdl.InputVariablesBlock;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.MdlFactory;
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
import org.ddmore.mdl.mdl.ParExpression;
import org.ddmore.mdl.mdl.ParameterDeclaration;
import org.ddmore.mdl.mdl.ParameterObject;
import org.ddmore.mdl.mdl.ParameterObjectBlock;
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.Primary;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.RandomVariableDefinitionBlock;
import org.ddmore.mdl.mdl.StructuralBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.SymbolModification;
import org.ddmore.mdl.mdl.UnaryExpression;
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VariabilityBlockStatement;
import org.ddmore.mdl.mdl.Vector;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.MapExtensions;

import com.google.common.base.Objects;
import com.google.inject.Injector;

import eu.ddmore.converter.mdlprinting.MdlPrinterUtility;
import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.spi.ConverterProvider;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;

public class MDLToPharmMLConverter extends MdlPrinterUtility implements ConverterProvider {

    private final String xsi = "http://www.w3.org/2001/XMLSchema-instance";
    private final String xsi_schemaLocation = "http://www.pharmml.org/2013/03/PharmML http://www.pharmml.org/2013/03/PharmML";
    private final String xmlns_pharmML = "http://www.pharmml.org/2013/03/PharmML";
    private final String xmlns_math = "http://www.pharmml.org/2013/03/Maths";
    private final String xmlns_ct = "http://www.pharmml.org/2013/03/CommonTypes";
    private final String xmlns_mdef = "http://www.pharmml.org/2013/03/ModelDefinition";
    private final String xmlns_mstep = "http://www.pharmml.org/2013/03/ModellingSteps";
    private final String xmlns_design = "http://www.pharmml.org/2013/03/TrialDesign";
    private final String xmlns_uncert = "http://www.pharmml.org/2013/03/Uncertainty";    
    private final String xmlns_dataSet = "http://www.pharmml.org/2013/08/Dataset";
    private final String TYPE_INT = "int";
    private final String TYPE_REAL = "real";
    private final String writtenVersion = "0.1";    
    private Mcl mcl = null;

    private LanguageVersion source;
    private LanguageVersion target;
    private Version converterVersion;

    public MDLToPharmMLConverter() {
        source = new LanguageVersionImpl();
        source.setLanguage("MDL");
        Version sourceVersion = new VersionImpl();
        sourceVersion.setMajor(5);
        sourceVersion.setMinor(0);
        sourceVersion.setPatch(8);
        source.setVersion(sourceVersion);

        target = new LanguageVersionImpl();
        target.setLanguage("PharmML");
        Version targetVersion = new VersionImpl();
        targetVersion.setMajor(0);
        targetVersion.setMinor(2);
        targetVersion.setPatch(1);
        target.setVersion(targetVersion);
        
        converterVersion = new VersionImpl();
        converterVersion.setMajor(1);
        converterVersion.setMinor(0);
        converterVersion.setPatch(2);
    }

    @Override
    public ConversionReport performConvert(File src, File outputDirectory) {
        Injector injector = new MdlStandaloneSetup().createInjectorAndDoEMFRegistration();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

        Resource resource = resourceSet.getResource(URI.createURI("file:///" + src.getAbsolutePath()), true);
        Mcl mcl = (Mcl) resource.getContents().get(0);

        CharSequence converted = convertToPharmML(mcl);

        return printOutputFile(src, outputDirectory, converted.toString(), ".xml");
    }

    @Override
    public ConversionReport[] performConvert(File[] src, File outputDirectory) {
        ConversionReport[] reports = new ConversionReport[src.length];
        int i = 0;
        for (File f : src) {
            reports[i++] = performConvert(f, outputDirectory);
        }
        return reports;
    }

    @Override
    public LanguageVersion getSource() {
        return source;
    }

    @Override
    public LanguageVersion getTarget() {
        return target;
    }

    @Override
    public Version getConverterVersion() {
        return converterVersion;
    }

    @Override
    public String toString() {
        return String.format("MDLToPharmMLConverter [source=%s, target=%s, converterVersion=%s]", source, target, converterVersion) ;
    }

    
    public CharSequence convertToPharmML(final Mcl m) {
      CharSequence _xblockexpression = null;
      {
        this.mcl = m;
        this.prepareCollections(m);
        String idv = this.getIndependentVariable(m);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        _builder.newLine();
        _builder.append("<PharmML ");
        _builder.newLine();
        _builder.append("\t");
        CharSequence _print_PharmML_NameSpaces = this.print_PharmML_NameSpaces();
        _builder.append(_print_PharmML_NameSpaces, "  ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("writtenVersion=\"");
        _builder.append(this.writtenVersion, "    ");
        _builder.append("\">");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("<ct:Name>\"");
        Resource _eResource = m.eResource();
        String _fileName = this.fileName(_eResource);
        _builder.append(_fileName, "  ");
        _builder.append("\"</ct:Name>");
        _builder.newLineIfNotEmpty();
        {
          boolean _notEquals = (!Objects.equal(idv, null));
          if (_notEquals) {
            _builder.append("\t");
            _builder.append("<IndependentVariable symbID=\"");
            _builder.append(idv, "    ");
            _builder.append("\"/>");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        CharSequence _print_mdef_ModelDefinition = this.print_mdef_ModelDefinition();
        _builder.append(_print_mdef_ModelDefinition, "    ");
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
      _builder.append("xmlns:uncert=\"");
      _builder.append(this.xmlns_uncert, "");
      _builder.append("\"");
      _builder.newLineIfNotEmpty();
      return _builder;
    }
    
    public CharSequence print_mdef_ModelDefinition() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<ModelDefinition xmlns=\"");
      _builder.append(this.xmlns_mdef, "");
      _builder.append("\">");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_mdef_VariabilityModel = this.print_mdef_VariabilityModel();
      _builder.append(_print_mdef_VariabilityModel, " ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      String _print_mdef_CovariateModel = this.print_mdef_CovariateModel();
      _builder.append(_print_mdef_CovariateModel, "   ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      String _print_mdef_ParameterModel = this.print_mdef_ParameterModel();
      _builder.append(_print_mdef_ParameterModel, "   ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      String _print_mdef_StructuralModel = this.print_mdef_StructuralModel();
      _builder.append(_print_mdef_StructuralModel, "  ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      String _print_mdef_ObservationModel = this.print_mdef_ObservationModel();
      _builder.append(_print_mdef_ObservationModel, " ");
      _builder.newLineIfNotEmpty();
      _builder.append("</ModelDefinition>");
      _builder.newLine();
      return _builder;
    }
    
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
        _builder.append("symbId=\"combinedErrorModel\" symbolType=\"real\">");
        _builder.newLine();
        {
          for(final String arg : arguments) {
            _builder.append("\t");
            _builder.append("<FunctionArgument symbId=\"");
            _builder.append(arg, "    ");
            _builder.append("\" symbolType=\"");
            _builder.append(this.TYPE_REAL, " ");
            _builder.append("\"/>");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("<Definition>");
        _builder.newLine();
        _builder.append("\t\t");
        CharSequence _print_Math_Equation = this.print_Math_Equation(expr);
        _builder.append(_print_Math_Equation, "       ");
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
    
    public CharSequence print_mdef_VariabilityModel() {
      CharSequence _xblockexpression = null;
      {
        StringConcatenation _builder = new StringConcatenation();
        String model = _builder.toString();
        Set<Entry<Object,Object>> _entrySet = this.level_vars.entrySet();
        for (final Entry<Object,Object> e : _entrySet) {
          {
            Object _value = e.getValue();
            boolean _equals = _value.equals("1");
            if (_equals) {
              StringConcatenation _builder_1 = new StringConcatenation();
              Object _key = e.getKey();
              CharSequence _print_mdef_VariabilityModel = this.print_mdef_VariabilityModel("obsErr", "error", ((String) _key));
              _builder_1.append(_print_mdef_VariabilityModel, "");
              String _plus = (model + _builder_1);
              model = _plus;
            }
            Object _value_1 = e.getValue();
            boolean _equals_1 = _value_1.equals("2");
            if (_equals_1) {
              StringConcatenation _builder_2 = new StringConcatenation();
              Object _key_1 = e.getKey();
              CharSequence _print_mdef_VariabilityModel_1 = this.print_mdef_VariabilityModel("model", "model", ((String) _key_1));
              _builder_2.append(_print_mdef_VariabilityModel_1, "");
              String _plus_1 = (model + _builder_2);
              model = _plus_1;
            }
          }
        }
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(model, "");
        _xblockexpression = (_builder_1);
      }
      return _xblockexpression;
    }
    
    public CharSequence print_mdef_VariabilityModel(final String blkId, final String type, final String symbId) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<VariabilityModel blkId=\"");
      _builder.append(blkId, "");
      _builder.append("\" type=\"");
      _builder.append(type, "");
      _builder.append("\">");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("<Level symbId=\"");
      _builder.append(symbId, "   ");
      _builder.append("\"/>");
      _builder.newLineIfNotEmpty();
      _builder.append("</VariabilityModel>");
      _builder.newLine();
      return _builder;
    }
    
    public String print_mdef_CovariateModel() {
      String model = "";
      EList<MclObject> _objects = this.mcl.getObjects();
      for (final MclObject o : _objects) {
        ModelObject _modelObject = o.getModelObject();
        boolean _notEquals = (!Objects.equal(_modelObject, null));
        if (_notEquals) {
          String modelBlock = "";
          ModelObject _modelObject_1 = o.getModelObject();
          EList<ModelObjectBlock> _blocks = _modelObject_1.getBlocks();
          for (final ModelObjectBlock b : _blocks) {
            InputVariablesBlock _inputVariablesBlock = b.getInputVariablesBlock();
            boolean _notEquals_1 = (!Objects.equal(_inputVariablesBlock, null));
            if (_notEquals_1) {
              InputVariablesBlock _inputVariablesBlock_1 = b.getInputVariablesBlock();
              EList<SymbolDeclaration> _variables = _inputVariablesBlock_1.getVariables();
              for (final SymbolDeclaration s : _variables) {
                AnyExpression _expression = s.getExpression();
                boolean _notEquals_2 = (!Objects.equal(_expression, null));
                if (_notEquals_2) {
                  AnyExpression _expression_1 = s.getExpression();
                  org.ddmore.mdl.mdl.List _list = _expression_1.getList();
                  boolean _notEquals_3 = (!Objects.equal(_list, null));
                  if (_notEquals_3) {
                    AnyExpression _expression_2 = s.getExpression();
                    org.ddmore.mdl.mdl.List _list_1 = _expression_2.getList();
                    Arguments _arguments = _list_1.getArguments();
                    String use = this.getAttribute(_arguments, "use");
                    boolean _equalsIgnoreCase = use.equalsIgnoreCase("covariate");
                    if (_equalsIgnoreCase) {
                      String _identifier = s.getIdentifier();
                      CharSequence _print_mdef_CovariateModel = this.print_mdef_CovariateModel(_identifier);
                      String _plus = (modelBlock + _print_mdef_CovariateModel);
                      modelBlock = _plus;
                    }
                  }
                }
              }
            }
          }
          StringConcatenation _builder = new StringConcatenation();
          {
            int _length = modelBlock.length();
            boolean _greaterThan = (_length > 0);
            if (_greaterThan) {
              _builder.append("<CovariateModel blkId=\"");
              ObjectName _identifier_1 = o.getIdentifier();
              String _name = _identifier_1.getName();
              _builder.append(_name, "");
              _builder.append("\">");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append(modelBlock, "   ");
              _builder.newLineIfNotEmpty();
              _builder.append("</CovariateModel>");
              _builder.newLine();
            }
          }
          String _plus_1 = (model + _builder);
          model = _plus_1;
        }
      }
      return model;
    }
    
    public CharSequence print_mdef_CovariateModel(final String symbId) {
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
      _builder.append(symbId, "               ");
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
    
    public String print_mdef_ParameterModel() {
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
                      SymbolDeclaration _symbol = _statement_1.getSymbol();
                      boolean _notEquals_4 = (!Objects.equal(_symbol, null));
                      if (_notEquals_4) {
                        BlockStatement _statement_2 = st.getStatement();
                        SymbolDeclaration _symbol_1 = _statement_2.getSymbol();
                        final String id = _symbol_1.getIdentifier();
                        BlockStatement _statement_3 = st.getStatement();
                        SymbolDeclaration _symbol_2 = _statement_3.getSymbol();
                        final AnyExpression expr = _symbol_2.getExpression();
                        String _print_mdef_SimpleParameter = this.print_mdef_SimpleParameter(id, expr);
                        String _plus = (statements + _print_mdef_SimpleParameter);
                        statements = _plus;
                      }
                      BlockStatement _statement_4 = st.getStatement();
                      ConditionalStatement _statement_5 = _statement_4.getStatement();
                      boolean _notEquals_5 = (!Objects.equal(_statement_5, null));
                      if (_notEquals_5) {
                      }
                    }
                  }
                }
                RandomVariableDefinitionBlock _randomVariableDefinitionBlock = b_1.getRandomVariableDefinitionBlock();
                boolean _notEquals_6 = (!Objects.equal(_randomVariableDefinitionBlock, null));
                if (_notEquals_6) {
                  RandomVariableDefinitionBlock _randomVariableDefinitionBlock_1 = b_1.getRandomVariableDefinitionBlock();
                  EList<SymbolDeclaration> _variables = _randomVariableDefinitionBlock_1.getVariables();
                  for (final SymbolDeclaration s : _variables) {
                    String _identifier = s.getIdentifier();
                    Object _get = this.eps_vars.get(_identifier);
                    boolean _notEquals_7 = (!Objects.equal(_get, null));
                    if (_notEquals_7) {
                      CharSequence _print_mdef_RandomVariable = this.print_mdef_RandomVariable(s);
                      String _plus_1 = (statements + _print_mdef_RandomVariable);
                      statements = _plus_1;
                    }
                  }
                }
                IndividualVariablesBlock _individualVariablesBlock = b_1.getIndividualVariablesBlock();
                boolean _notEquals_8 = (!Objects.equal(_individualVariablesBlock, null));
                if (_notEquals_8) {
                  IndividualVariablesBlock _individualVariablesBlock_1 = b_1.getIndividualVariablesBlock();
                  EList<BlockStatement> _statements_1 = _individualVariablesBlock_1.getStatements();
                  for (final BlockStatement s_1 : _statements_1) {
                    SymbolDeclaration _symbol_3 = s_1.getSymbol();
                    boolean _notEquals_9 = (!Objects.equal(_symbol_3, null));
                    if (_notEquals_9) {
                      SymbolDeclaration _symbol_4 = s_1.getSymbol();
                      CharSequence _print_mdef_IndividualParameter = this.print_mdef_IndividualParameter(_symbol_4);
                      String _plus_2 = (statements + _print_mdef_IndividualParameter);
                      statements = _plus_2;
                    }
                  }
                }
              }
            }
          }
          int _length = statements.length();
          boolean _greaterThan = (_length > 0);
          if (_greaterThan) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("<ParameterModel blkId=\"");
            ObjectName _identifier = o.getIdentifier();
            String _name = _identifier.getName();
            _builder.append(_name, "");
            _builder.append("\">");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append(statements, " ");
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
    
    public String print_mdef_SimpleParameter(final String symbId, final AnyExpression expr) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<SimpleParameter symbId = \"");
      _builder.append(symbId, "");
      _builder.append("\"/>");
      String param = _builder.toString();
      boolean _notEquals = (!Objects.equal(expr, null));
      if (_notEquals) {
        Expression _expression = expr.getExpression();
        boolean _notEquals_1 = (!Objects.equal(_expression, null));
        if (_notEquals_1) {
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("<SimpleParameter symbId = \"");
          _builder_1.append(symbId, "");
          _builder_1.append("\">");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          Expression _expression_1 = expr.getExpression();
          CharSequence _print_Assign = this.print_Assign(_expression_1);
          _builder_1.append(_print_Assign, "  ");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("</SimpleParameter>\t");
          _builder_1.newLine();
          param = _builder_1.toString();
        }
      }
      return param;
    }
    
    /**
     * def print_ConditionalStatement(ConditionalStatement s){
     * var symbols = new HashMap<String, ArrayList<Piece>>();
     * s.prepareConditionalSymbols(null, symbols);
     * }
     * 
     * def prepareConditionalSymbols(ConditionalStatement s, Piece parent, HashMap<String, ArrayList<Piece>> symbols){
     * if (s.ifStatement != null){
     * s.ifStatement.addConditionalSymbol(s.expression, parent, symbols);
     * }
     * if (s.elseStatement != null){
     * s.elseStatement.addConditionalSymbol(s.expression.dualExpression, parent, symbols);
     * }
     * if (s.ifBlock != null){
     * for (b:s.ifBlock.statements){
     * b.addConditionalSymbol(s.expression, parent, symbols);
     * }
     * }
     * if (s.elseBlock != null){
     * for (b:s.elseBlock.statements){
     * b.addConditionalSymbol(s.expression.dualExpression, parent, symbols);
     * }
     * }
     * }
     * 
     * def addConditionalSymbol(BlockStatement s, OrExpression condition, Piece parent, HashMap<String, ArrayList<Piece>> symbols){
     * if (s.symbol != null){
     * var pieces = symbols.get(s.symbol.identifier);
     * if (pieces == null) pieces = new ArrayList<Piece>();
     * if (s.symbol.expression != null){
     * if (s.symbol.expression.expression != null){
     * var expression = s.symbol.expression.expression;
     * var Piece piece = new Piece(parent, expression, condition);
     * pieces.add(piece);
     * }
     * }
     * //add or update the map
     * symbols.put(s.symbol.identifier, pieces);
     * }
     * if (s.statement != null){//nested conditional statement
     * var Piece newParent = new Piece(parent, null, condition);
     * s.statement.prepareConditionalSymbols(newParent, symbols);
     * }
     * }
     */
    public CharSequence print_mdef_RandomVariable(final SymbolDeclaration s) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<RandomVariable symbIdRef=\"");
      String _identifier = s.getIdentifier();
      _builder.append(_identifier, "");
      _builder.append("\">");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_VariabilityReference = this.print_VariabilityReference(s);
      _builder.append(_print_VariabilityReference, "  ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_uncert_Distribution = this.print_uncert_Distribution(s);
      _builder.append(_print_uncert_Distribution, "   ");
      _builder.newLineIfNotEmpty();
      _builder.append("</RandomVariable>");
      _builder.newLine();
      return _builder;
    }
    
    public CharSequence print_mdef_IndividualParameter(final SymbolDeclaration s) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<IndividualParameter symbIdRef=\"");
      String _identifier = s.getIdentifier();
      _builder.append(_identifier, "");
      _builder.append("\">");
      _builder.newLineIfNotEmpty();
      {
        AnyExpression _expression = s.getExpression();
        boolean _notEquals = (!Objects.equal(_expression, null));
        if (_notEquals) {
          {
            AnyExpression _expression_1 = s.getExpression();
            Expression _expression_2 = _expression_1.getExpression();
            boolean _notEquals_1 = (!Objects.equal(_expression_2, null));
            if (_notEquals_1) {
              _builder.append("\t");
              AnyExpression _expression_3 = s.getExpression();
              Expression _expression_4 = _expression_3.getExpression();
              CharSequence _print_Assign = this.print_Assign(_expression_4);
              _builder.append(_print_Assign, "    ");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      _builder.append("</IndividualParameter>");
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
          String initial = "";
          ModelObject _modelObject_1 = o.getModelObject();
          EList<ModelObjectBlock> _blocks = _modelObject_1.getBlocks();
          for (final ModelObjectBlock b : _blocks) {
            ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
            boolean _notEquals_1 = (!Objects.equal(_modelPredictionBlock, null));
            if (_notEquals_1) {
              ModelPredictionBlock _modelPredictionBlock_1 = b.getModelPredictionBlock();
              EList<ModelPredictionBlockStatement> _statements = _modelPredictionBlock_1.getStatements();
              for (final ModelPredictionBlockStatement st : _statements) {
                {
                  SymbolDeclaration ref = null;
                  BlockStatement _statement = st.getStatement();
                  boolean _notEquals_2 = (!Objects.equal(_statement, null));
                  if (_notEquals_2) {
                    BlockStatement _statement_1 = st.getStatement();
                    SymbolDeclaration _symbol = _statement_1.getSymbol();
                    boolean _notEquals_3 = (!Objects.equal(_symbol, null));
                    if (_notEquals_3) {
                      BlockStatement _statement_2 = st.getStatement();
                      SymbolDeclaration _symbol_1 = _statement_2.getSymbol();
                      ref = _symbol_1;
                    }
                  }
                  OdeBlock _odeBlock = st.getOdeBlock();
                  boolean _notEquals_4 = (!Objects.equal(_odeBlock, null));
                  if (_notEquals_4) {
                    OdeBlock _odeBlock_1 = st.getOdeBlock();
                    EList<BlockStatement> _statements_1 = _odeBlock_1.getStatements();
                    for (final BlockStatement s : _statements_1) {
                      SymbolDeclaration _symbol_2 = s.getSymbol();
                      boolean _notEquals_5 = (!Objects.equal(_symbol_2, null));
                      if (_notEquals_5) {
                        SymbolDeclaration _symbol_3 = s.getSymbol();
                        ref = _symbol_3;
                      }
                    }
                  }
                  boolean _notEquals_6 = (!Objects.equal(ref, null));
                  if (_notEquals_6) {
                    StringConcatenation _builder = new StringConcatenation();
                    CharSequence _print_ct_VariableDefinitionType = this.print_ct_VariableDefinitionType(ref);
                    _builder.append(_print_ct_VariableDefinitionType, "");
                    String _plus = (variables + _builder);
                    variables = _plus;
                    StringConcatenation _builder_1 = new StringConcatenation();
                    CharSequence _print_InitialCondition = this.print_InitialCondition(ref);
                    _builder_1.append(_print_InitialCondition, "");
                    String _plus_1 = (initial + _builder_1);
                    initial = _plus_1;
                  }
                }
              }
            }
          }
          StringConcatenation _builder = new StringConcatenation();
          {
            int _length = variables.length();
            boolean _greaterThan = (_length > 0);
            if (_greaterThan) {
              _builder.append("<StructuralModel blkId=\"");
              ObjectName _identifier = o.getIdentifier();
              String _name = _identifier.getName();
              _builder.append(_name, "");
              _builder.append("\">");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              {
                int _length_1 = variables.length();
                boolean _greaterThan_1 = (_length_1 > 0);
                if (_greaterThan_1) {
                  _builder.append(variables, "    ");
                }
              }
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              {
                int _length_2 = initial.length();
                boolean _greaterThan_2 = (_length_2 > 0);
                if (_greaterThan_2) {
                  _builder.append(initial, "  ");
                }
              }
              _builder.newLineIfNotEmpty();
              _builder.append("</StructuralModel>");
              _builder.newLine();
            }
          }
          String _plus = (model + _builder);
          model = _plus;
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
              _builder_1.append("<ObservationModel blkId=\"");
              ObjectName _identifier = o.getIdentifier();
              String _name = _identifier.getName();
              _builder_1.append(_name, "");
              _builder_1.append("\">");
              _builder_1.newLineIfNotEmpty();
              _builder_1.append("\t");
              _builder_1.append(statements, " ");
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
          Arguments _arguments = _randomList_1.getArguments();
          final String level = this.getAttribute(_arguments, "level");
          _builder.newLineIfNotEmpty();
          {
            int _length = level.length();
            boolean _greaterThan = (_length > 0);
            if (_greaterThan) {
              _builder.append("<ct:VariabilityReference>");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("<ct:SymbRef symbIdRef=\"");
              _builder.append(level, "    ");
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
              HashMap<String,String> classifiedVars = this.classifyReferences(expr);
              _builder.newLineIfNotEmpty();
              final Function2<String,String,Boolean> _function = new Function2<String,String,Boolean>() {
                  public Boolean apply(final String k, final String v) {
                    boolean _equals = v.equals("other");
                    return Boolean.valueOf(_equals);
                  }
                };
              Map<String,String> simpleVars = MapExtensions.<String, String>filter(classifiedVars, _function);
              _builder.newLineIfNotEmpty();
              {
                int _size = simpleVars.size();
                boolean _greaterThan = (_size > 0);
                if (_greaterThan) {
                  {
                    Set<String> _keySet = simpleVars.keySet();
                    for(final String ss : _keySet) {
                      final String ref = ((String) ss);
                      _builder.newLineIfNotEmpty();
                      _builder.append("<SimpleParameter symbId=\"");
                      _builder.append(ref, "");
                      _builder.append("\"/>");
                      _builder.newLineIfNotEmpty();
                    }
                  }
                }
              }
              final Function2<String,String,Boolean> _function_1 = new Function2<String,String,Boolean>() {
                  public Boolean apply(final String k, final String v) {
                    boolean _equals = v.equals("eps");
                    return Boolean.valueOf(_equals);
                  }
                };
              Map<String,String> randomVars = MapExtensions.<String, String>filter(classifiedVars, _function_1);
              _builder.newLineIfNotEmpty();
              {
                int _size_1 = randomVars.size();
                boolean _greaterThan_1 = (_size_1 > 0);
                if (_greaterThan_1) {
                  {
                    Set<String> _keySet_1 = randomVars.keySet();
                    for(final String ss_1 : _keySet_1) {
                      final SymbolDeclaration ref_1 = this.defineDistribution(((String) ss_1));
                      _builder.newLineIfNotEmpty();
                      CharSequence _print_mdef_RandomVariable = this.print_mdef_RandomVariable(ref_1);
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
      _builder.append("<General symbId=\"");
      String _identifier = s.getIdentifier();
      _builder.append(_identifier, "");
      _builder.append("\">");
      _builder.newLineIfNotEmpty();
      {
        AnyExpression _expression_4 = s.getExpression();
        Expression _expression_5 = _expression_4.getExpression();
        boolean _notEquals_2 = (!Objects.equal(_expression_5, null));
        if (_notEquals_2) {
          _builder.append("\t");
          AnyExpression _expression_6 = s.getExpression();
          Expression _expression_7 = _expression_6.getExpression();
          CharSequence _print_Assign = this.print_Assign(_expression_7);
          _builder.append(_print_Assign, "    ");
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
          CharSequence _print_Assign = this.print_Assign(expr);
          _builder.append(_print_Assign, "    ");
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
              AnyExpression init = this.getAttributeExpression(_arguments, "init");
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
                      CharSequence _print_Math_Expr = this.print_Math_Expr(_expression_4);
                      _builder.append(_print_Math_Expr, " ");
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
    
    public SymbolDeclaration findIndividualVariable(final FullyQualifiedSymbolName ref) {
      EList<MclObject> _objects = this.mcl.getObjects();
      for (final MclObject o : _objects) {
        ModelObject _modelObject = o.getModelObject();
        boolean _notEquals = (!Objects.equal(_modelObject, null));
        if (_notEquals) {
          boolean _or = false;
          ObjectName _object = ref.getObject();
          boolean _equals = Objects.equal(_object, null);
          if (_equals) {
            _or = true;
          } else {
            ObjectName _identifier = o.getIdentifier();
            String _name = _identifier.getName();
            ObjectName _object_1 = ref.getObject();
            String _name_1 = _object_1.getName();
            boolean _equalsIgnoreCase = _name.equalsIgnoreCase(_name_1);
            _or = (_equals || _equalsIgnoreCase);
          }
          if (_or) {
            ModelObject _modelObject_1 = o.getModelObject();
            EList<ModelObjectBlock> _blocks = _modelObject_1.getBlocks();
            for (final ModelObjectBlock b : _blocks) {
              IndividualVariablesBlock _individualVariablesBlock = b.getIndividualVariablesBlock();
              boolean _notEquals_1 = (!Objects.equal(_individualVariablesBlock, null));
              if (_notEquals_1) {
                IndividualVariablesBlock _individualVariablesBlock_1 = b.getIndividualVariablesBlock();
                EList<BlockStatement> _statements = _individualVariablesBlock_1.getStatements();
                for (final BlockStatement st : _statements) {
                  SymbolDeclaration _symbol = st.getSymbol();
                  boolean _notEquals_2 = (!Objects.equal(_symbol, null));
                  if (_notEquals_2) {
                    SymbolDeclaration _symbol_1 = st.getSymbol();
                    String name = _symbol_1.getIdentifier();
                    String _identifier_1 = ref.getIdentifier();
                    boolean _equalsIgnoreCase_1 = name.equalsIgnoreCase(_identifier_1);
                    if (_equalsIgnoreCase_1) {
                      return st.getSymbol();
                    }
                  }
                }
              }
            }
          }
        }
      }
      return null;
    }
    
    public SymbolDeclaration findIndividualVariable(final String fullName) {
      FullyQualifiedSymbolName _fullyQualifiedSymbolName = this.toFullyQualifiedSymbolName(fullName);
      return this.findIndividualVariable(_fullyQualifiedSymbolName);
    }
    
    public FullyQualifiedSymbolName toFullyQualifiedSymbolName(final String fullName) {
      FullyQualifiedSymbolName ref = MdlFactory.eINSTANCE.createFullyQualifiedSymbolName();
      final int _index = fullName.indexOf("$");
      String varName = fullName;
      boolean _greaterThan = (_index > 0);
      if (_greaterThan) {
        int _plus = (_index + 1);
        String _substring = fullName.substring(_plus);
        varName = _substring;
        final String objName = fullName.substring(0, _index);
        final ObjectName object = MdlFactory.eINSTANCE.createObjectName();
        object.setName(objName);
        ref.setObject(object);
      }
      ref.setIdentifier(varName);
      return ref;
    }
    
    public String defineTransformationOperator(final SymbolDeclaration s) {
      String _function = s.getFunction();
      boolean _notEquals = (!Objects.equal(_function, null));
      if (_notEquals) {
        return s.getFunction();
      } else {
        AnyExpression _expression = s.getExpression();
        boolean _notEquals_1 = (!Objects.equal(_expression, null));
        if (_notEquals_1) {
          AnyExpression _expression_1 = s.getExpression();
          org.ddmore.mdl.mdl.List _list = _expression_1.getList();
          boolean _notEquals_2 = (!Objects.equal(_list, null));
          if (_notEquals_2) {
            AnyExpression _expression_2 = s.getExpression();
            org.ddmore.mdl.mdl.List _list_1 = _expression_2.getList();
            Arguments _arguments = _list_1.getArguments();
            String transform = this.getAttribute(_arguments, "transform");
            int _length = transform.length();
            boolean _greaterThan = (_length > 0);
            if (_greaterThan) {
              return transform;
            }
          }
        }
        return "log";
      }
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
    
    public HashMap<String,String> classifyReferences(final Expression expr) {
      HashMap<String,String> classifiedVars = CollectionLiterals.<String, String>newHashMap();
      TreeIterator<EObject> iterator = expr.eAllContents();
      boolean _hasNext = iterator.hasNext();
      boolean _while = _hasNext;
      while (_while) {
        {
          EObject obj = iterator.next();
          if ((obj instanceof FullyQualifiedSymbolName)) {
            FullyQualifiedSymbolName ref = ((FullyQualifiedSymbolName) obj);
            String _str = this.toStr(ref);
            String _get = classifiedVars.get(_str);
            boolean _equals = Objects.equal(_get, null);
            if (_equals) {
              String _str_1 = this.toStr(ref);
              Object _get_1 = this.theta_vars.get(_str_1);
              boolean _notEquals = (!Objects.equal(_get_1, null));
              if (_notEquals) {
                String _str_2 = this.toStr(ref);
                classifiedVars.put(_str_2, "theta");
              } else {
                String _str_3 = this.toStr(ref);
                Object _get_2 = this.eps_vars.get(_str_3);
                boolean _notEquals_1 = (!Objects.equal(_get_2, null));
                if (_notEquals_1) {
                  String _str_4 = this.toStr(ref);
                  classifiedVars.put(_str_4, "eps");
                } else {
                  String _str_5 = this.toStr(ref);
                  Object _get_3 = this.eta_vars.get(_str_5);
                  boolean _notEquals_2 = (!Objects.equal(_get_3, null));
                  if (_notEquals_2) {
                    String _str_6 = this.toStr(ref);
                    classifiedVars.put(_str_6, "eta");
                  } else {
                    String _str_7 = this.toStr(ref);
                    classifiedVars.put(_str_7, "other");
                  }
                }
              }
            }
          }
        }
        boolean _hasNext_1 = iterator.hasNext();
        _while = _hasNext_1;
      }
      return classifiedVars;
    }
    
    public CharSequence print_Assign(final Expression expr) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<Assign>");
      _builder.newLine();
      _builder.append("\t");
      CharSequence _print_Math_Equation = this.print_Math_Equation(expr);
      _builder.append(_print_Math_Equation, " ");
      _builder.newLineIfNotEmpty();
      _builder.append("</Assign>");
      _builder.newLine();
      return _builder;
    }
    
    public CharSequence print_Math_Equation(final Expression expr) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<Equation xmlns=\"");
      _builder.append(this.xmlns_math, "");
      _builder.append("\" writtenVersion=\"");
      _builder.append(this.writtenVersion, "");
      _builder.append("\">");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_Math_Expr = this.print_Math_Expr(expr);
      _builder.append(_print_Math_Expr, " ");
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
          CharSequence _print_Math_LogicOpPiece = this.print_Math_LogicOpPiece(_expression1_1, _expression);
          _builder.append(_print_Math_LogicOpPiece, " ");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          Expression _expression2 = expr.getExpression2();
          OrExpression _expression_1 = expr.getExpression();
          OrExpression _dualExpression = this.dualExpression(_expression_1);
          CharSequence _print_Math_LogicOpPiece_1 = this.print_Math_LogicOpPiece(_expression2, _dualExpression);
          _builder.append(_print_Math_LogicOpPiece_1, "   ");
          _builder.newLineIfNotEmpty();
          _builder.append("</Piecewise>");
          _builder.newLine();
        } else {
          OrExpression _expression_2 = expr.getExpression();
          String _print_Math_LogicOr = this.print_Math_LogicOr(_expression_2, 0);
          _builder.append(_print_Math_LogicOr, "");
          _builder.newLineIfNotEmpty();
        }
      }
      return _builder;
    }
    
    public CharSequence print_Math_LogicOpPiece(final Expression expr, final OrExpression condition) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<Piece>");
      _builder.newLine();
      _builder.append("\t");
      Object _print_Math_Expr = this.print_Math_Expr(expr);
      _builder.append(_print_Math_Expr, " ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("<Condition>");
      _builder.newLine();
      _builder.append("\t\t");
      String _print_Math_LogicOr = this.print_Math_LogicOr(condition, 0);
      _builder.append(_print_Math_LogicOr, "      ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("</Condition>");
      _builder.newLine();
      _builder.append("</Piece>");
      _builder.newLine();
      return _builder;
    }
    
    public String print_Math_LogicOr(final OrExpression expr, final int startIndex) {
      EList<AndExpression> _expression = expr.getExpression();
      boolean _notEquals = (!Objects.equal(_expression, null));
      if (_notEquals) {
        EList<String> _operator = expr.getOperator();
        int _size = _operator.size();
        boolean _lessThan = (startIndex < _size);
        if (_lessThan) {
          EList<AndExpression> _expression_1 = expr.getExpression();
          AndExpression _get = _expression_1.get(startIndex);
          final String first = this.print_Math_LogicAnd(_get, 0);
          int _plus = (startIndex + 1);
          final Object second = this.print_Math_LogicOr(expr, _plus);
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("<LogicBinop op=\"or\">");
          _builder.newLine();
          _builder.append("\t");
          _builder.append(first, "    ");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append(second, "   ");
          _builder.newLineIfNotEmpty();
          _builder.append("</LogicBinop>");
          _builder.newLine();
          return _builder.toString();
        } else {
          StringConcatenation _builder_1 = new StringConcatenation();
          EList<AndExpression> _expression_2 = expr.getExpression();
          AndExpression _get_1 = _expression_2.get(startIndex);
          String _print_Math_LogicAnd = this.print_Math_LogicAnd(_get_1, 0);
          _builder_1.append(_print_Math_LogicAnd, "");
          return _builder_1.toString();
        }
      }
      StringConcatenation _builder_2 = new StringConcatenation();
      return _builder_2.toString();
    }
    
    public String print_Math_LogicAnd(final AndExpression expr, final int startIndex) {
      EList<LogicalExpression> _expression = expr.getExpression();
      boolean _notEquals = (!Objects.equal(_expression, null));
      if (_notEquals) {
        EList<String> _operator = expr.getOperator();
        int _size = _operator.size();
        boolean _lessThan = (startIndex < _size);
        if (_lessThan) {
          EList<LogicalExpression> _expression_1 = expr.getExpression();
          LogicalExpression _get = _expression_1.get(startIndex);
          final String first = this.print_Math_LogicOp(_get, 0);
          int _plus = (startIndex + 1);
          final Object second = this.print_Math_LogicAnd(expr, _plus);
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("<LogicBinop op=\"and\">");
          _builder.newLine();
          _builder.append("\t");
          _builder.append(first, "    ");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append(second, "   ");
          _builder.newLineIfNotEmpty();
          _builder.append("</LogicBinop>");
          _builder.newLine();
          return _builder.toString();
        } else {
          StringConcatenation _builder_1 = new StringConcatenation();
          EList<LogicalExpression> _expression_2 = expr.getExpression();
          LogicalExpression _get_1 = _expression_2.get(startIndex);
          String _print_Math_LogicOp = this.print_Math_LogicOp(_get_1, 0);
          _builder_1.append(_print_Math_LogicOp, "");
          return _builder_1.toString();
        }
      }
      StringConcatenation _builder_2 = new StringConcatenation();
      return _builder_2.toString();
    }
    
    public String print_Math_LogicOp(final LogicalExpression expr, final int startIndex) {
      EList<AdditiveExpression> _expression = expr.getExpression();
      boolean _notEquals = (!Objects.equal(_expression, null));
      if (_notEquals) {
        EList<String> _operator = expr.getOperator();
        int _size = _operator.size();
        boolean _lessThan = (startIndex < _size);
        if (_lessThan) {
          EList<String> _operator_1 = expr.getOperator();
          String _get = _operator_1.get(startIndex);
          final String operator = this.convertOperator(_get);
          EList<AdditiveExpression> _expression_1 = expr.getExpression();
          AdditiveExpression _get_1 = _expression_1.get(startIndex);
          final String first = this.print_Math_AddOp(_get_1, 0);
          int _plus = (startIndex + 1);
          final Object second = this.print_Math_LogicOp(expr, _plus);
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("<LogicBinop op=\"");
          _builder.append(operator, "");
          _builder.append("\">");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append(first, "    ");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append(second, "   ");
          _builder.newLineIfNotEmpty();
          _builder.append("</LogicBinop>");
          _builder.newLine();
          return _builder.toString();
        } else {
          StringConcatenation _builder_1 = new StringConcatenation();
          EList<AdditiveExpression> _expression_2 = expr.getExpression();
          AdditiveExpression _get_2 = _expression_2.get(startIndex);
          String _print_Math_AddOp = this.print_Math_AddOp(_get_2, 0);
          _builder_1.append(_print_Math_AddOp, "");
          return _builder_1.toString();
        }
      }
      StringConcatenation _builder_2 = new StringConcatenation();
      return _builder_2.toString();
    }
    
    public String print_Math_AddOp(final AdditiveExpression expr, final int startIndex) {
      EList<MultiplicativeExpression> _expression = expr.getExpression();
      boolean _notEquals = (!Objects.equal(_expression, null));
      if (_notEquals) {
        EList<String> _operator = expr.getOperator();
        int _size = _operator.size();
        boolean _lessThan = (startIndex < _size);
        if (_lessThan) {
          EList<String> _operator_1 = expr.getOperator();
          String _get = _operator_1.get(startIndex);
          final String operator = this.convertOperator(_get);
          EList<MultiplicativeExpression> _expression_1 = expr.getExpression();
          MultiplicativeExpression _get_1 = _expression_1.get(startIndex);
          final String first = this.print_Math_MultOp(_get_1, 0);
          int _plus = (startIndex + 1);
          final Object second = this.print_Math_AddOp(expr, _plus);
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("<Binop op=\"");
          _builder.append(operator, "");
          _builder.append("\">");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append(first, "    ");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append(second, "   ");
          _builder.newLineIfNotEmpty();
          _builder.append("</Binop>");
          _builder.newLine();
          return _builder.toString();
        } else {
          StringConcatenation _builder_1 = new StringConcatenation();
          EList<MultiplicativeExpression> _expression_2 = expr.getExpression();
          MultiplicativeExpression _get_2 = _expression_2.get(startIndex);
          String _print_Math_MultOp = this.print_Math_MultOp(_get_2, 0);
          _builder_1.append(_print_Math_MultOp, "");
          return _builder_1.toString();
        }
      } else {
        EList<String> _string = expr.getString();
        boolean _notEquals_1 = (!Objects.equal(_string, null));
        if (_notEquals_1) {
          String res = "";
          EList<String> _string_1 = expr.getString();
          for (final String s : _string_1) {
            String _plus_1 = (res + s);
            res = _plus_1;
          }
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append("<ct:String>");
          _builder_2.append(res, "");
          _builder_2.append("</ct:String>");
          return _builder_2.toString();
        }
      }
      StringConcatenation _builder_3 = new StringConcatenation();
      return _builder_3.toString();
    }
    
    public String print_Math_MultOp(final MultiplicativeExpression expr, final int startIndex) {
      EList<PowerExpression> _expression = expr.getExpression();
      boolean _notEquals = (!Objects.equal(_expression, null));
      if (_notEquals) {
        EList<String> _operator = expr.getOperator();
        int _size = _operator.size();
        boolean _lessThan = (startIndex < _size);
        if (_lessThan) {
          EList<String> _operator_1 = expr.getOperator();
          String _get = _operator_1.get(startIndex);
          final String operator = this.convertOperator(_get);
          EList<PowerExpression> _expression_1 = expr.getExpression();
          PowerExpression _get_1 = _expression_1.get(startIndex);
          final String first = this.print_Math_PowerOp(_get_1, 0);
          int _plus = (startIndex + 1);
          final Object second = this.print_Math_MultOp(expr, _plus);
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("<Binop op=\"");
          _builder.append(operator, "");
          _builder.append("\">");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append(first, "    ");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append(second, "   ");
          _builder.newLineIfNotEmpty();
          _builder.append("</Binop>");
          _builder.newLine();
          return _builder.toString();
        } else {
          StringConcatenation _builder_1 = new StringConcatenation();
          EList<PowerExpression> _expression_2 = expr.getExpression();
          PowerExpression _get_2 = _expression_2.get(startIndex);
          String _print_Math_PowerOp = this.print_Math_PowerOp(_get_2, 0);
          _builder_1.append(_print_Math_PowerOp, "");
          return _builder_1.toString();
        }
      }
      StringConcatenation _builder_2 = new StringConcatenation();
      return _builder_2.toString();
    }
    
    public String print_Math_PowerOp(final PowerExpression expr, final int startIndex) {
      EList<UnaryExpression> _expression = expr.getExpression();
      boolean _notEquals = (!Objects.equal(_expression, null));
      if (_notEquals) {
        EList<String> _operator = expr.getOperator();
        int _size = _operator.size();
        boolean _lessThan = (startIndex < _size);
        if (_lessThan) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("<Binop op=\"");
          EList<String> _operator_1 = expr.getOperator();
          String _get = _operator_1.get(startIndex);
          String _convertOperator = this.convertOperator(_get);
          _builder.append(_convertOperator, "");
          _builder.append("\">");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          EList<UnaryExpression> _expression_1 = expr.getExpression();
          UnaryExpression _get_1 = _expression_1.get(startIndex);
          CharSequence _print_Math_UniOp = this.print_Math_UniOp(_get_1);
          _builder.append(_print_Math_UniOp, "    ");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          int _plus = (startIndex + 1);
          Object _print_Math_PowerOp = this.print_Math_PowerOp(expr, _plus);
          _builder.append(_print_Math_PowerOp, "  ");
          _builder.newLineIfNotEmpty();
          _builder.append("</Binop>");
          _builder.newLine();
          return _builder.toString();
        } else {
          StringConcatenation _builder_1 = new StringConcatenation();
          EList<UnaryExpression> _expression_2 = expr.getExpression();
          UnaryExpression _get_2 = _expression_2.get(startIndex);
          CharSequence _print_Math_UniOp_1 = this.print_Math_UniOp(_get_2);
          _builder_1.append(_print_Math_UniOp_1, "");
          return _builder_1.toString();
        }
      }
      StringConcatenation _builder_2 = new StringConcatenation();
      return _builder_2.toString();
    }
    
    public CharSequence print_Math_BinOp(final String operator, final Expression expr1, final Expression expr2) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<Binop op=\"");
      _builder.append(operator, "");
      _builder.append("\">");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_Math_Equation = this.print_Math_Equation(expr1);
      _builder.append(_print_Math_Equation, " ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_Math_Equation_1 = this.print_Math_Equation(expr2);
      _builder.append(_print_Math_Equation_1, "   ");
      _builder.newLineIfNotEmpty();
      _builder.append("</Binop>");
      _builder.newLine();
      return _builder;
    }
    
    public CharSequence print_Math_BinOp(final String operator, final UnaryExpression expr1, final UnaryExpression expr2) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<Binop op=\"");
      _builder.append(operator, "");
      _builder.append("\">");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_Math_UniOp = this.print_Math_UniOp(expr1);
      _builder.append(_print_Math_UniOp, "    ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_Math_UniOp_1 = this.print_Math_UniOp(expr2);
      _builder.append(_print_Math_UniOp_1, "  ");
      _builder.newLineIfNotEmpty();
      _builder.append("</Binop>");
      _builder.newLine();
      return _builder;
    }
    
    public CharSequence print_Math_UniOp(final UnaryExpression expr) {
      StringConcatenation _builder = new StringConcatenation();
      {
        String _operator = expr.getOperator();
        boolean _notEquals = (!Objects.equal(_operator, null));
        if (_notEquals) {
          _builder.append("<Uniop op=\"");
          String _operator_1 = expr.getOperator();
          _builder.append(_operator_1, "");
          _builder.append("\">");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          UnaryExpression _expression = expr.getExpression();
          Object _print_Math_UniOp = this.print_Math_UniOp(_expression);
          _builder.append(_print_Math_UniOp, "    ");
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
              Object _print_Math_Expr = this.print_Math_Expr(_expression_1);
              _builder.append(_print_Math_Expr, "");
              _builder.newLineIfNotEmpty();
            } else {
              {
                Primary _primary = expr.getPrimary();
                boolean _notEquals_2 = (!Objects.equal(_primary, null));
                if (_notEquals_2) {
                  {
                    Primary _primary_1 = expr.getPrimary();
                    FunctionCall _functionCall = _primary_1.getFunctionCall();
                    boolean _notEquals_3 = (!Objects.equal(_functionCall, null));
                    if (_notEquals_3) {
                      Primary _primary_2 = expr.getPrimary();
                      FunctionCall _functionCall_1 = _primary_2.getFunctionCall();
                      CharSequence _print_Math_FunctionCall = this.print_Math_FunctionCall(_functionCall_1);
                      _builder.append(_print_Math_FunctionCall, "");
                      _builder.newLineIfNotEmpty();
                    }
                  }
                  {
                    Primary _primary_3 = expr.getPrimary();
                    String _number = _primary_3.getNumber();
                    boolean _notEquals_4 = (!Objects.equal(_number, null));
                    if (_notEquals_4) {
                      Primary _primary_4 = expr.getPrimary();
                      String _number_1 = _primary_4.getNumber();
                      String _print_ct_Value = this.print_ct_Value(_number_1);
                      _builder.append(_print_ct_Value, "");
                      _builder.newLineIfNotEmpty();
                    }
                  }
                  {
                    Primary _primary_5 = expr.getPrimary();
                    FullyQualifiedSymbolName _symbol = _primary_5.getSymbol();
                    boolean _notEquals_5 = (!Objects.equal(_symbol, null));
                    if (_notEquals_5) {
                      Primary _primary_6 = expr.getPrimary();
                      FullyQualifiedSymbolName _symbol_1 = _primary_6.getSymbol();
                      CharSequence _print_ct_SymbolRef = this.print_ct_SymbolRef(_symbol_1);
                      _builder.append(_print_ct_SymbolRef, "");
                      _builder.newLineIfNotEmpty();
                    }
                  }
                  {
                    Primary _primary_7 = expr.getPrimary();
                    FullyQualifiedArgumentName _attribute = _primary_7.getAttribute();
                    boolean _notEquals_6 = (!Objects.equal(_attribute, null));
                    if (_notEquals_6) {
                      Primary _primary_8 = expr.getPrimary();
                      FullyQualifiedArgumentName _attribute_1 = _primary_8.getAttribute();
                      CharSequence _print_ct_SymbolRef_1 = this.print_ct_SymbolRef(_attribute_1);
                      _builder.append(_print_ct_SymbolRef_1, "");
                      _builder.newLineIfNotEmpty();
                    }
                  }
                  {
                    Primary _primary_9 = expr.getPrimary();
                    Vector _vector = _primary_9.getVector();
                    boolean _notEquals_7 = (!Objects.equal(_vector, null));
                    if (_notEquals_7) {
                      _builder.append("<!-- TODO: print vector -->");
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
    
    public CharSequence print_ct_Vector(final Vector vector) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<ct:Vector>");
      _builder.newLine();
      {
        EList<Expression> _values = vector.getValues();
        for(final Expression v : _values) {
          _builder.append("\t");
          CharSequence _print_Math_Expr = this.print_Math_Expr(v);
          _builder.append(_print_Math_Expr, " ");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("</ct:Vector>");
      _builder.newLine();
      return _builder;
    }
    
    public CharSequence print_ct_SymbolRef(final FullyQualifiedSymbolName ref) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<ct:SymbRef");
      {
        ObjectName _object = ref.getObject();
        boolean _notEquals = (!Objects.equal(_object, null));
        if (_notEquals) {
          _builder.append(" blkIdRef=\"");
          ObjectName _object_1 = ref.getObject();
          _builder.append(_object_1, "");
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
      _builder.append("<ct:SymbRef ");
      {
        FullyQualifiedSymbolName _parent = ref.getParent();
        boolean _notEquals = (!Objects.equal(_parent, null));
        if (_notEquals) {
          {
            FullyQualifiedSymbolName _parent_1 = ref.getParent();
            ObjectName _object = _parent_1.getObject();
            boolean _notEquals_1 = (!Objects.equal(_object, null));
            if (_notEquals_1) {
              _builder.append("blkIdRef=\"");
              FullyQualifiedSymbolName _parent_2 = ref.getParent();
              ObjectName _object_1 = _parent_2.getObject();
              _builder.append(_object_1, "");
              _builder.append("\"");
            }
          }
        }
      }
      _builder.append(" ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("symbIdRef=\"");
      FullyQualifiedSymbolName _parent_3 = ref.getParent();
      String _identifier = _parent_3.getIdentifier();
      _builder.append(_identifier, "  ");
      _builder.append(".");
      String _str_1 = this.toStr(ref);
      _builder.append(_str_1, "   ");
      _builder.append("\"/>");
      _builder.newLineIfNotEmpty();
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
    
    public CharSequence print_uncert_Distribution(final SymbolDeclaration s) {
      StringConcatenation _builder = new StringConcatenation();
      {
        RandomList _randomList = s.getRandomList();
        boolean _notEquals = (!Objects.equal(_randomList, null));
        if (_notEquals) {
          RandomList _randomList_1 = s.getRandomList();
          Arguments args = _randomList_1.getArguments();
          _builder.newLineIfNotEmpty();
          {
            boolean _notEquals_1 = (!Objects.equal(args, null));
            if (_notEquals_1) {
              String distrType = this.getAttribute(args, "type");
              _builder.newLineIfNotEmpty();
              AnyExpression mean = this.getAttributeExpression(args, "mean");
              _builder.newLineIfNotEmpty();
              AnyExpression variance = this.getAttributeExpression(args, "variance");
              _builder.append("\t\t\t");
              _builder.newLineIfNotEmpty();
              {
                boolean _and = false;
                int _length = distrType.length();
                boolean _greaterThan = (_length > 0);
                if (!_greaterThan) {
                  _and = false;
                } else {
                  boolean _equalsIgnoreCase = distrType.equalsIgnoreCase("Normal");
                  _and = (_greaterThan && _equalsIgnoreCase);
                }
                if (_and) {
                  _builder.append("<NormalDistribution xmlns=\"");
                  _builder.append(this.xmlns_uncert, "");
                  _builder.append("\" writtenVersion = \"");
                  _builder.append(this.writtenVersion, "");
                  _builder.append("\">");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("<Mean>");
                  _builder.newLine();
                  _builder.append("\t\t");
                  {
                    boolean _notEquals_2 = (!Objects.equal(mean, null));
                    if (_notEquals_2) {
                      CharSequence _print_Math_Expr = this.print_Math_Expr(mean);
                      _builder.append(_print_Math_Expr, "     ");
                    }
                  }
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("</Mean>");
                  _builder.newLine();
                  _builder.append("\t");
                  _builder.append("<StdDev>");
                  _builder.newLine();
                  _builder.append("\t\t");
                  {
                    boolean _notEquals_3 = (!Objects.equal(variance, null));
                    if (_notEquals_3) {
                      CharSequence _print_Math_Expr_1 = this.print_Math_Expr(variance);
                      _builder.append(_print_Math_Expr_1, "       ");
                    }
                  }
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("</StdDev>");
                  _builder.newLine();
                  _builder.append("</NormalDistribution>");
                  _builder.newLine();
                }
              }
            }
          }
        }
      }
      return _builder;
    }
    
    public CharSequence print_design_TrialDesign() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<TrialDesign>");
      _builder.newLine();
      _builder.append("\t");
      CharSequence _print_design_Structure = this.print_design_Structure();
      _builder.append(_print_design_Structure, "  ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_design_Population = this.print_design_Population();
      _builder.append(_print_design_Population, " ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_design_IndividualDosing = this.print_design_IndividualDosing();
      _builder.append(_print_design_IndividualDosing, "   ");
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
      _builder.append(_print_design_Epoch, "  ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_design_Arms = this.print_design_Arms();
      _builder.append(_print_design_Arms, "   ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_design_Cells = this.print_design_Cells();
      _builder.append(_print_design_Cells, "  ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_design_Segments = this.print_design_Segments();
      _builder.append(_print_design_Segments, "   ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_design_Activities = this.print_design_Activities();
      _builder.append(_print_design_Activities, " ");
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
      _builder.append(_print_design_Start, "  ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_design_End = this.print_design_End(start);
      _builder.append(_print_design_End, "    ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("<Order>");
      _builder.append(order, "    ");
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
      _builder.append(value, "    ");
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
      _builder.append(value, "    ");
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
      _builder.append(epochRef, " ");
      _builder.append("\"/>");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("<ArmRef oidRef=\"");
      _builder.append(armRef, "   ");
      _builder.append("\"/>");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("<SegmentRef oidRef=\"");
      _builder.append(segmentRef, "   ");
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
          _builder.append(activityRef, "  ");
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
      _builder.append(_print_design_Bolus, "  ");
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
      _builder.append(_print_design_DoseAmount, " ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_design_DosingTimes = this.print_design_DosingTimes(null);
      _builder.append(_print_design_DosingTimes, "    ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_design_SteadyState = this.print_design_SteadyState(null, null);
      _builder.append(_print_design_SteadyState, "    ");
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
      _builder.append(_print_Assign, "    ");
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
              CharSequence _print_Assign = this.print_Assign(_expression_3);
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
          CharSequence _print_ct_SymbolRef = this.print_ct_SymbolRef(_identifier);
          _builder.append(_print_ct_SymbolRef, "");
          _builder.newLineIfNotEmpty();
          org.ddmore.mdl.mdl.List _list = s.getList();
          Arguments _arguments = _list.getArguments();
          final AnyExpression value = this.getAttributeExpression(_arguments, "value");
          _builder.newLineIfNotEmpty();
          {
            boolean _notEquals_1 = (!Objects.equal(value, null));
            if (_notEquals_1) {
              _builder.append("<ct:Assign>");
              _builder.newLine();
              _builder.append("\t");
              CharSequence _print_Math_Expr = this.print_Math_Expr(value);
              _builder.append(_print_Math_Expr, " ");
              _builder.newLineIfNotEmpty();
              _builder.append("</ct:Assign>");
              _builder.newLine();
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
      _builder.append(_print_design_EndTime, "    ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_design_Interval = this.print_design_Interval(interval);
      _builder.append(_print_design_Interval, "   ");
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
      _builder.append(_print_Assign, "    ");
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
      _builder.append(_print_Assign, "    ");
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
      _builder.append(_print_design_IndividualTemplate, " ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_design_DataSet = this.print_design_DataSet();
      _builder.append(_print_design_DataSet, "    ");
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
                  org.ddmore.mdl.mdl.List _list = _expression_1.getList();
                  boolean _notEquals_3 = (!Objects.equal(_list, null));
                  if (_notEquals_3) {
                    AnyExpression _expression_2 = s.getExpression();
                    org.ddmore.mdl.mdl.List _list_1 = _expression_2.getList();
                    Arguments _arguments = _list_1.getArguments();
                    String use = this.getAttribute(_arguments, "use");
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
      _builder.append(mappings, " ");
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
      _builder.append(this.xmlns_dataSet, "   ");
      _builder.append("\" columnIdRef=\"");
      _builder.append(ref, "  ");
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
      _builder.append(_print_msteps_EstimationStep, " ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_msteps_SimulationStep = this.print_msteps_SimulationStep();
      _builder.append(_print_msteps_SimulationStep, " ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      CharSequence _print_msteps_StepDependencies = this.print_msteps_StepDependencies();
      _builder.append(_print_msteps_StepDependencies, "   ");
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
      _builder.append(_print_msteps_ObjectiveDataSet, "   ");
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
      _builder.append(_print_msteps_VariableAssignments, "    ");
      _builder.append("\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      Object _print_msteps_Observations = this.print_msteps_Observations();
      _builder.append(_print_msteps_Observations, "   ");
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
      _builder.append(_print_Assign, "    ");
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
      CharSequence _print_msteps_Sequence = this.print_msteps_Sequence("", "", "");
      _builder.append(_print_msteps_Sequence, "       ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("</Timepoints>");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("<Continuous>");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("<ct:SymbRef symbIdRef=\"");
      _builder.append(ref, "      ");
      _builder.append("\"");
      {
        int _length = blockID.length();
        boolean _greaterThan = (_length > 0);
        if (_greaterThan) {
          _builder.append(" blkIdRef=\"");
          _builder.append(blockID, "      ");
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
    
    public CharSequence print_msteps_Sequence(final String begin, final String stepSize, final String end) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<ct:Sequence>");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("<ct:Begin>");
      _builder.newLine();
      _builder.append("\t\t");
      String _print_ct_Value = this.print_ct_Value(begin);
      _builder.append(_print_ct_Value, "      ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("</ct:Begin>");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("<ct:StepSize>");
      _builder.newLine();
      _builder.append("\t\t");
      String _print_ct_Value_1 = this.print_ct_Value(stepSize);
      _builder.append(_print_ct_Value_1, "        ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("</ct:StepSize>");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("<ct:End>");
      _builder.newLine();
      _builder.append("\t\t");
      String _print_ct_Value_2 = this.print_ct_Value(end);
      _builder.append(_print_ct_Value_2, "        ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("</ct:End>");
      _builder.newLine();
      _builder.append("</ct:Sequence>");
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
      _builder.append(ref, "  ");
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
              _builder.append(_print_msteps_DataSet, "    ");
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
        String fileName = this.getDataSource(this.mcl);
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
        String _print_ct_Value = this.print_ct_Value(_next);
        String _plus = (row + _print_ct_Value);
        row = _plus;
      }
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<Row>");
      _builder.newLine();
      _builder.append("\t");
      _builder.append(row, "  ");
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
          _builder.append(definition, "       ");
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
          _builder.append(table, "        ");
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
    
    public CharSequence print_ct_AnnotationType(final String text) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<Description>");
      _builder.append(text, "");
      _builder.append("</Description>");
      _builder.newLineIfNotEmpty();
      return _builder;
    }
    
    public CharSequence print_ct_VariableDefinitionType(final SymbolDeclaration v) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<Variable symbId = \"");
      String _identifier = v.getIdentifier();
      _builder.append(_identifier, "");
      _builder.append("\"");
      {
        AnyExpression _expression = v.getExpression();
        OdeList _odeList = _expression.getOdeList();
        boolean _notEquals = (!Objects.equal(_odeList, null));
        if (_notEquals) {
          _builder.append(" symbolType=\"derivative\" independentVar=\"t\"");
        }
      }
      _builder.append(">");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      String _identifier_1 = v.getIdentifier();
      CharSequence _print_ct_AnnotationType = this.print_ct_AnnotationType(_identifier_1);
      _builder.append(_print_ct_AnnotationType, " ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      AnyExpression _expression_1 = v.getExpression();
      CharSequence _print_Math_Expr = this.print_Math_Expr(_expression_1);
      _builder.append(_print_Math_Expr, " ");
      _builder.newLineIfNotEmpty();
      _builder.append("</Variable>");
      _builder.newLine();
      return _builder;
    }
    
    public CharSequence print_Math_Expr(final AnyExpression e) {
      StringConcatenation _builder = new StringConcatenation();
      {
        Expression _expression = e.getExpression();
        boolean _notEquals = (!Objects.equal(_expression, null));
        if (_notEquals) {
          Expression _expression_1 = e.getExpression();
          Object _print_Math_Equation = this.print_Math_Equation(_expression_1);
          _builder.append(_print_Math_Equation, "");
          _builder.newLineIfNotEmpty();
        }
      }
      {
        org.ddmore.mdl.mdl.List _list = e.getList();
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
          Object _print_Math_Expr = this.print_Math_Expr(deriv);
          _builder.append(_print_Math_Expr, "");
        }
      }
      _builder.newLineIfNotEmpty();
      return _builder;
    }
    
    public CharSequence print_Categorical(final org.ddmore.mdl.mdl.List categories) {
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
          _builder.append(_identifier, "  ");
          _builder.newLineIfNotEmpty();
          _builder.append("</Category>");
          _builder.newLine();
        }
      }
      _builder.append("</Categorical>");
      _builder.newLine();
      return _builder;
    }
    
    public CharSequence print_list(final AnyExpression e) {
      CharSequence _xifexpression = null;
      org.ddmore.mdl.mdl.List _list = e.getList();
      boolean _notEquals = (!Objects.equal(_list, null));
      if (_notEquals) {
        CharSequence _xblockexpression = null;
        {
          org.ddmore.mdl.mdl.List _list_1 = e.getList();
          final Arguments args = _list_1.getArguments();
          final String type = this.getAttribute(args, "type");
          final AnyExpression define = this.getAttributeExpression(args, "define");
          CharSequence _xifexpression_1 = null;
          boolean _and = false;
          boolean _equals = type.equals("categorical");
          if (!_equals) {
            _and = false;
          } else {
            org.ddmore.mdl.mdl.List _list_2 = define.getList();
            boolean _notEquals_1 = (!Objects.equal(_list_2, null));
            _and = (_equals && _notEquals_1);
          }
          if (_and) {
            org.ddmore.mdl.mdl.List _list_3 = define.getList();
            CharSequence _print_Categorical = this.print_Categorical(_list_3);
            _xifexpression_1 = _print_Categorical;
          }
          _xblockexpression = (_xifexpression_1);
        }
        _xifexpression = _xblockexpression;
      }
      return _xifexpression;
    }
    
    public CharSequence print_Math_FunctionCall(final FunctionCall call) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<math:FunctionCall>");
      _builder.newLine();
      _builder.append("\t");
      FullyQualifiedSymbolName _identifier = call.getIdentifier();
      CharSequence _print_ct_SymbolRef = this.print_ct_SymbolRef(_identifier);
      _builder.append(_print_ct_SymbolRef, "  ");
      _builder.newLineIfNotEmpty();
      {
        Arguments _arguments = call.getArguments();
        EList<Argument> _arguments_1 = _arguments.getArguments();
        for(final Argument arg : _arguments_1) {
          _builder.append("\t");
          CharSequence _print_Math_FunctionArgument = this.print_Math_FunctionArgument(arg);
          _builder.append(_print_Math_FunctionArgument, " ");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("</math:FunctionCall>");
      _builder.newLine();
      return _builder;
    }
    
    public CharSequence print_Math_FunctionArgument(final Argument arg) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<FunctionArgument ");
      {
        String _identifier = arg.getIdentifier();
        boolean _notEquals = (!Objects.equal(_identifier, null));
        if (_notEquals) {
          _builder.append("symbId=\"");
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
      _builder.append(_print_Math_Expr, " ");
      _builder.newLineIfNotEmpty();
      _builder.append("</FunctionArgument>");
      _builder.newLine();
      return _builder;
    }
    
    public String getVarType(final FullyQualifiedSymbolName ref) {
      return this.TYPE_INT;
    }
    
    public String getVarType(final SymbolDeclaration s) {
      return this.TYPE_INT;
    }
    
    public OrExpression dualExpression(final OrExpression expr) {
      AndExpression dualAnd = MdlFactory.eINSTANCE.createAndExpression();
      EList<AndExpression> _expression = expr.getExpression();
      Iterator<AndExpression> iterator = _expression.iterator();
      boolean _hasNext = iterator.hasNext();
      boolean _while = _hasNext;
      while (_while) {
        {
          AndExpression andExpression = iterator.next();
          EList<LogicalExpression> _expression_1 = andExpression.getExpression();
          for (final LogicalExpression andAtom : _expression_1) {
            {
              LogicalExpression logicalExpr = MdlFactory.eINSTANCE.createLogicalExpression();
              EList<AdditiveExpression> _expression_2 = andAtom.getExpression();
              boolean _notEquals = (!Objects.equal(_expression_2, null));
              if (_notEquals) {
                EList<AdditiveExpression> _expression_3 = andAtom.getExpression();
                Iterator<AdditiveExpression> iterator1 = _expression_3.iterator();
                EList<String> _operator = andAtom.getOperator();
                Iterator<String> operatorIterator1 = _operator.iterator();
                boolean _hasNext_1 = iterator1.hasNext();
                if (_hasNext_1) {
                  EList<AdditiveExpression> _expression_4 = logicalExpr.getExpression();
                  AdditiveExpression _next = iterator1.next();
                  _expression_4.add(_next);
                }
                boolean _and = false;
                boolean _hasNext_2 = iterator1.hasNext();
                if (!_hasNext_2) {
                  _and = false;
                } else {
                  boolean _hasNext_3 = operatorIterator1.hasNext();
                  _and = (_hasNext_2 && _hasNext_3);
                }
                boolean _while_1 = _and;
                while (_while_1) {
                  {
                    EList<String> _operator_1 = logicalExpr.getOperator();
                    String _next_1 = operatorIterator1.next();
                    String _dualOperator = this.getDualOperator(_next_1);
                    _operator_1.add(_dualOperator);
                    EList<AdditiveExpression> _expression_5 = logicalExpr.getExpression();
                    AdditiveExpression _next_2 = iterator1.next();
                    _expression_5.add(_next_2);
                  }
                  boolean _and_1 = false;
                  boolean _hasNext_4 = iterator1.hasNext();
                  if (!_hasNext_4) {
                    _and_1 = false;
                  } else {
                    boolean _hasNext_5 = operatorIterator1.hasNext();
                    _and_1 = (_hasNext_4 && _hasNext_5);
                  }
                  _while_1 = _and_1;
                }
              } else {
                String _boolean = andAtom.getBoolean();
                boolean _notEquals_1 = (!Objects.equal(_boolean, null));
                if (_notEquals_1) {
                  String _boolean_1 = andAtom.getBoolean();
                  logicalExpr.setBoolean(_boolean_1);
                  String _negation = andAtom.getNegation();
                  boolean _equals = Objects.equal(_negation, null);
                  if (_equals) {
                    logicalExpr.setNegation("!");
                  }
                }
              }
              EList<LogicalExpression> _expression_5 = dualAnd.getExpression();
              _expression_5.add(logicalExpr);
            }
          }
        }
        boolean _hasNext_1 = iterator.hasNext();
        _while = _hasNext_1;
      }
      OrExpression dualOr = MdlFactory.eINSTANCE.createOrExpression();
      EList<AndExpression> _expression_1 = dualOr.getExpression();
      _expression_1.add(dualAnd);
      return dualOr;
    }
    
    public String getDualOperator(final String operator) {
      String _switchResult = null;
      boolean _matched = false;
      if (!_matched) {
        if (Objects.equal(operator,"<")) {
          _matched=true;
          _switchResult = ">";
        }
      }
      if (!_matched) {
        if (Objects.equal(operator,">")) {
          _matched=true;
          _switchResult = "<";
        }
      }
      if (!_matched) {
        if (Objects.equal(operator,"<=")) {
          _matched=true;
          _switchResult = ">=";
        }
      }
      if (!_matched) {
        if (Objects.equal(operator,">=")) {
          _matched=true;
          _switchResult = "<=";
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
          _switchResult = "le";
        }
      }
      if (!_matched) {
        if (Objects.equal(operator,">=")) {
          _matched=true;
          _switchResult = "ge";
        }
      }
      if (!_matched) {
        if (Objects.equal(operator,"==")) {
          _matched=true;
          _switchResult = "ne";
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
    
    public String getIndependentVariable(final Mcl m) {
      EList<MclObject> _objects = m.getObjects();
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
                  org.ddmore.mdl.mdl.List _list = _expression_1.getList();
                  boolean _notEquals_3 = (!Objects.equal(_list, null));
                  if (_notEquals_3) {
                    AnyExpression _expression_2 = s.getExpression();
                    org.ddmore.mdl.mdl.List _list_1 = _expression_2.getList();
                    Arguments _arguments = _list_1.getArguments();
                    String use = this.getAttribute(_arguments, "use");
                    boolean _equalsIgnoreCase = use.equalsIgnoreCase("idv");
                    if (_equalsIgnoreCase) {
                      return s.getIdentifier();
                    }
                  }
                }
              }
            }
          }
        }
      }
      return null;
    }


}
