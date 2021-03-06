package cl.zpricing.avant.web.administrators;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import cl.zpricing.avant.model.Publico;
import cl.zpricing.avant.servicios.PublicoDao;
import cl.zpricing.avant.web.form.PublicosForm;

/**
 * <b>Clase controladora de la vista publicos</b>
 * 
 * Registro de versiones:
 * <ul>
 * <li>1.0 21-12-2008 Daniel Est�vez Garay: versi�n inicial.</li>
 * </ul>
 * <P>
 * <B>Todos los derechos reservados por Zetha Pricing.</B>
 * <P>
 */
public class PublicosController extends SimpleFormController {

	/**
	 * Impresi�n de log.
	 */
	private Logger log = (Logger) Logger.getLogger(this.getClass());

	private PublicoDao publicoDao;

	/**
	 * @return the publicoDao
	 */
	public PublicoDao getPublicoDao() {
		return publicoDao;
	}

	/**
	 * @param publicoDao
	 *            the publicoDao to set
	 */
	public void setPublicoDao(PublicoDao publicoDao) {
		this.publicoDao = publicoDao;
	}

	/**
	 * 
	 * M�todo responsable de la vista publicos al hacer submit en su form
	 * asociado.
	 * 
	 * <P>
	 * Registro de versiones:
	 * <ul>
	 * <li>1.0 21-12-2008 Daniel Est�vez Garay: Versi�n Inicial</li>
	 * </ul>
	 * </P>
	 * 
	 * @param request
	 *            Solicitud HTTP
	 * @param response
	 *            Respuesta HTTP
	 * @param command
	 *            Objeto recibido por el form asociado a la vista
	 * @param errors
	 *            Errores
	 * @return Modelo y vista para publicos
	 * @throws Exception
	 * @since 1.0
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		PublicosForm form = (PublicosForm) command;
		int id = Integer.parseInt(form.getId());
		Publico publico = publicoDao.obtenerPublico(id);

		publico.setDescripcion(form.getDescripcion());
		publicoDao.actualizarPublico(publico);

		ModelAndView mv = showForm(request, response, errors);
		form.setDescripcion(publico.getDescripcion());
		return mv;
	}

	/**
	 * 
	 * Objeto responsable de crear el form asociado a la vista publicos.
	 * 
	 * <P>
	 * Registro de versiones:
	 * <ul>
	 * <li>1.0 21-12-2008 Daniel Est�vez Garay: Versi�n Inicial</li>
	 * </ul>
	 * </P>
	 * 
	 * @param request
	 *            Solicitud HTTP.
	 * @return objeto PublicosForm
	 * @throws ServletException
	 * @since 1.0
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws ServletException {
		log.debug("Iniciando formBackingObject...");
		PublicosForm form = new PublicosForm();

		return form;
	}

	/**
	 * 
	 * Objeto responsable de establecer las variables globales en la vista
	 * publicos.
	 * 
	 * <P>
	 * Registro de versiones:
	 * <ul>
	 * <li>1.0 21-12-2008 Daniel Est�vez Garay: Versi�n Inicial</li>
	 * </ul>
	 * </P>
	 * 
	 * @param request
	 *            Solicitud HTTP
	 * @return Map de variables
	 * @throws Exception
	 * @since 1.0
	 */
	protected Map<String, Object> referenceData(HttpServletRequest request)
			throws Exception {
		Map<String, Object> mapPublico = new HashMap<String, Object>(1);
		mapPublico.put("publicos", publicoDao.obtenerPublicos());
		return mapPublico;
	}

}
