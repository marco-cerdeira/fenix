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
package net.sourceforge.fenixedu.domain.curricularRules.executors.ruleExecutors;

import java.util.Collection;

import net.sourceforge.fenixedu.domain.CurricularCourse;
import net.sourceforge.fenixedu.domain.curricularRules.ICurricularRule;
import net.sourceforge.fenixedu.domain.curricularRules.RestrictionNotEnroledDegreeModule;
import net.sourceforge.fenixedu.domain.curricularRules.executors.RuleResult;
import net.sourceforge.fenixedu.domain.degreeStructure.CycleCourseGroup;
import net.sourceforge.fenixedu.domain.enrolment.EnrolmentContext;
import net.sourceforge.fenixedu.domain.enrolment.IDegreeModuleToEvaluate;
import net.sourceforge.fenixedu.domain.studentCurriculum.CycleCurriculumGroup;

public class RestrictionNotEnroledDegreeModuleExecutor extends CurricularRuleExecutor {

    @Override
    protected RuleResult executeEnrolmentVerificationWithRules(final ICurricularRule curricularRule,
            final IDegreeModuleToEvaluate sourceDegreeModuleToEvaluate, final EnrolmentContext enrolmentContext) {

        final RestrictionNotEnroledDegreeModule rule = (RestrictionNotEnroledDegreeModule) curricularRule;

        if (!canApplyRule(enrolmentContext, rule)) {
            return RuleResult.createNA(sourceDegreeModuleToEvaluate.getDegreeModule());
        }

        final CurricularCourse curricularCourseNotToBeEnroled = rule.getPrecedenceDegreeModule();
        if (!isApproved(enrolmentContext, curricularCourseNotToBeEnroled)
                && !isEnroled(enrolmentContext, curricularCourseNotToBeEnroled)
                && !isEnrolling(enrolmentContext, curricularCourseNotToBeEnroled)) {
            return RuleResult.createTrue(sourceDegreeModuleToEvaluate.getDegreeModule());
        }

        return RuleResult
                .createFalse(
                        sourceDegreeModuleToEvaluate.getDegreeModule(),
                        "curricularRules.ruleExecutors.RestrictionNotEnroledDegreeModuleExecutor.student.is.enroled.in.precendenceDegreeModule",
                        rule.getDegreeModuleToApplyRule().getName(), rule.getPrecedenceDegreeModule().getName());
    }

    @Override
    protected RuleResult executeEnrolmentWithRulesAndTemporaryEnrolment(final ICurricularRule curricularRule,
            final IDegreeModuleToEvaluate sourceDegreeModuleToEvaluate, final EnrolmentContext enrolmentContext) {
        return executeEnrolmentVerificationWithRules(curricularRule, sourceDegreeModuleToEvaluate, enrolmentContext);
    }

    @Override
    protected RuleResult executeEnrolmentInEnrolmentEvaluation(final ICurricularRule curricularRule,
            final IDegreeModuleToEvaluate sourceDegreeModuleToEvaluate, final EnrolmentContext enrolmentContext) {
        return RuleResult.createNA(sourceDegreeModuleToEvaluate.getDegreeModule());
    }

    @Override
    protected boolean canBeEvaluated(ICurricularRule curricularRule, IDegreeModuleToEvaluate sourceDegreeModuleToEvaluate,
            EnrolmentContext enrolmentContext) {

        RestrictionNotEnroledDegreeModule restrictionNotEnroledDegreeModule = (RestrictionNotEnroledDegreeModule) curricularRule;

        Collection<CycleCourseGroup> cycleCourseGroups =
                restrictionNotEnroledDegreeModule.getPrecedenceDegreeModule().getParentCycleCourseGroups();
        for (CycleCourseGroup cycleCourseGroup : cycleCourseGroups) {
            CycleCurriculumGroup cycleCurriculumGroup =
                    (CycleCurriculumGroup) enrolmentContext.getStudentCurricularPlan().findCurriculumGroupFor(cycleCourseGroup);
            if (cycleCurriculumGroup != null) {
                return true;
            }
        }
        return false;
    }
}
