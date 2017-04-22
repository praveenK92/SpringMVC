package learn.mvc.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { AppConfig.class };
	}
	
	/*@Override
	protected void customizeRegistration(Dynamic registration){
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	//add Servlets & Filters
	@Override
	public void onStartup(ServletContext servletContext)throws ServletException {
		Dynamic myServlet =servletContext.addServlet("myServlet", MyServlet.class);
		myServlet.addMapping("/custom/**");
		
		javax.servlet.FilterRegistration.Dynamic myFilter=servletContext.addFilter("myFilter", MyFilter.class);
		myFilter.addMappingForUrlPatterns(null, false, "/custom/*");
	}
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new MyFilter() };
	}*/
	
	/*@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration
	}*/
}
