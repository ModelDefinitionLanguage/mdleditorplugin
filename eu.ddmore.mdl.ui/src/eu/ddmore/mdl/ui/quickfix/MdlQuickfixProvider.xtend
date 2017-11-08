/*
* generated by Xtext
*/
package eu.ddmore.mdl.ui.quickfix

import org.eclipse.jface.text.FindReplaceDocumentAdapter
import org.eclipse.xtext.diagnostics.Diagnostic
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider
import org.eclipse.xtext.ui.editor.quickfix.Fix
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor
import org.eclipse.xtext.validation.Issue

//import org.eclipse.xtext.ui.editor.quickfix.Fix
//import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor
//import org.eclipse.xtext.validation.Issue

/**
 * Custom quickfixes.
 *
 * see http://www.eclipse.org/Xtext/documentation.html#quickfixes
 */
class MdlQuickfixProvider extends DefaultQuickfixProvider {

	@Fix(Diagnostic::LINKING_DIAGNOSTIC)
	def fixDanglingRef(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Change to an enumeration', "Changes '=' to 'is' to make this an enumeration.", '') [
			context |
			val xtextDoc = context.xtextDocument
			val adapt = new FindReplaceDocumentAdapter(xtextDoc)
			val rgn = adapt.find(issue.offset, '=', false, true, false, false)
			if(rgn !== null) adapt.replace('is', false)
		]
	}
}
