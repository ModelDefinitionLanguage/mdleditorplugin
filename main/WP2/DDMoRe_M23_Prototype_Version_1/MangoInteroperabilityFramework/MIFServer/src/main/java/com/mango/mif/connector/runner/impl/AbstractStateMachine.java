/* File			:  AbstractStateMachine.java
 * Project		:  treepoc
 * Created on	:  6 Jan 2012
 */
package com.mango.mif.connector.runner.impl;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * BASED on Apache SCXML AbstractStateMachine
 * 
 * Encapsulates Apache SCXML commands
 */
import java.io.IOException;
import java.net.URL;

import org.apache.commons.scxml.Context;
import org.apache.commons.scxml.Evaluator;
import org.apache.commons.scxml.SCXMLExecutor;
import org.apache.commons.scxml.TriggerEvent;
import org.apache.commons.scxml.env.SimpleDispatcher;
import org.apache.commons.scxml.env.SimpleErrorHandler;
import org.apache.commons.scxml.env.SimpleErrorReporter;
import org.apache.commons.scxml.env.jexl.JexlContext;
import org.apache.commons.scxml.env.jexl.JexlEvaluator;
import org.apache.commons.scxml.io.SCXMLParser;
import org.apache.commons.scxml.model.ModelException;
import org.apache.commons.scxml.model.SCXML;
import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

public abstract class AbstractStateMachine {
    /**
     * Logger
     */
    public static Logger LOG = Logger.getLogger(AbstractStateMachine.class);
   /**
    * The instance specific SCXML engine.
    */
   protected SCXMLExecutor engine;

   /**
    * Basic constructor
    */
   public AbstractStateMachine() {
   }

   /**
    * 
    * Initialisation of the state machine
    * 
    * @param scxmlDocument
    */
   public void initialize(URL scxmlDocument) {
       initialize(scxmlDocument, new JexlContext(), new JexlEvaluator());
   }
   /**
    *
    * @param scxmlDocument The URL pointing to the SCXML document that
    *                      describes the &quot;lifecycle&quot; of the
    *                      instances of this class.
    * @param rootCtx The root context for this instance.
    * @param evaluator The expression evaluator for this instance.
    *
    * @see Context
    * @see Evaluator
    */
   private void initialize( URL scxmlDocument,
            Context rootCtx,  Evaluator evaluator) {
       ErrorHandler errHandler = new SimpleErrorHandler();
       try {
           SCXML stateMachine = SCXMLParser.parse(scxmlDocument,
               errHandler);

           initializeEngine(stateMachine, rootCtx, evaluator);
       } catch (IOException ioe) {
           LOG.error(ioe);
       } catch (SAXException sae) {
           LOG.error(sae);
       } catch (ModelException me) {
           LOG.error(me);
       }
   }


   /**
    * Initialisation the underlying executor instance.
    *
    * @param stateMachine The state machine
    * @param rootCtx The root context
    * @param evaluator The expression evaluator
    */
   private void initializeEngine(SCXML stateMachine,
            Context rootCtx, Evaluator evaluator) {
       engine = new SCXMLExecutor(evaluator, new SimpleDispatcher(),
           new SimpleErrorReporter());
       engine.setStateMachine(stateMachine);
       engine.setSuperStep(true);
       engine.setRootContext(rootCtx);
       

       try {
           engine.go();
       } catch (ModelException me) {
           LOG.error(me);
       }
   }
   
   /**
    * Fire an event on the SCXML engine.
    *
    * @param event The event name.
    * @return Whether the state machine has reached a &quot;final&quot;
    *         configuration.
    */
   protected boolean fireEventInternal(final String event) {
       TriggerEvent[] evts = {new TriggerEvent(event,
               TriggerEvent.SIGNAL_EVENT, null)};
       try {
           engine.triggerEvents(evts);
       } catch (ModelException me) {
           LOG.error(me);
       }
       return engine.getCurrentStatus().isFinal();
   }


   /**
    * Get the SCXML engine driving the &quot;lifecycle&quot; of the
    * instances of this class.
    *
    * @return Returns the engine.
    */
   public SCXMLExecutor getEngine() {
       return engine;
   }

}

