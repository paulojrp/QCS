package diabetes;

import com.sun.xml.internal.ws.client.BindingProviderProperties;

import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.Map;

/**
 * Created by jpedrom on 11/05/15.
 */

public class BackgroundInsulinDose {

    private int input3_1;       /*weight*/
    private int next = 0;
    private String links[] = new String[] {"http://qcs12.dei.uc.pt:8080/insulin?wsdl", "http://liis-lab.dei.uc.pt:8080/Server?wsdl",
            "http://qcs01.dei.uc.pt:8080/InsulinDoseCalculator?wsdl", "http://qcs02.dei.uc.pt:8080/insulinDosage?wsdl",
            "http://qcs05.dei.uc.pt:8080/insulin?wsdl", "http://qcs06.dei.uc.pt:8080/insulin?wsdl", "http://qcs07.dei.uc.pt:8080/insulin?wsdl",
            "http://qcs08.dei.uc.pt:8080/InsulinDoseCalculator?wsdl", "http://qcs10.dei.uc.pt:8080/InsulinDoseCalculator?wsdl",
            "http://qcs11.dei.uc.pt:8080/insulin?wsdl", "http://qcs16.dei.uc.pt:8080/InsulinDoseCalculator?wsdl", "http://vm-sgd17.dei.uc.pt/InsulinDoseCalculator?wsdl"};
    private int n = 3;
    private int results[] = new int[n];
    private Webservice ws_threads[] = new Webservice[n];

    public int getInput3_1() {
        return input3_1;
    }

    public void setInput3_1(int input3_1) {
        this.input3_1 = input3_1;
    }


    public String getResult() {
        int result = 0, i, temp;

        this.next = 0;

        for(i = 0; i < 3; i++) {
            execution();

            temp = Voter.voter(this.results, this.n);

            if (temp != -999) {
                result = temp;
                break;
            }
        }
        if(result==-999)
            return "Unable to find any result";
        return Integer.toString(result);
    }

    public String getWSName(int i) {
        return this.ws_threads[i].wb;
    }

    public String getWSResult(int i) {
        if (results[i] == -999)
            return "Timeout";
        return Integer.toString(results[i]);
    }

    private void execution() {
        int i;

        for(i = 0; i < n; i++) {
            ws_threads[i] = new Webservice(this.links[next]);
            next = (next + 1) % links.length;
            ws_threads[i].start();
        }

        for(i=0; i < n; i++) {
            try {
                ws_threads[i].join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.results[i] = this.ws_threads[i].getResult();
        }
    }

    public class Webservice extends Thread{
        private int result;
        private String wb;

        public Webservice(String wb){
            this.wb = wb;
            this.result = -999;
        }

        public int getResult(){
            return this.result;
        }

        public void run(){
            try {
                //if (wb == 0) {
                diabetes.qcs12.InsulinDoseCalculatorService service = new diabetes.qcs12.InsulinDoseCalculatorService(new URL(this.wb));
                diabetes.qcs12.InsulinDoseCalculator proxy = service.getInsulinDoseCalculatorPort();

                //((BindingProvider) proxy).getRequestContext().put("javax.xml.ws.client.connectionTimeout", "100");
                Map<String, Object> temp = ((BindingProvider) proxy).getRequestContext();
                temp.put(BindingProviderProperties.REQUEST_TIMEOUT, 1000);
                temp.put(BindingProviderProperties.CONNECT_TIMEOUT, 1000);
                result = proxy.backgroundInsulinDose(getInput3_1());
                /*} else if (wb == 1) {
                    diabetes.liis_lab.InsulinDoseCalculatorService service = new diabetes.liis_lab.InsulinDoseCalculatorService();
                    diabetes.liis_lab.InsulinDoseCalculator proxy = service.getInsulinDoseCalculatorPort();
                    Map<String, Object> temp = ((BindingProvider) proxy).getRequestContext();
                    temp.put(BindingProviderProperties.REQUEST_TIMEOUT, 1000);
                    temp.put(BindingProviderProperties.CONNECT_TIMEOUT, 1000);
                    result = proxy.backgroundInsulinDose(getInput3_1());
                } else if (wb == 2) {
                    diabetes.qcs01.InsulinDoseCalculatorImpService service = new diabetes.qcs01.InsulinDoseCalculatorImpService();
                    diabetes.qcs01.InsulinDoseCalculator proxy = service.getInsulinDoseCalculatorImpPort();
                    Map<String, Object> temp = ((BindingProvider) proxy).getRequestContext();
                    temp.put(BindingProviderProperties.REQUEST_TIMEOUT, 1000);
                    temp.put(BindingProviderProperties.CONNECT_TIMEOUT, 1000);
                    result = proxy.backgroundInsulinDose(getInput3_1());
                }*/
            }
            catch (Exception e){
                System.out.println(e);
            }
            //System.out.println(wb + " : " + result);
        }
    }
}
