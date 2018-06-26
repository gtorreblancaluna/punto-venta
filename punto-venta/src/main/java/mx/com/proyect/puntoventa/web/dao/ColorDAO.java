package mx.com.proyect.puntoventa.web.dao;

import java.util.List;

import mx.com.proyect.puntoventa.web.model.ColorDTO;

public interface ColorDAO {
	
	ColorDTO add(ColorDTO color);
	List<ColorDTO> getAll();
	boolean update(ColorDTO color);
	boolean delete(ColorDTO color);
	ColorDTO getById(float colorId);
	ColorDTO getByDescription(String description);

}
