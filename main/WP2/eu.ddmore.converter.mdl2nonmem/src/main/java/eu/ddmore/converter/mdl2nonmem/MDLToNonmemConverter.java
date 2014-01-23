package eu.ddmore.converter.mdl2nonmem;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.ddmore.mdl.MdlStandaloneSetup;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.Block;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.ConditionalStatement;
import org.ddmore.mdl.mdl.DataBlock;
import org.ddmore.mdl.mdl.DataBlockStatement;
import org.ddmore.mdl.mdl.DataObject;
import org.ddmore.mdl.mdl.DataObjectBlock;
import org.ddmore.mdl.mdl.DiagBlock;
import org.ddmore.mdl.mdl.DropList;
import org.ddmore.mdl.mdl.EstimateTask;
import org.ddmore.mdl.mdl.EstimationBlock;
import org.ddmore.mdl.mdl.ExecuteTask;
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
import org.ddmore.mdl.mdl.IgnoreList;
import org.ddmore.mdl.mdl.ImportBlock;
import org.ddmore.mdl.mdl.ImportedFunction;
import org.ddmore.mdl.mdl.IndividualVariablesBlock;
import org.ddmore.mdl.mdl.LibraryBlock;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.MatrixBlock;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.MixtureBlock;
import org.ddmore.mdl.mdl.ModelBlock;
import org.ddmore.mdl.mdl.ModelBlockStatement;
import org.ddmore.mdl.mdl.ModelObject;
import org.ddmore.mdl.mdl.ModelObjectBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlockStatement;
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
import org.ddmore.mdl.mdl.PriorParametersBlock;
import org.ddmore.mdl.mdl.SameBlock;
import org.ddmore.mdl.mdl.Selector;
import org.ddmore.mdl.mdl.SimulateTask;
import org.ddmore.mdl.mdl.SimulationBlock;
import org.ddmore.mdl.mdl.StructuralBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.SymbolList;
import org.ddmore.mdl.mdl.SymbolModification;
import org.ddmore.mdl.mdl.TargetBlock;
import org.ddmore.mdl.mdl.TaskFunctionBlock;
import org.ddmore.mdl.mdl.TaskFunctionBody;
import org.ddmore.mdl.mdl.TaskFunctionDeclaration;
import org.ddmore.mdl.mdl.TaskObject;
import org.ddmore.mdl.mdl.TaskObjectBlock;
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VariabilityBlockStatement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Injector;

import eu.ddmore.converter.mdlprinting.MdlPrinterUtility;
import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.spi.ConverterProvider;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;

public class MDLToNonmemConverter extends MdlPrinterUtility implements ConverterProvider {

    private LanguageVersion source;
    private LanguageVersion target;
    private Version converterVersion;

    private final String TARGET = "NMTRAN_CODE";

