/**
 * Copyright © 2002 Instituto Superior Técnico
 *
 * This file is part of FenixEdu Core.
 *
 * FenixEdu Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu Core.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.fenixedu.presentationTier.Action.departmentMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.fenixedu.dataTransferObject.commons.ExecutionYearBean;
import net.sourceforge.fenixedu.domain.ExecutionYear;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.teacher.TeacherPersonalExpectation;
import net.sourceforge.fenixedu.presentationTier.Action.base.FenixDispatchAction;
import net.sourceforge.fenixedu.presentationTier.Action.departmentMember.DepartmentMemberApp.DepartmentMemberAccompanimentApp;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fenixedu.bennu.portal.EntryPoint;
import org.fenixedu.bennu.portal.StrutsFunctionality;

import pt.ist.fenixWebFramework.renderers.components.state.IViewState;
import pt.ist.fenixWebFramework.renderers.utils.RenderUtils;
import pt.ist.fenixWebFramework.struts.annotations.Forward;
import pt.ist.fenixWebFramework.struts.annotations.Forwards;
import pt.ist.fenixWebFramework.struts.annotations.Mapping;
import pt.ist.fenixframework.FenixFramework;

@StrutsFunctionality(app = DepartmentMemberAccompanimentApp.class, path = "expectation-auto-evaluation",
        titleKey = "label.autoEvaluation")
@Mapping(module = "departmentMember", path = "/teacherExpectationAutoAvaliation")
@Forwards(value = {
        @Forward(name = "editAutoEvaluation",
                path = "/departmentMember/expectationManagement/editTeachersExpectationAutoEvaluation.jsp"),
        @Forward(name = "showAutoEvaluation",
                path = "/departmentMember/expectationManagement/showTeachersExpectationAutoEvaluation.jsp") })
public class AutoEvaluationTeacherExpectationAction extends FenixDispatchAction {

    public ActionForward prepareEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String executionYearID = request.getParameter("executionYearId");
        if (executionYearID != null) {
            ExecutionYear executionYear = FenixFramework.getDomainObject(executionYearID);
            request.setAttribute("expectation", getTeacherExpectationForGivenYearInRequest(request, executionYear));
        }
        return mapping.findForward("editAutoEvaluation");
    }

    @EntryPoint
    public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ExecutionYear year = getYear(request);
        request.setAttribute("expectation", getTeacherExpectationForGivenYearInRequest(request, year));
        request.setAttribute("bean", new ExecutionYearBean(year));
        return mapping.findForward("showAutoEvaluation");
    }

    private TeacherPersonalExpectation getTeacherExpectationForGivenYearInRequest(HttpServletRequest request, ExecutionYear year) {
        Person person = getLoggedPerson(request);
        return person.getTeacher().getTeacherPersonalExpectationByExecutionYear(year);
    }

    private ExecutionYear getYear(HttpServletRequest request) {

        IViewState viewState = RenderUtils.getViewState("executionYear");
        ExecutionYear year;
        if (viewState != null) {
            year = (ExecutionYear) viewState.getMetaObject().getObject();
        } else {
            String id = request.getParameter("executionYearId");
            year = id != null ? (ExecutionYear) FenixFramework.getDomainObject(id) : ExecutionYear.readCurrentExecutionYear();
        }
        return year;
    }
}
