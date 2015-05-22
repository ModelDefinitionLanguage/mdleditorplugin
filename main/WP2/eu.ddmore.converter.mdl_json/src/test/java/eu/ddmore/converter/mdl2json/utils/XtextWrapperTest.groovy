package eu.ddmore.converter.mdl2json.utils;

import static org.junit.Assert.*
import static org.mockito.Mockito.*

import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.UnaryExpression
import org.eclipse.emf.common.util.EList
import org.junit.After
import org.junit.Before
import org.junit.Test


class XtextWrapperTest {
    
    private final UnaryExpression mockExpr1 = mock(UnaryExpression.class)
    private final UnaryExpression mockExpr2 = mock(UnaryExpression.class)
    private final UnaryExpression mockExpr3 = mock(UnaryExpression.class)

    /**
     * Set-up tasks prior to each test being run.
     */
    @Before
    public void setUp() {
    }

    /**
     * Test method for {@link eu.ddmore.converter.mdl2json.utils.XtextWrapper#unwrap(org.eclipse.emf.common.util.EList, org.eclipse.emf.common.util.EList)}.
     */
    @Test
    public void testUnwrapExpressionListWithOperatorList_SingleExpression() {
        stub(mockExpr1.getNumber()).toReturn("123")
        assertEquals("123", XtextWrapper.unwrap([mockExpr1] as EList, [] as EList))
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2json.utils.XtextWrapper#unwrap(org.eclipse.emf.common.util.EList, org.eclipse.emf.common.util.EList)}.
     */
    @Test
    public void testUnwrapExpressionListWithOperatorList_TwoExpressions() {
        stub(mockExpr1.getNumber()).toReturn("123")
        stub(mockExpr2.getNumber()).toReturn("456")
        assertEquals("123+456", XtextWrapper.unwrap([mockExpr1, mockExpr2] as EList, ["+"] as EList))
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2json.utils.XtextWrapper#unwrap(org.eclipse.emf.common.util.EList, org.eclipse.emf.common.util.EList)}.
     */
    @Test
    public void testUnwrapExpressionListWithOperatorList_ThreeExpressionsDifferentOperators() {
        stub(mockExpr1.getNumber()).toReturn("123")
        stub(mockExpr2.getNumber()).toReturn("456")
        stub(mockExpr3.getNumber()).toReturn("789")
        assertEquals("123+456-789", XtextWrapper.unwrap([mockExpr1, mockExpr2, mockExpr3] as EList, ["+", "-"] as EList))
    }

    /**
     * Test method for {@link eu.ddmore.converter.mdl2json.utils.XtextWrapper#unwrap(org.eclipse.emf.common.util.EList, org.eclipse.emf.common.util.EList)}.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testUnwrapExpressionListWithOperatorList_EmptyExpressionList() {
        XtextWrapper.unwrap([] as EList, [] as EList)
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2json.utils.XtextWrapper#unwrap(org.eclipse.emf.common.util.EList, org.eclipse.emf.common.util.EList)}.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testUnwrapExpressionListWithOperatorList_InconsistentListSizes_1() {
        stub(mockExpr1.getNumber()).toReturn("123")
        stub(mockExpr2.getNumber()).toReturn("456")
        stub(mockExpr3.getNumber()).toReturn("789")
        XtextWrapper.unwrap([mockExpr1, mockExpr2, mockExpr3] as EList, ["+"] as EList)
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2json.utils.XtextWrapper#unwrap(org.eclipse.emf.common.util.EList, org.eclipse.emf.common.util.EList)}.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testUnwrapExpressionListWithOperatorList_InconsistentListSizes_2() {
        stub(mockExpr1.getNumber()).toReturn("123")
        stub(mockExpr2.getNumber()).toReturn("456")
        XtextWrapper.unwrap([mockExpr1, mockExpr2] as EList, ["+", "-"] as EList)
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2json.utils.XtextWrapper#unwrap(org.eclipse.emf.common.util.EList, org.eclipse.emf.common.util.EList)}.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testUnwrapExpressionListWithOperatorList_InconsistentListSizes_3() {
        stub(mockExpr1.getNumber()).toReturn("123")
        XtextWrapper.unwrap([mockExpr1] as EList, ["+", "-"] as EList)
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2json.utils.XtextWrapper#unwrap(org.eclipse.emf.common.util.EList, org.eclipse.emf.common.util.EList)}.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testUnwrapExpressionListWithOperatorList_InconsistentListSizes_4() {
        XtextWrapper.unwrap([] as EList, ["+"] as EList)
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2json.utils.XtextWrapper#unwrap(org.eclipse.emf.common.util.EList, org.eclipse.emf.common.util.EList)}.
     */
    @Test(expected=NullPointerException.class)
    public void testUnwrapExpressionListWithOperatorList_NullExpressionList() {
        XtextWrapper.unwrap(null as EList, [] as EList)
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2json.utils.XtextWrapper#unwrap(org.eclipse.emf.common.util.EList, org.eclipse.emf.common.util.EList)}.
     */
    @Test(expected=NullPointerException.class)
    public void testUnwrapExpressionListWithOperatorList_NullOperatorList() {
        XtextWrapper.unwrap([] as EList, null as EList)
    }

}
