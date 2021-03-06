<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTransportDispChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_workflow_childwindow.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr height="5"><td colspan="2"></td></tr>
		<tr>
			<td colspan="3" class="text16Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			<spring:message code="systema.transportdisp.orders.invoice.form.label.gebyrcode.title"/>
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
		<form action="transportdisp_workflow_childwindow_gebyrcode.do?action=doFind" name="searchGebyrCodeForm" id="searchGebyrCodeForm" method="post">
			<%-- =====================================================  --%>
          	<%-- Here we have the search [Gebyr Codes] popup window --%>
          	<%-- =====================================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%">
					<tr height="5"><td></td></tr>
					<tr>
					<td>
						<table>
						<tr>
							<td class="text14">&nbsp;<spring:message code="systema.transportdisp.orders.invoice.form.label.gebyrcode.code"/></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="kode" id="kode" size="21" maxlength="20" value="${model.container.kode}"></td>
							<td class="text14">&nbsp;<spring:message code="systema.transportdisp.orders.invoice.form.label.gebyrcode.desc"/></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="tekst" id="tekst" size="21" maxlength="20" value="${model.container.tekst}"></td>
							<td class="text14">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.transportdisp.search"/>'>
		           		</tr>
		           		
		           		</table>
					</td>
					</tr>
					<%-- Validation errors --%>
					<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
					<tr>
						<td colspan="20">
			            	<table align="left" border="0" cellspacing="0" cellpadding="0">
			            	<tr>
							<td class="textError">					
					            <ul>
					            <c:forEach var="error" items="${errors.allErrors}">
					                <li >
					                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>
					                </li>
					            </c:forEach>
					            </ul>
							</td>
							</tr>
							</table>
						</td>
					</tr>
					</spring:hasBindErrors>
										
					
					<tr><td><hr size="1" width="100%"/></td></tr>								           		
	           		<tr height="15"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:50em;">
					<%-- this is the datatables grid (content)--%>
					<table id="gebyrcodeList" class="display compact cell-border" width="100%">
						<thead>
						<tr class="tableHeaderField" height="20">
						    <th class="text14">&nbsp;<spring:message code="systema.transportdisp.orders.invoice.form.label.gebyrcode.code"/>&nbsp;</th>   
		                    <th class="text14">&nbsp;<spring:message code="systema.transportdisp.orders.invoice.form.label.gebyrcode.desc"/>&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.gebyrCodeList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text14">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text14">
			                   </c:otherwise>
			               </c:choose>
			               <td style="width:20%; cursor:pointer;" class="textMediumBlue" id="code_${record.kgekod}@dt_${counter.count}">
			               		&nbsp;<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
			               		&nbsp;&nbsp;${record.kgekod}
	               		   </td>
			               <td class="text14">&nbsp;${record.kgenot}</td>
			               
			            </tr> 
			            </c:forEach>
			            </tbody>
		            </table>
		            </td>
	           		</tr>
        			</table>
				
		</form>	
		</td>
		</tr>
	</table> 
