package eu.ddmore.mdl.ui.contentassist

import com.google.inject.Inject
import com.google.inject.Singleton
import eu.ddmore.mdl.type.BuiltinEnumTypeInfo
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.EnumValue
import eu.ddmore.mdllib.mdllib.Library
import eu.ddmore.mdllib.mdllib.ListAttributeRef
import eu.ddmore.mdllib.mdllib.ListTypeDefinition
import eu.ddmore.mdllib.mdllib.NamedFuncArgs
import eu.ddmore.mdllib.mdllib.UnnamedFuncArgs
import java.util.ArrayList
import java.util.List
import org.eclipse.jface.preference.IPreferenceStore
import org.eclipse.jface.text.templates.ContextTypeRegistry
import org.eclipse.jface.text.templates.Template
import org.eclipse.jface.text.templates.persistence.TemplatePersistenceData
import org.eclipse.ui.plugin.AbstractUIPlugin
import org.eclipse.xtext.ui.editor.templates.XtextTemplateStore
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.VectorTypeInfo
import eu.ddmore.mdl.type.MatrixTypeInfo

@Singleton
class MdlDynamicTemplateStore extends XtextTemplateStore {
	static val TEMPL_AUTO_INSERT = false
	
	extension MdlLibUtils mlu = new MdlLibUtils
	
	var private Library libDefns;
	val private List<Template> templateCache
	
	public val static String SYMBOL_REF = "eu.ddmore.mdl.Mdl.SymbolReference"
	public val static String ATT_LIST_DEFN = "eu.ddmore.mdl.Mdl.AttributeList"
	
	@Inject
	new(ContextTypeRegistry registry, IPreferenceStore store, String key, AbstractUIPlugin plugin) {
		super(registry, store, key, plugin)
		this.templateCache = new ArrayList<Template>
	}

	
//	override Template[] getTemplates(String contextTypeId) {
//		val List<Template> retVal = new ArrayList<Template>(super.getTemplates(contextTypeId));
//		if(libDefns !== null)
//			retVal.addAll(templateCache)
//
//		return retVal;
//	}

	def private List<Template> buildListTemplatesFromLibrary(){
		val List<Template> retVal = new ArrayList<Template>
		for(bd : libDefns.blockDefns){
			if(bd.listType !== null){
				retVal.addAll(getListTemplates(bd.listType))
			}
			else if(!bd.listAttMappings.isEmpty){
				bd.listAttMappings.forEach[
					retVal.addAll(getListTemplates(attType))
				]
			}
			else{
				bd.listTypeMappings.forEach[
					retVal.addAll(getListTemplates(attType, bd.keyAttName, attDefn))
				]
			}
		}
		retVal
	}
	
	def private List<Template> getListTemplates(ListTypeDefinition defns){
		getListTemplates(defns, null, null)
	}
	
	def private List<Template> getListTemplates(ListTypeDefinition defns, String keyAtt, EnumValue keyValue){
		val List<Template> retVal = new ArrayList<Template>
		for(sig : defns.sigLists){
			val name = '''
				«FOR fad : sig.attRefs BEFORE "{" SEPARATOR ", " AFTER "}"»«fad.attRef.name»«IF defns.isBuiltinEnum(fad)» is «ELSE» = «ENDIF»«IF keyAtt !== null && fad.attRef.name == keyAtt»«keyValue.name»«ENDIF»«ENDFOR»
			'''
			var cntr = 1
			val pattern = '''«FOR fad : sig.attRefs BEFORE "{" SEPARATOR ", " AFTER "}"»«fad.attRef.name»«IF defns.isBuiltinEnum(fad)» is «ELSE» = «ENDIF»«IF keyAtt !== null && fad.attRef.name == keyAtt»«keyValue.name»«ELSE»«fad.typeInfo.LBrace»${attVal«cntr++»}«fad.typeInfo.RBrace»«ENDIF»«ENDFOR»'''
			retVal.add(new TypefulTemplate(name, if(defns.descn !== null) defns.descn else "", ATT_LIST_DEFN, pattern, TEMPL_AUTO_INSERT, defns.typeInfo))
		}
		retVal
	}
	
	def private getLBrace(TypeInfo argType){
		switch(argType){
			VectorTypeInfo:
				'['
			MatrixTypeInfo:
				'[['
			default:
				''
		}
	}
	
	def private getRBrace(TypeInfo argType){
		switch(argType){
			VectorTypeInfo:
				']'
			MatrixTypeInfo:
				']]'
			default:
				''
		}
	}
	
	
	def private TypeInfo getTypeInfo(ListAttributeRef it){
		attRef.attType.typeInfo
	}
	
	
	def private boolean isBuiltinEnum(ListTypeDefinition it, ListAttributeRef ar){
		ar.attRef.attType.typeInfo instanceof BuiltinEnumTypeInfo
	}
	
	
	def private List<Template> buildFunctionTemplatesFromLibrary(){
		val List<Template> retVal = new ArrayList<Template>
		for(defns : libDefns.getFuncDefns()){
			val methName = defns.name
			val spec = defns.funcSpec
			val args = spec.argument
			if(args instanceof UnnamedFuncArgs){
				val name = '''
					«methName»«FOR fad : args.arguments BEFORE "(" SEPARATOR ", " AFTER ")"»«fad.name»«ENDFOR»
				'''
				val pattern = '''
					«methName»«FOR fad : args.arguments BEFORE "(" SEPARATOR ", " AFTER ")"»${«fad.name»}«ENDFOR»
				'''
				retVal.add(new TypefulTemplate(name, if(spec.descn !== null) spec.descn else "", SYMBOL_REF, pattern, TEMPL_AUTO_INSERT, spec.returnType.typeInfo))
			}
			else if(args instanceof NamedFuncArgs){
				for(sig : args.sigLists){
					val name = '''
						«methName»«FOR fad : sig.argRefs BEFORE "(" SEPARATOR ", " AFTER ")"»«fad.argRef.name»«ENDFOR»
					'''
					var cntr = 1
					val pattern = '''
						«methName»«FOR fad : sig.argRefs BEFORE "(" SEPARATOR ", " AFTER ")"»«fad.argRef.name»«IF fad.argRef.typeSpec.typeInfo instanceof BuiltinEnumTypeInfo» is «ELSE» = «ENDIF»«fad.argRef.typeSpec.typeInfo.LBrace»${argVal«cntr++»}«fad.argRef.typeSpec.typeInfo.RBrace»«ENDFOR»
					'''
					retVal.add(new TypefulTemplate(name, if(spec.descn !== null) spec.descn else "", SYMBOL_REF, pattern, TEMPL_AUTO_INSERT, spec.returnType.typeInfo))
				}
			}
		}
		retVal
	}
	
	
	def setLibDefns(Library defn){
		if(this.libDefns != defn){
			this.libDefns = defn
			if(this.libDefns !== null){
				this.templateCache.addAll(buildFunctionTemplatesFromLibrary())
				this.templateCache.addAll(buildListTemplatesFromLibrary())
				for(t : this.templateCache){
					internalAdd(new TemplatePersistenceData(t, true, ATT_LIST_DEFN + "." + t.name))
				}
			}
			else
				this.templateCache.clear
		}
	}
	
}