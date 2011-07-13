package net.sourceforge.fenixedu.presentationTier.Action.vigilancy.examCoordination;

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

@Mapping(module = "examCoordination", path = "/vigilancy/unavailablePeriodManagement", scope = "request", parameter = "method")
@Forwards(value = {
		@Forward(name = "prepareAddPeriodToVigilant", path = "prepare-add-period-to-vigilant"),
		@Forward(name = "manageUnavailablePeriodsOfVigilants", path = "display-groups-unavailablePeriods"),
		@Forward(name = "editPeriodOfVigilant", path = "edit-unavailable-period-of-vigilant") })
public class UnavailablePeriodManagementForExamCoordination extends net.sourceforge.fenixedu.presentationTier.Action.vigilancy.UnavailablePeriodManagement {
}