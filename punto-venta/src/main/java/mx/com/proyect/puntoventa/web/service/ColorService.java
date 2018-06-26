package mx.com.proyect.puntoventa.web.service;

import java.util.List;
import mx.com.proyect.puntoventa.web.model.ColorDTO;


public interface ColorService {
	
	ColorDTO add(ColorDTO color);
	List<ColorDTO> getAll();
	boolean update(ColorDTO color);
	boolean delete(ColorDTO color);
	ColorDTO getById(float colorId);
	ColorDTO getByDescription(String description);

}
