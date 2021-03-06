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
package net.sourceforge.fenixedu.domain.candidacy;

import net.sourceforge.fenixedu.domain.EntryPhase;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.Qualification;
import net.sourceforge.fenixedu.domain.degreeStructure.CycleType;
import net.sourceforge.fenixedu.domain.student.Registration;
import net.sourceforge.fenixedu.domain.student.RegistrationAgreement;
import net.sourceforge.fenixedu.domain.student.RegistrationProtocol;
import net.sourceforge.fenixedu.domain.student.Student;
import net.sourceforge.fenixedu.domain.util.workflow.IState;
import net.sourceforge.fenixedu.domain.util.workflow.StateBean;
import net.sourceforge.fenixedu.injectionCode.AccessControl;

import org.joda.time.YearMonthDay;

public class RegisteredCandidacySituation extends RegisteredCandidacySituation_Base {

    public RegisteredCandidacySituation(Candidacy candidacy) {
        this(candidacy, null);
    }

    public RegisteredCandidacySituation(Candidacy candidacy, Person person) {
        this(candidacy, person, null, null);
    }

    @Deprecated
    public RegisteredCandidacySituation(Candidacy candidacy, RegistrationAgreement registrationAgreement, CycleType cycleType,
            Ingression ingression, EntryPhase entryPhase, Integer studentNumber) {
        this(candidacy, RegistrationProtocol.serveRegistrationProtocol(registrationAgreement), cycleType, ingression, entryPhase, studentNumber);
    }

    public RegisteredCandidacySituation(Candidacy candidacy, RegistrationProtocol registrationProtocol, CycleType cycleType,
            Ingression ingression, EntryPhase entryPhase, Integer studentNumber) {
        super();
        init(candidacy, AccessControl.getPerson());
        registerCandidacy(registrationProtocol, cycleType, studentNumber);

        ((StudentCandidacy) candidacy).setIngression(ingression);
        ((StudentCandidacy) candidacy).setEntryPhase(entryPhase);
    }

    private RegisteredCandidacySituation(Candidacy candidacy, Person person, RegistrationProtocol registrationProtocol,
            CycleType cycleType) {
        super();
        init(candidacy, person == null ? AccessControl.getPerson() : person);
        registerCandidacy(registrationProtocol, cycleType, null);
    }

    private void registerCandidacy(RegistrationProtocol registrationProtocol, CycleType cycleType, Integer studentNumber) {
        Person person = getCandidacy().getPerson();
        Registration registration = null;

        if (getCandidacy() instanceof DFACandidacy) {
            DFACandidacy dfaCandidacy = ((DFACandidacy) getCandidacy());
            registration =
                    new Registration(person, dfaCandidacy.getExecutionDegree().getDegreeCurricularPlan(), dfaCandidacy,
                            registrationProtocol, cycleType, dfaCandidacy.getExecutionDegree().getExecutionYear());

            //person.addPersonRoles(Role.getRoleByRoleType(RoleType.STUDENT));
            dfaCandidacy.setRegistration(registration);
            createQualification();

        }

        if (person.getStudent() == null && studentNumber == null) {
            new Student(person);
        } else if (person.getStudent() == null && studentNumber != null) {
            Student.createStudentWithCustomNumber(person, studentNumber);
        }
    }

    private void createQualification() {
        DFACandidacy dfaCandidacy = (DFACandidacy) getCandidacy();
        if (dfaCandidacy.getPrecedentDegreeInformation() != null) {
            Qualification qualification = new Qualification();
            qualification.setPerson(dfaCandidacy.getPerson());
            qualification.setMark(dfaCandidacy.getPrecedentDegreeInformation().getConclusionGrade());
            qualification.setSchool(dfaCandidacy.getPrecedentDegreeInformation().getInstitutionName());
            qualification.setDegree(dfaCandidacy.getPrecedentDegreeInformation().getDegreeDesignation());
            if (dfaCandidacy.getPrecedentDegreeInformation().getConclusionYear() != null) {
                qualification.setDateYearMonthDay(new YearMonthDay(dfaCandidacy.getPrecedentDegreeInformation()
                        .getConclusionYear(), 1, 1));
            }
            qualification.setCountry(dfaCandidacy.getPrecedentDegreeInformation().getCountry());
        }
    }

    @Override
    public CandidacySituationType getCandidacySituationType() {
        return CandidacySituationType.REGISTERED;
    }

    @Override
    public boolean canExecuteOperationAutomatically() {
        return false;
    }

    @Override
    public IState nextState() {
        return null;
    }

    @Override
    public IState nextState(final StateBean bean) {
        return null;
    }

}
