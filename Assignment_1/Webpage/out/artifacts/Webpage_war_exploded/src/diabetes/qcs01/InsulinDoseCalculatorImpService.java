
package diabetes.qcs01;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "InsulinDoseCalculatorImpService", targetNamespace = "http://server/", wsdlLocation = "http://qcs01.dei.uc.pt:8080/InsulinDoseCalculator?wsdl")
public class InsulinDoseCalculatorImpService
    extends Service
{

    private final static URL INSULINDOSECALCULATORIMPSERVICE_WSDL_LOCATION;
    private final static WebServiceException INSULINDOSECALCULATORIMPSERVICE_EXCEPTION;
    private final static QName INSULINDOSECALCULATORIMPSERVICE_QNAME = new QName("http://server/", "InsulinDoseCalculatorImpService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://qcs01.dei.uc.pt:8080/InsulinDoseCalculator?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        INSULINDOSECALCULATORIMPSERVICE_WSDL_LOCATION = url;
        INSULINDOSECALCULATORIMPSERVICE_EXCEPTION = e;
    }

    public InsulinDoseCalculatorImpService() {
        super(__getWsdlLocation(), INSULINDOSECALCULATORIMPSERVICE_QNAME);
    }

    public InsulinDoseCalculatorImpService(WebServiceFeature... features) {
        super(__getWsdlLocation(), INSULINDOSECALCULATORIMPSERVICE_QNAME, features);
    }

    public InsulinDoseCalculatorImpService(URL wsdlLocation) {
        super(wsdlLocation, INSULINDOSECALCULATORIMPSERVICE_QNAME);
    }

    public InsulinDoseCalculatorImpService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, INSULINDOSECALCULATORIMPSERVICE_QNAME, features);
    }

    public InsulinDoseCalculatorImpService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public InsulinDoseCalculatorImpService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns InsulinDoseCalculator
     */
    @WebEndpoint(name = "InsulinDoseCalculatorImpPort")
    public InsulinDoseCalculator getInsulinDoseCalculatorImpPort() {
        return super.getPort(new QName("http://server/", "InsulinDoseCalculatorImpPort"), InsulinDoseCalculator.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns InsulinDoseCalculator
     */
    @WebEndpoint(name = "InsulinDoseCalculatorImpPort")
    public InsulinDoseCalculator getInsulinDoseCalculatorImpPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server/", "InsulinDoseCalculatorImpPort"), InsulinDoseCalculator.class, features);
    }

    private static URL __getWsdlLocation() {
        if (INSULINDOSECALCULATORIMPSERVICE_EXCEPTION!= null) {
            throw INSULINDOSECALCULATORIMPSERVICE_EXCEPTION;
        }
        return INSULINDOSECALCULATORIMPSERVICE_WSDL_LOCATION;
    }

}
