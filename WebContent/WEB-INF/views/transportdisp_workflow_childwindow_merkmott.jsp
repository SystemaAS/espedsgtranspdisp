<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTransportDispChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_workflow_childwindow.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-dialog{font-size:11pt;}
	.ui-datepicker { font-size:9pt;}
	.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
	.ui-timepicker-div dl { text-align: left; }
	.ui-timepicker-div dl dt { float: left; clear:left; padding: 0 0 0 5px; }
	.ui-timepicker-div dl dd { margin: 0 10px 10px 40%; }
	.ui-timepicker-div td { font-size: 90%; }
	.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
	
	.ui-timepicker-rtl{ direction: rtl; }
	.ui-timepicker-rtl dl { text-align: right; padding: 0 5px 0 0; }
	.ui-timepicker-rtl dl dt{ float: right; clear: right; }
	.ui-timepicker-rtl dl dd { margin: 0 40% 10px 10px; }
	
	
	</style>
	
	<table width="90%" border="0">
		<tr>
			<td valign="top" colspan="3" class="text16Bold">&nbsp;Merk mottat</td>
		</tr>
						
	<%-- --------------------------- --%>
	<%-- Validation errors FRONT END --%>
	<%-- --------------------------- --%>
	<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
	<tr height="5"><td></td></tr>
	<tr>
		<td colspan="10">
           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td valign="bottom" class="textError">					
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
	
	<%-- -------------------------- --%>
	<%-- Validation errors BACK END --%>
	<%-- -------------------------- --%>
	<c:if test="${not empty model.errorMessage}">
		<tr>
		<td>
           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td valign="bottom" class="textError">					
	            <ul>
	            	<li >${model.errorMessage}</li>
	            </ul>
			</td>
			</tr>
			</table>
		</td>
		</tr>		
	</c:if>
			
	<%-- ------------------------------------------------- --%>
    	<%-- DETAIL Section - Create Item line PRIMARY SECTION --%>
    	<%-- ------------------------------------------------- --%>
    	<tr height="5"><td></td></tr>
    	<tr>
    <td valign="top">
    <table style="width:80" >
	    	<tr>
			<td >
				<form action="transportdisp_workflow_childwindow_merkmott_edit.do" name="transportdispUpdateMerkMottForm" id="transportdispUpdateMerkMottForm" method="post">
			 	<%--Required key parameters from the Topic parent --%>
			 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
			 	<input type="hidden" name="action" id="action" value='doUpdate'/>
			 	<input type="hidden" name="avd" id="avd" value='${model.record.avd}'>
				<input type="hidden" name="opd" id="opd" value='${model.record.opd}'>
				<input type="hidden" name="updateId" id="updateId" value="1">
				
			 	<%-- Record CREATE --%>
					<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						
			 		<tr height="15">
			 			<td class="text14White" align="left" >
			 				&nbsp;&nbsp;<img src="resources/images/update.gif" border="0" alt="edit">&nbsp;&nbsp;
			 				Op.&nbsp;<b>${model.record.avd}/${model.record.opd}</b>
		 				</td>
	 				</tr>
					</table>
				<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
			 		<tr height="12"><td class="text" align="left"></td></tr>
			 		<tr>
				 		<td>
					 		<table  class="tableBorderWithRoundCornersGray" width="99%" border="0" cellspacing="0" cellpadding="0">
					 			<tr height="5"><td class="text" align="left"></td></tr>
					 			<tr >
					 				<td title="wsSGM" class="text14" align="left">&nbsp;Gods mottatt av</td>
					                <td class="text14" align="left">
					                		<input type="text" class="inputTextMediumBlue"  name="wsSGM" id="wsSGM" size="12" maxlength="10" value='${model.record.wsSGM}'>
					                </td>
					            </tr>
					            <tr >
					 				<td title="wsDTMO" class="text14" align="left">&nbsp;Dato (DDMMÅÅ)</td>
					                <td class="text14" align="left">
					                		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue"  name="wsDTMO" id="wsDTMO" size="8" maxlength="6" value='${model.record.wsDTMO}'>
					                </td>
					            </tr>
					            <tr >
					 				<td title="wsKLMO" class="text14" align="left">&nbsp;Klokkeslett (ttmm)</td>
					                <td class="text14" align="left">
					                		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue"  name="wsKLMO" id="wsKLMO" size="6" maxlength="4" value='${model.record.wsKLMO}'>
					                </td>
					            </tr>
					            <tr height="12"><td colspan="3" class="text" align="left"></td></tr>
					            <tr >
					 				<td title="wsTLE" class="text14" align="left">&nbsp;Ekspedisjonssted</td>
					                <td class="text14" align="left">
					                		<input type="text" class="inputTextMediumBlue"  name="wsTLE" id="wsTLE" size="8" maxlength="6" value='${model.record.wsTLE}'>
					                </td>
					            </tr>
					            <tr >
					 				<td title="wsTLL" class="text14" align="left">&nbsp;Løpenr</td>
					                <td class="text14" align="left">
					                		<input type="text" class="inputTextMediumBlue"  name="wsTLL" id="wsTLL" size="12" maxlength="10" value='${model.record.wsTLL}'>
					                </td>
					            </tr>
					            <tr>
						        		<td title="wsTLKU" class="text14" align="left">&nbsp;Kan utføres</td>
					        		    <td class="text14" align="left" >
						            	<select class="inputTextMediumBlue" name="wsTLKU" id="wsTLKU" >
						 				  <option value=" " <c:if test="${model.record.wsTLKU == ' ' || empty model.record.wsTLKU }"> selected </c:if> >blank</option>
						 				  <option value="X" <c:if test="${model.record.wsTLKU == 'X'}"> selected </c:if> >X</option>
										</select>							            	
						            </td>
						        </tr>
						        <tr >
					 				<td title="wsDTG" class="text14" align="left">&nbsp;Dato (DDMMÅÅ)</td>
					                <td class="text14" align="left">
					                		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue"  name="wsDTG" id="wsDTG" size="8" maxlength="6" value='${model.record.wsDTG}'>
					                </td>
					            </tr>
						        <tr >
					 				<td title="wsGNN" class="text14" align="left">&nbsp;Nytt godsnr/tollpass</td>
					                <td class="text14" align="left">
					                		<input type="text" class="inputTextMediumBlue"  name="wsGNN" id="wsGNN" size="17" maxlength="15" value='${model.record.wsGNN}'>
					                </td>
					            </tr>
						        <tr height="5"><td class="text" align="left"></td></tr>
					        </table>
				        </td>
			        </tr>
				    <tr height="10"><td colspan="2" ></td></tr>
				    <tr>	
					    <td align="left" colspan="5">
					    	<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.transportdisp.submit.save"/>'>
						</td>
			        </tr>
	      	        </table>
	         	</form>
	        </td>
	    </tr>
		
		<tr height="10"><td ></td></tr>
		</table>

	</td>
	</tr>	
		
	</table> 
