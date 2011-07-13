package net.sourceforge.fenixedu.presentationTier.Action.manager.functionalities.person.functionalities;

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

@Mapping(module = "person/functionalities", path = "/private/filter", scope = "session", parameter = "method")
@Forwards(value = {
		@Forward(name = "index", path = "/manager/functionalities/filter/index.jsp"),
		@Forward(name = "results", path = "/manager/functionalities/filter/index.jsp") })
public class TestFilterActionForPersonFunctionalities extends net.sourceforge.fenixedu.presentationTier.Action.manager.functionalities.TestFilterAction {
}