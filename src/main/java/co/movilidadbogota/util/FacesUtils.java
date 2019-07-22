package co.movilidadbogota.util;

import javax.faces.component.UICommand;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class FacesUtils {
	
	public static FacesContext obtieneContexto(){
		return FacesContext.getCurrentInstance();
	}
	
	public static UIViewRoot obtieneVista(){
		return obtieneContexto().getViewRoot();
	}
	
	public static UIInput obtieneComponenteEntrada(String idComponente){
		return (UIInput)obtieneVista().findComponent(idComponente);
	}
	
	public static UIOutput obtieneComponenteSalida(String idComponente){
		return (UIOutput)obtieneVista().findComponent(idComponente);
	}
	
	public static UICommand obtieneComponenteComando(String idComponente){
		return (UICommand)obtieneVista().findComponent(idComponente);
	}
	
	public static void colocaBeanEnSesion(String bean, Object Objbean){
		obtieneContexto().getExternalContext().getSessionMap().put(bean, Objbean);
	}

}