    public MDLToNonmemConverter() {
        source = new LanguageVersionImpl();
        source.setLanguage("MDL");
        Version sourceVersion = new VersionImpl();
        sourceVersion.setMajor(5);
        sourceVersion.setMinor(0);
        sourceVersion.setPatch(8);
        source.setVersion(sourceVersion);

        target = new LanguageVersionImpl();
        target.setLanguage("NONMEM");
        Version targetVersion = new VersionImpl();
        targetVersion.setMajor(7);
        targetVersion.setMinor(2);
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

        CharSequence converted = convertToNMTRAN(mcl);

        return printOutputFile(src, outputDirectory, converted.toString(), ".ctl");
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

    public CharSequence convertToNMTRAN(final Mcl m) {
        CharSequence _xblockexpression = null;
        {
            this.prepareExternals(m);
            this.prepareCollections(m);
            String version = "1.02";
            String date = "24.08.2013";
            ArrayList<DataObject> _arrayList = new ArrayList<DataObject>();
            List<DataObject> dataObjects = _arrayList;
            ArrayList<TaskObject> _arrayList_1 = new ArrayList<TaskObject>();
            List<TaskObject> taskObjects = _arrayList_1;
            EList<MclObject> _objects = m.getObjects();
            for (final MclObject o : _objects) {
                {
                    DataObject _dataObject = o.getDataObject();
                    boolean _notEquals = (!Objects.equal(_dataObject, null));
                    if (_notEquals) {
                        DataObject _dataObject_1 = o.getDataObject();
                        dataObjects.add(_dataObject_1);
                    }
                    TaskObject _taskObject = o.getTaskObject();
                    boolean _notEquals_1 = (!Objects.equal(_taskObject, null));
                    if (_notEquals_1) {
                        TaskObject _taskObject_1 = o.getTaskObject();
                        taskObjects.add(_taskObject_1);
                    }
                }
            }
            StringConcatenation _builder = new StringConcatenation();
            _builder.append(";mdl2nt ");
            _builder.append(version, "");
            _builder.append(" beta, last modification ");
            _builder.append(date, "");
            _builder.append(", Natallia Kokash (natallia.kokash@gmail.com)  ");
            _builder.newLineIfNotEmpty();
            CharSequence _printSIZES = this.printSIZES(m);
            _builder.append(_printSIZES, "");
            _builder.newLineIfNotEmpty();
            CharSequence _printPROB = this.printPROB(m);
            _builder.append(_printPROB, "");
            _builder.newLineIfNotEmpty();
            {
                for (final DataObject d : dataObjects) {
                    CharSequence _convertToNMTRAN = this.convertToNMTRAN(d, taskObjects);
                    _builder.append(_convertToNMTRAN, "");
                    _builder.newLineIfNotEmpty();
                }
            }
            {
                for (final TaskObject t : taskObjects) {
                    CharSequence _printIGNORE = this.printIGNORE(t);
                    _builder.append(_printIGNORE, "");
                    _builder.newLineIfNotEmpty();
                }
            }
            CharSequence _printABBREVIATED = this.printABBREVIATED(m);
            _builder.append(_printABBREVIATED, "");
            _builder.newLineIfNotEmpty();
            {
                EList<MclObject> _objects_1 = m.getObjects();
                for (final MclObject o_1 : _objects_1) {
                    {
                        ModelObject _modelObject = o_1.getModelObject();
                        boolean _notEquals = (!Objects.equal(_modelObject, null));
                        if (_notEquals) {
                            ModelObject _modelObject_1 = o_1.getModelObject();
                            CharSequence _convertToNMTRAN_1 = this.convertToNMTRAN(_modelObject_1);
                            _builder.append(_convertToNMTRAN_1, "");
                        }
                    }
                    _builder.newLineIfNotEmpty();
                }
            }
            CharSequence _printAES = this.printAES(m);
            _builder.append(_printAES, "");
            _builder.newLineIfNotEmpty();
            {
                EList<MclObject> _objects_2 = m.getObjects();
                for (final MclObject o_2 : _objects_2) {
                    {
                        ParameterObject _parameterObject = o_2.getParameterObject();
                        boolean _notEquals_1 = (!Objects.equal(_parameterObject, null));
                        if (_notEquals_1) {
                            ParameterObject _parameterObject_1 = o_2.getParameterObject();
                            CharSequence _convertToNMTRAN_2 = this.convertToNMTRAN(_parameterObject_1);
                            _builder.append(_convertToNMTRAN_2, "");
                        }
                    }
                    _builder.newLineIfNotEmpty();
                }
            }
            {
                EList<MclObject> _objects_3 = m.getObjects();
                for (final MclObject o_3 : _objects_3) {
                    {
                        TaskObject _taskObject = o_3.getTaskObject();
                        boolean _notEquals_2 = (!Objects.equal(_taskObject, null));
                        if (_notEquals_2) {
                            TaskObject _taskObject_1 = o_3.getTaskObject();
                            CharSequence _convertToNMTRAN_3 = this.convertToNMTRAN(_taskObject_1);
                            _builder.append(_convertToNMTRAN_3, "");
                        }
                    }
                    _builder.newLineIfNotEmpty();
                }
            }
            {
                EList<MclObject> _objects_4 = m.getObjects();
                for (final MclObject o_4 : _objects_4) {
                    {
                        ModelObject _modelObject_2 = o_4.getModelObject();
                        boolean _notEquals_3 = (!Objects.equal(_modelObject_2, null));
                        if (_notEquals_3) {
                            ModelObject _modelObject_3 = o_4.getModelObject();
                            CharSequence _printTABLE = this.printTABLE(_modelObject_3);
                            _builder.append(_printTABLE, "");
                        }
                    }
                    _builder.newLineIfNotEmpty();
                }
            }
            _xblockexpression = (_builder);
        }
        return _xblockexpression;
    }

    public CharSequence printSIZES(final Mcl m) {
        StringConcatenation _builder = new StringConcatenation();
        {
            boolean _isTargetDefined = this.isTargetDefined("$SIZES");
            if (_isTargetDefined) {
                _builder.newLine();
                String _externalCodeStart = this.getExternalCodeStart("$SIZES");
                _builder.append(_externalCodeStart, "");
                _builder.newLineIfNotEmpty();
                String _externalCodeEnd = this.getExternalCodeEnd("$SIZES");
                _builder.append(_externalCodeEnd, "");
                _builder.newLineIfNotEmpty();
            }
        }
        return _builder;
    }

    public CharSequence printPROB(final Mcl m) {
        StringConcatenation _builder = new StringConcatenation();
        {
            boolean _or = false;
            boolean _isTargetDefined = this.isTargetDefined("$PROBLEM");
            if (_isTargetDefined) {
                _or = true;
            } else {
                boolean _isTargetDefined_1 = this.isTargetDefined("$PROB");
                _or = (_isTargetDefined || _isTargetDefined_1);
            }
            if (_or) {
                _builder.newLine();
                String _externalCodeStart = this.getExternalCodeStart("$PROBLEM");
                _builder.append(_externalCodeStart, "");
                _builder.newLineIfNotEmpty();
                String _externalCodeStart_1 = this.getExternalCodeStart("$PROB");
                _builder.append(_externalCodeStart_1, "");
                _builder.newLineIfNotEmpty();
                String _externalCodeEnd = this.getExternalCodeEnd("$PROBLEM");
                _builder.append(_externalCodeEnd, "");
                _builder.newLineIfNotEmpty();
                String _externalCodeEnd_1 = this.getExternalCodeEnd("$PROB");
                _builder.append(_externalCodeEnd_1, "");
                _builder.newLineIfNotEmpty();
            } else {
                _builder.newLine();
                _builder.append("$PROB ");
                String _fileNameUpperCase = this.fileNameUpperCase(m);
                _builder.append(_fileNameUpperCase, "");
                _builder.newLineIfNotEmpty();
            }
        }
        return _builder;
    }

    public CharSequence printABBREVIATED(final Mcl m) {
        StringConcatenation _builder = new StringConcatenation();
        {
            boolean _or = false;
            boolean _isTargetDefined = this.isTargetDefined("$ABB");
            if (_isTargetDefined) {
                _or = true;
            } else {
                boolean _isTargetDefined_1 = this.isTargetDefined("$ABBREVIATED");
                _or = (_isTargetDefined || _isTargetDefined_1);
            }
            if (_or) {
                _builder.newLine();
                String _externalCodeStart = this.getExternalCodeStart("$ABB");
                _builder.append(_externalCodeStart, "");
                _builder.newLineIfNotEmpty();
                String _externalCodeStart_1 = this.getExternalCodeStart("$ABBREVIATED");
                _builder.append(_externalCodeStart_1, "");
                _builder.newLineIfNotEmpty();
                String _externalCodeEnd = this.getExternalCodeEnd("$ABB");
                _builder.append(_externalCodeEnd, "");
                _builder.newLineIfNotEmpty();
                String _externalCodeEnd_1 = this.getExternalCodeEnd("$ABBREVIATED");
                _builder.append(_externalCodeEnd_1, "");
                _builder.newLineIfNotEmpty();
            }
        }
        return _builder;
    }

    public CharSequence printAES(final Mcl m) {
        StringConcatenation _builder = new StringConcatenation();
        {
            boolean _isTargetDefined = this.isTargetDefined("$AES");
            if (_isTargetDefined) {
                _builder.newLine();
                String _externalCodeStart = this.getExternalCodeStart("$AES");
                _builder.append(_externalCodeStart, "");
                _builder.newLineIfNotEmpty();
                String _externalCodeEnd = this.getExternalCodeEnd("$AES");
                _builder.append(_externalCodeEnd, "");
                _builder.newLineIfNotEmpty();
            }
        }
        return _builder;
    }

    public CharSequence convertToNMTRAN(final ModelObject o) {
        CharSequence _xblockexpression = null;
        {
            final boolean isLibraryDefined = this.isLibraryDefined(o);
            final boolean isPKDefined = this.isGroupOrIndividualDefined(o);
            final boolean isErrorDefined = this.isErrorDefined(o);
            final boolean isODEDefined = this.isODEDefined(o);
            StringConcatenation _builder = new StringConcatenation();
            {
                if (isLibraryDefined) {
                    _builder.newLine();
                    CharSequence _printSUBR = this.printSUBR(o, Boolean.valueOf(isPKDefined));
                    _builder.append(_printSUBR, "");
                    _builder.newLineIfNotEmpty();
                }
            }
            CharSequence _printMODEL = this.printMODEL(o, Boolean.valueOf(isODEDefined));
            _builder.append(_printMODEL, "");
            _builder.newLineIfNotEmpty();
            CharSequence _generateMODEL = this.generateMODEL(o);
            _builder.append(_generateMODEL, "");
            _builder.newLineIfNotEmpty();
            {
                if (isLibraryDefined) {
                    _builder.newLine();
                    CharSequence _printPK = this.printPK(o, Boolean.valueOf(isPKDefined));
                    _builder.append(_printPK, "");
                    _builder.newLineIfNotEmpty();
                    CharSequence _printMIX = this.printMIX(o);
                    _builder.append(_printMIX, "");
                    _builder.newLineIfNotEmpty();
                    CharSequence _printERROR = this.printERROR(o, Boolean.valueOf(isErrorDefined));
                    _builder.append(_printERROR, "");
                    _builder.newLineIfNotEmpty();
                } else {
                    CharSequence _printPRED = this.printPRED(o, Boolean.valueOf(isPKDefined), Boolean.valueOf(isErrorDefined));
                    _builder.append(_printPRED, "");
                    _builder.newLineIfNotEmpty();
                }
            }
            CharSequence _printDES = this.printDES(o, Boolean.valueOf(isODEDefined));
            _builder.append(_printDES, "");
            _builder.newLineIfNotEmpty();
            _xblockexpression = (_builder);
        }
        return _xblockexpression;
    }

    public CharSequence printPRED(final ModelObject o, final Boolean isPKDefined, final Boolean isErrorDefined) {
        StringConcatenation _builder = new StringConcatenation();
        {
            boolean _or = false;
            if ((isPKDefined).booleanValue()) {
                _or = true;
            } else {
                _or = ((isPKDefined).booleanValue() || (isErrorDefined).booleanValue());
            }
            if (_or) {
                _builder.newLine();
                _builder.append("$PRED");
                _builder.newLine();
            }
        }
        String _externalCodeStart = this.getExternalCodeStart("$PRED");
        _builder.append(_externalCodeStart, "");
        _builder.newLineIfNotEmpty();
        CharSequence _printPKContent = this.printPKContent(o);
        _builder.append(_printPKContent, "");
        _builder.newLineIfNotEmpty();
        CharSequence _printMIXContent = this.printMIXContent(o);
        _builder.append(_printMIXContent, "");
        _builder.newLineIfNotEmpty();
        CharSequence _printErrorContent = this.printErrorContent(o);
        _builder.append(_printErrorContent, "");
        _builder.newLineIfNotEmpty();
        String _externalCodeEnd = this.getExternalCodeEnd("$PRED");
        _builder.append(_externalCodeEnd, "");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public CharSequence printPK(final ModelObject o, final Boolean isPKDefined) {
        StringConcatenation _builder = new StringConcatenation();
        {
            if ((isPKDefined).booleanValue()) {
                _builder.newLine();
                _builder.append("$PK");
                _builder.newLine();
            }
        }
        String _externalCodeStart = this.getExternalCodeStart("$PK");
        _builder.append(_externalCodeStart, "");
        _builder.newLineIfNotEmpty();
        CharSequence _printPKContent = this.printPKContent(o);
        _builder.append(_printPKContent, "");
        _builder.newLineIfNotEmpty();
        String _externalCodeEnd = this.getExternalCodeEnd("$PK");
        _builder.append(_externalCodeEnd, "");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public CharSequence printPKContent(final ModelObject o) {
        StringConcatenation _builder = new StringConcatenation();
        {
            EList<ModelObjectBlock> _blocks = o.getBlocks();
            for (final ModelObjectBlock b : _blocks) {
                {
                    GroupVariablesBlock _groupVariablesBlock = b.getGroupVariablesBlock();
                    boolean _notEquals = (!Objects.equal(_groupVariablesBlock, null));
                    if (_notEquals) {
                        {
                            GroupVariablesBlock _groupVariablesBlock_1 = b.getGroupVariablesBlock();
                            EList<GroupVariablesBlockStatement> _statements = _groupVariablesBlock_1.getStatements();
                            for (final GroupVariablesBlockStatement st : _statements) {
                                {
                                    BlockStatement _statement = st.getStatement();
                                    boolean _notEquals_1 = (!Objects.equal(_statement, null));
                                    if (_notEquals_1) {
                                        BlockStatement _statement_1 = st.getStatement();
                                        CharSequence _print = this.print(_statement_1);
                                        _builder.append(_print, "");
                                        _builder.newLineIfNotEmpty();
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    IndividualVariablesBlock _individualVariablesBlock = b.getIndividualVariablesBlock();
                    boolean _notEquals_2 = (!Objects.equal(_individualVariablesBlock, null));
                    if (_notEquals_2) {
                        {
                            IndividualVariablesBlock _individualVariablesBlock_1 = b.getIndividualVariablesBlock();
                            EList<BlockStatement> _statements_1 = _individualVariablesBlock_1.getStatements();
                            boolean _hasElements = false;
                            for (final BlockStatement s : _statements_1) {
                                if (!_hasElements) {
                                    _hasElements = true;
                                } else {
                                    _builder.appendImmediate(" ", "");
                                }
                                CharSequence _print_1 = this.print(s);
                                _builder.append(_print_1, "");
                                _builder.newLineIfNotEmpty();
                            }
                        }
                    }
                }
                {
                    ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
                    boolean _notEquals_3 = (!Objects.equal(_modelPredictionBlock, null));
                    if (_notEquals_3) {
                        {
                            Set<Entry<Object, Object>> _entrySet = this.init_vars.entrySet();
                            int _size = _entrySet.size();
                            boolean _greaterThan = (_size > 0);
                            if (_greaterThan) {
                                _builder.newLine();
                                _builder.append(";initial conditions");
                                _builder.newLine();
                                {
                                    Set<Entry<Object, Object>> _entrySet_1 = this.init_vars.entrySet();
                                    for (final Entry<Object, Object> e : _entrySet_1) {
                                        _builder.append("A_0(");
                                        Object _key = e.getKey();
                                        _builder.append(_key, "");
                                        _builder.append(") = ");
                                        Object _value = e.getValue();
                                        _builder.append(_value, "");
                                        _builder.newLineIfNotEmpty();
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

    public CharSequence printMIX(final ModelObject o) {
        StringConcatenation _builder = new StringConcatenation();
        {
            boolean _isMixDefined = this.isMixDefined(o);
            if (_isMixDefined) {
                _builder.newLine();
                _builder.append("$MIX");
                _builder.newLine();
            }
        }
        String _externalCodeStart = this.getExternalCodeStart("$MIX");
        _builder.append(_externalCodeStart, "");
        _builder.newLineIfNotEmpty();
        String _externalCodeStart_1 = this.getExternalCodeStart("$MIXTURE");
        _builder.append(_externalCodeStart_1, "");
        _builder.newLineIfNotEmpty();
        CharSequence _printMIXContent = this.printMIXContent(o);
        _builder.append(_printMIXContent, "");
        _builder.newLineIfNotEmpty();
        String _externalCodeEnd = this.getExternalCodeEnd("$MIX");
        _builder.append(_externalCodeEnd, "");
        _builder.newLineIfNotEmpty();
        String _externalCodeEnd_1 = this.getExternalCodeEnd("$MIXTURE");
        _builder.append(_externalCodeEnd_1, "");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public CharSequence printMIXContent(final ModelObject o) {
        StringConcatenation _builder = new StringConcatenation();
        {
            EList<ModelObjectBlock> _blocks = o.getBlocks();
            for (final ModelObjectBlock b : _blocks) {
                {
                    GroupVariablesBlock _groupVariablesBlock = b.getGroupVariablesBlock();
                    boolean _notEquals = (!Objects.equal(_groupVariablesBlock, null));
                    if (_notEquals) {
                        {
                            GroupVariablesBlock _groupVariablesBlock_1 = b.getGroupVariablesBlock();
                            EList<GroupVariablesBlockStatement> _statements = _groupVariablesBlock_1.getStatements();
                            for (final GroupVariablesBlockStatement st : _statements) {
                                {
                                    MixtureBlock _mixtureBlock = st.getMixtureBlock();
                                    boolean _notEquals_1 = (!Objects.equal(_mixtureBlock, null));
                                    if (_notEquals_1) {
                                        MixtureBlock _mixtureBlock_1 = st.getMixtureBlock();
                                        CharSequence _print = this.print(_mixtureBlock_1);
                                        _builder.append(_print, "");
                                        _builder.newLineIfNotEmpty();
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

    public CharSequence print(final MixtureBlock b) {
        CharSequence _xblockexpression = null;
        {
            int nspop = 0;
            EList<BlockStatement> _statements = b.getStatements();
            for (final BlockStatement st : _statements) {
                SymbolDeclaration _symbol = st.getSymbol();
                boolean _notEquals = (!Objects.equal(_symbol, null));
                if (_notEquals) {
                    SymbolDeclaration _symbol_1 = st.getSymbol();
                    AnyExpression _expression = _symbol_1.getExpression();
                    org.ddmore.mdl.mdl.List _list = _expression.getList();
                    boolean _notEquals_1 = (!Objects.equal(_list, null));
                    if (_notEquals_1) {
                        SymbolDeclaration _symbol_2 = st.getSymbol();
                        AnyExpression _expression_1 = _symbol_2.getExpression();
                        org.ddmore.mdl.mdl.List _list_1 = _expression_1.getList();
                        Arguments _arguments = _list_1.getArguments();
                        EList<Argument> _arguments_1 = _arguments.getArguments();
                        int _size = _arguments_1.size();
                        nspop = _size;
                    }
                }
            }
            String res = "";
            boolean _greaterThan = (nspop > 0);
            if (_greaterThan) {
                String _plus = ("NSPOP = " + Integer.valueOf(nspop));
                String _plus_1 = (_plus + "\n");
                res = _plus_1;
                int i = 1;
                EList<BlockStatement> _statements_1 = b.getStatements();
                for (final BlockStatement st_1 : _statements_1) {
                    SymbolDeclaration _symbol_3 = st_1.getSymbol();
                    boolean _notEquals_2 = (!Objects.equal(_symbol_3, null));
                    if (_notEquals_2) {
                        SymbolDeclaration _symbol_4 = st_1.getSymbol();
                        AnyExpression _expression_2 = _symbol_4.getExpression();
                        Expression _expression_3 = _expression_2.getExpression();
                        boolean _notEquals_3 = (!Objects.equal(_expression_3, null));
                        if (_notEquals_3) {
                            String _plus_2 = (res + "P(");
                            String _plus_3 = (_plus_2 + Integer.valueOf(i));
                            String _plus_4 = (_plus_3 + ") = ");
                            SymbolDeclaration _symbol_5 = st_1.getSymbol();
                            AnyExpression _expression_4 = _symbol_5.getExpression();
                            Expression _expression_5 = _expression_4.getExpression();
                            String _str = this.toStr(_expression_5);
                            String _plus_5 = (_plus_4 + _str);
                            String _plus_6 = (_plus_5 + "\n");
                            res = _plus_6;
                            int _plus_7 = (i + 1);
                            i = _plus_7;
                        }
                    }
                }
            }
            StringConcatenation _builder = new StringConcatenation();
            _builder.append(res, "");
            _xblockexpression = (_builder);
        }
        return _xblockexpression;
    }

    public CharSequence printERROR(final ModelObject o, final Boolean isErrorDefined) {
        StringConcatenation _builder = new StringConcatenation();
        {
            if ((isErrorDefined).booleanValue()) {
                _builder.newLine();
                _builder.append("$ERROR");
                _builder.newLine();
            }
        }
        String _externalCodeStart = this.getExternalCodeStart("$ERROR");
        _builder.append(_externalCodeStart, "");
        _builder.newLineIfNotEmpty();
        CharSequence _printErrorContent = this.printErrorContent(o);
        _builder.append(_printErrorContent, "");
        _builder.newLineIfNotEmpty();
        String _externalCodeEnd = this.getExternalCodeEnd("$ERROR");
        _builder.append(_externalCodeEnd, "");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public CharSequence printErrorContent(final ModelObject o) {
        StringConcatenation _builder = new StringConcatenation();
        {
            EList<ModelObjectBlock> _blocks = o.getBlocks();
            for (final ModelObjectBlock mob : _blocks) {
                {
                    ModelPredictionBlock _modelPredictionBlock = mob.getModelPredictionBlock();
                    boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
                    if (_notEquals) {
                        {
                            ModelPredictionBlock _modelPredictionBlock_1 = mob.getModelPredictionBlock();
                            EList<ModelPredictionBlockStatement> _statements = _modelPredictionBlock_1.getStatements();
                            for (final ModelPredictionBlockStatement s : _statements) {
                                {
                                    BlockStatement _statement = s.getStatement();
                                    boolean _notEquals_1 = (!Objects.equal(_statement, null));
                                    if (_notEquals_1) {
                                        BlockStatement _statement_1 = s.getStatement();
                                        CharSequence _print = this.print(_statement_1);
                                        _builder.append(_print, "");
                                        _builder.newLineIfNotEmpty();
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    ObservationBlock _observationBlock = mob.getObservationBlock();
                    boolean _notEquals_2 = (!Objects.equal(_observationBlock, null));
                    if (_notEquals_2) {
                        ObservationBlock _observationBlock_1 = mob.getObservationBlock();
                        CharSequence _print_1 = this.print(_observationBlock_1);
                        _builder.append(_print_1, "");
                        _builder.newLineIfNotEmpty();
                    }
                }
                {
                    SimulationBlock _simulationBlock = mob.getSimulationBlock();
                    boolean _notEquals_3 = (!Objects.equal(_simulationBlock, null));
                    if (_notEquals_3) {
                        SimulationBlock _simulationBlock_1 = mob.getSimulationBlock();
                        CharSequence _print_2 = this.print(_simulationBlock_1);
                        _builder.append(_print_2, "");
                        _builder.newLineIfNotEmpty();
                    }
                }
                {
                    EstimationBlock _estimationBlock = mob.getEstimationBlock();
                    boolean _notEquals_4 = (!Objects.equal(_estimationBlock, null));
                    if (_notEquals_4) {
                        EstimationBlock _estimationBlock_1 = mob.getEstimationBlock();
                        CharSequence _print_3 = this.print(_estimationBlock_1);
                        _builder.append(_print_3, "");
                        _builder.newLineIfNotEmpty();
                    }
                }
            }
        }
        return _builder;
    }

    public CharSequence generateMODEL(final ModelObject o) {
        CharSequence _xblockexpression = null;
        {
            String res = "";
            int nmct = this.getNumberOfCompartments(o);
            boolean _greaterThan = (nmct > 0);
            if (_greaterThan) {
                String _plus = (res + "\n$MODEL\n");
                res = _plus;
                IntegerRange _upTo = new IntegerRange(1, nmct);
                for (final Integer i : _upTo) {
                    String _plus_1 = (res + "COMP (comp");
                    String _plus_2 = (_plus_1 + i);
                    String _plus_3 = (_plus_2 + ")\n");
                    res = _plus_3;
                }
            }
            StringConcatenation _builder = new StringConcatenation();
            _builder.append(res, "");
            _xblockexpression = (_builder);
        }
        return _xblockexpression;
    }

    public CharSequence printMODEL(final ModelObject o, final Boolean isODEDefined) {
        StringConcatenation _builder = new StringConcatenation();
        {
            if ((isODEDefined).booleanValue()) {
                _builder.newLine();
                _builder.append("$MODEL");
                _builder.newLine();
            }
        }
        String _externalCodeStart = this.getExternalCodeStart("$MODEL");
        _builder.append(_externalCodeStart, "");
        _builder.newLineIfNotEmpty();
        {
            EList<ModelObjectBlock> _blocks = o.getBlocks();
            for (final ModelObjectBlock b : _blocks) {
                {
                    ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
                    boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
                    if (_notEquals) {
                        ModelPredictionBlock bb = b.getModelPredictionBlock();
                        _builder.newLineIfNotEmpty();
                        {
                            EList<ModelPredictionBlockStatement> _statements = bb.getStatements();
                            for (final ModelPredictionBlockStatement s : _statements) {
                                {
                                    OdeBlock _odeBlock = s.getOdeBlock();
                                    boolean _notEquals_1 = (!Objects.equal(_odeBlock, null));
                                    if (_notEquals_1) {
                                        {
                                            OdeBlock _odeBlock_1 = s.getOdeBlock();
                                            EList<BlockStatement> _statements_1 = _odeBlock_1.getStatements();
                                            for (final BlockStatement ss : _statements_1) {
                                                SymbolDeclaration x = ss.getSymbol();
                                                _builder.newLineIfNotEmpty();
                                                {
                                                    boolean _notEquals_2 = (!Objects.equal(x, null));
                                                    if (_notEquals_2) {
                                                        {
                                                            AnyExpression _expression = x.getExpression();
                                                            boolean _notEquals_3 = (!Objects.equal(_expression, null));
                                                            if (_notEquals_3) {
                                                                {
                                                                    AnyExpression _expression_1 = x.getExpression();
                                                                    OdeList _odeList = _expression_1.getOdeList();
                                                                    boolean _notEquals_4 = (!Objects.equal(_odeList, null));
                                                                    if (_notEquals_4) {
                                                                        _builder.append("COMP(");
                                                                        String _identifier = x.getIdentifier();
                                                                        _builder.append(_identifier, "");
                                                                        _builder.append(")");
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
                            }
                        }
                    }
                }
            }
        }
        String _externalCodeEnd = this.getExternalCodeEnd("$MODEL");
        _builder.append(_externalCodeEnd, "");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public CharSequence printDES(final ModelObject o, final Boolean isODEDefined) {
        StringConcatenation _builder = new StringConcatenation();
        {
            if ((isODEDefined).booleanValue()) {
                _builder.newLine();
                _builder.append("$DES");
                _builder.newLine();
            }
        }
        String _externalCodeStart = this.getExternalCodeStart("$DES");
        _builder.append(_externalCodeStart, "");
        _builder.newLineIfNotEmpty();
        {
            EList<ModelObjectBlock> _blocks = o.getBlocks();
            for (final ModelObjectBlock b : _blocks) {
                {
                    ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
                    boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
                    if (_notEquals) {
                        ModelPredictionBlock bb = b.getModelPredictionBlock();
                        _builder.newLineIfNotEmpty();
                        {
                            EList<ModelPredictionBlockStatement> _statements = bb.getStatements();
                            for (final ModelPredictionBlockStatement s : _statements) {
                                {
                                    OdeBlock _odeBlock = s.getOdeBlock();
                                    boolean _notEquals_1 = (!Objects.equal(_odeBlock, null));
                                    if (_notEquals_1) {
                                        {
                                            OdeBlock _odeBlock_1 = s.getOdeBlock();
                                            EList<BlockStatement> _statements_1 = _odeBlock_1.getStatements();
                                            for (final BlockStatement ss : _statements_1) {
                                                SymbolDeclaration x = ss.getSymbol();
                                                _builder.newLineIfNotEmpty();
                                                {
                                                    boolean _notEquals_2 = (!Objects.equal(x, null));
                                                    if (_notEquals_2) {
                                                        {
                                                            AnyExpression _expression = x.getExpression();
                                                            boolean _notEquals_3 = (!Objects.equal(_expression, null));
                                                            if (_notEquals_3) {
                                                                {
                                                                    AnyExpression _expression_1 = x.getExpression();
                                                                    Expression _expression_2 = _expression_1.getExpression();
                                                                    boolean _notEquals_4 = (!Objects.equal(_expression_2, null));
                                                                    if (_notEquals_4) {
                                                                        CharSequence _print = this.print(x);
                                                                        _builder.append(_print, "");
                                                                        _builder.newLineIfNotEmpty();
                                                                    }
                                                                }
                                                                {
                                                                    AnyExpression _expression_3 = x.getExpression();
                                                                    OdeList _odeList = _expression_3.getOdeList();
                                                                    boolean _notEquals_5 = (!Objects.equal(_odeList, null));
                                                                    if (_notEquals_5) {
                                                                        AnyExpression _expression_4 = x.getExpression();
                                                                        OdeList _odeList_1 = _expression_4.getOdeList();
                                                                        Arguments _arguments = _odeList_1.getArguments();
                                                                        String deriv = this.getAttribute(_arguments, "deriv");
                                                                        _builder.newLineIfNotEmpty();
                                                                        {
                                                                            boolean _equals = deriv.equals("");
                                                                            boolean _not = (!_equals);
                                                                            if (_not) {
                                                                                String id = x.getIdentifier();
                                                                                _builder.newLineIfNotEmpty();
                                                                                {
                                                                                    Object _get = this.dadt_vars.get(id);
                                                                                    boolean _notEquals_6 = (!Objects.equal(_get, null));
                                                                                    if (_notEquals_6) {
                                                                                        _builder.append("DADT(");
                                                                                        Object _get_1 = this.dadt_vars.get(id);
                                                                                        _builder.append(_get_1, "");
                                                                                        _builder.append(") = ");
                                                                                        _builder.append(deriv, "");
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
                                                {
                                                    ConditionalStatement _statement = ss.getStatement();
                                                    boolean _notEquals_7 = (!Objects.equal(_statement, null));
                                                    if (_notEquals_7) {
                                                        ConditionalStatement _statement_1 = ss.getStatement();
                                                        CharSequence _print_1 = this.print(_statement_1);
                                                        _builder.append(_print_1, "");
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
            }
        }
        String _externalCodeEnd = this.getExternalCodeEnd("$DES");
        _builder.append(_externalCodeEnd, "");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public CharSequence printSUBR(final ModelObject o, final Boolean isPKDefined) {
        StringConcatenation _builder = new StringConcatenation();
        {
            if ((isPKDefined).booleanValue()) {
                _builder.newLine();
                _builder.append("$SUBR");
                _builder.newLine();
            }
        }
        String _externalCodeStart = this.getExternalCodeStart("$SUBR");
        _builder.append(_externalCodeStart, "");
        _builder.newLineIfNotEmpty();
        String _externalCodeStart_1 = this.getExternalCodeStart("$SUBROUTINE");
        _builder.append(_externalCodeStart_1, "");
        _builder.newLineIfNotEmpty();
        {
            EList<ModelObjectBlock> _blocks = o.getBlocks();
            for (final ModelObjectBlock b : _blocks) {
                {
                    ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
                    boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
                    if (_notEquals) {
                        ModelPredictionBlock _modelPredictionBlock_1 = b.getModelPredictionBlock();
                        String _printSUBR = this.printSUBR(_modelPredictionBlock_1);
                        _builder.append(_printSUBR, "");
                        _builder.newLineIfNotEmpty();
                    }
                }
            }
        }
        String _externalCodeEnd = this.getExternalCodeEnd("$SUBR");
        _builder.append(_externalCodeEnd, "");
        _builder.newLineIfNotEmpty();
        String _externalCodeEnd_1 = this.getExternalCodeEnd("$SUBROUTINE");
        _builder.append(_externalCodeEnd_1, "");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public String printSUBR(final ModelPredictionBlock b) {
        EList<ModelPredictionBlockStatement> _statements = b.getStatements();
        for (final ModelPredictionBlockStatement ss : _statements) {
            LibraryBlock _libraryBlock = ss.getLibraryBlock();
            boolean _notEquals = (!Objects.equal(_libraryBlock, null));
            if (_notEquals) {
                LibraryBlock _libraryBlock_1 = ss.getLibraryBlock();
                EList<FunctionCallStatement> _statements_1 = _libraryBlock_1.getStatements();
                for (final FunctionCallStatement st : _statements_1) {
                    {
                        FunctionCall _expression = st.getExpression();
                        FullyQualifiedSymbolName libraryRef = _expression.getIdentifier();
                        HashMap<String, String> attributes = this.getExternalFunctionAttributes(libraryRef);
                        String library = libraryRef.getIdentifier();
                        boolean _notEquals_1 = (!Objects.equal(attributes, null));
                        if (_notEquals_1) {
                            String name = attributes.get("name");
                            boolean _notEquals_2 = (!Objects.equal(name, null));
                            if (_notEquals_2) {
                                library = name;
                            }
                        }
                        FunctionCall _expression_1 = st.getExpression();
                        Arguments _arguments = _expression_1.getArguments();
                        final String model = this.getAttribute(_arguments, "model");
                        FunctionCall _expression_2 = st.getExpression();
                        Arguments _arguments_1 = _expression_2.getArguments();
                        final String trans = this.getAttribute(_arguments_1, "trans");
                        Resource _eResource = b.eResource();
                        final String tol = this.getTOL(_eResource);
                        StringConcatenation _builder = new StringConcatenation();
                        {
                            boolean _equals = model.equals("");
                            boolean _not = (!_equals);
                            if (_not) {
                                String _upperCase = library.toUpperCase();
                                _builder.append(_upperCase, "");
                                _builder.append(model, "");
                            }
                        }
                        _builder.append(" ");
                        {
                            boolean _equals_1 = trans.equals("");
                            boolean _not_1 = (!_equals_1);
                            if (_not_1) {
                                _builder.append("TRANS");
                                _builder.append(trans, "");
                            }
                        }
                        _builder.append(" ");
                        {
                            boolean _equals_2 = tol.equals("");
                            boolean _not_2 = (!_equals_2);
                            if (_not_2) {
                                _builder.append("TOL = ");
                                _builder.append(tol, "");
                            }
                        }
                        return _builder.toString();
                    }
                }
            }
        }
        return null;
    }

    public CharSequence printTABLE(final ModelObject o) {
        StringConcatenation _builder = new StringConcatenation();
        {
            boolean _isOutputVariablesDefined = this.isOutputVariablesDefined(o);
            if (_isOutputVariablesDefined) {
                _builder.newLine();
                _builder.append("$TABLE ");
                _builder.newLine();
            }
        }
        String _externalCodeStart = this.getExternalCodeStart("$TABLE");
        _builder.append(_externalCodeStart, "");
        _builder.newLineIfNotEmpty();
        {
            EList<ModelObjectBlock> _blocks = o.getBlocks();
            for (final ModelObjectBlock b : _blocks) {
                {
                    OutputVariablesBlock _outputVariablesBlock = b.getOutputVariablesBlock();
                    boolean _notEquals = (!Objects.equal(_outputVariablesBlock, null));
                    if (_notEquals) {
                        OutputVariablesBlock bb = b.getOutputVariablesBlock();
                        _builder.newLineIfNotEmpty();
                        {
                            EList<FullyQualifiedSymbolName> _variables = bb.getVariables();
                            int _size = _variables.size();
                            boolean _greaterThan = (_size > 0);
                            if (_greaterThan) {
                                {
                                    EList<FullyQualifiedSymbolName> _variables_1 = bb.getVariables();
                                    boolean _hasElements = false;
                                    for (final FullyQualifiedSymbolName st : _variables_1) {
                                        if (!_hasElements) {
                                            _hasElements = true;
                                        } else {
                                            _builder.appendImmediate(" ", "");
                                        }
                                        String _str = this.toStr(st);
                                        _builder.append(_str, "");
                                    }
                                }
                                _builder.newLineIfNotEmpty();
                                Resource _eResource = o.eResource();
                                final String file = this.getTaskObjectName(_eResource);
                                _builder.newLineIfNotEmpty();
                                _builder.append("ONEHEADER NOPRINT ");
                                {
                                    boolean _equals = file.equals("");
                                    boolean _not = (!_equals);
                                    if (_not) {
                                        _builder.append("FILE=");
                                        _builder.append(file, "");
                                        _builder.append(".fit");
                                    }
                                }
                                _builder.append(" ");
                                _builder.newLineIfNotEmpty();
                            }
                        }
                    }
                }
            }
        }
        String _externalCodeEnd = this.getExternalCodeEnd("$TABLE");
        _builder.append(_externalCodeEnd, "");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public CharSequence convertToNMTRAN(final ParameterObject obj) {
        CharSequence _xblockexpression = null;
        {
            this.collectDimensionsForSame(obj);
            StringConcatenation _builder = new StringConcatenation();
            CharSequence _printPRIOR = this.printPRIOR(obj);
            _builder.append(_printPRIOR, "");
            _builder.newLineIfNotEmpty();
            CharSequence _printTHETA = this.printTHETA(obj);
            _builder.append(_printTHETA, "");
            _builder.newLineIfNotEmpty();
            CharSequence _printOMEGA = this.printOMEGA(obj);
            _builder.append(_printOMEGA, "");
            _builder.newLineIfNotEmpty();
            CharSequence _printSIGMA = this.printSIGMA(obj);
            _builder.append(_printSIGMA, "");
            _builder.newLineIfNotEmpty();
            _xblockexpression = (_builder);
        }
        return _xblockexpression;
    }

    public CharSequence printPRIOR(final ParameterObject obj) {
        StringConcatenation _builder = new StringConcatenation();
        {
            boolean _isPriorDefined = this.isPriorDefined(obj);
            if (_isPriorDefined) {
                _builder.newLine();
                _builder.append("$PRIOR");
                _builder.newLine();
            }
        }
        String _externalCodeStart = this.getExternalCodeStart("$PRIOR");
        _builder.append(_externalCodeStart, "");
        _builder.newLineIfNotEmpty();
        {
            EList<ParameterObjectBlock> _blocks = obj.getBlocks();
            for (final ParameterObjectBlock b : _blocks) {
                {
                    PriorParametersBlock _priorBlock = b.getPriorBlock();
                    boolean _notEquals = (!Objects.equal(_priorBlock, null));
                    if (_notEquals) {
                        {
                            PriorParametersBlock _priorBlock_1 = b.getPriorBlock();
                            EList<BlockStatement> _statements = _priorBlock_1.getStatements();
                            for (final BlockStatement st : _statements) {
                                CharSequence _print = this.print(st);
                                _builder.append(_print, "");
                                _builder.newLineIfNotEmpty();
                            }
                        }
                    }
                }
            }
        }
        String _externalCodeEnd = this.getExternalCodeEnd("$PRIOR");
        _builder.append(_externalCodeEnd, "");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public CharSequence printTHETA(final ParameterObject obj) {
        StringConcatenation _builder = new StringConcatenation();
        {
            boolean _isStructuralDefined = this.isStructuralDefined(obj);
            if (_isStructuralDefined) {
                _builder.newLine();
                _builder.append("$THETA");
                _builder.newLine();
            }
        }
        String _externalCodeStart = this.getExternalCodeStart("$THETA");
        _builder.append(_externalCodeStart, "");
        _builder.newLineIfNotEmpty();
        {
            EList<ParameterObjectBlock> _blocks = obj.getBlocks();
            for (final ParameterObjectBlock b : _blocks) {
                {
                    StructuralBlock _structuralBlock = b.getStructuralBlock();
                    boolean _notEquals = (!Objects.equal(_structuralBlock, null));
                    if (_notEquals) {
                        {
                            StructuralBlock _structuralBlock_1 = b.getStructuralBlock();
                            EList<ParameterDeclaration> _parameters = _structuralBlock_1.getParameters();
                            for (final ParameterDeclaration st : _parameters) {
                                String _printTheta = this.printTheta(st);
                                _builder.append(_printTheta, "");
                                _builder.newLineIfNotEmpty();
                            }
                        }
                    }
                }
            }
        }
        String _externalCodeEnd = this.getExternalCodeEnd("$THETA");
        _builder.append(_externalCodeEnd, "");
        _builder.newLineIfNotEmpty();
        {
            boolean _isTargetDefined = this.isTargetDefined("$THETAI");
            if (_isTargetDefined) {
                _builder.newLine();
                ArrayList<String> _get = this.externalCodeStart.get("$THETAI");
                _builder.append(_get, "");
                _builder.newLineIfNotEmpty();
                ArrayList<String> _get_1 = this.externalCodeEnd.get("$THETAI");
                _builder.append(_get_1, "");
                _builder.newLineIfNotEmpty();
            }
        }
        {
            boolean _isTargetDefined_1 = this.isTargetDefined("$THETAR");
            if (_isTargetDefined_1) {
                _builder.newLine();
                ArrayList<String> _get_2 = this.externalCodeStart.get("$THETAR");
                _builder.append(_get_2, "");
                _builder.newLineIfNotEmpty();
                ArrayList<String> _get_3 = this.externalCodeEnd.get("$THETAR");
                _builder.append(_get_3, "");
                _builder.newLineIfNotEmpty();
            }
        }
        return _builder;
    }

    public CharSequence printOMEGA(final ParameterObject obj) {
        StringConcatenation _builder = new StringConcatenation();
        ArrayList<String> _get = this.externalCodeStart.get("$OMEGA");
        _builder.append(_get, "");
        _builder.newLineIfNotEmpty();
        String _printVariabilityBlock = this.printVariabilityBlock(obj, "$OMEGA");
        _builder.append(_printVariabilityBlock, "");
        _builder.newLineIfNotEmpty();
        ArrayList<String> _get_1 = this.externalCodeEnd.get("$OMEGA");
        _builder.append(_get_1, "");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public CharSequence printSIGMA(final ParameterObject obj) {
        StringConcatenation _builder = new StringConcatenation();
        String _externalCodeStart = this.getExternalCodeStart("$SIGMA");
        _builder.append(_externalCodeStart, "");
        _builder.newLineIfNotEmpty();
        String _printVariabilityBlock = this.printVariabilityBlock(obj, "$SIGMA");
        _builder.append(_printVariabilityBlock, "");
        _builder.newLineIfNotEmpty();
        String _externalCodeEnd = this.getExternalCodeEnd("$SIGMA");
        _builder.append(_externalCodeEnd, "");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public String printVariabilityBlock(final ParameterObject obj, final String section) {
        String res = "";
        EList<ParameterObjectBlock> _blocks = obj.getBlocks();
        for (final ParameterObjectBlock b : _blocks) {
            VariabilityBlock _variabilityBlock = b.getVariabilityBlock();
            boolean _notEquals = (!Objects.equal(_variabilityBlock, null));
            if (_notEquals) {
                boolean printSectionName = true;
                VariabilityBlock _variabilityBlock_1 = b.getVariabilityBlock();
                EList<VariabilityBlockStatement> _statements = _variabilityBlock_1.getStatements();
                for (final VariabilityBlockStatement c : _statements) {
                    {
                        ParameterDeclaration _parameter = c.getParameter();
                        boolean _notEquals_1 = (!Objects.equal(_parameter, null));
                        if (_notEquals_1) {
                            ParameterDeclaration _parameter_1 = c.getParameter();
                            String tmp = this.printVariabilityParameter(_parameter_1, section);
                            boolean _equals = tmp.equals("");
                            boolean _not = (!_equals);
                            if (_not) {
                                if (printSectionName) {
                                    String _plus = (res + "\n");
                                    String _plus_1 = (_plus + section);
                                    String _plus_2 = (_plus_1 + "\n");
                                    res = _plus_2;
                                    printSectionName = false;
                                }
                                String _plus_3 = (res + tmp);
                                res = _plus_3;
                            }
                        }
                        DiagBlock _diagBlock = c.getDiagBlock();
                        boolean _notEquals_2 = (!Objects.equal(_diagBlock, null));
                        if (_notEquals_2) {
                            DiagBlock _diagBlock_1 = c.getDiagBlock();
                            String _printDiag = this.printDiag(_diagBlock_1, section);
                            String _plus_4 = (res + _printDiag);
                            res = _plus_4;
                            printSectionName = true;
                        }
                        MatrixBlock _matrixBlock = c.getMatrixBlock();
                        boolean _notEquals_3 = (!Objects.equal(_matrixBlock, null));
                        if (_notEquals_3) {
                            MatrixBlock _matrixBlock_1 = c.getMatrixBlock();
                            String _printMatrix = this.printMatrix(_matrixBlock_1, section);
                            String _plus_5 = (res + _printMatrix);
                            res = _plus_5;
                            printSectionName = true;
                        }
                        SameBlock _sameBlock = c.getSameBlock();
                        boolean _notEquals_4 = (!Objects.equal(_sameBlock, null));
                        if (_notEquals_4) {
                            SameBlock _sameBlock_1 = c.getSameBlock();
                            String _printSame = this.printSame(_sameBlock_1, section);
                            String _plus_6 = (res + _printSame);
                            res = _plus_6;
                            printSectionName = true;
                        }
                    }
                }
            }
        }
        return res;
    }

    public String printSame(final SameBlock b, final String section) {
        Arguments _arguments = b.getArguments();
        String name = this.getAttribute(_arguments, "name");
        boolean _equals = name.equals("");
        if (_equals) {
            StringConcatenation _builder = new StringConcatenation();
            return _builder.toString();
        }
        boolean _and = false;
        boolean _equals_1 = section.equals("$OMEGA");
        if (!_equals_1) {
            _and = false;
        } else {
            Integer _get = this.namedOmegaBlocks.get(name);
            boolean _notEquals = (!Objects.equal(_get, null));
            _and = (_equals_1 && _notEquals);
        }
        final boolean isOmega = _and;
        boolean _and_1 = false;
        boolean _equals_2 = section.equals("$SIGMA");
        if (!_equals_2) {
            _and_1 = false;
        } else {
            Integer _get_1 = this.namedSigmaBlocks.get(name);
            boolean _notEquals_1 = (!Objects.equal(_get_1, null));
            _and_1 = (_equals_2 && _notEquals_1);
        }
        final boolean isSigma = _and_1;
        boolean _or = false;
        if (isOmega) {
            _or = true;
        } else {
            _or = (isOmega || isSigma);
        }
        if (_or) {
            int k = 0;
            if (isOmega) {
                Integer _get_2 = this.namedOmegaBlocks.get(name);
                k = (_get_2).intValue();
            }
            if (isSigma) {
                Integer _get_3 = this.namedSigmaBlocks.get(name);
                k = (_get_3).intValue();
            }
            StringConcatenation _builder_1 = new StringConcatenation();
            _builder_1.newLine();
            _builder_1.append(section, "");
            _builder_1.append(" ");
            {
                boolean _greaterThan = (k > 0);
                if (_greaterThan) {
                    _builder_1.append("BLOCK (");
                    _builder_1.append(k, "");
                    _builder_1.append(") SAME");
                }
            }
            _builder_1.newLineIfNotEmpty();
            {
                FormalArguments _parameters = b.getParameters();
                boolean _notEquals_2 = (!Objects.equal(_parameters, null));
                if (_notEquals_2) {
                    {
                        FormalArguments _parameters_1 = b.getParameters();
                        EList<FormalArgument> _arguments_1 = _parameters_1.getArguments();
                        for (final FormalArgument p : _arguments_1) {
                            _builder_1.append("; ");
                            String _identifier = p.getIdentifier();
                            _builder_1.append(_identifier, "");
                            _builder_1.newLineIfNotEmpty();
                        }
                    }
                }
            }
            return _builder_1.toString();
        }
        return "";
    }

    public void collectDimensionsForSame(final ParameterObject obj) {
        EList<ParameterObjectBlock> _blocks = obj.getBlocks();
        for (final ParameterObjectBlock b : _blocks) {
            VariabilityBlock _variabilityBlock = b.getVariabilityBlock();
            boolean _notEquals = (!Objects.equal(_variabilityBlock, null));
            if (_notEquals) {
                VariabilityBlock _variabilityBlock_1 = b.getVariabilityBlock();
                EList<VariabilityBlockStatement> _statements = _variabilityBlock_1.getStatements();
                for (final VariabilityBlockStatement c : _statements) {
                    {
                        DiagBlock _diagBlock = c.getDiagBlock();
                        boolean _notEquals_1 = (!Objects.equal(_diagBlock, null));
                        if (_notEquals_1) {
                            DiagBlock _diagBlock_1 = c.getDiagBlock();
                            this.collectDimensionsForSame(_diagBlock_1);
                        }
                        MatrixBlock _matrixBlock = c.getMatrixBlock();
                        boolean _notEquals_2 = (!Objects.equal(_matrixBlock, null));
                        if (_notEquals_2) {
                            MatrixBlock _matrixBlock_1 = c.getMatrixBlock();
                            this.collectDimensionsForSame(_matrixBlock_1);
                        }
                    }
                }
            }
        }
    }

    public Integer collectDimensionsForSame(final DiagBlock b) {
        Integer _xblockexpression = null;
        {
            int k = 0;
            Arguments _arguments = b.getArguments();
            String name = this.getAttribute(_arguments, "name");
            boolean isOmega = false;
            boolean isSigma = false;
            Integer _xifexpression = null;
            boolean _notEquals = (!Objects.equal(name, null));
            if (_notEquals) {
                Integer _xblockexpression_1 = null;
                {
                    Arguments _parameters = b.getParameters();
                    boolean _notEquals_1 = (!Objects.equal(_parameters, null));
                    if (_notEquals_1) {
                        Arguments _parameters_1 = b.getParameters();
                        EList<Argument> _arguments_1 = _parameters_1.getArguments();
                        for (final Argument p : _arguments_1) {
                            AnyExpression _expression = p.getExpression();
                            boolean _notEquals_2 = (!Objects.equal(_expression, null));
                            if (_notEquals_2) {
                                int _plus = (k + 1);
                                k = _plus;
                                String _identifier = p.getIdentifier();
                                boolean _notEquals_3 = (!Objects.equal(_identifier, null));
                                if (_notEquals_3) {
                                    String _identifier_1 = p.getIdentifier();
                                    String _plus_1 = ("eta_" + _identifier_1);
                                    Object _get = this.eta_vars.get(_plus_1);
                                    boolean _notEquals_4 = (!Objects.equal(_get, null));
                                    if (_notEquals_4) {
                                        isOmega = true;
                                    }
                                    String _identifier_2 = p.getIdentifier();
                                    String _plus_2 = ("eps_" + _identifier_2);
                                    Object _get_1 = this.eps_vars.get(_plus_2);
                                    boolean _notEquals_5 = (!Objects.equal(_get_1, null));
                                    if (_notEquals_5) {
                                        isSigma = true;
                                    }
                                }
                            }
                        }
                    }
                    if (isOmega) {
                        this.namedOmegaBlocks.put(name, Integer.valueOf(k));
                    }
                    Integer _xifexpression_1 = null;
                    if (isSigma) {
                        Integer _put = this.namedSigmaBlocks.put(name, Integer.valueOf(k));
                        _xifexpression_1 = _put;
                    }
                    _xblockexpression_1 = (_xifexpression_1);
                }
                _xifexpression = _xblockexpression_1;
            }
            _xblockexpression = (_xifexpression);
        }
        return _xblockexpression;
    }

    public Integer collectDimensionsForSame(final MatrixBlock b) {
        Integer _xblockexpression = null;
        {
            int k = 0;
            Arguments _arguments = b.getArguments();
            String name = this.getAttribute(_arguments, "name");
            boolean isOmega = false;
            boolean isSigma = false;
            Arguments _parameters = b.getParameters();
            boolean _notEquals = (!Objects.equal(_parameters, null));
            if (_notEquals) {
                Arguments _parameters_1 = b.getParameters();
                EList<Argument> _arguments_1 = _parameters_1.getArguments();
                for (final Argument p : _arguments_1) {
                    AnyExpression _expression = p.getExpression();
                    boolean _notEquals_1 = (!Objects.equal(_expression, null));
                    if (_notEquals_1) {
                        String _identifier = p.getIdentifier();
                        boolean _notEquals_2 = (!Objects.equal(_identifier, null));
                        if (_notEquals_2) {
                            String _identifier_1 = p.getIdentifier();
                            String _plus = ("eta_" + _identifier_1);
                            Object _get = this.eta_vars.get(_plus);
                            boolean _notEquals_3 = (!Objects.equal(_get, null));
                            if (_notEquals_3) {
                                isOmega = true;
                            }
                            String _identifier_2 = p.getIdentifier();
                            String _plus_1 = ("eps_" + _identifier_2);
                            Object _get_1 = this.eps_vars.get(_plus_1);
                            boolean _notEquals_4 = (!Objects.equal(_get_1, null));
                            if (_notEquals_4) {
                                isSigma = true;
                            }
                            int _plus_2 = (k + 1);
                            k = _plus_2;
                        }
                    }
                }
            }
            if (isOmega) {
                this.namedOmegaBlocks.put(name, Integer.valueOf(k));
            }
            Integer _xifexpression = null;
            if (isSigma) {
                Integer _put = this.namedSigmaBlocks.put(name, Integer.valueOf(k));
                _xifexpression = _put;
            }
            _xblockexpression = (_xifexpression);
        }
        return _xblockexpression;
    }

    public String printDiag(final DiagBlock b, final String section) {
        String result = "";
        boolean printFix = false;
        int k = 0;
        Arguments _arguments = b.getArguments();
        EList<Argument> _arguments_1 = _arguments.getArguments();
        for (final Argument a : _arguments_1) {
            String _identifier = a.getIdentifier();
            boolean _notEquals = (!Objects.equal(_identifier, null));
            if (_notEquals) {
                String _identifier_1 = a.getIdentifier();
                boolean _equals = _identifier_1.equals("fix");
                if (_equals) {
                    AnyExpression _expression = a.getExpression();
                    boolean _notEquals_1 = (!Objects.equal(_expression, null));
                    if (_notEquals_1) {
                        AnyExpression _expression_1 = a.getExpression();
                        boolean _isTrue = this.isTrue(_expression_1);
                        printFix = _isTrue;
                    }
                }
            }
        }
        Arguments _parameters = b.getParameters();
        boolean _notEquals_2 = (!Objects.equal(_parameters, null));
        if (_notEquals_2) {
            Arguments _parameters_1 = b.getParameters();
            EList<Argument> _arguments_2 = _parameters_1.getArguments();
            for (final Argument p : _arguments_2) {
                AnyExpression _expression_2 = p.getExpression();
                boolean _notEquals_3 = (!Objects.equal(_expression_2, null));
                if (_notEquals_3) {
                    int i = 0;
                    String tmpRes = "";
                    boolean _lessThan = (i < k);
                    boolean _while = _lessThan;
                    while (_while) {
                        {
                            String _plus = (tmpRes + "0 ");
                            tmpRes = _plus;
                            int _plus_1 = (i + 1);
                            i = _plus_1;
                        }
                        boolean _lessThan_1 = (i < k);
                        _while = _lessThan_1;
                    }
                    int _plus = (k + 1);
                    k = _plus;
                    String _identifier_2 = p.getIdentifier();
                    boolean _notEquals_4 = (!Objects.equal(_identifier_2, null));
                    if (_notEquals_4) {
                        boolean _and = false;
                        boolean _equals_1 = section.equals("$OMEGA");
                        if (!_equals_1) {
                            _and = false;
                        } else {
                            String _identifier_3 = p.getIdentifier();
                            String _plus_1 = ("eta_" + _identifier_3);
                            Object _get = this.eta_vars.get(_plus_1);
                            boolean _notEquals_5 = (!Objects.equal(_get, null));
                            _and = (_equals_1 && _notEquals_5);
                        }
                        final boolean isOmega = _and;
                        boolean _and_1 = false;
                        boolean _equals_2 = section.equals("$SIGMA");
                        if (!_equals_2) {
                            _and_1 = false;
                        } else {
                            String _identifier_4 = p.getIdentifier();
                            String _plus_2 = ("eps_" + _identifier_4);
                            Object _get_1 = this.eps_vars.get(_plus_2);
                            boolean _notEquals_6 = (!Objects.equal(_get_1, null));
                            _and_1 = (_equals_2 && _notEquals_6);
                        }
                        final boolean isSigma = _and_1;
                        boolean _or = false;
                        if (isOmega) {
                            _or = true;
                        } else {
                            _or = (isOmega || isSigma);
                        }
                        if (_or) {
                            String _plus_3 = (result + tmpRes);
                            AnyExpression _expression_3 = p.getExpression();
                            String _str = this.toStr(_expression_3);
                            String _plus_4 = (_plus_3 + _str);
                            String _plus_5 = (_plus_4 + " ");
                            result = _plus_5;
                            String _plus_6 = (result + "; ");
                            String _identifier_5 = p.getIdentifier();
                            String _plus_7 = (_plus_6 + _identifier_5);
                            String _plus_8 = (_plus_7 + "\n");
                            result = _plus_8;
                        }
                    } else {
                        boolean _equals_3 = result.equals("");
                        boolean _not = (!_equals_3);
                        if (_not) {
                            AnyExpression _expression_4 = p.getExpression();
                            String _str_1 = this.toStr(_expression_4);
                            String _plus_9 = (result + _str_1);
                            String _plus_10 = (_plus_9 + " ");
                            result = _plus_10;
                        }
                    }
                }
            }
        }
        boolean _and_2 = false;
        if (!printFix) {
            _and_2 = false;
        } else {
            boolean _equals_4 = result.equals("");
            boolean _not_1 = (!_equals_4);
            _and_2 = (printFix && _not_1);
        }
        if (_and_2) {
            String _plus_11 = (result + "FIX\n");
            result = _plus_11;
        }
        boolean _equals_5 = result.equals("");
        if (_equals_5) {
            return "";
        }
        StringConcatenation _builder = new StringConcatenation();
        _builder.newLine();
        _builder.append(section, "");
        _builder.append(" ");
        {
            boolean _greaterThan = (k > 0);
            if (_greaterThan) {
                _builder.append("BLOCK (");
                _builder.append(k, "");
                _builder.append(") ");
            }
        }
        _builder.newLineIfNotEmpty();
        _builder.append(result, "");
        _builder.newLineIfNotEmpty();
        return _builder.toString();
    }

    public String printMatrix(final MatrixBlock b, final String section) {
        String result = "";
        boolean printFix = false;
        int k = 0;
        Arguments _arguments = b.getArguments();
        EList<Argument> _arguments_1 = _arguments.getArguments();
        for (final Argument a : _arguments_1) {
            String _identifier = a.getIdentifier();
            boolean _notEquals = (!Objects.equal(_identifier, null));
            if (_notEquals) {
                String _identifier_1 = a.getIdentifier();
                boolean _equals = _identifier_1.equals("fix");
                if (_equals) {
                    AnyExpression _expression = a.getExpression();
                    boolean _notEquals_1 = (!Objects.equal(_expression, null));
                    if (_notEquals_1) {
                        AnyExpression _expression_1 = a.getExpression();
                        boolean _isTrue = this.isTrue(_expression_1);
                        printFix = _isTrue;
                    }
                }
            }
        }
        Arguments _parameters = b.getParameters();
        boolean _notEquals_2 = (!Objects.equal(_parameters, null));
        if (_notEquals_2) {
            Arguments _parameters_1 = b.getParameters();
            EList<Argument> _arguments_2 = _parameters_1.getArguments();
            for (final Argument p : _arguments_2) {
                AnyExpression _expression_2 = p.getExpression();
                boolean _notEquals_3 = (!Objects.equal(_expression_2, null));
                if (_notEquals_3) {
                    String _identifier_2 = p.getIdentifier();
                    boolean _notEquals_4 = (!Objects.equal(_identifier_2, null));
                    if (_notEquals_4) {
                        boolean _and = false;
                        boolean _equals_1 = section.equals("$OMEGA");
                        if (!_equals_1) {
                            _and = false;
                        } else {
                            String _identifier_3 = p.getIdentifier();
                            String _plus = ("eta_" + _identifier_3);
                            Object _get = this.eta_vars.get(_plus);
                            boolean _notEquals_5 = (!Objects.equal(_get, null));
                            _and = (_equals_1 && _notEquals_5);
                        }
                        final boolean isOmega = _and;
                        boolean _and_1 = false;
                        boolean _equals_2 = section.equals("$SIGMA");
                        if (!_equals_2) {
                            _and_1 = false;
                        } else {
                            String _identifier_4 = p.getIdentifier();
                            String _plus_1 = ("eps_" + _identifier_4);
                            Object _get_1 = this.eps_vars.get(_plus_1);
                            boolean _notEquals_6 = (!Objects.equal(_get_1, null));
                            _and_1 = (_equals_2 && _notEquals_6);
                        }
                        final boolean isSigma = _and_1;
                        boolean _or = false;
                        if (isOmega) {
                            _or = true;
                        } else {
                            _or = (isOmega || isSigma);
                        }
                        if (_or) {
                            AnyExpression _expression_3 = p.getExpression();
                            String _str = this.toStr(_expression_3);
                            String _plus_2 = (result + _str);
                            String _plus_3 = (_plus_2 + " ");
                            result = _plus_3;
                            String _plus_4 = (result + "; ");
                            String _identifier_5 = p.getIdentifier();
                            String _plus_5 = (_plus_4 + _identifier_5);
                            String _plus_6 = (_plus_5 + "\n");
                            result = _plus_6;
                            int _plus_7 = (k + 1);
                            k = _plus_7;
                        }
                    } else {
                        boolean _equals_3 = result.equals("");
                        boolean _not = (!_equals_3);
                        if (_not) {
                            AnyExpression _expression_4 = p.getExpression();
                            String _str_1 = this.toStr(_expression_4);
                            String _plus_8 = (result + _str_1);
                            String _plus_9 = (_plus_8 + " ");
                            result = _plus_9;
                        }
                    }
                }
            }
        }
        boolean _and_2 = false;
        if (!printFix) {
            _and_2 = false;
        } else {
            boolean _equals_4 = result.equals("");
            boolean _not_1 = (!_equals_4);
            _and_2 = (printFix && _not_1);
        }
        if (_and_2) {
            String _plus_10 = (result + "FIX\n");
            result = _plus_10;
        }
        boolean _equals_5 = result.equals("");
        if (_equals_5) {
            return "";
        }
        StringConcatenation _builder = new StringConcatenation();
        _builder.newLine();
        _builder.append(section, "");
        _builder.append(" ");
        {
            boolean _greaterThan = (k > 0);
            if (_greaterThan) {
                _builder.append("BLOCK (");
                _builder.append(k, "");
                _builder.append(") ");
            }
        }
        _builder.newLineIfNotEmpty();
        _builder.append(result, "");
        _builder.newLineIfNotEmpty();
        return _builder.toString();
    }

    public String printVariabilityParameter(final ParameterDeclaration s, final String section) {
        String name = s.getIdentifier();
        boolean _and = false;
        boolean _equals = section.equals("$OMEGA");
        if (!_equals) {
            _and = false;
        } else {
            String _plus = ("eta_" + name);
            Object _get = this.eta_vars.get(_plus);
            boolean _notEquals = (!Objects.equal(_get, null));
            _and = (_equals && _notEquals);
        }
        boolean isOmega = _and;
        boolean _and_1 = false;
        boolean _equals_1 = section.equals("$SIGMA");
        if (!_equals_1) {
            _and_1 = false;
        } else {
            String _plus_1 = ("eps_" + name);
            Object _get_1 = this.eps_vars.get(_plus_1);
            boolean _notEquals_1 = (!Objects.equal(_get_1, null));
            _and_1 = (_equals_1 && _notEquals_1);
        }
        boolean isSigma = _and_1;
        boolean _or = false;
        if (isOmega) {
            _or = true;
        } else {
            _or = (isOmega || isSigma);
        }
        if (_or) {
            org.ddmore.mdl.mdl.List _list = s.getList();
            Arguments _arguments = _list.getArguments();
            final String value = this.getAttribute(_arguments, "value");
            org.ddmore.mdl.mdl.List _list_1 = s.getList();
            Arguments _arguments_1 = _list_1.getArguments();
            final boolean printFix = this.isAttributeTrue(_arguments_1, "fix");
            StringConcatenation _builder = new StringConcatenation();
            {
                boolean _equals_2 = value.equals("");
                boolean _not = (!_equals_2);
                if (_not) {
                    _builder.append(value, "");
                    {
                        if (printFix) {
                            _builder.append(" FIX");
                        }
                    }
                }
            }
            _builder.append(" ; ");
            _builder.append(name, "");
            _builder.newLineIfNotEmpty();
            return _builder.toString();
        }
        return "";
    }

    public String printTheta(final ParameterDeclaration s) {
        String _xifexpression = null;
        org.ddmore.mdl.mdl.List _list = s.getList();
        boolean _notEquals = (!Objects.equal(_list, null));
        if (_notEquals) {
            String name = s.getIdentifier();
            org.ddmore.mdl.mdl.List _list_1 = s.getList();
            Arguments _arguments = _list_1.getArguments();
            final String value = this.getAttribute(_arguments, "value");
            org.ddmore.mdl.mdl.List _list_2 = s.getList();
            Arguments _arguments_1 = _list_2.getArguments();
            final String lo = this.getAttribute(_arguments_1, "lo");
            org.ddmore.mdl.mdl.List _list_3 = s.getList();
            Arguments _arguments_2 = _list_3.getArguments();
            final String hi = this.getAttribute(_arguments_2, "hi");
            org.ddmore.mdl.mdl.List _list_4 = s.getList();
            Arguments _arguments_3 = _list_4.getArguments();
            final boolean printFix = this.isAttributeTrue(_arguments_3, "fix");
            boolean _equals = value.equals("");
            if (_equals) {
                return "";
            }
            boolean _and = false;
            boolean _equals_1 = lo.equals("");
            if (!_equals_1) {
                _and = false;
            } else {
                boolean _equals_2 = hi.equals("");
                _and = (_equals_1 && _equals_2);
            }
            if (_and) {
                StringConcatenation _builder = new StringConcatenation();
                _builder.append(value, "");
                {
                    if (printFix) {
                        _builder.append(" FIX");
                    }
                }
                _builder.append(" ; ");
                _builder.append(name, "");
                return _builder.toString();
            }
            boolean _equals_3 = lo.equals("");
            if (_equals_3) {
                StringConcatenation _builder_1 = new StringConcatenation();
                _builder_1.append("(-INF, ");
                _builder_1.append(value, "");
                _builder_1.append(", ");
                _builder_1.append(hi, "");
                _builder_1.append(")");
                {
                    if (printFix) {
                        _builder_1.append(" FIX");
                    }
                }
                _builder_1.append(" ; ");
                _builder_1.append(name, "");
                return _builder_1.toString();
            }
            boolean _equals_4 = hi.equals("");
            if (_equals_4) {
                StringConcatenation _builder_2 = new StringConcatenation();
                _builder_2.append("(");
                _builder_2.append(lo, "");
                _builder_2.append(", ");
                _builder_2.append(value, "");
                _builder_2.append(", INF)");
                {
                    if (printFix) {
                        _builder_2.append(" FIX");
                    }
                }
                _builder_2.append(" ; ");
                _builder_2.append(name, "");
                return _builder_2.toString();
            }
            StringConcatenation _builder_3 = new StringConcatenation();
            _builder_3.append("(");
            _builder_3.append(lo, "");
            _builder_3.append(", ");
            _builder_3.append(value, "");
            _builder_3.append(", ");
            _builder_3.append(hi, "");
            _builder_3.append(")");
            {
                if (printFix) {
                    _builder_3.append(" FIX");
                }
            }
            _builder_3.append(" ; ");
            _builder_3.append(name, "");
            return _builder_3.toString();
        }
        return _xifexpression;
    }

    public CharSequence convertToNMTRAN(final DataObject d, final List<TaskObject> t) {
        StringConcatenation _builder = new StringConcatenation();
        CharSequence _printINPUT = this.printINPUT(d, t);
        _builder.append(_printINPUT, "");
        _builder.newLineIfNotEmpty();
        CharSequence _printDATA = this.printDATA(d);
        _builder.append(_printDATA, "");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public CharSequence printINPUT(final DataObject d, final List<TaskObject> t) {
        StringConcatenation _builder = new StringConcatenation();
        {
            boolean _isHeaderDefined = this.isHeaderDefined(d);
            if (_isHeaderDefined) {
                _builder.append("\t");
                _builder.newLine();
                _builder.append("$INPUT");
                _builder.newLine();
            }
        }
        String _externalCodeStart = this.getExternalCodeStart("$INPUT");
        _builder.append(_externalCodeStart, "");
        _builder.newLineIfNotEmpty();
        {
            EList<DataObjectBlock> _blocks = d.getBlocks();
            for (final DataObjectBlock b : _blocks) {
                {
                    HeaderBlock _headerBlock = b.getHeaderBlock();
                    boolean _notEquals = (!Objects.equal(_headerBlock, null));
                    if (_notEquals) {
                        {
                            HeaderBlock _headerBlock_1 = b.getHeaderBlock();
                            EList<SymbolModification> _variables = _headerBlock_1.getVariables();
                            boolean _hasElements = false;
                            for (final SymbolModification st : _variables) {
                                if (!_hasElements) {
                                    _hasElements = true;
                                } else {
                                    _builder.appendImmediate(" ", "");
                                }
                                {
                                    FullyQualifiedSymbolName _identifier = st.getIdentifier();
                                    boolean _isDrop = this.isDrop(_identifier, t);
                                    if (_isDrop) {
                                        FullyQualifiedSymbolName _identifier_1 = st.getIdentifier();
                                        String _str = this.toStr(_identifier_1);
                                        _builder.append(_str, "");
                                        _builder.append("=DROP");
                                    } else {
                                        FullyQualifiedSymbolName _identifier_2 = st.getIdentifier();
                                        String _str_1 = this.toStr(_identifier_2);
                                        _builder.append(_str_1, "");
                                    }
                                }
                            }
                        }
                        _builder.newLineIfNotEmpty();
                    }
                }
            }
        }
        String _externalCodeEnd = this.getExternalCodeEnd("$INPUT");
        _builder.append(_externalCodeEnd, "");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public boolean isDrop(final FullyQualifiedSymbolName id, final List<TaskObject> t) {
        for (final TaskObject tObj : t) {
            EList<TaskObjectBlock> _blocks = tObj.getBlocks();
            for (final TaskObjectBlock b : _blocks) {
                DataBlock _dataBlock = b.getDataBlock();
                boolean _notEquals = (!Objects.equal(_dataBlock, null));
                if (_notEquals) {
                    DataBlock _dataBlock_1 = b.getDataBlock();
                    EList<DataBlockStatement> _statements = _dataBlock_1.getStatements();
                    for (final DataBlockStatement block : _statements) {
                        DropList _dropList = block.getDropList();
                        boolean _notEquals_1 = (!Objects.equal(_dropList, null));
                        if (_notEquals_1) {
                            DropList _dropList_1 = block.getDropList();
                            SymbolList _list = _dropList_1.getList();
                            EList<FullyQualifiedSymbolName> _symbols = _list.getSymbols();
                            for (final FullyQualifiedSymbolName symbol : _symbols) {
                                String _identifier = id.getIdentifier();
                                String _identifier_1 = symbol.getIdentifier();
                                boolean _equals = _identifier.equals(_identifier_1);
                                if (_equals) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public CharSequence printDATA(final DataObject o) {
        StringConcatenation _builder = new StringConcatenation();
        {
            EList<DataObjectBlock> _blocks = o.getBlocks();
            for (final DataObjectBlock b : _blocks) {
                {
                    FileBlock _fileBlock = b.getFileBlock();
                    boolean _notEquals = (!Objects.equal(_fileBlock, null));
                    if (_notEquals) {
                        FileBlock _fileBlock_1 = b.getFileBlock();
                        String data = this.getData(_fileBlock_1);
                        _builder.newLineIfNotEmpty();
                        {
                            boolean _equals = data.equals("");
                            boolean _not = (!_equals);
                            if (_not) {
                                _builder.newLine();
                                _builder.append("$DATA ");
                                _builder.append(data, "");
                                _builder.newLineIfNotEmpty();
                            }
                        }
                        String _externalCodeStart = this.getExternalCodeStart("$DATA");
                        _builder.append(_externalCodeStart, "");
                        _builder.newLineIfNotEmpty();
                        {
                            FileBlock _fileBlock_2 = b.getFileBlock();
                            EList<FileBlockStatement> _statements = _fileBlock_2.getStatements();
                            for (final FileBlockStatement s : _statements) {
                                String _printIGNORE = this.printIGNORE(s);
                                _builder.append(_printIGNORE, "");
                                _builder.newLineIfNotEmpty();
                            }
                        }
                        String _externalCodeEnd = this.getExternalCodeEnd("$DATA");
                        _builder.append(_externalCodeEnd, "");
                        _builder.newLineIfNotEmpty();
                    }
                }
            }
        }
        return _builder;
    }

    public String printIGNORE(final FileBlockStatement s) {
        String _xifexpression = null;
        SymbolDeclaration _variable = s.getVariable();
        boolean _notEquals = (!Objects.equal(_variable, null));
        if (_notEquals) {
            String _xifexpression_1 = null;
            SymbolDeclaration _variable_1 = s.getVariable();
            String _identifier = _variable_1.getIdentifier();
            boolean _equals = _identifier.equals("data");
            if (_equals) {
                String _xifexpression_2 = null;
                SymbolDeclaration _variable_2 = s.getVariable();
                AnyExpression _expression = _variable_2.getExpression();
                boolean _notEquals_1 = (!Objects.equal(_expression, null));
                if (_notEquals_1) {
                    String _xifexpression_3 = null;
                    SymbolDeclaration _variable_3 = s.getVariable();
                    AnyExpression _expression_1 = _variable_3.getExpression();
                    org.ddmore.mdl.mdl.List _list = _expression_1.getList();
                    boolean _notEquals_2 = (!Objects.equal(_list, null));
                    if (_notEquals_2) {
                        SymbolDeclaration _variable_4 = s.getVariable();
                        AnyExpression _expression_2 = _variable_4.getExpression();
                        org.ddmore.mdl.mdl.List _list_1 = _expression_2.getList();
                        Arguments _arguments = _list_1.getArguments();
                        String ignore = this.getAttribute(_arguments, "ignore");
                        StringConcatenation _builder = new StringConcatenation();
                        {
                            boolean _equals_1 = ignore.equals("");
                            boolean _not = (!_equals_1);
                            if (_not) {
                                _builder.append("IGNORE=");
                                _builder.append(ignore, "");
                            }
                        }
                        return _builder.toString();
                    }
                    _xifexpression_2 = _xifexpression_3;
                }
                _xifexpression_1 = _xifexpression_2;
            }
            _xifexpression = _xifexpression_1;
        }
        return _xifexpression;
    }

    public String getData(final FileBlock b) {
        EList<FileBlockStatement> _statements = b.getStatements();
        for (final FileBlockStatement s : _statements) {
            SymbolDeclaration _variable = s.getVariable();
            String _identifier = _variable.getIdentifier();
            boolean _equals = _identifier.equals("data");
            if (_equals) {
                SymbolDeclaration _variable_1 = s.getVariable();
                AnyExpression _expression = _variable_1.getExpression();
                boolean _notEquals = (!Objects.equal(_expression, null));
                if (_notEquals) {
                    SymbolDeclaration _variable_2 = s.getVariable();
                    AnyExpression _expression_1 = _variable_2.getExpression();
                    org.ddmore.mdl.mdl.List _list = _expression_1.getList();
                    boolean _notEquals_1 = (!Objects.equal(_list, null));
                    if (_notEquals_1) {
                        SymbolDeclaration _variable_3 = s.getVariable();
                        AnyExpression _expression_2 = _variable_3.getExpression();
                        org.ddmore.mdl.mdl.List _list_1 = _expression_2.getList();
                        Arguments _arguments = _list_1.getArguments();
                        String data = this.getAttribute(_arguments, "source");
                        return data;
                    }
                }
            }
        }
        return null;
    }

    public CharSequence convertToNMTRAN(final TaskObject o) {
        StringConcatenation _builder = new StringConcatenation();
        {
            EList<TaskObjectBlock> _blocks = o.getBlocks();
            for (final TaskObjectBlock b : _blocks) {
                {
                    TaskFunctionDeclaration _functionDeclaration = b.getFunctionDeclaration();
                    boolean _notEquals = (!Objects.equal(_functionDeclaration, null));
                    if (_notEquals) {
                        TaskFunctionDeclaration _functionDeclaration_1 = b.getFunctionDeclaration();
                        final TaskFunctionBody body = _functionDeclaration_1.getFunctionBody();
                        _builder.newLineIfNotEmpty();
                        {
                            boolean _notEquals_1 = (!Objects.equal(body, null));
                            if (_notEquals_1) {
                                {
                                    EList<TaskFunctionBlock> _blocks_1 = body.getBlocks();
                                    for (final TaskFunctionBlock bb : _blocks_1) {
                                        {
                                            EstimateTask _estimateBlock = bb.getEstimateBlock();
                                            boolean _notEquals_2 = (!Objects.equal(_estimateBlock, null));
                                            if (_notEquals_2) {
                                                EstimateTask _estimateBlock_1 = bb.getEstimateBlock();
                                                CharSequence _print = this.print(_estimateBlock_1);
                                                _builder.append(_print, "");
                                                _builder.newLineIfNotEmpty();
                                            }
                                        }
                                        {
                                            SimulateTask _simulateBlock = bb.getSimulateBlock();
                                            boolean _notEquals_3 = (!Objects.equal(_simulateBlock, null));
                                            if (_notEquals_3) {
                                                SimulateTask _simulateBlock_1 = bb.getSimulateBlock();
                                                CharSequence _print_1 = this.print(_simulateBlock_1);
                                                _builder.append(_print_1, "");
                                                _builder.newLineIfNotEmpty();
                                            }
                                        }
                                        {
                                            ExecuteTask _executeBlock = bb.getExecuteBlock();
                                            boolean _notEquals_4 = (!Objects.equal(_executeBlock, null));
                                            if (_notEquals_4) {
                                                ExecuteTask _executeBlock_1 = bb.getExecuteBlock();
                                                CharSequence _print_2 = this.print(_executeBlock_1);
                                                _builder.append(_print_2, "");
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
        return _builder;
    }

    public CharSequence printIGNORE(final TaskObject o) {
        StringConcatenation _builder = new StringConcatenation();
        {
            EList<TaskObjectBlock> _blocks = o.getBlocks();
            for (final TaskObjectBlock b : _blocks) {
                {
                    DataBlock _dataBlock = b.getDataBlock();
                    boolean _notEquals = (!Objects.equal(_dataBlock, null));
                    if (_notEquals) {
                        {
                            DataBlock _dataBlock_1 = b.getDataBlock();
                            EList<DataBlockStatement> _statements = _dataBlock_1.getStatements();
                            for (final DataBlockStatement block : _statements) {
                                {
                                    IgnoreList _ignoreList = block.getIgnoreList();
                                    boolean _notEquals_1 = (!Objects.equal(_ignoreList, null));
                                    if (_notEquals_1) {
                                        IgnoreList _ignoreList_1 = block.getIgnoreList();
                                        String _identifier = _ignoreList_1.getIdentifier();
                                        _builder.append(_identifier, "");
                                        _builder.append(" (");
                                        IgnoreList _ignoreList_2 = block.getIgnoreList();
                                        OrExpression _expression = _ignoreList_2.getExpression();
                                        String _commaSeparatedStr = this.toCommaSeparatedStr(_expression);
                                        _builder.append(_commaSeparatedStr, "");
                                        _builder.append(")");
                                        _builder.newLineIfNotEmpty();
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

    public CharSequence print(final SimulateTask b) {
        StringConcatenation _builder = new StringConcatenation();
        EList<BlockStatement> _statements = b.getStatements();
        boolean isInlineTargetDefined = this.isInlineTargetDefined(this.TARGET, _statements);
        _builder.newLineIfNotEmpty();
        {
            boolean _not = (!isInlineTargetDefined);
            if (_not) {
                _builder.newLine();
                _builder.append("$SIM ");
                _builder.newLine();
            }
        }
        String _externalCodeStart = this.getExternalCodeStart("$SIM");
        _builder.append(_externalCodeStart, "");
        _builder.newLineIfNotEmpty();
        String _externalCodeStart_1 = this.getExternalCodeStart("$SIMULATION");
        _builder.append(_externalCodeStart_1, "");
        _builder.newLineIfNotEmpty();
        {
            boolean _not_1 = (!isInlineTargetDefined);
            if (_not_1) {
                {
                    EList<BlockStatement> _statements_1 = b.getStatements();
                    for (final BlockStatement s : _statements_1) {
                        {
                            SymbolDeclaration _symbol = s.getSymbol();
                            boolean _notEquals = (!Objects.equal(_symbol, null));
                            if (_notEquals) {
                                SymbolDeclaration _symbol_1 = s.getSymbol();
                                CharSequence _printDefaultSimulate = this.printDefaultSimulate(_symbol_1);
                                _builder.append(_printDefaultSimulate, "");
                            }
                        }
                        _builder.newLineIfNotEmpty();
                    }
                }
                _builder.append("NOABORT");
                _builder.newLine();
            } else {
                {
                    EList<BlockStatement> _statements_2 = b.getStatements();
                    for (final BlockStatement s_1 : _statements_2) {
                        {
                            TargetBlock _targetBlock = s_1.getTargetBlock();
                            boolean _notEquals_1 = (!Objects.equal(_targetBlock, null));
                            if (_notEquals_1) {
                                TargetBlock _targetBlock_1 = s_1.getTargetBlock();
                                CharSequence _print = this.print(_targetBlock_1);
                                _builder.append(_print, "");
                            }
                        }
                        _builder.newLineIfNotEmpty();
                    }
                }
            }
        }
        String _externalCodeEnd = this.getExternalCodeEnd("$SIM");
        _builder.append(_externalCodeEnd, "");
        _builder.newLineIfNotEmpty();
        String _externalCodeEnd_1 = this.getExternalCodeEnd("$SIMULATION");
        _builder.append(_externalCodeEnd_1, "");
        _builder.append("\t");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public CharSequence print(final EstimateTask b) {
        StringConcatenation _builder = new StringConcatenation();
        EList<BlockStatement> _statements = b.getStatements();
        boolean isInlineTargetDefined = this.isInlineTargetDefined(this.TARGET, _statements);
        _builder.newLineIfNotEmpty();
        {
            boolean _not = (!isInlineTargetDefined);
            if (_not) {
                _builder.newLine();
                _builder.append("$EST ");
                _builder.newLine();
            }
        }
        String _externalCodeStart = this.getExternalCodeStart("$EST");
        _builder.append(_externalCodeStart, "");
        _builder.newLineIfNotEmpty();
        String _externalCodeStart_1 = this.getExternalCodeStart("$ESTIMATION");
        _builder.append(_externalCodeStart_1, "");
        _builder.newLineIfNotEmpty();
        {
            boolean _not_1 = (!isInlineTargetDefined);
            if (_not_1) {
                {
                    EList<BlockStatement> _statements_1 = b.getStatements();
                    for (final BlockStatement s : _statements_1) {
                        {
                            SymbolDeclaration _symbol = s.getSymbol();
                            boolean _notEquals = (!Objects.equal(_symbol, null));
                            if (_notEquals) {
                                SymbolDeclaration _symbol_1 = s.getSymbol();
                                CharSequence _printDefaultEstimate = this.printDefaultEstimate(_symbol_1);
                                _builder.append(_printDefaultEstimate, "");
                            }
                        }
                        _builder.newLineIfNotEmpty();
                    }
                }
                _builder.append("NOABORT ");
                _builder.newLine();
                CharSequence _printCovariance = this.printCovariance(b);
                _builder.append(_printCovariance, "");
                _builder.newLineIfNotEmpty();
            } else {
                {
                    EList<BlockStatement> _statements_2 = b.getStatements();
                    for (final BlockStatement s_1 : _statements_2) {
                        {
                            TargetBlock _targetBlock = s_1.getTargetBlock();
                            boolean _notEquals_1 = (!Objects.equal(_targetBlock, null));
                            if (_notEquals_1) {
                                TargetBlock _targetBlock_1 = s_1.getTargetBlock();
                                CharSequence _print = this.print(_targetBlock_1);
                                _builder.append(_print, "");
                            }
                        }
                        _builder.newLineIfNotEmpty();
                    }
                }
            }
        }
        String _externalCodeEnd = this.getExternalCodeEnd("$EST");
        _builder.append(_externalCodeEnd, "");
        _builder.newLineIfNotEmpty();
        String _externalCodeEnd_1 = this.getExternalCodeEnd("$ESTIMATION");
        _builder.append(_externalCodeEnd_1, "");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public CharSequence printDefaultEstimate(final SymbolDeclaration s) {
        CharSequence _xifexpression = null;
        String _identifier = s.getIdentifier();
        boolean _equals = _identifier.equals("algo");
        if (_equals) {
            CharSequence _xifexpression_1 = null;
            AnyExpression _expression = s.getExpression();
            Expression _expression_1 = _expression.getExpression();
            boolean _notEquals = (!Objects.equal(_expression_1, null));
            if (_notEquals) {
                StringConcatenation _builder = new StringConcatenation();
                _builder.append(" ");
                _builder.append("METHOD=");
                AnyExpression _expression_2 = s.getExpression();
                Expression _expression_3 = _expression_2.getExpression();
                String _str = this.toStr(_expression_3);
                _builder.append(_str, " ");
                _xifexpression_1 = _builder;
            } else {
                CharSequence _xifexpression_2 = null;
                AnyExpression _expression_4 = s.getExpression();
                org.ddmore.mdl.mdl.List _list = _expression_4.getList();
                boolean _notEquals_1 = (!Objects.equal(_list, null));
                if (_notEquals_1) {
                    CharSequence _xblockexpression = null;
                    {
                        AnyExpression _expression_5 = s.getExpression();
                        org.ddmore.mdl.mdl.List _list_1 = _expression_5.getList();
                        Arguments args = _list_1.getArguments();
                        CharSequence _xifexpression_3 = null;
                        boolean _notEquals_2 = (!Objects.equal(args, null));
                        if (_notEquals_2) {
                            CharSequence _xifexpression_4 = null;
                            EList<Argument> _arguments = args.getArguments();
                            int _size = _arguments.size();
                            boolean _greaterThan = (_size > 0);
                            if (_greaterThan) {
                                StringConcatenation _builder_1 = new StringConcatenation();
                                _builder_1.append(" ");
                                _builder_1.append("METHOD=");
                                EList<Argument> _arguments_1 = args.getArguments();
                                Argument _get = _arguments_1.get(0);
                                AnyExpression _expression_6 = _get.getExpression();
                                String _str_1 = this.toStr(_expression_6);
                                _builder_1.append(_str_1, " ");
                                _xifexpression_4 = _builder_1;
                            }
                            _xifexpression_3 = _xifexpression_4;
                        }
                        _xblockexpression = (_xifexpression_3);
                    }
                    _xifexpression_2 = _xblockexpression;
                }
                _xifexpression_1 = _xifexpression_2;
            }
            _xifexpression = _xifexpression_1;
        } else {
            CharSequence _xifexpression_3 = null;
            String _identifier_1 = s.getIdentifier();
            boolean _equals_1 = _identifier_1.equals("max");
            if (_equals_1) {
                StringConcatenation _builder_1 = new StringConcatenation();
                _builder_1.append(" ");
                _builder_1.append("MAX=");
                AnyExpression _expression_5 = s.getExpression();
                CharSequence _print = this.print(_expression_5);
                _builder_1.append(_print, " ");
                _xifexpression_3 = _builder_1;
            } else {
                CharSequence _xifexpression_4 = null;
                String _identifier_2 = s.getIdentifier();
                boolean _equals_2 = _identifier_2.equals("sig");
                if (_equals_2) {
                    StringConcatenation _builder_2 = new StringConcatenation();
                    _builder_2.append(" ");
                    _builder_2.append("SIG=");
                    AnyExpression _expression_6 = s.getExpression();
                    CharSequence _print_1 = this.print(_expression_6);
                    _builder_2.append(_print_1, " ");
                    _xifexpression_4 = _builder_2;
                }
                _xifexpression_3 = _xifexpression_4;
            }
            _xifexpression = _xifexpression_3;
        }
        return _xifexpression;
    }

    public CharSequence printDefaultSimulate(final SymbolDeclaration s) {
        StringConcatenation _builder = new StringConcatenation();
        return _builder;
    }

    public CharSequence printCovariance(final EstimateTask b) {
        StringConcatenation _builder = new StringConcatenation();
        {
            boolean _isCovarianceDefined = this.isCovarianceDefined(b);
            if (_isCovarianceDefined) {
                _builder.newLine();
                _builder.append("$COV ");
                _builder.newLine();
            }
        }
        String _externalCodeStart = this.getExternalCodeStart("$COV");
        _builder.append(_externalCodeStart, "");
        _builder.newLineIfNotEmpty();
        String _externalCodeStart_1 = this.getExternalCodeStart("$COVARIANCE");
        _builder.append(_externalCodeStart_1, "");
        _builder.newLineIfNotEmpty();
        {
            EList<BlockStatement> _statements = b.getStatements();
            for (final BlockStatement s : _statements) {
                {
                    SymbolDeclaration _symbol = s.getSymbol();
                    boolean _notEquals = (!Objects.equal(_symbol, null));
                    if (_notEquals) {
                        SymbolDeclaration _symbol_1 = s.getSymbol();
                        CharSequence _printCovariance = this.printCovariance(_symbol_1);
                        _builder.append(_printCovariance, "");
                    }
                }
            }
        }
        _builder.newLineIfNotEmpty();
        String _externalCodeEnd = this.getExternalCodeEnd("$COV");
        _builder.append(_externalCodeEnd, "");
        _builder.newLineIfNotEmpty();
        String _externalCodeEnd_1 = this.getExternalCodeEnd("$COVARIANCE");
        _builder.append(_externalCodeEnd_1, "");
        _builder.newLineIfNotEmpty();
        return _builder;
    }

    public CharSequence printCovariance(final SymbolDeclaration s) {
        CharSequence _xifexpression = null;
        String _identifier = s.getIdentifier();
        boolean _equals = _identifier.equals("cov");
        if (_equals) {
            CharSequence _xifexpression_1 = null;
            AnyExpression _expression = s.getExpression();
            boolean _notEquals = (!Objects.equal(_expression, null));
            if (_notEquals) {
                CharSequence _xifexpression_2 = null;
                AnyExpression _expression_1 = s.getExpression();
                String _str = this.toStr(_expression_1);
                String _replaceAll = _str.replaceAll("\\s", "");
                boolean _equals_1 = _replaceAll.equals("");
                if (_equals_1) {
                    StringConcatenation _builder = new StringConcatenation();
                    AnyExpression _expression_2 = s.getExpression();
                    CharSequence _print = this.print(_expression_2);
                    _builder.append(_print, "");
                    _xifexpression_2 = _builder;
                }
                _xifexpression_1 = _xifexpression_2;
            }
            _xifexpression = _xifexpression_1;
        }
        return _xifexpression;
    }

    public CharSequence print(final ExecuteTask b) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.newLine();
        {
            EList<BlockStatement> _statements = b.getStatements();
            for (final BlockStatement s : _statements) {
                {
                    TargetBlock _targetBlock = s.getTargetBlock();
                    boolean _notEquals = (!Objects.equal(_targetBlock, null));
                    if (_notEquals) {
                        TargetBlock _targetBlock_1 = s.getTargetBlock();
                        CharSequence _print = this.print(_targetBlock_1);
                        _builder.append(_print, "");
                    }
                }
                _builder.newLineIfNotEmpty();
            }
        }
        return _builder;
    }

    public String getTaskObjectName(final Resource resource) {
        TreeIterator<EObject> _allContents = resource.getAllContents();
        Iterable<EObject> _iterable = IteratorExtensions.<EObject> toIterable(_allContents);
        Iterable<Mcl> _filter = Iterables.<Mcl> filter(_iterable, Mcl.class);
        for (final Mcl m : _filter) {
            EList<MclObject> _objects = m.getObjects();
            for (final MclObject obj : _objects) {
                TaskObject _taskObject = obj.getTaskObject();
                boolean _notEquals = (!Objects.equal(_taskObject, null));
                if (_notEquals) {
                    ObjectName _identifier = obj.getIdentifier();
                    return _identifier.getName();
                }
            }
        }
        return "";
    }

    public String getTOL(final Resource resource) {
        TreeIterator<EObject> _allContents = resource.getAllContents();
        Iterable<EObject> _iterable = IteratorExtensions.<EObject> toIterable(_allContents);
        Iterable<Mcl> _filter = Iterables.<Mcl> filter(_iterable, Mcl.class);
        for (final Mcl m : _filter) {
            EList<MclObject> _objects = m.getObjects();
            for (final MclObject obj : _objects) {
                TaskObject _taskObject = obj.getTaskObject();
                boolean _notEquals = (!Objects.equal(_taskObject, null));
                if (_notEquals) {
                    TaskObject _taskObject_1 = obj.getTaskObject();
                    final String tol = this.getTOL(_taskObject_1);
                    int _length = tol.length();
                    boolean _greaterThan = (_length > 0);
                    if (_greaterThan) {
                        return tol;
                    }
                }
            }
        }
        return "";
    }

    public String getTOL(final TaskObject obj) {
        EList<TaskObjectBlock> _blocks = obj.getBlocks();
        for (final TaskObjectBlock b : _blocks) {
            ModelBlock _modelBlock = b.getModelBlock();
            boolean _notEquals = (!Objects.equal(_modelBlock, null));
            if (_notEquals) {
                ModelBlock _modelBlock_1 = b.getModelBlock();
                EList<ModelBlockStatement> _statements = _modelBlock_1.getStatements();
                for (final ModelBlockStatement ss : _statements) {
                    {
                        BlockStatement _statement = ss.getStatement();
                        SymbolDeclaration x = _statement.getSymbol();
                        boolean _notEquals_1 = (!Objects.equal(x, null));
                        if (_notEquals_1) {
                            String _identifier = x.getIdentifier();
                            boolean _equals = _identifier.equals("tolrel");
                            if (_equals) {
                                AnyExpression _expression = x.getExpression();
                                boolean _notEquals_2 = (!Objects.equal(_expression, null));
                                if (_notEquals_2) {
                                    AnyExpression _expression_1 = x.getExpression();
                                    return this.toStr(_expression_1);
                                }
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    public String convertID(final String id) {
        int _indexOf = id.indexOf("_");
        boolean _greaterThan = (_indexOf > 0);
        if (_greaterThan) {
            Object _get = this.eta_vars.get(id);
            boolean _notEquals = (!Objects.equal(_get, null));
            if (_notEquals) {
                Object _get_1 = this.eta_vars.get(id);
                String _plus = ("ETA(" + _get_1);
                return (_plus + ")");
            }
            Object _get_2 = this.eps_vars.get(id);
            boolean _notEquals_1 = (!Objects.equal(_get_2, null));
            if (_notEquals_1) {
                Object _get_3 = this.eps_vars.get(id);
                String _plus_1 = ("EPS(" + _get_3);
                return (_plus_1 + ")");
            }
            Object _get_4 = this.theta_vars.get(id);
            boolean _notEquals_2 = (!Objects.equal(_get_4, null));
            if (_notEquals_2) {
                Object _get_5 = this.theta_vars.get(id);
                String _plus_2 = ("THETA(" + _get_5);
                return (_plus_2 + ")");
            }
        }
        Object _get_6 = this.dadt_vars.get(id);
        boolean _notEquals_3 = (!Objects.equal(_get_6, null));
        if (_notEquals_3) {
            Object _get_7 = this.dadt_vars.get(id);
            String _plus_3 = ("A(" + _get_7);
            return (_plus_3 + ")");
        }
        boolean _equalsIgnoreCase = id.equalsIgnoreCase("ln");
        if (_equalsIgnoreCase) {
            return "LOG";
        }
        return id.toUpperCase();
    }

    public String convertOperator(final String op) {
        boolean _equals = op.equals("<");
        if (_equals) {
            return ".LT.";
        }
        boolean _equals_1 = op.equals(">");
        if (_equals_1) {
            return ".GT.";
        }
        boolean _equals_2 = op.equals("<=");
        if (_equals_2) {
            return ".LE.";
        }
        boolean _equals_3 = op.equals(">=");
        if (_equals_3) {
            return ".GE.";
        }
        boolean _equals_4 = op.equals("==");
        if (_equals_4) {
            return ".EQ.";
        }
        boolean _equals_5 = op.equals("!=");
        if (_equals_5) {
            return ".NE.";
        }
        boolean _equals_6 = op.equals("^");
        if (_equals_6) {
            return "**";
        }
        boolean _equals_7 = op.equals("||");
        if (_equals_7) {
            return ".OR.";
        }
        boolean _equals_8 = op.equals("&&");
        if (_equals_8) {
            return ".AND.";
        }
        return op;
    }

    public String toCommaSeparatedStr(final OrExpression e) {
        String res = "";
        EList<AndExpression> _expression = e.getExpression();
        Iterator<AndExpression> iterator = _expression.iterator();
        boolean _hasNext = iterator.hasNext();
        if (_hasNext) {
            AndExpression _next = iterator.next();
            String _str = this.toStr(_next);
            res = _str;
        }
        boolean _hasNext_1 = iterator.hasNext();
        boolean _while = _hasNext_1;
        while (_while) {
            String _plus = (res + ", ");
            AndExpression _next_1 = iterator.next();
            String _str_1 = this.toStr(_next_1);
            String _plus_1 = (_plus + _str_1);
            res = _plus_1;
            boolean _hasNext_2 = iterator.hasNext();
            _while = _hasNext_2;
        }
        return res;
    }

    public String toCommaSeparatedStr(final AndExpression e) {
        String res = "";
        EList<LogicalExpression> _expression = e.getExpression();
        Iterator<LogicalExpression> iterator = _expression.iterator();
        boolean _hasNext = iterator.hasNext();
        if (_hasNext) {
            LogicalExpression _next = iterator.next();
            String _str = this.toStr(_next);
            res = _str;
        }
        boolean _hasNext_1 = iterator.hasNext();
        boolean _while = _hasNext_1;
        while (_while) {
            String _plus = (res + ", ");
            LogicalExpression _next_1 = iterator.next();
            String _str_1 = this.toStr(_next_1);
            String _plus_1 = (_plus + _str_1);
            res = _plus_1;
            boolean _hasNext_2 = iterator.hasNext();
            _while = _hasNext_2;
        }
        return res;
    }

    public CharSequence print(final TargetBlock b) {
        CharSequence _xblockexpression = null;
        {
            String target = "";
            Arguments _arguments = b.getArguments();
            boolean _notEquals = (!Objects.equal(_arguments, null));
            if (_notEquals) {
                Arguments _arguments_1 = b.getArguments();
                String _attribute = this.getAttribute(_arguments_1, "target");
                target = _attribute;
            }
            CharSequence _xifexpression = null;
            boolean _equals = target.equals(this.TARGET);
            if (_equals) {
                StringConcatenation _builder = new StringConcatenation();
                String _externalCode = b.getExternalCode();
                String _externalCode_1 = b.getExternalCode();
                int _length = _externalCode_1.length();
                int _minus = (_length - 3);
                String printedCode = _externalCode.substring(3, _minus);
                _builder.newLineIfNotEmpty();
                _builder.append(printedCode, "");
                _builder.newLineIfNotEmpty();
                _xifexpression = _builder;
            }
            _xblockexpression = (_xifexpression);
        }
        return _xblockexpression;
    }

    public CharSequence print(final ConditionalStatement s) {
        StringConcatenation _builder = new StringConcatenation();
        {
            OrExpression _expression = s.getExpression();
            boolean _notEquals = (!Objects.equal(_expression, null));
            if (_notEquals) {
                _builder.append("IF ");
                OrExpression _expression_1 = s.getExpression();
                CharSequence _print = this.print(_expression_1);
                _builder.append(_print, "");
                _builder.append(" THEN");
                _builder.newLineIfNotEmpty();
                {
                    BlockStatement _ifStatement = s.getIfStatement();
                    boolean _notEquals_1 = (!Objects.equal(_ifStatement, null));
                    if (_notEquals_1) {
                        _builder.append("\t");
                        BlockStatement _ifStatement_1 = s.getIfStatement();
                        CharSequence _print_1 = this.print(_ifStatement_1);
                        _builder.append(_print_1, " ");
                        _builder.newLineIfNotEmpty();
                    }
                }
                {
                    Block _ifBlock = s.getIfBlock();
                    boolean _notEquals_2 = (!Objects.equal(_ifBlock, null));
                    if (_notEquals_2) {
                        _builder.append("\t");
                        Block _ifBlock_1 = s.getIfBlock();
                        CharSequence _print_2 = this.print(_ifBlock_1);
                        _builder.append(_print_2, " ");
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
                        _builder.append("ELSE ");
                        _builder.newLine();
                        {
                            BlockStatement _elseStatement_1 = s.getElseStatement();
                            boolean _notEquals_5 = (!Objects.equal(_elseStatement_1, null));
                            if (_notEquals_5) {
                                _builder.append("\t");
                                BlockStatement _elseStatement_2 = s.getElseStatement();
                                CharSequence _print_3 = this.print(_elseStatement_2);
                                _builder.append(_print_3, " ");
                                _builder.newLineIfNotEmpty();
                            }
                        }
                        {
                            Block _elseBlock_1 = s.getElseBlock();
                            boolean _notEquals_6 = (!Objects.equal(_elseBlock_1, null));
                            if (_notEquals_6) {
                                _builder.append("\t");
                                Block _elseBlock_2 = s.getElseBlock();
                                CharSequence _print_4 = this.print(_elseBlock_2);
                                _builder.append(_print_4, " ");
                                _builder.newLineIfNotEmpty();
                            }
                        }
                    }
                }
                _builder.append("ENDIF");
                _builder.newLine();
            }
        }
        return _builder;
    }

    public String toStr(final LogicalExpression e) {
        String _boolean = e.getBoolean();
        boolean _notEquals = (!Objects.equal(_boolean, null));
        if (_notEquals) {
            return "";
        }
        return super.toStr(e);
    }

    public String toStr(final SymbolDeclaration v) {
        AnyExpression _expression = v.getExpression();
        boolean _notEquals = (!Objects.equal(_expression, null));
        if (_notEquals) {
            AnyExpression _expression_1 = v.getExpression();
            org.ddmore.mdl.mdl.List _list = _expression_1.getList();
            boolean _notEquals_1 = (!Objects.equal(_list, null));
            if (_notEquals_1) {
                AnyExpression _expression_2 = v.getExpression();
                org.ddmore.mdl.mdl.List _list_1 = _expression_2.getList();
                Arguments _arguments = _list_1.getArguments();
                String type = this.getAttribute(_arguments, "type");
                String res = "";
                boolean _equals = type.equals("continuous");
                if (_equals) {
                    res = "F_FLAG = 0\n";
                }
                boolean _equals_1 = type.equals("LIKELIHOOD");
                if (_equals_1) {
                    res = "F_FLAG = 1\n";
                }
                AnyExpression _expression_3 = v.getExpression();
                org.ddmore.mdl.mdl.List _list_2 = _expression_3.getList();
                String listExpr = this.toStr(_list_2);
                boolean _and = false;
                boolean _equals_2 = listExpr.equals("");
                boolean _not = (!_equals_2);
                if (!_not) {
                    _and = false;
                } else {
                    boolean _equals_3 = res.equals("");
                    boolean _not_1 = (!_equals_3);
                    _and = (_not && _not_1);
                }
                if (_and) {
                    String _function = v.getFunction();
                    boolean _notEquals_2 = (!Objects.equal(_function, null));
                    if (_notEquals_2) {
                        String _function_1 = v.getFunction();
                        String _convertID = this.convertID(_function_1);
                        String _plus = (res + _convertID);
                        String _plus_1 = (_plus + "(Y) = ");
                        String _plus_2 = (_plus_1 + listExpr);
                        return (_plus_2 + "\n");
                    }
                    String _plus_3 = (res + "Y = ");
                    String _plus_4 = (_plus_3 + listExpr);
                    return (_plus_4 + "\n");
                }
            }
        }
        return super.toStr(v);
    }

    public String toStr(final org.ddmore.mdl.mdl.List l) {
        Arguments _arguments = l.getArguments();
        String type = this.getAttribute(_arguments, "type");
        String res = "";
        boolean _equals = type.equals("LIKELIHOOD");
        if (_equals) {
            Arguments _arguments_1 = l.getArguments();
            String _attribute = this.getAttribute(_arguments_1, "likelihood");
            res = _attribute;
        } else {
            boolean _equals_1 = type.equals("continuous");
            if (_equals_1) {
                Arguments _arguments_2 = l.getArguments();
                String ruv = this.getAttribute(_arguments_2, "ruv");
                Arguments _arguments_3 = l.getArguments();
                String prediction = this.getAttribute(_arguments_3, "prediction");
                String _plus = (prediction + ruv);
                res = _plus;
            }
        }
        return res;
    }

    public void prepareExternalFunctions(final ImportBlock b, final String objName) {
        EList<ImportedFunction> _functions = b.getFunctions();
        for (final ImportedFunction f : _functions) {
            {
                HashMap<String, String> _hashMap = new HashMap<String, String>();
                HashMap<String, String> args = _hashMap;
                org.ddmore.mdl.mdl.List _list = f.getList();
                Arguments _arguments = _list.getArguments();
                String target = this.getAttribute(_arguments, "target");
                boolean _notEquals = (!Objects.equal(target, null));
                if (_notEquals) {
                    boolean _equals = target.equals(this.TARGET);
                    if (_equals) {
                        org.ddmore.mdl.mdl.List _list_1 = f.getList();
                        Arguments _arguments_1 = _list_1.getArguments();
                        EList<Argument> _arguments_2 = _arguments_1.getArguments();
                        for (final Argument arg : _arguments_2) {
                            String _identifier = arg.getIdentifier();
                            boolean _notEquals_1 = (!Objects.equal(_identifier, null));
                            if (_notEquals_1) {
                                String _identifier_1 = arg.getIdentifier();
                                AnyExpression _expression = arg.getExpression();
                                String _str = this.toStr(_expression);
                                args.put(_identifier_1, _str);
                            }
                        }
                        String _plus = (objName + "$");
                        String _identifier_2 = f.getIdentifier();
                        String _plus_1 = (_plus + _identifier_2);
                        this.externalFunctions.put(_plus_1, args);
                    }
                }
            }
        }
    }

    public void prepareExternalCode(final TargetBlock b) {
        Arguments _arguments = b.getArguments();
        final String target = this.getAttribute(_arguments, "target");
        boolean _notEquals = (!Objects.equal(target, null));
        if (_notEquals) {
            boolean _equals = target.equals(this.TARGET);
            if (_equals) {
                Arguments _arguments_1 = b.getArguments();
                final String location = this.getAttribute(_arguments_1, "location");
                Arguments _arguments_2 = b.getArguments();
                boolean _isAttributeTrue = this.isAttributeTrue(_arguments_2, "first");
                if (_isAttributeTrue) {
                    ArrayList<String> codeSnippets = this.externalCodeStart.get(location);
                    boolean _equals_1 = Objects.equal(codeSnippets, null);
                    if (_equals_1) {
                        ArrayList<String> _arrayList = new ArrayList<String>();
                        codeSnippets = _arrayList;
                    }
                    String _str = this.toStr(b);
                    codeSnippets.add(_str);
                    this.externalCodeStart.put(location, codeSnippets);
                } else {
                    ArrayList<String> codeSnippets_1 = this.externalCodeEnd.get(location);
                    boolean _equals_2 = Objects.equal(codeSnippets_1, null);
                    if (_equals_2) {
                        ArrayList<String> _arrayList_1 = new ArrayList<String>();
                        codeSnippets_1 = _arrayList_1;
                    }
                    String _str_1 = this.toStr(b);
                    codeSnippets_1.add(_str_1);
                    this.externalCodeEnd.put(location, codeSnippets_1);
                }
            }
        }
    }

    public String toStr(final FullyQualifiedArgumentName name) {
        String res = "";
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
                return _identifier_1.getIdentifier();
            }
            String _xifexpression = null;
            String _selector = s.getSelector();
            boolean _notEquals_1 = (!Objects.equal(_selector, null));
            if (_notEquals_1) {
                String _selector_1 = s.getSelector();
                String _plus = ("(" + _selector_1);
                return (_plus + ")");
            }
            _xblockexpression = (_xifexpression);
        }
        return _xblockexpression;
    }

    public String toStr(final FunctionCall call) {
        FullyQualifiedSymbolName _identifier = call.getIdentifier();
        String _str = this.toStr(_identifier);
        String _trim = _str.trim();
        boolean _equalsIgnoreCase = _trim.equalsIgnoreCase("errorexit");
        if (_equalsIgnoreCase) {
            Arguments _arguments = call.getArguments();
            String _strWithoutCommas = this.toStrWithoutCommas(_arguments);
            return ("EXIT" + _strWithoutCommas);
        }
        return super.toStr(call);
    }

    public String toStrWithoutCommas(final Arguments arg) {
        String res = "";
        EList<Argument> _arguments = arg.getArguments();
        Iterator<Argument> iterator = _arguments.iterator();
        boolean _hasNext = iterator.hasNext();
        boolean _while = _hasNext;
        while (_while) {
            {
                Argument a = iterator.next();
                String _plus = (res + " ");
                AnyExpression _expression = a.getExpression();
                String _str = this.toStr(_expression);
                String _plus_1 = (_plus + _str);
                res = _plus_1;
            }
            boolean _hasNext_1 = iterator.hasNext();
            _while = _hasNext_1;
        }
        return res;
    }

}
