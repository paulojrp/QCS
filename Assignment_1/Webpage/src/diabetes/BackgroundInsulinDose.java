package diabetes;

import com.sun.xml.internal.ws.client.BindingProviderProperties;

import javax.xml.ws.BindingProvider;
import java.util.Map;

/**
 * Created by jpedrom on 11/05/15.
 */

public class BackgroundInsulinDose {

    private int next = 0;
    //private String links[] = new String[] {"diabetes.qcs12.InsulinDoseCalculatorService()", "diabetes.liis_lab.InsulinDoseCalculatorService()", "diabetes.qcs01.InsulinDoseCalculatorImpService()"};
    private int input3_1;       /*weight*/
    private int number_Ws = 3;
    private int n = 3;
    private int results[] = new int[n];
    private Webservice ws_threads[] = new Webservice[n];

    public int getInput3_1() {
        return input3_1;
    }

    public void setInput3_1(int input3_1) {
        this.input3_1 = input3_1;
    }

    public int getResult(){
        int result = 0;
        int i;

        for(i = 0; i < n; i++) {
            ws_threads[i] = new Webservice(next);
            next = (next + 1) % number_Ws;
            ws_threads[i].start();
        }

        for(i=0; i < n; i++) {
            try {
                ws_threads[i].join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.results[i] = this.ws_threads[i].getResult();
            System.out.println(this.results[i]);
        }

        return result;
    }

    public class Webservice extends Thread{
        private int result;
        private int wb;

        public Webservice(int wb){
            this.wb = wb;
            this.result = -1;
        }

        public int getResult(){
            return this.result;
        }

        public void run(){
            try {
                if (wb == 0) {
                    diabetes.qcs12.InsulinDoseCalculatorService service = new diabetes.qcs12.InsulinDoseCalculatorService();
                    diabetes.qcs12.InsulinDoseCalculator proxy = service.getInsulinDoseCalculatorPort();
                    //((BindingProvider) proxy).getRequestContext().put("javax.xml.ws.client.connectionTimeout", "100");
                    Map<String, Object> temp = ((BindingProvider) proxy).getRequestContext();
                    temp.put(BindingProviderProperties.REQUEST_TIMEOUT, 1000);
                    temp.put(BindingProviderProperties.CONNECT_TIMEOUT, 1000);
                    result = proxy.backgroundInsulinDose(getInput3_1());
                } else if (wb == 1) {
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
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
            //System.out.println(wb + " : " + result);
        }
    }
}
