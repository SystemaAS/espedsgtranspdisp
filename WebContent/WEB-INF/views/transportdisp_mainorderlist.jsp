<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTransportDisp.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/transportdispglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_mainorderlist.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	.ui-datepicker { font-size:9pt;}
	/* this is in order to customize a SPECIFIC ui dialog in the .js file ...dialog() */
	.print-dialog-class .ui-widget-content{ background-color:lightsteelblue } 
	.main-dialog-class .ui-widget-content{ background-image:none;background-color:lemonchiffon }
	
	/* this line will align the datatable search field in the left */
	.dataTables_wrapper .transpDispMainOrderListFilter .dataTables_filter{float:left}
	</style>
	

<table width="100%"  class="text14" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text14" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<c:choose>
				<c:when test="${not empty searchFilter.tur}">
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkOrderListId" style="display:block;" href="transportdisp_mainorderlist.do?action=doFind&avd=${searchFilter.avd}">
							<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
							<font class="tabDisabledLink"><spring:message code="systema.transportdisp.workflow.trip.all.openorders.tab"/></font>&nbsp;<font class="text10Orange">F3</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkTripListId" style="display:block;">
							<%--<a id="alinkTripListId" style="display:block;" href="transportdisp_workflow_getTrip.do?user=${user.user}&tuavd=${searchFilter.avd}&tupro=">--%>
							<img style="vertical-align:bottom;" src="resources/images/list.gif" border="0" alt="general list">
							<font class="tabDisabledLink"><spring:message code="systema.transportdisp.workflow.trip.tab"/></font>&nbsp;<font class="text10Orange">F9</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<img title="Planning" style="vertical-align:bottom;" src="resources/images/math.png" width="16" height="16" border="0" alt="planning">
						<font class="tabLink"><spring:message code="systema.transportdisp.open.orderlist.trip.label"/><font class="text14MediumBlue">&nbsp;&nbsp;${searchFilter.tur}</font></font>
						&nbsp;
						<div title="Økonomi" style="display:inline-block; cursor:pointer;" onClick="showDialogMatrixDraggable();" >
							<font class="text14OrangeBold">e</font>
						</div>	           	
					</td>
					<td width="40%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:when>
				<c:otherwise>
					<td width="18%" valign="bottom" class="tab" align="center" nowrap>
						<a id="alinkOrderListId" style="display:block;" href="transportdisp_mainorderlist.do?action=doFind&avd=${searchFilter.avd}">
							<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
							<font class="tabLink"><spring:message code="systema.transportdisp.workflow.trip.all.openorders.tab"/></font>&nbsp;<font class="text10Orange">F3</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="18%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkTripListId" style="display:block;">
							<%--<a id="alinkTripListId" style="display:block;" href="transportdisp_workflow_getTrip.do?user=${user.user}&tuavd=${searchFilter.avd}&tupro=">--%>
							<img style="vertical-align:bottom;" src="resources/images/list.gif" border="0" alt="general list">
							<font class="tabDisabledLink"><spring:message code="systema.transportdisp.workflow.trip.tab"/></font>&nbsp;<font class="text10Orange">F9</font>
						</a>
						
					</td>
					<c:if test="${ empty user.spedKuKod}">
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="18%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a class="text14" onClick="setBlockUI(this);" href="transportdisp_mainorderlist_fellesutskrift.do?avd=${searchFilter.avd}&sign=${searchFilter.sign}" > 	
							<img style="vertical-align:middle;" src="resources/images/printer2.png" width="12px" height="12px" border="0" alt="create new">
							<font class="tabDisabledLink"><spring:message code="systema.transportdisp.fellesutskrift.tab"/></font>
						</a>
						
					</td>
					</c:if>
					<td width="70%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
				</c:otherwise>
			</c:choose>
				
		</tr>
	</table>
	</td>
	</tr>
	
	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
	<input type="hidden" name="insid" id="insid" value='${user.insid}'>
	<input type="hidden" name="userHttpJQueryDocRoot" id="userHttpJQueryDocRoot" value='${user.httpJQueryDocRoot}'>
	<input type="hidden" name="tripNr" id="tripNr" value='${searchFilter.tur}'>
	<input type="hidden" name="fkeysavd" id="fkeysavd" value='${searchFilter.avd}'>
	<input type="hidden" name="fkeysopd" id="fkeystur" value='${searchFilter.tur}'>
	 	        
	
	<tr>
	<td>
		<table width="100%" class="tabThinBorderWhiteWithSideBorders" border="0" cellspacing="0" cellpadding="0">
			<tr height="10"><td></td></tr>
			<%-- Should be set-on for the whole solution. This here was just a prototype
 	        <tr>
 	        <td height="2px" valign="top" align="right"><font class="text11MediumBlue">Stretch workspace</font><input tabindex="-1" type="checkbox" id="checkBoxVisibility">&nbsp;&nbsp;</td>
 	        </tr>
 	        --%>
		</table>		
	</td>
	</tr>
	
	
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
	
	
		<tr>
		<td>
			<%-- this table wrapper is necessary to apply the css class with the thin border --%>
			<table id="wrapperTable" class="tabThinBorderWhite" width="100%" cellspacing="1">
			
			<%-- Datatables component --%>
			<%-- CURRENT ORDERS in TRIP --%>
			<c:if test="${not empty searchFilter.tur}">
				<tr>
					<td >
					<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
						 the cosmetic frame will not follow the whole datatable grid including the search field... --%>
					<table id="containerdatatableTable1" style="width:100%;" cellspacing="2" align="left" >
					<%-- Extra tur-information --%>
					<tr>
						<td class="text16">
							<table style="background-color: lightyellow;" >
								<tr>
									<td ><span title="tupro"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.trip"/></span>&nbsp;<b><font class="text14MediumBlue">${model.record.tupro}</font></b></td>
									<td >&nbsp;&nbsp;<span title="tupro"><spring:message code="systema.transportdisp.workflow.trip.list.search.label.roundTrip"/></span>&nbsp;<b><font class="text14MediumBlue">${model.record.turund}</font></b></td>
									<td >&nbsp;&nbsp;<span title="tubiln"><spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic"/></span>&nbsp;<b><font class="text14MediumBlue">${model.record.tubiln}</font></b></td>
									<td >&nbsp;&nbsp;<span title="tuheng"><spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.henger"/></span>&nbsp;<b><font class="text14MediumBlue">${model.record.tuheng}</font></b></td>
									<td width="25">&nbsp;</td>
									<td >&nbsp;&nbsp;<span title="tuknt2"><spring:message code="systema.transportdisp.workflow.trip.form.label.truckersno"/></span>&nbsp;<b><font class="text14MediumBlue">${model.record.tuknt2}</font></b></td>
									<td >&nbsp;&nbsp;<span title="tunat"><spring:message code="systema.transportdisp.workflow.trip.form.label.truckersno.carrierName"/></span>&nbsp;<b><font class="text14MediumBlue">${model.record.tunat}</font></b></td>
								</tr>							
							</table>
						</td>
					</tr>
					
					<tr>
						<td id="dtuavd${searchFilter.avd}_dtupro${searchFilter.tur}_oncontainer" ondrop="dropX(event)" ondragenter="highlightDropArea(event)" ondragleave="noHighlightDropArea(event)" ondragover="allowDrop(event)" class="text14MediumBlue">
							<spring:message code="systema.transportdisp.orders.current.header"/>&nbsp;&nbsp;
							<c:if test="${ empty user.spedKuAvd}">
								<button name="smsButton" id="smsButton" class="buttonGrayWithGreenFrame" type="button" >Send SMS</button>
								<button name="emailButton" id="emailButton" class="buttonGrayWithGreenFrame" type="button" >Send Mail</button>
							</c:if>
						</td>
					</tr>
					<c:if test="${not empty model.containerCurrentOrders.maxWarning}">
						<tr>	
							<td class="listMaxLimitWarning">
							<img style="vertical-align:bottom;" src="resources/images/redFlag.png" width="16" height="16" border="0" alt="Warning">
							${model.containerCurrentOrders.maxWarning}</td>
						</tr>
					</c:if>
					 
				    <tr height="3"><td></td></tr>	 
					<tr>
					<td>
					
					<table style="width:100%;" id="currentOrders" class="display compact cell-border" cellspacing="0" >
						<thead >
						<tr class="tableHeaderField" >
							<th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.current.list.search.label.remove"/></th>   
		                    <th width="2%" class="text14">
		            			<input title="Fjerne en eller flere" style="cursor:pointer;" type="button" value="M." name="currentordersColumnHeaderButtonRmv" id="currentordersColumnHeaderButtonRmv" onClick="getValidCheckis(this);">
		                    </th>
		                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.current.list.search.label.ourRef"/></th> 
							<th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.current.list.search.label.ttstat"/></th> 
							
							<th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.current.list.search.label.supplier"/></th>   
		                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.current.list.search.label.date"/>&nbsp;</th> 
		                    <th width="2%" class="text14">
		                    		<img style="vertical-align:bottom;" src="resources/images/clock2.png" width="12" height="12" border="0" alt="time">&nbsp;
	                   		</th>
		                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.current.list.search.label.consignee"/></th>
		                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.current.list.search.label.date"/>&nbsp;</th> 
		                    <th width="2%" class="text14">
		                    		<img style="vertical-align:bottom;" src="resources/images/clock2.png" width="12" height="12" border="0" alt="time">&nbsp;
	                   		</th>   
		                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.current.list.search.label.goodsDesc"/></th>   
		                    
		                    <th width="2%" class="text14">
	                    		<input title="Oppd.posisjon..."style="cursor:pointer;" type="button" value="<spring:message code="systema.transportdisp.orders.current.list.search.label.pos"/>" name="currentordersColumnHeaderButtonPos" id="currentordersColumnHeaderButtonPos" onClick="getValidPositions(this);">
		                    </th> 
		                    <th width="2%" class="text14" title="faktura status"><spring:message code="systema.transportdisp.orders.current.list.search.label.o"/></th>   
		                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.current.list.search.label.pcs"/></th>   
		                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.current.list.search.label.weight"/></th>   
		                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.current.list.search.label.volume"/></th>   
		                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.current.list.search.label.loadMtr"/></th>
		                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.current.list.search.label.poNr"/></th>
		                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.dangerousgoods.adr"/></th>
		                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.printDocs"/></th>  
		                </tr> 
		                </thead>
		              
		                <tbody >
			            <c:forEach items="${listCurrentOrders}" var="record" varStatus="counter">    
			               <tr height="20" >
			                  
			               <td align="center" width="2%" class="text14 tableCellGray">&nbsp;
			           		<a onClick="setBlockUI(this)" href="transportdisp_mainorderlist_add_remove_order.do?user=${user.user}&wmode=D&wstur=${searchFilter.tur}&wsavd=${record.heavd}&wsopd=${record.heopd}&wsprebook=${searchFilter.wsprebook}">
	    		    				<img title="Remove" style="vertical-align:bottom;" src="resources/images/remove.png" width="14" height="15" border="0" alt="remove">
			   				</a>
				           </td>
			               <td width="2%" class="text14 tableCellGray" align="center">
			               		<input class="clazz_checkis_currentorders" type="checkbox" id="checkis_currentorders${counter.count}@user=${user.user}&wmode=D&wstur=${searchFilter.tur}&wsavd=${record.heavd}&wsopd=${record.heopd}&wsprebook=${searchFilter.wsprebook}" >
			               </td>
			               <td align="center" width="2%" class="textMediumBlue tableCellGray">
			               		<c:choose>
			           				<c:when test="${ empty user.spedKuKod}">
						           		<a id="alinkCurrentOrdersListId_${counter.count}" onClick="setBlockUI(this);" href="transportdisp_mainorder.do?hepro=${searchFilter.tur}&heavd=${record.heavd}&heopd=${record.heopd}">
			    		    					<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">
			    		    					<font class="textMediumBlue">${record.heavd}/${record.heopd}</font>
					   					</a>
				   					</c:when>
				   					<c:otherwise>
				   						${record.heavd}/${record.heopd}
				   					</c:otherwise>
			   					</c:choose>
				           </td>
				           
				           <c:choose>
					           <c:when test="${not empty record.ttstat}">
					           		<c:choose>
					           		<c:when test="${record.ttstat=='Levert' || record.ttstat=='Urørt' || record.ttstat=='Hentet' }">
						           		<c:if test="${record.ttstat=='Levert'}" >   
						           			<td width="2%" align="center" class="text14 tableCellGray" >
							           			<font class="inputText11" style="background-color: #DFF2BF;color: #4F8A10;" onMouseOver="showPop('delivered_info${counter.count}');" onMouseOut="hidePop('delivered_info${counter.count}');">${record.ttstat}</font>
							           			<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute; left:15px; top:2px;" id="delivered_info${counter.count}" class="popupWithInputText"  >
							 							<font class="text11" >
										           			${record.hesgm}-${record.hedtmo}:${record.heklmo}
									           			</font>
													</span>
												</div>
						           			</td>
						           			
						           		</c:if>
						           		<c:if test="${record.ttstat=='Urørt'}" >   
						           			<td width="2%" align="center" class="text14 tableCellGray" style="color: #D8000C;">
							           			<font class="inputText11" style="background-color: #FFBABA;color: #D8000C;">${record.ttstat}</font>
							           			
						           			</td>
						           			
						           		</c:if>
						           		<c:if test="${record.ttstat=='Hentet'}" >   
						           			<td width="2%" align="center" class="text14 tableCellGray" style="color: #9F6000;">
							           			<font class="inputText11" style="background-color: #FEEFB3;color: #9F6000;">${record.ttstat}</font>
							           		</td>
						           			
						           		</c:if>
					           		</c:when>
					           		<c:otherwise>
					           			<td width="2%" align="center" class="text14 tableCellGray">&nbsp;${record.ttstat}</td>
					           		</c:otherwise>
					           		</c:choose>
					           </c:when>
					           <c:otherwise>
					           		<td width="2%" align="center" class="text14 tableCellGray">&nbsp;${record.ttstat}</td>
					           </c:otherwise>
			               </c:choose>
			               
			               <td width="2%" class="text14 tableCellGray">&nbsp;${record.henas}</td>
			               <td width="2%" class="text14 tableCellGray">&nbsp;${record.trsdfd}</td>
			               <td width="2%" class="text14 tableCellGray">&nbsp;${record.trsdfk}</td>
			               <td width="2%" class="text14 tableCellGray">&nbsp;${record.henak}</td>
			               <td width="2%" class="text14 tableCellGray">&nbsp;${record.trsdtd}</td>
			               <td width="2%" class="text14 tableCellGray">&nbsp;${record.trsdtk}</td>
			               <td width="2%" class="text14 tableCellGray">&nbsp;${record.hevs1}</td>
			               
			               <td width="2%" class="text14 tableCellGray">&nbsp;
			               		<input onKeyPress="return numberKey(event)" type="text" class="inputText11 clazz_position_currentorders" name="wspos${counter.count}" id="wspos${counter.count}" title="wspos${counter.count}@user=${user.user}&wsavd=${record.heavd}&wsopd=${record.heopd}" size="4" maxlength="3" value='${record.wspos}'>&nbsp;
			               </td>
			               <td width="2%" align="center" class="text14 tableCellGray">&nbsp;${record.hest}&nbsp;</td>
			               <td width="2%" align="right" class="text14 tableCellGray">
		               			<c:choose>
					        		<c:when test="${ not empty user.spedKuKod && not empty user.spedKuAvd}">
				               		<a style="padding:20px 10px;display:block;" target="popup" onclick="window.open('http://esped.ramberg.no/sycgip/syOPCL.pgm?user=${user.user}&avd=${record.heavd}&opd=${record.heopd}','popupLegacyColli','width=1000,height=450')">
				               			<font class="text14Orange">&nbsp;<b>${record.hent}</b></font>
				               		</a>
			               		</c:when>
			               		<c:otherwise>
			               			&nbsp;${record.hent}
			               		</c:otherwise>
			               		</c:choose>
			               </td>
			               <td width="2%" align="right" class="text14 tableCellGray">&nbsp;${record.hevkt}&nbsp;</td>
			               <td width="2%" align="right" class="text14 tableCellGray">&nbsp;${record.hem3}&nbsp;</td>
			               <td width="2%" align="right" class="text14 tableCellGray">&nbsp;${record.helm}&nbsp;</td>
			               <td width="2%" class="text14 tableCellGray">&nbsp;${record.herfa}</td>
			               <td width="2%" align="center" class="text14 tableCell11RedFont">&nbsp;${record.hepoen}</td>
			               
			               <td width="2%" align="center" class="textMediumBlue">
			               		<c:if test="${ empty user.spedKuKod}">
			               		<a title="print Oppd.&nbsp;${record.heopd}" class="printLinkCo" id="printLinkCo${counter.count}" runat="server" href="#">
									<img style="vertical-align: middle;" src="resources/images/printer3.png" width="20px" height="20px" border="0" alt="Print">
								</a>
								<div style="display: none;" class="clazz_dialogPrintCo" id="dialogPrintCo${counter.count}" title="Dialog Print">
								<form id="printFormCo${counter.count}">
								<input type="hidden" id="avdCo${counter.count}" name="avdCo${counter.count}" value="${record.heavd}">
								<input type="hidden" id="opdCo${counter.count}" name="opdCo${counter.count}" value="${record.heopd}">
								<input type="hidden" id="turCo${counter.count}" name="turCo${counter.count}" value="${Xrecord.hepro}">
								<input type="hidden" id="signPCo${counter.count}" name="signPCo${counter.count}" value="${record.hesg}">
							 	
							 	<table>
			   						<tr height="3"><td></td></tr>
			   						<tr>
										<td class="text14" align="left" >
											<c:choose>
											<c:when test="${record.hepk1 == 'J'}">
												<input type="checkbox" name="fbTypeCo${counter.count}" id="fbTypeCo${counter.count}" value="fb">
											</c:when>
											<c:otherwise>
												&nbsp;<img onMouseOver="showPop('fbTypeCo_info${counter.count}');" onMouseOut="hidePop('fbTypeCo_info${counter.count}');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/darkbluesquare.png" border="0" alt="info">&nbsp;
								 				<div class="text11" style="position: relative; display:inline;" align="left">
													<span style="position:absolute; left:0px; top:0px;width:200px;" id="fbTypeCo_info${counter.count}" class="popupWithInputText"  >
														<font class="text11">
									           			<b style="color:red;">Direct print out not available</b>
									           			<div>
									           			<p>Fraktbrev must be checked (and saved) in order to allow for direct print out</p>
									           			
									           			</div>
								           			</font>
													</span>
												</div>
											</c:otherwise>
											</c:choose>
											
											<span class="clazz_alinkFraktbrevPdfCo" id="alinkFraktbrevPdfCo${counter.count}" style="text-decoration: underline;" onMouseOver="style='color:lemonchiffon;cursor:pointer;text-decoration: underline;'" onMouseOut="style='color:black;text-decoration: underline;'">Fraktbrev</span>
										</td>	
										<td class="text14" align="left" >	
											<img class="clazz_imgFraktbrevPdfCo" id="imgFraktbrevPdfCo${counter.count}" title="Fraktbr.PDF" style="vertical-align:middle;cursor:pointer;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="Fraktbr. PDF">
											
										</td>
			   						</tr>
			   						<tr>
										<td class="text14" align="left" >
											<c:choose>
											<c:when test="${record.hepk7 == 'C'}">
												<input type="checkbox" name="cmrTypeCo${counter.count}" id="cmrTypeCo${counter.count}" value="cmr">
											</c:when>
											<c:otherwise>
												&nbsp;<img onMouseOver="showPop('cmrTypeCo_info${counter.count}');" onMouseOut="hidePop('cmrTypeCo_info${counter.count}');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/darkbluesquare.png" border="0" alt="info">&nbsp;
								 				<div class="text11" style="position: relative; display:inline;" align="left">
													<span style="position:absolute; left:0px; top:0px;width:200px;" id="cmrTypeCo_info${counter.count}" class="popupWithInputText"  >
														<font class="text11">
									           			<b style="color:red;">Direct print out not available</b>
									           			<div>
									           			<p>CMR-Fraktbrev must be checked (and saved) in order to allow for direct print out</p>
									           			
									           			</div>
								           			</font>
													</span>
												</div>
											</c:otherwise>
											</c:choose>
											
											<span class="clazz_alinkCmrFraktbrevPdfCo" id="alinkCmrFraktbrevPdfCo${counter.count}" style="text-decoration: underline;" onMouseOver="style='color:lemonchiffon;cursor:pointer;text-decoration: underline;'" onMouseOut="style='color:black;text-decoration: underline;'">CMR-Fraktbrev</span>
										</td>
										<td class="text14" align="left" >	
											<img class="clazz_imgCmrFraktbrevPdfCo" id="imgCmrFraktbrevPdfCo${counter.count}" title="CMR.PDF" style="vertical-align:middle;cursor:pointer;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="CMR. PDF">
										</td>
			   						</tr>
			   						<tr>
										<td class="text14" align="left" >
											<input type="checkbox" name="ffTypeCo${counter.count}" id="ffTypeCo${counter.count}" value="ff">
											<span class="clazz_alinkFFaktPdfCo" id="alinkFFaktPdfCo${counter.count}" >Ferdigmeldte-fakturaer</span>
										</td>
										
			   						</tr>
			   						<tr>
										<td colspan="2" class="text14" align="left" >
											<input type="checkbox" name="aordTypeCo${counter.count}" id="aordTypeCo${counter.count}" value="aord">
											<span id="alinkAordPdf" >Arbeidsordre</span>
											<c:choose>
											<c:when test="${not empty record.hepro}">
												<select class="inputTextMediumBlue" style="font-size:11px;background-color:#EEEEEE;" name="aordDocumentTypeCo${counter.count}" id="aordDocumentTypeCo${counter.count}" >
													<option value="S">Single</option>
						 							<option value="I">Intern</option>
						 							<option value="E">Ekstern</option>
												</select>
											</c:when>
											<c:otherwise>
												<input type="hidden" id="aordDocumentTypeCo${counter.count}" name="aordDocumentTypeCo${counter.count}" value="S">
											</c:otherwise>
											</c:choose>
										</td>
			   						</tr>
			   						<tr height="15"><td></td></tr>
									<tr>
										<td colspan="4" class="text14MediumBlue" align="left">
											<label id="printStatusCo${counter.count}"></label>
										</td>
									</tr>
								</table>
								
								</form>
								</div>
								</c:if>
		            		   </td>
		            		   
			            </tr> 
			            
			            </c:forEach>
			            </tbody>
			            
			            <tfoot>
						<tr style="background-color:#EEEEEE">
							<th align="left" class="text14">SUM.</th>
							<th class="text14">&nbsp;</th>
							<th class="text14">&nbsp;</th>
							<th class="text14">&nbsp;</th>
							<th class="text14">&nbsp;</th>
							<th class="text14">&nbsp;</th>
							<th class="text14">&nbsp;</th>
							<th class="text14">&nbsp;</th>
							<th class="text14">&nbsp;</th>
							   
							<th align="right" class="text14"><c:out value="${model.containerCurrentOrders.hentTotalAmount}"></c:out>&nbsp;</th>   
		                    <th align="right" class="text14"><c:out value="${model.containerCurrentOrders.hevktTotalAmount}"></c:out>&nbsp;</th>   
		                    <th align="right" class="text14"><c:out value="${model.containerCurrentOrders.hem3TotalAmount}"></c:out>&nbsp;</th>   
		                    <th align="right" class="text14"><c:out value="${model.containerCurrentOrders.helmTotalAmount}"></c:out>&nbsp;</th>
							<th class="text14">&nbsp;</th>
							<th class="text14">&nbsp;</th>
							<th class="text14">&nbsp;</th>
		                </tr> 
		                </tfoot>
		                
		            </table>
					</td>	
					</tr>
					
					<tr>
            		<td align="right" class="text14">
            			<table >
						<tr>
							<td>	
							<a href="transportDispWorkflowSpecificTripContentListExcelView.do" target="_new">
		                		<img valign="bottom" id="specificTripContentListExcel" src="resources/images/excel.gif" width="14" height="14" border="0" alt="excel">
		                		<font class="text12MediumBlue">&nbsp;Eksport til Excel</font>
	 	        			</a>
							</td>
						</tr>
						</table>
					</td>
					</tr>
					
					</table>
					</td>
				</tr>
				<tr height="10"><td></td></tr> 
			</c:if>
			
			<%-- OPEN ORDERS --%>
			<%-- search filter component --%>
			
			<tr>
				<td>
					
				<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
					 the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable2" width="100%" cellspacing="2" align="left" >
				
			    <tr>
   				    <form name="searchForm" id="searchForm" action="transportdisp_mainorderlist.do?action=doFind" method="post" >
					<input type="hidden" name="tur" id="tur" value='${searchFilter.tur}'>
					<input type="hidden" name="wstur" id="wstur" value='${searchFilter.tur}'>
					<input type="hidden" name="wssavd" id="wssavd" value='${model.wssavd}'>
					<input type="hidden" name="userAvd" id="userAvd" value='${model.userAvd}'>
			    	<td> 
			    	<table cellspacing="2" >
			    		<tr> 
			    			<td>
				        	&nbsp;<font title="wsprebook" class="text14"><spring:message code="systema.transportdisp.orders.open.search.label.prebook"/></font>
				        </td>
				        
				        <td>
				        	<c:choose>
				        		<c:when test="${ not empty user.spedKuKod && not empty user.spedKuAvd}">
					        		<select class="inputTextMediumBlue" name="wsprebook" id="wsprebook">
					        			<c:forEach var="record" items="${model.impExpValidList}" >
			                       	 	<option title="${record.value}" value="${record.key}" <c:if test="${searchFilter.wsprebook == record.key}"> selected </c:if> >${record.value}</option>
									</c:forEach>
								</select>
					        	</c:when>
					        	<c:otherwise>
					        		<select class="inputTextMediumBlue" name="wsprebook" id="wsprebook">
			 						<option value="A" <c:if test="${searchFilter.wsprebook == 'A'}"> selected </c:if> >Alle</option>
			 						<option value="F" <c:if test="${searchFilter.wsprebook == 'F'}"> selected </c:if> >Ordre</option>
			 						<option value="P" <c:if test="${searchFilter.wsprebook == 'P'}"> selected </c:if> >PreBook</option>
								</select>
							</c:otherwise>
							</c:choose>
				        </td>
			    		
				        <td>
							<font title="avd/wssavd" class="text14"><spring:message code="systema.transportdisp.orders.open.search.label.dept"/></font>
							<c:choose>
								<c:when test="${ not empty user.spedKuKod && not empty user.spedKuAvd}">
									&nbsp;
			 					</c:when>
			 					<c:otherwise>
			 						<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_avd.do?action=doFind','avdWin','top=100px,left=300px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
				 						<img id="imgAvdSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
				 					</a>
				 					<font id="objAvdGroupsList" class="text14SkyBlue" style="cursor:pointer;text-decoration: underline;">Grp</font>
			 					</c:otherwise>
		 					</c:choose>
			        		</td>
			        	    
			    			<td>
			    				<c:choose>
	 						<c:when test="${ not empty user.spedKuKod && not empty user.spedKuAvd}">
		 						<select class="inputTextMediumBlue" name="avd" id="avd">
				            		<option value="">-select-</option>
				 				  	<c:forEach var="record" items="${model.avdValidList}" >
			                       	 	<option title="${record}" value="${record}" <c:if test="${searchFilter.avd == record}"> selected </c:if> >${record}</option>
									</c:forEach> 
								</select>
							</c:when>
							<c:otherwise>
								<input type="text" class="inputTextMediumBlue" name="avd" id="avd" size="5" maxlength="4" value='${searchFilter.avd}'>
							</c:otherwise>
							</c:choose>
							
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
			        	
			        	<td>	
			        		&nbsp;<font title="opd/wssopd" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.ourRef"/></font>
			        		<%-- release 2 BRING (punkt (17)) and delete the above space with the link --%>
			        		<a href="javascript:void(0);" onClick="window.open('sporringoppdraggate.do?lang=NO&cw=true','opdWin','top=100px,left=200px,height=900px,width=1500px,scrollbars=no,status=no,location=no')">
		 						<img id="imgOpdSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
		 					</a>
		 					
				        </td>
				        
			        	
			        	<td>	
			        		<input type="text" class="inputTextMediumBlue" name="opd" id="opd" size="10" maxlength="15" value='${searchFilter.opd}'>
				        </td>
				        <td>	
			        		&nbsp;<font title="opdType/wssot" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.orderType"/></font>
				        	<a tabindex=-1 id="opdTypeIdLink" >	
								<img id="opdTypeImg" tabindex=0 style="vertical-align: middle;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
							</a>
				        </td>
				        
				        <td>	
			        		<select class="inputTextMediumBlue" name="opdType" id="opdType">
			            		<option value="">-select-</option>
			 				  	<c:forEach var="record" items="${model.oppdragstypeList}" >
		                       	 	<option title="${record.beskr}" value="${record.opdTyp}"<c:if test="${searchFilter.opdType == record.opdTyp}"> selected </c:if> >${record.opdTyp}</option>
								</c:forEach> 
							</select>	
							
			        		
				        </td>
				        <td>	
			        		&nbsp;<font title="wsavs" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.shipper"/></font>
				        </td>
				        <td>	
			        		<input type="text" class="inputTextMediumBlue" name="wsavs" id="wsavs" size="10" maxlength="20" value='${searchFilter.wsavs}'>
				        </td>
			        	<td>	
			        		&nbsp;<font title="from/wssdf" class="text14"><spring:message code="systema.transportdisp.orders.open.search.label.from"/></font>
				        </td>
				        <td>	
			        		<input type="text" class="inputTextMediumBlue" name="from" id="from" size="9" maxlength="8" value='${searchFilter.from}'>
				        </td>
				        <td>	
				        	&nbsp;<font title="fromDateF/fromDateT-wopdtf/wopdtf2" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.date"/></font>
				        </td>
				        
				        <td>	
			        		<input autocomplete="off" type="text" class="inputTextMediumBlue" name="fromDateF" id="fromDateF" size="9" maxlength="8" value='${searchFilter.fromDateF}'>
				        	-<input autocomplete="off" type="text" class="inputTextMediumBlue" name="fromDateT" id="fromDateT" size="9" maxlength="8" value='${searchFilter.fromDateT}'>
				        </td>
				        
				        
				        </tr>
				        
				        
				        <tr> 
				        <td>
				        	&nbsp;<font title="wsdista" class="text14"><spring:message code="systema.transportdisp.orders.open.search.label.ordreType"/></font>
				        </td>
				        <td>
				        		<c:choose>
				        		<c:when test="${ not empty user.spedKuKod && not empty user.spedKuAvd}">
					        		<select class="inputTextMediumBlue" name="wsdista" id="wsdista">
			 						<option value="" >Udisp.ordrer</option>
		 						</select>
							</c:when>
							<c:otherwise>
								<select class="inputTextMediumBlue" name="wsdista" id="wsdista">
			 						<option value="" <c:if test="${empty searchFilter.wsdista}"> selected </c:if> >Udisp.ordrer</option>
			 						<option value="V" <c:if test="${searchFilter.wsdista == 'V'}"> selected </c:if>  >Vent.ordrer</option>
			 						<option value="E" <c:if test="${searchFilter.wsdista == 'E'}"> selected </c:if> >EDI ordrer</option>
								</select>
							</c:otherwise>
							</c:choose>
				        </td>
				        
				        <td align="right">	
			        		&nbsp;<font title="sign/wsssg" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.sign"/></font>
				        </td>
						<td>	
			        		<input type="text" class="inputTextMediumBlue" name="sign" id="sign" size="4" maxlength="5" value='${searchFilter.sign}'>
				        </td>
				        <td>	
			        		&nbsp;<font title="wsrfa" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.poNr"/></font>
				        </td>
						<td>	
			        		<input type="text" class="inputTextMediumBlue" name="wsrfa" id="wsrfa" size="10" maxlength="15" value='${searchFilter.wsrfa}'>
				        </td>
				        <td>	
			        		&nbsp;<font title="wsopdgi" class="text14">Op.giver</font>
				        </td>

						<td>	
			        		<input type="text" class="inputTextMediumBlue" name="wsopdgi" id="wsopdgi" size="10" maxlength="20" value='${searchFilter.wsopdgi}'>
				        </td>
				        <td>	
			        		&nbsp;<font title="wsmot" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.consignee"/></font>
				        </td>

						<td>	
			        		<input type="text" class="inputTextMediumBlue" name="wsmot" id="wsmot" size="10" maxlength="20" value='${searchFilter.wsmot}'>
				        </td>
			        	<td>	
				        	&nbsp;<font title="to/wssdt" class="text14"><spring:message code="systema.transportdisp.orders.open.search.label.to"/></font>
				        </td>
			        	
				        <td>	
				        	<input type="text" class="inputTextMediumBlue" name="to" id="to" size="9" maxlength="8" value='${searchFilter.to}'>
				        </td>
				        <td>	
				        	&nbsp;<font title="toDateF/toDateT-wopdtt/wopdtt2" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.date"/></font>
				        </td>
				        
				        <td>	
			        		<input autocomplete="off" type="text" class="inputTextMediumBlue" name="toDateF" id="toDateF" size="9" maxlength="8" value='${searchFilter.toDateF}'>
				        	-<input autocomplete="off" type="text" class="inputTextMediumBlue" name="toDateT" id="toDateT" size="9" maxlength="8" value='${searchFilter.toDateT}'>
				        </td>
				        <td >	
				        	<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.transportdisp.search"/>'>
				        	<input onClick="setBlockUI(this);" class="inputFormSubmitStd" type="button" name="removeFilterButton" id="removeFilterButton" value='<spring:message code="systema.transportdisp.search.remove.filter"/>'>
				        </td> 
				        </tr>
				        
				        <tr>
					        <td>	
				        		&nbsp;<font title="wsfskod" class="text14">Frisøk.kode</font>
					        </td>
					        <td>	
				        		<input type="text" class="inputText" name="wsfskod" id="wsfskod" size="4" maxlength="3" value='${searchFilter.wsfskod}'>
					        </td>
					        <td align="right">	
				        		&nbsp;<font title="wsfssok" class="text14">Frisøk.txt</font>
					        </td>
					        <td colspan="3">	
				        		<input type="text" class="inputText" name="wsfssok" id="wsfssok" size="10" maxlength="35" value='${searchFilter.wsfssok}'>
					        </td>
				        </tr>
				        
						<tr height="5"><td></td></tr>
						<c:if test="${ empty user.spedKuKod}">
					        <tr>
							<td colspan="2" class="text14MediumBlue">
					            <input class="inputFormSubmitStd" tabindex=0 style="cursor:pointer;" type="button" value="<spring:message code="systema.transportdisp.orders.open.form.button.createnew.trip"/>" name="cnButton" id="cnButton">
					        </td>
					        </tr>
				        </c:if>
				    </table>    
					</td>
					</form>
				</tr>
				<tr height="5"><td></td></tr>
				
				<c:if test="${not empty model.containerOpenOrders.maxWarning}">
					<tr>	
						<td class="listMaxLimitWarning">
						<img style="vertical-align:bottom;" src="resources/images/redFlag.png" width="16" height="16" border="0" alt="Warning">
						${model.containerOpenOrders.maxWarning}</td>
					</tr>
				</c:if>
				
				<tr height="5"><td></td></tr>
				<tr>
				<td >
				<table id="openOrders" class="display compact cell-border" width="100%">
					<thead >
					<tr class="tableHeaderField" >
						<c:if test="${not empty searchFilter.tur}">
							<th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.pick"/></th>   
                    		<th width="2%" class="text14"><input title="Plukk en eller flere" style="cursor:pointer;" type="button" value="M." name="openordersColumnHeaderButton" id="openordersColumnHeaderButton" onClick="getValidCheckis(this);"></th>
                   		</c:if>
                   		<th title="avd/ordre" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.ourRef"/></th> 
                   		<c:if test="${empty searchFilter.tur}">
                   			<th width="2%" class="text14"></th>   
                   		</c:if>  
                   		<th title="Track&Trace status:Urørt/Hentet/Levert" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.current.list.search.label.ttstat"/></th> 
                   		<th title="Oppdragstype" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.orderType"/></th>
	                    <th title="Signatur" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.sign"/></th>
	                    <c:if test="${empty searchFilter.tur && not empty searchFilter.opd}">
							<th title="Ordretype" width="2%" class="text14">Turnr</th>
	                    </c:if>
	                    <th title="Avsender" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.shipper"/></th>   
	                    <th title="Fra" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.from"/></th> 
	                    <th title="Dato" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.date"/></th> 
	                    <th title="Mottaker" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.consignee"/></th>   
	                    <th title="Til" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.to"/></th> 
	                    <th title="Dato" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.date"/></th>
	                     
	                    <th title="Antall" align="right" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.pcs"/></th>   
	                    <th title="V.slag" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.goodsDesc"/></th>   
	                    <th title="Vekt" align="right" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.weight"/></th>   
	                    <th title="M3" align="right" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.volume"/></th>   
	                    <th title="Lm" align="right" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.loadMtr"/></th>  
	                    <th title="PONr" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.poNr"/></th>
	                    <th title="Farliggods" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.dangerousgoods.adr"/></th>
	                    <th title="Internmelding" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.internmelding.text"/></th>
	                    <th title="MerkMott" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.merkmott"/></th>
	                    <th title="Print" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.printDocs"/></th>
	                    <%--
	                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.fraktbrev"/></th>
	                     --%>
	                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.copy"/></th>
	                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.move"/></th>
	                    <th width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.delete"/></th>
	                    <th title="P=Prebooking. Linjen lys gul!" width="2%" class="text14"><spring:message code="systema.transportdisp.orders.open.list.search.label.prebooking"/></th>
	                    
	                </tr> 
	                </thead>
	                
	                
	                <tbody >
		            <c:forEach items="${listOpenOrders}" var="record" varStatus="counter">  
		            <%-- Prebooking=hestn7: should be categorized with another color. In this case with warning color... --%>  
		            <tr class="tex14" <c:if test="${not empty record.hestn7}"> style="background-color:#FEEFB3; color: #9F6000;"</c:if> >
		               <c:if test="${not empty searchFilter.tur}">
                   			<td align="center" width="2%" class="textMediumBlue">&nbsp;
			           			<a style="display:block;" onClick="setBlockUI(this)" href="transportdisp_mainorderlist_add_remove_order.do?user=${user.user}&wmode=A&wstur=${searchFilter.tur}&wsavd=${record.heavd}&wsopd=${record.heopd}&wsprebook=${searchFilter.wsprebook}">
    		    							<img title="Add" style="vertical-align:bottom;" src="resources/images/addOrder.png" width="14" hight="15" border="0" alt="add">
		   						</a>
		   					</td>
		   					<td align="center" width="2%" class="textMediumBlue" ><input class="clazz_checkis_openorders" type="checkbox" id="checkis_openorders${counter.count}@user=${user.user}&wmode=A&wstur=${searchFilter.tur}&wsavd=${record.heavd}&wsopd=${record.heopd}&wsprebook=${searchFilter.wsprebook}"></td>		
            		   		</c:if>
            		   
            		   <td width="2%" class="textMediumBlue" nowrap>
		           		<div id="davd${record.heavd}_dopd${record.heopd}_linkcontainer${counter.count}" ondrop="drop(event)" ondragenter="highlightDropArea(event)" ondragleave="noHighlightDropArea(event)" ondragover="allowDrop(event)" >
		           		<c:choose>
		           		<c:when test="${empty searchFilter.tur && not empty searchFilter.opd}">
		           			<c:choose>
		           			<c:when test="${ empty user.spedKuKod}">
		           				<a style="cursor:pointer;" id="hepro_${record.hepro}@heavd_${record.heavd}@heopd_${record.heopd}@alinkOpenOrdersListId_${counter.count}" onClick="goToSpecificOrder(this);">
		    		    					<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">
			    		    				<font class="textMediumBlue">${record.heavd}/${record.heopd}</font>
		    		    				</a>
	    		    				</c:when>
	    		    				<c:otherwise>
	    		    						${record.heavd}/${record.heopd}
	    		    				</c:otherwise>
	    		    				</c:choose>
	    		    			</c:when>
	    		    			<c:otherwise>
	    		    				<c:choose>
		           			<c:when test="${ empty user.spedKuKod}">
	    		    				<a style="cursor:pointer;" id="hepro_${searchFilter.tur}@heavd_${record.heavd}@heopd_${record.heopd}@alinkOpenOrdersListId_${counter.count}" onClick="goToSpecificOrder(this);">
		    		    				<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">
		    		    				<font class="textMediumBlue">${record.heavd}/${record.heopd}</font>
		    		    			</a>
		    		    			</c:when>
		    		    			<c:otherwise>
	    		    						${record.heavd}/${record.heopd}
	    		    				</c:otherwise>
	    		    				</c:choose>
	    		    			</c:otherwise>
    		    			</c:choose>
    		    			</div>
			           </td>
			           <c:if test="${empty searchFilter.tur}">
			           	   <c:choose>
		           			<c:when test="${ empty user.spedKuKod}">	
					           <td width="2%" class="textMediumBlue">
				           			<%-- Drag and drop handle (when being source) --%>
				 			  		&nbsp;<img title="Drag to target..." style="vertical-align:middle;cursor:pointer;" src="resources/images/icon_drag_drop.png" width="25px" height="25px" border="0" alt="edit" draggable="true" ondragstart="drag(event)" id="avd_${record.heavd}@opd_${record.heopd}@tripnr_@${counter.count}">
							   </td>
						   </c:when>
						   <c:otherwise>
						   		<td width="2%">&nbsp;</td>
						   </c:otherwise>
						   </c:choose>
					   </c:if>	
					   
					   <c:choose>
					           <c:when test="${not empty record.ttstat}">
					           		<c:choose>
					           		<c:when test="${record.ttstat=='Levert' || record.ttstat=='Urørt' || record.ttstat=='Hentet' }">
						           		<c:if test="${record.ttstat=='Levert'}" >   
						           			<td width="2%" align="center" class="textMediumBlue" >
							           			<font class="inputText11" style="background-color: #DFF2BF;color: #4F8A10;" onMouseOver="showPop('delivered_info${counter.count}');" onMouseOut="hidePop('delivered_info${counter.count}');">${record.ttstat}</font>
							           			<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute; left:15px; top:2px;" id="delivered_info${counter.count}" class="popupWithInputText"  >
							 							<font class="text11" >
										           			${record.hesgm}-${record.hedtmo}:${record.heklmo}
									           			</font>
													</span>
												</div>
												<c:if test="${not empty record.ttstat2}" > 
						           					&nbsp;${record.ttstat2}
						           				</c:if>
						           			</td>
						           		</c:if>
						           		<c:if test="${record.ttstat=='Urørt'}" >   
						           			<td width="2%" align="center" class="textMediumBlue" style="color: #D8000C;">
							           			<font class="inputText11" style="background-color: #FFBABA;color: #D8000C;">${record.ttstat}</font>
							           			<c:if test="${not empty record.ttstat2}" > 
						           					&nbsp;${record.ttstat2}
						           				</c:if>
						           			</td>
						           		</c:if>
						           		<c:if test="${record.ttstat=='Hentet'}" >   
						           			<td width="2%" align="center" class="textMediumBlue" style="color: #9F6000;">
							           			<font class="inputText11" style="background-color: #FEEFB3;color: #9F6000;">${record.ttstat}</font>
							           			<c:if test="${not empty record.ttstat2}" > 
						           					&nbsp;${record.ttstat2}
						           				</c:if>
						           			</td>
						           		</c:if>
					           		</c:when>
					           		<c:otherwise>
					           			<td width="2%" align="center" class="textMediumBlue">&nbsp;${record.ttstat}</td>
					           		</c:otherwise>
					           		</c:choose>
					           </c:when>
					           <c:otherwise>
					           		<td width="2%" align="center" class="textMediumBlue">&nbsp;${record.ttstat}</td>
					           </c:otherwise>
			               </c:choose>
			               
					   						 		
			           <td width="2%" align="center" class="textMediumBlue">&nbsp;${record.heot}</td>
		               <td width="2%" align="center" class="textMediumBlue">&nbsp;${record.hesg}</td>
		               <c:if test="${empty searchFilter.tur && not empty searchFilter.opd}">
							<td width="2%" class="textMediumBlue">&nbsp;${record.hepro}</td>	
	                   </c:if>
		               <td width="2%" class="textMediumBlue">&nbsp;${record.henas}</td>
		               <td width="2%" class="textMediumBlue">&nbsp;${record.helka}-${record.heads3}</td>
		               <td width="2%" class="textMediumBlue">&nbsp;${record.trsdfd}&nbsp;${record.trsdfk}</td>
		               <td width="2%" class="textMediumBlue">&nbsp;${record.henak}</td>
		               <td width="2%" class="textMediumBlue">&nbsp;${record.hetri}-${record.headk3}</td>
		               <td width="2%" class="textMediumBlue">&nbsp;${record.trsdtd}&nbsp;${record.trsdtk}</td>
		               <td align="right" width="2%" class="textMediumBlue">
		               		<c:choose>
				        		<c:when test="${ not empty user.spedKuKod && not empty user.spedKuAvd}">
			               		<a style="padding:20px 10px;display:block;" target="popup" onclick="window.open('http://esped.ramberg.no/sycgip/syOPCL.pgm?user=${user.user}&avd=${record.heavd}&opd=${record.heopd}','popupLegacyColli','width=1000,height=450')">
			               			<font class="text14Orange">&nbsp;<b>${record.hent}</b></font>
			               		</a>
		               		</c:when>
		               		<c:otherwise>
		               			&nbsp;${record.hent}
		               		</c:otherwise>
		               		</c:choose>
		               </td>
		               <td width="2%" class="textMediumBlue">&nbsp;${record.hevs1}</td>
		               <td align="right" width="2%" class="textMediumBlue">&nbsp;${record.hevkt}</td>
		               <td align="right" width="2%" class="textMediumBlue">&nbsp;${record.hem3}</td>
		               <td align="right" width="2%" class="textMediumBlue">&nbsp;${record.helm}</td>
		               <td width="2%" class="textMediumBlue">&nbsp;${record.herfa}</td>
		               <td width="2%" align="center" class="textMediumBlue" style="color:red;">&nbsp;${record.hepoen}</td>
		               <td width="2%" align="center" class="textMediumBlue">
		               		<c:if test="${not empty record.interninfo}">
			               		<img onMouseOver="showPop('imText_info${counter.count}');" onMouseOut="hidePop('imText_info${counter.count}');" style="vertical-align:bottom;" src="resources/images/info4.png" width="12" height="12" border="0" alt="Internmelding">
			               		
			               		<div class="text10" style="position: relative;" align="left">
		 						<span style="position:absolute; left:-50px; top:2px; width:250px;" id="imText_info${counter.count}" class="popupWithInputText"  >
		 							<font class="text10">
					           			<b>Internmelding</b>
					           			<p>${record.interninfo}</p>
				           			</font>
								</span>
								</div>
							</c:if>
		               </td>
		               <td width="2%" align="center" class="textMediumBlue">
		               <a title="Merk mottat, Avd.&nbsp;${record.heavd}&nbsp;Oppd.&nbsp;${record.heopd}" class="merkmottLink" id="avd${record.heavd}_opd${record.heopd}" >
							<img style="vertical-align: middle;" src="resources/images/testsuites.png" width="18px" height="18px" border="0" alt="Merk mottat">
						</a>
				       </td>			
		               <td width="2%" align="center" class="textMediumBlue">
		               		<c:if test="${ empty user.spedKuKod}">
		               		<a title="print Oppd.&nbsp;${record.heopd}" class="printLink" id="printLink${counter.count}" runat="server" href="#">
								<img style="vertical-align: middle;" src="resources/images/printer3.png" width="20px" height="20px" border="0" alt="Print">
							</a>
							<div style="display: none;" class="clazz_dialogPrint" id="dialogPrint${counter.count}" title="Dialog Print">
									<form id="printForm${counter.count}">
										<input type="hidden" id="avd${counter.count}" name="avd${counter.count}" value="${record.heavd}">
										<input type="hidden" id="opd${counter.count}" name="opd${counter.count}" value="${record.heopd}">
										<input type="hidden" id="tur${counter.count}" name="tur${counter.count}" value="${Xrecord.hepro}">
										<input type="hidden" id="signP${counter.count}" name="signP${counter.count}" value="${record.hesg}">
								 	<table>
				   						<tr height="3"><td></td></tr>
				   						<tr>
											<td class="text14" align="left" >
												<c:choose>
												<c:when test="${record.hepk1 == 'J'}">
													<input type="checkbox" name="fbType${counter.count}" id="fbType${counter.count}" value="fb">
												</c:when>
												<c:otherwise>
													&nbsp;<img onMouseOver="showPop('fbType_info${counter.count}');" onMouseOut="hidePop('fbType_info${counter.count}');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/darkbluesquare.png" border="0" alt="info">&nbsp;
									 				<div class="text11" style="position: relative; display:inline;" align="left">
														<span style="position:absolute; left:0px; top:0px;width:200px;" id="fbType_info${counter.count}" class="popupWithInputText"  >
															<font class="text11">
										           			<b style="color:red;">Direct print out not available</b>
										           			<div>
										           			<p>Fraktbrev must be checked (and saved) in order to allow for direct print out</p>
										           			
										           			</div>
									           			</font>
														</span>
													</div>
												</c:otherwise>
												</c:choose>
												
												<span class="clazz_alinkFraktbrevPdf" id="alinkFraktbrevPdf${counter.count}" style="text-decoration: underline;" onMouseOver="style='color:lemonchiffon;cursor:pointer;text-decoration: underline;'" onMouseOut="style='color:black;text-decoration: underline;'">Fraktbrev</span>
											</td>	
											<td class="text14" align="left" >	
												<img class="clazz_imgFraktbrevPdf" id="imgFraktbrevPdf${counter.count}" title="Fraktbr.PDF" style="vertical-align:middle;cursor:pointer;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="Fraktbr. PDF">
												
											</td>
				   						</tr>
				   						<tr>
											<td class="text14" align="left" >
												<c:choose>
												<c:when test="${record.hepk7 == 'C'}">
													<input type="checkbox" name="cmrType${counter.count}" id="cmrType${counter.count}" value="cmr">
												</c:when>
												<c:otherwise>
													&nbsp;<img onMouseOver="showPop('cmrType_info${counter.count}');" onMouseOut="hidePop('cmrType_info${counter.count}');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/darkbluesquare.png" border="0" alt="info">&nbsp;
									 				<div class="text11" style="position: relative; display:inline;" align="left">
														<span style="position:absolute; left:0px; top:0px;width:200px;" id="cmrType_info${counter.count}" class="popupWithInputText"  >
															<font class="text11">
										           			<b style="color:red;">Direct print out not available</b>
										           			<div>
										           			<p>CMR-Fraktbrev must be checked (and saved) in order to allow for direct print out</p>
										           			
										           			</div>
									           			</font>
														</span>
													</div>
												</c:otherwise>
												</c:choose>
												
												<span class="clazz_alinkCmrFraktbrevPdf" id="alinkCmrFraktbrevPdf${counter.count}" style="text-decoration: underline;" onMouseOver="style='color:lemonchiffon;cursor:pointer;text-decoration: underline;'" onMouseOut="style='color:black;text-decoration: underline;'">CMR-Fraktbrev</span>
											</td>
											<td class="text14" align="left" >	
												<img class="clazz_imgCmrFraktbrevPdf" id="imgCmrFraktbrevPdf${counter.count}" title="CMR.PDF" style="vertical-align:middle;cursor:pointer;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="CMR. PDF">
											</td>
				   						</tr>
				   						<tr>
											<td class="text14" align="left" >
												<input type="checkbox" name="ffType${counter.count}" id="ffType${counter.count}" value="ff">
												<span class="clazz_alinkFFaktPdf" id="alinkFFaktPdf${counter.count}" >Ferdigmeldte-fakturaer</span>
											</td>
											
				   						</tr>
				   						<tr>
											<td colspan="2" class="text14" align="left" >
												<input type="checkbox" name="aordType${counter.count}" id="aordType${counter.count}" value="aord">
												<span id="alinkAordPdf" >Arbeidsordre</span>
												<c:choose>
												<c:when test="${not empty record.hepro}">
													<select class="inputTextMediumBlue" style="font-size:11px;background-color:#EEEEEE;" name="aordDocumentType${counter.count}" id="aordDocumentType${counter.count}" >
														<option value="S">Single</option>
							 							<option value="I">Intern</option>
							 							<option value="E">Ekstern</option>
													</select>
												</c:when>
												<c:otherwise>
													<input type="hidden" id="aordDocumentType${counter.count}" name="aordDocumentType${counter.count}" value="S">
												</c:otherwise>
												</c:choose>
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
								</c:if>
	           		   				
            		   </td>
		               
            		   <td width="2%" align="center" class="textMediumBlue">
            		   			<c:if test="${ empty user.spedKuKod}">
		               		<a title="copy" class="copyLink" id="copyLink${counter.count}" runat="server" href="#">
								<img src="resources/images/copy.png" border="0" alt="copy">
							</a>
							<div style="display: none;" class="clazz_dialog" id="dialog${counter.count}" title="Dialog">
								<form  action="transportdisp_mainorderlist_copy_order.do" name="copyForm${counter.count}" id="copyForm${counter.count}" method="post">
								 	<input type="hidden" name="action${counter.count}" id="action${counter.count}" value='doUpdate'/>
									<input type="hidden" name="originalAvd${counter.count}" id="originalAvd${counter.count}" value='${record.heavd}'/>
				 					<input type="hidden" name="originalOpd${counter.count}" id="originalOpd${counter.count}" value='${record.heopd}'/>
					 				<input type="hidden" name="sign${counter.count}" id="sign${counter.count}" value='${user.signatur}'/>
					 				
									<p class="text14" >Du kan velge en ny avdeling</p>
									<p class="text14" >En ny oppdrag vil bli etablert automatisk.</p>
									
									<table>
										<tr>
											<td class="text14" align="left" >Ny Avd.</td>
											<td class="text14MediumBlue">
												<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue newAvd" name="newAvd${counter.count}" id="newAvd${counter.count}" size="5" maxlength="4" value="${record.heavd}">
											</td>
                						</tr>
									</table>
								</form>
							</div>
							</c:if>
		               </td>
		               
		               <td width="2%" align="center" class="textMediumBlue">
		               		<c:if test="${ empty user.spedKuKod}">
		               		<a title="move" class="moveLink" id="moveLink${counter.count}" runat="server" href="#">
								<img src="resources/images/move.png" width="18" height="18" border="0" alt="move">
							</a>
							<div style="display: none;" class="clazz_dialogMove" id="dialogMove${counter.count}" title="Dialog">
								<form  action="transportdisp_mainorderlist_move_order.do" name="moveForm${counter.count}" id="moveForm${counter.count}" method="post">
								 	<input type="hidden" name="action${counter.count}" id="action${counter.count}" value='doUpdate'/>
									<input type="hidden" name="originalAvd${counter.count}" id="originalAvd${counter.count}" value='${record.heavd}'/>
				 					<input type="hidden" name="originalOpd${counter.count}" id="originalOpd${counter.count}" value='${record.heopd}'/>
					 				
									<p class="text14" >Du må velge en ny avdeling</p>
									<p class="text14" >Denne oppdrag vil bli flyttet.</p>
									
									<table>
										<tr>
											<td class="text14" align="left" ><b>Ny Avd.</b></td>
											<td class="text14MediumBlue">
												<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue newAvdMove" name="newAvdMove${counter.count}" id="newAvdMove${counter.count}" size="5" maxlength="4" value="">
											</td>
                						</tr>
									</table>
								</form>
							</div>
							</c:if>
		               </td>
		               <td width="2%" align="center" class="textMediumBlue">
		               		<c:if test="${ empty user.spedKuKod}">
	            		  	 		<a id="avd_${record.heavd}@opd_${record.heopd}" title="delete" onClick="doPermanentlyDeleteOrder(this)" tabindex=-1>
				               		<img src="resources/images/delete.gif" border="0" alt="remove">
				               	</a>&nbsp;
			               	</c:if>
					   </td>
					   <td width="2%" align="center" class="text14RedBold">&nbsp;${record.hestn7}</td>
		               
					   			               	
		            </tr> 
		            </c:forEach>
		            </tbody>
		            
	            </table>
				</td>	
				</tr>
				
				 
				
				<tr>
            		<td align="right" class="text14text14">
            		<table >
						<tr>
						<td>	
							<a href="transportDispWorkflowOpenOrdersListExcelView.do" target="_new">
			                		<img valign="bottom" id="openOrdersListExcel" src="resources/images/excel.gif" width="14" height="14" border="0" alt="excel">
			                		<font class="text14MediumBlue">&nbsp;Eksport til Excel</font>
			 	        		</a>
			 	        		&nbsp;
		 	        		</td>
		 	        		<%--TODO
		 	        		<td>		            		
			 	        		<a href="todo.do" target="_new">
			                		<img valign="bottom" id="printer" src="resources/images/printer.png" width="14" height="14" border="0" alt="print">
			                		<font class="text12MediumBlue">&nbsp;Print</font>
			 	        		</a>
			 	        		&nbsp;
		 	        		</td>
		 	        		 --%>
		 	        		<td class="text12" width="15px">&nbsp;</td>
	 	        		</tr>
 	        		</table>
			 		</td>
	         	</tr>
				</table>
				</td>
			</tr>
			
			<tr height="10"><td></td></tr>
			
			<c:if test="${not empty searchFilter.tur}">
				<c:if test="${ empty user.spedKuKod}">
				<tr>
					<td valign="bottom" >
						<%--<span style="position:absolute; left:1600px; top:160px; width:480px; height:250px;" id="economyMatrixInfo" class="popupFloating"  > --%>
		           		<div id="dialogDraggableMatrix" title="Økonomi">
		           		<p>
		           		<table align="left" class="popupFloatingWithRoundCorners3D">
						    <tr height="10"><td></td></tr>
						    <tr>
					    		<td class="text14" title="tuao/tuts"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.ordersColli"/></td>
					    		<td colspan="2" class="text14" title="berbud"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.estimatedTransportCost"/></td>
					    		<td class="text14"><input readonly tabindex=-1 type="text10" class="inputTextMediumBlueReadOnlyMateBg" style="text-align:right;" name="berbud" id="berbud" size="6" value="${model.record.berbud}"></td>
					    	</tr>
					    	<tr>
					    		<td class="text14" nowrap>
					    			<input readonly tabindex=-1 type="text" class="inputTextMediumBlueReadOnlyMateBg" style="text-align:center;" name="tuao" id="tuao" size="6" value="${model.record.tuao}">
					    			<b>/</b>
					    			<input readonly tabindex=-1 type="text" class="inputTextMediumBlueReadOnlyMateBg" style="text-align:center;" name="tuts" id="tuts" size="4" value="${model.record.tuts}">
					    		</td>
					    		<td class="tableHeaderFieldFirst11" align="center"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.header.open"/></td>
					    		<td class="tableHeaderField11" align="center"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.header.finished"/></td>
					    		<td class="tableHeaderField11" align="right"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.header.sum"/>&nbsp;</td>
					    	</tr>
					    	<tr class="tableRow">	
					    		<td class="text14" >
					    			<img onMouseOver="showPop('totiaa_info');" onMouseOut="hidePop('totiaa_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="totiaa/totioa/totisa"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.line.inntekt.avrgrl"/></span>
					    		</td>
				    			<td class="tableCellFirst" align="right"><label name="totiaa" id="totiaa">${model.record.totiaa}&nbsp;</label></td>
				    			<td class="tableCell" align="right"><label name="totioa" id="totioa">${model.record.totioa}&nbsp;</label></td>
				    			<td class="tableCell" align="right"><label name="totisa" id="totisa">${model.record.totisa}&nbsp;</label></td>
					    	</tr>
					    	<tr class="tableRow">	
					    		<td class="text14" >
					    			<img onMouseOver="showPop('totiaa_info');" onMouseOut="hidePop('totiaa_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="totiag/totiog/totisg"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.line.inntekt.ovriga"/></span>
					    		
					    		<div class="text10" style="position: relative;" align="left">
		 						<span style="position:absolute; top:10px; width:350px;" id="totiaa_info" class="popupWithInputText"  >
		 							<font class="text10">
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
				    			<td class="tableCellFirst" align="right"><label name="totiag" id="totiag">${model.record.totiag}&nbsp;</label></td>
				    			<td class="tableCell" align="right"><label name="totiog" id="totiog">${model.record.totiog}&nbsp;</label></td>
				    			<td class="tableCell" align="right"><label name="totisg" id="totisg">${model.record.totisg}&nbsp;</label></td>
					    	</tr>
					    	<tr class="tableRow">	
					    		<td class="text14">
					    			<img onMouseOver="showPop('totkaa_info');" onMouseOut="hidePop('totkaa_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="totkaa/totkoa/totksa"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.line.kostnad.avrtrans"/></span>
					    			<div class="text10" style="position: relative;" align="left">
		 						<span style="position:absolute; top:0px; width:350px;" id="totkaa_info" class="popupWithInputText"  >
		 							<font class="text10">
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
				    			<td class="tableCellFirst" align="right"><label name="totkaa" id="totkaa">${model.record.totkaa}&nbsp;</label></td>
				    			<td class="tableCell" align="right"><label name="totkoa" id="totkoa">${model.record.totkoa}&nbsp;</label></td>
				    			<td class="tableCell" align="right"><label name="totksa" id="totksa">${model.record.totksa}&nbsp;</label></td>
	
					    	</tr>
					    	<tr class="tableRow">	
					    		<td class="text14">
					    			<img onMouseOver="showPop('totkao_info');" onMouseOut="hidePop('totkao_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="totkao/totkoo/totkso"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.line.kostnad.ovriga"/></span>
					    			<div class="text11" style="position: relative;" align="left">
		 						<span style="position:absolute; top:0px; width:500px;" id="totkao_info" class="popupWithInputText"  >
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
				    			<td class="tableCellFirst" align="right"><label name="totkao" id="totkao">${model.record.totkao}&nbsp;</label></td>
				    			<td class="tableCell" align="right"><label name="totkoo" id="totkoo">${model.record.totkoo}&nbsp;</label></td>
				    			<td class="tableCell" align="right"><label name="totkso" id="totkso">${model.record.totkso}&nbsp;</label></td>
					    	</tr>
					    	<tr class="tableRow">
					    		<td class="text14Bold" title="totopn/totovf/totsum"><spring:message code="systema.transportdisp.workflow.trip.form.label.economy.matrix.line.sum.resultat"/></td>
				    			<td class="tableCellFirst" align="right"><label name="totopn" id="totopn"><b>${model.record.totopn}&nbsp;</b></label></td>
				    			<td class="tableCell" align="right"><label name="totovf" id="totovf"><b>${model.record.totovf}&nbsp;</b></label></td>
				    			<td class="tableCell" align="right"><label name="totsum" id="totsum"><b>${model.record.totsum}&nbsp;</b></label></td>
					    	</tr>
					    	<tr height="10"><td></td></tr>
					    	<%--
					    	<tr align="left" >
								<td class="text12">
									<button name="economyMatrixButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('economyMatrixInfo');">&nbsp;Close</button> 
								</td>
							</tr>
							 --%> 
							</table>
							</p>
						  </div>	
					</td>
				</tr>
				</c:if>	
			</c:if>
			</table>
		</td>
		</tr>
		
		
		<%-- ---------------- --%>
		<%-- DIALOG SMS		  --%>
		<%-- ---------------- --%>
		<tr>
		<td>
			<div id="dialogSMS" title="Dialog">
				 	<table>
				 		<tr>
							<td colspan="3" class="text14" align="left" >Send SMS med lenke til TKeventGrabber</td>
   						</tr>
						<tr height="10"><td></td></tr>
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
		 							<option value="EN" >Engelsk</option>
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
		
		
		
		
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

