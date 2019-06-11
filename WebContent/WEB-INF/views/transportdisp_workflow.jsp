<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%> 
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTransportDisp.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/transportdispglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_workflow.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	
	
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
	
	/* this is in order to customize a SPECIFIC ui dialog in the .js file ...dialog() */
	.print-dialog-class .ui-widget-content{ background-color:lightsteelblue }
	.main-dialog-class .ui-widget-content{ background-image:none;background-color:lemonchiffon }
	
	/* this line will align the datatable search field in the left */
	.dataTables_wrapper .transpDispWorkflowFilter .dataTables_filter{float:left}
	</style>

<table width="100%" class="text14" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text14" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkOrderListId" style="display:block;" id="ordersOpen" href="transportdisp_mainorderlist.do?action=doFind&avd=${searchFilter.wssavd}">
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
					<font class="tabDisabledLink"><spring:message code="systema.transportdisp.workflow.trip.all.openorders.tab"/></font>&nbsp;<font class="text10Orange">F3</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
				<img style="vertical-align:bottom;" src="resources/images/list.gif" border="0" alt="general list">
				<font class="tabLink"><spring:message code="systema.transportdisp.workflow.trip.tab"/></font>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<div class="clazzOrderTripTab" <c:if test="${empty model.record.tupro}">style="visibility:collapse;"</c:if> >
				<a style="display:block;" class="ordersTripOpen" href="transportdisp_mainorderlist.do?action=doFind&wssavd=${model.record.tuavd}&wstur=${model.record.tupro}">
					<img title="Planning" style="vertical-align:bottom;" src="resources/images/math.png" width="16" height="16" border="0" alt="planning">
					<font class="tabDisabledLink"><font class="text14MediumBlue"><spring:message code="systema.transportdisp.open.orderlist.trip.label"/></font></font>
					<c:choose>
						<c:when test="${not empty model.record.tupro}">
							&nbsp;<font id="tuproTab" class="text14MediumBlue">&nbsp;${model.record.tupro}&nbsp;</font>
						</c:when>
						<c:otherwise>
							&nbsp;<font id="tuproTab" class="text12Gray">&nbsp;</font>
						</c:otherwise>
					</c:choose>
				</a>
				</div>
			</td>
			<td width="40%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
	</tr>

	<tr>
	<td>
	<%-- search filter component --%>
		<table width="100%" class="tabThinBorderWhiteWithSideBorders" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="15"><td></td></tr>
 	        <tr>
        		<td>
        		<form name="searchForm" id="searchForm" action="transportdisp_workflow.do?action=doFind" method="post" >
        		<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
        		<input type="hidden" name="filand" id="filand" value='${user.filand}'>
        		<input type="hidden" name="fkeysavd" id="fkeysavd" value='${searchFilter.wssavd}'>
				<input type="hidden" name="fkeysopd" id="fkeystur" value='${model.record.tupro}'>
	 	        
	 	        <table cellspacing="2">
	 	        <tr>
	 	        	<td valign="bottom" class="text14" align="left" >&nbsp;&nbsp;&nbsp;<span title="wssst"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.status"/></span></td>
	                	
	                <td valign="bottom" class="text14" align="left" >
                		<span title="wssavd"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.department"/></span>
		 				<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_avd.do?action=doInit','avdWin','top=150px,left=300px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
		 					<img id="imgAvdSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 				</a>
		 				<font id="objAvdGroupsList" class="text14SkyBlue" style="cursor:pointer;text-decoration: underline;">Grp</font>
                		
	                </td>
	                <td valign="bottom" class="text14" align="left" >&nbsp;&nbsp;&nbsp;<span title="wsstur"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.trip"/></span></td>
	                <td valign="bottom" class="text14" align="left" >&nbsp;&nbsp;&nbsp;<span title="wtusg"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.sign"/></span></td>
	                <td valign="bottom" class="text14" align="left" >&nbsp;&nbsp;&nbsp;<span title="wtubiln"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.trucknr"/></span></td>
	                <td valign="bottom" class="text14" align="left" >&nbsp;&nbsp;&nbsp;<span title="wtustef"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.from"/></span></td>
	                <td valign="bottom" class="text14" align="left" >
	                		&nbsp;&nbsp;&nbsp;<span title="wtudt/wtudt2"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.date"/></span>
	                		<img src="resources/images/calendar.gif" height="12px" width="12px" border="0" alt="date">
                	</td>
	                <td valign="bottom" class="text14" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.to"/></td>
	                <td valign="bottom" class="text14" align="left" >
	                		&nbsp;&nbsp;&nbsp;<span title="wtudtt/wtudtt2"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.date"/></span>
	                		<img src="resources/images/calendar.gif" height="12px" width="12px" border="0" alt="date">
	                </td>
	                <td>&nbsp;</td>
				</tr>
				<tr>
					<td class="text14" align="left" >
						<select class="inputTextMediumBlue" name="wssst" id="wssst">
								<option value="" <c:if test="${searchFilter.wssst == ''}"> selected </c:if> >Åpne</option>
								<option value="A" <c:if test="${searchFilter.wssst == 'A'}"> selected </c:if> >A-Stengde</option>
			            		<option value="B" <c:if test="${searchFilter.wssst == 'B'}"> selected </c:if> >B-Underveis</option>
			            		<option value="C" <c:if test="${searchFilter.wssst == 'C'}"> selected </c:if> >C-Ferdige</option>
			            		<option value="Z" <c:if test="${searchFilter.wssst == 'Z'}"> selected </c:if> >Alle</option>
			            		
						</select>
					</td>			       
	                <c:choose>
						<c:when test="${not empty searchFilter.wssavd}">	
			                <td align="left" >&nbsp;<input type="text" class="inputTextMediumBlueUPPERCASE" name="wssavd" id="wssavd" size="5" maxlength="4" value='${searchFilter.wssavd}'>&nbsp;
		                		<div id="divAvdGroupsList" style="display:none;position: relative;height:10em;" class="ownScrollableSubWindowDynamicWidthHeight" align="left" >
			 						
									<table id="tblAvdGroupsList" class="inputTextMediumBlueMandatoryField">
										<c:forEach items="${model.avdGroupsList}" var="record" varStatus="counter">  
										<tr>
											<td id="id_${record.agrKode}" OnClick="doPickAvdGroup(this)" class="tableHeaderFieldFirst" style="cursor:pointer;" ><font class="text14SkyBlue">${record.agrKode}</font></td>
											<td class="tableHeaderField">${record.agrNavn}</td>
										</tr>
										</c:forEach>
									</table>	
								</div>	
			                
			                </td>
		                </c:when>
		                <c:otherwise>
		                		<td align="left" >&nbsp;<input type="text" class="inputTextMediumBlueUPPERCASE" name="wssavd" id="wssavd" size="5" maxlength="4" value='${model.record.tuavd}'>&nbsp;
		                		
			                		<div id="divAvdGroupsList" style="display:none;position: relative;height:10em;" class="ownScrollableSubWindowDynamicWidthHeight" align="left" >
				 						
										<table id="tblAvdGroupsList" class="inputTextMediumBlueMandatoryField">
											<c:forEach items="${model.avdGroupsList}" var="record" varStatus="counter">  
											<tr>
												<td id="id_${record.agrKode}" OnClick="doPickAvdGroup(this)" class="tableHeaderFieldFirst" style="cursor:pointer;" ><font class="text14SkyBlue">${record.agrKode}</font></td>
												<td class="tableHeaderField">${record.agrNavn}</td>
											</tr>
											</c:forEach>
										</table>	
									</div>	
		                		</td>
		                </c:otherwise>
	                </c:choose>
					<td align="left" >&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueUPPERCASE" name="wsstur" id="wsstur" size="9" maxlength="8" value='${searchFilter.wsstur}'>&nbsp;</td>
					<td align="left" >&nbsp;<input type="text" class="inputTextMediumBlueUPPERCASE" name="wtusg" id="wtusg" size="5" maxlength="5" value='${searchFilter.wtusg}'>&nbsp;</td>
					<td align="left" >&nbsp;<input type="text" class="inputTextMediumBlueUPPERCASE" name="wtubiln" id="wtubiln" size="10" maxlength="10" value='${searchFilter.wtubiln}'>&nbsp;</td>
					<td align="left" >&nbsp;<input type="text" class="inputTextMediumBlueUPPERCASE" name="wtustef" id="wtustef" size="6" maxlength="5" value='${searchFilter.wtustef}'>&nbsp;</td>
					<td align="left" >&nbsp;
						<input type="text" class="inputTextMediumBlue" name="wtudt" id="wtudt" size="9" maxlength="8" value='${searchFilter.wtudt}'>
						-<input type="text" class="inputTextMediumBlue" name="wtudt2" id="wtudt2" size="9" maxlength="8" value='${searchFilter.wtudt2}'>
						
					</td>
					<td align="left" >&nbsp;<input type="text" class="inputTextMediumBlueUPPERCASE" name="wtustet" id="wtustet" size="6" maxlength="5" value='${searchFilter.wtustet}'>&nbsp;</td>
					<td align="left" >&nbsp;
						<input type="text" class="inputTextMediumBlue" name="wtudtt" id="wtudtt" size="9" maxlength="8" value='${searchFilter.wtudtt}'>
						-<input type="text" class="inputTextMediumBlue" name="wtudtt2" id="wtudtt2" size="9" maxlength="8" value='${searchFilter.wtudtt2}'>
					</td>
					<td valign="bottom" align="left" >&nbsp;
						<input class="inputFormSubmit" type="submit" name="submitSearch" id="submitSearch" name="submitSearch" value='<spring:message code="systema.transportdisp.search"/>'>
	                </td>
				</tr>
				</table>
				</form>
				</td>
			</tr>
			<%-- -------------------------- --%>
			<%-- Validation errors on model --%>
			<%-- -------------------------- --%>
			<c:if test="${not empty model.errorMessage}">
				<tr height="2"><td></td></tr>
			
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
				<tr height="5"><td></td></tr>
			</c:if>

		</table>		
	</td>
	</tr>
	
		<tr>
		<td>
			<%-- this table wrapper is necessary to apply the css class with the thin border --%>
			<table style="width:100%;" id="wrapperTable" class="tabThinBorderWhite" cellspacing="1">
			<tr height="2"><td></td></tr> 
			<%-- -------------------- --%>
			<%-- Datatables component --%>
			<%-- -------------------- --%>
			<%-- list component --%>
			<c:if test="${not empty list}">
				<c:if test="${not empty model.containerTripList.maxWarning}">
					<tr>	
						<td class="listMaxLimitWarning">
						<img style="vertical-align:bottom;" src="resources/images/redFlag.png" width="16" height="16" border="0" alt="Warning">
						${model.containerTripList.maxWarning}</td>
					</tr>
					<tr height="5"><td></td></tr>
				</c:if>
				<tr>
					<td>
					<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
						 the cosmetic frame will not follow the whole datatable grid including the search field... --%>
					<table id="containerdatatableTable" width="100%" cellspacing="1" align="left">
					<tr>
					<td>
					<%-- this is the datatables grid (content) --%>
					<table id="workflowTrips" class="display compact cell-border">
						<thead>
						<tr class="tableHeaderField" height="20">
						    <th title="Update" width="2%" class="text14" width="2%"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.update"/></th>
						    <th title="Avd." width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.department"/>&nbsp;</th>
						    <th title="Tur" width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.trip"/>&nbsp;</th>
						    <th title="Signatur" width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.sign"/>&nbsp;</th>  
		                    <th title="Bilnr." width="2%" class="text14">
		                    		<img style="vertical-align: bottom;" src="resources/images/lorry_green.png" width="15px" height="15px" border="0" alt="lorry no.">
		                    		<spring:message code="systema.transportdisp.workflow.trip.list.search.label.trucknr"/>
                    		</th>
                    		<th title="Oppdragstype" width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.ordertype"/>&nbsp;</th>
						    <th title="PDA status" width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.pda.status"/>&nbsp;</th>
						    <th title="Fra" width="2%" class="text14" nowrap>
			                    	<spring:message code="systema.transportdisp.workflow.trip.list.search.label.from"/>

			                </th>
		                    <th title="Fra dato" width="2%" class="text14">
		                    		<spring:message code="systema.transportdisp.workflow.trip.list.search.label.date"/>&nbsp;
	                    	</th>
   		                    <th title="Fra kl" width="2%" class="text14">
	                    		<img style="vertical-align:bottom;" src="resources/images/clock2.png" width="12" height="12" border="0" alt="time">&nbsp;
   		            		</th> 
		                    <th title="Til" width="2%" class="text14" nowrap>
	                    		<spring:message code="systema.transportdisp.workflow.trip.list.search.label.to"/>
		                    </th>
		                    <th title="Til dato" width="2%" class="text14">
		                    		<spring:message code="systema.transportdisp.workflow.trip.list.search.label.date"/>&nbsp;
                    		</th>
   		                    <th title="Til kl" width="2%" class="text14">
	                    		<img style="vertical-align:bottom;" src="resources/images/clock2.png" width="12" height="12" border="0" alt="time">&nbsp;
  		            		</th> 
		                    
		                    <th title="Antall opd" width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.antopd"/>&nbsp;</th>
		                    <th title="Antall pod" width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.antpod"/>&nbsp;</th>
		                    
		                    <th title="Vekt" width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.weight"/>&nbsp;</th>
		                    <th title="M3" width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.m3"/>&nbsp;</th>
		                    <th title="Lastemeter" width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.lm"/>&nbsp;</th>
		                    <th title="Farliggods" width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.fg"/>&nbsp;</th>
		                    <th title="Resultat i %" width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.res"/>&nbsp;</th>
		                    <th title="Rundtur" width="2%" align="right" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.roundTrip"/>&nbsp;</th>
		                    <th title="Kopi Tur" width="2%" width="1%" align="left" class="text14">&nbsp;</th>

		                    <%--
		                    <th title="Close/Open" width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.closeopen"/>&nbsp;</th>
		                     --%>
		                    <th title="Steng en eller flere" width="2%" class="text14" align="center">
		                    		<input title="Steng en eller flere" style="cursor:pointer;" type="button" value="S." name="currentordersColumnHeaderButtonClose" id="currentordersColumnHeaderButtonClose" onClick="getValidCheckisCloseOpen(this);">
		                    </th>		
	                    
		                    <th title="Godsliste print status" width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.gp"/>&nbsp;</th>
		                    
		                    <th title="Print" width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.printDocs"/>&nbsp;</th>
		                    <th title="Upload" width="2%" class="text14">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.list.search.label.upl"/>&nbsp;</th>
		                    
		                </tr> 
		                </thead>
		                <tbody>
		                 <c:forEach items="${list}" var="record" varStatus="counter">    
			               <tr class="text14 tableRow" >
			             
			               <td width="3%" align="center" nowrap style="cursor:pointer;" class="text14 tableCellGray" id="avd_${record.tuavd}@tripnr_${record.tupro}@status_${record.turclose}@${counter.count}">
			               		<div style="line-height: 25px;line-width: 50px;" id="dtuavd${record.tuavd}_dtupro${record.tupro}_onlist${counter.count}" ondrop="drop(event)" ondragenter="highlightDropArea(event)" ondragleave="noHighlightDropArea(event)" ondragover="allowDrop(event)" >
			               		&nbsp;<img title="Edit trip ${record.tupro}" style="vertical-align:middle;cursor:pointer;" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
			               		<%--<font class="text11MediumBlue"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.edit"/></font> --%>
			               		</div>
			               </td>	
			               <td width="3%" align="center" class="text14 tableCellGray">
			               		${record.tuavd}
			               </td>
			               <td width="3%" nowrap align="left" class="textMediumBlue tableCellGray" id="htmlpost_${counter.count}">
			               	   <c:choose>
				               <c:when test="${record.turclose=='close'}"> 
			               			<a id="alinkTripListId_${counter.count}" onClick="setBlockUI(this);" style="display:block;" href="transportdisp_mainorderlist.do?action=doFind&wssavd=${record.tuavd}&wstur=${record.tupro}">
										<div style="line-height: 25px;line-width: 100px;" id="dtuavd${record.tuavd}_dtupro${record.tupro}_onlistA${counter.count}" ondrop="drop(event)" ondragenter="highlightDropArea(event)" ondragleave="noHighlightDropArea(event)" ondragover="allowDrop(event)" > 
										<img title="Trip planning" src="resources/images/math.png" width="14px" height="14px" border="0" alt="planning">
										<font class="textMediumBlue">${record.tupro}</font>
										</div>
			            	       		</a>
			            	       </c:when>
				               <c:otherwise>
				               		<span title="record.turclose is not = close...">&nbsp;${record.tupro}</span>
				               </c:otherwise>
				               </c:choose>
			               </td>
			               	
			               
			               <td width="3%" class="text14 tableCellGray">&nbsp;${record.tusg}</td>
			               <td width="3%" class="text14 tableCellGray">&nbsp;${record.tubiln}</td>
			               <td width="3%" class="text14 tableCellGray">&nbsp;${record.tuopdt}</td>
			               <td width="3%" align="center" class="text14 tableCellGray" >
			               	<c:if test="${not empty record.pdaStat}">
				               <c:choose>
					               <c:when test="${record.pdaStat=='inWork'}">
					               		<img title="inProcess" src="resources/images/engines.png" width="18px" height="18px" border="0" alt="PDA">
					               </c:when>
					               <c:otherwise>
					               		<c:choose>
						               		<c:when test="${record.pdaStat=='finished'}">
						               			<img title="finished" src="resources/images/checkmarkOK.png" width="14px" height="14px" border="0" alt="PDA">
						               		</c:when>
						               		<c:otherwise>
						               			<img title="assigned" src="resources/images/warning.png" width="18px" height="18px" border="0" alt="PDA">
						               		</c:otherwise>
					               		</c:choose>
					               </c:otherwise>
					           </c:choose> 
				           </c:if>   
			               </td>
	            		   <td width="3%" class="text14 tableCellGray">&nbsp;${record.tustef}</td>
	            		   <td width="3%" class="text14 tableCellGray">
	            		   	<c:if test="${not empty record.tudt && fn:startsWith(record.tudt, '20')}">
	            		   		<fmt:parseDate value="${record.tudt}" var="dateEtdDate" pattern="yyyyMMdd" />
	            		   		&nbsp;<fmt:formatDate pattern="yyyyMMdd" value="${dateEtdDate}"/>
	            		   	</c:if>
	            		   </td>
	            		   <td width="3%" class="text14 tableCellGray">&nbsp;${record.tutm}</td>
	            		   <td width="3%" class="text14 tableCellGray">&nbsp;${record.tustet}</td>
	            		   <td width="3%" class="text14 tableCellGray">
            		   		<c:if test="${not empty record.tudtt && fn:startsWith(record.tudtt, '20')}">
	            		   		<fmt:parseDate value="${record.tudtt}" var="dateEtaDate" pattern="yyyyMMdd" />
	            		   		&nbsp;<fmt:formatDate pattern="yyyyMMdd" value="${dateEtaDate}"/>
	            		   	</c:if>
	            		   </td>
	            		   <td width="3%" class="text14 tableCellGray">&nbsp;${record.tutmt}</td>
	            		   
	            		   <td width="3%" align="center" class="text14 tableCellGray">&nbsp;${record.tuao}</td>
	            		   <td width="3%" align="center" class="text14 tableCellGray">&nbsp;${record.podTxt}</td>
	            		   
	            		   <td width="3%" align="right" class="text14 tableCellGray">&nbsp;${record.tutvkt}&nbsp;</td>
	            		   <td width="3%" align="right" class="text14 tableCellGray">&nbsp;${record.tutm3}&nbsp;</td>
	            		   <td width="3%" align="right" class="text14 tableCellGray">&nbsp;${record.tutlm2}&nbsp;</td>
	            		   <td width="3%" align="right" class="text14 tableCellGray">&nbsp;${record.tupoen}&nbsp;</td>
	            		   <td width="3%" align="right" class="text14 tableCellGray">&nbsp;${record.tures}&nbsp;</td>
	            		   <td width="1%" align="right" class="text14 tableCellGray">${record.turund}</td>
		            	   <td width="1%" align="left" class="text14">
		            	   		<a title="copy" class="copyLink" id="copyLink${counter.count}" runat="server" href="#">
									<img style="vertical-align:middle;" title="Copy-Round trip" src="resources/images/copy.png" width="12px" height="12px" border="0" alt="copy">
								</a>
								<div style="display: none;" class="clazz_dialog" id="dialog${counter.count}" title="Dialog">
									<form  action="transportdisp_workflow_copyRoundTrip.do" name="copyForm${counter.count}" id="copyForm${counter.count}" method="post">
									 	<input type="hidden" name="action${counter.count}" id="action${counter.count}" value='doUpdate'/>
										<input type="hidden" name="originalAvd${counter.count}" id="originalAvd${counter.count}" value='${record.tuavd}'/>
					 					<input type="hidden" name="originalTrip${counter.count}" id="originalTrip${counter.count}" value='${record.tupro}'/>
						 				<input type="hidden" name="sign${counter.count}" id="sign${counter.count}" value='${user.signatur}'/>
						 				
										<p class="text14" >Du kan velge en ny avdeling</p>
										<p class="text14" >En ny tur vil bli etablert automatisk.</p>
										
										<table>
											<tr>
												<td class="text14" align="left" >Ny Avd.</td>
												<td class="text14MediumBlue">
													<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue newAvd" name="newAvd${counter.count}" id="newAvd${counter.count}" size="5" maxlength="4" value="${record.tuavd}">
												</td>
	                						</tr>
										</table>
									</form>
								</div>
	            		   </td>
	            		   <%-- Close Open one-on-one --> REPLACE with bulk-close-open in next cell
	            		   <td width="3%" align="center" class="text14 tableCellGray">
	            		   		<c:choose>	
		            		   		<c:when test="${record.turclose=='close'}">
					           		<a href="transportdisp_workflow_closeOpenTrip.do?user=${user.user}&tuavd=${record.tuavd}&tupro=${record.tupro}&tust=A">
	    		    					<img title="Close" style="vertical-align:bottom;" src="resources/images/close.png" width="15" hight="15" border="0" alt="close">
				   					</a><font class="text12Bold" >${record.tust}</font>
			   					</c:when>
			   					<c:otherwise>
									<a href="transportdisp_workflow_closeOpenTrip.do?user=${user.user}&tuavd=${record.tuavd}&tupro=${record.tupro}&tust=">
	    		    					<img title="Open" style="vertical-align:bottom;" src="resources/images/open.png" width="18" hight="18" border="0" alt="open">
				   					</a><font class="text12Bold" >${record.tust}</font>
			   					</c:otherwise>
		   					</c:choose>
	            		   	</td>
	            		   	--%>
	            		   	<td width="2%" class="text14 tableCellGray" align="center">
	            		   		<c:choose>	
		            		   		<c:when test="${record.turclose=='close'}">
					           		<input class="clazz_checkis_closeOpen" type="checkbox" id="checkis_closeOpen${counter.count}@user=${user.user}&tuavd=${record.tuavd}&tupro=${record.tupro}&tust=A" >
			   					</c:when>
			   					<c:otherwise>
									<input class="clazz_checkis_closeOpen" type="checkbox" id="checkis_closeOpen${counter.count}@user=${user.user}&tuavd=${record.tuavd}&tupro=${record.tupro}&tust=" >
			   					</c:otherwise>
		   					</c:choose>	            		   	
			             </td>
	            		   
	            		   <td width="3%" align="center" class="text14 tableCellGray">&nbsp;${record.tutst1}&nbsp;</td>
	            		   <td width="3%" align="center" class="textMediumBlue">
			               		<a title="print Tur.&nbsp;${record.tupro}" class="printLink" id="printLink${counter.count}" runat="server" href="#">
									<img style="vertical-align: middle;" src="resources/images/printer3.png" width="20px" height="20px" border="0" alt="Print">
								</a>
								<div style="display: none;" class="clazz_dialogPrint" id="dialogPrint${counter.count}" title="Dialog Print">
										<form id="printFormOnList${counter.count}">
										<input type="hidden" id="tur${counter.count}" name="tur${counter.count}" value="${record.tupro}">
										<input type="hidden" id="avd${counter.count}" name="avd${counter.count}" value="${record.tuavd}">
										<input type="hidden" id="sign${counter.count}" name="sign${counter.count}" value="${record.tusg}">
									 	<table>
					   						<tr height="3"><td></td></tr>
					   						<tr>
												<td colspan="2" class="text14" align="left" >
													<input type="checkbox" name="fbType${counter.count}" id="fbType${counter.count}" value="fb">
													Fraktbrev
												</td>	
					   						</tr>
					   						<tr>
												<td colspan="2" class="text14" align="left" >
													<input type="checkbox" name="cmrType${counter.count}" id="cmrType${counter.count}" value="cmr">
													CMR-Fraktbrev
												</td>	
					   						</tr>
					   						<tr>
												<td colspan="2" class="text14" align="left" >
													<input type="checkbox" name="ffType${counter.count}" id="ffType${counter.count}" value="ff">
													Ferdigmeldte-fakturaer
												</td>	
					   						</tr>
					   						<tr>
												<td colspan="2" class="text14" align="left" >
													<input type="checkbox" name="aordType${counter.count}" id="aordType${counter.count}" value="I">
													<span id="alinkAordPdfi" >Arbeidsordre Intern</span>													
												</td>
					   						</tr>
					   						<tr>
												<td colspan="2" class="text14" align="left" >
													<input type="checkbox" name="aordTypee${counter.count}" id="aordTypee${counter.count}" value="E">
													<span id="alinkAordPdfe" >Arbeidsordre Ekstern</span>													
												</td>
					   						</tr>
					   						<tr>
												<td class="text14" align="left" >
													<input type="checkbox" name="godslistType${counter.count}" id="godslistType${counter.count}" value="gl">
													<span class="clazz_alinkGodslistePdf" id="alinkGodslistePdf${counter.count}" style="text-decoration: underline;" onMouseOver="style='color:lemonchiffon;cursor:pointer;text-decoration: underline;'" onMouseOut="style='color:black;text-decoration: underline;'">Godsliste</span>
												</td>	
												<td class="text14" align="left" >	
													<img class="clazz_imgGodslistePdf" id="imgGodslistePdf${counter.count}" title="G.l" style="vertical-align:middle;cursor:pointer;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="Godsliste PDF">
												</td>
					   						</tr>
					   						<tr>
												<td class="text14" align="left" >
													<input type="checkbox" name="lastlistType${counter.count}" id="lastlistType${counter.count}" value="ll">
													<span class="clazz_alinkLastlistePdf" id="alinkLastlistePdf${counter.count}" style="text-decoration: underline;" onMouseOver="style='color:lemonchiffon;cursor:pointer;text-decoration: underline;'" onMouseOut="style='color:black;text-decoration: underline;'">Lasteliste</span>
												</td>
												<td class="text14" align="left" >	
													<img class="clazz_imgLastlistePdf" id="imgLastlistePdf${counter.count}" title="LL PDF" style="vertical-align:middle;cursor:pointer;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="LL. PDF">
												</td>
					   						</tr>
					   						
					   						<tr>
												<td class="text14" align="left" >
													<input type="checkbox" name="turkonvoluttType${counter.count}" id="turkonvoluttType${counter.count}" value="tk">
													<span class="clazz_alinkTurkonvoluttTypePdf" id="alinkTurkonvoluttTypePdf${counter.count}" >Turkonvolutt</span>
												</td>
												
					   						</tr>
					   						
					   						<tr height="15"><td></td></tr>
											<tr>
												<td colspan="4" class="text14MediumBlue" align="left">
													<label id="printStatus${counter.count}"></label>
												</td>
											</tr>
										</table>
										</form>
								</div>
							</td>
	            		   
	            		   
	            		   
	            		   <td width="3%" align="center" class="text14 tableCellGray">
	            		   		<input class="inputFormSubmit11Slim" type="button" value="Upload" name="uplButton${counter.count}" onClick="window.open('transportdisp_workflow_childwindow_uploadFile.do?action=doInit&wstur=${record.tupro}','transpDispWorklistFileUpload','top=300px,left=800px,height=250px,width=330px,scrollbars=no,status=no,location=no')">	 
	            		   </td>
	            		   <%--
	            		   <td align="center" class="text14 tableCellGray">
            		   			 <form name="uploadFileForm_${counter.count}" id="uploadFileForm_${counter.count}" method="post" enctype="multipart/form-data">
	            		   		 	<input ondragenter="myFileUploadDragEnter(event,this)" ondragleave="myFileUploadDragLeave(event,this)" class="tableBorderWithRoundCornersLightYellow noFileChosenTransparent" style="height:25px;display:block;" onChange="uploadFile(this);" type="file" name="file_${counter.count}" id="file_${counter.count}" />
	            		   		 	
	            		   		 	<%-- everything below this line will be hidden for the end-user but not for jquery
	            		   		 	<input type="hidden" name="applicationUserUpload_${counter.count}" id="applicationUserUpload_${counter.count}" value='${user.user}'>
									<input type="hidden" name="wstur_${counter.count}" id="wstur_${counter.count}" value='${record.tupro}'>
									 <div class="text14" style="position: relative;" align="left">
										<span style="position:absolute; left:0px; top:0px; width:250px" id="upload_phantom" class="popupWithInputText"  >
											<select class="inputText14" name="wstype_${counter.count}" id="wstype_${counter.count}">
												<c:forEach var="record" items="${user.arkivKodTurList}" >
						                       	 	<option value="${record.arkKod}">${record.arkKod}</option>
												</c:forEach> 
											</select>
											<input class="inputFormSubmit" type="button" name="submitUpload_${counter.count}" id="submitUpload_${counter.count}" value='Save'>
										</span>
									</div>
								</form>
	            		   </td>
	            		    --%>
			            </tr> 
		            	</c:forEach>
		            </tbody>
		            </table>
		            </td>
	            		</tr>
	            		<tr>
	            		<td align="right" class="text12">
	            		<table >
						<tr>
							<td>	
								<a href="transportDispWorkflowTripListExcelView.do" target="_new">
			                		<img valign="bottom" id="tripListExcel" src="resources/images/excel.gif" width="14" height="14" border="0" alt="excel">
			                		<font class="text12MediumBlue">&nbsp;Eksport til Excel</font>
			 	        		</a>
			 	        		&nbsp;
		 	        		</td>
		 	        		<td class="text12" width="20px">&nbsp;</td>
	 	        		</tr>
	 	        		</table>
			 		</td>
		         	</tr>
	            		</table> <%--containerdatatableTable END --%>
	            		</td>
	            </tr>
	           </c:if> 
	            
	            
	            
            	<tr height="2"><td></td></tr>
	            
	            <%-- Validation errors --%>
				<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
				<tr>
					<td>
			           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
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
				
	            <tr>
	            		<td>
	            		<c:choose>
						<c:when test="${not empty searchFilter.wssst}">	
			                <c:set var="requestParamWssst" scope="request" value="${searchFilter.wssst}"/>
		                </c:when>
		                <c:otherwise>
		                		<c:set var="requestParamWssst" scope="request" value="${model.record.tust}"/>
		                </c:otherwise>
	                </c:choose>
	                <c:choose>
	                
						<c:when test="${not empty searchFilter.wssavd}">	
			                <c:set var="requestParamWssavd" scope="request" value="${searchFilter.wssavd}"/>
		                </c:when>
		                <c:otherwise>
		                		<c:set var="requestParamWssavd" scope="request" value="${model.record.tuavd}"/>
		                </c:otherwise>
	                </c:choose>
	                	<%-- Replace bt updCancelButton  (Kingsr.NO--> requirement)
	            			<input style="cursor:pointer;" type="button" value="<spring:message code="systema.transportdisp.workflow.trip.form.button.createnew.trip"/>" name="cnButton" onClick="location.href = 'transportdisp_workflow.do?action=doFind&wssst=${requestParamWssst}&wssavd=${requestParamWssavd}'">
	            		--%>
	            		</td>
	           	</tr>
	            
	            <%-- FORM HEADER --%>
		 		<tr>
	            		<td>
		        			<table width="98%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
					 		<tr height="15">
					 			<td class="text14White">
									&nbsp;<spring:message code="systema.transportdisp.workflow.trip.form.label.header.workWithTrip"/>&nbsp;<img style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit">
				 				</td>
			 				</tr>
		 				</table>
	            		</td>
	            </tr>
	            <%-- FORM DETAIL --%>
		 		<tr id="formcontainer" ondrop="drop(event)" ondragenter="highlightDropArea(event)" ondragleave="noHighlightDropArea(event)" ondragover="allowDrop(event)" >
	            		<td>
	            			<form action="transportdisp_workflow_edit.do?action=doUpdate" name="transportdispForm" id="transportdispForm" method="post">
	            			<%-- <input type="hidden" name="tuavd" id="tuavd" value='${model.record.tuavd}'> --%>
	            			<input type="hidden" name="tupro" id="tupro" value='${model.record.tupro}'>
	            			<input type="hidden" name="turund" id="turund" value='${model.record.turund}'>
	            			<input type="hidden" name="tutref" id="tutref" value='${model.record.tutref}'>
	            			<input type="hidden" name="originalAvd" id="originalAvd" value='${searchFilter.wssavd}'>
	            			<table width="98%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
					 		<tr height="10"><td ></td></tr>
					 		<tr>
								<td colspan="2" valign="top">
									<table style="width:51%" cellspacing="1" cellpadding="0">
							 			<tr>
								 			<td class="text14" nowrap>
									 			<font class="text14"><b><spring:message code="systema.transportdisp.workflow.trip.list.search.label.dept"/></b>&nbsp;</font>
									 			<c:choose>
							 				    <c:when test="${not empty model.record.tupro}">
													<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="tuavd" id="tuavd" size="4" value='${model.record.tuavd}'>
									 			</c:when>
									 			<c:otherwise>
									 				<c:choose>
							 				    	<c:when test="${not empty model.record.tuavd}">
										 				<input type="text" class="inputTextMediumBlue" name="tuavd" id="tuavd" size="4" maxlength="4" value='${model.record.tuavd}'>
									 				</c:when>
									 				<c:otherwise>
										 				<input type="text" class="inputTextMediumBlue" name="tuavd" id="tuavd" size="4" maxlength="4" value='${searchFilter.wssavd}'>
										 				<div style="display:inline-block;" class="clazzAvdCreateNew" >
											 				<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_avd.do?action=doInit&status=a','avdWin','top=150px,left=300px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
				 												<img id="imgAvdSearchOnCreateNew" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
				 											</a>
			 											</div>
		 											</c:otherwise>
		 											</c:choose>
		 											
									 			</c:otherwise>
									 			</c:choose>
									 				<span title="tusg"><b><spring:message code="systema.transportdisp.workflow.trip.list.search.label.sign"/></b></span>
									 			<c:choose>
										 			<c:when test="${not empty model.record.tupro}">
											 			<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="tusg" id="tusg" size="5" value='${model.record.tusg}'>
									 				</c:when>
									 				<c:otherwise>
									 					<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="tusg" id="tusg" size="5" value='${user.signatur}'>
									 				</c:otherwise>
								 				</c:choose>
								 				<span title="tuproJS"><b><spring:message code="systema.transportdisp.workflow.trip.list.search.label.trip"/>:</b></span>
									 			<div style="display:inline-block;" class="clazzOrderTripTab" <c:if test="${empty model.record.tupro}">style="visibility:collapse;"</c:if> >
													<a class="ordersTripOpen" style="display:block" href="transportdisp_mainorderlist.do?action=doFind&wssavd=${model.record.tuavd}&wstur=${model.record.tupro}">
										 				<label id="tuproJS" class="text14MediumBlue" style="cursor:pointer;">${model.record.tupro}</label>
										 			</a>
									 			</div>
									 			
								 			</td>
								 			<td nowrap class="text14" ><span title="turundJS"><b><spring:message code="systema.transportdisp.workflow.trip.list.search.label.roundTrip"/>:</b></span>
								 				<label id="turundJS" class="text14MediumBlue" >${model.record.turund}</label>
								 			</td>
					 						<td nowrap align="right">
						 						<c:choose>
								 				    <c:when test="${ not empty model.record.tupro}">
								 				    	<div id="divSmsEmailButtons" style="display:inline">
								 				    		<img id="printImg" name="printImg" title="Print" style="vertical-align: bottom;cursor:pointer;" src="resources/images/printer3.png" width="28px" height="28px" border="0" alt="Print"> 
									 						<button name="smsButton" id="smsButton" class="buttonGrayWithGreenFrame" type="button" >Send SMS</button>
									 						<button name="emailButton" id="emailButton" class="buttonGrayWithGreenFrame" type="button" >Send Mail</button>
						 									<button name="budgetButton" id="budgetButton" class="buttonGrayWithGreenFrame" type="button" >Budsjett/rekv.</button>
					 									</div>
								 				    	<input class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" value='<spring:message code="systema.transportdisp.submit.save"/>'/>
								 				    	<input class="inputFormSubmitGray" type="button" name="updCancelButton" id="updCancelButton" value='<spring:message code="systema.transportdisp.cancel"/>'>
								 				    </c:when>
								 				    <c:otherwise>
								 				    	<div id="divSmsEmailButtons" style="display:none">
								 				    		<img id="printImg" name="printImg" title="Print" style="vertical-align: bottom;cursor:pointer;" src="resources/images/printer3.png" width="28px" height="28px" border="0" alt="Print"> 
									 						<button name="smsButton" id="smsButton" class="buttonGrayWithGreenFrame" type="button" >Send SMS</button>
									 						<button name="emailButton" id="emailButton" class="buttonGrayWithGreenFrame" type="button" >Send Mail</button>
						 									<button name="budgetButton" id="budgetButton" class="buttonGrayWithGreenFrame" type="button" >Budsjett/rekv.</button>
						 									
					 									</div>
							 				    		<input class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" value='<spring:message code="systema.transportdisp.submit.createnew"/>'/>
							 				    		<input class="inputFormSubmitGray" type="button" name="updCancelButton" id="updCancelButton" value='<spring:message code="systema.transportdisp.cancel"/>'>
								 				    </c:otherwise>	
							 				    </c:choose>
						 				    </td>
								    		</tr>
							 		</table>
								</td>
							</tr>	
					 		
					 		<tr>
					 			<td valign="top" >
					 			 <table width="98%" class="tableBorderWithRoundCornersLightGray" cellspacing="1" cellpadding="0">
							 		<tr height="10"><td >&nbsp;</td></tr>
								    	<tr>
								    		<td class="text14">
								    			&nbsp;<img onMouseOver="showPop('invperiod_info');" onMouseOut="hidePop('invperiod_info');" style="vertical-align: bottom;" src="resources/images/info3.png" width="12px" height="12px" border="0" alt="info">
								    			<span title="centuryYearTurccTuraar/turmnd">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.period"/><font class="text12RedBold" >*</font>
							    				</span>
								    			<div class="text11" style="position: relative;" align="left">
						 						<span style="position:absolute; width:200px; left:0px; top:0px;" id="invperiod_info" class="popupWithInputText"  >
						 							<font class="text11">
									           			<b>Invoice Period</b>
									           			<div>
									           			<p>Always a year and month</p>
									           			</div>
								           			</font>
												</span>
											</div>
								    			
							    			</td>
								    		<td>
								    			<select class="inputTextMediumBlueMandatoryField" name="centuryYearTurccTuraar" id="centuryYearTurccTuraar">
							            		<option value=""><spring:message code="systema.transportdisp.dropdown.default"></spring:message></option>
							 				  	<c:forEach var="record" items="${model.yearList}" >
						                       	 	<option value="${record}"<c:if test="${model.record.centuryYearTurccTuraar == record}"> selected </c:if> >${record}</option>
												</c:forEach> 
												</select>	
													
								    			<select class="inputTextMediumBlueMandatoryField" name="turmnd" id="turmnd">
							            		<option value=""><spring:message code="systema.transportdisp.dropdown.default"></spring:message></option>
							 				  	<c:forEach var="record" items="${model.monthList}" >
						                       	 	<option value="${record}"<c:if test="${model.record.turmnd == record}"> selected </c:if> >${record}</option>
												</c:forEach> 
											</select>
								    		</td>
								    	</tr>
								    	<tr height="5"><td ></td></tr>
								    	<tr>
								    		<td class="text14">
								    			<span title="tubiln">
								    				&nbsp;<img style="vertical-align: bottom;" src="resources/images/lorry_green.png" height="16px" width="16px" border="0" alt="edit">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic"/><font class="text12RedBold" >*</font>
								    			</span>
							    			</td>
								    		<td class="text14" nowrap><input type="text" class="inputTextMediumBlueUPPERCASEMandatoryField" name="tubiln" id="tubiln" size="10" maxlength="8" value="${model.record.tubiln}">
								    		<a tabindex=0 id="tubilnIdLink" >
								    			<img id="imgTruckLicSearch" style="cursor:pointer; vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
		 									</a>
								    		
								    		</td>
								    		<td class="text14"><span title="tulk"><spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.countryCode"/></span></td>
								    		<td class="text14">
								    			<select class="inputTextMediumBlueMandatoryField" name="tulk" id="tulk">
						 						<option value=""><spring:message code="systema.transportdisp.dropdown.default"></spring:message></option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.zkod}"<c:if test="${model.record.tulk == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="tulkIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
											</td>

								    		<td class="text14"><span title="tuheng"><spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.henger"/></span>
								    			<input type="text" class="inputTextMediumBlueUPPERCASE" name="tuheng" id="tuheng" size="10" maxlength="10" value="${model.record.tuheng}">
								    		</td>
								    		<td class="text14" align="center">
								    			<span title="tulkh"><spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.countryCode"/></span>
								    		</td>
								    		<td class="text14" >
								    			<select class="inputText14" name="tulkh" id="tulkh">
						 						<option value=""><spring:message code="systema.transportdisp.dropdown.default"></spring:message></option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.zkod}"<c:if test="${model.record.tulkh == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>  
												</select>
												<a tabindex="-1" id="tulkhIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
												
							    			</td>
							    			
								    	</tr>
									    <tr>
								    		<td class="text14">
								    			<span title="tubilk">
								    				&nbsp;<img style="vertical-align: bottom;" src="resources/images/lorry_green.png" height="16px" width="16px" border="0" alt="edit">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.trucktype"/><font class="text12RedBold" >*</font>
							    				</span>
							    			</td>
								    		<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASEMandatoryField" name="tubilk" id="tubilk" size="4" maxlength="3" value="${model.record.tubilk}">
								    			<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_bilcode.do?action=doInit','bilcodeWin','top=300px,left=350px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
		 											<img id="imgTruckTypeSearch" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
		 										</a>
		 									</td>
		 									<td class="text14"><span title="tuopdt"><spring:message code="systema.transportdisp.workflow.trip.form.label.ordertype"/></span></td>
		 									<td class="text14">
					    						<select class="inputText14" name="tuopdt" id="tuopdt">
								            		<option value=""><spring:message code="systema.transportdisp.dropdown.default"></spring:message></option>
								 				  	<c:forEach var="record" items="${model.oppdragstypeList}" >
							                       	 	<option value="${record.opdTyp}"<c:if test="${model.record.tuopdt == record.opdTyp}"> selected </c:if> >${record.opdTyp}</option>
													</c:forEach> 
												</select>	
												<%-- info span --%>
												<img id="tuopdtIdLink" tabindex=-1 style="cursor:pointer; vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
												
								 			</td>
								    	</tr>
								    	<tr height="5"><td ></td></tr>
								    	<tr>
								    		<td colspan="8" nowrap>
								    		<table style="width:98%" class="tableBorderWithRoundCorners">
								    		<tr>
								    		<td class="text14">
								    			<span title="tucon1/tulkc1">
								    				&nbsp;<img style="vertical-align: bottom;" src="resources/images/containerYellow.png" height="16px" width="16px" border="0" alt="edit">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.container.nr"/>&nbsp;<b>1</b>
								    			</span>
							    			</td>
								    		<td class="text14" nowrap><input type="text" class="inputTextMediumBlueUPPERCASE" name="tucon1" id="tucon1" size="15" maxlength="17" value="${model.record.tucon1}"></td>
								    		
								    		<td class="text14"><spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.countryCode"/></td>
								    		<td class="text14">
								    			<select class="inputText14" name="tulkc1" id="tulkc1">
						 						<option value=""><spring:message code="systema.transportdisp.dropdown.default"></spring:message></option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.zkod}"<c:if test="${model.record.tulkc1 == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>  
												</select>
												<a tabindex="-1" id="tulkc1IdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
								    		</td>
											<td class="text14">
								    			<span title="tucon2/tulkc2">
								    				&nbsp;<img style="vertical-align: bottom;" src="resources/images/containerYellow.png" height="16px" width="16px" border="0" alt="edit">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.container.nr"/>&nbsp;<b>2</b>
								    			</span>
							    			</td>	
								    		<td><input type="text" class="inputTextMediumBlueUPPERCASE" name="tucon2" id="tucon2" size="18" maxlength="17" value="${model.record.tucon2}"></td>
								    		<td class="text14" align="center">
								    			<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.countryCode"/>
								    		</td>
								    		<td class="text14" align="left">
								    			<select class="inputText14" name="tulkc2" id="tulkc2">
						 						<option value=""><spring:message code="systema.transportdisp.dropdown.default" /></option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.zkod}"<c:if test="${model.record.tulkc2 == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="tulkc2IdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
							    			</td>
							    			</tr>
							    			
							    			<tr>
								    		<td class="text14">
								    			<span title="tucon3/tulkc3">
								    				&nbsp;<img style="vertical-align: bottom;" src="resources/images/containerYellow.png" height="16px" width="16px" border="0" alt="edit">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.container.nr"/>&nbsp;<b>3</b>
								    			</span>
							    			</td>
								    		<td class="text14" nowrap><input type="text" class="inputTextMediumBlueUPPERCASE" name="tucon3" id="tucon3" size="15" maxlength="17" value="${Xmodel.record.tucon3}"></td>
								    		
								    		<td class="text14"><spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.countryCode"/></td>
								    		<td class="text14">
								    			<select class="inputText14" name="tulkc3" id="tulkc3">
						 						<option value=""><spring:message code="systema.transportdisp.dropdown.default"></spring:message></option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.zkod}"<c:if test="${Xmodel.record.tulkc3 == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>  
												</select>
												<a tabindex="-1" id="tulkc3IdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
								    		</td>
								    		</tr>
							    			
							    			
							    			</table>
							    			</td>
								    	</tr>
								    	<tr height="5"><td ></td></tr>
								    	<tr>
								    		<td class="text14">
								    			<span title="tuknt2/tunat">
								    				&nbsp;<img style="vertical-align: bottom;" src="resources/images/lorry_green.png" height="16px" width="16px" border="0" alt="edit">								    				
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.truckersno"/><font class="text12RedBold" >*</font>
							    				</span>
						    				</td>
								    		<td><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueUPPERCASEMandatoryField" name="tuknt2" id="tuknt2" size="9" maxlength="8" value="${model.record.tuknt2}">
								    			<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_transpcarrier.do?action=doInit','transpcarrierWin','top=300px,left=350px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
		 										<img id="imgTruckersNrSearch" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
		 									</a>
								    		</td>
								    		<td colspan="3"><input readonly tabindex=-1 type="text" class="inputTextMediumBlueUPPERCASE inputTextReadOnly" name="tunat" id="tunat" size="35" maxlength="30" value="${model.record.tunat}"></td>
  								 		<td class="text14" align="left"> 
  								 			<input readonly tabindex=-1 type="text" class="inputTextMediumBlue inputTextReadOnly" name="tnrType" id="tnrType" size="15" maxlength="40" value="${model.record.typKod}&nbsp;${model.record.typTxt}">
  								 		</td>
  								 		
  								 		
  								 		<td class="text14" align="left">
  								 				<img onMouseOver="showPop('tutrma_info');" onMouseOut="hidePop('tutrma_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 					<span title="tutrma"><spring:message code="systema.transportdisp.workflow.trip.form.label.truckersno.transportType"/></span>
							 					<select class="inputTextMediumBlue" name="tutrma" id="tutrma">
									            		<option value=""><spring:message code="systema.transportdisp.dropdown.default" /></option>
									            		<c:forEach var="record" items="${model.transporttypeList}" >
									            			<option title="${record.ks4ftx}" value="${record.ks4trm}"<c:if test="${model.record.tutrma == record.ks4trm}"> selected </c:if> >${record.ks4trm}</option>
													</c:forEach>	
												</select>
												<a tabindex=0 id="tutrmaIdLink" >
		 											<img id="imgTransportTypeSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
		 										</a>	
							 					
							 					<div class="text11" style="position: relative;" align="left" >
									 				<span style="position:absolute; top:2px; width:200px;" id="tutrma_info" class="popupWithInputText text11"  >
										           		<p><b><spring:message code="systema.transportdisp.workflow.trip.form.label.truckersno.transportType"/></b></p> 
									           			Tast gyldig kode for transportmåte ved grense. Overføres SAD:
									           			<ul>
									           				<c:forEach var="record" items="${model.transporttypeList}" >
									           					<li><b>${record.ks4trm}</b>&nbsp;${record.ks4ftx}
															</c:forEach>
									           			</ul>
													</span>	
												</div>
  								 			</td>	
  									</tr>
									<tr>
								    		<td class="text14">
								    			<span title="tusja1/tusjn1">
								    				&nbsp;<img style="vertical-align: bottom;" src="resources/images/appUserOg.gif" height="16px" width="16px" border="0" alt="edit">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.driver1"/>
								    			</span>
								    			
								    		</td>
								    		<td><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueUPPERCASE" name="tusja1" id="tusja1" size="6" maxlength="8" value="${model.record.tusja1}">
								    			<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_driver.do?action=doInit&dv=1','driverWin','top=300px,left=350px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
		 										<img id="imgDriver1Search" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
		 									</a>
								    		</td>
								    		<td colspan="4"><input type="text" class="inputTextMediumBlueUPPERCASE" name="tusjn1" id="tusjn1" size="35" maxlength="30" value="${model.record.tusjn1}"></td>
								    	</tr>
								    	<tr>	
								    		<td class="text14">
								    			<span title="tusja2/tusjn2">
								    				&nbsp;<img style="vertical-align: bottom;" src="resources/images/appUserOg.gif" height="16px" width="16px" border="0" alt="edit">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.driver2"/>
								    			</span>
								    		</td>
								    		<td><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueUPPERCASE" name="tusja2" id="tusja2" size="6" maxlength="8" value="${model.record.tusja2}">
								    			<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_driver.do?action=doInit&dv=2','driverWin','top=300px,left=350px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
		 										<img id="imgDriver2Search" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
		 									</a>
								    		</td>
								    		<td colspan="4"><input type="text" class="inputTextMediumBlueUPPERCASE" name="tusjn2" id="tusjn2" size="35" maxlength="30" value="${model.record.tusjn2}"></td>
								    	</tr>
								    	<tr height="5"><td ></td></tr>
								 </table>	
				 				</td>
				 				<td valign="top">
					 			 <table width="98%" class="tableBorderWithRoundCornersLightGray" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="10"><td >&nbsp;</td></tr>
								    <tr>
								    		<td class="text14" nowrap>
							    				&nbsp;<img onMouseOver="showPop('etd_info');" onMouseOut="hidePop('etd_info');" style="vertical-align: bottom;" src="resources/images/info3.png" width="12px" height="12px" border="0" alt="info">
							    				<span title="tudt/tutm"><spring:message code="systema.transportdisp.workflow.trip.form.label.date.departure"/></span>
							    				<div class="text11" style="position: relative;" align="left">
					 						<span style="position:absolute; width:200px; left:0px; top:0px;" id="etd_info" class="popupWithInputText"  >
					 							<font class="text11">
								           			<b>Date of Departture (ETD)</b>
								           			<div>
								           			<p>Always a time-stamp and the place.</p>
								           			<p>Place:
								           			<ul>
								           				<li>Country Code</li>
								           			    <li>Postal number</li>
								           			    <li>The city will be filled automatically</li>
								           			</ul>
								           			</p>	
								           			</div>
							           			</font>
											</span>
											</div>
							    			</td>
								    		<td>
							    				<c:choose>
								    				<c:when test="${not empty model.record.tudt && !fn:contains(model.record.tudt,'yyyy')}">
								    					<input type="text" class="inputTextMediumBlue" name="tudt" id="tudt" size="9" maxlength="8" value="${model.record.tudt}">
							    					</c:when>
							    					<c:otherwise>
							    						<input onfocus="if (this.value==this.defaultValue) this.value = ''" type="text" class="inputTextMediumBlue" style="color:#CCCCCC;" name="tudt" id="tudt" size="9" maxlength="8" value="">
								    				</c:otherwise>
							    				</c:choose>
								    			<img src="resources/images/calendar.gif" height="12px" width="12px" border="0" alt="date">
								    			
								    			<c:choose>
								    			<c:when test="${not empty model.record.tutm && !fn:contains(model.record.tutm,'HH')}">
								    				&nbsp;<input type="text" class="inputTextMediumBlue" name="tutm" id="tutm" size="5" maxlength="4" value="${model.record.tutm}">
								    			</c:when>
								    			<c:otherwise>
								    				&nbsp;<input onfocus="if (this.value==this.defaultValue) this.value = ''" type="text" class="inputTextMediumBlue" style="color:#CCCCCC;" name="tutm" id="tutm" size="5" maxlength="4" value="">
								    			</c:otherwise>
								    			</c:choose>
							    			</td>
								    		<td class="text14" nowrap>&nbsp;
								    			<span title="tusonf/tustef/tusdf">
								    				<img onMouseOver="showPop('etd_info');" onMouseOut="hidePop('etd_info');" style="vertical-align: bottom;" src="resources/images/addressIcon.png" width="11px" height="11px" border="0" alt="address">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.date.departure.from"/>
								    			</span>
							    			</td>
								    		<td class="text14" nowrap>
								    			<select class="inputText14" name="tusonf" id="tusonf">
						 						<option value=""><spring:message code="systema.transportdisp.dropdown.default"></spring:message></option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.zkod}"<c:if test="${model.record.tusonf == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="tusonfIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
											
							    			</td>
								    		<td class="text14" nowrap><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="tustef" id="tustef" size="6" maxlength="5" value="${model.record.tustef}">
								    			<a tabindex=0 id=tustefIdLink>
								    				<img id="imgFromSearch" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
								    			</a>
								    		</td>
								    		<td ><input tabindex=-1 readonly tabindex=-1 type="text" class="inputTextReadOnlyNormal" name="tusdf" id="tusdf" size="22" maxlength="20" value="${model.record.tusdf}"></td>
								    	</tr>
								    	<%--
								    	<tr>
								    		<td>&nbsp;</td>
								    		<td colspan="6"><input tabindex=-1 readonly tabindex=-1 type="text" class="inputTextReadOnlyNormal" name="tusdf" id="tusdf" size="22" maxlength="20" value="${model.record.tusdf}"></td>
								    	</tr>
								    	 --%>
								    	<tr>
								    		<td class="text14" nowrap>
							    				&nbsp;<img onMouseOver="showPop('eta_info');" onMouseOut="hidePop('eta_info');" style="vertical-align: bottom;" src="resources/images/info3.png" width="12px" height="12px" border="0" alt="info">
							    				<span title="tudtt/tutmt"><spring:message code="systema.transportdisp.workflow.trip.form.label.date.arrival"/></span>
							    				<div class="text11" style="position: relative;" align="left">
					 						<span style="position:absolute; width:200px; left:0px; top:0px;" id="eta_info" class="popupWithInputText"  >
					 							<font class="text11">
								           			<b>Date of Arrival (ETA)</b>
								           			<div>
								           			<p>Always a time-stamp and the place.</p>
								           			<p>Place:
								           			<ul>
								           				<li>Country Code</li>
								           			    <li>Postal number</li>
								           			    <li>The city will be filled automatically</li>
								           			</ul>
								           			</p>	
								           			</div>
							           			</font>
											</span>
											</div>
						    				</td>
								    		<td>
								    			<c:choose>
								    				<c:when test="${not empty model.record.tudtt && !fn:contains(model.record.tudtt,'yyyy')}">
								    					<input type="text" class="inputTextMediumBlue" name="tudtt" id="tudtt" size="9" maxlength="8" value="${model.record.tudtt}">
							    					</c:when>
							    					<c:otherwise>
							    						<input onfocus="if (this.value==this.defaultValue) this.value = ''" type="text" class="inputTextMediumBlue" style="color:#CCCCCC;" name="tudtt" id="tudtt" size="9" maxlength="8" value="">
								    				</c:otherwise>
							    				</c:choose>
								    			<img src="resources/images/calendar.gif" height="12px" width="12px" border="0" alt="date">
								    			
								    			<c:choose>
								    			<c:when test="${not empty model.record.tutmt && !fn:contains(model.record.tutmt,'HH')}">
								    				&nbsp;<input type="text" class="inputTextMediumBlue" name="tutmt" id="tutmt" size="5" maxlength="4" value="${model.record.tutmt}">
								    			</c:when>
								    			<c:otherwise>
								    				&nbsp;<input onfocus="if (this.value==this.defaultValue) this.value = ''" type="text" class="inputTextMediumBlue" style="color:#CCCCCC;" name="tutmt" id="tutmt" size="5" maxlength="4" value="">
								    			</c:otherwise>
								    			</c:choose>
								    		</td>
								    		<td class="text14" nowrap>&nbsp;
								    			<span title="tusont/tustet/tusdt">
								    				<img onMouseOver="showPop('eta_info');" onMouseOut="hidePop('eta_info');" style="vertical-align: bottom;" src="resources/images/addressIcon.png" width="11px" height="11px" border="0" alt="address">
								    				<spring:message code="systema.transportdisp.workflow.trip.form.label.date.arrival.to"/>
								    			</span>
							    			</td>
								    		<td class="text14" nowrap>
								    			<select class="inputText14" name="tusont" id="tusont">
						 						<option value=""><spring:message code="systema.transportdisp.dropdown.default"></spring:message></option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.zkod}"<c:if test="${model.record.tusont == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="tusontIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
							    			</td>
								    		<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="tustet" id="tustet" size="6" maxlength="5" value="${model.record.tustet}">
								    			<a tabindex=0 id="tustetIdLink" >
		 											<img id="imgToSearch" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
		 										</a>
								    		</td>
								    		<td ><input tabindex=-1 readonly tabindex=-1 type="text" class="inputTextReadOnlyNormal" name="tusdt" id="tusdt" size="22" maxlength="20" value="${model.record.tusdt}"></td>
								    	</tr>
								    	
								    	<tr height="5"><td ></td></tr>
								    	<tr>
								    		<td colspan="8" nowrap>
								    		<table class="tableBorderWithRoundCorners">
								    		<tr>
								    		<td class="text14" nowrap>
								    			&nbsp;<img onMouseOver="showPop('agreedPrice_info');" onMouseOut="hidePop('agreedPrice_info');" style="vertical-align: bottom;" src="resources/images/info3.png" width="12px" height="12px" border="0" alt="info">
								    			<span title="tutbel/tutval/tutako"><b><spring:message code="systema.transportdisp.workflow.trip.form.label.price.agreed"/></b></span>
								    			<div class="text11" style="position: relative;" align="left">
						 						<span style="position:absolute; width:200px; left:0px; top:0px;" id="agreedPrice_info" class="popupWithInputText"  >
						 							<font class="text11">
									           			<b>Agreed/Estim. Price</b>
									           			<div>
									           			<ul>
									           				<li>Currency</li>
									           			    <li>Amount</li>
									           			    <li>A=Agreed, E=Estimated</li>
									           			</ul>
									           			</p>	
									           			</div>
								           			</font>
												</span>
												</div>
							    			</td>
								    		<td class="text14" nowrap>
								    			<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="tutbel" id="tutbel" size="8" maxlength="8" value="${model.record.tutbel}">
								    			<select class="inputTextMediumBlue" style="background-color:lemonchiffon" id="tutval" name="tutval">
								    			<option value=""><spring:message code="systema.transportdisp.dropdown.default"></spring:message></option>
							    				<c:forEach var="record" items="${model.currencyCodeList}" >
							    					<option value="${record}" <c:if test="${model.record.tutval == record}"> selected </c:if> >${record}</option>
												</c:forEach>
						           				</select>
								    			<select class="inputTextMediumBlue" name="tutako" id="tutako">
								    			<option value=""><spring:message code="systema.transportdisp.dropdown.default"></spring:message></option>
				            					<option value="A"<c:if test="${model.record.tutako == 'A'}"> selected </c:if> >A</option>
				 				  				<option value="E"<c:if test="${model.record.tutako == 'E'}"> selected </c:if> >E</option>
												</select>
								    		</td>
								    		<td class="text14" >&nbsp;&nbsp;
								    			<span title="tuant1/tuenh1/tuant2/tuenh2"><b><spring:message code="systema.transportdisp.workflow.trip.form.label.price.construction"/></b></span>
							    				<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" style="background-color:lemonchiffon" name="tuant1" id="tuant1" size="6" maxlength="5" value="${model.record.tuant1}">
								    			Enh:
								    			<input type="text" class="inputTextMediumBlue" name="tuenh1" id="tuenh1" size="3" maxlength="3" value="${model.record.tuenh1}">
								    			x
								    			<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" style="background-color:lemonchiffon" name="tuant2" id="tuant2" size="6" maxlength="6" value="${model.record.tuant2}">
								    			Enh:
								    			<input type="text" class="inputTextMediumBlue" name="tuenh2" id="tuenh2" size="3" maxlength="3" value="${model.record.tuenh2}">
								    		</td>
								    		</tr>
								    		</table>
								    	</tr>
								    	<tr height="5"><td ></td></tr>
								    	<tr>
						 				<td colspan="10" class="text14">
						 					<table class="tableBorderWithRoundCorners" >
												<tr>
										 			<td valign="top" class="text14"><spring:message code="systema.transportdisp.workflow.trip.form.label.shippinglist"/>
										 			<img id="imgUpdateFerjeoverfarter" title="Endre ferjeoverfarter" style="vertical-align:middle;cursor:pointer;" src="resources/images/addOrder.png" width="14" height="14" border="0" alt="Add shipping trip">
									 					<div id="resultShippingList">
									 						<table >
										 						<tr class="tableHeaderField" >
										 						<th align="left" class="text14">Dato</th>
										 						<th align="left" class="text14">Kl.</th>
										 						<th align="left" class="text14">Fra</th>
										 						<th align="left" class="text14">Til</th>
										 						<th align="left" class="text14">Lengd</th>
										 						<th align="left" class="text14">Selskap</th>
										 						<th align="left" class="text14">Kostpris</th>
										 						<th align="left" class="text14">Valuta</th>
										 						<th align="left" class="text14">Bilnr.</th>
										 						
										 						</tr>
									 						
											 					<c:forEach items="${model.record.shippingTripList}" var="record" varStatus="counter">
											 						<tr class="text14 tableRow">
											 						<td class="tableCellFirst" style="white-space:nowrap">${record.fedat2}</td>
							 										<td class="tableCell" style="white-space:nowrap">${record.fetime}</td>
										   							<td class="tableCell" style="white-space:nowrap">${record.fefrom}</td>
										   							<td class="tableCell" style="white-space:nowrap">${record.feto}</td>
										   							<td class="tableCell" style="white-space:nowrap">${record.feleng}</td>
										   							<td class="tableCell" style="white-space:nowrap">${record.levNavn}</td>
										   							<td class="tableCell" style="white-space:nowrap">${record.fepri1}</td>
										   							<td class="tableCell" style="white-space:nowrap">${record.fecurr}</td>
										   							<td class="tableCell" style="white-space:nowrap">${record.febiln}</td>
										   							
										   							</tr>
											 					</c:forEach>
											 				</table>
									 					</div>
									 					
									 				</td>
												</tr>
											</table>
										 	
					 						
							 				</td>
							 			</tr>
								    	
								    	
								    	<tr height="5"><td ></td></tr>
								    	
								 </table>	
				 				</td>
			 				</tr>
			 				<%--
			 				<tr>
			 					<td>&nbsp;</td>
			 					<td>
			 						<table style="width: 90%;">
			 						<tr>
				 						<td align="right">
					 				    <c:choose>
						 				    <c:when test="${ not empty model.record.tupro}">
						 				    		<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" onclick="javascript: form.action='transportdisp_workflow_edit.do?action=doUpdate';" value='<spring:message code="systema.transportdisp.submit.save"/>'/>
						 				    </c:when>
						 				    <c:otherwise>
						 				    		<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" onclick="javascript: form.action='transportdisp_workflow_edit.do?action=doUpdate';" value='<spring:message code="systema.transportdisp.submit.createnew"/>'/>
						 				    </c:otherwise>	
					 				    </c:choose>
					 				    </td>
				 				    </tr>
				 				    </table>
			 				    </td>
			 				</tr>
			 				 --%>
				 			<tr height="10"><td></td> </tr>
			 				<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text"></td></tr>
				 			<tr height="10"><td></td> </tr>
				 			<tr>
						 		<td valign="top" width="50%">	
						 		<table width="100" border="0" cellspacing="1" cellpadding="0">		
							 		<tr>
							 			<td class="text14">
							 				<img onMouseOver="showPop('messageNote_info');" onMouseOut="hidePop('messageNote_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="messageNote"><spring:message code="systema.transportdisp.workflow.trip.form.label.messageNotes"/></span>
							 				<div class="text11" style="position: relative;" align="left">
					 						<span style="position:absolute; left:0px; top:0px;" id="messageNote_info" class="popupWithInputText"  >
					 							<font class="text11">
								           			<b>Message/Notes</b>
								           			<div>
								           			<p>The message will be printed as shown in screen.</p>
								           			<ul>
								           				<li>Max.character per line: 70-characters</li>
								           			    <li>Max.number of lines: 12</li>
								           			</ul>	
								           			</div>
							           			</font>
											</span>
											</div>
							 			</td>
							 		</tr>
							 		<tr>	
								 		<td colspan="20">
								 			<textarea class="text12UPPERCASE" id="messageNote" name="messageNote" limit='70,12' cols="75" rows="8">${model.record.messageNote}</textarea>
								 		</td>
							 		</tr>
							 		<tr height="10"><td></td></tr>
							 		<tr>
						 				<td colspan="20" class="text14">
						 					<table class="tableBorderWithRoundCorners" >
												<tr>
										 			<td valign="top" class="text12">
									 					<spring:message code="systema.transportdisp.workflow.trip.form.label.uploadedDocs"/>&nbsp;
									 					<div id="resultUploadedDocs">
									 						<table >
										 						<tr class="tableHeaderField" >
										 						<th align="left" class="text14">Dok.type</th>
										 						<th align="left" class="text14">Dok.navn</th>
										 						<th align="left" class="text14">Dato/kl</th>
										 						</tr>
									 						
											 					<c:forEach items="${model.record.getdoctrip}" var="record" varStatus="counter">
											 						<tr class="text14 tableRow">
											 						<td class="tableCellFirst" style="white-space:nowrap">${record.doctyp}</td>
							 										<td class="tableCell" style="white-space:nowrap">
											 						<a target="_blank" href="transportdisp_workflow_renderArchivedDocs.do?doclnk=${record.doclnk}">
							    		    							<c:choose>
								    		    							<c:when test="${fn:contains(record.doclnk, '.pdf')}">
								    		    								<img title="Archive" style="vertical-align:middle;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="PDF arch.">
								    		    							</c:when>
								    		    							<c:otherwise>
								    		    								<img title="Archive" style="vertical-align:middle;" src="resources/images/jpg.png" width="14" height="14" border="0" alt="Other arch.">
								    		    							</c:otherwise>
							    		    							</c:choose>
							    		    							${record.doctxt}
									   								</a>
									   								</td>
										   							<td class="tableCell" style="white-space:nowrap">${record.docdat}&nbsp;${record.doctim}</td>
									   								</tr>
											 					</c:forEach>
											 				</table>
									 					</div>
									 					
									 				</td>
												</tr>
											</table>
										 	
					 						
						 				</td>
						 			</tr>
								</table>
								</td>

				 				<td valign="top" width="50%">
					 			<table width="98%" class="tableBorderWithRoundCornersDarkGray" cellspacing="1" cellpadding="0" border="0">
							 		<tr>
									    <td valign="middle">
										    <table>
										    <tr class="tableRow">
										    		<%--
										    		<input type="hidden" name="own_tuhoyb" id="own_tuhoyb" value=""/>
										    		<input type="hidden" name="own_tuhoyh" id="own_tuhoyh" value=""/>
										    		<input type="hidden" name="own_tukvkt" id="own_tukvkt" value=""/>
										    		<input type="hidden" name="own_tutara" id="own_tutara" value=""/>
										    		<input type="hidden" name="own_tukam3" id="own_tukam3" value=""/>
										    		<input type="hidden" name="own_tukalM" id="own_tukalM" value=""/>
										    		 --%>
										    		 
										    		<td class="tableHeaderFieldFirst" >
										    			<img onMouseOver="showPop('tuhoyb_info');" onMouseOut="hidePop('tuhoyb_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="tuhoyb/Utilised"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.height.truck"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="tuhoyb_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Høyde bil</b>
										           			<div>
										           			<p>Hentes fra bilregister til info for senere lasteplanlegging
										           			</p>
										           			
										           			</div>
									           			</font>
													</span>
													</div>
										    		</td>
												<td class="tableHeaderField" align="right"><label name="tuhoyb" id="tuhoyb">&nbsp;${model.record.tuhoyb}</label></td>			
										    		<td class="tableHeaderField" align="right">&nbsp;</td>										    
								    			</tr>
										    	<tr class="tableRow">	
										    		<td class="tableHeaderFieldFirst" >
										    			<img onMouseOver="showPop('tuhoyh_info');" onMouseOut="hidePop('tuhoyh_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="tuhoyh(Capacity)"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.height.hangs"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="tuhoyh_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Høyde henger</b>
										           			<div>
										           			<p>Dersom hengeren er en egen "unit" i unitregisteret hentes høyde derfra. Til info for senere lasteplanlegging
										           			</p>
										           			</div>
									           			</font>
													</span>
													</div>
										    			
										    			
										    		</td>
										    		<td class="tableHeaderField" align="right"><label name="tuhoyh" id="tuhoyh">&nbsp;${model.record.tuhoyh}</label></td>
										    		<td class="tableHeaderField" align="right">&nbsp;</td>
										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="tableHeaderFieldFirst" >
										    			<img onMouseOver="showPop('tukvkt_info');" onMouseOut="hidePop('tukvkt_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="tukvkt/tutvkt"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.capacity"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="tukvkt_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Lasteevne(Capacity)</b>
										           			<div>
										           			<p>Samlet lastekapasitet for bil og henger i kilo last.
															  2. kolonne - hittil oppfylt (sum vekt av oppdrag så langt)
										           			</p>
										           			</div>
									           			</font>
													</span>
													</div>
									    			</td>
										    		<td class="tableHeaderField" align="right"><label name="tukvkt" id="tukvkt">&nbsp;${model.record.tukvkt}</label></td>
										    		<td class="tableHeaderField" align="right"><label name="tutvkt" id="tutvkt">&nbsp;${model.record.tutvkt}</label></td>
										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="tableHeaderFieldFirst" >
										    			<img onMouseOver="showPop('tutara_info');" onMouseOut="hidePop('tutara_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="tutara"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.tara"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="tutara_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Tara</b>
										           			<div>
										           			<p>Samlet egenvekt bil og henger.
										           			</p>
										           			</div>
									           			</font>
													</span>
													</div>
										    		</td>	
										    		<td class="tableHeaderField" align="right"><label name="tutara" id="tutara">&nbsp;${model.record.tutara}</label></td>
										    		<td class="tableHeaderField" align="right">&nbsp;</td>
										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="tableHeaderFieldFirst">
										    			<img onMouseOver="showPop('wstov1_info');" onMouseOut="hidePop('wstov1_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="wstov1/wstov2"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.total.weight"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="wstov1_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Totalvekt</b>
										           			<div>
										           			<p>Samlet vekt inkl. last for bil og henger. 2. kolonne - hittil oppfylt (bil/henger+sum vekt av oppdrag så langt)
										           			</p>
										           			</div>
									           			</font>
													</span>
													</div>
									    			</td>
									    			<c:set var="tmptukvkt" value="${fn:replace(model.record.tukvkt,',','.')}" />
									    			<c:set var="tmptutara" value="${fn:replace(model.record.tutara,',','.')}" />
									    			<c:choose>
										    			<c:when test="${tmptukvkt!='' || tmptutara!=''}">
										    				<fmt:parseNumber var="dtmptukvkt" type="number" value="${tmptukvkt}" />
										    				<fmt:parseNumber var="dtmptutara" type="number" value="${tmptutara}" />
										    				<td class="tableHeaderField" align="right"><label name="wstov1" id="wstov1">&nbsp;<c:out value="${dtmptukvkt + dtmptutara}"/></label></td>
											    			<td class="tableHeaderField" align="right"><label name="wstov2" id="wstov2">&nbsp;</label></td>
										    			</c:when>
										    			<c:otherwise>
										    				<td class="tableHeaderField" align="right"><label name="wstov1" id="wstov1">&nbsp;</label></td>
											    			<td class="tableHeaderField" align="right"><label name="wstov2" id="wstov2">&nbsp;</label></td>
										    			</c:otherwise>
									    			</c:choose>
										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="tableHeaderFieldFirst" >
										    			<img onMouseOver="showPop('tukam3_info');" onMouseOut="hidePop('tukam3_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="tukam3/tutm3"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.total.m3"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="tukam3_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Total M3</b>
										           			<div>
										           			<p>Samlet kubik-kapasitet for bil og henger. 2. kolonne - hittil oppfylt (sum av oppdrag så langt hvor kubikk er fylt ut)
										           			</p>
										           			</div>
									           			</font>
													</span>
													</div>
										    			
									    			</td>
										    		<td class="tableHeaderField" align="right"><label name="tukam3" id="tukam3">&nbsp;${model.record.tukam3}</label></td>
										    		<td class="tableHeaderField" align="right"><label name="tutm3" id="tutm3">&nbsp;${model.record.tutm3}</label></td>
										    	</tr>
										    	<tr class="tableRow">
										    		<td class="tableHeaderFieldFirst" >
										    			<img onMouseOver="showPop('tukalM_info');" onMouseOut="hidePop('tukalM_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="tukalM/tutlm"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.total.lm"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="tukalM_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Total LM</b>
										           			<div>
										           			<p>Samlet lastemeter-kapasitet for bil og henger. oppfylt (sum av oppdrag så langt hvor lastemeter er fylt ut)
										           			</p>
										           			</div>
									           			</font>
													</span>
													</div>
										    			
										    		</td>
										    		<td class="tableHeaderField" align="right"><label name="tukalM" id="tukalM">&nbsp;${model.record.tukalM}</label></td>
										    		<td class="tableHeaderField" align="right"><label name="tutlm" id="tutlm">&nbsp;${model.record.tutlm}</label></td>
										    	</tr>
									    		<tr class="tableRow">
										    		<td class="tableHeaderFieldFirst" >
										    			<img onMouseOver="showPop('simlm_info');" onMouseOut="hidePop('simlm_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										    			<span title="simlm/simm3"><spring:message code="systema.transportdisp.workflow.trip.form.label.uom.matrix.line.total.simLMM3"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:250px;" id="simlm_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Simulert LM/M3</b>
										           			<div>
										           			<p>todo</p>
										           			</div>
									           			</font>
													</span>
													</div>
										    			
										    			
										    		</td>
										    		<td class="tableHeaderField" align="right"><label name="simlm" id="simlm">&nbsp;${model.record.simlm}</label></td>
										    		<td class="tableHeaderField" align="right"><label name="simm3" id="simm3">&nbsp;${model.record.simm3}</label></td>
										    	</tr>
											</table>
										</td>
									    <td valign="middle">
										    <table class="tableNoBorderWithRoundCorners" >
										    <tr>
										    		<td class="text14" title="tuao/tuts"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.ordersColli"/></td>
										    		<td colspan="2" class="text14" title="berbud"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.estimatedTransportCost"/></td>
										    		<td class="text14"><input readonly tabindex=-1 type="text" class="inputTextMediumBlueReadOnlyMateBg" style="text-align:right;" name="berbud" id="berbud" size="8" value="${model.record.berbud}"></td>
										    	</tr>
										    	<tr>
										    		<td class="text14" nowrap>
										    			<input readonly tabindex=-1 type="text" class="inputTextMediumBlueReadOnlyMateBg" style="text-align:center;" name="tuao" id="tuao" size="6" value="${model.record.tuao}">
										    			<b>/</b>
										    			<input readonly tabindex=-1 type="text" class="inputTextMediumBlueReadOnlyMateBg" style="text-align:center;" name="tuts" id="tuts" size="6" value="${model.record.tuts}">
										    		</td>
										    		<td class="tableHeaderFieldFirst12" align="center"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.header.open"/></td>
										    		<td class="tableHeaderField12" align="center"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.header.finished"/></td>
										    		<td class="tableHeaderField12" align="right"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.header.sum"/>&nbsp;</td>
										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="text14" >
										    			<img onMouseOver="showPop('totiaa_info');" onMouseOut="hidePop('totiaa_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 				<span title="totiaa/totioa/totisa"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.line.inntekt.avrgrl"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:10px; top:0px; width:350px;" id="totiaa_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Inntekt avregning grl.- og Inntekt øvrige</b>
										           			<div>
										           			<p>Basert på det som ligger som FAKTURALINJER på alle oppdragene summeres inntektene. 
										           			De grupperes som "FERDIGE" dersom statuskoden er "F" (Fakturert) eller høyere.
										           			</p>
										           			<p> 
										           			Lavere status akkumuleres under åpne. Inntektene klassifiseres som "avregningsgrunnlag" dersom gebyrkoden
															har kode "T" i gebyrkoderegisteret. Ellers i "øvrige".
															</p>
										           			</div>
									           			</font>
													</span>
													</div>
										    			
										    		</td>
									    			<td class="tableCellFirst12" align="right"><label name="totiaa" id="totiaa">${model.record.totiaa}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totioa" id="totioa">${model.record.totioa}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totisa" id="totisa">${model.record.totisa}&nbsp;</label></td>
										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="text14" >
										    			<img onMouseOver="showPop('totiaa_info');" onMouseOut="hidePop('totiaa_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 				<span title="totiag/totiog/totisg"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.line.inntekt.ovriga"/></span>
										    		</td>
									    			<td class="tableCellFirst12" align="right"><label name="totiag" id="totiag">${model.record.totiag}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totiog" id="totiog">${model.record.totiog}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totisg" id="totisg">${model.record.totisg}&nbsp;</label></td>
										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="text12">
										    			<img onMouseOver="showPop('totkaa_info');" onMouseOut="hidePop('totkaa_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 				<span title="totkaa/totkoa/totksa"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.line.kostnad.avrtrans"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:0px; top:0px; width:250px;" id="totkaa_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Kostn. avregning/tran</b>
										           			<div>
										           			<p>Når tur ER avregnet/inng faktura kontert (RØD TEKST OPPE TIL HØYRE):Plasseres beløp i kolonne "FERDIGE". ....Ved transportører som avregnes (kode 0 bak transportørs navn), hentes
																beløp rett fra avregnings-filer. Ved transportører som sender faktura (kode 2) hentes evt. beløp fra
																turbildets <b>Pris transp</b>.
										           			</p>
										           			<p>Når tur IKKE er avregnet/kontert: Når <b>Pris transp</b> er utfylt, legges dette inn i kolonne <b>ÅPNE</b>.</p>
										           			<p>Er det IKKE utfylt må en selv estimere</p>
										           			</div>
									           			</font>
													</span>
													</div>
									    			</td>
									    			<td class="tableCellFirst12" align="right"><label name="totkaa" id="totkaa">${model.record.totkaa}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totkoa" id="totkoa">${model.record.totkoa}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totksa" id="totksa">${model.record.totksa}&nbsp;</label></td>

										    	</tr>
										    	<tr class="tableRow">	
										    		<td class="text12">
										    			<img onMouseOver="showPop('totkao_info');" onMouseOut="hidePop('totkao_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 				<span title="totkao/totkoo/totkso"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.line.kostnad.ovriga"/></span>
										    			<div class="text11" style="position: relative;" align="left">
							 						<span style="position:absolute; left:0px; top:0px; width:500px;" id="totkao_info" class="popupWithInputText"  >
							 							<font class="text11">
										           			<b>Kostnad øvrige</b>
										           			<div>
										           			<p>Fra fakturalinjene akkumuleres <b>Kostnad øvrige</b> / <b>FERDIGE</b>.(Alle linjer med opprinnelseskode <b>K</b>=Kostnad, UNNTATT de som har inneholder "AVREGNING HOVEDTRANS" eller "*T*" i fakturatekst.<br>
										           			 (Disse takles under "Kostn. avr/tran")).Fra registeret "Foventede kostnader/Rekvisisjoner" (F7 i turbildet) akkumuleres til kolonnen "ÅPNE" (Men linjen som evt kommer fra "Pris transp" hoppes over, da denne alt er tatt med).
										           			</p>
										           			<p>
										           			OBS! FORVENTEDE KOSTNADER SOM ER PLUKKET TIL KOSTNADSBILAG
															(=HENFØRT, OG DERMED UTE AV LISTA OVER ÅPNE FORVENTEDE KOSTNADER),
															MEN ENNÅ IKKE OVERFØRT TIL ØKONOMI (=KOMMET INN SOM "FAKT.LINJE" PÅ
															OPPDRAGENE, OG MEDTATT UNDER "FERDIG") "FALLER MELLOM 2 STOLER".
															DISSE FORVENTEDE KOSTNADENE VIL VÆRE "SKJULT" INNTIL DE OVERFØRES.
															</p>
															<p>
															FOR AT DETTE IKKE SKAL GJELDE EN SÅ VIKTIG KOMPONENT SOM "PRIS TRANSPORTØR" (NÅR TRANSPORTØREN SENDER REGNING) ER DENNE HÅNDTERT SOM BESKREVET OVER (PLUKKET RETT FRA TURBILDET).
															DETTE INNEBÆRER EN VISS RISIKO. (DEN SOM HAR KONTERT KAN!!! HA ENDRET BELØP I FØRINGSØYEBLIKKET PÅ MÅTER SOM IKKE SYSTEMET HAR FANGET OPP.<br>
															MEN!!! SLIKE EVT ENDRINGER VIL STATISTIKK / OG ANALYSEPROGRAMMER FANGE)
										           			</p>
										           			<p>
										           			OBS 2 !!! MIDLERTIDIG!!!!
															Versjon 5 av SYSPED merket IKKE spesielt ut (med *T* som i versj 6) "fakturalinjer" skapt basert på føring av inngående transportør-faktura (=basert på plukket budsjett-post med "Pris transp").
															DET BETYR AT NÅR EN SER PÅ GAMLE TURER (der kontering/plukking av budsjettpost er skjedd under versjon 5) VIL DENNE KOSTNADEN SYNS DOBBELT OPP!!! (Igjen, her må en se på turanalyse, meny Cost pkt 18 for å få et rett bilde).
										           			</p>
										           			</div>
									           			</font>
													</span>
													</div>
										    			
									    			</td>
									    			<td class="tableCellFirst12" align="right"><label name="totkao" id="totkao">${model.record.totkao}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totkoo" id="totkoo">${model.record.totkoo}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totkso" id="totkso">${model.record.totkso}&nbsp;</label></td>
										    	</tr>
										    	<tr class="tableRow">
										    		<td class="text12Bold" title="totopn/totovf/totsum"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.line.sum.resultat"/></td>
									    			<td class="tableCellFirst12" align="right"><label name="totopn" id="totopn">${model.record.totopn}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totovf" id="totovf">${model.record.totovf}&nbsp;</label></td>
									    			<td class="tableCell12" align="right"><label name="totsum" id="totsum">${model.record.totsum}&nbsp;</label></td>
										    	</tr>
											</table>
										</td>
									</tr>
									
								</table>
								</td>
							</tr>
					    		<tr height="2"><td >&nbsp;</td></tr>
		 				</table>
		 				</form>
	            		</td>
	            </tr> 
            
            
            <tr height="5"><td>&nbsp;</td></tr>
			</table> <%--wrapperTable END --%>
         </td>
         </tr>
         <tr height="10"><td>&nbsp;</td></tr>
         
        <%-- ---------------- --%>
		<%-- DIALOG SMS		  --%>
		<%-- ---------------- --%>
		<tr>
		<td>
			<div id="dialogSMS" title="Dialog">
					<form id="smsForm">
				 	<table>
						<tr height="2"><td></td></tr>
						<tr><td colspan="3" class="text14" >Send SMS med lenke til TKeventGrabber</td></tr>
						<tr height="4"><td></td></tr>
						<tr>
							<td class="text14" align="left" ><b>SMS-nummer</b>&nbsp;</td>
							<td class="text14" align="left" >
							<input type="text" class="inputText" onKeyPress="return numberKey(event)" id="smsnr" name="smsnr" size="20" maxlength="15" value=''>
							</td>
   						</tr>
						<tr>
   							<td class="text14" align="left" >Språk&nbsp;</td>
							<td class="text14" align="left" >
		   						<select class="inputTextMediumBlue" name="smslang" id="smslang">
		 							<option value="NO" selected>Norsk</option>
		 							<option value="EN">Engelsk</option>
								</select>
							</td>
						</tr>
						
						<tr height="10"><td></td></tr>
						<tr>
							<td colspan="3" class="text14MediumBlue" align="left">
								<label id="smsStatus"></label>
							</td>
						</tr>
					</table>
					</form>
			</div>
		</td>
		</tr>
		
		<%-- ---------------- --%>
		<%-- DIALOG EMAIL		  --%>
		<%-- ---------------- --%>
		<tr>
		<td>
			<div id="dialogEmail" title="Dialog">
				 	<table>
						<tr>
							<td colspan="3" class="text14" align="left" ><spring:message code="systema.transportdisp.workflow.trip.email.manifest.label"/></td>
   						</tr>
   						<tr height="10"><td></td></tr>
   						
						<tr>
							<td class="text14" align="left" ><b><spring:message code="systema.transportdisp.workflow.trip.email.email.label"/></b></td>
							<td class="text14" align="left" >
								<input type="text" class="inputText" id="email" name="email" size="25" maxlength="20" value=''>
							</td>
   						</tr>
   						
						<tr>
   							<td class="text14" align="left" ><spring:message code="systema.transportdisp.workflow.trip.email.mark.label"/></td>
							<td class="text12" align="left" >
		   						<textarea id="emailText" name="emailText" limit='48,4' cols="50" rows="4"></textarea>
							</td>
						</tr>
						<tr>
   							<td class="text14" align="left" ><spring:message code="systema.transportdisp.workflow.trip.email.language.label"/></td>
							<td class="text14" align="left" >
		   						<select class="inputTextMediumBlue" name="emailLang" id="emailLang">
		 							<option value="NO" selected><spring:message code="systema.transportdisp.workflow.trip.email.language.no"/></option>
		 							<option value="EN"><spring:message code="systema.transportdisp.workflow.trip.email.language.en"/></option>
								</select>
							</td>
						</tr>
						

						<tr height="10"><td></td></tr>
						<tr>
							<td colspan="3" class="text14MediumBlue" align="left">
								<label id="emailStatus"></label>
							</td>
						</tr>
						
					</table>
			</div>
		</td>
		</tr>
		
		
		
		<%-- ---------------- --%>
		<%-- DIALOG PRINT     --%>
		<%-- ---------------- --%>
		<tr>
		<td>
			<div id="dialogPrint" title="Dialog Print">
					<form id="printForm">
					<input type="hidden" id="tur" name="tur" value="${model.record.tupro}">
						
				 	<table>
   						<tr height="3"><td></td></tr>
   						<tr>
							<td colspan="2" class="text14" align="left" >
								<input type="checkbox" name="fbType" id="fbType" value="fb">
								Fraktbrev
							</td>	
   						</tr>
   						<tr>
							<td colspan="2" class="text14" align="left" >
								<input type="checkbox" name="cmrType" id="cmrType" value="cmr">
								CMR-Fraktbrev
							</td>	
   						</tr>
   						<tr>
							<td colspan="2" class="text14" align="left" >
								<input type="checkbox" name="ffType" id="ffType" value="ff">
								Ferdigmeldte-fakturaer
							</td>	
   						</tr>
   						<tr>
							<td colspan="2" class="text14" align="left" >
								<input type="checkbox" name="aordType" id="aordType" value="I">
								<span id="alinkAordPdfi" >Arbeidsordre Intern</span>													
							</td>
   						</tr>
   						<tr>
							<td colspan="2" class="text14" align="left" >
								<input type="checkbox" name="aordTypee" id="aordTypee" value="E">
								<span id="alinkAordPdfe" >Arbeidsordre Ekstern</span>													
							</td>
   						</tr>
   						<tr>
							<td class="text14" align="left" >
								<input type="checkbox" name="godslistType" id="godslistType" value="gl">
								<span id="alinkGodslistePdf" style="text-decoration: underline;" onMouseOver="style='color:lemonchiffon;cursor:pointer;text-decoration: underline;'" onMouseOut="style='color:black;text-decoration: underline;'">Godsliste</span>
							</td>	
							<td class="text14" align="left" >	
								<img id="imgGodslistePdf" title="GL.PDF" style="vertical-align:middle;cursor:pointer" src="resources/images/pdf.png" width="14" height="14" border="0" alt="GL. PDF">
							</td>
   						</tr>
   						<tr>
							<td class="text14" align="left" >
								<input type="checkbox" name="lastlistType" id="lastlistType" value="ll">
								<span id="alinkLastlistePdf" style="text-decoration: underline;" onMouseOver="style='color:lemonchiffon;cursor:pointer;text-decoration: underline;'" onMouseOut="style='color:black;text-decoration: underline;'">Lasteliste</span>
							</td>
							<td class="text14" align="left" >	
								<img id="imgLastlistePdf" title="LL.PDF" style="vertical-align:middle;cursor:pointer" src="resources/images/pdf.png" width="14" height="14" border="0" alt="LL. PDF">
							</td>	
   						</tr>
   						<tr>
							<td class="text14" align="left" >
								<input type="checkbox" name="turkonvoluttType" id="turkonvoluttType" value="tk">
								<span class="clazz_alinkTurkonvoluttTypePdf" id="alinkTurkonvoluttTypePdf" >Turkonvolutt</span>
							</td>
						
  						</tr>
   						
   						<tr height="15"><td></td></tr>
						<tr>
							<td colspan="4" class="text14MediumBlue" align="left">
								<label id="printStatus"></label>
							</td>
						</tr>
					</table>
					</form>
			</div>
		</td>
		</tr>
		
		
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

