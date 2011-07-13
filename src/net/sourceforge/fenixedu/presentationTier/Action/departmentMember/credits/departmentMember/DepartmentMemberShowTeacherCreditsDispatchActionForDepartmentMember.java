package net.sourceforge.fenixedu.presentationTier.Action.departmentMember.credits.departmentMember;

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

@Mapping(module = "departmentMember", path = "/showFullTeacherCreditsSheet", attribute = "teacherCreditsForm", formBean = "teacherCreditsForm", scope = "request", parameter = "method")
@Forwards(value = {
		@Forward(name = "show-teacher-credits", path = "/departmentMember/credits/showTeacherCredits.jsp"),
		@Forward(name = "teacher-not-found", path = "/showAllTeacherCreditsResume.do?method=showTeacherCreditsResume&page=0") })
public class DepartmentMemberShowTeacherCreditsDispatchActionForDepartmentMember extends net.sourceforge.fenixedu.presentationTier.Action.departmentMember.credits.DepartmentMemberShowTeacherCreditsDispatchAction {
}