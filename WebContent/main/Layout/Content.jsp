<jsp:useBean id="builderBean" scope="request" class="app.API.Layout.ContentBuilderBean"/>
<%@ taglib uri="http://jakarta.apache.org/taglibs/i18n-1.0" prefix="i18n"  %>
<%=builderBean.getPageContent()%>
