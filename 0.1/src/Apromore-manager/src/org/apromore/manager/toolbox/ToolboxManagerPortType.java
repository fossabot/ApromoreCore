package org.apromore.manager.toolbox;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.2.9
 * Mon Dec 06 11:48:36 CET 2010
 * Generated source version: 2.2.9
 * 
 */
 
@WebService(targetNamespace = "http://www.apromore.org/toolbox/service_manager", name = "ToolboxManagerPortType")
@XmlSeeAlso({org.apromore.manager.model_toolbox.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ToolboxManagerPortType {

    @WebResult(name = "SearchForSimilarProcessesOutputMsg", targetNamespace = "http://www.apromore.org/toolbox/model_manager", partName = "payload")
    @WebMethod(operationName = "SearchForSimilarProcesses")
    public org.apromore.manager.model_toolbox.SearchForSimilarProcessesOutputMsgType searchForSimilarProcesses(
        @WebParam(partName = "payload", name = "SearchForSimilarProcessesInputMsg", targetNamespace = "http://www.apromore.org/toolbox/model_manager")
        org.apromore.manager.model_toolbox.SearchForSimilarProcessesInputMsgType payload
    );

    @WebResult(name = "MergeProcessesOutputMsg", targetNamespace = "http://www.apromore.org/toolbox/model_manager", partName = "payload")
    @WebMethod(operationName = "MergeProcesses")
    public org.apromore.manager.model_toolbox.MergeProcessesOutputMsgType mergeProcesses(
        @WebParam(partName = "payload", name = "MergeProcessesInputMsg", targetNamespace = "http://www.apromore.org/toolbox/model_manager")
        org.apromore.manager.model_toolbox.MergeProcessesInputMsgType payload
    );
}
