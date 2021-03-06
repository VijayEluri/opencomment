package uk.org.opencomment.svm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import uk.org.opencomment.classifiers.Classifier;
import uk.org.opencomment.engine.Engine;
import junit.framework.TestCase;

public class SVMClassifierTest extends TestCase {
	
	private Log log = LogFactory.getLog(SVMClassifierTest.class);
	
	private Engine engine = new Engine();
	
	public void setUp() {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Initialising engine with test ruleset");
			}
			engine.parseConfiguration("A207.maps", "file:src/data/question_rule.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testClassifyGarbage() throws Exception {
		Classifier svm = engine.getClassifier("svmtest");
		assertFalse(svm.classify("Testing SVM"));
	}
		
	public void testClassifyPhilosophy() throws Exception {
		Classifier svm = engine.getClassifier("svmtest");	
		assertTrue(svm.classify("definitely about philosophy"));
	}
	
	public void testClassifyMorePhilosophy() throws Exception {
		Classifier svm = engine.getClassifier("svmtest");	
		assertTrue(svm.classify("Corsican philosophers never dine"));
	}
	
	public void testClassifyMoreGarbage() throws Exception {
		Classifier svm = engine.getClassifier("svmtest");	
		assertTrue(svm.classify("Socrates did not live in Corsica and drink wine"));
	}
}
