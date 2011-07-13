package net.sourceforge.fenixedu.presentationTier.Action.commons.delegates.delegate;

import pt.ist.fenixWebFramework.struts.annotations.ExceptionHandling;
import pt.ist.fenixWebFramework.struts.annotations.Exceptions;
import pt.ist.fenixWebFramework.struts.annotations.Forward;
import pt.ist.fenixWebFramework.struts.annotations.Forwards;
import pt.ist.fenixWebFramework.struts.annotations.Mapping;
import pt.ist.fenixWebFramework.struts.annotations.Tile;
import pt.ist.fenixWebFramework.struts.annotations.ExceptionHandling;
import pt.ist.fenixWebFramework.struts.annotations.Exceptions;
import pt.ist.fenixWebFramework.struts.annotations.Forward;
import pt.ist.fenixWebFramework.struts.annotations.Forwards;
import pt.ist.fenixWebFramework.struts.annotations.Mapping;
import pt.ist.fenixWebFramework.struts.annotations.Tile;

@Mapping(module = "delegate", path = "/findDelegates", scope = "request", parameter = "method")
@Forwards(value = { @Forward(name = "showDelegates", path = "/commons/delegates/showDelegates.jsp"),
		@Forward(name = "searchDelegates", path = "/commons/delegates/searchDelegates.jsp") })
public class FindDelegatesDispatchActionForDelegate extends net.sourceforge.fenixedu.presentationTier.Action.commons.delegates.FindDelegatesDispatchAction {
}