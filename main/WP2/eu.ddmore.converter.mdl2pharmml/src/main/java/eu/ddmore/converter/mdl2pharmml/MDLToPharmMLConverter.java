package eu.ddmore.converter.mdl2pharmml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ddmore.mdl.MdlStandaloneSetup;
import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.ConditionalExpression;
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
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.Primary;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.RandomVariableDefinitionBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.UnaryExpression;
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
    private final String TYPE_INT = "int";
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
        System.out.println("Input: " + src.getAbsolutePath());

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

    public CharSequence convertToPharmML(final Mcl m) {
        CharSequence _xblockexpression = null;
        {
            this.mcl = m;
            this.prepareCollections(m);
            String version = "1.002";
            String date = "20.11.2013";
            String _plus = ("mdl2pharmML " + version);
            String _plus_1 = (_plus + " beta, last modification ");
            String _plus_2 = (_plus_1 + date);
            final String info = (_plus_2 + " Natallia Kokash (natallia.kokash@gmail.com)");
            boolean printIndependent = this.isIndependentVariableDefined(m);
            StringConcatenation _builder = new StringConcatenation();
            CharSequence _print_XS_Comment = this.print_XS_Comment(info);
            _builder.append(_print_XS_Comment, "");
            _builder.newLineIfNotEmpty();
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
            _builder.append("<ct:Name\">");
            Resource _eResource = m.eResource();
            String _fileName = this.fileName(_eResource);
            _builder.append(_fileName, "	");
            _builder.append("\"</ct:Name>");
            _builder.newLineIfNotEmpty();
            {
                if (printIndependent) {
                    _builder.append("\t");
                    _builder.append("<IndependentVariable symbID=\"t\"/>");
                    _builder.newLine();
                }
            }
            _builder.append("  \t\t\t");
            CharSequence _print_mdef_ModelDefinition = this.print_mdef_ModelDefinition();
            _builder.append(_print_mdef_ModelDefinition, "  			");
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
        Object _print_mdef_FunctionDefinition = this.print_mdef_FunctionDefinition();
        _builder.append(_print_mdef_FunctionDefinition, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        CharSequence _print_mdef_VariabilityModel = this.print_mdef_VariabilityModel();
        _builder.append(_print_mdef_VariabilityModel, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        CharSequence _print_mdef_ParameterModel = this.print_mdef_ParameterModel();
        _builder.append(_print_mdef_ParameterModel, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        CharSequence _print_mdef_StructuralModel = this.print_mdef_StructuralModel();
        _builder.append(_print_mdef_StructuralModel, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        CharSequence _print_mdef_CovariateModel = this.print_mdef_CovariateModel();
        _builder.append(_print_mdef_CovariateModel, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        CharSequence _print_mdef_ObservationModel = this.print_mdef_ObservationModel();
        _builder.append(_print_mdef_ObservationModel, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        CharSequence _print_msteps_ModellingSteps = this.print_msteps_ModellingSteps();
        _builder.append(_print_msteps_ModellingSteps, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</ModelDefinition>");
        _builder.newLine();
        return _builder;
    }

    public Object print_mdef_FunctionDefinition() {
        return null;
    }

    public CharSequence print_mdef_VariabilityModel() {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<VariabilityModel blkId=\"model\" type=\"model\">");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("<Level symbId=\"indiv\">");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("<ct:Name>Individual Variability</ct:Name>");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("</Level>");
        _builder.newLine();
        _builder.append("</VariabilityModel>");
        _builder.newLine();
        _builder.append("<VariabilityModel blkId=\"obsErr\" type=\"error\">");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("<Level  symbId=\"residual\">");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("<ct:Name>Residual Error</ct:Name>");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("</Level>");
        _builder.newLine();
        _builder.append("</VariabilityModel>");
        _builder.newLine();
        return _builder;
    }

    public CharSequence print_mdef_ParameterModel() {
        CharSequence _xblockexpression = null;
        {
            String parameterModel = "";
            int i = 1;
            String blockName = ("p" + Integer.valueOf(i));
            Set<Object> _keySet = this.theta_vars.keySet();
            for (final Object s : _keySet) {
                {
                    final String paramName = ((String) s);
                    boolean _notEquals = (!Objects.equal(paramName, null));
                    if (_notEquals) {
                        String varName = paramName;
                        String operator = "";
                        final int _index = paramName.indexOf("_");
                        int _minus = (-1);
                        boolean _greaterThan = (_index > _minus);
                        if (_greaterThan) {
                            int _plus = (_index + 1);
                            String _substring = paramName.substring(_plus);
                            varName = _substring;
                            SymbolDeclaration idv = this.findIndividualVariable(varName);
                            boolean _notEquals_1 = (!Objects.equal(idv, null));
                            if (_notEquals_1) {
                                String _defineTransformationOperator = this.defineTransformationOperator(idv);
                                operator = _defineTransformationOperator;
                            }
                        }
                        StringConcatenation _builder = new StringConcatenation();
                        _builder.append("<ParameterModel id=\"");
                        _builder.append(blockName, "");
                        _builder.append("\">");
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t");
                        CharSequence _print_mdef_ModelParameter = this.print_mdef_ModelParameter(paramName, varName, blockName, operator);
                        _builder.append(_print_mdef_ModelParameter, "	");
                        _builder.newLineIfNotEmpty();
                        _builder.append("</ParameterModel>");
                        _builder.newLine();
                        String _plus_1 = (parameterModel + _builder);
                        parameterModel = _plus_1;
                    }
                    int _plus_2 = (i + 1);
                    i = _plus_2;
                }
            }
            StringConcatenation _builder = new StringConcatenation();
            _builder.append(parameterModel, "");
            _builder.newLineIfNotEmpty();
            _xblockexpression = (_builder);
        }
        return _xblockexpression;
    }

    public CharSequence print_mdef_ModelParameter(final String paramName, final String varName, final String blockName,
            final String operator) {
        StringConcatenation _builder = new StringConcatenation();
        {
            boolean _notEquals = (!Objects.equal(paramName, null));
            if (_notEquals) {
                _builder.append("<Parameter symbId = \"");
                _builder.append(paramName, "");
                _builder.append("\"/>");
                _builder.newLineIfNotEmpty();
                {
                    boolean _notEquals_1 = (!Objects.equal(varName, null));
                    if (_notEquals_1) {
                        _builder.append("<Parameter symbId = \"omega_");
                        _builder.append(varName, "");
                        _builder.append("\"/>");
                        _builder.newLineIfNotEmpty();
                        _builder.append("<Parameter symbId = \"");
                        _builder.append(varName, "");
                        _builder.append("\"");
                        {
                            boolean _notEquals_2 = (!Objects.equal(operator, null));
                            if (_notEquals_2) {
                                {
                                    int _length = operator.length();
                                    boolean _greaterThan = (_length > 0);
                                    if (_greaterThan) {
                                        _builder.append(" transformation=\"");
                                        _builder.append(operator, "");
                                        _builder.append("\">");
                                    }
                                }
                            }
                        }
                        _builder.append(" ");
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t");
                        CharSequence _print_Math_Equation = this.print_Math_Equation(operator, paramName);
                        _builder.append(_print_Math_Equation, "	");
                        _builder.newLineIfNotEmpty();
                        _builder.append("</Parameter> ");
                        _builder.newLine();
                    }
                }
            }
        }
        return _builder;
    }

    public CharSequence print_mdef_StructuralModel() {
        CharSequence _xblockexpression = null;
        {
            String parameters = "";
            int i = 1;
            String blockName = ("p" + Integer.valueOf(i));
            Set<Object> _keySet = this.theta_vars.keySet();
            for (final Object s : _keySet) {
                {
                    final String paramName = ((String) s);
                    boolean _notEquals = (!Objects.equal(paramName, null));
                    if (_notEquals) {
                        String varName = paramName;
                        int _index = paramName.indexOf("_");
                        int _minus = (-1);
                        boolean _greaterThan = (_index > _minus);
                        if (_greaterThan) {
                            int _plus = (_index + 1);
                            String _substring = paramName.substring(_plus);
                            varName = _substring;
                            final SymbolDeclaration idv = this.findIndividualVariable(varName);
                            boolean _notEquals_1 = (!Objects.equal(idv, null));
                            if (_notEquals_1) {
                                CharSequence _print_mdef_Parameter = this.print_mdef_Parameter(varName, blockName);
                                String _plus_1 = (parameters + _print_mdef_Parameter);
                                parameters = _plus_1;
                            }
                        }
                    }
                    int _plus_2 = (i + 1);
                    i = _plus_2;
                }
            }
            String variables = "";
            String initial = "";
            EList<MclObject> _objects = this.mcl.getObjects();
            for (final MclObject o : _objects) {
                ModelObject _modelObject = o.getModelObject();
                boolean _notEquals = (!Objects.equal(_modelObject, null));
                if (_notEquals) {
                    ModelObject _modelObject_1 = o.getModelObject();
                    EList<ModelObjectBlock> _blocks = _modelObject_1.getBlocks();
                    for (final ModelObjectBlock b : _blocks) {
                        ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
                        boolean _notEquals_1 = (!Objects.equal(_modelPredictionBlock, null));
                        if (_notEquals_1) {
                            ModelPredictionBlock _modelPredictionBlock_1 = b.getModelPredictionBlock();
                            EList<ModelPredictionBlockStatement> _statements = _modelPredictionBlock_1.getStatements();
                            for (final ModelPredictionBlockStatement st : _statements) {
                                OdeBlock _odeBlock = st.getOdeBlock();
                                boolean _notEquals_2 = (!Objects.equal(_odeBlock, null));
                                if (_notEquals_2) {
                                    OdeBlock _odeBlock_1 = st.getOdeBlock();
                                    EList<BlockStatement> _statements_1 = _odeBlock_1.getStatements();
                                    for (final BlockStatement s_1 : _statements_1) {
                                        SymbolDeclaration _symbol = s_1.getSymbol();
                                        boolean _notEquals_3 = (!Objects.equal(_symbol, null));
                                        if (_notEquals_3) {
                                            StringConcatenation _builder = new StringConcatenation();
                                            SymbolDeclaration _symbol_1 = s_1.getSymbol();
                                            CharSequence _print_ct_VariableDefinitionType = this.print_ct_VariableDefinitionType(_symbol_1);
                                            _builder.append(_print_ct_VariableDefinitionType, "");
                                            String _plus = (variables + _builder);
                                            variables = _plus;
                                            StringConcatenation _builder_1 = new StringConcatenation();
                                            SymbolDeclaration _symbol_2 = s_1.getSymbol();
                                            CharSequence _print_InitialCondition = this.print_InitialCondition(_symbol_2);
                                            _builder_1.append(_print_InitialCondition, "");
                                            String _plus_1 = (initial + _builder_1);
                                            initial = _plus_1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            StringConcatenation _builder_2 = new StringConcatenation();
            {
                boolean _or = false;
                int _length = parameters.length();
                boolean _greaterThan = (_length > 0);
                if (_greaterThan) {
                    _or = true;
                } else {
                    int _length_1 = variables.length();
                    boolean _greaterThan_1 = (_length_1 > 0);
                    _or = (_greaterThan || _greaterThan_1);
                }
                if (_or) {
                    _builder_2.append("<StructuralModel id=\"main\">");
                    _builder_2.newLine();
                    _builder_2.append("\t");
                    {
                        int _length_2 = parameters.length();
                        boolean _greaterThan_2 = (_length_2 > 0);
                        if (_greaterThan_2) {
                            _builder_2.append(parameters, "	");
                        }
                    }
                    _builder_2.newLineIfNotEmpty();
                    _builder_2.append("\t");
                    {
                        int _length_3 = variables.length();
                        boolean _greaterThan_3 = (_length_3 > 0);
                        if (_greaterThan_3) {
                            _builder_2.append(variables, "	");
                        }
                    }
                    _builder_2.newLineIfNotEmpty();
                    _builder_2.append("\t");
                    {
                        int _length_4 = initial.length();
                        boolean _greaterThan_4 = (_length_4 > 0);
                        if (_greaterThan_4) {
                            _builder_2.append(initial, "	");
                        }
                    }
                    _builder_2.newLineIfNotEmpty();
                    _builder_2.append("</StructuralModel>");
                    _builder_2.newLine();
                    _builder_2.append("\t\t");
                }
            }
            _xblockexpression = (_builder_2);
        }
        return _xblockexpression;
    }

    public CharSequence print_mdef_CovariateModel() {
        CharSequence _xblockexpression = null;
        {
            String model = "";
            EList<MclObject> _objects = this.mcl.getObjects();
            for (final MclObject o : _objects) {
                ModelObject _modelObject = o.getModelObject();
                boolean _notEquals = (!Objects.equal(_modelObject, null));
                if (_notEquals) {
                    ModelObject _modelObject_1 = o.getModelObject();
                    EList<ModelObjectBlock> _blocks = _modelObject_1.getBlocks();
                    for (final ModelObjectBlock b : _blocks) {
                        GroupVariablesBlock _groupVariablesBlock = b.getGroupVariablesBlock();
                        boolean _notEquals_1 = (!Objects.equal(_groupVariablesBlock, null));
                        if (_notEquals_1) {
                            GroupVariablesBlock _groupVariablesBlock_1 = b.getGroupVariablesBlock();
                            EList<GroupVariablesBlockStatement> _statements = _groupVariablesBlock_1.getStatements();
                            for (final GroupVariablesBlockStatement st : _statements) {
                                BlockStatement _statement = st.getStatement();
                                boolean _notEquals_2 = (!Objects.equal(_statement, null));
                                if (_notEquals_2) {
                                    BlockStatement _statement_1 = st.getStatement();
                                    SymbolDeclaration _symbol = _statement_1.getSymbol();
                                    boolean _notEquals_3 = (!Objects.equal(_symbol, null));
                                    if (_notEquals_3) {
                                        StringConcatenation _builder = new StringConcatenation();
                                        BlockStatement _statement_2 = st.getStatement();
                                        SymbolDeclaration _symbol_1 = _statement_2.getSymbol();
                                        CharSequence _print_ct_Covariate = this.print_ct_Covariate(_symbol_1);
                                        _builder.append(_print_ct_Covariate, "");
                                        String _plus = (model + _builder);
                                        model = _plus;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            StringConcatenation _builder_1 = new StringConcatenation();
            {
                int _length = model.length();
                boolean _greaterThan = (_length > 0);
                if (_greaterThan) {
                    _builder_1.append("<CovariateModel>");
                    _builder_1.newLine();
                    _builder_1.append("\t");
                    _builder_1.append(model, "	");
                    _builder_1.newLineIfNotEmpty();
                    _builder_1.append("</CovariateModel>");
                    _builder_1.newLine();
                }
            }
            _xblockexpression = (_builder_1);
        }
        return _xblockexpression;
    }

    public CharSequence print_ct_Covariate(final SymbolDeclaration s) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<SimpleParameter  symbId=\"pop_");
        String _identifier = s.getIdentifier();
        _builder.append(_identifier, "");
        _builder.append("\"/>");
        _builder.newLineIfNotEmpty();
        _builder.append("<SimpleParameter  symbId=\"omega_");
        String _identifier_1 = s.getIdentifier();
        _builder.append(_identifier_1, "");
        _builder.append("\"/>");
        _builder.newLineIfNotEmpty();
        _builder.append("<Covariate symbId=\"");
        String _identifier_2 = s.getIdentifier();
        _builder.append(_identifier_2, "");
        _builder.append("\">");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("<Continuous>");
        _builder.newLine();
        _builder.append("\t\t");
        AnyExpression _expression = s.getExpression();
        CharSequence _print_Math_Expr = this.print_Math_Expr(_expression);
        _builder.append(_print_Math_Expr, "		");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("</Continuous>\t");
        _builder.newLine();
        _builder.append("</Covariate>");
        _builder.newLine();
        return _builder;
    }

    public CharSequence print_mdef_ObservationModel() {
        CharSequence _xblockexpression = null;
        {
            String model = "";
            EList<MclObject> _objects = this.mcl.getObjects();
            for (final MclObject o : _objects) {
                ModelObject _modelObject = o.getModelObject();
                boolean _notEquals = (!Objects.equal(_modelObject, null));
                if (_notEquals) {
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
                                    String _plus = (model + _builder);
                                    model = _plus;
                                }
                            }
                        }
                    }
                }
            }
            StringConcatenation _builder_1 = new StringConcatenation();
            {
                int _length = model.length();
                boolean _greaterThan = (_length > 0);
                if (_greaterThan) {
                    _builder_1.append("<ObservationModel>");
                    _builder_1.newLine();
                    _builder_1.append("\t");
                    _builder_1.append(model, "	");
                    _builder_1.newLineIfNotEmpty();
                    _builder_1.append("</ObservationModel>");
                    _builder_1.newLine();
                }
            }
            _xblockexpression = (_builder_1);
        }
        return _xblockexpression;
    }

    public CharSequence print_mdef_ObservationModel(final SymbolDeclaration s) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<SimpleParameter symbId=\"pop_");
        String _identifier = s.getIdentifier();
        _builder.append(_identifier, "");
        _builder.append("\"/>");
        _builder.newLineIfNotEmpty();
        _builder.append("<Continuous>");
        _builder.newLine();
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
                        CharSequence _print_Math_Contituous = this.print_Math_Contituous(_expression_4);
                        _builder.append(_print_Math_Contituous, "	");
                        _builder.newLineIfNotEmpty();
                    }
                }
            }
        }
        _builder.append("</Continuous>\t");
        _builder.newLine();
        return _builder;
    }

    public CharSequence print_Math_Contituous(final Expression expr) {
        StringConcatenation _builder = new StringConcatenation();
        HashMap<String, String> classifiedVars = this.classifyReferences(expr);
        _builder.newLineIfNotEmpty();
        final Function2<String, String, Boolean> _function = new Function2<String, String, Boolean>() {

            public Boolean apply(final String k, final String v) {
                boolean _equals = v.equals("other");
                return Boolean.valueOf(_equals);
            }
        };
        Map<String, String> simpleVars = MapExtensions.<String, String> filter(classifiedVars, _function);
        _builder.newLineIfNotEmpty();
        {
            int _size = simpleVars.size();
            boolean _greaterThan = (_size > 0);
            if (_greaterThan) {
                {
                    Set<String> _keySet = simpleVars.keySet();
                    for (final String s : _keySet) {
                        final String ref = ((String) s);
                        _builder.newLineIfNotEmpty();
                        _builder.append("<SimpleParameter symbId=\"");
                        _builder.append(ref, "");
                        _builder.append("\"/>");
                        _builder.newLineIfNotEmpty();
                    }
                }
            }
        }
        final Function2<String, String, Boolean> _function_1 = new Function2<String, String, Boolean>() {

            public Boolean apply(final String k, final String v) {
                boolean _equals = v.equals("theta");
                return Boolean.valueOf(_equals);
            }
        };
        Map<String, String> errorVars = MapExtensions.<String, String> filter(classifiedVars, _function_1);
        _builder.newLineIfNotEmpty();
        {
            int _size_1 = errorVars.size();
            boolean _greaterThan_1 = (_size_1 > 0);
            if (_greaterThan_1) {
                {
                    Set<String> _keySet_1 = errorVars.keySet();
                    for (final String s_1 : _keySet_1) {
                        final String ref_1 = ((String) s_1);
                        _builder.newLineIfNotEmpty();
                        CharSequence _print_mdef_ErrorModel = this.print_mdef_ErrorModel(ref_1);
                        _builder.append(_print_mdef_ErrorModel, "");
                        _builder.newLineIfNotEmpty();
                    }
                }
            }
        }
        final Function2<String, String, Boolean> _function_2 = new Function2<String, String, Boolean>() {

            public Boolean apply(final String k, final String v) {
                boolean _equals = v.equals("eps");
                return Boolean.valueOf(_equals);
            }
        };
        Map<String, String> randomVars = MapExtensions.<String, String> filter(classifiedVars, _function_2);
        _builder.newLineIfNotEmpty();
        {
            int _size_2 = randomVars.size();
            boolean _greaterThan_2 = (_size_2 > 0);
            if (_greaterThan_2) {
                _builder.append("<RandomEffects>");
                _builder.newLine();
                {
                    Set<String> _keySet_2 = randomVars.keySet();
                    for (final String s_2 : _keySet_2) {
                        _builder.append("\t");
                        final String ref_2 = ((String) s_2);
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t");
                        _builder.append("<ct:SymbRef symbIdRef=\"");
                        _builder.append(ref_2, "	");
                        _builder.append("\"/>");
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t");
                        CharSequence _print_uncert_Distribution = this.print_uncert_Distribution(ref_2);
                        _builder.append(_print_uncert_Distribution, "	");
                        _builder.newLineIfNotEmpty();
                    }
                }
                _builder.append("</RandomEffects>");
                _builder.newLine();
            }
        }
        return _builder;
    }

    public CharSequence print_mdef_ErrorModel(final String ref) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<ErrorModel>");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("<ct:Assign>");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("<Equation xmlns=\"");
        _builder.append(this.xmlns_math, "        ");
        _builder.append("\">");
        _builder.newLineIfNotEmpty();
        _builder.append("            ");
        _builder.append("<FunctionCall>");
        _builder.newLine();
        _builder.append("                ");
        _builder.append("<ct:SymbRef symbIdRef=\"constantErrorModel\"/>");
        _builder.newLine();
        _builder.append("                ");
        _builder.append("<FunctionArgument symbId=\"");
        _builder.append(ref, "                ");
        _builder.append("\">");
        _builder.newLineIfNotEmpty();
        _builder.append("                    ");
        _builder.append("<ct:SymbRef symbIdRef=\"");
        _builder.append(ref, "                    ");
        _builder.append("\"/>");
        _builder.newLineIfNotEmpty();
        _builder.append("                ");
        _builder.append("</FunctionArgument>");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("</FunctionCall>");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("</Equation>");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("</ct:Assign>");
        _builder.newLine();
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

    public String defineTransformationOperator(final SymbolDeclaration idv) {
        String _function = idv.getFunction();
        boolean _notEquals = (!Objects.equal(_function, null));
        if (_notEquals) {
            return idv.getFunction();
        } else {
            return "ln";
        }
    }

    public Arguments defineDistribution(final String ref) {
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
                            {
                                RandomList _randomList = s.getRandomList();
                                Arguments _arguments = _randomList.getArguments();
                                String variance = this.getAttribute(_arguments, "variance");
                                boolean _equalsIgnoreCase = variance.equalsIgnoreCase(ref);
                                if (_equalsIgnoreCase) {
                                    RandomList _randomList_1 = s.getRandomList();
                                    return _randomList_1.getArguments();
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public HashMap<String, String> classifyReferences(final Expression expr) {
        HashMap<String, String> classifiedVars = CollectionLiterals.<String, String> newHashMap();
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

    public CharSequence print_mdef_Parameter(final String varName, final String blockName) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<Parameter symbId = \"");
        _builder.append(varName, "");
        _builder.append("\">;");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("<Var block=\"");
        _builder.append(blockName, "	");
        _builder.append("\" xmlns=\"");
        _builder.append(this.xmlns_math, "	");
        _builder.append("\" symbId=\"");
        _builder.append(varName, "	");
        _builder.append("\"/>");
        _builder.newLineIfNotEmpty();
        _builder.append("</Parameter>");
        _builder.newLine();
        return _builder;
    }

    public CharSequence print_Math_Equation(final String operator, final String paramName) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<Equation xmlns=\"");
        _builder.append(this.xmlns_math, "");
        _builder.append("\" writtenVersion=\"");
        _builder.append(this.writtenVersion, "");
        _builder.append("\">;");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        CharSequence _print_Math_UniOp = this.print_Math_UniOp(operator, paramName);
        _builder.append(_print_Math_UniOp, "	");
        _builder.append(" ");
        _builder.newLineIfNotEmpty();
        _builder.append("</Equation>");
        _builder.newLine();
        return _builder;
    }

    public CharSequence print_Math_UniOp(final String operator, final String param) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<Uniop op=\"");
        _builder.append(operator, "");
        _builder.append("\">");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("<Var symbId=\"");
        _builder.append(param, "	");
        _builder.append("\"/>");
        _builder.newLineIfNotEmpty();
        _builder.append("</Uniop>");
        _builder.newLine();
        return _builder;
    }

    public CharSequence print_Math_Equation(final Expression expr) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<Equation xmlns=\"");
        _builder.append(this.xmlns_math, "");
        _builder.append("\" writtenVersion=\"");
        _builder.append(this.writtenVersion, "");
        _builder.append("\">;");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        CharSequence _print_Math_Expr = this.print_Math_Expr(expr);
        _builder.append(_print_Math_Expr, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</Equation>\"");
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
                _builder.append(_print_Math_LogicOpPiece, "	");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                Expression _expression2 = expr.getExpression2();
                OrExpression _expression_1 = expr.getExpression();
                OrExpression _dualExpression = this.dualExpression(_expression_1);
                CharSequence _print_Math_LogicOpPiece_1 = this.print_Math_LogicOpPiece(_expression2, _dualExpression);
                _builder.append(_print_Math_LogicOpPiece_1, "	");
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
        _builder.append(_print_Math_Expr, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("<Condition>");
        _builder.newLine();
        _builder.append("\t\t");
        String _print_Math_LogicOr = this.print_Math_LogicOr(condition, 0);
        _builder.append(_print_Math_LogicOr, "		");
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
            int _minus = (_size - 1);
            boolean _lessThan = (startIndex < _minus);
            if (_lessThan) {
                StringConcatenation _builder = new StringConcatenation();
                _builder.append("<LogicBinop op=\"or\">");
                _builder.newLine();
                _builder.append("\t");
                EList<AndExpression> _expression_1 = expr.getExpression();
                AndExpression _get = _expression_1.get(startIndex);
                String _print_Math_LogicAnd = this.print_Math_LogicAnd(_get, 0);
                _builder.append(_print_Math_LogicAnd, "	");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                int _plus = (startIndex + 1);
                Object _print_Math_LogicOr = this.print_Math_LogicOr(expr, _plus);
                _builder.append(_print_Math_LogicOr, "	");
                _builder.newLineIfNotEmpty();
                _builder.append("</LogicBinop>");
                _builder.newLine();
                return _builder.toString();
            } else {
                StringConcatenation _builder_1 = new StringConcatenation();
                EList<AndExpression> _expression_2 = expr.getExpression();
                AndExpression _get_1 = _expression_2.get(startIndex);
                String _print_Math_LogicAnd_1 = this.print_Math_LogicAnd(_get_1, 0);
                _builder_1.append(_print_Math_LogicAnd_1, "");
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
            int _minus = (_size - 1);
            boolean _lessThan = (startIndex < _minus);
            if (_lessThan) {
                StringConcatenation _builder = new StringConcatenation();
                _builder.append("<LogicBinop op=\"and\">");
                _builder.newLine();
                _builder.append("\t");
                EList<LogicalExpression> _expression_1 = expr.getExpression();
                LogicalExpression _get = _expression_1.get(startIndex);
                String _print_Math_LogicOp = this.print_Math_LogicOp(_get, 0);
                _builder.append(_print_Math_LogicOp, "	");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                int _plus = (startIndex + 1);
                Object _print_Math_LogicAnd = this.print_Math_LogicAnd(expr, _plus);
                _builder.append(_print_Math_LogicAnd, "	");
                _builder.newLineIfNotEmpty();
                _builder.append("</LogicBinop>");
                _builder.newLine();
                return _builder.toString();
            } else {
                StringConcatenation _builder_1 = new StringConcatenation();
                EList<LogicalExpression> _expression_2 = expr.getExpression();
                LogicalExpression _get_1 = _expression_2.get(startIndex);
                String _print_Math_LogicOp_1 = this.print_Math_LogicOp(_get_1, 0);
                _builder_1.append(_print_Math_LogicOp_1, "");
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
            int _minus = (_size - 1);
            boolean _lessThan = (startIndex < _minus);
            if (_lessThan) {
                StringConcatenation _builder = new StringConcatenation();
                _builder.append("<LogicBinop op=\"");
                EList<String> _operator_1 = expr.getOperator();
                int _plus = (startIndex + 1);
                String _get = _operator_1.get(_plus);
                String _convertOperator = this.convertOperator(_get);
                _builder.append(_convertOperator, "");
                _builder.append("\">");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                EList<AdditiveExpression> _expression_1 = expr.getExpression();
                AdditiveExpression _get_1 = _expression_1.get(startIndex);
                String _print_Math_AddOp = this.print_Math_AddOp(_get_1, 0);
                _builder.append(_print_Math_AddOp, "	");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                int _plus_1 = (startIndex + 1);
                Object _print_Math_LogicOp = this.print_Math_LogicOp(expr, _plus_1);
                _builder.append(_print_Math_LogicOp, "	");
                _builder.newLineIfNotEmpty();
                _builder.append("</LogicBinop>");
                _builder.newLine();
                return _builder.toString();
            } else {
                StringConcatenation _builder_1 = new StringConcatenation();
                EList<AdditiveExpression> _expression_2 = expr.getExpression();
                AdditiveExpression _get_2 = _expression_2.get(startIndex);
                String _print_Math_AddOp_1 = this.print_Math_AddOp(_get_2, 0);
                _builder_1.append(_print_Math_AddOp_1, "");
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
            int _minus = (_size - 1);
            boolean _lessThan = (startIndex < _minus);
            if (_lessThan) {
                StringConcatenation _builder = new StringConcatenation();
                _builder.append("<Binop op=\"");
                EList<String> _operator_1 = expr.getOperator();
                int _plus = (startIndex + 1);
                String _get = _operator_1.get(_plus);
                String _convertOperator = this.convertOperator(_get);
                _builder.append(_convertOperator, "");
                _builder.append("\">");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                EList<MultiplicativeExpression> _expression_1 = expr.getExpression();
                MultiplicativeExpression _get_1 = _expression_1.get(startIndex);
                String _print_Math_MultOp = this.print_Math_MultOp(_get_1, 0);
                _builder.append(_print_Math_MultOp, "	");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                int _plus_1 = (startIndex + 1);
                Object _print_Math_AddOp = this.print_Math_AddOp(expr, _plus_1);
                _builder.append(_print_Math_AddOp, "	");
                _builder.newLineIfNotEmpty();
                _builder.append("</Binop>");
                _builder.newLine();
                return _builder.toString();
            } else {
                StringConcatenation _builder_1 = new StringConcatenation();
                EList<MultiplicativeExpression> _expression_2 = expr.getExpression();
                MultiplicativeExpression _get_2 = _expression_2.get(startIndex);
                String _print_Math_MultOp_1 = this.print_Math_MultOp(_get_2, 0);
                _builder_1.append(_print_Math_MultOp_1, "");
                return _builder_1.toString();
            }
        } else {
            EList<String> _string = expr.getString();
            boolean _notEquals_1 = (!Objects.equal(_string, null));
            if (_notEquals_1) {
                String res = "";
                EList<String> _string_1 = expr.getString();
                for (final String s : _string_1) {
                    String _plus_2 = (res + s);
                    res = _plus_2;
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
            int _minus = (_size - 1);
            boolean _lessThan = (startIndex < _minus);
            if (_lessThan) {
                StringConcatenation _builder = new StringConcatenation();
                _builder.append("<Binop op=\"");
                EList<String> _operator_1 = expr.getOperator();
                int _plus = (startIndex + 1);
                String _get = _operator_1.get(_plus);
                String _convertOperator = this.convertOperator(_get);
                _builder.append(_convertOperator, "");
                _builder.append("\">");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                EList<PowerExpression> _expression_1 = expr.getExpression();
                PowerExpression _get_1 = _expression_1.get(startIndex);
                String _print_Math_PowerOp = this.print_Math_PowerOp(_get_1, 0);
                _builder.append(_print_Math_PowerOp, "	");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                int _plus_1 = (startIndex + 1);
                Object _print_Math_MultOp = this.print_Math_MultOp(expr, _plus_1);
                _builder.append(_print_Math_MultOp, "	");
                _builder.newLineIfNotEmpty();
                _builder.append("</Binop>");
                _builder.newLine();
                return _builder.toString();
            } else {
                StringConcatenation _builder_1 = new StringConcatenation();
                EList<PowerExpression> _expression_2 = expr.getExpression();
                PowerExpression _get_2 = _expression_2.get(startIndex);
                String _print_Math_PowerOp_1 = this.print_Math_PowerOp(_get_2, 0);
                _builder_1.append(_print_Math_PowerOp_1, "");
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
            int _minus = (_size - 1);
            boolean _lessThan = (startIndex < _minus);
            if (_lessThan) {
                StringConcatenation _builder = new StringConcatenation();
                _builder.append("<Binop op=\"");
                EList<String> _operator_1 = expr.getOperator();
                int _plus = (startIndex + 1);
                String _get = _operator_1.get(_plus);
                String _convertOperator = this.convertOperator(_get);
                _builder.append(_convertOperator, "");
                _builder.append("\">");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                EList<UnaryExpression> _expression_1 = expr.getExpression();
                UnaryExpression _get_1 = _expression_1.get(startIndex);
                CharSequence _print_Math_UniOp = this.print_Math_UniOp(_get_1);
                _builder.append(_print_Math_UniOp, "	");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                int _plus_1 = (startIndex + 1);
                Object _print_Math_PowerOp = this.print_Math_PowerOp(expr, _plus_1);
                _builder.append(_print_Math_PowerOp, "	");
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
        _builder.append(_print_Math_Equation, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        CharSequence _print_Math_Equation_1 = this.print_Math_Equation(expr2);
        _builder.append(_print_Math_Equation_1, "	");
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
        _builder.append(_print_Math_UniOp, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        CharSequence _print_Math_UniOp_1 = this.print_Math_UniOp(expr2);
        _builder.append(_print_Math_UniOp_1, "	");
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
                                        _builder.append("//expr.primary.vector");
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
            for (final Expression v : _values) {
                _builder.append("\t");
                CharSequence _print_Math_Expr = this.print_Math_Expr(v);
                _builder.append(_print_Math_Expr, "	");
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
        _builder.append(_identifier, "	");
        _builder.append(".");
        String _str_1 = this.toStr(ref);
        _builder.append(_str_1, "	");
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
                final NumberFormatException e = (NumberFormatException) _t;
                StringConcatenation _builder_2 = new StringConcatenation();
                _builder_2.append("<ct:String>");
                _builder_2.append(value, "");
                _builder_2.append("</ct:String>");
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

    public CharSequence print_uncert_Distribution(final String ref) {
        StringConcatenation _builder = new StringConcatenation();
        Arguments args = this.defineDistribution(ref);
        _builder.newLineIfNotEmpty();
        {
            boolean _notEquals = (!Objects.equal(args, null));
            if (_notEquals) {
                String distrType = this.getAttribute(args, "type");
                _builder.newLineIfNotEmpty();
                AnyExpression mean = this.getAttributeExpression(args, "mean");
                _builder.newLineIfNotEmpty();
                AnyExpression variance = this.getAttributeExpression(args, "variance");
                _builder.newLineIfNotEmpty();
                {
                    int _length = distrType.length();
                    boolean _greaterThan = (_length > 0);
                    if (_greaterThan) {
                        _builder.append("<Distribution xmlns=\"");
                        _builder.append(this.xmlns_uncert, "");
                        _builder.append("\" writtenVersion = \"");
                        _builder.append(this.writtenVersion, "");
                        _builder.append(">\";");
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t");
                        _builder.append("<");
                        _builder.append(distrType, "	");
                        _builder.append(">");
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t\t");
                        _builder.append("<Mean>");
                        _builder.newLine();
                        _builder.append("\t\t\t");
                        {
                            boolean _notEquals_1 = (!Objects.equal(mean, null));
                            if (_notEquals_1) {
                                CharSequence _print_Math_Expr = this.print_Math_Expr(mean);
                                _builder.append(_print_Math_Expr, "			");
                            }
                        }
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t\t");
                        _builder.append("</Mean>");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("<StdDev>");
                        _builder.newLine();
                        _builder.append("\t\t\t");
                        {
                            boolean _notEquals_2 = (!Objects.equal(variance, null));
                            if (_notEquals_2) {
                                CharSequence _print_Math_Expr_1 = this.print_Math_Expr(variance);
                                _builder.append(_print_Math_Expr_1, "			");
                            }
                        }
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t\t");
                        _builder.append("</StrDev>");
                        _builder.newLine();
                        _builder.append("\t");
                        _builder.append("</");
                        _builder.append(distrType, "	");
                        _builder.append(">");
                        _builder.newLineIfNotEmpty();
                        _builder.append("</Distribution>\"");
                        _builder.newLine();
                    }
                }
            }
        }
        return _builder;
    }

    public CharSequence print_msteps_ModellingSteps() {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<ModellingSteps>");
        _builder.newLine();
        {
            EList<MclObject> _objects = this.mcl.getObjects();
            for (final MclObject o : _objects) {
                _builder.append("\t");
                {
                    ModelObject _modelObject = o.getModelObject();
                    boolean _notEquals = (!Objects.equal(_modelObject, null));
                    if (_notEquals) {
                        ModelObject _modelObject_1 = o.getModelObject();
                        CharSequence _print_ct_DataSet = this.print_ct_DataSet(_modelObject_1);
                        _builder.append(_print_ct_DataSet, "	");
                    }
                }
                _builder.newLineIfNotEmpty();
            }
        }
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
        _builder.append("\t");
        CharSequence _print_msteps_ObjectiveDataSet = this.print_msteps_ObjectiveDataSet();
        _builder.append(_print_msteps_ObjectiveDataSet, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</ModellingSteps>");
        _builder.newLine();
        return _builder;
    }

    public CharSequence print_ct_DataSet(final ModelObject obj) {
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
                CharSequence _print_ds_DataSet = this.print_ds_DataSet(fileName, names, types);
                _xifexpression = _print_ds_DataSet;
            }
            _xblockexpression = (_xifexpression);
        }
        return _xblockexpression;
    }

    public CharSequence print_ds_DataSet(final String fileName, final ArrayList<String> names, final ArrayList<String> types) {
        try {
            CharSequence _xblockexpression = null;
            {
                StringConcatenation _builder = new StringConcatenation();
                String table = _builder.toString();
                BufferedReader fileReader = null;
                try {
                    FileReader _fileReader = new FileReader(fileName);
                    BufferedReader _bufferedReader = new BufferedReader(_fileReader);
                    fileReader = _bufferedReader;
                } catch (final Throwable _t) {
                    if (_t instanceof FileNotFoundException) {
                        final FileNotFoundException e = (FileNotFoundException) _t;
                        Resource _eResource = this.mcl.eResource();
                        URI _uRI = _eResource.getURI();

                        File _file = new File(_uRI.toFileString());
                        File file = _file;
                        String _parent = file.getParent();
                        String _plus = (_parent + "\\data\\");
                        String dataPath = (_plus + fileName);
                        try {
                            FileReader _fileReader_1 = new FileReader(dataPath);
                            BufferedReader _bufferedReader_1 = new BufferedReader(_fileReader_1);
                            fileReader = _bufferedReader_1;
                        } catch (final Throwable _t_1) {
                            if (_t_1 instanceof FileNotFoundException) {
                                final FileNotFoundException e1 = (FileNotFoundException) _t_1;
                                final int dotIndex = fileName.indexOf(".");
                                String fileExtension = "";
                                boolean _greaterThan = (dotIndex > 0);
                                if (_greaterThan) {
                                    int _plus_1 = (dotIndex + 1);
                                    String _substring = fileName.substring(_plus_1);
                                    fileExtension = _substring;
                                }
                                StringConcatenation _builder_1 = new StringConcatenation();
                                _builder_1.append("<Description>Source file not found (");
                                _builder_1.append(dataPath, "");
                                _builder_1.append(")!</Description>");
                                _builder_1.newLineIfNotEmpty();
                                _builder_1.append("<ExternalSource url=\"file=///");
                                _builder_1.append(fileName, "");
                                _builder_1.append("\" format=\"");
                                _builder_1.append(fileExtension, "");
                                _builder_1.append("\"/>\t");
                                _builder_1.newLineIfNotEmpty();
                                table = _builder_1.toString();
                            } else {
                                throw Exceptions.sneakyThrow(_t_1);
                            }
                        }
                    } else {
                        throw Exceptions.sneakyThrow(_t);
                    }
                }
                StringConcatenation _builder_2 = new StringConcatenation();
                String definition = _builder_2.toString();
                int _size = names.size();
                int _minus = (_size - 1);
                IntegerRange _upTo = new IntegerRange(0, _minus);
                for (final Integer i : _upTo) {
                    StringConcatenation _builder_3 = new StringConcatenation();
                    _builder_3.append("<ds:Column columnId=\"");
                    String _get = names.get((i).intValue());
                    _builder_3.append(_get, "");
                    _builder_3.append("\" valueType=\"");
                    String _get_1 = types.get((i).intValue());
                    _builder_3.append(_get_1, "");
                    _builder_3.append("\" columnNum=\"");
                    _builder_3.append(i, "");
                    _builder_3.append("\"/>");
                    _builder_3.newLineIfNotEmpty();
                    String _plus_2 = (definition + _builder_3);
                    definition = _plus_2;
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
                                String[] _split = line.split("\\s{1,}|,|;");
                                final Iterator<String> atoms = ((List<String>) Conversions.doWrapArray(_split)).iterator();
                                String row = "";
                                int _size_1 = IteratorExtensions.size(atoms);
                                int _minus_1 = (_size_1 - 1);
                                IntegerRange _upTo_1 = new IntegerRange(0, _minus_1);
                                for (final Integer i_1 : _upTo_1) {
                                    String _next = atoms.next();
                                    String _print_ct_Value = this.print_ct_Value(_next);
                                    String _plus_3 = (row + _print_ct_Value);
                                    row = _plus_3;
                                }
                                StringConcatenation _builder_4 = new StringConcatenation();
                                _builder_4.append("<Row>");
                                _builder_4.newLine();
                                _builder_4.append("\t");
                                _builder_4.append(row, "	");
                                _builder_4.newLineIfNotEmpty();
                                _builder_4.append("</Row>");
                                _builder_4.newLine();
                                String _plus_4 = (table + _builder_4);
                                table = _plus_4;
                            }
                            String _readLine_1 = fileReader.readLine();
                            String _line_1 = line = _readLine_1;
                            boolean _notEquals_2 = (!Objects.equal(_line_1, null));
                            _while = _notEquals_2;
                        }
                        fileReader.close();
                    }
                }
                StringConcatenation _builder_4 = new StringConcatenation();
                _builder_4.append("<DataSet>");
                _builder_4.newLine();
                {
                    int _length = definition.length();
                    boolean _greaterThan_1 = (_length > 0);
                    if (_greaterThan_1) {
                        _builder_4.append("\t");
                        _builder_4.append("<ds:Definition>");
                        _builder_4.newLine();
                        _builder_4.append("\t");
                        _builder_4.append("\t");
                        _builder_4.append(definition, "		");
                        _builder_4.newLineIfNotEmpty();
                        _builder_4.append("\t");
                        _builder_4.append("</ds:Definition>");
                        _builder_4.newLine();
                    }
                }
                {
                    int _length_1 = table.length();
                    boolean _greaterThan_2 = (_length_1 > 0);
                    if (_greaterThan_2) {
                        _builder_4.append("\t");
                        _builder_4.append("<ds:Table>\t");
                        _builder_4.newLine();
                        _builder_4.append("\t");
                        _builder_4.append("\t");
                        _builder_4.append(table, "		");
                        _builder_4.newLineIfNotEmpty();
                        _builder_4.append("\t");
                        _builder_4.append("</ds:Table>");
                        _builder_4.newLine();
                    }
                }
                _builder_4.append("</DataSet>\t");
                _builder_4.newLine();
                _xblockexpression = (_builder_4);
            }
            return _xblockexpression;
        } catch (Throwable _e) {
            throw Exceptions.sneakyThrow(_e);
        }
    }

    public CharSequence print_msteps_EstimationStep() {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<SimulationStep>\t\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("<Description>MDL source?</Description>");
        _builder.newLine();
        _builder.append("\t");
        Object _print_InitialValues = this.print_InitialValues();
        _builder.append(_print_InitialValues, "	");
        _builder.append("\t");
        _builder.newLineIfNotEmpty();
        _builder.append("</SimulationStep>");
        _builder.newLine();
        return _builder;
    }

    public CharSequence print_msteps_SimulationStep() {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<EstimationStep>");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("<Description>MDL source?</Description>");
        _builder.newLine();
        _builder.append("\t");
        Object _print_InitialValues = this.print_InitialValues();
        _builder.append(_print_InitialValues, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("</EstimationStep>");
        _builder.newLine();
        return _builder;
    }

    public Object print_InitialValues() {
        return null;
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

    public CharSequence print_msteps_ObjectiveDataSet() {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("<ObjectiveDataSet dataSetRef=\"\">");
        _builder.newLine();
        _builder.append("</ObjectiveDataSet>");
        _builder.newLine();
        return _builder;
    }

    public String print_Math_Mapping() {
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
                                    boolean _equalsIgnoreCase = use.equalsIgnoreCase("idv");
                                    if (_equalsIgnoreCase) {
                                        final String varName = s.getIdentifier();
                                        StringConcatenation _builder = new StringConcatenation();
                                        _builder.append("<Mapping columnName=\"");
                                        _builder.append(varName, "");
                                        _builder.append("\">");
                                        _builder.newLineIfNotEmpty();
                                        _builder.append("\t");
                                        _builder.append("<Var xmlns=\"");
                                        _builder.append(this.xmlns_math, "	");
                                        _builder.append("\" symbId=\"t\"/>");
                                        _builder.newLineIfNotEmpty();
                                        _builder.append("</Mapping>");
                                        _builder.newLine();
                                        return _builder.toString();
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
        _builder.append(_print_ct_AnnotationType, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        AnyExpression _expression_1 = v.getExpression();
        CharSequence _print_Math_Expr = this.print_Math_Expr(_expression_1);
        _builder.append(_print_Math_Expr, "	");
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
                CharSequence _print_Math_Equation = this.print_Math_Equation(_expression_1);
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
            for (final Argument c : _arguments_1) {
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
        _builder.append(_print_ct_SymbolRef, "	");
        _builder.newLineIfNotEmpty();
        {
            Arguments _arguments = call.getArguments();
            EList<Argument> _arguments_1 = _arguments.getArguments();
            for (final Argument arg : _arguments_1) {
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
        Object _print_Math_Expr = this.print_Math_Expr(_expression);
        _builder.append(_print_Math_Expr, "	");
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
            if (Objects.equal(operator, "<")) {
                _matched = true;
                _switchResult = ">";
            }
        }
        if (!_matched) {
            if (Objects.equal(operator, ">")) {
                _matched = true;
                _switchResult = "<";
            }
        }
        if (!_matched) {
            if (Objects.equal(operator, "<=")) {
                _matched = true;
                _switchResult = ">=";
            }
        }
        if (!_matched) {
            if (Objects.equal(operator, ">=")) {
                _matched = true;
                _switchResult = "<=";
            }
        }
        if (!_matched) {
            if (Objects.equal(operator, "==")) {
                _matched = true;
                _switchResult = "!=";
            }
        }
        if (!_matched) {
            if (Objects.equal(operator, "!=")) {
                _matched = true;
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
            if (Objects.equal(operator, "<")) {
                _matched = true;
                _switchResult = "lt";
            }
        }
        if (!_matched) {
            if (Objects.equal(operator, ">")) {
                _matched = true;
                _switchResult = "gt";
            }
        }
        if (!_matched) {
            if (Objects.equal(operator, "<=")) {
                _matched = true;
                _switchResult = "le";
            }
        }
        if (!_matched) {
            if (Objects.equal(operator, ">=")) {
                _matched = true;
                _switchResult = "ge";
            }
        }
        if (!_matched) {
            if (Objects.equal(operator, "==")) {
                _matched = true;
                _switchResult = "ne";
            }
        }
        if (!_matched) {
            if (Objects.equal(operator, "!=")) {
                _matched = true;
                _switchResult = "eq";
            }
        }
        if (!_matched) {
            if (Objects.equal(operator, "+")) {
                _matched = true;
                _switchResult = "plus";
            }
        }
        if (!_matched) {
            if (Objects.equal(operator, "-")) {
                _matched = true;
                _switchResult = "minus";
            }
        }
        if (!_matched) {
            if (Objects.equal(operator, "*")) {
                _matched = true;
                _switchResult = "times";
            }
        }
        if (!_matched) {
            if (Objects.equal(operator, "/")) {
                _matched = true;
                _switchResult = "divide";
            }
        }
        if (!_matched) {
            if (Objects.equal(operator, "^")) {
                _matched = true;
                _switchResult = "power";
            }
        }
        if (!_matched) {
            _switchResult = operator;
        }
        return _switchResult;
    }

    public boolean isIndependentVariableDefined(final Mcl m) {
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
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
