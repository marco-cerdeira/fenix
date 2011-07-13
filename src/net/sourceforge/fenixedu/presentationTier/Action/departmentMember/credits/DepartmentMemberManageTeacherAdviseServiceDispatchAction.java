package net.sourceforge.fenixedu.presentationTier.Action.departmentMember.credits;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.fenixedu.applicationTier.IUserView;
import net.sourceforge.fenixedu.applicationTier.Filtro.exception.FenixFilterException;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.domain.DomainObject;
import net.sourceforge.fenixedu.domain.ExecutionSemester;
import net.sourceforge.fenixedu.domain.Teacher;
import net.sourceforge.fenixedu.domain.person.RoleType;
import net.sourceforge.fenixedu.presentationTier.Action.credits.ManageTeacherAdviseServiceDispatchAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import pt.ist.fenixWebFramework.security.UserView;
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

@Mapping(module = "departmentMember", path = "/teacherAdviseServiceManagement", input = "/teacherAdviseServiceManagement.do?method=showTeacherAdvises&page=0", attribute = "teacherDegreeFinalProjectStudentForm", formBean = "teacherDegreeFinalProjectStudentForm", scope = "request", parameter = "method")
@Forwards(value = {
		@Forward(name = "list-teacher-advise-services", path = "/credits/adviseServices/showTeacherAdviseServices.jsp"),
		@Forward(name = "successfull-delete", path = "/teacherAdviseServiceManagement.do?method=showTeacherAdvises&page=0"),
		@Forward(name = "successfull-edit", path = "/teacherAdviseServiceManagement.do?method=showTeacherAdvises&page=0"),
		@Forward(name = "teacher-not-found", path = "/showAllTeacherCreditsResume.do?method=showTeacherCreditsResume&page=0") })
@Exceptions(value = {
		@ExceptionHandling(type = net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException.class, handler = net.sourceforge.fenixedu.presentationTier.config.FenixExceptionMessageHandler.class, scope = "request"),
		@ExceptionHandling(type = net.sourceforge.fenixedu.domain.exceptions.DomainException.class, handler = net.sourceforge.fenixedu.presentationTier.config.FenixDomainExceptionHandler.class, scope = "request") })
public class DepartmentMemberManageTeacherAdviseServiceDispatchAction extends ManageTeacherAdviseServiceDispatchAction {

    public ActionForward showTeacherAdvises(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws NumberFormatException, FenixFilterException, FenixServiceException {

	DynaActionForm dynaForm = (DynaActionForm) form;

	final Integer executionPeriodID = (Integer) dynaForm.get("executionPeriodId");
	final ExecutionSemester executionSemester = rootDomainObject.readExecutionSemesterByOID(executionPeriodID);

	Teacher teacher = DomainObject.fromExternalId(dynaForm.getString("teacherId"));

	if (teacher == null || getLoggedTeacher(request) != teacher) {
	    createNewActionMessage(request);
	    return mapping.findForward("teacher-not-found");
	}

	getAdviseServices(request, dynaForm, executionSemester, teacher);
	return mapping.findForward("list-teacher-advise-services");
    }

    private void createNewActionMessage(HttpServletRequest request) {
	ActionMessages actionMessages = new ActionMessages();
	actionMessages.add("", new ActionMessage("message.invalid.teacher"));
	saveMessages(request, actionMessages);
    }

    private Teacher getLoggedTeacher(HttpServletRequest request) {
	IUserView userView = UserView.getUser();
	return userView.getPerson().getTeacher();
    }

    public ActionForward editAdviseService(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws NumberFormatException, FenixFilterException, FenixServiceException {

	return editAdviseService(form, request, mapping, RoleType.DEPARTMENT_MEMBER);
    }

    public ActionForward deleteAdviseService(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws NumberFormatException, FenixFilterException, FenixServiceException {

	deleteAdviseService(request, RoleType.DEPARTMENT_MEMBER);
	return mapping.findForward("successfull-delete");

    }
}
