package eu.ddmore.mdl.ui.contentassist

import com.google.inject.Inject
import com.google.inject.Singleton
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.Library
import eu.ddmore.mdllib.mdllib.NamedFuncArgs
import eu.ddmore.mdllib.mdllib.UnnamedFuncArgs
import java.util.ArrayList
import java.util.List
import org.eclipse.jface.preference.IPreferenceStore
import org.eclipse.jface.text.templates.ContextTypeRegistry
import org.eclipse.jface.text.templates.Template
import org.eclipse.ui.plugin.AbstractUIPlugin
import org.eclipse.xtext.ui.editor.templates.XtextTemplateStore

@Singleton
class MdlDynamicTemplateStore extends XtextTemplateStore {
	
	extension MdlLibUtils mlu = new MdlLibUtils
	
	var private Library libDefns;
	var private List<Template> templateCache = null
	
	public val static String SYMBOL_REF = "eu.ddmore.mdl.Mdl.SymbolReference"
	
	@Inject
	new(ContextTypeRegistry registry, IPreferenceStore store, String key, AbstractUIPlugin plugin) {
		super(registry, store, key, plugin);
	}

	
	override Template[] getTemplates(String contextTypeId) {
		val List<Template> retVal = new ArrayList<Template>(super.getTemplates(contextTypeId));
		if(libDefns != null)
			retVal.addAll(templateCache)

		return retVal;
	}

	def private List<Template> buildTemplatesFromLibrary(){
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
				retVal.add(new TypefulTemplate(name, if(spec.descn != null) spec.descn else "", SYMBOL_REF, pattern, true, spec.returnType.typeInfo))
			}
			else if(args instanceof NamedFuncArgs){
				for(sig : args.sigLists){
					val name = '''
						«methName»«FOR fad : sig.argRefs BEFORE "(" SEPARATOR ", " AFTER ")"»«fad.argRef.name»«ENDFOR»
					'''
					var cntr = 1
					val pattern = '''
						«methName»«FOR fad : sig.argRefs BEFORE "(" SEPARATOR ", " AFTER ")"»«fad.argRef.name»=${argVal«cntr++»}«ENDFOR»
					'''
					retVal.add(new TypefulTemplate(name, if(spec.descn != null) spec.descn else "", SYMBOL_REF, pattern, true, spec.returnType.typeInfo))
				}
			}
		}
		retVal
	}
	
	
	def setLibDefns(Library defn){
		if(this.libDefns != defn){
			this.libDefns = defn
			if(this.libDefns != null)
				this.templateCache = buildTemplatesFromLibrary()
			else
				this.templateCache = null
		}
	}
	
}