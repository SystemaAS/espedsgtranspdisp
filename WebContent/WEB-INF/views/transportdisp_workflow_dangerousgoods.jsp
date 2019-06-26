<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ==================================== -->
<jsp:include page="/WEB-INF/views/headerTransportDispDangerousgoods.jsp" />
<!-- =====================end header =================================== -->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/transportdispglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>			
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_workflow_dangerousgoods.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
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
	
	
	<%-- -------------------------------- --%>	
 	<%-- tab area container MASTER TOPIC  --%>
	<%-- -------------------------------- --%>
 	<table width="100%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" border="0" cellspacing="0" cellpadding="0">
		<tr height="3"><td colspan="2">&nbsp;</td></tr>
		<tr>
			<td colspan="3" class="text16Bold">&nbsp;
				<spring:message code="systema.transportdisp.title"/> - <spring:message code="systema.transportdisp.workflow.dangerousgoods.label"/>
			
				&nbsp;&nbsp;<font class="text16MediumBlue">Avd/Opd&nbsp;<b>${model.avd}</b>/<b>${model.opd}</b> &nbsp;Linjenr&nbsp;<b>${model.linNr}</b></font>	
				<font class="text16MediumBlue">&nbsp;/&nbsp;<b>${model.container.fvant}</b>&nbsp;<b>${model.container.fvpakn}</b> &nbsp;<b>${model.container.fvvt}</b>&nbsp;<b>${model.container.fvvkt}</b></font>	
			</td>
			
		</tr>
		<tr height="5"><td colspan="2">&nbsp;</td></tr>
			
			
		<tr>
		<td >
		<table border="0" width="99%" align="center">
			
           	<%-- ---------------------- --%>
           	<%-- LIST of existent ITEMs --%>
           	<%-- ---------------------- --%>
           	<tr>
				<td>
					<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    				<%-- separator --%>
	        			<tr height="5"><td></td></tr> 
						
						<tr>
							<td class="ownScrollableSubWindow" style="width:90%; height:15em;">
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr class="tableHeaderField" height="20" valign="left">
										<td width="2%" class="tableHeaderFieldFirst" title="Update"></td>
										<td width="2%" align="center" class="tableHeaderField" style="font-size: 12px"><span title="fflin2">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.linnr"/>&nbsp;</span></td>
							 			<td width="2%" align="center" class="tableHeaderField" style="font-size: 12px"><span title="ffunnr">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.unNr"/>&nbsp;</span></td>
							 			<td width="2%" align="center" class="tableHeaderField" style="font-size: 12px"><span title="ffembg">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.emg"/>&nbsp;</span></td>
							 			<td width="2%" align="right" class="tableHeaderField" style="font-size: 12px"><span title="ffantk">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.ant2"/>&nbsp;</span></td>
							 			<td width="2%" align="right" class="tableHeaderField" style="font-size: 12px"><span title="ffante">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.mengd"/>&nbsp;</span></td>
							 			<td width="2%" align="left" class="tableHeaderField" style="font-size: 12px"><span title="ffenh">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.unit"/>&nbsp;</span></td>
					        				
					        				<td width="2%" align="center" class="tableHeaderField" style="font-size: 12px"><span title="ffklas">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.fklas"/>&nbsp;</span></td>
					        				<td width="2%" align="center" class="tableHeaderField" style="font-size: 12px"><span title="ffsedd">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.fsedd"/>&nbsp;</span></td>
					        				<td width="2%" align="center" class="tableHeaderField" style="font-size: 12px"><span title="fftres">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.ftres"/>&nbsp;</span></td>
					        				<td width="2%" align="center" class="tableHeaderField" style="font-size: 12px"><span title="fffakt">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.ffakt"/>&nbsp;</span></td>
					        				
					        				
					        				<td width="2%" align="right" class="tableHeaderField" style="font-size: 12px"><span title="ffpoen/hepoen(Tot)">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.adr"/>&nbsp;</span></td>
						 				<td width="2%" align="center" class="tableHeaderField" style="font-size: 12px">&nbsp;<spring:message code="systema.transportdisp.delete" />&nbsp;</td>
					               </tr> 
					               
				 					  <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <tr class="tableRow" >
							                   
							               <td align="center" width="2%" class="tableCellFirst" >
							     				<a id="recordUpdate_${record.fflin2}" href="#" onClick="getItemData(this);">
							     					<c:choose>
								     					<c:when test="${not empty record.fflin2}">
						               						<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">&nbsp;
						               					</c:when>
						               					<c:otherwise>
						               						&nbsp;
						               					</c:otherwise>
					               					</c:choose>
					               				</a>
						               	   </td>
						               	   <td class="tableCell" width="2%" align="center">${record.fflin2}</td>
						               	   <td class="tableCell" width="2%" align="center">${record.ffunnr}</td>
						               	   <td class="tableCell" width="2%" align="center">${record.ffembg}</td>
						               	   <td class="tableCell" width="2%" align="right">${record.ffantk}</td>
						               	   <td class="tableCell" width="2%" align="right">${record.ffante}</td>
						               	   <td class="tableCell" width="2%" align="left">${record.ffenh}</td>
						               	   
						               	   <td class="tableCell" width="2%" align="center">${record.ffklas}</td>
						               	   <td class="tableCell" width="2%" align="center">${record.ffsedd}</td>
						               	   <td class="tableCell" width="2%" align="center">${record.fftres}</td>
						               	   <td class="tableCell" width="2%" align="center">${record.fffakt}</td>
						               	   
						               	   <td class="tableCell" width="2%" align="right">${record.ffpoen}</td>
						               	   
						               	   <%-- DELETE cell --%>							           
							               <td width="2%" class="tableCell" align="center">
							               	   <c:if test="${not empty record.fflin2}">
							                   		<a style="cursor:pointer;" id="lin2_${record.fflin2}" onClick="doDeleteItemLine(this);" tabindex=-1 >
									               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
									               	</a>&nbsp;
								               	</c:if>
					               		  </td> 
							            </tr>
								        <%-- this param is used ONLY in this JSP 
								        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" />
								        --%> 
								        <%-- this param is used throughout the Controller --%>
								        <c:set var="numberOfItemLinesInTopic" value="${Xrecord.svln}" scope="request" /> 
								        </c:forEach>
						           
						        </table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="5"><td></td></tr>
			<%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td>
	            	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
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
			
			<%-- -------------------------- --%>
			<%-- Validation errors on model --%>
			<%-- -------------------------- --%>
			<c:if test="${not empty model.errorMessage}">
				<tr>
				<td>
		           	<table class="tabThinBorderWhiteWithSideBorders" width="90%" align="left" border="0" cellspacing="0" cellpadding="0">
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
           	<tr>
	 			<td class="text14" align="left" >
					<input tabindex=-1  class="inputFormSubmitStd" type="button" name="newRecordButton" id="newRecordButton" value='<spring:message code="systema.transportdisp.createnew"/>'>
				</td>
			</tr>
			<tr height="5"><td class="text14" align="left" ></td></tr>
           	<tr>
	 			<td >
	 				<form action="transportdisp_workflow_dangerousgoods_edit.do" name="transportDispDangerousgoodsUpdateItemForm" id="transportDispDangerousgoodsUpdateItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
					<input type="hidden" name="avd" id="avd" value='${model.avd}'>
					<input type="hidden" name="opd" id="opd" value='${model.opd}'>
					<input type="hidden" name="linNr" id="linNr" value='${model.linNr}'>
					<input type="hidden" name="fflin2" id="fflin2" value='${model.record.fflin2}'>
					<input type="hidden" name="ffklas" id="ffklas" value='${model.record.ffklas}'>
					<input type="hidden" name="ffsedd" id="ffsedd" value='${model.record.ffsedd}'>
					<input type="hidden" name="fftres" id="fftres" value='${model.record.fftres}'>
					<input type="hidden" name="fffakt" id="fffakt" value='${model.record.fffakt}'>
					
					<input type="hidden" name="isModeUpdate" id="isModeUpdate" value="${model.record.isModeUpdate}">
					
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="90%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
	 					
				 		<tr height="15">
				 			<td class="text14White" align="left" >
				 				<b>&nbsp;&nbsp;<spring:message code="systema.transportdisp.itemline"/>&nbsp;</b>
 								<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">&nbsp;&nbsp;<b><label id="editLineNr">${model.record.fflin2}</label></b>
			 				</td>
		 				</tr>
	 				</table>
					<table width="90%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="12"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table  class="tableBorderWithRoundCornersGray" width="90%" border="0" cellspacing="0" cellpadding="0">
						 			<tr height="5"><td class="text" align="left"></td></tr>
						 			<tr >
							 			<td class="text14" align="left">&nbsp;<font class="text14RedBold" >*</font><span title="ffunnr">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.unNr"/></span></td>
								        <td class="text14" align="left">&nbsp;<span title="ffembg">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.emg"/></span></td>
								        <td class="text14" align="left">&nbsp;<span title="ffindx">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.emg.index"/></span></td>
								        <td class="text14" align="left">&nbsp;<span title="ffantk">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.ant2"/></span></td>
								        <td class="text14" align="left">&nbsp;<span title="ffante">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.mengd"/></span></td>
								        <td class="text14" align="left">&nbsp;<span title="ffenh">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.unit"/></span></td>
							        </tr>
							        <tr>
							        		<td class="text14" align="left" >
							        			<input required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="ffunnr" id="ffunnr" size="5" maxlength="4" value="${model.record.ffunnr}">
								        </td>
						        			<td class="text14" align="left" >
							        			<input type="text" class="inputTextMediumBlue" name="ffembg" id="ffembg" size="4" maxlength="3" value="${model.record.ffembg}">
								        </td>
								        <td class="text14" align="left" >
							        			<input type="text" class="inputTextMediumBlue" name="ffindx" id="ffindx" size="3" maxlength="2" value="${model.record.ffindx}">
								        </td>
								        <td class="text14" align="left" >
							        			<input onKeyPress="return amountKey(event)" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="ffantk" id="ffantk" size="6" maxlength="5" value="${model.record.ffantk}">
								        </td>
								        <td class="text14" align="left" >
							        			<input onKeyPress="return amountKey(event)" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" type="text" class="inputTextMediumBlueMandatoryField" name="ffante" id="ffante" size="13" maxlength="12" value="${model.record.ffante}">
								        </td>
								        <td class="text14" align="left" >
							        			<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" onChange="TODOvalidateDangerousGoodsUnnrNewLine();" name="ffenh" id="ffenh">
					 							<option value="">?</option>
					 							<option value="KG" <c:if test="${model.record.ffenh == 'KG'}"> selected </c:if> >KG</option>
					 							<option value="LTR" <c:if test="${model.record.ffenh == 'LTR'}"> selected </c:if> >LTR</option>
					 							<option value="GR" <c:if test="${model.record.ffenh == 'GR'}"> selected </c:if> >GR</option>
											</select>
								        </td>
							        </tr>
							        
							        <tr height="8"><td class="text" align="left"></td></tr>
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
			<tr height="20"><td colspan="2" ></td></tr>
			<tr height="30"><td></td></tr>
			
		</table>
		</td>
		</tr>
	</table>    
	
	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

