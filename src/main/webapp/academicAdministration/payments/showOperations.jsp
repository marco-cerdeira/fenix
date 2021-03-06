<%--

    Copyright © 2002 Instituto Superior Técnico

    This file is part of FenixEdu Core.

    FenixEdu Core is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FenixEdu Core is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FenixEdu Core.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html:xhtml />
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/fenix-renderers" prefix="fr"%>
<%@ page import="net.sourceforge.fenixedu.domain.Person"%>

<html:messages id="message" message="true" bundle="APPLICATION_RESOURCES">
	<p>
		<span class="error0"><!-- Error messages go here --><bean:write name="message" /></span>
	</p>
</html:messages>

<bean:define id="personId" name="person" property="externalId" />
<h2><bean:message bundle="ACADEMIC_OFFICE_RESOURCES"
	key="label.payments.management" /></h2>

<p class="mtop15 mbottom05"><strong><bean:message
	bundle="ACADEMIC_OFFICE_RESOURCES" key="label.payments.person" /></strong></p>
<fr:view name="person"
	schema="person.view-with-name-and-idDocumentType-and-documentIdNumber">
	<fr:layout name="tabular">
		<fr:property name="classes" value="tstyle2 thlight thright mtop05" />
		<fr:property name="rowClasses" value="tdhl1,," />
	</fr:layout>
</fr:view>

<!-- Operations -->
<logic:equal name="permission" value="true">
<ul>
	<li><html:link
		action="<%="/paymentsManagement.do?method=showEvents&amp;personId=" + personId %>">
		<bean:message bundle="ACADEMIC_OFFICE_RESOURCES"
			key="label.payments.currentEvents" />
	</html:link></li>
	<li><html:link
		action="<%="/paymentsManagement.do?method=showReceipts&amp;personId=" + personId%>">
		<bean:message bundle="ACADEMIC_OFFICE_RESOURCES"
			key="label.payments.receipts" />
	</html:link></li>
</ul>
</logic:equal>
<!-- End of Operations -->
